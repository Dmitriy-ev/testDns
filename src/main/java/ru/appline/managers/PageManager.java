package ru.appline.managers;

import ru.appline.pages.*;

public class PageManager {

	/**
	 * Менеджр страниц
	 */
	private static PageManager pageManeger;

	/**
	 * Стартовая страница и поиск товара
	 */
	private StartPage startPage;

	/**
	 * Страница результата поиска
	 */
	private ResulSearchPage resulSearchPage;

	/**
	 * Страница с найденным товаром
	 */
	private ProductAndPriceInfomationPage productAndPriceInformationPage;

	/**
	 * Страница корзины
	 */
	private BasketPage basketPage;

	/**
	 * private конструктор (singleton паттерн)
	 */
	private PageManager() {

	}

	/**
	 * Ленивая инициализация 
	 * @return PageManeger
	 */
	public static PageManager getPageManager() {
		if(pageManeger == null)
			pageManeger = new PageManager();

		return pageManeger;
	}

	public  StartPage getStartPage() {
		if(startPage == null)
			startPage = new StartPage();

		return startPage;
	}

	public ResulSearchPage getResulSearchPage() {
		if(resulSearchPage == null)
			resulSearchPage = new ResulSearchPage();

		return resulSearchPage;
	}

	public ProductAndPriceInfomationPage getProductAndPriceInfomationPage() {
		if(productAndPriceInformationPage == null)
			productAndPriceInformationPage = new ProductAndPriceInfomationPage();

		return productAndPriceInformationPage;
	}

	public BasketPage getBasketPage() {
		if(basketPage == null)
			basketPage = new BasketPage();

		return basketPage;
	}
}
