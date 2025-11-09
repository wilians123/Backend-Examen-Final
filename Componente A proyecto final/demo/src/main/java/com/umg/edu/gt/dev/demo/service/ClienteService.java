package com.umg.edu.gt.dev.demo.service;

import com.logistica.commons.util.GeneradorCodigos;
import com.logistica.commons.util.ValidadorUtil;
import com.umg.edu.gt.dev.demo.entity.ClienteEntity;
import com.umg.edu.gt.dev.demo.repository.ClienteRepository;
import com.umg.edu.gt.dev.model.Cliente;
import com.umg.edu.gt.dev.model.ClienteInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio de lógica de negocio para Clientes
 * Usa el Componente C para validaciones y generación de códigos
 */
@Service
public class ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);
    
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Crear un nuevo cliente
     * Usa Componente C para: validar email y generar código único
     */
    @Transactional
    public Cliente crearCliente(ClienteInput input) {
        logger.info("Creando nuevo cliente: {}", input.getNombre());

        // Validar usando Componente C
        if (!ValidadorUtil.validarEmail(input.getEmail())) {
            throw new IllegalArgumentException("Email inválido: " + input.getEmail());
        }

        if (!ValidadorUtil.validarLongitudMinima(input.getNombre(), 3, "nombre")) {
            throw new IllegalArgumentException("El nombre debe tener al menos 3 caracteres");
        }

        // Verificar que el email no exista
        if (clienteRepository.existsByEmail(input.getEmail())) {
            throw new IllegalArgumentException("Ya existe un cliente con el email: " + input.getEmail());
        }

        // Validar teléfono si se proporciona
        if (input.getTelefono() != null && !input.getTelefono().trim().isEmpty()) {
            if (!ValidadorUtil.validarTelefono(input.getTelefono())) {
                throw new IllegalArgumentException("Teléfono inválido: " + input.getTelefono());
            }
        }

        // Generar código único usando Componente C
        String codigoUnico = GeneradorCodigos.generarCodigoCliente();
        logger.debug("Código único generado: {}", codigoUnico);

        // Crear entidad
        ClienteEntity entity = new ClienteEntity(
                input.getNombre(),
                input.getEmail(),
                input.getTelefono(),
                input.getDireccion(),
                codigoUnico
        );

        // Guardar en BD
        ClienteEntity saved = clienteRepository.save(entity);
        logger.info("Cliente creado exitosamente con ID: {}", saved.getId());

        // Convertir a DTO
        return convertirADTO(saved);
    }

    /**
     * Listar clientes con paginación
     */
    @Transactional(readOnly = true)
    public Page<Cliente> listarClientes(Integer page, Integer size) {
        logger.debug("Listando clientes - página: {}, tamaño: {}", page, size);

        int pageNum = (page != null && page >= 0) ? page : 0;
        int pageSize = (size != null && size > 0) ? Math.min(size, 100) : 10;

        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("fechaRegistro").descending());
        
        Page<ClienteEntity> clientesPage = clienteRepository.findAll(pageable);
        
        return clientesPage.map(this::convertirADTO);
    }

    /**
     * Obtener cliente por ID
     */
    @Transactional(readOnly = true)
    public Cliente obtenerClientePorId(Long id) {
        logger.debug("Obteniendo cliente con ID: {}", id);

        ClienteEntity entity = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));

        return convertirADTO(entity);
    }

    /**
     * Actualizar cliente
     */
    @Transactional
    public Cliente actualizarCliente(Long id, ClienteInput input) {
        logger.info("Actualizando cliente con ID: {}", id);

        // Buscar cliente existente
        ClienteEntity entity = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));

        // Validar email usando Componente C
        if (!ValidadorUtil.validarEmail(input.getEmail())) {
            throw new IllegalArgumentException("Email inválido: " + input.getEmail());
        }

        // Verificar si el email cambió y si ya existe
        if (!entity.getEmail().equals(input.getEmail()) && 
            clienteRepository.existsByEmail(input.getEmail())) {
            throw new IllegalArgumentException("Ya existe un cliente con el email: " + input.getEmail());
        }

        // Validar nombre
        if (!ValidadorUtil.validarLongitudMinima(input.getNombre(), 3, "nombre")) {
            throw new IllegalArgumentException("El nombre debe tener al menos 3 caracteres");
        }

        // Actualizar campos
        entity.setNombre(input.getNombre());
        entity.setEmail(input.getEmail());
        entity.setTelefono(input.getTelefono());
        entity.setDireccion(input.getDireccion());

        // Guardar cambios
        ClienteEntity updated = clienteRepository.save(entity);
        logger.info("Cliente actualizado exitosamente con ID: {}", updated.getId());

        return convertirADTO(updated);
    }

    /**
     * Eliminar cliente (soft delete)
     */
    @Transactional
    public void eliminarCliente(Long id) {
        logger.info("Eliminando cliente con ID: {}", id);

        ClienteEntity entity = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));

        // Soft delete - solo marcamos como inactivo
        entity.setActivo(false);
        clienteRepository.save(entity);

        logger.info("Cliente marcado como inactivo con ID: {}", id);
    }

    /**
     * Convertir ClienteEntity a DTO Cliente (modelo OpenAPI)
     */
    private Cliente convertirADTO(ClienteEntity entity) {
        Cliente dto = new Cliente();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setEmail(entity.getEmail());
        dto.setTelefono(entity.getTelefono());
        dto.setDireccion(entity.getDireccion());
        dto.setCodigoUnico(entity.getCodigoUnico());
        return dto;
    }
}