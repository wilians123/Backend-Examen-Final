package com.logistica.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Generador de códigos únicos para diferentes entidades
 */
public class GeneradorCodigos {
    
    private static final Logger logger = LoggerFactory.getLogger(GeneradorCodigos.class);
    
    // Contadores atómicos para cada tipo de entidad (thread-safe)
    private static final AtomicInteger contadorCliente = new AtomicInteger(1);
    private static final AtomicInteger contadorPedido = new AtomicInteger(1);
    private static final AtomicInteger contadorProveedor = new AtomicInteger(1);
    private static final AtomicInteger contadorFactura = new AtomicInteger(1);
    
    // Formato de fecha para códigos: YYYYMMDD
    private static final DateTimeFormatter formateadorFecha = DateTimeFormatter.ofPattern("yyyyMMdd");
    
    /**
     * Genera un código único basado en el tipo de entidad
     * @param tipoEntidad Tipo: CLIENTE, PEDIDO, PROVEEDOR, FACTURA
     * @return Código único generado
     */
    public static String generarCodigoUnico(String tipoEntidad) {
        if (tipoEntidad == null || tipoEntidad.trim().isEmpty()) {
            logger.error("Tipo de entidad nulo o vacío");
            throw new IllegalArgumentException("El tipo de entidad no puede ser nulo o vacío");
        }
        
        String tipo = tipoEntidad.trim().toUpperCase();
        String prefijo;
        int secuencia;
        
        switch (tipo) {
            case "CLIENTE":
                prefijo = "CLI";
                secuencia = contadorCliente.getAndIncrement();
                break;
            case "PEDIDO":
                prefijo = "PED";
                secuencia = contadorPedido.getAndIncrement();
                break;
            case "PROVEEDOR":
                prefijo = "PROV";
                secuencia = contadorProveedor.getAndIncrement();
                break;
            case "FACTURA":
                prefijo = "FACT";
                secuencia = contadorFactura.getAndIncrement();
                break;
            default:
                logger.error("Tipo de entidad no reconocido: {}", tipo);
                throw new IllegalArgumentException("Tipo de entidad no válido: " + tipo);
        }
        
        String fecha = LocalDateTime.now().format(formateadorFecha);
        String codigo = String.format("%s-%s-%03d", prefijo, fecha, secuencia);
        
        logger.info("Código generado: {} para tipo: {}", codigo, tipo);
        return codigo;
    }
    
    /**
     * Genera código específico para Cliente
     * @return Código de cliente (CLI-YYYYMMDD-XXX)
     */
    public static String generarCodigoCliente() {
        return generarCodigoUnico("CLIENTE");
    }
    
    /**
     * Genera código específico para Pedido
     * @return Código de pedido (PED-YYYYMMDD-XXX)
     */
    public static String generarCodigoPedido() {
        return generarCodigoUnico("PEDIDO");
    }
    
    /**
     * Genera código específico para Proveedor
     * @return Código de proveedor (PROV-YYYYMMDD-XXX)
     */
    public static String generarCodigoProveedor() {
        return generarCodigoUnico("PROVEEDOR");
    }
    
    /**
     * Genera código específico para Factura
     * @return Código de factura (FACT-YYYYMMDD-XXX)
     */
    public static String generarCodigoFactura() {
        return generarCodigoUnico("FACTURA");
    }
    
    /**
     * Reinicia los contadores (útil para testing)
     */
    public static void reiniciarContadores() {
        contadorCliente.set(1);
        contadorPedido.set(1);
        contadorProveedor.set(1);
        contadorFactura.set(1);
        logger.info("Contadores reiniciados");
    }
}