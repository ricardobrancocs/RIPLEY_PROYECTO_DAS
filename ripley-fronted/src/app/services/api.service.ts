import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {
  LoginRequest, AuthResponse,
  MovimientoRequest, StockResponse,
  RegistrarPagoDTO, ComprobanteDTO,
  AlertaResponse, ProveedorRequest, EntregaRequest,
  PrediccionRequest, PrediccionResponse,
  RecomendacionResponse, SimulacionRequest, EscenariosResponse,
  MovimientoTrazabilidadRequest, HistorialResponse,
  ClasificacionResponse
} from '../models/models';

@Injectable({ providedIn: 'root' })
export class ApiService {

  // Con el proxy de Angular apunta a /api → WildFly :8080/proyecto_final/api
  private readonly base = '/api';

  constructor(private http: HttpClient) {}

  private headers(): HttpHeaders {
  const token = localStorage.getItem('token');
  let h = new HttpHeaders({ 'Content-Type': 'application/json' });
  if (token) h = h.set('Authorization', `Bearer ${token}`);  // ← agregar Bearer
  return h;
}

  // ── Auth ──────────────────────────────────────────────────
  login(dto: LoginRequest): Observable<AuthResponse> {
  return this.http.post<AuthResponse>(
    `${this.base}/auth/login`, dto,
    { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) }
  );
}

  logout(): Observable<any> {
    return this.http.post(`${this.base}/auth/logout`, {}, { headers: this.headers() });
  }

  // ── Inventario ────────────────────────────────────────────
  registrarMovimiento(dto: MovimientoRequest): Observable<any> {
    return this.http.post(`${this.base}/inventario/movimiento`, dto, { headers: this.headers() });
  }

  consultarStock(idProducto: number): Observable<StockResponse> {
    return this.http.get<StockResponse>(`${this.base}/inventario/stock/${idProducto}`, { headers: this.headers() });
  }

  // ── Ventas ────────────────────────────────────────────────
  registrarPago(idVenta: number, dto: RegistrarPagoDTO): Observable<ComprobanteDTO> {
    return this.http.post<ComprobanteDTO>(`${this.base}/ventas/${idVenta}/pago`, dto, { headers: this.headers() });
  }

  getVenta(idVenta: number): Observable<any> {
    return this.http.get(`${this.base}/ventas/${idVenta}`, { headers: this.headers() });
  }

  // ── Alertas ───────────────────────────────────────────────
  getAlertas(): Observable<AlertaResponse[]> {
    return this.http.get<AlertaResponse[]>(`${this.base}/alertas`, { headers: this.headers() });
  }

  atenderAlerta(id: number): Observable<any> {
    return this.http.put(`${this.base}/alertas/${id}/atender`, {}, { headers: this.headers() });
  }

  descartarAlerta(id: number, justificacion: string): Observable<any> {
    return this.http.put(`${this.base}/alertas/${id}/descartar?justificacion=${justificacion}`, {}, { headers: this.headers() });
  }

  // ── Proveedores ───────────────────────────────────────────
  registrarProveedor(dto: ProveedorRequest): Observable<any> {
    return this.http.post(`${this.base}/proveedores`, dto, { headers: this.headers() });
  }

  registrarEntrega(idProveedor: number, dto: EntregaRequest): Observable<any> {
    return this.http.post(`${this.base}/proveedores/${idProveedor}/entregas`, dto, { headers: this.headers() });
  }

  // ── Predicciones ──────────────────────────────────────────
  getPredicciones(): Observable<PrediccionResponse[]> {
    return this.http.get<PrediccionResponse[]>(`${this.base}/predicciones`, { headers: this.headers() });
  }

  crearPrediccion(dto: PrediccionRequest): Observable<PrediccionResponse> {
    return this.http.post<PrediccionResponse>(`${this.base}/predicciones`, dto, { headers: this.headers() });
  }

  // ── Reposición ────────────────────────────────────────────
  getRecomendaciones(): Observable<RecomendacionResponse[]> {
    return this.http.get<RecomendacionResponse[]>(`${this.base}/reposicion/recomendaciones`, { headers: this.headers() });
  }

  aprobarRecomendacion(id: number): Observable<any> {
    return this.http.post(`${this.base}/reposicion/aprobar/${id}`, {}, { headers: this.headers() });
  }

  // ── Simulaciones ──────────────────────────────────────────
  ejecutarSimulacion(dto: SimulacionRequest): Observable<EscenariosResponse> {
    return this.http.post<EscenariosResponse>(`${this.base}/simulaciones`, dto, { headers: this.headers() });
  }

  // ── Trazabilidad ──────────────────────────────────────────
  getTrazabilidad(): Observable<HistorialResponse[]> {
    return this.http.get<HistorialResponse[]>(`${this.base}/trazabilidad`, { headers: this.headers() });
  }

  registrarMovimientoTrazabilidad(dto: MovimientoTrazabilidadRequest): Observable<any> {
    return this.http.post(`${this.base}/trazabilidad`, dto, { headers: this.headers() });
  }

  // ── Clasificación ABC ─────────────────────────────────────
  getClasificacionAbc(): Observable<ClasificacionResponse[]> {
    return this.http.get<ClasificacionResponse[]>(`${this.base}/clasificacion-abc`, { headers: this.headers() });
  }

  // ── Stock general ──────────────────────────────────────────────
  getStock(): Observable<any[]> {
    return this.http.get<any[]>(`${this.base}/inventario/stock`, { headers: this.headers() });
  }

  // ── Ventas lista ───────────────────────────────────────────────
  getVentas(): Observable<any[]> {
    return this.http.get<any[]>(`${this.base}/ventas`, { headers: this.headers() });
  }

  // ── Devoluciones ───────────────────────────────────────────────
  registrarDevolucion(dto: any): Observable<any> {
    return this.http.post(`${this.base}/ventas/devolucion`, dto, { headers: this.headers() });
  }
}
