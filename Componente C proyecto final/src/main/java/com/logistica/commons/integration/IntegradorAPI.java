package com.logistica.commons.integration;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Clase para integración entre componentes mediante llamadas REST
 * Permite que el Componente B consulte al Componente A y viceversa
 * Cumple con el requisito de "flujo circular de integración"
 */
public class IntegradorAPI {
    
    private static final Logger logger = LoggerFactory.getLogger(IntegradorAPI.class);
    
    // URLs base de los componentes (configurables)
    private static final String URL_BASE_COMPONENTE_A = "http://localhost:8080/api/v1";
    private static final String URL_BASE_COMPONENTE_B = "http://localhost:8081/api/v1";
    
    /**
     * Consulta un cliente desde el Componente A
     * Usado por el Componente B cuando necesita información de un cliente
     * 
     * @param clienteId ID del cliente a consultar
     * @return JSON con la información del cliente
     * @throws IOException si hay error en la conexión
     */
    public static String consultarCliente(Long clienteId) throws IOException {
        if (clienteId == null || clienteId <= 0) {
            logger.error("ID de cliente inválido: {}", clienteId);
            throw new IllegalArgumentException("El ID del cliente debe ser mayor a 0");
        }
        
        String url = URL_BASE_COMPONENTE_A + "/clientes/" + clienteId;
        logger.info("Consultando cliente {} en URL: {}", clienteId, url);
        
        return ejecutarGetRequest(url);
    }
    
    /**
     * Consulta un pedido desde el Componente A
     * Usado por el Componente B para vincular facturas con pedidos
     * 
     * @param pedidoId ID del pedido a consultar
     * @return JSON con la información del pedido
     * @throws IOException si hay error en la conexión
     */
    public static String consultarPedido(Long pedidoId) throws IOException {
        if (pedidoId == null || pedidoId <= 0) {
            logger.error("ID de pedido inválido: {}", pedidoId);
            throw new IllegalArgumentException("El ID del pedido debe ser mayor a 0");
        }
        
        String url = URL_BASE_COMPONENTE_A + "/pedidos/" + pedidoId;
        logger.info("Consultando pedido {} en URL: {}", pedidoId, url);
        
        return ejecutarGetRequest(url);
    }
    
    /**
     * Consulta un proveedor desde el Componente B
     * Usado por el Componente A cuando necesita información de proveedores
     * 
     * @param proveedorId ID del proveedor a consultar
     * @return JSON con la información del proveedor
     * @throws IOException si hay error en la conexión
     */
    public static String consultarProveedor(Long proveedorId) throws IOException {
        if (proveedorId == null || proveedorId <= 0) {
            logger.error("ID de proveedor inválido: {}", proveedorId);
            throw new IllegalArgumentException("El ID del proveedor debe ser mayor a 0");
        }
        
        String url = URL_BASE_COMPONENTE_B + "/proveedores/" + proveedorId;
        logger.info("Consultando proveedor {} en URL: {}", proveedorId, url);
        
        return ejecutarGetRequest(url);
    }
    
    /**
     * Consulta una factura desde el Componente B
     * Usado por el Componente A para vincular información de facturación
     * 
     * @param facturaId ID de la factura a consultar
     * @return JSON con la información de la factura
     * @throws IOException si hay error en la conexión
     */
    public static String consultarFactura(Long facturaId) throws IOException {
        if (facturaId == null || facturaId <= 0) {
            logger.error("ID de factura inválido: {}", facturaId);
            throw new IllegalArgumentException("El ID de la factura debe ser mayor a 0");
        }
        
        String url = URL_BASE_COMPONENTE_B + "/facturas/" + facturaId;
        logger.info("Consultando factura {} en URL: {}", facturaId, url);
        
        return ejecutarGetRequest(url);
    }
    
    /**
     * Método privado para ejecutar peticiones GET HTTP
     * Reutilizable para todas las consultas
     * 
     * @param url URL completa del endpoint
     * @return Respuesta en formato JSON
     * @throws IOException si hay error en la conexión
     */
    private static String ejecutarGetRequest(String url) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-Type", "application/json");
            
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getCode();
                
                // Manejar ParseException internamente
                String responseBody;
                try {
                    responseBody = EntityUtils.toString(response.getEntity());
                } catch (ParseException e) {
                    logger.error("Error al parsear respuesta de {}: {}", url, e.getMessage());
                    throw new IOException("Error al procesar respuesta del servidor", e);
                }
                
                if (statusCode >= 200 && statusCode < 300) {
                    logger.info("Petición exitosa a {}: Status {}", url, statusCode);
                    return responseBody;
                } else {
                    logger.error("Error en petición a {}: Status {} - Response: {}", 
                                url, statusCode, responseBody);
                    throw new IOException("Error HTTP " + statusCode + ": " + responseBody);
                }
            }
        } catch (IOException e) {
            logger.error("Error al ejecutar petición GET a {}: {}", url, e.getMessage());
            throw e;
        }
    }
    
    /**
     * Verifica si un componente está disponible
     * Útil para health checks y validaciones
     * 
     * @param urlBase URL base del componente (A o B)
     * @return true si el componente responde, false en caso contrario
     */
    public static boolean verificarDisponibilidad(String urlBase) {
        try {
            ejecutarGetRequest(urlBase + "/actuator/health");
            logger.info("Componente {} está disponible", urlBase);
            return true;
        } catch (Exception e) {
            logger.warn("Componente {} no está disponible: {}", urlBase, e.getMessage());
            return false;
        }
    }
}