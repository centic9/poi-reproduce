package org.dstadler.poi;

import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Collections;

import org.apache.commons.io.file.NoopPathVisitor;

public class TestFiles {

	public static void main(String[] args) {
		URL url = TestFiles.class.getResource("/multiple.zip");
		URI uri = URI.create("jar:" + url.getProtocol() + "://" + url.getFile());
		try (FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
			System.out.println("FS: " + fs);

			Files.walkFileTree(fs.getPath("."), new NoopPathVisitor());
		} catch (Exception e) {
			throw new IllegalStateException("Failed for URI: " + uri, e);
		}
	}
}
