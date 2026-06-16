-- ================================================================
-- RIPLEY INVENTARIO — Script BD PostgreSQL
-- Base de datos: ripley
-- ================================================================

-- TABLAS
CREATE TABLE IF NOT EXISTS categorias (
    id_categoria SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    costo_total NUMERIC(15,2) DEFAULT 0
);

CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario SERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    correo VARCHAR(150) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    rol VARCHAR(10) CHECK (rol IN ('ADMIN','EMPLEADO')) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT NOW(),
    intentos_fallidos INTEGER DEFAULT 0,
    cuenta_bloqueada BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS proveedores (
    id_proveedor SERIAL PRIMARY KEY,
    empresa VARCHAR(150) NOT NULL,
    contacto VARCHAR(150),
    telefono VARCHAR(20),
    correo VARCHAR(150),
    calificacion_promedio INTEGER DEFAULT 0
);

CREATE TABLE IF NOT EXISTS productos (
    id_producto SERIAL PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    descripcion TEXT,
    codigo_barras VARCHAR(50),
    precio NUMERIC(10,2) NOT NULL,
    stock_actual INTEGER DEFAULT 0,
    stock_minimo INTEGER DEFAULT 0,
    id_categoria INTEGER REFERENCES categorias(id_categoria),
    fecha_registro TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS ventas (
    id_venta SERIAL PRIMARY KEY,
    fecha_venta TIMESTAMP DEFAULT NOW(),
    total NUMERIC(12,2),
    estado VARCHAR(20) DEFAULT 'PENDIENTE',
    id_usuario INTEGER REFERENCES usuarios(id_usuario)
);

CREATE TABLE IF NOT EXISTS detalle_ventas (
    id_detalle SERIAL PRIMARY KEY,
    id_venta INTEGER REFERENCES ventas(id_venta),
    id_producto INTEGER REFERENCES productos(id_producto),
    cantidad INTEGER NOT NULL,
    precio_unitario NUMERIC(10,2),
    subtotal NUMERIC(12,2),
    id_proforma INTEGER
);

CREATE TABLE IF NOT EXISTS entradas_inventario (
    id_entrada SERIAL PRIMARY KEY,
    id_producto INTEGER REFERENCES productos(id_producto),
    cantidad INTEGER NOT NULL,
    fecha_entrada TIMESTAMP DEFAULT NOW(),
    id_proveedor INTEGER REFERENCES proveedores(id_proveedor)
);

CREATE TABLE IF NOT EXISTS salidas_inventario (
    id_salida SERIAL PRIMARY KEY,
    id_producto INTEGER REFERENCES productos(id_producto),
    cantidad INTEGER NOT NULL,
    fecha_salida TIMESTAMP DEFAULT NOW(),
    id_venta INTEGER REFERENCES ventas(id_venta)
);

CREATE TABLE IF NOT EXISTS alertas_stock (
    id_alerta SERIAL PRIMARY KEY,
    id_producto INTEGER REFERENCES productos(id_producto),
    tipo_alerta VARCHAR(20) CHECK (tipo_alerta IN ('CRITICA','ADVERTENCIA','INFORMATIVA')),
    mensaje TEXT,
    fecha_alerta TIMESTAMP DEFAULT NOW(),
    estado VARCHAR(20) DEFAULT 'PENDIENTE'
);

CREATE TABLE IF NOT EXISTS predicciones_ia (
    id_prediccion SERIAL PRIMARY KEY,
    id_producto INTEGER REFERENCES productos(id_producto),
    demanda_estimada NUMERIC(10,2),
    fecha_prediccion DATE,
    estado VARCHAR(20) DEFAULT 'PENDIENTE',
    recomendacion TEXT,
    nivel_confianza NUMERIC(5,2)
);

CREATE TABLE IF NOT EXISTS recomendaciones (
    id_recomendacion SERIAL PRIMARY KEY,
    id_producto INTEGER REFERENCES productos(id_producto),
    nombre_producto VARCHAR(200),
    cantidad_sugerida INTEGER,
    id_proveedor INTEGER REFERENCES proveedores(id_proveedor),
    nombre_proveedor VARCHAR(150),
    costo_estimado NUMERIC(12,2),
    fecha_limite DATE,
    estado VARCHAR(20) DEFAULT 'PENDIENTE'
);

CREATE TABLE IF NOT EXISTS simulaciones (
    id_simulacion SERIAL PRIMARY KEY,
    tipo_cambio NUMERIC(5,2),
    inflacion_proyectada NUMERIC(5,2),
    horizonte_dias INTEGER,
    factor_ajuste NUMERIC(10,4),
    impacto_financiero NUMERIC(15,2),
    factor_ajuste_extra NUMERIC(10,4),
    estado VARCHAR(20) DEFAULT 'COMPLETADA',
    id_usuario INTEGER,
    fecha_ejecucion TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS trazabilidad_movimientos (
    id_movimiento SERIAL PRIMARY KEY,
    id_producto INTEGER REFERENCES productos(id_producto),
    cantidad INTEGER,
    tipo_movimiento VARCHAR(20) CHECK (tipo_movimiento IN ('ENTRADA','SALIDA')),
    responsable VARCHAR(150),
    costo_estimado NUMERIC(12,2),
    descripcion TEXT,
    fecha TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS clasificacion_abc (
    id_clasificacion SERIAL PRIMARY KEY,
    id_producto INTEGER REFERENCES productos(id_producto),
    nombre_producto VARCHAR(200),
    porcentaje_ventas NUMERIC(5,2),
    categoria CHAR(1) CHECK (categoria IN ('A','B','C')),
    fecha_clasificacion TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS devoluciones (
    id_devolucion SERIAL PRIMARY KEY,
    id_venta_original INTEGER REFERENCES ventas(id_venta),
    id_producto INTEGER REFERENCES productos(id_producto),
    cantidad INTEGER NOT NULL,
    motivo VARCHAR(20) CHECK (motivo IN ('DEFECTO','CAMBIO_TALLA','OTRO')),
    estado VARCHAR(20) DEFAULT 'REGISTRADA',
    fecha_devolucion TIMESTAMP DEFAULT NOW()
);

-- ================================================================
-- DATOS INICIALES
-- ================================================================

INSERT INTO categorias (nombre, descripcion) VALUES
('Electrónica','Televisores, laptops, celulares'),
('Ropa','Prendas para hombre, mujer y niños'),
('Hogar','Muebles y electrodomésticos'),
('Deportes','Ropa y equipos deportivos'),
('Juguetería','Juguetes y entretenimiento')
ON CONFLICT DO NOTHING;

INSERT INTO proveedores (empresa, contacto, telefono, correo) VALUES
('Samsung Perú','Carlos Ríos','01-4501234','carlos@samsung.pe'),
('LG Electronics','Ana Torres','01-4502345','ana@lg.pe'),
('Nike Perú','Luis Vega','01-4503456','luis@nike.pe'),
('Adidas Perú','María Paz','01-4504567','maria@adidas.pe'),
('Sony Perú','Jorge Ruiz','01-4505678','jorge@sony.pe')
ON CONFLICT DO NOTHING;

INSERT INTO usuarios (nombre, correo, contrasena, rol) VALUES
('Gabriel Torres','gabriel@ripley.pe','$2a$12$N9RpfH5LJSAMu2yIGSn6pOyFVnhKyVYPTWBZJcFwJQGrXFxc5vD.G','ADMIN'),
('Ana Pérez','ana@ripley.pe','$2a$12$N9RpfH5LJSAMu2yIGSn6pOyFVnhKyVYPTWBZJcFwJQGrXFxc5vD.G','EMPLEADO'),
('Luis Quispe','luis@ripley.pe','$2a$12$N9RpfH5LJSAMu2yIGSn6pOyFVnhKyVYPTWBZJcFwJQGrXFxc5vD.G','EMPLEADO'),
('Manuel García','manuel@ripley.pe','$2a$12$N9RpfH5LJSAMu2yIGSn6pOyFVnhKyVYPTWBZJcFwJQGrXFxc5vD.G','EMPLEADO')
ON CONFLICT DO NOTHING;

INSERT INTO productos (nombre, descripcion, codigo_barras, precio, stock_actual, stock_minimo, id_categoria) VALUES
('Samsung TV 55" 4K','Televisor QLED 55 pulgadas','BAR-001',2499.90,48,5,1),
('LG Refrigeradora 300L','Refrigeradora No Frost','BAR-002',1899.90,10,3,3),
('Nike Air Max 270','Zapatillas running hombre','BAR-003',459.90,50,10,4),
('Adidas Camiseta','Camiseta deportiva manga corta','BAR-004',129.90,50,20,2),
('Sony PlayStation 5','Consola PS5 256GB','BAR-005',2299.90,50,5,1),
('Samsung Galaxy S24','Smartphone 256GB','BAR-006',3199.90,50,5,1),
('Nike Shorts Running','Short deportivo hombre','BAR-007',99.90,48,15,4),
('LG Lavadora 10kg','Lavadora carga frontal','BAR-008',1599.90,7,3,3)
ON CONFLICT DO NOTHING;

INSERT INTO alertas_stock (id_producto, tipo_alerta, mensaje, estado) VALUES
(2,'ADVERTENCIA','LG Refrigeradora 300L tiene stock bajo: 10 unidades','PENDIENTE'),
(8,'CRITICA','LG Lavadora 10kg tiene stock crítico: 7 unidades','PENDIENTE')
ON CONFLICT DO NOTHING;

INSERT INTO recomendaciones (id_producto, nombre_producto, cantidad_sugerida, id_proveedor, nombre_proveedor, costo_estimado, fecha_limite, estado) VALUES
(8,'LG Lavadora 10kg',6,2,'LG Electronics',9599.40,'2026-06-17','PENDIENTE'),
(2,'LG Refrigeradora 300L',6,2,'LG Electronics',11399.40,'2026-06-17','PENDIENTE'),
(7,'Nike Shorts Running',30,3,'Nike Perú',2997.00,'2026-06-15','APROBADA')
ON CONFLICT DO NOTHING;

-- Contraseña para todos: 1234 (hasheada con BCrypt)
-- Para desarrollo puede usar: UPDATE usuarios SET contrasena = '1234' WHERE TRUE;
