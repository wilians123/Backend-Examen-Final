package com.logistica.commons.util;

import com.logistica.commons.dto.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Clase con lógica de negocio compartida para cálculos
 * Usada por los Componentes A y B
 */
public class OperacionesNegocio {
    
    private static final Logger logger = LoggerFactory.getLogger(OperacionesNegocio.class);
    
    // Constante IVA 12% (Guatemala)
    private static final double IVA = 0.12;
    
    /**
     * Calcula el total con IVA incluido
     * @param subtotal Subtotal sin IVA
     * @return Total con IVA
     */
    public static double calcularTotalConIVA(double subtotal) {
        if (subtotal < 0) {
            logger.warn("Subtotal negativo recibido: {}", subtotal);
            return 0.0;
        }
        double total = subtotal * (1 + IVA);
        logger.debug("Subtotal: {} → Total con IVA: {}", subtotal, total);
        return Math.round(total * 100.0) / 100.0; // Redondeo a 2 decimales
    }
    
    /**
     * Calcula solo el monto de impuestos
     * @param subtotal Subtotal sin impuestos
     * @return Monto de IVA
     */
    public static double calcularImpuestos(double subtotal) {
        if (subtotal < 0) {
            logger.warn("Subtotal negativo recibido para cálculo de impuestos: {}", subtotal);
            return 0.0;
        }
        double impuestos = subtotal * IVA;
        return Math.round(impuestos * 100.0) / 100.0;
    }
    
    /**
     * Aplica un descuento porcentual al total
     * @param total Total actual
     * @param porcentaje Porcentaje de descuento (0-100)
     * @return Total con descuento aplicado
     */
    public static double aplicarDescuento(double total, double porcentaje) {
        if (porcentaje < 0 || porcentaje > 100) {
            logger.error("Porcentaje de descuento inválido: {}%. Debe estar entre 0 y 100", porcentaje);
            return total;
        }
        if (total < 0) {
            logger.warn("Total negativo recibido: {}", total);
            return 0.0;
        }
        double descuento = total * (porcentaje / 100);
        double totalConDescuento = total - descuento;
        logger.info("Total: {} → Descuento {}%: {} → Total final: {}", 
                    total, porcentaje, descuento, totalConDescuento);
        return Math.round(totalConDescuento * 100.0) / 100.0;
    }
    
    /**
     * Calcula el total de una lista de items
     * @param items Lista de items
     * @return Suma de todos los subtotales
     */
    public static double calcularTotal(List<ItemDTO> items) {
        if (items == null || items.isEmpty()) {
            logger.warn("Lista de items vacía o nula");
            return 0.0;
        }
        
        double total = items.stream()
                .mapToDouble(item -> {
                    if (item.getSubtotal() == null) {
                        logger.warn("Item sin subtotal: {}", item.getNombre());
                        return 0.0;
                    }
                    return item.getSubtotal();
                })
                .sum();
        
        logger.debug("Total calculado de {} items: {}", items.size(), total);
        return Math.round(total * 100.0) / 100.0;
    }
    
    /**
     * Calcula el total con IVA de una lista de items
     * @param items Lista de items
     * @return Total con IVA incluido
     */
    public static double calcularTotalConIVA(List<ItemDTO> items) {
        double subtotal = calcularTotal(items);
        return calcularTotalConIVA(subtotal);
    }
}