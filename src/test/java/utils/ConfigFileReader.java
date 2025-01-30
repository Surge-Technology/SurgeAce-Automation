package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	
	private final Properties properties;
	private final String propertyFilePath= System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\SurgeAce_Regression.properties";

	
	public ConfigFileReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("SurgeAce_Regression.properties not found at " + propertyFilePath);
		}		
	}
	
	public String getDriverPath(){
		String driverPath = System.getProperty("user.dir")+properties.getProperty("driverPath");
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("driverPath not specified in the SurgeAce_Regression.properties file.");		
	}
	
	public long getImplicitlyWait() {		
		String implicitlyWait = System.getProperty("user.dir")+properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
		else throw new RuntimeException("implicitlyWait not specified in the SurgeAce_Regression.properties file.");		
	}
	
	public String getApplicationUrl() {
		String url = System.getProperty("user.dir")+properties.getProperty("Application_URL");
		if(url != null) return url;
		else throw new RuntimeException("url not specified in the SurgeAce_Regression.properties file.");
	}
	
	public String getReportConfigPath(){
		String reportConfigPath = System.getProperty("user.dir")+properties.getProperty("extentReportConfigPath");
		if(reportConfigPath!= null) return reportConfigPath;
		else throw new RuntimeException("Report Config Path not specified in the SurgeAce_Regression.properties file for the Key:reportConfigPath");		
	}
	public String getCorrespondenceFilePath(){
		String correspondenceFile = System.getProperty("user.dir")+properties.getProperty("correspondenceFilePath");
		if(correspondenceFile!= null) return correspondenceFile;
		else throw new RuntimeException("Report Config Path not specified in the SurgeAce_Regression.properties file for the Key:correspondenceFilePath");		
	}
	public String getBulkUploadFilePath(){
		String bulkUploadFile = System.getProperty("user.dir")+properties.getProperty("bulkUploadFilePath");
		if(bulkUploadFile!= null) return bulkUploadFile;
		else throw new RuntimeException("Report Config Path not specified in the SurgeAce_Regression.properties file for the Key:bulkUploadFilePath");		
	}
	
}
