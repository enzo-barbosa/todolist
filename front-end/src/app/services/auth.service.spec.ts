import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AuthService } from './auth.service';
import { Usuario } from '../models/usuario';

describe('AuthService', () => {
  let service: AuthService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AuthService]
    });
    service = TestBed.inject(AuthService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
    localStorage.clear();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should login user', () => {
    const mockUser: Usuario = {
      id: 1,
      nome: 'João Silva',
      email: 'joao@email.com'
    };

    service.login('joao@email.com', 'senha123').subscribe(user => {
      expect(user).toEqual(mockUser);
    });

    const req = httpMock.expectOne('http://localhost:8080/usuario/email/joao@email.com');
    expect(req.request.method).toBe('GET');
    expect(req.request.headers.get('Authorization')).toBeDefined();
    req.flush(mockUser);
  });
});