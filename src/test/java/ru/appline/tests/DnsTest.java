package ru.appline.tests;

import org.junit.Test;

import ru.appline.base.BaseTest;

public class DnsTest extends BaseTest {

	@Test
	public void test() throws InterruptedException {

		app.getStartPage()
		 				.siteSearchProduct("playstation")
						.selectProductTheList("Игровая консоль PlayStation 4 Slim Black")
						.selectWarranty("1")
						.clickToBuy()
						.productSearch("Detroit")
						.clickToBuy()
						.clickToCart()
						.checkingWarranty("Игровая консоль PlayStation 4 Slim Black")
						.chekingPrice("Игровая консоль PlayStation 4 Slim Black", 29999)
						.chekingPrice("Detroit", 2599)
						.checkingSum()
						.deleteProductInCart("Detroit")
						.addProduct("Игровая консоль PlayStation 4 Slim Black")
						.returnProduct("Detroit");

	}
}
