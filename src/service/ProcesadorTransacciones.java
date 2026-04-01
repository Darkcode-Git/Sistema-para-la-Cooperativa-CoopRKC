package service;

import model.CuentaAhorro;
import model.Transaccion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Servicio para el procesamiento de transacciones en la cooperativa CoopRKC
 * Implementa elementos de programación funcional para el filtrado y procesamiento
 */
public class ProcesadorTransacciones {
    private List<Transaccion> historialTransacciones;
    
    public ProcesadorTransacciones() {
        this.historialTransacciones = new ArrayList<>();
    }
    
    /**
     * Procesa un depósito en una cuenta de ahorro
     */
    public boolean procesarDeposito(CuentaAhorro cuenta, BigDecimal monto, String descripcion) {
        if (cuenta == null || monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        
        boolean exitoso = cuenta.depositar(monto, descripcion);
        if (exitoso) {
            // Registrar la transacción en el historial global
            Transaccion transaccion = new Transaccion(
                cuenta.getNumeroCuenta(), 
                Transaccion.TipoTransaccion.DEPOSITO, 
                monto, 
                descripcion
            );
            historialTransacciones.add(transaccion);
        }
        return exitoso;
    }
    
    /**
     * Procesa un retiro de una cuenta de ahorro
     */
    public boolean procesarRetiro(CuentaAhorro cuenta, BigDecimal monto, String descripcion) {
        if (cuenta == null || monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        
        boolean exitoso = cuenta.retirar(monto, descripcion);
        if (exitoso) {
            // Registrar la transacción en el historial global
            Transaccion transaccion = new Transaccion(
                cuenta.getNumeroCuenta(), 
                Transaccion.TipoTransaccion.RETIRO, 
                monto, 
                descripcion
            );
            historialTransacciones.add(transaccion);
        }
        return exitoso;
    }
    
    /**
     * Procesa una consulta de saldo
     */
    public BigDecimal procesarConsultaSaldo(CuentaAhorro cuenta) {
        if (cuenta == null) {
            return BigDecimal.ZERO;
        }
        
        // Registrar la consulta como transacción
        Transaccion transaccion = new Transaccion(
            cuenta.getNumeroCuenta(), 
            "Consulta de saldo"
        );
        historialTransacciones.add(transaccion);
        
        return cuenta.getSaldo();
    }
    
    // Métodos con programación funcional para filtrar transacciones
    
    /**
     * Obtiene transacciones filtradas por tipo usando programación funcional
     */
    public List<Transaccion> obtenerTransaccionesPorTipo(Transaccion.TipoTransaccion tipo) {
        return historialTransacciones.stream()
            .filter(t -> t.getTipo() == tipo)
            .collect(Collectors.toList());
    }
    
    /**
     * Obtiene transacciones de una cuenta específica
     */
    public List<Transaccion> obtenerTransaccionesPorCuenta(String numeroCuenta) {
        return historialTransacciones.stream()
            .filter(t -> t.getNumeroCuenta().equals(numeroCuenta))
            .collect(Collectors.toList());
    }
    
    /**
     * Obtiene transacciones que cumplen un criterio específico usando un predicado
     */
    public List<Transaccion> obtenerTransaccionesPorCriterio(Predicate<Transaccion> criterio) {
        return historialTransacciones.stream()
            .filter(criterio)
            .collect(Collectors.toList());
    }
    
    /**
     * Calcula el total de depósitos usando programación funcional
     */
    public BigDecimal calcularTotalDepositos() {
        return historialTransacciones.stream()
            .filter(Transaccion::esDeposito)
            .map(Transaccion::getMonto)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    /**
     * Calcula el total de retiros usando programación funcional
     */
    public BigDecimal calcularTotalRetiros() {
        return historialTransacciones.stream()
            .filter(Transaccion::esRetiro)
            .map(Transaccion::getMonto)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    /**
     * Obtiene todas las transacciones registradas
     */
    public List<Transaccion> obtenerHistorialCompleto() {
        return new ArrayList<>(historialTransacciones);
    }
    
    /**
     * Obtiene el número total de transacciones
     */
    public long contarTransacciones() {
        return historialTransacciones.size();
    }
}