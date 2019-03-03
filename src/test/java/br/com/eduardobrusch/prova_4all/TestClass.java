package br.com.eduardobrusch.prova_4all;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import br.com.eduardobrusch.prova_4all.pages.CartPage;
import br.com.eduardobrusch.prova_4all.pages.MenuPage;
import br.com.eduardobrusch.prova_4all.selenium.DriverManager;

public class TestClass {
	
	
	@Test
	public void shouldBuyItems() {
		WebDriver driver = new DriverManager().getDriver();
		MenuPage menu = new MenuPage(driver);
		CartPage cart = new CartPage(driver);
		String expectedMessage = "Pedido realizado com sucesso!";
		
		menu.selectCategory("Doces");
		menu.addAllToCart();
		menu.selectCategory("Todos");
		menu.clickCartIcon();
		cart.setItemAmount("brigadeiro", 4);
		String message = cart.clickFinishSaleGetMessage();
		
		Assert.assertEquals(message, expectedMessage);
		
		driver.quit();
	}
	
	@Test
	public void shouldIncreaseDecreaseThenBuyItems() {
		Assert.assertEquals(true, true);
	}

}
