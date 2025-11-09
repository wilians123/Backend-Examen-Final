package com.umg.edu.gt.dev.demo.service;

import com.logistica.commons.dto.ItemDTO;
import com.logistica.commons.util.GeneradorCodigos;
import com.logistica.commons.util.OperacionesNegocio;
import com.umg.edu.gt.dev.demo.entity.ItemPedidoEntity;
import com.umg.edu.gt.dev.demo.entity.PedidoEntity;
import com.umg.edu.gt.dev.demo.repository.ClienteRepository;
import com.umg.edu.gt.dev.demo.repository.PedidoRepository;
import com.umg.edu.gt.dev.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio de lógica de negocio para Pedidos
 * Usa intensivamente el Componente C para cálculos y códigos
 */
@Service
public class PedidoService {

    private static final Logger logger = LoggerFactory.getLogger(PedidoService.class);

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    /**
     * Crear un nuevo pedido
     * Usa Componente C para: generar código, calcular totales e impuestos
     */
    @Transactional
    public Pedido crearPedido(PedidoInput input) {
        logger.info("Creando nuevo pedido para cliente ID: {}", input.getClienteId());

        // Validar que el cliente existe
        if (!clienteRepository.existsById(input.getClienteId())) {
            throw new IllegalArgumentException("Cliente no encontrado con ID: " + input.getClienteId());
        }

        // Validar que hay items
        if (input.getItems() == null || input.getItems().isEmpty()) {
            throw new IllegalArgumentException("El pedido debe tener al menos un item");
        }

        // Generar código único usando Componente C
        String codigoPedido = GeneradorCodigos.generarCodigoPedido();
        logger.debug("Código de pedido generado: {}", codigoPedido);

        // Crear entidad de pedido
        PedidoEntity pedidoEntity = new PedidoEntity(input.getClienteId(), codigoPedido);

        // Convertir items de input a ItemDTO para cálculos
        List<ItemDTO> itemsDTO = new ArrayList<>();
        for (ItemPedidoInput itemInput : input.getItems()) {
            ItemDTO itemDTO = new ItemDTO(
                    itemInput.getProductoNombre(),
                    itemInput.getCantidad(),
                    itemInput.getPrecioUnitario()
            );
            itemsDTO.add(itemDTO);

            // Crear entidad de item
            ItemPedidoEntity itemEntity = new ItemPedidoEntity(
                    itemInput.getProductoNombre(),
                    itemInput.getCantidad(),
                    itemInput.getPrecioUnitario()
            );
            pedidoEntity.agregarItem(itemEntity);
        }

        // Calcular totales usando Componente C
        double subtotal = OperacionesNegocio.calcularTotal(itemsDTO);
        double impuestos = OperacionesNegocio.calcularImpuestos(subtotal);
        double total = subtotal + impuestos;

        logger.debug("Cálculos - Subtotal: {}, Impuestos: {}, Total: {}", subtotal, impuestos, total);

        // Asignar totales al pedido
        pedidoEntity.setSubtotal(subtotal);
        pedidoEntity.setImpuestos(impuestos);
        pedidoEntity.setTotal(total);

        // Guardar en BD (cascade guarda los items automáticamente)
        PedidoEntity saved = pedidoRepository.save(pedidoEntity);
        logger.info("Pedido creado exitosamente con ID: {}", saved.getId());

        // Convertir a DTO
        return convertirADTO(saved);
    }

    /**
     * Listar pedidos con filtros opcionales
     */
    @Transactional(readOnly = true)
    public Page<Pedido> listarPedidos(Long clienteId, String estado, Integer page, Integer size) {
        logger.debug("Listando pedidos - clienteId: {}, estado: {}, página: {}, tamaño: {}", 
                     clienteId, estado, page, size);

        int pageNum = (page != null && page >= 0) ? page : 0;
        int pageSize = (size != null && size > 0) ? Math.min(size, 100) : 10;

        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("fechaPedido").descending());

        Page<PedidoEntity> pedidosPage;

        if (clienteId != null) {
            pedidosPage = pedidoRepository.findByClienteId(clienteId, pageable);
        } else {
            pedidosPage = pedidoRepository.findAll(pageable);
        }

