package com.fatec.mom.domain.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Reader {
	
	//metodo criado para ser usado nos outros metodos, reduzindo o código
	public Iterator<Row> read(String file) throws IOException{
		File excelfile = new File(file);
		FileInputStream fis = new FileInputStream(excelfile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowit = sheet.rowIterator();
		workbook.close();
		fis.close();
		return rowit;
	}

	//printa o arquivo todo. Cada linha do excel é separada por ------
	//o arquivo das codelist está em "doc/Mockup FATEC/Codelist.xlsx"
	public void printFile(String file) throws IOException {
		Iterator<Row> r = read(file);
		while(r.hasNext()) {
			Row row = r.next();
			Iterator<Cell> cellit = row.cellIterator();
			while(cellit.hasNext()) {
				Cell cell = cellit.next();
				if (cell.toString().isBlank()) {
					System.out.println("NULL");
				}
				else {
					System.out.println(cell.toString());
				}
			}
			System.out.println("\n-----\n");
		}
	}
	
	//retorna uma lista com os dados de um linha do excel. A linha deve ser informada no index
	//quando uma cell está vazia é adicionado NULL na lista
	public ArrayList<String> getRow(String file, int index) throws IOException {
		ArrayList<String> lista = new ArrayList<String>();
		Iterator<Row> r = read(file);
		int counter = 0;
		while(r.hasNext()) {
			Row row = r.next();
			Iterator<Cell> cellit = row.cellIterator();
			if (counter == index) {
				while(cellit.hasNext()) {
					Cell cell = cellit.next();
					if (cell.toString().isBlank()) {
						lista.add("NULL");
					}
					else {
						lista.add(cell.toString());
					}
				}
				
			}
			counter += 1;
		}
		return lista;
	}
	
	//retorna o número de linhas existente na tabela do excel
	public int getSize(String file) throws IOException {
		Iterator<Row> r = read(file);
		int counter = 0;
		while (r.hasNext()) {
			r.next();
			counter += 1;
		}
		return counter;
	}
}
