package br.com.eduardobrusch.prova_4all.pages;

import java.text.Normalizer;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

public class Pages {
	
	protected final boolean logConsole = Boolean.parseBoolean(System.getProperty("log.console"));
	private WebDriver driver;
	
	public Pages (WebDriver driver) {
		this.driver = driver;
	}	
		
	protected WebElement find(By locator) {				
		try {			
			return driver.findElement(locator);			
		} catch(NoSuchElementException ex) {
			String message = getErrorNotFoundMessage(locator.toString());
			Reporter.log(message, logConsole);
			throw new RuntimeException(message, ex); 
		} catch(Exception ex2) {
			String message = getErrorGeneral(locator);
			Reporter.log(message, logConsole);
			throw new RuntimeException(message, ex2); 
		}
		
	}
	
	protected List<WebElement> findAll(By locator) {				
		try {			
			return driver.findElements(locator);			
		} catch(NoSuchElementException ex) {
			String message = getErrorNotFoundMessage(locator.toString());
			Reporter.log(message, logConsole);
			throw new RuntimeException(message, ex); 
		} catch(Exception ex2) {
			String message = getErrorGeneral(locator);
			Reporter.log(message, logConsole);
			throw new RuntimeException(message, ex2); 
		}
		
	}
	
	protected Actions getActions() {		
		return new Actions(driver);
	}
	
	protected void log(String log) {
		Reporter.log(log, logConsole);
	}
	
	private String getErrorGeneral(By name) {
		return String.format("Error! error trying to find element located by %s.", name);
	}

	private String getErrorNotFoundMessage(String element) {
		return String.format("Error! Element located by %s was not found. Test will fail.", element);
	}

	protected String actionInteractMessage(String name, String action) {
		return String.format("Executing %s on element: %s.", action, name);
	}
	
	protected String getNormalized(String font) {
		return Normalizer.normalize(font, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

}
