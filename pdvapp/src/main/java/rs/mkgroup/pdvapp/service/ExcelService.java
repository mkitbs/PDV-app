package rs.mkgroup.pdvapp.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.DecoderException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import rs.mkgroup.pdvapp.dto.ExcelDTO;
import rs.mkgroup.pdvapp.dto.TroskoviDTO;
import rs.mkgroup.pdvapp.model.Field;
import rs.mkgroup.pdvapp.model.PoreskiIzvestajType;
import rs.mkgroup.pdvapp.model.PoreskiPodaci;
import rs.mkgroup.pdvapp.repository.FieldRepository;
import rs.mkgroup.pdvapp.repository.PoreskiPodaciRepository;

@Service
@Slf4j
public class ExcelService {

	public static final String TEMPLATE_FILE = "src/main/resources/templates/template123.xls";
	public static final String TEMPLATE_FILE_2021 = "src/main/resources/templates/template1232021.xls";
	//public static final String TEMPLATE_FILE_2022 = "src/main/resources/templates/template1232022.xls";
	public static final String TEMPLATE_FILE_2022_UPDATED = "src/main/resources/templates/pdv2022Updated.xls";
	public static final String TEMPLATE_FILE_TROSKOVI = "src/main/resources/templates/template123troskovi.xls";
	public static final String TEMPLATE_FILE_PORESKI_GUBICI = "src/main/resources/templates/poreskigubici.xls";
	public static final String TEMPLATE_FILE_PORESKI_KREDITI = "src/main/resources/templates/poreskikrediti.xls";
	public static final String TEMPLATE_FILE_KAPITALNI_PORESKI_GUBICI = "src/main/resources/templates/kapitalnigubici.xls";
	public static final String TEMPLATE_FILE_PORESKI_BILANS_2020 = "src/main/resources/templates/poreskiBilans2020.xls";
	public static final String TEMPLATE_FILE_PORESKI_GUBICI_2020 = "src/main/resources/templates/poreskiGubici2020.xls";
	public static final String TEMPLATE_FILE_PORESKI_KREDITI_2020 = "src/main/resources/templates/poreskiKrediti2020.xls";
	public static final String TEMPLATE_FILE_KAPITALNI_PORESKI_GUBICI_2020 = "src/main/resources/templates/kapitalniPoreskiGubici2020.xls";
	//public static final String TEMPLATE_FILE_PORESKI_BILANS_2021 = "src/main/resources/templates/poreskiBilans2021.xls";
	public static final String TEMPLATE_FILE_PORESKI_BILANS_2021_UPDATED = "src/main/resources/templates/PoreskiBilans2021Updated.xls";
	//public static final String TEMPLATE_FILE_PORESKI_GUBICI_2021 = "src/main/resources/templates/poreskiGubici2021.xls";
	public static final String TEMPLATE_FILE_PORESKI_GUBICI_2021_UPDATED = "src/main/resources/templates/poreskiGubici2021Updated.xls";
	//public static final String TEMPLATE_FILE_PORESKI_KREDITI_2021 = "src/main/resources/templates/poreskiKrediti2021.xls";
	public static final String TEMPLATE_FILE_PORESKI_KREDITI_2021_UPDATED = "src/main/resources/templates/poreskiKrediti2021Updated.xls";
	//public static final String TEMPLATE_FILE_KAPITALNI_PORESKI_GUBICI_2021 = "src/main/resources/templates/kapitalniPoreskiGubici2021.xls";
	public static final String TEMPLATE_FILE_KAPITALNI_PORESKI_GUBICI_2021_UPDATED = "src/main/resources/templates/kapitalniPoreskiGubici2021Updated.xls";
	
	@Autowired
	private PoreskiPodaciRepository poreskiGubiciRepository;
	
	@Autowired
	private FieldRepository fieldRepository;

