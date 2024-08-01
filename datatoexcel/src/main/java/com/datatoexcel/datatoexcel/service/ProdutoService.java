package com.datatoexcel.datatoexcel.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datatoexcel.datatoexcel.entity.Produto;
import com.datatoexcel.datatoexcel.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public ByteArrayInputStream generateExcel(List<Produto> entities) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Sheet1");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Id");
            headerRow.createCell(1).setCellValue("Nome");
            // Add more headers as needed

            // Add data rows
            int rowNum = 1;
            for (Produto entity : entities) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(entity.getId());
                row.createCell(1).setCellValue(entity.getNome());
                // Add more columns as needed
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
	
	public List<Produto> listagemProdutos(){
		return this.produtoRepository.findAll();
	}

}
