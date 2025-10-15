import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TarefaService } from './tarefa.service';
import { AuthService } from './auth.service';
import { Tarefa, Prioridade, StatusTarefa } from '../models/tarefa';

describe('TarefaService', () => {
  let service: TarefaService;
  let httpMock: HttpTestingController;
  let authService: AuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [TarefaService, AuthService]
    });
    service = TestBed.inject(TarefaService);
    httpMock = TestBed.inject(HttpTestingController);
    authService = TestBed.inject(AuthService);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get all tarefas', () => {
    const mockTarefas: Tarefa[] = [
      {
        id: 1,
        titulo: 'Teste Tarefa',
        descricao: 'Descrição teste',
        prioridade: Prioridade.ALTA,
        status: StatusTarefa.PENDENTE,
        usuarioId: 1
      }
    ];

    service.getAllTarefas().subscribe(tarefas => {
      expect(tarefas).toEqual(mockTarefas);
    });

    const req = httpMock.expectOne('http://localhost:8080/tarefa');
    expect(req.request.method).toBe('GET');
    req.flush(mockTarefas);
  });

  it('should return correct colors for priorities', () => {
    expect(service.getPrioridadeColor('ALTA')).toBe('#dc3545');
    expect(service.getPrioridadeColor('MEDIA')).toBe('#ffc107');
    expect(service.getPrioridadeColor('BAIXA')).toBe('#28a745');
  });
});