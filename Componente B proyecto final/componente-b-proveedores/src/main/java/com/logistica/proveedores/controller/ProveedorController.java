package com.logistica.proveedores.controller;

import com.logistica.proveedores.model.ListarProveedores200Response;
import com.logistica.proveedores.model.Proveedor;
import com.logistica.proveedores.model.ProveedorInput;
import com.logistica.proveedores.service.ProveedorService;
import com.logistica.proveedores.spec.ProveedoresApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

/**
 * Controlador REST para gestión de Proveedores
 * Implementa la interfaz generada por OpenAPI
 * Conectado con ProveedorService que usa Componente C
 */
@RestController
public class ProveedorController implements ProveedoresApi {
    
    private static final Logger logger = LoggerFactory.getLogger(ProveedorController.class);
    
    private final ProveedorService proveedorService;
    
    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }
    
    /**
     * POST /api/v1/proveedores - Crear un proveedor
     */
    @Override
    public ResponseEntity<Proveedor> crearProveedor(@Valid ProveedorInput proveedorInput) {
        try {
            logger.info("Solicitud para crear proveedor: {}", proveedorInput.getRazonSocial());
            Proveedor proveedor = proveedorService.crearProveedor(proveedorInput);
            return ResponseEntity.status(HttpStatus.CREATED).body(proveedor);
        } catch (IllegalArgumentException e) {
            logger.error("Error al crear proveedor: {}", e.getMessage());
            throw e;
        }
    }
    
    /**
     * GET /api/v1/proveedores - Listar todos los proveedores
     */
    @Override
    public ResponseEntity<ListarProveedores200Response> listarProveedores(
            Integer page, 
            Integer size, 
            Boolean activo) {
        try {
            logger.debug("Solicitud para listar proveedores - página: {}, tamaño: {}, activo: {}", 
                        page, size, activo);
            
            Page<Proveedor> proveedoresPage = proveedorService.listarProveedores(page, size, activo);
            
            // Crear respuesta con paginación
            ListarProveedores200Response response = new ListarProveedores200Response();
            response.setContent(proveedoresPage.getContent());
            response.setTotalElements((int) proveedoresPage.getTotalElements());
            response.setTotalPages(proveedoresPage.getTotalPages());
            response.setCurrentPage(proveedoresPage.getNumber());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al listar proveedores: {}", e.getMessage());
            throw e;
        }
    }
    
    /**
     * GET /api/v1/proveedores/{id} - Obtener un proveedor por ID
     */
    @Override
    public ResponseEntity<Proveedor> obtenerProveedorPorId(Long id) {
        try {
            logger.debug("Solicitud para obtener proveedor con ID: {}", id);
            Proveedor proveedor = proveedorService.obtenerProveedorPorId(id);
            return ResponseEntity.ok(proveedor);
        } catch (IllegalArgumentException e) {
            logger.error("Proveedor no encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * PUT /api/v1/proveedores/{id} - Actualizar un proveedor
     */
    @Override
    public ResponseEntity<Proveedor> actualizarProveedor(
            Long id, 
            @Valid ProveedorInput proveedorInput) {
        try {
            logger.info("Solicitud para actualizar proveedor con ID: {}", id);
            Proveedor proveedor = proveedorService.actualizarProveedor(id, proveedorInput);
            return ResponseEntity.ok(proveedor);
        } catch (IllegalArgumentException e) {
            logger.error("Error al actualizar proveedor: {}", e.getMessage());
            if (e.getMessage().contains("no encontrado")) {
                return ResponseEntity.notFound().build();
            }
            throw e;
        }
    }
    
    /**
     * DELETE /api/v1/proveedores/{id} - Eliminar un proveedor
     */
    @Override
    public ResponseEntity<Void> eliminarProveedor(Long id) {
        try {
            logger.info("Solicitud para eliminar proveedor con ID: {}", id);
            proveedorService.eliminarProveedor(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            logger.error("Error al eliminar proveedor: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}