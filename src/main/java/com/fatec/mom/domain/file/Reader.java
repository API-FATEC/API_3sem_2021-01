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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Reader {

	@Value("${default-upload-path}")
	private String defaultPath;
	private int page = 0;

	//metodo criado para ser usado nos outros metodos, reduzindo o código
	public Iterator<Row> read(String file) throws IOException{
		File excelfile = new File(String.format("%s/%s", defaultPath, file));
		FileInputStream fis = new FileInputStream(excelfile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(page);
		Iterator<Row> rowit = sheet.rowIterator();
		workbook.close();
		fis.close();
		return rowit;
	}

	//retorna uma lista com os dados de um linha do excel. A linha deve ser informada no index
	//quando uma cell está vazia é adicionado NULL na lista
	//a aba/página deve ser informada com setPage, por padrão sempre inicializa na página 0
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
	//a aba/página deve ser informada com setPage, por padrão sempre inicializa na página 0
	public int getSize(String file) throws IOException {
		Iterator<Row> r = read(file);
		int counter = 0;
		while (r.hasNext()) {
			r.next();
			counter += 1;
		}
		return counter;
	}
	
	//retorna o número de abas/páginas do arquivo
	public int getPages(String file) throws IOException{
		File excelfile = new File(file);
		FileInputStream fis = new FileInputStream(excelfile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		workbook.close();
		return workbook.getNumberOfSheets();
	}
	
	//modifica a aba/página que é lida
	public void setPage(int page) {
		this.page = page;
	}
}