        return pedidosPage.map(this::convertirADTO);
    }

    /**
     * Obtener pedido por ID
     */
    @Transactional(readOnly = true)
    public Pedido obtenerPedidoPorId(Long id) {
        logger.debug("Obteniendo pedido con ID: {}", id);

        PedidoEntity entity = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado con ID: " + id));

        return convertirADTO(entity);
    }

    /**
     * Actualizar pedido
     */
    @Transactional
    public Pedido actualizarPedido(Long id, PedidoInput input) {
        logger.info("Actualizando pedido con ID: {}", id);

        // Buscar pedido existente
        PedidoEntity entity = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado con ID: " + id));

        // Validar que el cliente existe
        if (!clienteRepository.existsById(input.getClienteId())) {
            throw new IllegalArgumentException("Cliente no encontrado con ID: " + input.getClienteId());
        }

        // Limpiar items anteriores
        entity.getItems().clear();

        // Convertir items para cálculos
        List<ItemDTO> itemsDTO = new ArrayList<>();
        for (ItemPedidoInput itemInput : input.getItems()) {
            ItemDTO itemDTO = new ItemDTO(
                    itemInput.getProductoNombre(),
                    itemInput.getCantidad(),
                    itemInput.getPrecioUnitario()
            );
            itemsDTO.add(itemDTO);

            // Crear nueva entidad de item
            ItemPedidoEntity itemEntity = new ItemPedidoEntity(
                    itemInput.getProductoNombre(),
                    itemInput.getCantidad(),
                    itemInput.getPrecioUnitario()
            );
            entity.agregarItem(itemEntity);
        }

        // Recalcular totales usando Componente C
        double subtotal = OperacionesNegocio.calcularTotal(itemsDTO);
        double impuestos = OperacionesNegocio.calcularImpuestos(subtotal);
        double total = subtotal + impuestos;

        // Actualizar totales
        entity.setClienteId(input.getClienteId());
        entity.setSubtotal(subtotal);
        entity.setImpuestos(impuestos);
        entity.setTotal(total);

        // Guardar cambios
        PedidoEntity updated = pedidoRepository.save(entity);
        logger.info("Pedido actualizado exitosamente con ID: {}", updated.getId());

        return convertirADTO(updated);
    }

    /**
     * Calcular total de un pedido usando Componente C
     */
    @Transactional(readOnly = true)
    public CalcularTotalPedido200Response calcularTotalPedido(Long id) {
        logger.debug("Calculando total del pedido con ID: {}", id);

        PedidoEntity entity = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado con ID: " + id));

        // Crear respuesta
        CalcularTotalPedido200Response response = new CalcularTotalPedido200Response();
        response.setPedidoId(entity.getId());
        response.setSubtotal(entity.getSubtotal());
        response.setImpuestos(entity.getImpuestos());
        response.setTotal(entity.getTotal());

        return response;
    }

    /**
     * Convertir PedidoEntity a DTO Pedido (modelo OpenAPI)
     */
    private Pedido convertirADTO(PedidoEntity entity) {
        Pedido dto = new Pedido();
        dto.setId(entity.getId());
        dto.setClienteId(entity.getClienteId());
        dto.setCodigoPedido(entity.getCodigoPedido());
        dto.setSubtotal(entity.getSubtotal());
        dto.setImpuestos(entity.getImpuestos());
        dto.setTotal(entity.getTotal());

        // Convertir items
        List<ItemPedido> itemsDTO = entity.getItems().stream()
                .map(this::convertirItemADTO)
                .collect(Collectors.toList());
        dto.setItems(itemsDTO);

        return dto;
    }

    /**
     * Convertir ItemPedidoEntity a ItemPedido DTO
     */
    private ItemPedido convertirItemADTO(ItemPedidoEntity entity) {
        ItemPedido dto = new ItemPedido();
        dto.setId(entity.getId());
        dto.setProductoNombre(entity.getProductoNombre());
        dto.setCantidad(entity.getCantidad());
        dto.setPrecioUnitario(entity.getPrecioUnitario());
        dto.setSubtotal(entity.getSubtotal());
        return dto;
    }
}