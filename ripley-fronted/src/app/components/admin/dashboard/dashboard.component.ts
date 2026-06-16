import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ApiService } from '../../../services/api.service';
import { StockResponse } from '../../../models/models';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  stock: StockResponse[] = [];
  alertasCount = 0;
  ventasCount = 0;
  loading = false;
  usuario = localStorage.getItem('username') || '';

  constructor(private api: ApiService) {}

  ngOnInit() {
    this.loading = true;
    this.api.getStock().subscribe({
      next: (data) => {
        this.stock = data;
        this.loading = false;
        this.alertasCount = data.filter(p => p.estado !== 'NORMAL').length;
      },
      error: () => { this.loading = false; }
    });
    this.api.getVentas().subscribe({
      next: (v) => this.ventasCount = v.length,
      error: () => {}
    });
  }
}
