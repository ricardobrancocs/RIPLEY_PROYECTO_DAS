// login.component.ts
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="login-wrap">
      <div class="login-card">
        <div class="login-logo">
          <div class="logo-box">R</div>
          <div>
            <h1>Ripley Inventario</h1>
            <p>Sistema de gestión de stock</p>
          </div>
        </div>
        <div class="field">
          <label>Usuario</label>
          <input type="text" [(ngModel)]="username" placeholder="gabriel@ripley.pe" (keyup.enter)="login()"/>
        </div>
        <div class="field">
          <label>Contraseña</label>
          <input type="password" [(ngModel)]="password" placeholder="••••" (keyup.enter)="login()"/>
        </div>
        <div class="error" *ngIf="error">{{ error }}</div>
        <button class="btn-login" (click)="login()" [disabled]="loading">
          {{ loading ? 'Ingresando...' : 'Iniciar sesión' }}
        </button>
        <p class="hint">Demo: gabriel&#64;ripley.pe / 1234</p>
        </div>
    </div>
  `,
  styles: [`
    .login-wrap { min-height:100vh; display:flex; align-items:center; justify-content:center; background:#f5f5f3; }
    .login-card { background:#fff; border:1px solid #e5e5e0; border-radius:14px; padding:2rem; width:360px; }
    .login-logo { display:flex; align-items:center; gap:12px; margin-bottom:1.75rem; }
    .logo-box { width:38px; height:38px; border-radius:8px; background:#185FA5; color:#fff; display:flex; align-items:center; justify-content:center; font-weight:600; font-size:1.1rem; flex-shrink:0; }
    h1 { font-size:1rem; font-weight:600; margin:0; }
    p { font-size:0.8rem; color:#888; margin:0; }
    .field { margin-bottom:0.9rem; }
    .field label { display:block; font-size:0.78rem; font-weight:500; color:#555; margin-bottom:4px; }
    .field input { width:100%; border:1px solid #ddd; border-radius:7px; padding:0.6rem 0.85rem; font-size:0.875rem; outline:none; transition:border .2s; box-sizing:border-box; }
    .field input:focus { border-color:#185FA5; }
    .error { color:#E24B4A; font-size:0.8rem; margin-bottom:0.75rem; }
    .btn-login { width:100%; background:#185FA5; color:#fff; border:none; border-radius:7px; padding:0.7rem; font-size:0.9rem; font-weight:600; cursor:pointer; transition:background .2s; }
    .btn-login:hover { background:#0C447C; }
    .btn-login:disabled { opacity:.5; cursor:not-allowed; }
    .hint { font-size:0.72rem; color:#aaa; text-align:center; margin-top:0.75rem; }
  `]
})
export class LoginComponent {
  username = '';
  password = '';
  loading = false;
  error = '';

  constructor(private api: ApiService, private router: Router) {}

  login() {
    if (!this.username || !this.password) { this.error = 'Completa todos los campos.'; return; }
    this.loading = true;
    this.error = '';
    this.api.login({ username: this.username, password: this.password }).subscribe({
      next: (res) => {
      localStorage.setItem('token', res.token);
      localStorage.setItem('rol', res.rol);
      localStorage.setItem('username', res.nombre || res.username || '');
      
      if (res.rol === 'ADMIN') {
        this.router.navigate(['/admin/dashboard']);
      } else {
        this.router.navigate(['/empleado/resumen']);
      }
    },
      error: (e) => {
        if (e.status === 401) {
            this.error = 'Credenciales incorrectas.';
        } else if (e.status === 423) {
            this.error = 'Cuenta bloqueada — espere 2 minutos.';
        } else if (e.status === 500 && e.error?.error?.includes('bloqueada')) {
            this.error = 'Cuenta bloqueada — espere 2 minutos.';
        } else if (e.status === 500 && e.error?.error?.includes('restantes')) {
            this.error = e.error.error;
        } else {
            this.error = 'Error de conexión.';
        }
        this.loading = false;
    }
    });
  }
}
