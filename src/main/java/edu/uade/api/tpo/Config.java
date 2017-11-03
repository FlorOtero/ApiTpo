package edu.uade.api.tpo;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
	private static Properties config = new Properties();
	static {
		try {
			FileInputStream in = new FileInputStream("src/main/resources/config.properties");
			config.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return config.getProperty(key);
	}
}
