package br.com.eduardobrusch.prova_4all.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MenuPage extends Pages{
	
	private By IDDVcategoryOpen = By.id("open-categories-btn");
	private By XPBtItem = By.xpath("//button[contains(@id, 'add-product-')]");
	private By XPLICategoryItem = By.xpath("//button[contains(@id, 'category-')]");
		
	private By IDBtCart = By.id("cart-btn");
	
	public MenuPage(WebDriver driver) {
		super(driver);		
	}

	public void selectCategory(String category) {
		logAction(String.format("Categoria %s", category), "select");
		
		find(IDDVcategoryOpen).click();
				
		List<WebElement> itemsCategory = findAll(XPLICategoryItem);
		
		for(WebElement itemEl : itemsCategory) {
			if(itemEl.getText().toLowerCase().contains(category)) {
				itemEl.click();
			}
		}
	}

	public void addAllToCart() {
		logAction("Itens Doce", "click");
		List<WebElement> doces = findAll(XPBtItem);
		for(WebElement doce: doces)
			doce.click();
		
	}

	public void clickCartIcon() {
		logAction("Cart", "click");
		find(IDBtCart).click();
	}

}
