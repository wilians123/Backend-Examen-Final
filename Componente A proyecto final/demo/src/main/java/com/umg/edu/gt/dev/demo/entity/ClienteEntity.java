package com.umg.edu.gt.dev.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad JPA que representa la tabla 'clientes' en MariaDB
 */
@Entity
@Table(name = "clientes")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 20)
    private String telefono;

    @Column(length = 255)
    private String direccion;

    @Column(name = "codigo_unico", unique = true, length = 50)
    private String codigoUnico;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(nullable = false)
    private Boolean activo = true;

    // Constructor vacío (requerido por JPA)
    public ClienteEntity() {
    }

    // Constructor con parámetros
    public ClienteEntity(String nombre, String email, String telefono, String direccion, String codigoUnico) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.codigoUnico = codigoUnico;
        this.fechaRegistro = LocalDateTime.now();
        this.activo = true;
    }

    // Método que se ejecuta antes de persistir (crear)
    @PrePersist
    protected void onCreate() {
        if (fechaRegistro == null) {
            fechaRegistro = LocalDateTime.now();
        }
        if (activo == null) {
            activo = true;
        }
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "ClienteEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", codigoUnico='" + codigoUnico + '\'' +
                '}';
    }
}