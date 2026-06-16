import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../../services/api.service';

@Component({
  selector: 'app-reposicion',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './reposicion.component.html',
  styleUrls: ['./reposicion.component.css']
})
export class ReposicionComponent implements OnInit {
  recomendaciones: any[] = [];
  loading = false;

  constructor(private api: ApiService) {}

  ngOnInit() {
    this.loading = true;
    this.api.getRecomendaciones().subscribe({
      next: (d) => { this.recomendaciones = d; this.loading = false; },
      error: () => { this.loading = false; }
    });
  }

  get pendientes() { return this.recomendaciones.filter(r => r.estado === 'PENDIENTE').length; }
  get aprobadas()  { return this.recomendaciones.filter(r => r.estado === 'APROBADA').length; }

  aprobar(id: number) {
    this.api.aprobarRecomendacion(id).subscribe({
      next: () => {
        const r = this.recomendaciones.find(x => x.idRecomendacion === id);
        if (r) r.estado = 'APROBADA';
      },
      error: () => {}
    });
  }
}
