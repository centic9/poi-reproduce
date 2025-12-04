package org.dstadler.poi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.apache.poi.Version;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestSqrtRegression {

    public static void main(String[] args) throws IOException {
        System.out.println();
        System.out.println("Running for version " + Version.getVersion() + " and Java " + System.getProperty("java.version"));

        {
            double rootOfEvil = Math.sqrt(666.0);
            double evil = rootOfEvil * rootOfEvil;
            System.out.println("RootOfEvil = " + rootOfEvil);
            System.out.println("Evil       = " + evil);

            assertEquals(666.0, evil, 0.0);
            assertEquals(Math.sqrt(evil), rootOfEvil);
        }

        try (Workbook wb = new XSSFWorkbook()) {
            Sheet sheet = wb.createSheet("Test");

            // Cell B1 = SQRT(666.0)
            Row row0 = sheet.createRow(0);
            Cell rootOfEvil = row0.createCell(1);
            rootOfEvil.setCellFormula("SQRT(666.0)");

            // Cell B2 = B1 * B1
            Row row1 = sheet.createRow(1);
            Cell evil = row1.createCell(1);
            evil.setCellFormula("B1*B1");

            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

            // evaluate
            evaluator.evaluateFormulaCell(rootOfEvil);
            evaluator.evaluateFormulaCell(evil);

            double rootOfEvilValue = rootOfEvil.getNumericCellValue();
            double evilValue = evil.getNumericCellValue(); // e.g. rootOfEvil * rootOfEvil

            System.out.println("RootOfEvil = " + rootOfEvilValue);
            System.out.println("Evil       = " + evilValue);

            // it's not a rounding problem with sqrt(evil)
            assertEquals(Math.sqrt(evilValue), rootOfEvilValue);

            // That's the assertion that fails with POI version 5.5.0
            assertEquals(666.0, evilValue, 0.0);

            // Fails also with tolerance on POI version 5.5.0
            assertEquals(666.0, evilValue, 1e-12);

        }
    }

}