	public void fillTemplateTroskovi(List<TroskoviDTO> trosks, String year)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		if(year.equals("2019")) {
			Workbook workbook = WorkbookFactory.create(new File(TEMPLATE_FILE_TROSKOVI));
			Sheet sheet = workbook.getSheetAt(0);
			/*
			 * for (Row myrow : sheet) {
			 * 
			 * for (Cell cell : myrow) { //set foreground color here printCellValue(cell);
			 * //if(cell.getRowIndex() ==9 && cell.getColumnIndex() == 4) { //
			 * cell.setCellValue("nemanja dimsic"); //} } }
			 */
			for (int x = 2; x < 28; x++) { // cells
				for (int i = 3; i <= 83; i++) { // rows
					Row ofComp = sheet.getRow(1);
					Cell ofCompCell = ofComp.getCell(x);
					String compName = "";
					Integer int1 = 1;
					if (ofCompCell.getCellType() == 1) {
						compName = ofCompCell.getStringCellValue();
					}

					for (TroskoviDTO dto : trosks) {
						if (dto.getCompanyName().trim().equals(compName.trim())) {
							// System.out.println("komp " + dto.getCompanyName());
							// vreme za upis
							for (Field f : dto.getListaFieldova()) {
								// System.out.println("upisujem " + compName + " value: " + f.getValue() + " u
								// red" + i + " u celiju " + x);
								Row rowa = sheet.getRow(i);
								Cell cl = rowa.getCell(0);
								if (cl != null) {
									if (f.getInid().equals(new Double(cl.getNumericCellValue()).longValue())) { // isti broj
																												// fielda
										Row row2write = sheet.getRow(i);
										Cell cell2write = row2write.getCell(x);
										System.out.println("upis -> " + compName);
										// NumberFormat formatter = NumberFormat.getNumberInstance();

										// System.out.println(moneyString);
										// CellStyle headerCellStyle = sheet.getWorkbook().createCellStyle();
										// headerCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));

										// cell2write.setCellStyle(headerCellStyle);
										cell2write.setCellValue(f.getValue());

									}
								}

							}
						}
					}
				}
			}
			/*
			 * for(Row r: sheet) { for(Cell c: r) {
			 * sheet.autoSizeColumn(c.getColumnIndex()); }
			 * 
			 * }
			 */

			try {
				File currDir = new File(".");
				String path = currDir.getAbsolutePath();
				String fileLocation = path.substring(0, path.length() - 1) + "IzvestajDrugo.xls";
				FileOutputStream outputStream = new FileOutputStream(fileLocation);
				workbook.write(outputStream);
				workbook.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Workbook workbook = null;
			int cells = 0;
			if (year.equals("2020")) {
				workbook = WorkbookFactory.create(new File(TEMPLATE_FILE_PORESKI_BILANS_2020));
				cells = 34;
			} else if(year.equals("2021")) {
				System.out.println("USAO U 2021");
				workbook = WorkbookFactory.create(new File(TEMPLATE_FILE_PORESKI_BILANS_2021_UPDATED));
				cells = 27;
			}
			
			Sheet sheet = workbook.getSheetAt(0);
			/*
			 * for (Row myrow : sheet) {
			 * 
			 * for (Cell cell : myrow) { //set foreground color here printCellValue(cell);
			 * //if(cell.getRowIndex() ==9 && cell.getColumnIndex() == 4) { //
			 * cell.setCellValue("nemanja dimsic"); //} } }
			 */
			for (int x = 4; x < cells; x++) { // cells
				for (int i = 11; i <= 91; i++) { // rows
					Row ofComp = sheet.getRow(8);
					Cell ofCompCell = ofComp.getCell(x);
					String compName = "";
					Integer int1 = 1;
					if (ofCompCell.getCellType() == 1) {
						compName = ofCompCell.getStringCellValue();
					}

					for (TroskoviDTO dto : trosks) {
						if (dto.getCompanyName().trim().equals(compName.trim())) {
							// System.out.println("komp " + dto.getCompanyName());
							// vreme za upis
							for (Field f : dto.getListaFieldova()) {
								// System.out.println("upisujem " + compName + " value: " + f.getValue() + " u
								// red" + i + " u celiju " + x);
								Row rowa = sheet.getRow(i);
								Cell cl = rowa.getCell(1);
								if (cl != null && !cl.getStringCellValue().isEmpty()) {
									System.out.println("CL VALUEEEE = " + cl.getStringCellValue().replace(".", ""));
									if (f.getInid().equals(Long.parseLong(cl.getStringCellValue().replace(".", "")))) { // isti broj
																												// fielda
										Row row2write = sheet.getRow(i);
										Cell cell2write = row2write.getCell(x);
										System.out.println("upis -> " + compName);
										// NumberFormat formatter = NumberFormat.getNumberInstance();

										// System.out.println(moneyString);
										// CellStyle headerCellStyle = sheet.getWorkbook().createCellStyle();
										// headerCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));

										// cell2write.setCellStyle(headerCellStyle);
										cell2write.setCellValue(f.getValue());

									}
								}

							}
						}
					}
				}
			}
			Long inid = 1L;
			
			
			// time to write sum of sums
			for (int r = 11; r <= 91; r++) {

				Row toWriteRow = sheet.getRow(r);
				

				Cell toWriteCell = toWriteRow.getCell(3);
				Cell cl = toWriteRow.getCell(1);
				if(cl != null && !cl.getStringCellValue().isEmpty()) {
					if(inid <= 69) {
						// get sum value 
						Long sumValue = 0L;
						if(year.equals("2019")) {
							sumValue = fieldRepository.getSumValue2019(year, inid).orElse(0L);
						} else if (year.equals("2020")) {
							sumValue = fieldRepository.getSumValue2020(year, inid).orElse(0L);
						} else if (year.equals("2021")) {
							sumValue = fieldRepository.getSumValue2021(year, inid).orElse(0L);
						}
						// set sum value
						toWriteCell.setCellValue(sumValue);
						//increase inid
						inid++;
					}
				}
				
				
			}

			try {
				File currDir = new File(".");
				String path = currDir.getAbsolutePath();
				String fileLocation = path.substring(0, path.length() - 1) + "IzvestajDrugo.xls";
				FileOutputStream outputStream = new FileOutputStream(fileLocation);
				workbook.write(outputStream);
				workbook.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	}

	private static void printCellValue(Cell cell) {
		switch (cell.getCellTypeEnum()) {
		case BOOLEAN:
			System.out.println(cell.getBooleanCellValue() + " BOOLEAN cindex: " + cell.getColumnIndex() + " rindex: "
					+ cell.getRow().getRowNum());
			break;
		case STRING:
			System.out.println(cell.getRichStringCellValue().getString() + "  STRING cindex: " + cell.getColumnIndex()
					+ " rindex: " + cell.getRow().getRowNum());
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				System.out.println(cell.getDateCellValue() + " NUMERIC cindex: " + cell.getColumnIndex() + " rindex: "
						+ cell.getRow().getRowNum());
			} else {
				System.out.println(cell.getNumericCellValue() + " NUMERIC cindex: " + cell.getColumnIndex()
						+ " rindex: " + cell.getRow().getRowNum());
			}
			break;
		case FORMULA:
			System.out.println(cell.getCellFormula() + " FORMULA cindex: " + cell.getColumnIndex() + " rindex: "
					+ cell.getRow().getRowNum());
			break;
		case BLANK:
			System.out.println("" + "blank");
			break;
		default:
			System.out.print("" + "default");
		}

		System.out.print("\t");
	}

	public void fillTemplatePDV(List<ExcelDTO> excels, String year)
			throws EncryptedDocumentException, InvalidFormatException, IOException, DecoderException {

		Workbook workbook;
		System.out.println(year);
		if (year.equals("2021")) {
			System.out.println("USAO U 2021");
			workbook = WorkbookFactory.create(new File(TEMPLATE_FILE_2021));
		}else if (year.equals("2022")){
			System.out.println("USAO U 2022");
			workbook = WorkbookFactory.create(new File(TEMPLATE_FILE_2022_UPDATED));
		} else {
			System.out.println("USAO U ELSE");
			workbook = WorkbookFactory.create(new File(TEMPLATE_FILE));
		}

		Sheet sheet = workbook.getSheetAt(0);

		Row rowYear = sheet.getRow(2);
		Cell cl = rowYear.getCell(1);
		 cl.setCellValue(year + ".godina");

		 if(year.equals("2021")) {
			
			for (int j = 10; j <= 29 * 4; j = j + 4) {
				Row row2write = sheet.getRow(j);
				Cell cellComp = row2write.getCell(1);
				String compName = cellComp.getStringCellValue();

				for (int i = 0; i < excels.size(); i++) {
					ExcelDTO ex = excels.get(i);
					System.out.println("COMPARING123" + ex.getCompanyName().trim() + " = " + compName.trim());
					if (ex.getCompanyName().toLowerCase().trim().equals(compName.toLowerCase().trim())) {

						Iterator it = ex.getPdv1().entrySet().iterator();
						while (it.hasNext()) {
							Map.Entry pair = (Map.Entry) it.next();
							writeToMonthCell(pair, row2write, false, null);
						}
					}
				}
			}

			for (int j = 11; j <= 29 * 4; j = j + 4) {
				Row row2write = sheet.getRow(j);
				Row row2read = sheet.getRow(j - 1);
				Cell cellComp = row2read.getCell(1);
				String compName = cellComp.getStringCellValue();

				for (int i = 0; i < excels.size(); i++) {
					ExcelDTO ex = excels.get(i);

					if (ex.getCompanyName().toLowerCase().trim().equals(compName.toLowerCase().trim())) {

						Iterator it = ex.getPdv2().entrySet().iterator();
						while (it.hasNext()) {
							Map.Entry pair = (Map.Entry) it.next();

							writeToMonthCell(pair, row2write, false, null);
						}
					}
				}
			}

			for (int j = 12; j <= 29 * 4; j = j + 4) {
				Row row2write = sheet.getRow(j);
				Row row2read = sheet.getRow(j - 2);
				Cell cellComp = row2read.getCell(1);
				String compName = cellComp.getStringCellValue();

				for (int i = 0; i < excels.size(); i++) {
					ExcelDTO ex = excels.get(i);

					if (ex.getCompanyName().toLowerCase().trim().equals(compName.toLowerCase().trim())) {

						Iterator it = ex.getListSumValues().entrySet().iterator();
						while (it.hasNext()) {
							Map.Entry pair = (Map.Entry) it.next();
							Font headerFont = workbook.createFont();
							headerFont.setColor(IndexedColors.WHITE.index);
							CellStyle headerCellStyle = sheet.getWorkbook().createCellStyle();
							// fill foreground color ...
							headerCellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.index);
							// and solid fill pattern produces solid grey cell fill
							headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
							headerCellStyle.setFont(headerFont);
							headerCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));

							writeToMonthCell(pair, row2write, true, headerCellStyle);

						}
					}
				}
			}
		} else if (year.equals("2022")) {
			for (int j = 11; j <= 101; j = j + 4) {
				Row row2write = sheet.getRow(j);
				Cell cellComp = row2write.getCell(1);
				String compName = cellComp.getStringCellValue();

				for (int i = 0; i < excels.size(); i++) {
					ExcelDTO ex = excels.get(i);
					System.out.println("COMPARING123" + ex.getCompanyName().trim() + " = " + compName.trim());
					if (ex.getCompanyName().toLowerCase().trim().equals(compName.toLowerCase().trim())) {

						Iterator it = ex.getPdv1().entrySet().iterator();
						while (it.hasNext()) {
							Map.Entry pair = (Map.Entry) it.next();
							writeToMonthCell(pair, row2write, false, null);
						}
					}
				}
			}

			for (int j = 12; j <= 101; j = j + 4) {
				Row row2write = sheet.getRow(j);
				Row row2read = sheet.getRow(j - 1);
				Cell cellComp = row2read.getCell(1);
				String compName = cellComp.getStringCellValue();

				for (int i = 0; i < excels.size(); i++) {
					ExcelDTO ex = excels.get(i);

					if (ex.getCompanyName().toLowerCase().trim().equals(compName.toLowerCase().trim())) {

						Iterator it = ex.getPdv2().entrySet().iterator();
						while (it.hasNext()) {
							Map.Entry pair = (Map.Entry) it.next();

							writeToMonthCell(pair, row2write, false, null);
						}
					}
				}
			}

			for (int j = 13; j <= 101; j = j + 4) {
				Row row2write = sheet.getRow(j);
				Row row2read = sheet.getRow(j - 2);
				Cell cellComp = row2read.getCell(1);
				String compName = cellComp.getStringCellValue();

				for (int i = 0; i < excels.size(); i++) {
					ExcelDTO ex = excels.get(i);

					if (ex.getCompanyName().toLowerCase().trim().equals(compName.toLowerCase().trim())) {

						Iterator it = ex.getListSumValues().entrySet().iterator();
						while (it.hasNext()) {
							Map.Entry pair = (Map.Entry) it.next();
							//Font headerFont = workbook.createFont();
							//headerFont.setColor(IndexedColors.WHITE.index);
							CellStyle headerCellStyle = sheet.getWorkbook().createCellStyle();
							// fill foreground color ...
							//headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
							// and solid fill pattern produces solid grey cell fill
							//headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
							//headerCellStyle.setFont(headerFont);
							headerCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));

							writeToMonthCell(pair, row2write, false, headerCellStyle);

						}
					}
				}
			}
		} else {
			
			for (int j = 10; j <= 31 * 4; j = j + 4) {
				Row row2write = sheet.getRow(j);
				Cell cellComp = row2write.getCell(1);
				String compName = cellComp.getStringCellValue();

				for (int i = 0; i < excels.size(); i++) {
					ExcelDTO ex = excels.get(i);
					System.out.println("COMPARING123" + ex.getCompanyName().trim() + " = " + compName.trim());
					if (ex.getCompanyName().toLowerCase().trim().equals(compName.toLowerCase().trim())) {

						Iterator it = ex.getPdv1().entrySet().iterator();
						while (it.hasNext()) {
							Map.Entry pair = (Map.Entry) it.next();
							writeToMonthCell(pair, row2write, false, null);
						}
					}
				}
			}

			for (int j = 11; j <= 31 * 4; j = j + 4) {
				Row row2write = sheet.getRow(j);
				Row row2read = sheet.getRow(j - 1);
				Cell cellComp = row2read.getCell(1);
				String compName = cellComp.getStringCellValue();

				for (int i = 0; i < excels.size(); i++) {
					ExcelDTO ex = excels.get(i);

					if (ex.getCompanyName().toLowerCase().trim().equals(compName.toLowerCase().trim())) {

						Iterator it = ex.getPdv2().entrySet().iterator();
						while (it.hasNext()) {
							Map.Entry pair = (Map.Entry) it.next();

							writeToMonthCell(pair, row2write, false, null);
						}
					}
				}
			}

			for (int j = 12; j <= 31 * 4; j = j + 4) {
				Row row2write = sheet.getRow(j);
				Row row2read = sheet.getRow(j - 2);
				Cell cellComp = row2read.getCell(1);
				String compName = cellComp.getStringCellValue();

				for (int i = 0; i < excels.size(); i++) {
					ExcelDTO ex = excels.get(i);

					if (ex.getCompanyName().toLowerCase().trim().equals(compName.toLowerCase().trim())) {

						Iterator it = ex.getListSumValues().entrySet().iterator();
						while (it.hasNext()) {
							Map.Entry pair = (Map.Entry) it.next();
							Font headerFont = workbook.createFont();
							headerFont.setColor(IndexedColors.WHITE.index);
							CellStyle headerCellStyle = sheet.getWorkbook().createCellStyle();
							// fill foreground color ...
							headerCellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.index);
							// and solid fill pattern produces solid grey cell fill
							headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
							headerCellStyle.setFont(headerFont);
							headerCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));

							writeToMonthCell(pair, row2write, true, headerCellStyle);

						}
					}
				}
			}
		}

		/*
		 * for(Row r: sheet) { for(Cell c: r) {
		 * sheet.autoSizeColumn(c.getColumnIndex()); }
		 * 
		 * }
		 */

		try {
			File currDir = new File(".");
			String path = currDir.getAbsolutePath();
			String fileLocation = path.substring(0, path.length() - 1) + "Izvestaj.xls";
			FileOutputStream outputStream = new FileOutputStream(fileLocation);
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void writeToMonthCell(Map.Entry pair, Row row2write, boolean color, CellStyle style) {
		if (pair.getKey().equals("January")) {
			Cell cell4 = row2write.getCell(4);
			if (color) {

				cell4.setCellStyle(style);
				System.out.println(cell4.getCellStyle().getFillBackgroundColor());
			}
			// NumberFormat formatter = NumberFormat.getCurrencyInstance();
			// String moneyString = formatter.format(pair.getValue());
			// moneyString = moneyString.replace(",00", "");
			// moneyString = moneyString.replace("RSD", "");
			// System.out.println(moneyString);
			cell4.setCellValue((Long) pair.getValue());

		}
		if (pair.getKey().equals("February")) {
			Cell cell4 = row2write.getCell(5);
			if (color) {

				cell4.setCellStyle(style);
				System.out.println(cell4.getCellStyle().getFillBackgroundColor());
			}
			/*
			 * NumberFormat formatter = NumberFormat.getCurrencyInstance(); String
			 * moneyString = formatter.format(pair.getValue()); moneyString =
			 * moneyString.replace(",00", ""); moneyString = moneyString.replace("RSD", "");
			 * System.out.println(moneyString); cell4.setCellValue(moneyString);
			 */
			cell4.setCellValue((Long) pair.getValue());

		}
		if (pair.getKey().equals("March")) {
			Cell cell4 = row2write.getCell(6);
			if (color) {

				cell4.setCellStyle(style);
				System.out.println(cell4.getCellStyle().getFillBackgroundColor());
			}
			/*
			 * NumberFormat formatter = NumberFormat.getCurrencyInstance(); String
			 * moneyString = formatter.format(pair.getValue()); moneyString =
			 * moneyString.replace(",00", ""); moneyString = moneyString.replace("RSD", "");
			 * System.out.println(moneyString);
			 */
			cell4.setCellValue((Long) pair.getValue());

		}
		if (pair.getKey().equals("April")) {
			Cell cell4 = row2write.getCell(7);
			if (color) {

				cell4.setCellStyle(style);
				System.out.println(cell4.getCellStyle().getFillBackgroundColor());
			}
			/*
			 * NumberFormat formatter = NumberFormat.getCurrencyInstance(); String
			 * moneyString = formatter.format(pair.getValue()); moneyString =
			 * moneyString.replace(",00", ""); moneyString = moneyString.replace("RSD", "");
			 * System.out.println(moneyString);
			 */
			cell4.setCellValue((Long) pair.getValue());

		}
		if (pair.getKey().equals("May")) {
			Cell cell4 = row2write.getCell(8);
			if (color) {

				cell4.setCellStyle(style);
				System.out.println(cell4.getCellStyle().getFillBackgroundColor());
			}
			/*
			 * NumberFormat formatter = NumberFormat.getCurrencyInstance(); String
			 * moneyString = formatter.format(pair.getValue()); moneyString =
			 * moneyString.replace(",00", ""); moneyString = moneyString.replace("RSD", "");
			 * System.out.println(moneyString);
			 * 
			 * cell4.setCellValue(moneyString);
			 */
			cell4.setCellValue((Long) pair.getValue());

		}
		if (pair.getKey().equals("June")) {
			Cell cell4 = row2write.getCell(9);
			if (color) {

				cell4.setCellStyle(style);
				System.out.println(cell4.getCellStyle().getFillBackgroundColor());
			}
			/*
			 * NumberFormat formatter = NumberFormat.getCurrencyInstance(); String
			 * moneyString = formatter.format(pair.getValue()); moneyString =
			 * moneyString.replace(",00", ""); moneyString = moneyString.replace("RSD", "");
			 * System.out.println(moneyString); cell4.setCellValue(moneyString);
			 */
			cell4.setCellValue((Long) pair.getValue());

		}
		if (pair.getKey().equals("July")) {
			Cell cell4 = row2write.getCell(10);
			if (color) {

				cell4.setCellStyle(style);
				System.out.println(cell4.getCellStyle().getFillBackgroundColor());
			}
			/*
			 * NumberFormat formatter = NumberFormat.getCurrencyInstance(); String
			 * moneyString = formatter.format(pair.getValue()); moneyString =
			 * moneyString.replace(",00", ""); moneyString = moneyString.replace("RSD", "");
			 * System.out.println(moneyString); cell4.setCellValue(moneyString);
			 */
			cell4.setCellValue((Long) pair.getValue());

		}
		if (pair.getKey().equals("August")) {
			Cell cell4 = row2write.getCell(11);
			if (color) {

				cell4.setCellStyle(style);
				System.out.println(cell4.getCellStyle().getFillBackgroundColor());
			}
			/*
			 * NumberFormat formatter = NumberFormat.getCurrencyInstance(); String
			 * moneyString = formatter.format(pair.getValue()); moneyString =
			 * moneyString.replace(",00", ""); moneyString = moneyString.replace("RSD", "");
			 * System.out.println(moneyString); cell4.setCellValue(moneyString);
			 */
			cell4.setCellValue((Long) pair.getValue());

		}
		if (pair.getKey().equals("September")) {
			Cell cell4 = row2write.getCell(12);
			if (color) {

				cell4.setCellStyle(style);
				System.out.println(cell4.getCellStyle().getFillBackgroundColor());
			}
			/*
			 * NumberFormat formatter = NumberFormat.getCurrencyInstance(); String
			 * moneyString = formatter.format(pair.getValue()); moneyString =
			 * moneyString.replace(",00", ""); moneyString = moneyString.replace("RSD", "");
			 * System.out.println(moneyString);
			 */

			cell4.setCellValue((Long) pair.getValue());

		}
		if (pair.getKey().equals("October")) {
			Cell cell4 = row2write.getCell(13);
			if (color) {

				cell4.setCellStyle(style);
				System.out.println(cell4.getCellStyle().getFillBackgroundColor());
			}
			/*
			 * NumberFormat formatter = NumberFormat.getCurrencyInstance(); String
			 * moneyString = formatter.format(pair.getValue()); moneyString =
			 * moneyString.replace(",00", ""); moneyString = moneyString.replace("RSD", "");
			 * System.out.println(moneyString); cell4.setCellValue(moneyString);
			 */
			cell4.setCellValue((Long) pair.getValue());

		}
		if (pair.getKey().equals("November")) {
			Cell cell4 = row2write.getCell(14);
			if (color) {

				cell4.setCellStyle(style);
				System.out.println(cell4.getCellStyle().getFillBackgroundColor());
			}
			/*
			 * NumberFormat formatter = NumberFormat.getCurrencyInstance(); String
			 * moneyString = formatter.format(pair.getValue()); moneyString =
			 * moneyString.replace(",00", ""); moneyString = moneyString.replace("RSD", "");
			 * System.out.println(moneyString); cell4.setCellValue(moneyString);
			 */
			cell4.setCellValue((Long) pair.getValue());

		}
		if (pair.getKey().equals("December")) {
			Cell cell4 = row2write.getCell(15);
			if (color) {

				cell4.setCellStyle(style);
				System.out.println(cell4.getCellStyle().getFillBackgroundColor());
			}
			/*
			 * NumberFormat formatter = NumberFormat.getCurrencyInstance(); String
			 * moneyString = formatter.format(pair.getValue()); moneyString =
			 * moneyString.replace(",00", ""); moneyString = moneyString.replace("RSD", "");
			 * System.out.println(moneyString); cell4.setCellValue(moneyString);
			 */
			cell4.setCellValue((Long) pair.getValue());

		}
	}

	public void fillPoreskiGubiciExcel(PoreskiIzvestajType type, String year)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		Workbook workbook = null;
		Sheet sheet = null;
		int cells = 0;
		int rows = 0;
		if(year.equals("2019")) {
			cells = 32;
			rows = 0;
			if (type.equals(PoreskiIzvestajType.PORESKI_GUBICI)) {
				rows = 10;
				workbook = WorkbookFactory.create(new File(TEMPLATE_FILE_PORESKI_GUBICI));
			} else if (type.equals(PoreskiIzvestajType.PORESKI_KREDITI)) {
				rows = 9;
				workbook = WorkbookFactory.create(new File(TEMPLATE_FILE_PORESKI_KREDITI));
			} else {
				rows = 10;
				workbook = WorkbookFactory.create(new File(TEMPLATE_FILE_KAPITALNI_PORESKI_GUBICI));
			}

			sheet = workbook.getSheetAt(0);
			
			List<PoreskiPodaci> valuesToFill = poreskiGubiciRepository.findByType(type.ordinal());
			//List<PoreskiPodaci> valuesToFill = poreskiGubiciRepository.findAll();

			for (int x = 1; x < cells; x++) { // cells
				for (int i = 0; i <= rows; i++) { // rows
					Row ofComp = sheet.getRow(3);
					System.out.println("CELL = " + x);
					System.out.println("ROW = " + i);
					
					Cell ofCompCell = ofComp.getCell(x);
					String compName = "";
					if (ofCompCell.getCellType() == 1) {
						compName = ofCompCell.getStringCellValue();
					}
					String yearsPeriod = "";

					for (PoreskiPodaci dto : valuesToFill) {
						if (dto.getId().getCompanyName().trim().equals(compName.trim())) {
							if (sheet.getRow(i).getCell(1).getCellType() == 0) {
								Double yearsDouble = sheet.getRow(i).getCell(1).getNumericCellValue();
								yearsPeriod = yearsDouble.toString().split("\\.")[0];
							}

							// time to write into the cell
							if (dto.getId().getYearsPeriod().split("-")[0].equals(yearsPeriod)) {
								log.info("Upis: " + dto.getId().getYearsPeriod() + " = " + yearsPeriod);
								Row row2write = sheet.getRow(i);
								Cell cell2write = row2write.getCell(x);
								System.out.println("VALUEEEE " + dto.getValue());
								cell2write.setCellValue(dto.getValue());

							}

							// time to write to the sum row cells
							if (i == rows) {
								Row row2write = sheet.getRow(i);
								Cell cell2write = row2write.getCell(x);
								// get sum value for a company
								System.out.println("TYPE " + type.ordinal());
								System.out.println("COMPANY NAME " + compName.trim());
								Long sumValue = poreskiGubiciRepository.getSumValueForCompany2019(type.ordinal(),
										compName.trim());
								if (sumValue == null) {
									cell2write.setCellValue(0);
								} else {
									cell2write.setCellValue(sumValue);
								}

							}
						}

					}

				}
			}

			List<Long> listOfSums = new ArrayList<Long>();
			// time to write sum of sums
			for (int r = 4; r <= rows; r++) {

				Row toWriteRow = sheet.getRow(r);

				Cell toWriteCell = toWriteRow.getCell(3);

				Cell cellOfYear = toWriteRow.getCell(2);

				// get sum value for a year period
				
				Long sumValue = poreskiGubiciRepository
						.getSumValueForType2019(type.ordinal(), ((Double) cellOfYear.getNumericCellValue()).toString().split("\\.")[0]).orElse(0L);
				if (r != rows) {
					if(!((Double) cellOfYear.getNumericCellValue()).toString().split("\\.")[0].equals("2019")) {
						listOfSums.add(sumValue);
					}
					toWriteCell.setCellValue(sumValue);
					
				} else {
					Long sums = 0L;
					for (Long s : listOfSums) {
						sums += s;
					}
					toWriteCell.setCellValue(sums);
				}

			}

		} else {
			cells = 0;
			rows = 0;
			if (type.equals(PoreskiIzvestajType.PORESKI_GUBICI)) {
				rows = 12;
				if(year.equals("2020")) {
					cells = 34;
					workbook = WorkbookFactory.create(new File(TEMPLATE_FILE_PORESKI_GUBICI_2020));
				} else if(year.equals("2021")) {
					cells = 27;
					workbook = WorkbookFactory.create(new File(TEMPLATE_FILE_PORESKI_GUBICI_2021_UPDATED));
				}
				
			} else if (type.equals(PoreskiIzvestajType.PORESKI_KREDITI)) {
				
				if(year.equals("2020")) {
					rows = 10;
					cells = 34;
					workbook = WorkbookFactory.create(new File(TEMPLATE_FILE_PORESKI_KREDITI_2020));
				} else if(year.equals("2021")) {
					rows = 9;
					cells = 27;
					workbook = WorkbookFactory.create(new File(TEMPLATE_FILE_PORESKI_KREDITI_2021_UPDATED));
				}
				
			} else {
				
				rows = 12;
				if(year.equals("2020")) {
					cells = 34;
					workbook = WorkbookFactory.create(new File(TEMPLATE_FILE_KAPITALNI_PORESKI_GUBICI_2020));
				} else if (year.equals("2021")) {
					cells = 27;
					workbook = WorkbookFactory.create(new File(TEMPLATE_FILE_KAPITALNI_PORESKI_GUBICI_2021_UPDATED));
				}
				
			}

			sheet = workbook.getSheetAt(0);
			List<PoreskiPodaci> valuesToFill = poreskiGubiciRepository.findByType(type.ordinal());
			for (int x = 1; x < cells; x++) { // cells
				for (int i = 0; i <= rows; i++) { // rows
					Row ofComp = sheet.getRow(4);
					System.out.println("CELL = " + x);
					System.out.println("ROW = " + i);
					
					Cell ofCompCell = ofComp.getCell(x);
					String compName = "";
					if (ofCompCell.getCellType() == 1) {
						compName = ofCompCell.getStringCellValue();
					}
					String yearsPeriod = "";

					for (PoreskiPodaci dto : valuesToFill) {
						if (dto.getId().getCompanyName().trim().equals(compName.trim())) {
							if(i != rows && i != rows - 1) {
								System.out.println(compName);
								System.out.println(sheet.getRow(2));
								System.out.println(sheet.getRow(i));
								if(sheet.getRow(i) != null && sheet.getRow(i).getCell(1) != null) {
									if (sheet.getRow(i).getCell(1).getCellType() == 0) {
										Double yearsDouble = sheet.getRow(i).getCell(1).getNumericCellValue();
										yearsPeriod = yearsDouble.toString().split("\\.")[0];
									}

									// time to write into the cell
									if (dto.getId().getYearsPeriod().split("-")[0].equals(yearsPeriod)) {
										log.info("Upis: " + dto.getId().getYearsPeriod() + " = " + yearsPeriod);
										Row row2write = sheet.getRow(i);
										Cell cell2write = row2write.getCell(x);
										System.out.println("VALUEEEE " + dto.getValue());
										cell2write.setCellValue(dto.getValue());
										System.out.println("UPISAO i == else");
									}
								}
							}
							// time to write into cell "Ukupno na dan 31.12.2020. (sa 2015./bez 2020.)
							if (i == rows - 1) {
								Row row2write = sheet.getRow(i);
								Cell cell2write = row2write.getCell(x);
								// get sum value for a company
								System.out.println("TYPE " + type.ordinal());
								System.out.println("COMPANY NAME " + compName.trim());
								Long sumValue = 0L;
								if(type.equals(PoreskiIzvestajType.PORESKI_GUBICI) || type.equals(PoreskiIzvestajType.KAPITALNI_PORESKI_GUBICI)) {
									if(year.equals("2020")) {
										sumValue = poreskiGubiciRepository.getSumValuePerCompanyForLastDayOn2020PG(
												type.ordinal(), compName.trim()).orElse(0L); // "Ukupno na dan 31.12.2020. (sa 2015./bez 2020.)
									} else if (year.equals("2021")) {
										sumValue = poreskiGubiciRepository.getSumValuePerCompanyForLastDayOn2021PG(
												type.ordinal(), compName.trim()).orElse(0L); // "Ukupno na dan 31.12.2021. (sa 2016./bez 2021.)
									}
									
								} else if (type.equals(PoreskiIzvestajType.PORESKI_KREDITI)) {
									if(year.equals("2020")) {
										sumValue = poreskiGubiciRepository.getSumValuePerCompanyForLastDayOn2020PK(
												type.ordinal(), compName.trim()).orElse(0L);
									} else if (year.equals("2021")) {
										sumValue = poreskiGubiciRepository.getSumValuePerCompanyForLastDayOn2021PK(
												type.ordinal(), compName.trim()).orElse(0L);
									}
									
								}
								
								if (sumValue == null) {
									cell2write.setCellValue(0);
								} else {
									cell2write.setCellValue(sumValue);
								}
								
							}
								
							
							// time to write into cell "Ukupno za koriscenje od 2021. god (bez 2015./sa 2020.)
							if (i == rows) {
								Row row2write = sheet.getRow(i);
								Cell cell2write = row2write.getCell(x);
								// get sum value for a company
								System.out.println("TYPE " + type.ordinal());
								System.out.println("COMPANY NAME " + compName.trim());
								Long sumValue = 0L;
								if(year.equals("2021")) {
									sumValue = poreskiGubiciRepository.getSumValueToUsePerCompany2021(type.ordinal(),
											compName.trim()).orElse(0L);
								} else {
									sumValue = poreskiGubiciRepository.getSumValueToUsePerCompany(type.ordinal(),
											compName.trim()).orElse(0L);
								}
								
								if (sumValue == null) {
									cell2write.setCellValue(0);
								} else {
									cell2write.setCellValue(sumValue);
								}	
							}
							
						}

					}

				}
			}
			Long sumValueForLastDayOn2020 = 0L;
			Long sumValueToUse = 0L;
			// time to write sum of sums
			for (int r = 5; r <= rows - 2; r++) {

				Row toWriteRow = sheet.getRow(r);

				Cell toWriteCell = toWriteRow.getCell(3);

				Cell cellOfYear = toWriteRow.getCell(2);

				// get sum value for a year period
				Long sumValue = 0L;
				if(year.equals("2020")) {
					sumValue = poreskiGubiciRepository
							.getSumValueForType2020(type.ordinal(), ((Double) cellOfYear.getNumericCellValue()).toString().split("\\.")[0]).orElse(0L);
				} else if (year.equals("2021")) {
					sumValue = poreskiGubiciRepository
							.getSumValueForType2021(type.ordinal(), ((Double) cellOfYear.getNumericCellValue()).toString().split("\\.")[0]).orElse(0L);
				}
				
				toWriteCell.setCellValue(sumValue);
				if (type.equals(PoreskiIzvestajType.PORESKI_GUBICI) || type.equals(PoreskiIzvestajType.KAPITALNI_PORESKI_GUBICI)) {
					if(r >= 5 && r <= 9) {
						sumValueForLastDayOn2020 += sumValue;
					}
					if(r >=6 && r <= 10) {
						sumValueToUse += sumValue;
					}
				} else if (type.equals(PoreskiIzvestajType.PORESKI_KREDITI)) {
					if(year.equals("2021")) {
						if(r >= 5 && r <= 7) {
							sumValueForLastDayOn2020 += sumValue;
						}
						if(r >=6 && r <= 7) {
							sumValueToUse += sumValue;
						}
					} else {
						if(r >= 5 && r <= 8) {
							sumValueForLastDayOn2020 += sumValue;
						}
						if(r >=6 && r <= 8) {
							sumValueToUse += sumValue;
						}
					}
				
				}
				

			}
			
			if(type.equals(PoreskiIzvestajType.PORESKI_GUBICI) || type.equals(PoreskiIzvestajType.KAPITALNI_PORESKI_GUBICI)) {
				// time to write into cell "Ukupno na dan 31.12.2020. (sa 2015./bez 2020.)"
				Row toWriteRow = sheet.getRow(11);
				Cell toWriteCell = toWriteRow.getCell(3);
				toWriteCell.setCellValue(sumValueForLastDayOn2020);
				
				// time to write into cell "Ukupno za koriscenje od 2021. god (bez 2015./sa 2020.)"
				Row toWriteRow2 = sheet.getRow(12);
				Cell toWriteCell2 = toWriteRow2.getCell(3);
				toWriteCell2.setCellValue(sumValueToUse);
				
			} else if (type.equals(PoreskiIzvestajType.PORESKI_KREDITI)) {
				// time to write into cell "Ukupno na dan 31.12.2020."
				if(year.equals("2021")) {
					Row toWriteRow = sheet.getRow(8);
					Cell toWriteCell = toWriteRow.getCell(3);
					toWriteCell.setCellValue(sumValueForLastDayOn2020);
					
					// time to write into cell "Ukupno za koriscenje od 2021. god (bez 2010./sa 2020.)"
					Row toWriteRow2 = sheet.getRow(9);
					Cell toWriteCell2 = toWriteRow2.getCell(3);
					toWriteCell2.setCellValue(sumValueToUse);
				} else {
					Row toWriteRow = sheet.getRow(9);
					Cell toWriteCell = toWriteRow.getCell(3);
					toWriteCell.setCellValue(sumValueForLastDayOn2020);
					
					// time to write into cell "Ukupno za koriscenje od 2021. god (bez 2010./sa 2020.)"
					Row toWriteRow2 = sheet.getRow(10);
					Cell toWriteCell2 = toWriteRow2.getCell(3);
					toWriteCell2.setCellValue(sumValueToUse);
				}
				
				
				
			}
			
		}
		
		try {
			File currDir = new File(".");
			String path = currDir.getAbsolutePath();
			String fileLocation = path.substring(0, path.length() - 1) + "IzvestajPoreskiGubici.xls";
			FileOutputStream outputStream = new FileOutputStream(fileLocation);
			workbook.write(outputStream);
			clearTheTemplate(cells, rows, sheet);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Async
	public void clearTheTemplate(int cells, int rows, Sheet sheet) {
		// clear template
		for (int x = 3; x < cells; x++) { // cells
			for (int i = 4; i <= rows; i++) { // rows
				Row ofComp = sheet.getRow(i);

				Cell ofCompCell = ofComp.getCell(x);
				if (ofCompCell != null) {
					ofCompCell.setCellValue("");
				}

			}
		}

	}

}
