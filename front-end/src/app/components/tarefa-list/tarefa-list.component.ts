import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-tarefa-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './tarefa-list.component.html', 
  styleUrls: ['./tarefa-list.component.css']
})
export class TarefaListComponent { 
  message = 'Lista de Tarefas - Em desenvolvimento';
}