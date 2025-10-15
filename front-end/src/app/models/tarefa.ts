export interface Tarefa {
  id?: number;
  titulo: string;
  descricao: string;
  dataCriacao?: string;
  prioridade: Prioridade;
  status: StatusTarefa;
  usuarioId: number;
}

export enum Prioridade {
  BAIXA = 'BAIXA',
  MEDIA = 'MEDIA', 
  ALTA = 'ALTA'
}

export enum StatusTarefa {
  PENDENTE = 'PENDENTE',
  CONCLUIDA = 'CONCLUIDA',
  ARQUIVADA = 'ARQUIVADA'
}