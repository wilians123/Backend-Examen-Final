package com.umg.edu.gt.dev.demo.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import com.umg.edu.gt.dev.model.Cliente;
import com.umg.edu.gt.dev.model.ClienteInput;
import com.umg.edu.gt.dev.model.ListarClientes200Response;
import com.umg.edu.gt.dev.spec.ClientesApi;

import jakarta.validation.Valid;

/**
 * Controlador REST para gestión de Clientes
 * Implementa la interfaz generada por OpenAPI
 */
@RestController
public class ClienteController implements ClientesApi {

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ClientesApi.super.getRequest();
    }

    /**
     * POST /api/v1/clientes - Crear un cliente
     */
    @Override
    public ResponseEntity<Cliente> crearCliente(@Valid ClienteInput clienteInput) {
        // TODO: Implementar lógica de creación
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * GET /api/v1/clientes - Listar todos los clientes
     */
    @Override
    public ResponseEntity<ListarClientes200Response> listarClientes(
            Integer page, 
            Integer size) {
        // TODO: Implementar lógica de listado
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * GET /api/v1/clientes/{id} - Obtener un cliente por ID
     */
    @Override
    public ResponseEntity<Cliente> obtenerClientePorId(Long id) {
        // TODO: Implementar lógica de búsqueda por ID
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * PUT /api/v1/clientes/{id} - Actualizar un cliente
     */
    @Override
    public ResponseEntity<Cliente> actualizarCliente(
            Long id, 
            @Valid ClienteInput clienteInput) {
        // TODO: Implementar lógica de actualización
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * DELETE /api/v1/clientes/{id} - Eliminar un cliente
     */
    @Override
    public ResponseEntity<Void> eliminarCliente(Long id) {
        // TODO: Implementar lógica de eliminación
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}