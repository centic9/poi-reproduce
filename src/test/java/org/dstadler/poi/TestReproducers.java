package org.dstadler.poi;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestReproducers {
	private static File save;

	@BeforeAll
	static void copyFiles() throws IOException {
		save = File.createTempFile("TestSXSSFWorkbook", ".xlsx");
		FileUtils.copyFile(new File("src/main/resources/sample.xlsx"), save);
	}

	@AfterAll
	static void restoreFiles() throws IOException {
		FileUtils.copyFile(save, new File("src/main/resources/sample.xlsx"));
		assertTrue(save.delete());
	}

	@Test
	void testCloseBehavior() throws IOException, InvalidFormatException {
		TestCloseBehavior.main(new String[] {"stream"});
		TestCloseBehavior.main(new String[] {"file"});
		TestCloseBehavior.main(new String[] {"path"});
		TestCloseBehavior.main(new String[] {"opcstream"});
		TestCloseBehavior.main(new String[] {"opcfile"});
		TestCloseBehavior.main(new String[] {"opcpath"});

		assertThrows(POIXMLException.class,
				() -> TestCloseBehavior.main(new String[] {"specialstream"}));
		assertThrows(POIXMLException.class,
				() -> TestCloseBehavior.main(new String[] {"specialfile"}));
		assertThrows(POIXMLException.class,
				() -> TestCloseBehavior.main(new String[] {"specialpath"}));
	}

	@Test
	void testFiles() {
		TestFiles.main(null);
	}

	@Test
	void testSXSSFWorkbook() throws IOException {
		File temp = File.createTempFile("TestSXSSFWorkbook", ".xlsx");
		try {
			// <file> [<bool-compressTemp> <bool-sharedStringTable> <windowSize> <Zip64Mode>]
			TestSXSSFWorkbook.main(new String[] { temp.getAbsolutePath() });
			TestSXSSFWorkbook.main(new String[] { temp.getAbsolutePath(), "true" });
			TestSXSSFWorkbook.main(new String[] { temp.getAbsolutePath(), "false" });
			TestSXSSFWorkbook.main(new String[] { temp.getAbsolutePath(), "false", "true" });
			TestSXSSFWorkbook.main(new String[] { temp.getAbsolutePath(), "false", "true", "50" });
			TestSXSSFWorkbook.main(
					new String[] { temp.getAbsolutePath(), "false", "false", "200", "AlwaysWithCompatibility" });
		} finally {
			assertTrue( !temp.exists() || temp.delete());
		}
	}

	@Test
	void testXMLSlideShow() throws IOException {
		TestXMLSlideShow.main(null);
	}
}
