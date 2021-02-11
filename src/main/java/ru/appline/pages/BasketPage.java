package ru.appline.pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends BasePage{

	@FindBy(xpath = "//div[@data-cart-product-id]")
	List<WebElement> productId;

	public BasketPage deleteProductInCart(String nameProduct) throws InterruptedException{
		WebElement name;
		for (WebElement webElement : productId) {
			name = webElement.findElement(By.xpath(".//div[@class='cart-items__product-name']/a"));
			if(name.getText().contains(nameProduct)) {
				WebElement delete = webElement.findElement(By.xpath(".//button[.='Удалить']"));
				delete.click();
				return this;
			}

		}
		Assert.fail("Товар не был удален");
		return null;
	}
	
	public BasketPage addProduct(String name) {
		
		
		
		return this;
	}


}
