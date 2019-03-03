package br.com.eduardobrusch.prova_4all.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends Pages {
	
	private By CLDvItemCart = By.className("sc-csuQGl wfUhd");
	private By XPDvItemCArtName = By.xpath("//*[contains(@id, '-name')]");
	private By XPPItemAmount = By.xpath("//*[contains(@id, '-qtd')]");
	private By XPDvAddItem = By.xpath("//*[contains(@id, 'add-product-')]");
	private By XPDvRemoveItem = By.xpath("//*[contains(@id, 'remove-product-')]");
	private By IDBtFinishSale = By.id("finish-checkout-button");
	private By CLLbMessageCheckout = By.className("sc-dNLxif jyncPa");
	
	public CartPage(WebDriver driver) {
		super(driver);		
	}

	public void setItemAmount(String item, int amount) {
		logAction(item, "set amount in the cart");
		setAmoutItemCart(findItemInTheCart(item), amount);
	}	

	public String clickFinishSaleGetMessage() {
		logAction("Finish Checkout", "Click");
		find(IDBtFinishSale).click();
		
		return find(CLLbMessageCheckout).getText();
		
	}
	
	private void setAmoutItemCart(WebElement cartItem, int amount) {
		int currentAmount = Integer.parseInt(cartItem.findElement(XPPItemAmount).getText());
		
		while(currentAmount < amount) {
			logAction("plus button", "click");
			find(XPDvAddItem).click();
			currentAmount ++;
		}
		
		while(currentAmount > amount) {
			logAction("minus button", "click");
			find(XPDvRemoveItem).click();
			currentAmount --;
		}
		
	}

	private WebElement findItemInTheCart(String item) {
		WebElement index = null;
		boolean found = false;
		
		List<WebElement> itemsCart = findAll(CLDvItemCart);
		
		if(itemsCart.size()==0)
			throw new RuntimeException(String.format("Item %s not found in the cart.", item));
		
		for(WebElement itemEl : itemsCart) {
			if(itemEl.findElement(XPDvItemCArtName).getText().toLowerCase().contains(item.toLowerCase())) {
				index = itemEl;
			}
		}
		
		if(!found)
			return null;
		
		return index;
	}

}
