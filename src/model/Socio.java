package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa un socio de la cooperativa CoopRKC
 */
public class Socio {
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private List<CuentaAhorro> cuentas;
    
    public Socio(String cedula, String nombre, String apellido, String telefono, String email) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.cuentas = new ArrayList<>();
    }
    
    // Getters
    public String getCedula() {
        return cedula;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public String getEmail() {
        return email;
    }
    
    public List<CuentaAhorro> getCuentas() {
        return new ArrayList<>(cuentas);
    }
    
    // Setters
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    // Métodos de negocio
    public void agregarCuenta(CuentaAhorro cuenta) {
        if (cuenta != null && !cuentas.contains(cuenta)) {
            cuentas.add(cuenta);
        }
    }
    
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Socio socio = (Socio) obj;
        return Objects.equals(cedula, socio.cedula);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(cedula);
    }
    
    @Override
    public String toString() {
        return String.format("Socio{cedula='%s', nombre='%s', apellido='%s', cuentas=%d}", 
                           cedula, nombre, apellido, cuentas.size());
    }
}