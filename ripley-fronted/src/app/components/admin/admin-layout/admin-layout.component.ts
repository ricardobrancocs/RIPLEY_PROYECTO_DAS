import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';

@Component({
selector: 'app-admin-layout',
standalone: true,
imports: [CommonModule, RouterModule],
template: `
    <div class="layout">
    <aside class="sidebar">
        <div class="logo"><div class="logo-box">R</div><span>Ripley Inv.</span></div>
        <nav>
        <div class="sec">Principal</div>
        <a routerLink="/admin/dashboard" routerLinkActive="active" class="ni">Dashboard</a>
        <a routerLink="/admin/inventario" routerLinkActive="active" class="ni">Inventario</a>
        <a routerLink="/admin/ventas" routerLinkActive="active" class="ni">Ventas</a>
        <div class="sec">Análisis</div>
        <a routerLink="/admin/alertas" routerLinkActive="active" class="ni">Alertas</a>
        <a routerLink="/admin/predicciones" routerLinkActive="active" class="ni">Predicciones IA</a>
        <a routerLink="/admin/reposicion" routerLinkActive="active" class="ni">Reposición</a>
        <a routerLink="/admin/simulaciones" routerLinkActive="active" class="ni">Simulaciones</a>
        <div class="sec">Gestión</div>
        <a routerLink="/admin/proveedores" routerLinkActive="active" class="ni">Proveedores</a>
        <a routerLink="/admin/trazabilidad" routerLinkActive="active" class="ni">Trazabilidad</a>
        </nav>
        <div class="foot">
        <div class="urow">
            <div class="av">{{ initiales }}</div>
            <div><div class="uname">{{ usuario }}</div><div class="urole">ADMIN</div></div>
        </div>
        <button class="btn-logout" (click)="logout()">Cerrar sesión</button>
        </div>
    </aside>
    <main class="main">
        <router-outlet></router-outlet>
    </main>
    </div>

    `,styles: [`
    .layout{display:flex;height:100vh;overflow:hidden}
    .sidebar{width:200px;background:#f8f8f8;border-right:1px solid #eee;display:flex;flex-direction:column;flex-shrink:0}
    .logo{padding:14px;border-bottom:1px solid #eee;display:flex;align-items:center;gap:8px;font-weight:500}
    .logo-box{width:26px;height:26px;background:#185FA5;color:#fff;border-radius:6px;display:flex;align-items:center;justify-content:center;font-size:12px;font-weight:500}
    nav{padding:8px;flex:1;overflow-y:auto}
    .sec{font-size:10px;color:#999;padding:10px 8px 3px;text-transform:uppercase;letter-spacing:.05em}
    .ni{display:block;padding:6px 8px;border-radius:6px;color:#555;font-size:12px;text-decoration:none;margin-bottom:1px}
    .ni:hover,.ni.active{background:#E6F1FB;color:#185FA5;font-weight:500}
    .foot{padding:10px;border-top:1px solid #eee}
    .urow{display:flex;align-items:center;gap:8px;margin-bottom:6px}
    .av{width:28px;height:28px;border-radius:50%;background:#E6F1FB;color:#185FA5;display:flex;align-items:center;justify-content:center;font-size:11px;font-weight:500}
    .uname{font-size:11px;font-weight:500} .urole{font-size:10px;color:#999}
    .btn-logout{width:100%;background:none;border:1px solid #ddd;border-radius:6px;padding:5px;font-size:11px;cursor:pointer;color:#666}
    .main{flex:1;display:flex;flex-direction:column;overflow:hidden}
`]
})
export class AdminLayoutComponent {
usuario = localStorage.getItem('username') || 'Admin';
initiales = this.usuario.split(' ').map(n => n[0]).join('').toUpperCase().slice(0, 2);

constructor(private router: Router) {}

logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
    }
}