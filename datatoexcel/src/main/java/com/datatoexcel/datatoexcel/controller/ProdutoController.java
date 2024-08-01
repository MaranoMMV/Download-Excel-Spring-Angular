package com.datatoexcel.datatoexcel.controller;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datatoexcel.datatoexcel.entity.Produto;
import com.datatoexcel.datatoexcel.repository.ProdutoRepository;
import com.datatoexcel.datatoexcel.service.ProdutoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class ProdutoController {
	
	private final ProdutoService produtoService;
	private final ProdutoRepository produtorRepository;
	
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadExcel() {
        try {
            List<Produto> entities = produtorRepository.findAll();
            ByteArrayInputStream in = produtoService.generateExcel(entities);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=consulta-produto.xlsx");

            return new ResponseEntity<>(in.readAllBytes(), headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @GetMapping
    public ResponseEntity<List<Produto>> getProdutos() {
        List<Produto> produtos = this.produtoService.listagemProdutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }
}
