import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Produto } from '../produto/Produto';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  private baseUrl = 'http://localhost:8080/api/produtos';

  constructor(private http: HttpClient) { }

  downloadExcel() {
    return this.http.get(`${this.baseUrl}/download`, { responseType: 'blob' });
  }

  list(): Observable<Produto[]>{
    return this.http.get<Produto[]>(`${this.baseUrl}`)
  }
}
