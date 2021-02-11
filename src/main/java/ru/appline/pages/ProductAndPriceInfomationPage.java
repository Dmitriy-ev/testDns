package ru.appline.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductAndPriceInfomationPage extends BasePage{

	@FindBy(xpath = "//div[@class='node-block']")
	WebElement product;

	@FindBy(xpath = "//input[contains(@placeholder,'Поиск по сайту')]")
	WebElement productSearch;

	String priceProductOne = product.findElement(By.xpath(".//div[@class='product-card-price']//span[@class='product-card-price__current']")).getText();
	public ProductAndPriceInfomationPage selectWarranty(String value) {
		Select select = new Select(product.findElement(By.xpath(".//select[@class='ui-input-select product-warranty__select']")));
		select.selectByValue(value);
		return this;
	}
	
	public ProductAndPriceInfomationPage clickToBuy() {
		product.findElement(By.xpath(".//div[@class='primary-btn']")).click();
		return this;
	}

	public ProductAndPriceInfomationPage productSearch(String name){
		productSearch.click();
		productSearch.sendKeys(name + "\n");
		return this;
	}

	public BasketPage clickToCart() {
		WebElement cart = product.findElement(By.xpath(".//button[.='В корзине']"));
		cart.click();
		return app.getBasketPage();
	}
}
