package org.dstadler.poi.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestCloseBehavior {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        if (args.length == 0) {
            System.out.println("Usage: TestCloseBehavior <mode>");
            System.exit(1);
        }

        String mode = args[0];
        System.out.println();
        System.out.println("Running mode: " + mode);

        switch (mode) {
            case "stream":
                try (XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("test-data/spreadsheet/sample.xlsx"))) {
                    wb.getSheetAt(0);
                }
                break;
            case "file":
                try (XSSFWorkbook wb = new XSSFWorkbook(new File("test-data/spreadsheet/sample.xlsx"))) {
                    wb.getSheetAt(0);
                }
                break;
            case "path":
                try (XSSFWorkbook wb = new XSSFWorkbook("test-data/spreadsheet/sample.xlsx")) {
                    wb.getSheetAt(0);
                }
                break;
            case "opcstream":
                try (XSSFWorkbook wb = new XSSFWorkbook(OPCPackage.open(new FileInputStream("test-data/spreadsheet/sample.xlsx")))) {
                    wb.getSheetAt(0);
                }
                break;
            case "opcfile":
                try (XSSFWorkbook wb = new XSSFWorkbook(OPCPackage.open(new File("test-data/spreadsheet/sample.xlsx"), PackageAccess.READ))) {
                    wb.getSheetAt(0);
                }
                break;
            case "opcpath":
                try (XSSFWorkbook wb = new XSSFWorkbook(OPCPackage.open("test-data/spreadsheet/sample.xlsx", PackageAccess.READ))) {
                    wb.getSheetAt(0);
                }
                break;
            case "specialstream":
                try (XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("clusterfuzz-testcase-minimized-POIXWPFFuzzer-4791943399604224.docx"))) {
                    wb.getSheetAt(0);
                }
                break;
            case "specialfile":
                try (XSSFWorkbook wb = new XSSFWorkbook(new File("clusterfuzz-testcase-minimized-POIXWPFFuzzer-4791943399604224.docx"))) {
                    wb.getSheetAt(0);
                }
                break;
            case "specialpath":
                try (XSSFWorkbook wb = new XSSFWorkbook("clusterfuzz-testcase-minimized-POIXWPFFuzzer-4791943399604224.docx")) {
                    wb.getSheetAt(0);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown mode: " + mode);
        }
    }
}
