package org.dstadler.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.Version;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class TestHWPFDocument {
	public static void main(String[] args) throws IOException, InvalidFormatException {
		if (args.length == 0) {
			System.err.println("Usage: TestHWPFDocument <file>");
			System.exit(1);
		}

		String file = args[0];
		System.out.println();
		System.out.println("Running file: " + file + " for version " + Version.getVersion() + " and Java " + System.getProperty(
				"java.version"));

		try (HWPFDocument doc = new HWPFDocument(new FileInputStream(file))) {
			List<Picture> list = doc.getPicturesTable().getAllPictures();

			System.out.println(list.size() + " pictures in original document: " + list);

			File newFile = new File(file + "-" + Version.getVersion() + ".doc");
			doc.write(newFile);

			try (HWPFDocument newDoc = new HWPFDocument(new FileInputStream(newFile))) {
				List<Picture> newList = newDoc.getPicturesTable().getAllPictures();

				System.out.println(newList.size() + " pictures in resulting document: " + newList);
			}
		}
	}
}
