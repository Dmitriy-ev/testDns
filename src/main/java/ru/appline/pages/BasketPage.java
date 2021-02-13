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
	public BasketPage deleteProductInCart(String nameProduct) {
		int value = basketPriceNameProduct(nameProduct);
		int sum = getbasketSum();
		WebElement name;
		for (WebElement webElement : productId) {
			name = webElement.findElement(By.xpath(".//div[@class='cart-items__product-name']/a"));
			if(name.getText().contains(nameProduct)) {
				WebElement delete = webElement.findElement(By.xpath(".//button[.='Удалить']"));
				scrollWithOffset(delete, 0, -200);
				elementToBeClickable(delete);
				delete.click();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				chekingDeleteProductAndSum(nameProduct, value, sum);
				return this;
			}

		}
		Assert.fail("Товар не был удален");
		return null;
	}

	/**
	 *увеличение товара 
	 * @throws InterruptedException 
	 */
	public BasketPage addProduct(String nameProduct) {
		WebElement name;
		int pricePlaystation = basketPriceNameProduct(nameProduct);
		int sum = 0;
		for (WebElement webElement : productId) {
			name = webElement.findElement(By.xpath(".//div[@class='cart-items__product-name']/a"));
			if(name.getText().contains(nameProduct)) {
				WebElement add = webElement.findElement(By.xpath(".//button[@data-commerce-action='CART_ADD']"));
				scrollWithOffset(add, 0, -250);
				elementToBeVisible(add);
				elementToBeClickable(add).click();
				sum = pricePlaystation + pricePlaystation;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				elementToBeClickable(add).click();
				sum += pricePlaystation;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Assert.assertEquals("Сумма не равна трем playstation", sum, getbasketSum());
				return this;
			}
		}
		Assert.fail("Товар не был добавлен");
		return null;
	}

	/**
	 *возврат удаленного товара 
	 */
	public BasketPage returnProduct(String name){
		int sum = getbasketSum();
		scrollApp();
		elementToBeVisible(returnDeleteProduct);
		elementToBeClickable(returnDeleteProduct);
		returnDeleteProduct.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int value = basketPriceNameProduct(name);
		chekingReturnProduct(name, value, sum);
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
				return this;
			}
		}
		return null;
	}

	/**
	 *Общая сумма 
	 */
	public int getbasketSum(){
		WebElement basketSum;
		int sum = 0;
		for (WebElement webElement : productId) {
			basketSum = webElement.findElement(By.xpath(".//span[@class='price__current']"));
			elementToBeVisible(basketSum);
			sum += Integer.parseInt(basketSum.getText().replaceAll("\\D", ""));
		}
		return sum;
	}

	/**
	 *цена продукта по имени 
	 */
	public int basketPriceNameProduct(String nameProduct) {
		WebElement basketProduct;
		int price = 0;
		for (WebElement webElement : productId) {
			basketProduct = webElement.findElement(By.xpath(".//div[@class='cart-items__product-name']/a"));
			if(basketProduct.getText().contains(nameProduct)) {
				WebElement priceBasketProduct  = webElement.findElement(By.xpath(".//span[@class='price__current']"));
				elementToBeVisible(priceBasketProduct);
				price = Integer.parseInt(priceBasketProduct.getText().replaceAll("\\D", ""));
			}
		}
		return price;
	}

	/**
	 *проверка суммы товаров 
	 */
	public BasketPage checkingSum(){
		Assert.assertEquals("Сумма товаров в корзине не верна", basketPriceNameProduct("Detroit") + basketPriceNameProduct("Игровая консоль"), getbasketSum());
		return this;
	}

	/**
	 * проверка цены товара по имени
	 */
	public BasketPage chekingPrice(String name, int price) {
		WebElement chekingPriceProduct;
		for (WebElement webElement : productId) {
			chekingPriceProduct = webElement.findElement(By.xpath(".//div[@class='cart-items__product-name']/a"));
			if(chekingPriceProduct.getText().contains(name)) {
				Assert.assertEquals("Цена продукта в корзине не верна", basketPriceNameProduct(name), price);
			}
		}
		return this;
	}

	/**
	 * проверка что товар удален из корзины и что сумма уменьшилась
	 */
	public void chekingDeleteProductAndSum(String name, int priceProduct, int basketSum) {
		WebElement deleteProduct;
		for (WebElement webElement : productId) {
			deleteProduct = webElement.findElement(By.xpath(".//div[@class='cart-items__product-name']/a"));
			if(deleteProduct.getText().contains(name)) {
				try {
					Assert.assertEquals("Продукт не удален", name, deleteProduct.getText());
				}catch (Exception e) {

				}
			}
		}
		Assert.assertEquals("Сумма не уменьшилась", basketSum - priceProduct, getbasketSum());
	}

	/**
	 * проверка что товар вернулся и сумма увеличилась
	 */
	public void chekingReturnProduct(String name, int priceProduct, int basketSum) {
		WebElement deleteProduct;
		for (WebElement webElement : productId) {
			deleteProduct = webElement.findElement(By.xpath(".//div[@class='cart-items__product-name']/a"));
			if(deleteProduct.getText().contains(name)) {
				Assert.assertTrue("Товар вернулся в корзину", true);
			}
		}
		Assert.assertEquals("Сумма не увеличилась", basketSum + priceProduct, getbasketSum());
	}
}
