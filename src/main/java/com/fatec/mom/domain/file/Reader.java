package com.fatec.mom.domain.file;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Reader {

	private final static String DEFAULT_PATH = "/com/fatec/mom/test/mockup/";

	public void printFile(String file) throws IOException {
		File excelfile = new File(file);
		FileInputStream fis = new FileInputStream(excelfile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowit = sheet.rowIterator();
		
		while(rowit.hasNext()) {
			Row row = rowit.next();
			Iterator<Cell> cellit = row.cellIterator();
			while(cellit.hasNext()) {
				Cell cell = cellit.next();
				System.out.println(cell.toString());
			}
			System.out.println();
		}
		workbook.close();
		fis.close();
	}
	
	public ArrayList getRow(String file, int index) {
		ArrayList lista = new ArrayList();
		try {
			File excelfile = new File(String.valueOf(String.format("D:/Documents/facul/PI III/API/API_3sem_2021-01/doc/Mockup FATEC/%s", file)));
			FileInputStream fis = new FileInputStream(excelfile);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowit = sheet.rowIterator();
			int counter = 0;
			while(rowit.hasNext()) {
				Row row = rowit.next();
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
			workbook.close();
			fis.close();
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}

		return lista;
	}
}
