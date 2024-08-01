package com.datatoexcel.datatoexcel.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.datatoexcel.datatoexcel.entity.Produto;
import com.datatoexcel.datatoexcel.repository.ProdutoRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Produto produto1 = new Produto(null, "Cadeira");
		Produto produto2 = new Produto(null, "Mesa");
		Produto produto3 = new Produto(null, "Monitor");
		Produto produto4 = new Produto(null, "Mouse");
		Produto produto5 = new Produto(null, "teclado");
		
		this.produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4, produto5));
		
	}
	
	
}
