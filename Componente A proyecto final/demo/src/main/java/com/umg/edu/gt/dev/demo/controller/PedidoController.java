package com.umg.edu.gt.dev.demo.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.umg.edu.gt.dev.demo.service.PedidoService;
import com.umg.edu.gt.dev.model.CalcularTotalPedido200Response;
import com.umg.edu.gt.dev.model.ListarPedidos200Response;
import com.umg.edu.gt.dev.model.Pedido;
import com.umg.edu.gt.dev.model.PedidoInput;
import com.umg.edu.gt.dev.spec.PedidosApi;

import jakarta.validation.Valid;

/**
 * Controlador REST para gestión de Pedidos
 * Implementa la interfaz generada por OpenAPI
 * Conectado con PedidoService que usa Componente C
 */
@RestController
public class PedidoController implements PedidosApi {

    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);
    
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return PedidosApi.super.getRequest();
    }

    /**
     * POST /api/v1/pedidos - Crear un pedido
     */
    @Override
    public ResponseEntity<Pedido> crearPedido(@Valid PedidoInput pedidoInput) {
        try {
            logger.info("Solicitud para crear pedido para cliente ID: {}", pedidoInput.getClienteId());
            Pedido pedido = pedidoService.crearPedido(pedidoInput);
            return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
        } catch (IllegalArgumentException e) {
            logger.error("Error al crear pedido: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * GET /api/v1/pedidos - Listar todos los pedidos
     */
    @Override
    public ResponseEntity<ListarPedidos200Response> listarPedidos(
            Long clienteId,
            String estado,
            Integer page,
            Integer size) {
        try {
            logger.debug("Solicitud para listar pedidos - clienteId: {}, estado: {}", clienteId, estado);
            
            Page<Pedido> pedidosPage = pedidoService.listarPedidos(clienteId, estado, page, size);
            
            // Crear respuesta con paginación
            ListarPedidos200Response response = new ListarPedidos200Response();
            response.setContent(pedidosPage.getContent());
            response.setTotalElements((int) pedidosPage.getTotalElements());
            response.setTotalPages(pedidosPage.getTotalPages());
            response.setCurrentPage(pedidosPage.getNumber());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al listar pedidos: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * GET /api/v1/pedidos/{id} - Obtener un pedido por ID
     */
    @Override
    public ResponseEntity<Pedido> obtenerPedidoPorId(Long id) {
        try {
            logger.debug("Solicitud para obtener pedido con ID: {}", id);
            Pedido pedido = pedidoService.obtenerPedidoPorId(id);
            return ResponseEntity.ok(pedido);
        } catch (IllegalArgumentException e) {
            logger.error("Pedido no encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * PUT /api/v1/pedidos/{id} - Actualizar un pedido
     */
    @Override
    public ResponseEntity<Pedido> actualizarPedido(Long id, @Valid PedidoInput pedidoInput) {
        try {
            logger.info("Solicitud para actualizar pedido con ID: {}", id);
            Pedido pedido = pedidoService.actualizarPedido(id, pedidoInput);
            return ResponseEntity.ok(pedido);
        } catch (IllegalArgumentException e) {
            logger.error("Error al actualizar pedido: {}", e.getMessage());
            if (e.getMessage().contains("no encontrado")) {
                return ResponseEntity.notFound().build();
            }
            throw e;
        }
    }

    /**
     * GET /api/v1/pedidos/{id}/calcular-total - Calcular total del pedido
     * Usa Componente C para los cálculos
     */
    @Override
    public ResponseEntity<CalcularTotalPedido200Response> calcularTotalPedido(Long id) {
        try {
            logger.debug("Solicitud para calcular total del pedido ID: {}", id);
            CalcularTotalPedido200Response response = pedidoService.calcularTotalPedido(id);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            logger.error("Error al calcular total: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}