import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../../services/api.service';

@Component({
  selector: 'app-empleado-ventas',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './EmpleadoVentasComponent.html',
  styleUrls: ['./EmpleadoVentasComponent.css']
})
export class EmpleadoVentasComponent {
  tab = 'venta';
  paso = 1;
  tipo = '';

  // Datos producto
  idVenta    = 0;
  idProducto = 1;
  cantidad   = 1;
  precio     = 0;

  // Datos cliente (tienda)
  cliente = '';
  dni     = '';

  // Datos delivery
  destinatario    = '';
  direccion       = '';
  empresaDelivery = 'Rappi';
  guia            = '';

  // Pago
  metodoPago    = '';
  montoRecibido = 0;
  digitos       = '';
  tipoTarjeta   = 'Visa';
  nroOperacion  = '';

  // Resultado
  compNum  = 1000;
  historial: any[] = [];

  // Confirmar delivery
  delId   = 0;
  delDest = '';
  delGuia = '';
  delMsg  = '';

  // Devolución
  devVenta  = 0;
  devProd   = 0;
  devCant   = 1;
  devMotivo = 'DEFECTO';
  devMsg    = '';

  constructor(private api: ApiService) {}

  calcVuelto(): string {
    return Math.max(0, this.montoRecibido - (this.precio * this.cantidad)).toFixed(2);
  }

  confirmarVenta() {
    this.historial.unshift({
      comp:  this.compNum++,
      tipo:  this.tipo.toUpperCase(),
      total: (this.precio * this.cantidad).toFixed(2),
      pago:  this.metodoPago
    });
    this.paso = 5;
  }

  resetVenta() {
    this.paso = 1;
    this.tipo = '';
    this.metodoPago = '';
    this.idVenta = 0;
    this.idProducto = 1;
    this.cantidad = 1;
    this.precio = 0;
  }

  confirmarDelivery() {
    this.delMsg = `Delivery #${this.delId} confirmado como entregado a ${this.delDest}. Guía: ${this.delGuia}`;
  }

  registrarDevolucion() {
    const dto = {
      idVentaOriginal: this.devVenta,
      idProducto:      this.devProd,
      cantidad:        this.devCant,
      motivo:          this.devMotivo
    };
    this.api.registrarDevolucion(dto).subscribe({
      next: () => {
        this.devMsg = `Devolución registrada. ${this.devCant} unidad(es) repuestas al stock.`;
      },
      error: (e) => {
        this.devMsg = e.error?.error || 'Error al registrar devolución.';
      }
    });
  }
}
