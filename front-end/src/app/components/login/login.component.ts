import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Usuario } from '../../models/usuario';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.component.html', 
  styleUrls: ['./login.component.css']
})
export class LoginComponent { 
  email: string = '';
  password: string = '';
  errorMessage: string = '';
  isLoading: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onSubmit(): void {
    this.isLoading = true;
    this.errorMessage = '';

    this.authService.login(this.email, this.password).subscribe({
      next: (usuario: Usuario) => {
        this.isLoading = false;
        this.router.navigate(['/tarefas']);
      },
      error: (error) => {
        this.isLoading = false;
        this.errorMessage = 'Credenciais inválidas. Tente: joao@email.com / senha123';
        console.error('Login error:', error);
      }
    });
  }
}