import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ApiService } from '../../../services/api.service';

@Component({
  selector: 'app-resumen',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './resumen.component.html',
  styleUrls: ['./resumen.component.css']
})
export class ResumenComponent implements OnInit {
  usuario = localStorage.getItem('username') || 'Empleado';
  criticos = 0;
  bajos    = 0;
  normales = 0;

  constructor(private api: ApiService) {}

  ngOnInit() {
    this.api.getStock().subscribe({
      next: (d) => {
        this.criticos = d.filter((p: any) => p.estado === 'CRITICO').length;
        this.bajos    = d.filter((p: any) => p.estado === 'BAJO').length;
        this.normales = d.filter((p: any) => p.estado === 'NORMAL').length;
      },
      error: () => {}
    });
  }
}
