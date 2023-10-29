package org.dstadler.poi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ZipTest {

	@Disabled("This did not work in 5.2.4, should work in 5.2.5 again")
	@Test
	public void test() throws IOException {
		InputStream fileInputStream = new FileInputStream("src/main/resources/multiple.zip");

		ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);

		ZipEntry zipEntry1 = zipInputStream.getNextEntry();
		assertNotNull(zipEntry1);
		assertEquals("WithChart.xlsx", zipEntry1.getName());

		Workbook workbook1 = new XSSFWorkbook(zipInputStream);
		assertEquals("Sheet1", workbook1.getSheetAt(0).getSheetName());

		// poi-ooxml:5.2.2 throws java.io.IOException: Stream closed
		ZipEntry zipEntry2 = zipInputStream.getNextEntry();
		assertEquals("WithTable.xlsx", zipEntry2.getName());

		Workbook workbook2 = new XSSFWorkbook(zipInputStream);
		assertEquals("Foglio1", workbook2.getSheetAt(0).getSheetName());

		assertNull(zipInputStream.getNextEntry());
	}
}
