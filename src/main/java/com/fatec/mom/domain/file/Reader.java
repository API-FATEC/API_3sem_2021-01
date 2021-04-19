package com.fatec.mom.domain.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Reader {

	@Value("${default-upload-path}")
	private String defaultPath;
	private int page = 0;

	//metodo criado para ser usado nos outros metodos, reduzindo o código
	public Iterator<Row> read(String file) {
		File excelfile = new File(String.format("%s/%s", defaultPath, file));
		Iterator<Row> rowit = null;
		try (FileInputStream fis = new FileInputStream(excelfile)) {
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(page);
			workbook.close();
			rowit = sheet.rowIterator();
		} catch (IOException e) {
			log.error("Não é possível ler o arquivo.", e);
		}
		return rowit;
	}

	//retorna uma lista com os dados de um linha do excel. A linha deve ser informada no index
	//quando uma cell está vazia é adicionado NULL na lista
	//a aba/página deve ser informada com setPage, por padrão sempre inicializa na página 0
	public List<String> getRow(String file, int index) {
		ArrayList<String> lista = new ArrayList<>();
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
	public int getSize(String file) {
		Iterator<Row> r = read(file);
		int counter = 0;
		while (r.hasNext()) {
			r.next();
			counter += 1;
		}
		return counter;
	}
}
