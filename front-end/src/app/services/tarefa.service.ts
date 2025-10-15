import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tarefa } from '../models/tarefa';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TarefaService {
  private apiUrl = 'http://localhost:8080/tarefa';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  private getHeaders(): HttpHeaders {
    return this.authService.getAuthHeaders();
  }

  getAllTarefas(): Observable<Tarefa[]> {
    return this.http.get<Tarefa[]>(this.apiUrl, { headers: this.getHeaders() });
  }

  getTarefaById(id: number): Observable<Tarefa> {
    return this.http.get<Tarefa>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }

  createTarefa(tarefa: Tarefa): Observable<Tarefa> {
    return this.http.post<Tarefa>(this.apiUrl, tarefa, { headers: this.getHeaders() });
  }

  updateTarefa(id: number, tarefa: Tarefa): Observable<Tarefa> {
    return this.http.put<Tarefa>(`${this.apiUrl}/${id}`, tarefa, { headers: this.getHeaders() });
  }

  deleteTarefa(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }

  // Método auxiliar para mapear cores baseadas na prioridade
  getPrioridadeColor(prioridade: string): string {
    switch (prioridade) {
      case 'ALTA': return '#dc3545'; // vermelho
      case 'MEDIA': return '#ffc107'; // amarelo
      case 'BAIXA': return '#28a745'; // verde
      default: return '#6c757d'; // cinza
    }
  }

  getStatusColor(status: string): string {
    switch (status) {
      case 'CONCLUIDA': return '#28a745'; // verde
      case 'ARQUIVADA': return '#6c757d'; // cinza
      case 'PENDENTE': return '#ffc107'; // amarelo
      default: return '#6c757d';
    }
  }
}