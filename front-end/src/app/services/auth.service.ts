import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Usuario } from '../models/usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080';
  private currentUser: Usuario | null = null;

  constructor(private http: HttpClient) {}

  // Autenticação HTTP Basic
  private createAuthHeader(username: string, password: string): HttpHeaders {
    const authToken = btoa(`${username}:${password}`);
    return new HttpHeaders({
      'Authorization': `Basic ${authToken}`,
      'Content-Type': 'application/json'
    });
  }

  login(email: string, password: string): Observable<Usuario> {
    const headers = this.createAuthHeader(email, password);
    
    return this.http.get<Usuario>(`${this.apiUrl}/usuario/email/${email}`, { headers })
      .pipe(
        tap(usuario => {
          this.currentUser = usuario;
          // Salvar credenciais para requests futuros
          localStorage.setItem('currentUser', JSON.stringify(usuario));
          localStorage.setItem('authToken', btoa(`${email}:${password}`));
        })
      );
  }

  logout(): void {
    this.currentUser = null;
    localStorage.removeItem('currentUser');
    localStorage.removeItem('authToken');
  }

  getCurrentUser(): Usuario | null {
    if (!this.currentUser) {
      const stored = localStorage.getItem('currentUser');
      this.currentUser = stored ? JSON.parse(stored) : null;
    }
    return this.currentUser;
  }

  isAuthenticated(): boolean {
    return this.getCurrentUser() !== null;
  }

  getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('authToken');
    if (token) {
      return new HttpHeaders({
        'Authorization': `Basic ${token}`,
        'Content-Type': 'application/json'
      });
    }
    return new HttpHeaders({ 'Content-Type': 'application/json' });
  }
}