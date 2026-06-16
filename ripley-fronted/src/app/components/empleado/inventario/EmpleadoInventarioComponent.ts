import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../../services/api.service';

@Component({
  selector: 'app-empleado-inventario',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './EmpleadoInventarioComponent.html',
  styleUrls: ['./EmpleadoInventarioComponent.css']
})
export class EmpleadoInventarioComponent implements OnInit {
  tab     = 'entrega';
  stock: any[] = [];
  loading = false;

  // Confirmar entrega proveedor
  idProv       = 0;
  idProd       = 0;
  cantRecibida = 0;
  calificacion = 5;
  entregaMsg   = '';
  entregaErr   = '';
  loadingE     = false;

  constructor(private api: ApiService) {}

  ngOnInit() {
    this.loading = true;
    this.api.getStock().subscribe({
      next: (d) => { this.stock = d; this.loading = false; },
      error: () => { this.loading = false; }
    });
  }

  confirmarEntrega() {
    if (!this.idProv || !this.idProd || !this.cantRecibida) {
      this.entregaErr = 'Completa todos los campos.';
      return;
    }
    this.loadingE = true;
    this.entregaMsg = '';
    this.entregaErr = '';
    this.api.registrarEntrega(this.idProv, {
      idProducto:   this.idProd,
      cantidad:     this.cantRecibida,
      calificacion: this.calificacion,
      fechaEntrega: new Date().toISOString().split('T')[0]
    }).subscribe({
      next: () => {
        this.entregaMsg = `Recepción confirmada. ${this.cantRecibida} uds. ingresadas. Calificación: ${this.calificacion}/5.`;
        this.loadingE = false;
      },
      error: (e) => {
        this.entregaErr = e.error?.error || 'Error al registrar entrega.';
        this.loadingE = false;
      }
    });
  }

  getEstadoClass(estado: string): string {
    if (estado === 'NORMAL')  return 'b-g';
    if (estado === 'BAJO')    return 'b-a';
    if (estado === 'CRITICO') return 'b-r';
    return '';
  }
}
