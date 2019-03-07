package br.com.eduardobrusch.prova_4all.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MenuPage extends Pages{
	
	private By IDDVcategoryOpen = By.id("open-categories-btn");
	private By XPBtItem = By.xpath("//button[contains(@id, 'add-product-')]");	
	private By XPLICategoryItem = By.xpath("//li[contains(@id, 'category-')]");		
	private By IDBtCart = By.id("cart-btn");
	//private By XPDvVisibleItems = By.xpath("//li[contains(@class, 'sc-bdVaJa')]"); 
	//private By XPDvVisibleItemString = By.xpath("//h1[contains(@data-id, '-name')]"); 
	private By XPDvVisibleItemString = By.xpath("//h1[contains(@class, 'sc-EHOje')]");

	
	public MenuPage(WebDriver driver) {
		super(driver);		
	}

	public void selectCategory(String category) {
		log(actionInteractMessage(String.format("Categoria %s", category), "select"));		
		
		find(IDDVcategoryOpen).click();
				
		List<WebElement> itemsCategory = findAll(XPLICategoryItem);
		
		for(WebElement itemEl : itemsCategory) {				
			
			if(itemEl.getText().toLowerCase().contains(category.toLowerCase())) {
				log(String.format("Found matching category: %s <=> %s", category, itemEl.getText()));				
				getActions().moveToElement(itemEl).click().perform();
				break;
			}
		}
		
	}
	
	public void addToCart(String itemToAdd) {
		int index = -1;
		String compareToAdd = getNormalized(itemToAdd).replaceAll(" ", "").toLowerCase();		
		
		List<WebElement> visible = findAll(XPDvVisibleItemString);
		
		log("ITEMS "+String.valueOf(visible.size()));
		
		int iteratorIndex = 0;
		for(WebElement item: visible) {
			
			String current = getNormalized(item.getText()).replaceAll(" ", "").toLowerCase();
			log("ITEM "+iteratorIndex +": " + current);
			if(compareToAdd.contains(current)) {
				log(String.format("Found matching item: %s <=> %s. index %s", current, compareToAdd, iteratorIndex));
				index = iteratorIndex;
			} else {
				iteratorIndex ++;
			}
		}
		
		if(index == -1)
			throw new RuntimeException(String.format("Item %s not found", itemToAdd));
		
		log("Adding to cart: "+findAll(XPDvVisibleItemString).get(index).getText());
		findAll(XPBtItem).get(index).click();		
	}
	
	public void addAllToCart() {
		log("Add all visible items to cart.");		
		List<WebElement> visible = findAll(XPBtItem);
		for(WebElement item: visible)
			item.click();		
	}

	public void clickCartIcon() {
		log(actionInteractMessage("Cart", "click"));
		find(IDBtCart).click();
	}

}
