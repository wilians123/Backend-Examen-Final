package com.umg.edu.gt.dev.demo.repository;

import com.umg.edu.gt.dev.demo.entity.ClienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para acceso a datos de Clientes
 * Spring Data JPA genera automáticamente las implementaciones
 */
@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    /**
     * Buscar cliente por email
     * @param email Email del cliente
     * @return Optional con el cliente si existe
     */
    Optional<ClienteEntity> findByEmail(String email);

    /**
     * Buscar cliente por código único
     * @param codigoUnico Código único del cliente
     * @return Optional con el cliente si existe
     */
    Optional<ClienteEntity> findByCodigoUnico(String codigoUnico);

    /**
     * Verificar si existe un cliente con el email dado
     * @param email Email a verificar
     * @return true si existe, false si no
     */
    boolean existsByEmail(String email);

    /**
     * Listar solo clientes activos con paginación
     * @param activo Estado del cliente (true = activo)
     * @param pageable Configuración de paginación
     * @return Página de clientes activos
     */
    Page<ClienteEntity> findByActivo(Boolean activo, Pageable pageable);

    /**
     * Buscar clientes por nombre (búsqueda parcial)
     * @param nombre Parte del nombre a buscar
     * @param pageable Configuración de paginación
     * @return Página de clientes que coinciden
     */
    @Query("SELECT c FROM ClienteEntity c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Page<ClienteEntity> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);
}