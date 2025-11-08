package com.logistica.commons.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class IntegradorAPI {

    private static final Logger logger = LoggerFactory.getLogger(IntegradorAPI.class);

    private static final String URL_BASE_COMPONENTE_A = "http://localhost:8080/api/v1";
    private static final String URL_BASE_COMPONENTE_B = "http://localhost:8081/api/v1";

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public static String consultarCliente(Long clienteId) throws IOException {
        if (clienteId == null || clienteId <= 0) {
            logger.error("ID de cliente inválido: {}", clienteId);
            throw new IllegalArgumentException("El ID del cliente debe ser mayor a 0");
        }

        String url = URL_BASE_COMPONENTE_A + "/clientes/" + clienteId;
        logger.info("Consultando cliente {} en URL: {}", clienteId, url);

        return ejecutarGetRequest(url);
    }

    public static String consultarPedido(Long pedidoId) throws IOException {
        if (pedidoId == null || pedidoId <= 0) {
            logger.error("ID de pedido inválido: {}", pedidoId);
            throw new IllegalArgumentException("El ID del pedido debe ser mayor a 0");
        }

        String url = URL_BASE_COMPONENTE_A + "/pedidos/" + pedidoId;
        logger.info("Consultando pedido {} en URL: {}", pedidoId, url);

        return ejecutarGetRequest(url);
    }

    public static String consultarProveedor(Long proveedorId) throws IOException {
        if (proveedorId == null || proveedorId <= 0) {
            logger.error("ID de proveedor inválido: {}", proveedorId);
            throw new IllegalArgumentException("El ID del proveedor debe ser mayor a 0");
        }

        String url = URL_BASE_COMPONENTE_B + "/proveedores/" + proveedorId;
        logger.info("Consultando proveedor {} en URL: {}", proveedorId, url);

        return ejecutarGetRequest(url);
    }

    public static String consultarFactura(Long facturaId) throws IOException {
        if (facturaId == null || facturaId <= 0) {
            logger.error("ID de factura inválido: {}", facturaId);
            throw new IllegalArgumentException("El ID de la factura debe ser mayor a 0");
        }

        String url = URL_BASE_COMPONENTE_B + "/facturas/" + facturaId;
        logger.info("Consultando factura {} en URL: {}", facturaId, url);

        return ejecutarGetRequest(url);
    }

    private static String ejecutarGetRequest(String url) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-Type", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getCode();
                String responseBody;
                try {
                    responseBody = EntityUtils.toString(response.getEntity());
                } catch (ParseException e) {
                    logger.error("Error al parsear la respuesta: {}", e.getMessage());
                    throw new IOException("Error al parsear la respuesta", e);
                }

                if (statusCode >= 200 && statusCode < 300) {
                    logger.info("Petición exitosa a {}: Status {}", url, statusCode);
                    return responseBody;
                } else {
                    logger.error("Error en petición a {}: Status {} - Response: {}", url, statusCode, responseBody);
                    throw new IOException("Error HTTP " + statusCode + ": " + responseBody);
                }
            }
        } catch (IOException e) {
            logger.error("Error al ejecutar petición GET a {}: {}", url, e.getMessage());
            throw e;
        }
    }

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