import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../../services/api.service';

@Component({
  selector: 'app-predicciones',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './predicciones.component.html',
  styleUrls: ['./predicciones.component.css']
})
export class PrediccionesComponent implements OnInit {
  predicciones: any[] = [];
  cargando = false;
  loading  = false;
  error    = '';

  form = {
    idProducto:    null as number | null,
    horizonteDias: 30
  };

  constructor(private api: ApiService) {}

  ngOnInit() {
    this.cargando = true;
    this.api.getPredicciones().subscribe({
      next: (d) => { this.predicciones = d; this.cargando = false; },
      error: () => { this.cargando = false; }
    });
  }

  ejecutar() {
    if (!this.form.idProducto) { this.error = 'Ingresa el ID del producto.'; return; }
    this.loading = true;
    this.error   = '';
    this.api.crearPrediccion(this.form as any).subscribe({
      next: (r) => { this.predicciones.unshift(r); this.loading = false; },
      error: (e) => { this.error = e.error?.error || 'Error al ejecutar predicción.'; this.loading = false; }
    });
  }
}
