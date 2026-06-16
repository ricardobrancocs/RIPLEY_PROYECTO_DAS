// ── Auth ─────────────────────────────────────────────────────
export interface LoginRequest {
  username: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  rol: string;       // VENDEDOR | ANALISTA | ADMIN | GERENTE
  username: string;
  nombre: string;
  mensaje: string;
}

// ── Inventario ───────────────────────────────────────────────
export interface MovimientoRequest {
  idProducto: number;
  cantidad: number;
  tipoMovimiento: 'ENTRADA' | 'SALIDA';
  fecha?: string;    // ISO LocalDateTime
}

export interface StockResponse {
  idProducto: number;
  nombre: string;
  stockActual: number;
  stockMinimo: number;
  estado: 'NORMAL' | 'BAJO' | 'CRITICO';
}

// ── Ventas ───────────────────────────────────────────────────
export interface RegistrarPagoDTO {
  idProforma: number;
  montoPagado: number;
  metodoPago: 'EFECTIVO' | 'TARJETA' | 'TRANSFERENCIA';
}

export interface ComprobanteDTO {
  numeroComprobante: string;
  idVenta: number;
  total: number;
  estado: string;
  fechaVenta: string;
}

// ── Alertas ──────────────────────────────────────────────────
export interface AlertaResponse {
  id: number;
  idProducto: number;
  nombreProducto: string;
  nivel: 'INFORMATIVA' | 'ADVERTENCIA' | 'CRITICA';
  mensaje: string;
  estado: 'ACTIVA' | 'ATENDIDA' | 'DESCARTADA';
  fechaGeneracion: string;
}

// ── Proveedores ──────────────────────────────────────────────
export interface ProveedorRequest {
  empresa: string;
  contacto: string;
  telefono: string;
  correo: string;
}

export interface EntregaRequest {
  idProveedor?: number;
  idProducto: number;
  cantidad: number;
  fechaEntrega: string;
  calificacion: number;   // 1-5
}

// ── Predicciones ─────────────────────────────────────────────
export interface PrediccionRequest {
  idProducto: number;
  horizonteDias: number;
}

export interface PrediccionResponse {
  idProducto: number;
  demandaProyectada: number;
  horizonteDias: number;
  fechaGeneracion: string;
  estado: 'COMPLETADA' | 'SIN_DATOS';
}

// ── Reposición ───────────────────────────────────────────────
export interface RecomendacionResponse {
  id: number;
  idProducto: number;
  nombreProducto: string;
  cantidadSugerida: number;
  idProveedorRecomendado: number;
  nombreProveedor: string;
  costoEstimado: number;
  fechaLimitePedido: string;
  estado: 'PENDIENTE' | 'APROBADA';
}

// ── Simulaciones ─────────────────────────────────────────────
export interface SimulacionRequest {
  tipoCambio: number;
  inflacionProyectada: number;
  horizonteDias: number;
}

export interface EscenariosResponse {
  escenarios: {
    nombre: string;
    costoTotal: number;
    impactoFinanciero: number;
    factorAjuste: number;
  }[];
}

// ── Trazabilidad ─────────────────────────────────────────────
export interface MovimientoTrazabilidadRequest {
  idProducto: number;
  ubicacion: string;
  responsable: string;
  descripcion: string;
}

export interface HistorialResponse {
  idMovimiento: number;
  idProducto: number;
  ubicacion: string;
  responsable: string;
  fecha: string;
  descripcion: string;
}

// ── Clasificación ABC ────────────────────────────────────────
export interface ClasificacionResponse {
  idProducto: number;
  nombreProducto: string;
  porcentajeVentas: number;
  categoria: 'A' | 'B' | 'C';
  fechaClasificacion: string;

}

// ── Devoluciones ─────────────────────────────────────────────
export interface DevolucionRequest {
  idVentaOriginal: number;
  idProducto: number;
  cantidad: number;
  motivo: string;
}
