export interface Usuario {
  id?: number;
  nome: string;
  email: string;
  senha?: string;
}

export interface UsuarioDTO {
  id?: number;
  nome: string;
  email: string;
}