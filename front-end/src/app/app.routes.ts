import { Routes } from '@angular/router';
import { TarefaListComponent } from './components/tarefa-list/tarefa-list.component';
import { TarefaFormComponent } from './components/tarefa-form/tarefa-form.component';
import { LoginComponent } from './components/login/login.component';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'tarefas', component: TarefaListComponent },
  { path: 'tarefas/nova', component: TarefaFormComponent },
  { path: 'tarefas/editar/:id', component: TarefaFormComponent },
  { path: '**', redirectTo: '/login' }
];