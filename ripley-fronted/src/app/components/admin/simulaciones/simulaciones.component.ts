import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../../services/api.service';

@Component({
  selector: 'app-simulaciones',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './simulaciones.component.html',
  styleUrls: ['./simulaciones.component.css']
})
export class SimulacionesComponent {
  form = {
    tipoCambio:          3.75,
    inflacionProyectada: 3.5,
    horizonteDias:       30,
    factorAjuste:        1.0
  };

  resultado: any  = null;
  loading  = false;
  error    = '';

  constructor(private api: ApiService) {}

  ejecutar() {
    this.loading  = true;
    this.error    = '';
    this.resultado = null;
    this.api.ejecutarSimulacion(this.form as any).subscribe({
      next: (r) => { this.resultado = r; this.loading = false; },
      error: (e) => { this.error = e.error?.error || 'Error al ejecutar simulación.'; this.loading = false; }
    });
  }
}
