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
	@FindBy(xpath = "//div[@data-id='product']")
	List<WebElement> selectProduct;


	/**
	 *выбор товара из найденных 
	 */
	public ProductAndPriceInfomationPage selectProductTheList(String name) {
		WebElement nameProduct;
		for (WebElement webElement : selectProduct) {
			nameProduct = webElement.findElement(By.xpath(".//a[@class='ui-link']"));
			if(nameProduct.getText().contains(name)) {
				scrollWithOffset(nameProduct, 0, -200);
				elementToBeVisible(nameProduct);
				elementToBeClickable(nameProduct);
				nameProduct.click();
			}
			return app.getProductAndPriceInfomationPage();
		}
		Assert.fail("Товар " + name + " не был найден");
		return null;
	}
}
