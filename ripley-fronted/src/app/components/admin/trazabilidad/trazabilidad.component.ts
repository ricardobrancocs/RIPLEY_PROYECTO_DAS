import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../../services/api.service';

@Component({
  selector: 'app-trazabilidad',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './trazabilidad.component.html',
  styleUrls: ['./trazabilidad.component.css']
})
export class TrazabilidadComponent implements OnInit {
  historial: any[] = [];
  loading = false;

  constructor(private api: ApiService) {}

  ngOnInit() {
    this.loading = true;
    this.api.getTrazabilidad().subscribe({
      next: (d) => { this.historial = d; this.loading = false; },
      error: () => { this.loading = false; }
    });
  }

  get entradas() { return this.historial.filter(h => h.tipoMovimiento === 'ENTRADA').length; }
  get salidas()  { return this.historial.filter(h => h.tipoMovimiento === 'SALIDA').length; }

  getBadgeClass(tipo: string): string {
    return tipo === 'ENTRADA' ? 'badge-green' : 'badge-amber';
  }
}
