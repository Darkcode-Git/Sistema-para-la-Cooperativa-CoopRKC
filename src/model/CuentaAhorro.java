package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa una cuenta de ahorro en la cooperativa CoopRKC
 */
public class CuentaAhorro {
    private String numeroCuenta;
    private BigDecimal saldo;
    private LocalDateTime fechaApertura;
    private String cedulaSocio;
    private List<Transaccion> transacciones;
    private boolean activa;
    
    public CuentaAhorro(String numeroCuenta, String cedulaSocio) {
        this.numeroCuenta = numeroCuenta;
        this.cedulaSocio = cedulaSocio;
        this.saldo = BigDecimal.ZERO;
        this.fechaApertura = LocalDateTime.now();
        this.transacciones = new ArrayList<>();
        this.activa = true;
    }
    
    // Getters
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    
    public BigDecimal getSaldo() {
        return saldo;
    }
    
    public LocalDateTime getFechaApertura() {
        return fechaApertura;
    }
    
    public String getCedulaSocio() {
        return cedulaSocio;
    }
    
    public List<Transaccion> getTransacciones() {
        return new ArrayList<>(transacciones);
    }
    
    public boolean isActiva() {
        return activa;
    }
    
    // Métodos de negocio
    public boolean depositar(BigDecimal monto, String descripcion) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0 || !activa) {
            return false;
        }
        
        this.saldo = this.saldo.add(monto);
        Transaccion transaccion = new Transaccion(
            numeroCuenta, 
            Transaccion.TipoTransaccion.DEPOSITO, 
            monto, 
            descripcion
        );
        transacciones.add(transaccion);
        return true;
    }
    
    public boolean retirar(BigDecimal monto, String descripcion) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0 || 
            monto.compareTo(saldo) > 0 || 
            !activa) {
            return false;
        }
        
        this.saldo = this.saldo.subtract(monto);
        Transaccion transaccion = new Transaccion(
            numeroCuenta, 
            Transaccion.TipoTransaccion.RETIRO, 
            monto, 
            descripcion
        );
        transacciones.add(transaccion);
        return true;
    }
    
    public void desactivar() {
        this.activa = false;
    }
    
    public void activar() {
        this.activa = true;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CuentaAhorro that = (CuentaAhorro) obj;
        return Objects.equals(numeroCuenta, that.numeroCuenta);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(numeroCuenta);
    }
    
    @Override
    public String toString() {
        return String.format("CuentaAhorro{numero='%s', saldo=%s, activa=%s}", 
                           numeroCuenta, saldo, activa);
    }
}