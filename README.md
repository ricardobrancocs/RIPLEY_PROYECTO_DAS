# Ripley Inventario — Sistema de Gestión de Stock

## Stack tecnológico
- **Backend:** Java EE 10 · WildFly 31 · JAX-RS · Hibernate 6.4 · CDI
- **Seguridad:** JWT (JJWT 0.12.3) · BCrypt (jBCrypt 0.4)
- **Base de datos:** PostgreSQL 18 · 15 tablas
- **Frontend:** Angular 17 Standalone · TypeScript 5 · RxJS

## Arquitectura — 4 capas (Clean Architecture)
```
presentation/  → routes/ + filters/ + controllers/
application/   → services/ + dtos/
domain/        → entities/ + interfaces/
infrastructure/ → security/ + models/ + mappings/ + repositories/
```

## Arranque backend
```powershell
cd ripley-backend
mvn clean package
copy target\proyecto_final.war C:\servidor\wildfly-31.0.0.Final\standalone\deployments\
cd C:\servidor\wildfly-31.0.0.Final\bin
.\standalone.bat
```

## Arranque frontend
```powershell
cd ripley-fronted
npm install
ng serve
```

## Usuarios de prueba
| Correo | Contraseña | Rol |
|--------|-----------|-----|
| gabriel@ripley.pe | 1234 | ADMIN |
| ana@ripley.pe | 1234 | EMPLEADO |
| luis@ripley.pe | 1234 | EMPLEADO |

> Nota: Las contraseñas en BD están hasheadas con BCrypt.
> Para desarrollo ejecutar: `UPDATE usuarios SET contrasena = '1234' WHERE TRUE;`
> antes de ejecutar el script completo.

## Endpoints principales
- POST /api/auth/login
- GET  /api/inventario/stock
- POST /api/inventario/movimiento
- GET  /api/ventas
- POST /api/ventas/{id}/pago
- POST /api/devoluciones
- GET  /api/alertas
- POST /api/reposicion/aprobar/{id}
- GET  /api/proveedores
- POST /api/proveedores/{id}/entregas
