package com.umg.edu.gt.dev.demo.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.umg.edu.gt.dev.demo.service.ClienteService;
import com.umg.edu.gt.dev.model.Cliente;
import com.umg.edu.gt.dev.model.ClienteInput;
import com.umg.edu.gt.dev.model.ListarClientes200Response;
import com.umg.edu.gt.dev.spec.ClientesApi;

import jakarta.validation.Valid;

/**
 * Controlador REST para gestión de Clientes
 * Implementa la interfaz generada por OpenAPI
 * Conectado con ClienteService que usa Componente C
 */
@RestController
public class ClienteController implements ClientesApi {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
    
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ClientesApi.super.getRequest();
    }

    /**
     * POST /api/v1/clientes - Crear un cliente
     */
    @Override
    public ResponseEntity<Cliente> crearCliente(@Valid ClienteInput clienteInput) {
        try {
            logger.info("Solicitud para crear cliente: {}", clienteInput.getNombre());
            Cliente cliente = clienteService.crearCliente(clienteInput);
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
        } catch (IllegalArgumentException e) {
            logger.error("Error al crear cliente: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * GET /api/v1/clientes - Listar todos los clientes
     */
    @Override
    public ResponseEntity<ListarClientes200Response> listarClientes(Integer page, Integer size) {
        try {
            logger.debug("Solicitud para listar clientes - página: {}, tamaño: {}", page, size);
            
            Page<Cliente> clientesPage = clienteService.listarClientes(page, size);
            
            // Crear respuesta con paginación
            ListarClientes200Response response = new ListarClientes200Response();
            response.setContent(clientesPage.getContent());
            response.setTotalElements((int) clientesPage.getTotalElements());
            response.setTotalPages(clientesPage.getTotalPages());
            response.setCurrentPage(clientesPage.getNumber());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al listar clientes: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * GET /api/v1/clientes/{id} - Obtener un cliente por ID
     */
    @Override
    public ResponseEntity<Cliente> obtenerClientePorId(Long id) {
        try {
            logger.debug("Solicitud para obtener cliente con ID: {}", id);
            Cliente cliente = clienteService.obtenerClientePorId(id);
            return ResponseEntity.ok(cliente);
        } catch (IllegalArgumentException e) {
            logger.error("Cliente no encontrado: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * PUT /api/v1/clientes/{id} - Actualizar un cliente
     */
    @Override
    public ResponseEntity<Cliente> actualizarCliente(Long id, @Valid ClienteInput clienteInput) {
        try {
            logger.info("Solicitud para actualizar cliente con ID: {}", id);
            Cliente cliente = clienteService.actualizarCliente(id, clienteInput);
            return ResponseEntity.ok(cliente);
        } catch (IllegalArgumentException e) {
            logger.error("Error al actualizar cliente: {}", e.getMessage());
            if (e.getMessage().contains("no encontrado")) {
                return ResponseEntity.notFound().build();
            }
            throw e;
        }
    }

    /**
     * DELETE /api/v1/clientes/{id} - Eliminar un cliente
     */
    @Override
    public ResponseEntity<Void> eliminarCliente(Long id) {
        try {
            logger.info("Solicitud para eliminar cliente con ID: {}", id);
            clienteService.eliminarCliente(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            logger.error("Error al eliminar cliente: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}