import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../../services/api.service';
@Component({
  selector: 'app-proveedores',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './proveedores.component.html',
  styleUrls: ['./proveedores.component.css']
})
export class ProveedoresComponent implements OnInit {

  tab = 'lista';
  proveedores: any[] = [];
  loadingLista = false;
  editando: any = null;

  formProveedor = { empresa: '', contacto: '', telefono: '', correo: '' };
  loadingP = false; okP = false; errP = '';

  formEntrega = {
    idProveedor:  null as number | null,
    idProducto:   null as number | null,
    cantidad:     null as number | null,
    calificacion: 5
  };
  loadingE = false; okE = false; errE = '';

  constructor(private api: ApiService) {}

  ngOnInit() {
    this.cargarProveedores();
  }

  cargarProveedores() {
    this.loadingLista = true;
    // getProveedores no existe en el api.service — usamos lista vacía
    // hasta que el endpoint GET /api/proveedores esté implementado
    this.loadingLista = false;
    this.proveedores  = [];
  }

  iniciarEdicion(p: any) {
    this.editando = { ...p };
    this.tab = 'registrar';
  }

  cancelarEdicion() {
    this.editando = null;
  }

  registrarProveedor() {
    const datos = this.editando ?? this.formProveedor;
    if (!datos.empresa) { this.errP = 'Ingresa el nombre de la empresa.'; return; }
    this.loadingP = true; this.okP = false; this.errP = '';
    this.api.registrarProveedor(datos as any).subscribe({
      next: () => {
        this.okP = true;
        this.loadingP = false;
        this.editando = null;
        this.formProveedor = { empresa: '', contacto: '', telefono: '', correo: '' };
        this.cargarProveedores();
      },
      error: (e) => {
        this.errP = e.error?.error || 'Error al registrar proveedor.';
        this.loadingP = false;
      }
    });
  }

  registrarEntrega() {
    if (!this.formEntrega.idProveedor) { this.errE = 'Ingresa el ID del proveedor.'; return; }
    this.loadingE = true; this.okE = false; this.errE = '';
    this.api.registrarEntrega(this.formEntrega.idProveedor, this.formEntrega as any).subscribe({
      next: () => { this.okE = true; this.loadingE = false; },
      error: (e) => {
        this.errE = e.error?.error || 'Error al registrar entrega.';
        this.loadingE = false;
      }
    });
  }
}