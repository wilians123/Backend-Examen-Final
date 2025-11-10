package com.logistica.proveedores.service;

import com.logistica.commons.util.GeneradorCodigos;
import com.logistica.commons.util.ValidadorUtil;
import com.logistica.proveedores.entity.ProveedorEntity;
import com.logistica.proveedores.repository.ProveedorRepository;
import com.logistica.proveedores.model.Proveedor;
import com.logistica.proveedores.model.ProveedorInput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneId;

/**
 * Servicio de lógica de negocio para Proveedores
 * Usa el Componente C para validaciones y generación de códigos
 */
@Service
public class ProveedorService {
    
    private static final Logger logger = LoggerFactory.getLogger(ProveedorService.class);
    
    private final ProveedorRepository proveedorRepository;
    
    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }
    
    /**
     * Crear un nuevo proveedor
     * Usa Componente C para: validar email, RFC y generar código único
     */
    @Transactional
    public Proveedor crearProveedor(ProveedorInput input) {
        logger.info("Creando nuevo proveedor: {}", input.getRazonSocial());
        
        // Validar usando Componente C
        if (!ValidadorUtil.validarEmail(input.getEmail())) {
            throw new IllegalArgumentException("Email inválido: " + input.getEmail());
        }
        
        if (!ValidadorUtil.validarRFC(input.getRfc())) {
            throw new IllegalArgumentException("RFC inválido: " + input.getRfc());
        }
        
        if (!ValidadorUtil.validarLongitudMinima(input.getRazonSocial(), 3, "razón social")) {
            throw new IllegalArgumentException("La razón social debe tener al menos 3 caracteres");
        }
        
        // Verificar que el email no exista
        if (proveedorRepository.existsByEmail(input.getEmail())) {
            throw new IllegalArgumentException("Ya existe un proveedor con el email: " + input.getEmail());
        }
        
        // Verificar que el RFC no exista
        if (proveedorRepository.existsByRfc(input.getRfc())) {
            throw new IllegalArgumentException("Ya existe un proveedor con el RFC: " + input.getRfc());
        }
        
        // Validar teléfono si se proporciona
        if (input.getTelefono() != null && !input.getTelefono().trim().isEmpty()) {
            if (!ValidadorUtil.validarTelefono(input.getTelefono())) {
                throw new IllegalArgumentException("Teléfono inválido: " + input.getTelefono());
            }
        }
        
        // Generar código único usando Componente C
        String codigoUnico = GeneradorCodigos.generarCodigoProveedor();
        logger.debug("Código único generado: {}", codigoUnico);
        
        // Crear entidad
        ProveedorEntity entity = new ProveedorEntity(
            input.getRazonSocial(),
            input.getRfc().toUpperCase(), // Normalizar RFC a mayúsculas
            input.getEmail(),
            input.getTelefono(),
            input.getDireccion(),
            input.getContactoNombre(),
            codigoUnico,
            input.getCategoriaProductos()
        );
        
        // Guardar en BD
        ProveedorEntity saved = proveedorRepository.save(entity);
        logger.info("Proveedor creado exitosamente con ID: {}", saved.getId());
        
        // Convertir a DTO
        return convertirADTO(saved);
    }
    
    /**
     * Listar proveedores con paginación y filtros
     */
    @Transactional(readOnly = true)
    public Page<Proveedor> listarProveedores(Integer page, Integer size, Boolean activo) {
        logger.debug("Listando proveedores - página: {}, tamaño: {}, activo: {}", page, size, activo);
        
        int pageNum = (page != null && page >= 0) ? page : 0;
        int pageSize = (size != null && size > 0) ? Math.min(size, 100) : 10;
        
        Pageable pageable = PageRequest.of(pageNum, pageSize, 
                                          Sort.by("fechaRegistro").descending());
        
        Page<ProveedorEntity> proveedoresPage;
        
        if (activo != null) {
            proveedoresPage = proveedorRepository.findByActivo(activo, pageable);
        } else {
            proveedoresPage = proveedorRepository.findAll(pageable);
        }
        
        return proveedoresPage.map(this::convertirADTO);
    }
    
    /**
     * Obtener proveedor por ID
     */
    @Transactional(readOnly = true)
    public Proveedor obtenerProveedorPorId(Long id) {
        logger.debug("Obteniendo proveedor con ID: {}", id);
        
        ProveedorEntity entity = proveedorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado con ID: " + id));
        
        return convertirADTO(entity);
    }
    
    /**
     * Actualizar proveedor
     */
    @Transactional
    public Proveedor actualizarProveedor(Long id, ProveedorInput input) {
        logger.info("Actualizando proveedor con ID: {}", id);
        
        // Buscar proveedor existente
        ProveedorEntity entity = proveedorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado con ID: " + id));
        
        // Validar email usando Componente C
        if (!ValidadorUtil.validarEmail(input.getEmail())) {
            throw new IllegalArgumentException("Email inválido: " + input.getEmail());
        }
        
        // Validar RFC usando Componente C
        if (!ValidadorUtil.validarRFC(input.getRfc())) {
            throw new IllegalArgumentException("RFC inválido: " + input.getRfc());
        }
        
        // Verificar si el email cambió y si ya existe
        if (!entity.getEmail().equals(input.getEmail()) && 
            proveedorRepository.existsByEmail(input.getEmail())) {
            throw new IllegalArgumentException("Ya existe un proveedor con el email: " + input.getEmail());
        }
        
        // Verificar si el RFC cambió y si ya existe
        String rfcNormalizado = input.getRfc().toUpperCase();
        if (!entity.getRfc().equals(rfcNormalizado) && 
            proveedorRepository.existsByRfc(rfcNormalizado)) {
            throw new IllegalArgumentException("Ya existe un proveedor con el RFC: " + rfcNormalizado);
        }
        
        // Validar razón social
        if (!ValidadorUtil.validarLongitudMinima(input.getRazonSocial(), 3, "razón social")) {
            throw new IllegalArgumentException("La razón social debe tener al menos 3 caracteres");
        }
        
        // Validar teléfono si se proporciona
        if (input.getTelefono() != null && !input.getTelefono().trim().isEmpty()) {
            if (!ValidadorUtil.validarTelefono(input.getTelefono())) {
                throw new IllegalArgumentException("Teléfono inválido: " + input.getTelefono());
            }
        }
        
        // Actualizar campos
        entity.setRazonSocial(input.getRazonSocial());
        entity.setRfc(rfcNormalizado);
        entity.setEmail(input.getEmail());
        entity.setTelefono(input.getTelefono());
        entity.setDireccion(input.getDireccion());
        entity.setContactoNombre(input.getContactoNombre());
        entity.setCategoriaProductos(input.getCategoriaProductos());
        
        // Guardar cambios
        ProveedorEntity updated = proveedorRepository.save(entity);
        logger.info("Proveedor actualizado exitosamente con ID: {}", updated.getId());
        
        return convertirADTO(updated);
    }
    
    /**
     * Eliminar proveedor (soft delete)
     */
    @Transactional
    public void eliminarProveedor(Long id) {
        logger.info("Eliminando proveedor con ID: {}", id);
        
        ProveedorEntity entity = proveedorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado con ID: " + id));
        
        // Soft delete - solo marcamos como inactivo
        entity.setActivo(false);
        proveedorRepository.save(entity);
        
        logger.info("Proveedor marcado como inactivo con ID: {}", id);
    }
    
    /**
     * Buscar proveedor por email (método auxiliar para integraciones)
     */
    @Transactional(readOnly = true)
    public Proveedor buscarPorEmail(String email) {
        return proveedorRepository.findByEmail(email)
            .map(this::convertirADTO)
            .orElse(null);
    }
    
    /**
     * Buscar proveedor por RFC (método auxiliar para integraciones)
     */
    @Transactional(readOnly = true)
    public Proveedor buscarPorRfc(String rfc) {
        return proveedorRepository.findByRfc(rfc.toUpperCase())
            .map(this::convertirADTO)
            .orElse(null);
    }
    
    /**
     * Convertir ProveedorEntity a DTO Proveedor (modelo OpenAPI)
     */
    private Proveedor convertirADTO(ProveedorEntity entity) {
        Proveedor dto = new Proveedor();
        dto.setId(entity.getId());
        dto.setRazonSocial(entity.getRazonSocial());
        dto.setRfc(entity.getRfc());
        dto.setEmail(entity.getEmail());
        dto.setTelefono(entity.getTelefono());
        dto.setDireccion(entity.getDireccion());
        dto.setContactoNombre(entity.getContactoNombre());
        dto.setCodigoUnico(entity.getCodigoUnico());
        dto.setCategoriaProductos(entity.getCategoriaProductos());
        dto.setActivo(entity.getActivo());
        
        // Convertir LocalDateTime a OffsetDateTime
        if (entity.getFechaRegistro() != null) {
            dto.setFechaRegistro(
                entity.getFechaRegistro()
                    .atZone(ZoneId.systemDefault())
                    .toOffsetDateTime()
            );
        }
        
        return dto;
    }
}