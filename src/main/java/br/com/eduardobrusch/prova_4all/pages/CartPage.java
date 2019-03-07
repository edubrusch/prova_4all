package br.com.eduardobrusch.prova_4all.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends Pages {	
	
	private By XPDvItemCArtName = By.xpath("//div[contains(@id, '-name')]");
	private By XPPItemAmount = By.xpath("//p[contains(@id, '-qtd')]");
	private By XPDvAddItem = By.xpath("//*[contains(@id, 'add-product-')]");
	private By XPDvRemoveItem = By.xpath("//*[contains(@id, 'remove-product-')]");
	private By IDBtFinishSale = By.id("finish-checkout-button");
	private By XPLbMessageCheckout = By.xpath("//h2[contains(@class, 'sc-dNLxif')]"); 
	private By XPBtCheckoutClose = By.xpath("//button[contains(@class, 'close-modal')]");
	
	
	public CartPage(WebDriver driver) {
		super(driver);		
	}

	public void setItemAmount(String item, int amount) {
		log(String.format("set amount of the item %s in the cart", item));
		int itemIndex = findItemInTheCart(item);		
		setAmoutItemCart(itemIndex, amount);
	}	

	public String clickFinishSaleGetMessage() {
		log(actionInteractMessage("Finish Checkout", "Click"));		
		find(IDBtFinishSale).click();		
		return find(XPLbMessageCheckout).getText();		
	}
	
	public void clickCloseFinishedSale() {
		log(actionInteractMessage("Close", "Click"));		
		find(XPBtCheckoutClose).click();		
	}
	
	public String getItemAmount(String cartItem) {
		return findAll(XPPItemAmount).get(findItemInTheCart(cartItem)).getText();
	}
	
	private void setAmoutItemCart(int cartItem, int amountDesired) {
		String amountStr = findAll(XPPItemAmount).get(cartItem).getText();
		
		int currentAmount = Integer.parseInt(findAll(XPPItemAmount).get(cartItem).getText());
				
		while(currentAmount < amountDesired) {			
			findAll(XPDvAddItem).get(cartItem).click();
			currentAmount ++;
			amountStr = findAll(XPPItemAmount).get(cartItem).getText();			
		}		
				
		while(currentAmount > amountDesired) {			
			findAll(XPDvRemoveItem).get(cartItem).click();			
			currentAmount --;
			amountStr = findAll(XPPItemAmount).get(cartItem).getText();			
		}
		
		log(String.format("Item amount: %s", amountStr));
		
	}

	private int findItemInTheCart(String item) {
		int index = -1;		
		String errorMmssage = String.format("Not found item %s the cart", item); 
		log(String.format("Looking for %s in the cart", item));		
		
		List<WebElement> itemsCart = findAll(XPDvItemCArtName);		
		
		if(itemsCart.size()==0)
			throw new RuntimeException(errorMmssage);		
		
		for(int i = 0; i< itemsCart.size(); i++) {			
			String currentItem = itemsCart.get(i).getText();			
			if(currentItem.toLowerCase().contains(item.toLowerCase())) {	
				log(String.format("Found matching cart item: %s <=> %s index %s", item, currentItem, i));
				index = i;
			}
		}
		
		if(index == -1) {			
			log(errorMmssage);
			throw new RuntimeException(errorMmssage);
		}	
		
		return index;
	}

	

}
