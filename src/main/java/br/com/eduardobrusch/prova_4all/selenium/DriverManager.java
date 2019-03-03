package br.com.eduardobrusch.prova_4all.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
	
	private WebDriver driver;
	
	public DriverManager() {
		bootDriver();
	}
	
	public WebDriver getDriver() {		
		if(driver==null)
			bootDriver();
		
		return driver;
	}	
	
	private void bootDriver() {
		System.out.println(System.getProperty("browser.name"));
		if(System.getProperty("browser.name").toLowerCase().contains("chrome")) {
			bootChrome();
		} else {
			bootFirefox();
		}
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(System.getProperty("homepage.address"));
	}
	
	private void bootChrome() {
		String path = System.getProperty("driver.chrome.path");
		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver();
	}
	
	private void bootFirefox() {
		String path = System.getProperty("driver.firefox.path");
		System.setProperty("webdriver.firefox.driver", path);
		driver = new FirefoxDriver();
	}

}