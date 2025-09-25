package service;

import model.Socio;
import model.CuentaAhorro;
import model.Transaccion;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Servicio principal de la Cooperativa CoopRKC
 * Gestiona socios, cuentas y coordina las operaciones
 */
public class Cooperativa {
    private String nombre;
    private Map<String, Socio> socios;
    private Map<String, CuentaAhorro> cuentas;
    private ProcesadorTransacciones procesadorTransacciones;
    private int contadorCuentas;
    
    public Cooperativa(String nombre) {
        this.nombre = nombre;
        this.socios = new HashMap<>();
        this.cuentas = new HashMap<>();
        this.procesadorTransacciones = new ProcesadorTransacciones();
        this.contadorCuentas = 1000; // Empezar desde 1000
    }
    
    // Gestión de socios
    
    /**
     * Registra un nuevo socio en la cooperativa
     */
    public boolean registrarSocio(String cedula, String nombre, String apellido, String telefono, String email) {
        if (socios.containsKey(cedula)) {
            return false; // Ya existe
        }
        
        Socio nuevoSocio = new Socio(cedula, nombre, apellido, telefono, email);
        socios.put(cedula, nuevoSocio);
        return true;
    }
    
    /**
     * Busca un socio por cédula
     */
    public Optional<Socio> buscarSocio(String cedula) {
        return Optional.ofNullable(socios.get(cedula));
    }
    
    /**
     * Obtiene todos los socios registrados
     */
    public List<Socio> obtenerTodosSocios() {
        return new ArrayList<>(socios.values());
    }
    
    // Gestión de cuentas
    
    /**
     * Abre una nueva cuenta de ahorro para un socio
     */
    public Optional<CuentaAhorro> abrirCuentaAhorro(String cedulaSocio) {
        Optional<Socio> socio = buscarSocio(cedulaSocio);
        if (!socio.isPresent()) {
            return Optional.empty();
        }
        
        String numeroCuenta = generarNumeroCuenta();
        CuentaAhorro nuevaCuenta = new CuentaAhorro(numeroCuenta, cedulaSocio);
        
        cuentas.put(numeroCuenta, nuevaCuenta);
        socio.get().agregarCuenta(nuevaCuenta);
        
        return Optional.of(nuevaCuenta);
    }
    
    /**
     * Busca una cuenta por número
     */
    public Optional<CuentaAhorro> buscarCuenta(String numeroCuenta) {
        return Optional.ofNullable(cuentas.get(numeroCuenta));
    }
    
    /**
     * Obtiene todas las cuentas de un socio
     */
    public List<CuentaAhorro> obtenerCuentasDeSocio(String cedulaSocio) {
        return cuentas.values().stream()
            .filter(cuenta -> cuenta.getCedulaSocio().equals(cedulaSocio))
            .collect(Collectors.toList());
    }
    
    // Operaciones bancarias
    
    /**
     * Realiza un depósito en una cuenta
     */
    public boolean depositar(String numeroCuenta, BigDecimal monto, String descripcion) {
        Optional<CuentaAhorro> cuenta = buscarCuenta(numeroCuenta);
        if (!cuenta.isPresent()) {
            return false;
        }
        
        return procesadorTransacciones.procesarDeposito(cuenta.get(), monto, descripcion);
    }
    
    /**
     * Realiza un retiro de una cuenta
     */
    public boolean retirar(String numeroCuenta, BigDecimal monto, String descripcion) {
        Optional<CuentaAhorro> cuenta = buscarCuenta(numeroCuenta);
        if (!cuenta.isPresent()) {
            return false;
        }
        
        return procesadorTransacciones.procesarRetiro(cuenta.get(), monto, descripcion);
    }
    
    /**
     * Consulta el saldo de una cuenta
     */
    public BigDecimal consultarSaldo(String numeroCuenta) {
        Optional<CuentaAhorro> cuenta = buscarCuenta(numeroCuenta);
        if (!cuenta.isPresent()) {
            return BigDecimal.ZERO;
        }
        
        return procesadorTransacciones.procesarConsultaSaldo(cuenta.get());
    }
    
    // Reportes y estadísticas usando programación funcional
    
    /**
     * Obtiene el historial de transacciones de una cuenta
     */
    public List<Transaccion> obtenerHistorialCuenta(String numeroCuenta) {
        return procesadorTransacciones.obtenerTransaccionesPorCuenta(numeroCuenta);
    }
    
    /**
     * Calcula el saldo total de todos los socios
     */
    public BigDecimal calcularSaldoTotal() {
        return cuentas.values().stream()
            .filter(CuentaAhorro::isActiva)
            .map(CuentaAhorro::getSaldo)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    /**
     * Obtiene estadísticas generales de la cooperativa
     */
    public Map<String, Object> obtenerEstadisticas() {
        Map<String, Object> estadisticas = new HashMap<>();
        estadisticas.put("totalSocios", socios.size());
        estadisticas.put("totalCuentas", cuentas.size());
        estadisticas.put("totalTransacciones", procesadorTransacciones.contarTransacciones());
        estadisticas.put("saldoTotal", calcularSaldoTotal());
        estadisticas.put("totalDepositos", procesadorTransacciones.calcularTotalDepositos());
        estadisticas.put("totalRetiros", procesadorTransacciones.calcularTotalRetiros());
        return estadisticas;
    }
    
    // Métodos de utilidad
    
    private String generarNumeroCuenta() {
        return String.valueOf(++contadorCuentas);
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public ProcesadorTransacciones getProcesadorTransacciones() {
        return procesadorTransacciones;
    }
}