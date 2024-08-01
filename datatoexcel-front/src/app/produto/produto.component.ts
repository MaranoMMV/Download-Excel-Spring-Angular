import { Component, OnInit } from '@angular/core';
import { ProdutoService } from '../services/produto.service';
import { HttpClientModule } from '@angular/common/http';
import {MatTableModule} from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import { MatTableDataSource } from '@angular/material/table';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { Produto } from './Produto';

@Component({
  selector: 'app-produto',
  standalone: true,
  imports: [HttpClientModule, MatTableModule, MatIconModule, MatButtonModule],
  templateUrl: './produto.component.html',
  styleUrl: './produto.component.scss',
  providers: [ProdutoService]
})
export class ProdutoComponent implements OnInit {

  constructor(private produtoService: ProdutoService) { }

  produtosDataSource!:  MatTableDataSource<Produto>
   displayedColumns: string[] = ['id', 'nome'];

  ngOnInit(): void {
    this.listarProdutos()


  }

  downloadExcelFile() {
    this.produtoService.downloadExcel().subscribe(response => {
      const blob = new Blob([response], { type: 'application/vnd.ms-excel' });
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'produtos.xlsx';
      a.click();
      window.URL.revokeObjectURL(url);
    });
  }

  listarProdutos() {
      this.produtoService.list().subscribe(
        (response: any) => {
          this.produtosDataSource = new MatTableDataSource<any>(response);
        },
      );
    }
  }
