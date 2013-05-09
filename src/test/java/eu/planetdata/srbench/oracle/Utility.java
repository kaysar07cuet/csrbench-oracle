package eu.planetdata.srbench.oracle;

import static org.junit.Assert.fail;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openrdf.model.impl.URIImpl;
import org.slf4j.Logger;

import eu.planetdata.srbench.oracle.result.TimestampedRelation;
import eu.planetdata.srbench.oracle.result.TimestampedRelationElement;

public class Utility {
	public static TimestampedRelation importRelation(String filename, Logger logger){
		try {
			TimestampedRelation ret;
			Configuration relationImporter = new PropertiesConfiguration(filename);

			ret = new TimestampedRelation();
			ret.setComputationTimestamp(relationImporter.getLong("timestamp"));
			
			for(String s : relationImporter.getStringArray("sensor")){
				TimestampedRelationElement tre = new TimestampedRelationElement();
				tre.add("sensor", new URIImpl(s));
				//FIXME: should import the element timestamp!
				tre.setTimestamp(relationImporter.getLong("timestamp"));
				ret.addElement(tre);
			}
			return ret;
		} catch (ConfigurationException e) {
			logger.error("Error while reading the configuration file", e);
			fail();
		}
		return null;
	}
}