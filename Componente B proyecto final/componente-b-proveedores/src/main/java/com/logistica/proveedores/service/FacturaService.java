package com.logistica.proveedores.service;

import com.logistica.commons.dto.ItemDTO;
import com.logistica.commons.util.GeneradorCodigos;
import com.logistica.commons.util.OperacionesNegocio;
import com.logistica.commons.integration.IntegradorAPI;
import com.logistica.proveedores.entity.FacturaEntity;
import com.logistica.proveedores.entity.ItemFacturaEntity;
import com.logistica.proveedores.repository.FacturaRepository;
import com.logistica.proveedores.repository.ProveedorRepository;
import com.logistica.proveedores.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio de lógica de negocio para Facturas
 * Usa intensivamente el Componente C para cálculos y códigos
 * Implementa integración circular con Componente A
 */
@Service
public class FacturaService {
    
    private static final Logger logger = LoggerFactory.getLogger(FacturaService.class);
    
    private final FacturaRepository facturaRepository;
    private final ProveedorRepository proveedorRepository;
    
    public FacturaService(FacturaRepository facturaRepository, 
                         ProveedorRepository proveedorRepository) {
        this.facturaRepository = facturaRepository;
        this.proveedorRepository = proveedorRepository;
    }
    
    /**
     * Crear una nueva factura
     * Usa Componente C para: generar código, calcular totales e impuestos
     * INTEGRACIÓN CIRCULAR: Puede consultar pedidos del Componente A
     */
    @Transactional
    public Factura crearFactura(FacturaInput input) {
        logger.info("Creando nueva factura para proveedor ID: {}", input.getProveedorId());
        
        // Validar que el proveedor existe
        if (!proveedorRepository.existsById(input.getProveedorId())) {
            throw new IllegalArgumentException("Proveedor no encontrado con ID: " + input.getProveedorId());
        }
        
        // Validar que hay items
        if (input.getItems() == null || input.getItems().isEmpty()) {
            throw new IllegalArgumentException("La factura debe tener al menos un item");
        }
        
        // Verificar que el número de factura no exista
        if (facturaRepository.existsByNumeroFactura(input.getNumeroFactura())) {
            throw new IllegalArgumentException("Ya existe una factura con el número: " + input.getNumeroFactura());
        }
        
        // Generar código único usando Componente C
        String codigoFactura = GeneradorCodigos.generarCodigoFactura();
        logger.debug("Código de factura generado: {}", codigoFactura);
        
        // Crear entidad de factura
        FacturaEntity facturaEntity = new FacturaEntity(
            input.getProveedorId(),
            input.getNumeroFactura(),
            codigoFactura,
            input.getFechaEmision(),
            input.getFechaVencimiento()
        );
        
        // Convertir items de input a ItemDTO para cálculos
        List<ItemDTO> itemsDTO = new ArrayList<>();
        for (ItemFacturaInput itemInput : input.getItems()) {
            ItemDTO itemDTO = new ItemDTO(
                itemInput.getDescripcion(),
                itemInput.getCantidad(),
                itemInput.getPrecioUnitario()
            );
            itemsDTO.add(itemDTO);
            
            // Crear entidad de item
            ItemFacturaEntity itemEntity = new ItemFacturaEntity(
                itemInput.getDescripcion(),
                itemInput.getCantidad(),
                itemInput.getPrecioUnitario()
            );
            facturaEntity.agregarItem(itemEntity);
        }
        
        // Calcular totales usando Componente C
        double subtotal = OperacionesNegocio.calcularTotal(itemsDTO);
        double impuestos = OperacionesNegocio.calcularImpuestos(subtotal);
        
        // Aplicar descuentos si existen
        double descuentos = (input.getDescuentos() != null) ? input.getDescuentos() : 0.0;
        if (descuentos > 0) {
            // Validar que el descuento no sea mayor al subtotal
            if (descuentos > subtotal) {
                throw new IllegalArgumentException("El descuento no puede ser mayor al subtotal");
            }
        }
        
        double total = subtotal + impuestos - descuentos;
        
        logger.debug("Cálculos - Subtotal: {}, Impuestos: {}, Descuentos: {}, Total: {}", 
                    subtotal, impuestos, descuentos, total);
        
        // Asignar totales a la factura
        facturaEntity.setSubtotal(subtotal);
        facturaEntity.setImpuestos(impuestos);
        facturaEntity.setDescuentos(descuentos);
        facturaEntity.setTotal(total);
        facturaEntity.setObservaciones(input.getObservaciones());
        
        // Guardar en BD (cascade guarda los items automáticamente)
        FacturaEntity saved = facturaRepository.save(facturaEntity);
        logger.info("Factura creada exitosamente con ID: {}", saved.getId());
        
        // Convertir a DTO
        return convertirADTO(saved);
    }
    
