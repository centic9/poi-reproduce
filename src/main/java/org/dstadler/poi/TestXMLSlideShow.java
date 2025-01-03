package org.dstadler.poi;

import java.io.IOException;

import org.apache.poi.Version;
import org.apache.poi.xslf.usermodel.XMLSlideShow;

public class TestXMLSlideShow {

	public static void main(String[] args) throws IOException {

		System.out.println();
		System.out.println("Creating SlideShow" +
				" for version " + Version.getVersion() +
				" and Java " + System.getProperty("java.version"));

		System.out.printf("Memory before,   total/max/free: %3dm/%3dm/%3dm%n", Runtime.getRuntime().totalMemory() / 1024 / 1024,
				Runtime.getRuntime().maxMemory() / 1024 / 1024, Runtime.getRuntime().freeMemory() / 1024 / 1024);
		try (XMLSlideShow pptx = new XMLSlideShow()) {
			System.out.println("Slides: " + pptx.getSlides().size());

			System.out.printf("Memory when allocated, total/max/free: %3dm/%3dm/%3dm%n",
					Runtime.getRuntime().totalMemory() / 1024 / 1024, Runtime.getRuntime().maxMemory() / 1024 / 1024,
					Runtime.getRuntime().freeMemory() / 1024 / 1024);

			System.gc();

			System.out.printf("Memory when GC,        total/max/free: %3dm/%3dm/%3dm%n", Runtime.getRuntime().totalMemory() / 1024 / 1024,
					Runtime.getRuntime().maxMemory() / 1024 / 1024, Runtime.getRuntime().freeMemory() / 1024 / 1024);
		}
		System.out.printf("Memory after allocate, total/max/free: %3dm/%3dm/%3dm%n",
				Runtime.getRuntime().totalMemory() / 1024 / 1024, Runtime.getRuntime().maxMemory() / 1024 / 1024,
				Runtime.getRuntime().freeMemory() / 1024 / 1024);

		System.gc();

		System.out.printf("Memory after GC,       total/max/free: %3dm/%3dm/%3dm%n", Runtime.getRuntime().totalMemory() / 1024 / 1024,
				Runtime.getRuntime().maxMemory() / 1024 / 1024, Runtime.getRuntime().freeMemory() / 1024 / 1024);
	}
}
