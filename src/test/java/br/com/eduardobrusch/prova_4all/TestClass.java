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
		cart.clickCloseFinishedSale();		
		Assert.assertEquals(message, expectedMessage);
		
		driver.quit();
	}
	
	@Test
	public void shouldIncreaseDecreaseThenBuyItems() {
		
		WebDriver driver = new DriverManager().getDriver();
		MenuPage menu = new MenuPage(driver);
		CartPage cart = new CartPage(driver);
		String expectedMessage = "Pedido realizado com sucesso!";
		
		menu.selectCategory("Bebidas");
		menu.addAllToCart();
		menu.selectCategory("Todos");
		menu.addToCart("Rissole médio");
		menu.clickCartIcon();
		
		int currentAmount = Integer.parseInt(cart.getItemAmount("Rissole médio"));
		
		cart.setItemAmount("Rissole médio", currentAmount+9);
		currentAmount = Integer.parseInt(cart.getItemAmount("Rissole médio"));		
		Assert.assertEquals(currentAmount, 10);
		
		cart.setItemAmount("Rissole médio", currentAmount-5);
		currentAmount = Integer.parseInt(cart.getItemAmount("Rissole médio"));		
		Assert.assertEquals(currentAmount, 5);
		
		String message = cart.clickFinishSaleGetMessage();		
		cart.clickCloseFinishedSale();		
		Assert.assertEquals(message, expectedMessage);
		
		driver.quit();
	}

}
