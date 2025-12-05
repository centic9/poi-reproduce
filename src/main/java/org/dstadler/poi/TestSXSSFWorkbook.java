package org.dstadler.poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.poi.Version;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestSXSSFWorkbook {

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.err.println("Usage: TestXSSFWorkbook <file> [<bool-compressTemp> <bool-sharedStringTable> <windowSize> <Zip64Mode>]");
			System.exit(1);
		}

		String file = args[0];

		boolean compressTemp = args.length == 1 || Boolean.parseBoolean(args[1]);
		boolean sharedStringTable = args.length <= 2 || Boolean.parseBoolean(args[2]);
		int windowSize = args.length > 3 ? Integer.parseInt(args[3]) : 100;
		Zip64Mode zipMode = args.length > 4 ? Zip64Mode.valueOf(args[4]) : null;

		System.out.println();
		System.out.println("Writing to " + file + " with compressTemp: " + compressTemp +
				", sharedStringTable: " + sharedStringTable +
				", windowSize: " + windowSize +
				", zipMode: " + zipMode +
				" for version " + Version.getVersion() +
				" and Java " + System.getProperty("java.version"));

		try (SXSSFWorkbook wb  = new SXSSFWorkbook(new XSSFWorkbook(), windowSize, compressTemp, sharedStringTable)) {
			if (zipMode != null) {
				wb.setZip64Mode(zipMode);
			}

			wb.createSheet("test")/*.createRow(0).createCell(0).setCellValue("string")*/;

			try (OutputStream fileOutputStream = new FileOutputStream(file)) {
                wb.write(fileOutputStream);
            }
		}
	}
}
