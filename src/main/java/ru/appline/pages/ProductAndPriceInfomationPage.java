package ru.appline.pages;



import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductAndPriceInfomationPage extends BasePage{

	@FindBy(xpath = "//div[@class='item-header']")
	WebElement product;

	@FindBy(xpath = "//div[@class= 'product-card-price__current-wrap']")
	WebElement productPrice;

	/**
	 *сохранение цены 
	 */
	public int extractionPrice(WebElement price) {
		priceNotWarranty = Integer.parseInt(productPrice.getText().trim().replaceAll("\\D", ""));
		return priceNotWarranty;
	}
	
	/**
	 *выбор гарантии 
	 */
	public ProductAndPriceInfomationPage selectWarranty(String name) {
		WebElement buttonSelect = product.findElement(By.xpath(".//select[@class='ui-input-select product-warranty__select']"));
		Select select = new Select(buttonSelect);
		select.selectByValue(name);
		elementToBeVisible(productPrice);
		priceWithWarranty = extractionPrice(productPrice);
		return this;
	}

	/**
	 *купить товар 
	 */
	public ProductAndPriceInfomationPage clickToBuy() {
		WebElement buttonBuy = product.findElement(By.xpath(".//div[@class='primary-btn']"));
		elementToBeVisible(buttonBuy);
		elementToBeClickable(buttonBuy);
		buttonBuy.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		allSum();
		return this;
	}

	/**
	 *найти товар  
	 */
	public ProductAndPriceInfomationPage productSearch(String name){
		WebElement siteSearch = navigation.findElement(By.xpath(".//input[contains(@placeholder,'Поиск по сайту')]"));
		elementToBeClickable(siteSearch);
		siteSearch.click();
		siteSearch.sendKeys(name + "\n");
		elementToBeVisible(productPrice);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		priceGame = extractionPrice(productPrice);
		return this;
	}

	/**
	 *клик по корзине 
	 */
	public BasketPage clickToCart() {
		WebElement cart = product.findElement(By.xpath(".//button[.='В корзине']"));
		elementToBeVisible(cart);
		elementToBeClickable(cart);
		cart.click();
		return app.getBasketPage();
	}

	/**
	 * проверка суммы товаров с суммой корзины
	 */
	public void allSum() {
		elementToBeVisible(linkBasket);
		int priceBasket = Integer.parseInt(linkBasket.getText().trim().replaceAll("\\D", ""));
		Assert.assertEquals("Сумма товаров не равна сумме корзины", priceGame + priceWithWarranty, priceBasket);
	}
}
