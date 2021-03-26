package com.fatec.mom.application;

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
	
	public ArrayList getRow(String file, int index) throws IOException {
		ArrayList lista = new ArrayList();
		File excelfile = new File(file);
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
		
		return lista;
	}
}
