package com.logistica.proveedores.repository;

import com.logistica.proveedores.entity.ProveedorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para acceso a datos de Proveedores
 * Spring Data JPA genera automáticamente las implementaciones
 */
@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Long> {
    
    /**
     * Buscar proveedor por email
     * @param email Email del proveedor
     * @return Optional con el proveedor si existe
     */
    Optional<ProveedorEntity> findByEmail(String email);
    
    /**
     * Buscar proveedor por RFC
     * @param rfc RFC del proveedor
     * @return Optional con el proveedor si existe
     */
    Optional<ProveedorEntity> findByRfc(String rfc);
    
    /**
     * Buscar proveedor por código único
     * @param codigoUnico Código único del proveedor
     * @return Optional con el proveedor si existe
     */
    Optional<ProveedorEntity> findByCodigoUnico(String codigoUnico);
    
    /**
     * Verificar si existe un proveedor con el email dado
     * @param email Email a verificar
     * @return true si existe, false si no
     */
    boolean existsByEmail(String email);
    
    /**
     * Verificar si existe un proveedor con el RFC dado
     * @param rfc RFC a verificar
     * @return true si existe, false si no
     */
    boolean existsByRfc(String rfc);
    
    /**
     * Listar solo proveedores activos con paginación
     * @param activo Estado del proveedor (true = activo)
     * @param pageable Configuración de paginación
     * @return Página de proveedores activos
     */
    Page<ProveedorEntity> findByActivo(Boolean activo, Pageable pageable);
    
    /**
     * Buscar proveedores por razón social (búsqueda parcial)
     * @param razonSocial Parte de la razón social a buscar
     * @param pageable Configuración de paginación
     * @return Página de proveedores que coinciden
     */
    @Query("SELECT p FROM ProveedorEntity p WHERE LOWER(p.razonSocial) LIKE LOWER(CONCAT('%', :razonSocial, '%'))")
    Page<ProveedorEntity> buscarPorRazonSocial(@Param("razonSocial") String razonSocial, Pageable pageable);
    
    /**
     * Buscar proveedores por categoría de productos
     * @param categoria Categoría de productos
     * @param pageable Configuración de paginación
     * @return Página de proveedores de esa categoría
     */
    Page<ProveedorEntity> findByCategoriaProductos(String categoria, Pageable pageable);
    
    /**
     * Contar proveedores activos
     * @param activo Estado del proveedor
     * @return Cantidad de proveedores activos
     */
    long countByActivo(Boolean activo);
}