package ru.appline.pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResulSearchPage extends BasePage{

	/**
	 * Найденные товары
	 */
	@FindBy(xpath = "//div[@class='items-group']")
	List<WebElement> selectProduct;

	public ProductAndPriceInfomationPage selectProductTheList(String name) {
		WebElement nameProduct;
		for (WebElement webElement : selectProduct) {
			nameProduct = webElement.findElement(By.xpath(".//div[@class='product-info__title-link']/a"));
			if(nameProduct.getText().toLowerCase().contains(name)) {
				nameProduct.click();
			}
			return app.getProductAndPriceInfomationPage();
		}
		Assert.fail("Товар " + name + " не был найден");
		return null;
	}
}