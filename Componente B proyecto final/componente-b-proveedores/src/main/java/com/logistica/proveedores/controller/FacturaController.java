package com.logistica.proveedores.controller;

import com.logistica.proveedores.model.CalcularTotalFactura200Response;
import com.logistica.proveedores.model.Factura;
import com.logistica.proveedores.model.FacturaInput;
import com.logistica.proveedores.model.ListarFacturas200Response;
import com.logistica.proveedores.model.PagarFacturaRequest;
import com.logistica.proveedores.service.FacturaService;
import com.logistica.proveedores.spec.FacturasApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controlador REST para gestión de Facturas
 * Implementa la interfaz generada por OpenAPI
 * Conectado con FacturaService que usa Componente C
 * Incluye integración circular con Componente A
 */
@RestController
public class FacturaController implements FacturasApi {
    
    private static final Logger logger = LoggerFactory.getLogger(FacturaController.class);
    
    private final FacturaService facturaService;
    
    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }
    
    /**
     * POST /api/v1/facturas - Crear una factura
     */
    @Override
    public ResponseEntity<Factura> crearFactura(@Valid FacturaInput facturaInput) {
        try {
            logger.info("Solicitud para crear factura para proveedor ID: {}", 
                       facturaInput.getProveedorId());
            Factura factura = facturaService.crearFactura(facturaInput);
            return ResponseEntity.status(HttpStatus.CREATED).body(factura);
        } catch (IllegalArgumentException e) {
            logger.error("Error al crear factura: {}", e.getMessage());
            throw e;
        }
    }
    
    /**
     * GET /api/v1/facturas - Listar todas las facturas
     */
    @Override
    public ResponseEntity<ListarFacturas200Response> listarFacturas(
            Long proveedorId,
            String estado,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            Integer page,
            Integer size) {
        try {
            logger.debug("Solicitud para listar facturas - proveedorId: {}, estado: {}", 
                        proveedorId, estado);
            
            Page<Factura> facturasPage = facturaService.listarFacturas(
                proveedorId, estado, fechaInicio, fechaFin, page, size
            );
            
            // Crear respuesta con paginación
            ListarFacturas200Response response = new ListarFacturas200Response();
            response.setContent(facturasPage.getContent());
            response.setTotalElements((int) facturasPage.getTotalElements());
            response.setTotalPages(facturasPage.getTotalPages());
            response.setCurrentPage(facturasPage.getNumber());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al listar facturas: {}", e.getMessage());
            throw e;
        }
    }
    
    /**
     * GET /api/v1/facturas/{id} - Obtener una factura por ID
     */
    @Override
    public ResponseEntity<Factura> obtenerFacturaPorId(Long id) {
        try {
            logger.debug("Solicitud para obtener factura con ID: {}", id);
            Factura factura = facturaService.obtenerFacturaPorId(id);
            return ResponseEntity.ok(factura);
        } catch (IllegalArgumentException e) {
            logger.error("Factura no encontrada: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * PUT /api/v1/facturas/{id} - Actualizar una factura
     */
    @Override
    public ResponseEntity<Factura> actualizarFactura(
            Long id, 
            @Valid FacturaInput facturaInput) {
        try {
            logger.info("Solicitud para actualizar factura con ID: {}", id);
            Factura factura = facturaService.actualizarFactura(id, facturaInput);
            return ResponseEntity.ok(factura);
        } catch (IllegalArgumentException e) {
            logger.error("Error al actualizar factura: {}", e.getMessage());
            if (e.getMessage().contains("no encontrada")) {
                return ResponseEntity.notFound().build();
            }
            throw e;
        }
    }
    
    /**
     * GET /api/v1/facturas/{id}/calcular-total - Calcular total de la factura
     * Usa Componente C para los cálculos
     */
    @Override
    public ResponseEntity<CalcularTotalFactura200Response> calcularTotalFactura(Long id) {
        try {
            logger.debug("Solicitud para calcular total de factura ID: {}", id);
            CalcularTotalFactura200Response response = facturaService.calcularTotalFactura(id);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            logger.error("Error al calcular total: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * POST /api/v1/facturas/{id}/pagar - Registrar pago de factura
     */
    @Override
    public ResponseEntity<Factura> pagarFactura(
            Long id, 
            @Valid PagarFacturaRequest pagarFacturaRequest) {
        try {
            logger.info("Solicitud para registrar pago de factura ID: {}", id);
            Factura factura = facturaService.pagarFactura(id, pagarFacturaRequest);
            return ResponseEntity.ok(factura);
        } catch (IllegalArgumentException e) {
            logger.error("Error al registrar pago: {}", e.getMessage());
            if (e.getMessage().contains("no encontrada")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * INTEGRACIÓN CIRCULAR: Consultar pedido del Componente A
     * Endpoint: GET /api/v1/facturas/consultar-pedido/{pedidoId}
     * 
     * Este endpoint demuestra la integración circular:
     * Componente B → Componente C (IntegradorAPI) → Componente A
     */
    @GetMapping("/facturas/consultar-pedido/{pedidoId}")
    public ResponseEntity<String> consultarPedidoComponenteA(
            @PathVariable Long pedidoId) {
        try {
            logger.info("🔄 INTEGRACIÓN CIRCULAR: Consultando pedido {} desde Componente A", 
                       pedidoId);
            
            String pedidoJson = facturaService.consultarPedidoComponenteA(pedidoId);
            
            return ResponseEntity.ok(pedidoJson);
        } catch (Exception e) {
            logger.error("❌ Error en integración circular: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}