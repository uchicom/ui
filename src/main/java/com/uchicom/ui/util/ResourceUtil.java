// (c) 2017 uchicom
package com.uchicom.ui.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class ResourceUtil {

	public static Properties createProperties(File file, String charset) {
		Properties properties = new Properties();
		if (file.exists() && file.isFile()) {
			try (FileInputStream fis = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fis, charset)) {
				properties.load(isr);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}
}
