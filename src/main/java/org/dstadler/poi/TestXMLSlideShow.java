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

		System.out.println("Memory before total/max/free: " + Runtime.getRuntime().totalMemory()/1024/1024 + "m/" +
					Runtime.getRuntime().maxMemory()/1024/1024 + "m/" + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "m");
		try (XMLSlideShow pptx = new XMLSlideShow()) {
			System.out.println("Slides: " + pptx.getSlides().size());

			System.out.println("Memory when allocated total/max/free: " + Runtime.getRuntime().totalMemory()/1024/1024 + "m/" +
					Runtime.getRuntime().maxMemory()/1024/1024 + "m/" + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "m");
		}
		System.out.println("Memory after total/max/free: " + Runtime.getRuntime().totalMemory()/1024/1024 + "m/" +
				Runtime.getRuntime().maxMemory()/1024/1024 + "m/" + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "m");
	}
}
