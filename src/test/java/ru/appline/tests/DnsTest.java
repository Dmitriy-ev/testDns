package ru.appline.tests;

import org.junit.Test;

import ru.appline.base.BaseTest;

public class DnsTest extends BaseTest {

	@Test
	public void test() throws InterruptedException {
				
		app.getStartPage().
				  siteSearchProduct("playstation")
				 .selectProductTheList("Игровая консоль PlayStation 4 Slim Black")
				 .selectWarranty("1")
				 .clickToBuy()
				 .productSearch("Detroit")
				 .clickToBuy()
				 .clickToCart()
				 .checkingPriceAndSum()
				 .checkingWarranty("Игровая консоль PlayStation 4 Slim Black")
				 .deleteProductInCart("Detroit")
				 .addProduct("Игровая консоль PlayStation 4 Slim Black")
				 .returnProduct();

	}
}
