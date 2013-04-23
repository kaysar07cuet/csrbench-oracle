package it.polimi.deib.streams.oracle;

import it.polimi.deib.streams.oracle.s2r.ReportPolicy;
import it.polimi.deib.streams.oracle.s2r.Window;

import java.io.File;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {
	private static Config _instance = null;
	private static final Logger logger = LoggerFactory.getLogger(Config.class); 
	
	private Configuration config;
	
	private Config(){
		try {
			config = new PropertiesConfiguration("setup.properties");
		} catch (ConfigurationException e) {
			logger.error("Error while reading the configuration file", e);
		}
	}
	
	public File getRepoDir(){
		String dir = config.getString("repo.datadir");
		File ret = new File(dir);
		return ret;
	}
	
	public Window getWindow(){
		return new Window(config.getLong("window.size"), config.getLong("window.slide"));
	}
	
	public Long getFirstT0(){
		return config.getLong("window.firstt0");
	}
	
	public Long getTimeUnit(){
		return config.getLong("system.timeunit");
	}
	
	public ReportPolicy getPolicy(){
		ReportPolicy ret = new ReportPolicy();
		ret.setWindowClose(config.getBoolean("system.policy.windowclose"));
		ret.setNonEmptyContent(config.getBoolean("system.policy.nonemptycontent"));
		return ret;
	}
	
	public static Config getInstance(){
		if(_instance==null)
			_instance=new Config();
		return _instance;
	}
	
}