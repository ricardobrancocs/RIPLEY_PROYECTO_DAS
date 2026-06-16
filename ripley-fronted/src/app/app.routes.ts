import { Routes } from '@angular/router';
import { authGuard } from './shared/auth.guard';

import { LoginComponent } from './components/login/login.component';
import { AdminLayoutComponent } from './components/admin/admin-layout/admin-layout.component';
import { EmpleadoLayoutComponent } from './components/empleado/empleado-layout/empleado-layout.component';

// Admin components
import { DashboardComponent } from './components/admin/dashboard/dashboard.component';
import { InventarioComponent } from './components/admin/inventario/inventario.component';
import { VentasComponent } from './components/admin/ventas/ventas.component';
import { AlertasComponent } from './components/admin/alertas/alertas.component';
import { PrediccionesComponent } from './components/admin/predicciones/predicciones.component';
import { ReposicionComponent } from './components/admin/reposicion/reposicion.component';
import { SimulacionesComponent } from './components/admin/simulaciones/simulaciones.component';
import { ProveedoresComponent } from './components/admin/proveedores/proveedores.component';
import { TrazabilidadComponent } from './components/admin/trazabilidad/trazabilidad.component';

// Empleado components
import { ResumenComponent } from './components/empleado/resumen/resumen.component';
import { EmpleadoInventarioComponent } from './components/empleado/inventario/EmpleadoInventarioComponent';
import { EmpleadoVentasComponent } from './components/empleado/ventas/EmpleadoVentasComponent';
import { EmpleadoAlertasComponent } from './components/empleado/alertas/EmpleadoAlertasComponent';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },

  {
    path: 'admin',
    component: AdminLayoutComponent,
    canActivate: [authGuard],
    children: [
      { path: 'dashboard',    component: DashboardComponent },
      { path: 'inventario',   component: InventarioComponent },
      { path: 'ventas',       component: VentasComponent },
      { path: 'alertas',      component: AlertasComponent },
      { path: 'predicciones', component: PrediccionesComponent },
      { path: 'reposicion',   component: ReposicionComponent },
      { path: 'simulaciones', component: SimulacionesComponent },
      { path: 'proveedores',  component: ProveedoresComponent },
      { path: 'trazabilidad', component: TrazabilidadComponent },
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
    ]
  },

  {
    path: 'empleado',
    component: EmpleadoLayoutComponent,
    canActivate: [authGuard],
    children: [
      { path: 'resumen',    component: ResumenComponent },
      { path: 'inventario', component: EmpleadoInventarioComponent },
      { path: 'ventas',     component: EmpleadoVentasComponent },
      { path: 'alertas',    component: EmpleadoAlertasComponent },
      { path: '', redirectTo: 'resumen', pathMatch: 'full' }
    ]
  },

  { path: '**', redirectTo: 'login' }
];