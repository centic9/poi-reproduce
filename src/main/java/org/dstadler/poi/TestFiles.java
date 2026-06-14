package org.dstadler.poi;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.Objects;

public class TestFiles {

	public static void main(String[] args) {
		URL url = TestFiles.class.getResource("/multiple.zip");
		Objects.requireNonNull(url);
		URI uri = URI.create("jar:" + url.getProtocol() + "://" + url.getFile());
		try (FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
			System.out.println("FS: " + fs);

			Files.walkFileTree(fs.getPath("."), new NoopPathVisitor<>());
		} catch (Exception e) {
			throw new IllegalStateException("Failed for URI: " + uri, e);
		}
	}

	public static class NoopPathVisitor<T> implements FileVisitor<T> {
		public NoopPathVisitor() {
		}

		@Override
		public FileVisitResult preVisitDirectory(T dir, BasicFileAttributes attrs)
				throws IOException
		{
			Objects.requireNonNull(dir);
			Objects.requireNonNull(attrs);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(T file, BasicFileAttributes attrs)
				throws IOException
		{
			Objects.requireNonNull(file);
			Objects.requireNonNull(attrs);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(T file, IOException exc)
				throws IOException
		{
			Objects.requireNonNull(file);
			throw exc;
		}

		@Override
		public FileVisitResult postVisitDirectory(T dir, IOException exc)
				throws IOException
		{
			Objects.requireNonNull(dir);
			if (exc != null)
				throw exc;
			return FileVisitResult.CONTINUE;
		}
	}
}
