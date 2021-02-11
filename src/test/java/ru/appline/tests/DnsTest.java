package ru.appline.tests;

import org.junit.Test;

import ru.appline.base.BaseTest;

public class DnsTest extends BaseTest {

	@Test
	public void test() throws InterruptedException {
				
		app.getStartPage().
				  siteSearchProduct("playstation")
				 .selectProductTheList("Игра The Legend")
				 .selectWarranty("1")
				 .clickToBuy()
				 .productSearch("Detroit")
				 .clickToBuy()
				 .clickToCart()
				 .deleteProductInCart("Detroit");

	}
}
