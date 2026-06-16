import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../../services/api.service';

@Component({
  selector: 'app-empleado-alertas',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './EmpleadoAlertasComponent.html',
  styleUrls: ['./EmpleadoAlertasComponent.css']
})
export class EmpleadoAlertasComponent implements OnInit {
  alertas: any[] = [];
  loading = false;

  constructor(private api: ApiService) {}

  ngOnInit() {
    this.loading = true;
    this.api.getAlertas().subscribe({
      next: (d) => { this.alertas = d; this.loading = false; },
      error: () => { this.loading = false; }
    });
  }

  getDotColor(tipo: string): string {
    if (tipo === 'CRITICA')     return '#E24B4A';
    if (tipo === 'ADVERTENCIA') return '#BA7517';
    return '#185FA5';
  }
}
