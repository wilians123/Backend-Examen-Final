package com.umg.edu.gt.dev.demo.repository;

import com.umg.edu.gt.dev.demo.entity.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para acceso a datos de Pedidos
 * Spring Data JPA genera automáticamente las implementaciones
 */
@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

    /**
     * Buscar pedido por código único
     * @param codigoPedido Código único del pedido
     * @return Optional con el pedido si existe
     */
    Optional<PedidoEntity> findByCodigoPedido(String codigoPedido);

    /**
     * Listar pedidos de un cliente específico
     * @param clienteId ID del cliente
     * @param pageable Configuración de paginación
     * @return Página de pedidos del cliente
     */
    Page<PedidoEntity> findByClienteId(Long clienteId, Pageable pageable);

    /**
     * Listar todos los pedidos de un cliente
     * @param clienteId ID del cliente
     * @return Lista de pedidos
     */
    List<PedidoEntity> findByClienteId(Long clienteId);

    /**
     * Buscar pedidos con filtros opcionales
     * @param clienteId ID del cliente (opcional)
     * @param pageable Configuración de paginación
     * @return Página de pedidos filtrados
     */
    @Query("SELECT p FROM PedidoEntity p WHERE (:clienteId IS NULL OR p.clienteId = :clienteId)")
    Page<PedidoEntity> buscarConFiltros(
            @Param("clienteId") Long clienteId,
            Pageable pageable
    );

    /**
     * Contar pedidos de un cliente
     * @param clienteId ID del cliente
     * @return Cantidad de pedidos
     */
    long countByClienteId(Long clienteId);
}