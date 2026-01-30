package main.java.yt_multi_lang_ui_validator.seleniumGrid;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GlobalGridUtility {

	
	public static WebDriver createRemoteFirefoxDriver(String gridHubUrl,FirefoxOptions firefoxOptions) {
		
		  try {
		        URI uri = URI.create(gridHubUrl);
		        URL url = uri.toURL();

		        return new RemoteWebDriver(url, firefoxOptions);

		    } catch (MalformedURLException e) {
		        throw new RuntimeException("Invalid Grid URL: " + gridHubUrl, e);
		    }
		  
	}
	
	
	
	
	public static WebDriver createRemoteEdgeDriver(String gridHubUrl,EdgeOptions edgeOptions) {
		
		  try {
		        URI uri = URI.create(gridHubUrl);
		        URL url = uri.toURL();

		        return new RemoteWebDriver(url, edgeOptions);

		    } catch (MalformedURLException e) {
		        throw new RuntimeException("Invalid Grid URL: " + gridHubUrl, e);
		    }
		  
	}
	
	
	
	
	public static WebDriver createRemoteChromeDriver(String gridHubUrl,ChromeOptions chromeOptions) {
		
		  try {
		        URI uri = URI.create(gridHubUrl);
		        URL url = uri.toURL();

		        return new RemoteWebDriver(url, chromeOptions);

		    } catch (MalformedURLException e) {
		        throw new RuntimeException("Invalid Grid URL: " + gridHubUrl, e);
		    }
		  
	}
	
	
	

}
