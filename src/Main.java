import service.Cooperativa;
import model.Socio;
import model.CuentaAhorro;
import model.Transaccion;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;
import java.util.Map;

/**
 * Clase principal para demostrar el funcionamiento del sistema
 * de la Cooperativa CoopRKC
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Sistema de la Cooperativa CoopRKC ===");
        System.out.println("Prototipo Java - Versión 1.0\n");
        
        // Crear instancia de la cooperativa
        Cooperativa cooperativa = new Cooperativa("Cooperativa CoopRKC");
        
        // Demostrar funcionalidades del sistema
        demostrarRegistroSocios(cooperativa);
        demostrarGestionCuentas(cooperativa);
        demostrarOperacionesBancarias(cooperativa);
        demostrarReportesEstadisticas(cooperativa);
        
        System.out.println("\n=== Fin de la demostración ===");
    }
    
    private static void demostrarRegistroSocios(Cooperativa cooperativa) {
        System.out.println("1. REGISTRO DE SOCIOS");
        System.out.println("---------------------");
        
        // Registrar algunos socios
        cooperativa.registrarSocio("12345678", "Juan", "Pérez", "555-0101", "juan.perez@email.com");
        cooperativa.registrarSocio("87654321", "María", "González", "555-0102", "maria.gonzalez@email.com");
        cooperativa.registrarSocio("11223344", "Carlos", "Rodríguez", "555-0103", "carlos.rodriguez@email.com");
        
        System.out.println("Socios registrados:");
        List<Socio> socios = cooperativa.obtenerTodosSocios();
        socios.forEach(System.out::println);
        System.out.println();
    }
    
    private static void demostrarGestionCuentas(Cooperativa cooperativa) {
        System.out.println("2. GESTIÓN DE CUENTAS");
        System.out.println("----------------------");
        
        // Abrir cuentas para los socios
        Optional<CuentaAhorro> cuenta1 = cooperativa.abrirCuentaAhorro("12345678");
        Optional<CuentaAhorro> cuenta2 = cooperativa.abrirCuentaAhorro("87654321");
        Optional<CuentaAhorro> cuenta3 = cooperativa.abrirCuentaAhorro("11223344");
        
        if (cuenta1.isPresent()) {
            System.out.println("Cuenta creada para Juan Pérez: " + cuenta1.get());
        }
        if (cuenta2.isPresent()) {
            System.out.println("Cuenta creada para María González: " + cuenta2.get());
        }
        if (cuenta3.isPresent()) {
            System.out.println("Cuenta creada para Carlos Rodríguez: " + cuenta3.get());
        }
        System.out.println();
    }
    
    private static void demostrarOperacionesBancarias(Cooperativa cooperativa) {
        System.out.println("3. OPERACIONES BANCARIAS");
        System.out.println("-------------------------");
        
        // Realizar depósitos
        System.out.println("Realizando depósitos...");
        cooperativa.depositar("1001", new BigDecimal("1000.00"), "Depósito inicial");
        cooperativa.depositar("1002", new BigDecimal("500.00"), "Depósito inicial");
        cooperativa.depositar("1003", new BigDecimal("750.00"), "Depósito inicial");
        
        // Realizar algunos retiros
        System.out.println("Realizando retiros...");
        cooperativa.retirar("1001", new BigDecimal("200.00"), "Retiro para gastos");
        cooperativa.retirar("1002", new BigDecimal("100.00"), "Retiro cajero automático");
        
        // Consultar saldos
        System.out.println("\nSaldos actuales:");
        System.out.println("Cuenta 1001: $" + cooperativa.consultarSaldo("1001"));
        System.out.println("Cuenta 1002: $" + cooperativa.consultarSaldo("1002"));
        System.out.println("Cuenta 1003: $" + cooperativa.consultarSaldo("1003"));
        
        // Mostrar historial de una cuenta
        System.out.println("\nHistorial de transacciones de la cuenta 1001:");
        List<Transaccion> historial = cooperativa.obtenerHistorialCuenta("1001");
        historial.forEach(System.out::println);
        System.out.println();
    }
    
    private static void demostrarReportesEstadisticas(Cooperativa cooperativa) {
        System.out.println("4. REPORTES Y ESTADÍSTICAS");
        System.out.println("---------------------------");
        
        Map<String, Object> estadisticas = cooperativa.obtenerEstadisticas();
        
        System.out.println("Estadísticas de la cooperativa:");
        estadisticas.forEach((clave, valor) -> 
            System.out.println("- " + clave + ": " + valor));
        
        // Demostrar programación funcional con filtros
        System.out.println("\nTransacciones de depósito:");
        cooperativa.getProcesadorTransacciones()
            .obtenerTransaccionesPorTipo(Transaccion.TipoTransaccion.DEPOSITO)
            .forEach(System.out::println);
        
        System.out.println("\nTransacciones de retiro:");
        cooperativa.getProcesadorTransacciones()
            .obtenerTransaccionesPorTipo(Transaccion.TipoTransaccion.RETIRO)
            .forEach(System.out::println);
    }
}