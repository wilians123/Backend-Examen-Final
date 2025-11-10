package com.logistica.proveedores.repository;

import com.logistica.proveedores.entity.FacturaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para acceso a datos de Facturas
 * Spring Data JPA genera automáticamente las implementaciones
 */
@Repository
public interface FacturaRepository extends JpaRepository<FacturaEntity, Long> {
    
    /**
     * Buscar factura por número único
     * @param numeroFactura Número único de la factura
     * @return Optional con la factura si existe
     */
    Optional<FacturaEntity> findByNumeroFactura(String numeroFactura);
    
    /**
     * Buscar factura por código único
     * @param codigoFactura Código único de la factura
     * @return Optional con la factura si existe
     */
    Optional<FacturaEntity> findByCodigoFactura(String codigoFactura);
    
    /**
     * Verificar si existe una factura con el número dado
     * @param numeroFactura Número de factura a verificar
     * @return true si existe, false si no
     */
    boolean existsByNumeroFactura(String numeroFactura);
    
    /**
     * Listar facturas de un proveedor específico
     * @param proveedorId ID del proveedor
     * @param pageable Configuración de paginación
     * @return Página de facturas del proveedor
     */
    Page<FacturaEntity> findByProveedorId(Long proveedorId, Pageable pageable);
    
    /**
     * Listar todas las facturas de un proveedor
     * @param proveedorId ID del proveedor
     * @return Lista de facturas
     */
    List<FacturaEntity> findByProveedorId(Long proveedorId);
    
    /**
     * Buscar facturas por rango de fechas de emisión
     * @param fechaInicio Fecha de inicio
     * @param fechaFin Fecha de fin
     * @param pageable Configuración de paginación
     * @return Página de facturas en el rango
     */
    @Query("SELECT f FROM FacturaEntity f WHERE f.fechaEmision BETWEEN :fechaInicio AND :fechaFin ORDER BY f.fechaEmision DESC")
    Page<FacturaEntity> findByFechaEmisionBetween(
            @Param("fechaInicio") LocalDate fechaInicio, 
            @Param("fechaFin") LocalDate fechaFin, 
            Pageable pageable
    );
    
    /**
     * Buscar facturas con filtros opcionales
     * @param proveedorId ID del proveedor (opcional)
     * @param fechaInicio Fecha de inicio (opcional)
     * @param fechaFin Fecha de fin (opcional)
     * @param pageable Configuración de paginación
     * @return Página de facturas filtradas
     */
    @Query("SELECT f FROM FacturaEntity f WHERE " +
           "(:proveedorId IS NULL OR f.proveedorId = :proveedorId) AND " +
           "(:fechaInicio IS NULL OR f.fechaEmision >= :fechaInicio) AND " +
           "(:fechaFin IS NULL OR f.fechaEmision <= :fechaFin) " +
           "ORDER BY f.fechaEmision DESC")
    Page<FacturaEntity> buscarConFiltros(
            @Param("proveedorId") Long proveedorId,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin,
            Pageable pageable
    );
    
    /**
     * Buscar facturas vencidas (sin pagar y fecha de vencimiento pasada)
     * @param fechaActual Fecha actual para comparación
     * @param pageable Configuración de paginación
     * @return Página de facturas vencidas
     */
    @Query("SELECT f FROM FacturaEntity f WHERE f.fechaPago IS NULL AND f.fechaVencimiento < :fechaActual")
    Page<FacturaEntity> findFacturasVencidas(@Param("fechaActual") LocalDate fechaActual, Pageable pageable);
    
    /**
     * Buscar facturas pagadas
     * @param pageable Configuración de paginación
     * @return Página de facturas pagadas
     */
    @Query("SELECT f FROM FacturaEntity f WHERE f.fechaPago IS NOT NULL ORDER BY f.fechaPago DESC")
    Page<FacturaEntity> findFacturasPagadas(Pageable pageable);
    
    /**
     * Buscar facturas pendientes de pago
     * @param pageable Configuración de paginación
     * @return Página de facturas pendientes
     */
    @Query("SELECT f FROM FacturaEntity f WHERE f.fechaPago IS NULL AND f.fechaVencimiento >= CURRENT_DATE ORDER BY f.fechaVencimiento ASC")
    Page<FacturaEntity> findFacturasPendientes(Pageable pageable);
    
    /**
     * Contar facturas de un proveedor
     * @param proveedorId ID del proveedor
     * @return Cantidad de facturas
     */
    long countByProveedorId(Long proveedorId);
    
    /**
     * Calcular total de facturas pendientes de un proveedor
     * @param proveedorId ID del proveedor
     * @return Suma total de facturas pendientes
     */
    @Query("SELECT COALESCE(SUM(f.total), 0.0) FROM FacturaEntity f WHERE f.proveedorId = :proveedorId AND f.fechaPago IS NULL")
    Double calcularTotalPendientePorProveedor(@Param("proveedorId") Long proveedorId);
    
    /**
     * Calcular total de facturas pagadas de un proveedor
     * @param proveedorId ID del proveedor
     * @return Suma total de facturas pagadas
     */
    @Query("SELECT COALESCE(SUM(f.total), 0.0) FROM FacturaEntity f WHERE f.proveedorId = :proveedorId AND f.fechaPago IS NOT NULL")
    Double calcularTotalPagadoPorProveedor(@Param("proveedorId") Long proveedorId);
}