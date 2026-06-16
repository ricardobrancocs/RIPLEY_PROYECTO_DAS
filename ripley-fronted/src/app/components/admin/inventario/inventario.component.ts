import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../../services/api.service';

@Component({
  selector: 'app-inventario',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './inventario.component.html',
  styleUrls: ['./inventario.component.css']
})
export class InventarioComponent implements OnInit {
  stock: any[] = [];
  loading = false;

  constructor(private api: ApiService) {}

  ngOnInit() {
    this.loading = true;
    this.api.getStock().subscribe({
      next: (d) => { this.stock = d; this.loading = false; },
      error: () => { this.loading = false; }
    });
  }

  contarEstado(estado: string): number {
    return this.stock.filter(p => p.estado === estado).length;
  }

  getEstadoClass(estado: string): string {
    if (estado === 'NORMAL')  return 'badge-green';
    if (estado === 'BAJO')    return 'badge-amber';
    if (estado === 'CRITICO') return 'badge-red';
    return '';
  }
}