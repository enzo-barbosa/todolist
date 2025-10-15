import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common'; 
import { AuthService } from './services/auth.service';
import { Usuario } from './models/usuario';

@Component({
  selector: 'app-root',
  standalone: true, 
  imports: [RouterModule, CommonModule], 
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'TodoList';
  currentUser: Usuario | null = null;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {
    this.currentUser = this.authService.getCurrentUser();
  }

  logout(): void {
    this.authService.logout();
    this.currentUser = null;
    this.router.navigate(['/login']);
  }

  isLoginPage(): boolean {
    return this.router.url === '/login';
  }
}