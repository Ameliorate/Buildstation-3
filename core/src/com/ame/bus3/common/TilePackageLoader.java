package com.ame.bus3.common;

import com.sun.org.apache.xml.internal.utils.URI;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;

/**
 * Used for loading content that wasn't compiled into the jar.
 * @author Amelorate
 */
public class TilePackageLoader {
	/**
	 * Loads all of the content packages in $RunningDirectory/contentpackages/
	 */
	public void load() {
		CodeSource codeSource = Variables.mainClass.getProtectionDomain().getCodeSource();
		File jarFile = null;
		try {
			jarFile = new File(codeSource.getLocation().toURI().getPath());
		}
		catch (URISyntaxException e) {}
		if (jarFile != null) {
			String contentPackagesDir = jarFile.getParentFile().getPath() + "contentpackages/";
			// The above gets the directory the game is running in.
			// Now I need to load the class.
			File[] loading = new File(contentPackagesDir).listFiles();
			URL loadingURL;
			URL[] loadingURLs;
			ClassLoader loader;		// I'm not really sure if it is actually better to initialise variables outside loops, but meh.
			Class loadingClass;
			TileInitaliser loadedInitaliser;
			for (int i = 0; i < loading.length; i++) {
				try {
					loadingURL = loading[i].toURI().toURL();
				}
				catch (MalformedURLException e) {
					continue;	// I'm not entirely sure this can happen, but no harm in doing this.
				}
				loadingURLs = new URL[]{loadingURL};
				loader = new URLClassLoader(loadingURLs);
				try {
					loadingClass = loader.loadClass("foo");
				}
				catch (ClassNotFoundException e) {
					System.out.println("[Error] Bad contentpackage. Skipping...");
					continue;
				}
				// Load and call the class.
				try {
					loadedInitaliser = (TileInitaliser) loadingClass.newInstance();
				}
				catch (IllegalAccessException e) {
					System.out.println("[Error] IllegalAccessException");
					continue;
				}
				catch (java.lang.InstantiationException e) {
					System.out.println("[Error] InstantiationException");
					continue;
				}
				catch (ClassCastException e) {
					System.out.println("[Error] Content package does not fallow specification. It does not implement the required interface.");
					continue;
				}
				loadedInitaliser.create(Variables.map);
			}
		}
	}
}
