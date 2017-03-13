package com.jungle.learning.weblearning.config;

import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

	private static PropertiesManager instance;

	private PropertiesManager() {
	}

	public static PropertiesManager getInstance() {
		if (instance == null) {
			instance = new PropertiesManager();
		}
		return instance;
	}

	private Properties jdbcCfg = null;

	public Properties getJdbcConfig() {
		if (jdbcCfg == null) {
			try {
				jdbcCfg = new Properties();
				jdbcCfg.load(PropertiesManager.class.getResourceAsStream("jdbc.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jdbcCfg;
	}
}
