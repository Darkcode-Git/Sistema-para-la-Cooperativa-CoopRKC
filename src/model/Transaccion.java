package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa una transacción en la cooperativa CoopRKC
 */
public class Transaccion {
    public enum TipoTransaccion {
        DEPOSITO, RETIRO, CONSULTA
    }
    
    private String id;
    private String numeroCuenta;
    private TipoTransaccion tipo;
    private BigDecimal monto;
    private LocalDateTime fecha;
    private String descripcion;
    
    public Transaccion(String numeroCuenta, TipoTransaccion tipo, BigDecimal monto, String descripcion) {
        this.id = UUID.randomUUID().toString();
        this.numeroCuenta = numeroCuenta;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
        this.descripcion = descripcion;
    }
    
    // Constructor para transacciones de consulta (sin monto)
    public Transaccion(String numeroCuenta, String descripcion) {
        this(numeroCuenta, TipoTransaccion.CONSULTA, BigDecimal.ZERO, descripcion);
    }
    
    // Getters
    public String getId() {
        return id;
    }
    
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    
    public TipoTransaccion getTipo() {
        return tipo;
    }
    
    public BigDecimal getMonto() {
        return monto;
    }
    
    public LocalDateTime getFecha() {
        return fecha;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    // Métodos de utilidad
    public String getFechaFormateada() {
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
    
    public boolean esDeposito() {
        return tipo == TipoTransaccion.DEPOSITO;
    }
    
    public boolean esRetiro() {
        return tipo == TipoTransaccion.RETIRO;
    }
    
    public boolean esConsulta() {
        return tipo == TipoTransaccion.CONSULTA;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Transaccion that = (Transaccion) obj;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return String.format("Transaccion{id='%s', cuenta='%s', tipo=%s, monto=%s, fecha='%s'}", 
                           id.substring(0, 8), numeroCuenta, tipo, monto, getFechaFormateada());
    }
}