    /**
     * Listar facturas con filtros opcionales
     */
    @Transactional(readOnly = true)
    public Page<Factura> listarFacturas(Long proveedorId, String estado, 
                                        LocalDate fechaInicio, LocalDate fechaFin,
                                        Integer page, Integer size) {
        logger.debug("Listando facturas - proveedorId: {}, estado: {}, página: {}, tamaño: {}",
                    proveedorId, estado, page, size);
        
        int pageNum = (page != null && page >= 0) ? page : 0;
        int pageSize = (size != null && size > 0) ? Math.min(size, 100) : 10;
        
        Pageable pageable = PageRequest.of(pageNum, pageSize, 
                                          Sort.by("fechaEmision").descending());
        
        Page<FacturaEntity> facturasPage;
        
        // Aplicar filtros según estado
        if (estado != null) {
            switch (estado.toUpperCase()) {
                case "PAGADA":
                    facturasPage = facturaRepository.findFacturasPagadas(pageable);
                    break;
                case "VENCIDA":
                    facturasPage = facturaRepository.findFacturasVencidas(LocalDate.now(), pageable);
                    break;
                case "PENDIENTE":
                case "EMITIDA":
                    facturasPage = facturaRepository.findFacturasPendientes(pageable);
                    break;
                default:
                    facturasPage = aplicarFiltrosGenerales(proveedorId, fechaInicio, fechaFin, pageable);
            }
        } else {
            facturasPage = aplicarFiltrosGenerales(proveedorId, fechaInicio, fechaFin, pageable);
        }
        
        return facturasPage.map(this::convertirADTO);
    }
    
    /**
     * Aplicar filtros generales (proveedor, fechas)
     */
    private Page<FacturaEntity> aplicarFiltrosGenerales(Long proveedorId, LocalDate fechaInicio, 
                                                        LocalDate fechaFin, Pageable pageable) {
        if (proveedorId != null || fechaInicio != null || fechaFin != null) {
            return facturaRepository.buscarConFiltros(proveedorId, fechaInicio, fechaFin, pageable);
        } else {
            return facturaRepository.findAll(pageable);
        }
    }
    
    /**
     * Obtener factura por ID
     */
    @Transactional(readOnly = true)
    public Factura obtenerFacturaPorId(Long id) {
        logger.debug("Obteniendo factura con ID: {}", id);
        
        FacturaEntity entity = facturaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada con ID: " + id));
        
        return convertirADTO(entity);
    }
    
    /**
     * Actualizar factura
     */
    @Transactional
    public Factura actualizarFactura(Long id, FacturaInput input) {
        logger.info("Actualizando factura con ID: {}", id);
        
        // Buscar factura existente
        FacturaEntity entity = facturaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada con ID: " + id));
        
        // Validar que el proveedor existe
        if (!proveedorRepository.existsById(input.getProveedorId())) {
            throw new IllegalArgumentException("Proveedor no encontrado con ID: " + input.getProveedorId());
        }
        
        // Validar que hay items
        if (input.getItems() == null || input.getItems().isEmpty()) {
            throw new IllegalArgumentException("La factura debe tener al menos un item");
        }
        
        // Si cambió el número de factura, verificar que no exista
        if (!entity.getNumeroFactura().equals(input.getNumeroFactura()) &&
            facturaRepository.existsByNumeroFactura(input.getNumeroFactura())) {
            throw new IllegalArgumentException("Ya existe una factura con el número: " + input.getNumeroFactura());
        }
        
        // Limpiar items anteriores
        entity.getItems().clear();
        
        // Convertir items para cálculos
        List<ItemDTO> itemsDTO = new ArrayList<>();
        for (ItemFacturaInput itemInput : input.getItems()) {
            ItemDTO itemDTO = new ItemDTO(
                itemInput.getDescripcion(),
                itemInput.getCantidad(),
                itemInput.getPrecioUnitario()
            );
            itemsDTO.add(itemDTO);
            
            // Crear nueva entidad de item
            ItemFacturaEntity itemEntity = new ItemFacturaEntity(
                itemInput.getDescripcion(),
                itemInput.getCantidad(),
                itemInput.getPrecioUnitario()
            );
            entity.agregarItem(itemEntity);
        }
        
        // Recalcular totales usando Componente C
        double subtotal = OperacionesNegocio.calcularTotal(itemsDTO);
        double impuestos = OperacionesNegocio.calcularImpuestos(subtotal);
        double descuentos = (input.getDescuentos() != null) ? input.getDescuentos() : 0.0;
        
        if (descuentos > subtotal) {
            throw new IllegalArgumentException("El descuento no puede ser mayor al subtotal");
        }
        
        double total = subtotal + impuestos - descuentos;
        
        // Actualizar datos de la factura
        entity.setProveedorId(input.getProveedorId());
        entity.setNumeroFactura(input.getNumeroFactura());
        entity.setFechaEmision(input.getFechaEmision());
        entity.setFechaVencimiento(input.getFechaVencimiento());
        entity.setSubtotal(subtotal);
        entity.setImpuestos(impuestos);
        entity.setDescuentos(descuentos);
        entity.setTotal(total);
        entity.setObservaciones(input.getObservaciones());
        
        // Guardar cambios
        FacturaEntity updated = facturaRepository.save(entity);
        logger.info("Factura actualizada exitosamente con ID: {}", updated.getId());
        
        return convertirADTO(updated);
    }
    
