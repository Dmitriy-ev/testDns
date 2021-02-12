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

	public BasketPage addProduct(String nameProduct) {
		WebElement name;
		for (WebElement webElement : productId) {
			name = webElement.findElement(By.xpath(".//div[@class='cart-items__product-info']"));
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

	public BasketPage returnProduct() {
		scrollApp();
		elementToBeVisible(returnDeleteProduct);
		elementToBeClickable(returnDeleteProduct);
		returnDeleteProduct.click();
		return this;
	}

}
