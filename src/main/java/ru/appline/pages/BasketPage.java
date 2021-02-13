package ru.appline.pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends BasePage{

	@FindBy(xpath = "//div[@data-cart-product-id]")
	List<WebElement> productId;

	@FindBy(xpath = "//div[@class='group-tabs']//span[@class = 'restore-last-removed']")
	WebElement returnDeleteProduct;

	/**
	 *удаление товара 
	 */
	public BasketPage deleteProductInCart(String nameProduct) throws InterruptedException{
		WebElement name;
		for (WebElement webElement : productId) {
			name = webElement.findElement(By.xpath(".//div[@class='cart-items__product-name']/a"));
			if(name.getText().contains(nameProduct)) {
				WebElement delete = webElement.findElement(By.xpath(".//button[.='Удалить']"));
				scrollWithOffset(delete, 0, -200);
				elementToBeClickable(delete);
				delete.click();
				return this;
			}

		}
		Assert.fail("Товар не был удален");
		return null;
	}

	/**
	 *увеличение товара 
	 */
	public BasketPage addProduct(String nameProduct) {
		WebElement name;
		for (WebElement webElement : productId) {
			name = webElement.findElement(By.xpath(".//div[@class='cart-items__product-name']/a"));
			if(name.getText().contains(nameProduct)) {
				WebElement add = webElement.findElement(By.xpath(".//button[@data-commerce-action='CART_ADD']"));
				scrollWithOffset(add, 0, -250);
				elementToBeVisible(add);
				elementToBeClickable(add).click();
				return this;
			}
		}
		Assert.fail("Товар не был добавлен");
		return null;
	}

	/**
	 *возврат удаленного товара 
	 */
	public BasketPage returnProduct() {
		scrollApp();
		elementToBeVisible(returnDeleteProduct);
		elementToBeClickable(returnDeleteProduct);
		returnDeleteProduct.click();
		return this;
	}

	/**
	 * проверка гарантии
	 */
	public BasketPage checkingWarranty(String nameProduct) {
		WebElement check;
		for (WebElement webElement : productId) {
			check = webElement.findElement(By.xpath(".//div[@class='cart-items__product-name']/a"));
			if(check.getText().contains(nameProduct)) {
				WebElement guarantee = webElement.findElement(By.xpath(".//div[@data-commerce-target='basket_additional_warranty_24']//span"));
				Assert.assertEquals("Гарантия не стоит на кнопке 24 месяца", "+ 24 мес.", guarantee.getText());
				return app.getBasketPage();
			}
		}
		return null;
	}

	/**
	 *Общая суммы 
	 */
	public int getbasketSum(){
		WebElement basketSum;
		int sum = 0;
		for (WebElement webElement : productId) {
			basketSum = webElement.findElement(By.xpath(".//span[@class='price__current']"));
			sum += Integer.parseInt(basketSum.getText().replaceAll("\\D", ""));
		}
		return sum;
	}

	/**
	 *цена продукта по имени 
	 */
	public int basketPriceProduct(String nameProduct) {
		WebElement basketProduct;
		int price = 0;
		for (WebElement webElement : productId) {
			basketProduct = webElement.findElement(By.xpath(".//div[@class='cart-items__product-name']/a"));
			if(basketProduct.getText().contains(nameProduct)) {
				WebElement priceBasketProduct  = webElement.findElement(By.xpath(".//span[@class='price__current']"));
				price = Integer.parseInt(priceBasketProduct.getText().replaceAll("\\D", ""));
			}
		}
		return price;
	}

	/**
	 *проверка суммы товаров 
	 */
	public BasketPage checkingPriceAndSum(){
		Assert.assertEquals("Сумма товаров в корзине не верна", basketPriceProduct("Detroit") + basketPriceProduct("Игровая консоль"), getbasketSum());
		return app.getBasketPage();
	}
}