    /**
     * Calcular total de una factura usando Componente C
     */
    @Transactional(readOnly = true)
    public CalcularTotalFactura200Response calcularTotalFactura(Long id) {
        logger.debug("Calculando total de factura con ID: {}", id);
        
        FacturaEntity entity = facturaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada con ID: " + id));
        
        // Crear respuesta
        CalcularTotalFactura200Response response = new CalcularTotalFactura200Response();
        response.setFacturaId(entity.getId());
        response.setSubtotal(entity.getSubtotal());
        response.setImpuestos(entity.getImpuestos());
        response.setDescuentos(entity.getDescuentos());
        response.setTotal(entity.getTotal());
        
        return response;
    }
    
    /**
     * Registrar pago de factura
     */
    @Transactional
    public Factura pagarFactura(Long id, PagarFacturaRequest pagoRequest) {
        logger.info("Registrando pago de factura con ID: {}", id);
        
        FacturaEntity entity = facturaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Factura no encontrada con ID: " + id));
        
        // Validar que la factura no esté ya pagada
        if (entity.getFechaPago() != null) {
            throw new IllegalArgumentException("La factura ya ha sido pagada");
        }
        
        // Registrar información del pago
        entity.setFechaPago(pagoRequest.getFechaPago());
        entity.setMetodoPago(pagoRequest.getMetodoPago().getValue());
        entity.setReferenciaPago(pagoRequest.getReferencia());
        
        // Guardar cambios
        FacturaEntity updated = facturaRepository.save(entity);
        logger.info("Pago registrado exitosamente para factura ID: {}", updated.getId());
        
        return convertirADTO(updated);
    }
    
    /**
     * INTEGRACIÓN CIRCULAR: Consultar pedido del Componente A
     * Este método demuestra la integración circular A ↔ B a través de C
     */
    @Transactional(readOnly = true)
    public String consultarPedidoComponenteA(Long pedidoId) {
        logger.info("🔄 INTEGRACIÓN CIRCULAR: Consultando pedido {} del Componente A", pedidoId);
        
        try {
            // Usar IntegradorAPI del Componente C para consultar Componente A
            String pedidoJson = IntegradorAPI.consultarPedido(pedidoId);
            logger.info("✅ Pedido obtenido exitosamente del Componente A");
            return pedidoJson;
        } catch (Exception e) {
            logger.error("❌ Error al consultar Componente A: {}", e.getMessage());
            throw new RuntimeException("Error al consultar pedido del Componente A: " + e.getMessage());
        }
    }
    
    /**
     * Convertir FacturaEntity a DTO Factura (modelo OpenAPI)
     */
    private Factura convertirADTO(FacturaEntity entity) {
        Factura dto = new Factura();
        dto.setId(entity.getId());
        dto.setProveedorId(entity.getProveedorId());
        dto.setNumeroFactura(entity.getNumeroFactura());
        dto.setCodigoFactura(entity.getCodigoFactura());
        dto.setFechaEmision(entity.getFechaEmision());
        dto.setFechaVencimiento(entity.getFechaVencimiento());
        dto.setFechaPago(entity.getFechaPago());
        dto.setSubtotal(entity.getSubtotal());
        dto.setImpuestos(entity.getImpuestos());
        dto.setDescuentos(entity.getDescuentos());
        dto.setTotal(entity.getTotal());
        dto.setObservaciones(entity.getObservaciones());
        
        // Determinar estado
        dto.setEstado(determinarEstado(entity));
        
        // Método de pago si existe
        if (entity.getMetodoPago() != null) {
            try {
                dto.setMetodoPago(Factura.MetodoPagoEnum.fromValue(entity.getMetodoPago()));
            } catch (IllegalArgumentException e) {
                logger.warn("Método de pago inválido: {}", entity.getMetodoPago());
            }
        }
        
        dto.setReferenciaPago(entity.getReferenciaPago());
        
        // Convertir items
        List<ItemFactura> itemsDTO = entity.getItems().stream()
            .map(this::convertirItemADTO)
            .collect(Collectors.toList());
        dto.setItems(itemsDTO);
        
        return dto;
    }
    
    /**
     * Determinar estado de la factura
     */
    private Factura.EstadoEnum determinarEstado(FacturaEntity entity) {
        if (entity.getFechaPago() != null) {
            return Factura.EstadoEnum.PAGADA;
        } else if (entity.getFechaVencimiento().isBefore(LocalDate.now())) {
            return Factura.EstadoEnum.VENCIDA;
        } else {
            return Factura.EstadoEnum.EMITIDA;
        }
    }
    
    /**
     * Convertir ItemFacturaEntity a ItemFactura DTO
     */
    private ItemFactura convertirItemADTO(ItemFacturaEntity entity) {
        ItemFactura dto = new ItemFactura();
        dto.setId(entity.getId());
        dto.setDescripcion(entity.getDescripcion());
        dto.setCantidad(entity.getCantidad());
        dto.setPrecioUnitario(entity.getPrecioUnitario());
        dto.setSubtotal(entity.getSubtotal());
        return dto;
    }
}