package br.com.template.steps;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.template.context.TestContext;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractSteps {
	
	@Autowired
	protected TestContext context;

	InputStream inputStream;

	@Value("${env}")
	private String env;

	protected Logger LOG = Logger.getLogger(this.getClass());

	public String getProperty(String propertyName) {
		String propertyNameResult = propertyName;
		try {
			Properties prop = new Properties();
			String propFileName = "configs/"+env+"/env.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			if(prop.getProperty(propertyName) !=null){
				propertyNameResult = prop.getProperty(propertyName);
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		return propertyNameResult;
	}
}
