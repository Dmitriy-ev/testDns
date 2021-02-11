package ru.appline.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends BasePage{

	@FindBy(xpath = "//input[contains(@placeholder,'Поиск по сайту')]")
	WebElement siteSearch;

	public ResulSearchPage siteSearchProduct(String name){
		elementToBeClickable(siteSearch);
		siteSearch.click();
		siteSearch.sendKeys(name + "\n");
		return app.getResulSearchPage();
	}
	
}
