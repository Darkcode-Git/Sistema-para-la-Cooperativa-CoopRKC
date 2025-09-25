# Sistema-para-la-Cooperativa-CoopRKC

La Cooperativa CoopRKC presta servicios de ahorro y crédito a sus asociados. Cada socio puede abrir diferentes cuentas de ahorro, realizar depósitos, retiros y consultar su saldo. Además, la cooperativa debe registrar todas las transacciones. El director de tecnología ha solicitado a su equipo desarrollar un prototipo en Java.

## Estructura del Proyecto

El prototipo está desarrollado siguiendo los principios de Programación Orientada a Objetos (POO) con elementos de Programación Funcional:

```
src/
├── model/
│   ├── Socio.java              # Representa un socio de la cooperativa
│   ├── CuentaAhorro.java       # Representa una cuenta de ahorro
│   └── Transaccion.java        # Representa una transacción
├── service/
│   ├── Cooperativa.java        # Servicio principal de gestión
│   └── ProcesadorTransacciones.java # Procesamiento de transacciones
└── Main.java                   # Punto de entrada de la aplicación
```

## Características del Sistema

### Modelo de Datos
- **Socio**: Gestiona la información de los asociados (cédula, nombre, teléfono, email)
- **CuentaAhorro**: Maneja cuentas de ahorro con operaciones de depósito y retiro
- **Transaccion**: Registra todas las operaciones realizadas con timestamps

### Servicios
- **Cooperativa**: Servicio principal que coordina todas las operaciones
- **ProcesadorTransacciones**: Maneja el procesamiento y historial de transacciones

### Funcionalidades
- ✅ Registro y gestión de socios
- ✅ Apertura de cuentas de ahorro
- ✅ Depósitos y retiros con validaciones
- ✅ Consulta de saldos
- ✅ Historial completo de transacciones
- ✅ Reportes y estadísticas usando programación funcional
- ✅ Filtrado avanzado de transacciones con predicados

## Cómo Ejecutar

1. Compilar el proyecto:
```bash
javac -cp src src/Main.java src/model/*.java src/service/*.java
```

2. Ejecutar el programa:
```bash
java -cp src Main
```

## Ejemplo de Uso

El programa de demostración muestra:
- Registro de 3 socios
- Apertura de cuentas de ahorro
- Operaciones de depósito y retiro
- Consultas de saldo
- Generación de reportes estadísticos

## Principios de Diseño

- **Encapsulación**: Atributos privados con métodos getter/setter apropiados
- **Inmutabilidad**: Uso de `BigDecimal` para operaciones monetarias precisas
- **Programación Funcional**: Streams y predicados para filtrado de datos
- **Manejo de Opcionales**: Uso de `Optional` para evitar `NullPointerException`
- **Separación de Responsabilidades**: Modelo, servicio y presentación separados
