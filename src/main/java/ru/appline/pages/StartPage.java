package ru.appline.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class StartPage extends BasePage{

	public ResulSearchPage siteSearchProduct(String name){
		WebElement siteSearch = navigation.findElement(By.xpath(".//input[contains(@placeholder,'Поиск по сайту')]"));
		elementToBeClickable(siteSearch);
		siteSearch.click();
		siteSearch.sendKeys(name + "\n");
		return app.getResulSearchPage();
	}
	
}
