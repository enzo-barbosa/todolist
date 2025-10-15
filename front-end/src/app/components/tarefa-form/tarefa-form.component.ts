import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-tarefa-form',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './tarefa-form.component.html', 
  styleUrls: ['./tarefa-form.component.css']
})
export class TarefaFormComponent { 
  message = 'Formulário de Tarefa - Em desenvolvimento';
}