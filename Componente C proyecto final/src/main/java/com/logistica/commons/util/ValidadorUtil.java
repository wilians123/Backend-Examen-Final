package com.logistica.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * Utilidad para validaciones comunes
 * Usada por los Componentes A y B para validar datos de entrada
 */
public class ValidadorUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(ValidadorUtil.class);
    
    // Patrones de validación
    private static final Pattern PATRON_EMAIL = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );
    
    private static final Pattern PATRON_CODIGO = Pattern.compile(
        "^[A-Z]{3,4}-\\d{8}-\\d{3}$"
    );
    
    private static final Pattern PATRON_RFC = Pattern.compile(
        "^[A-Z0-9]{10,20}$"
    );
    
    private static final Pattern PATRON_TELEFONO = Pattern.compile(
        "^\\+?[0-9\\s\\-()]{8,20}$"
    );
    
    /**
     * Valida formato de email
     * @param email Email a validar
     * @return true si es válido, false en caso contrario
     */
    public static boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            logger.warn("Email nulo o vacío");
            return false;
        }
        
        boolean esValido = PATRON_EMAIL.matcher(email.trim()).matches();
        if (!esValido) {
            logger.warn("Email inválido: {}", email);
        }
        return esValido;
    }
    
    /**
     * Valida formato de código generado por GeneradorCodigos
     * Formato esperado: XXX-YYYYMMDD-NNN o XXXX-YYYYMMDD-NNN
     * Ejemplos: CLI-20241107-001, PROV-20241107-015
     * @param codigo Código a validar
     * @return true si cumple el formato, false en caso contrario
     */
    public static boolean validarCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            logger.warn("Código nulo o vacío");
            return false;
        }
        
        boolean esValido = PATRON_CODIGO.matcher(codigo.trim()).matches();
        if (!esValido) {
            logger.warn("Código inválido: {}", codigo);
        }
        return esValido;
    }
    
    /**
     * Valida RFC o NIT (Registro Federal de Contribuyentes)
     * Acepta alfanuméricos de 10 a 20 caracteres
     * @param rfc RFC/NIT a validar
     * @return true si es válido, false en caso contrario
     */
    public static boolean validarRFC(String rfc) {
        if (rfc == null || rfc.trim().isEmpty()) {
            logger.warn("RFC nulo o vacío");
            return false;
        }
        
        String rfcLimpio = rfc.trim().toUpperCase();
        boolean esValido = PATRON_RFC.matcher(rfcLimpio).matches();
        
        if (!esValido) {
            logger.warn("RFC inválido: {}", rfc);
        }
        return esValido;
    }
    
    /**
     * Valida formato de número de teléfono
     * Acepta diversos formatos con o sin código de país
     * @param telefono Teléfono a validar
     * @return true si es válido, false en caso contrario
     */
    public static boolean validarTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            logger.warn("Teléfono nulo o vacío");
            return false;
        }
        
        boolean esValido = PATRON_TELEFONO.matcher(telefono.trim()).matches();
        if (!esValido) {
            logger.warn("Teléfono inválido: {}", telefono);
        }
        return esValido;
    }
    
    /**
     * Valida que un número sea positivo
     * @param numero Número a validar
     * @return true si es mayor a 0, false en caso contrario
     */
    public static boolean validarNumeroPositivo(Double numero) {
        if (numero == null) {
            logger.warn("Número nulo recibido");
            return false;
        }
        
        if (numero <= 0) {
            logger.warn("Número no positivo: {}", numero);
            return false;
        }
        return true;
    }
    
    /**
     * Valida que una cadena no sea nula ni vacía
     * @param texto Texto a validar
     * @param nombreCampo Nombre del campo para logs
     * @return true si contiene texto, false si es nulo o vacío
     */
    public static boolean validarTextoNoVacio(String texto, String nombreCampo) {
        if (texto == null || texto.trim().isEmpty()) {
            logger.warn("Campo '{}' está vacío o nulo", nombreCampo);
            return false;
        }
        return true;
    }
    
    /**
     * Valida longitud mínima de texto
     * @param texto Texto a validar
     * @param longitudMinima Longitud mínima requerida
     * @param nombreCampo Nombre del campo para logs
     * @return true si cumple la longitud, false en caso contrario
     */
    public static boolean validarLongitudMinima(String texto, int longitudMinima, String nombreCampo) {
        if (!validarTextoNoVacio(texto, nombreCampo)) {
            return false;
        }
        
        if (texto.trim().length() < longitudMinima) {
            logger.warn("Campo '{}' debe tener al menos {} caracteres. Longitud actual: {}", 
                       nombreCampo, longitudMinima, texto.trim().length());
            return false;
        }
        return true;
    }
    
    /**
     * Valida longitud máxima de texto
     * @param texto Texto a validar
     * @param longitudMaxima Longitud máxima permitida
     * @param nombreCampo Nombre del campo para logs
     * @return true si cumple la longitud, false en caso contrario
     */
    public static boolean validarLongitudMaxima(String texto, int longitudMaxima, String nombreCampo) {
        if (texto == null) {
            return true; // null es válido para longitud máxima
        }
        
        if (texto.length() > longitudMaxima) {
            logger.warn("Campo '{}' excede la longitud máxima de {} caracteres. Longitud actual: {}", 
                       nombreCampo, longitudMaxima, texto.length());
            return false;
        }
        return true;
    }
}