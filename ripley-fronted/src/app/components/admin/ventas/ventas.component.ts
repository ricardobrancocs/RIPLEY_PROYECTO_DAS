import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../../services/api.service';

@Component({
  selector: 'app-ventas',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './ventas.component.html',
  styleUrls: ['./ventas.component.css']
})
export class VentasComponent implements OnInit {
  ventas: any[] = [];
  loading = false;
  completadas = 0;
  pendientes  = 0;
  devoluciones = 0;

  constructor(private api: ApiService) {}

  ngOnInit() {
    this.loading = true;
    this.api.getVentas().subscribe({
      next: (d) => {
        this.ventas = d;
        this.loading = false;
        this.completadas  = d.filter((v: any) => v.estado === 'COMPLETADA').length;
        this.pendientes   = d.filter((v: any) => v.estado === 'PENDIENTE').length;
        this.devoluciones = d.filter((v: any) => v.estado === 'DEVOLUCION').length;
      },
      error: () => { this.loading = false; }
    });
  }

  getBadgeClass(estado: string): string {
    if (estado === 'COMPLETADA') return 'badge-green';
    if (estado === 'PENDIENTE')  return 'badge-amber';
    return 'badge-red';
  }
}
