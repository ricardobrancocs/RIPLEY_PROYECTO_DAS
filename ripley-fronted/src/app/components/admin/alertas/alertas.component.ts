import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../../services/api.service';

@Component({
  selector: 'app-alertas',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './alertas.component.html',
  styleUrls: ['./alertas.component.css']
})
export class AlertasComponent implements OnInit {
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

  atender(id: number) {
    this.api.atenderAlerta(id).subscribe({
      next: () => {
        const a = this.alertas.find(x => x.idAlerta === id);
        if (a) a.estado = 'ATENDIDA';
      },
      error: () => {}
    });
  }

  descartar(id: number) {
    this.api.descartarAlerta(id, 'Descartado por admin').subscribe({
      next: () => {
        this.alertas = this.alertas.filter(x => x.idAlerta !== id);
      },
      error: () => {}
    });
  }

  getDotClass(tipo: string): string {
    if (tipo === 'CRITICA')      return 'dot-r';
    if (tipo === 'ADVERTENCIA')  return 'dot-a';
    return 'dot-b';
  }
}
