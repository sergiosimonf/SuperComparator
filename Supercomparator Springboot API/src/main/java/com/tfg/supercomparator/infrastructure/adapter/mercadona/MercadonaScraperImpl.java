package com.tfg.supercomparator.infrastructure.adapter.mercadona;

import com.tfg.supercomparator.domain.mercadona.MercadonaProduct;
import com.tfg.supercomparator.infrastructure.scraper.DriverSupplier;
import com.tfg.supercomparator.infrastructure.scraper.mercadona.MercadonaScraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MercadonaScraperImpl extends DriverSupplier implements MercadonaScraper {

    private String name = "";

    private List<MercadonaProduct> mercadonaProductList = new ArrayList<>();

    public List<MercadonaProduct> searchProduct(String productQuery) {
        String pageSource;

        do {
            FirefoxDriver driver = super.getDriver();

            driver.get("https://tienda.mercadona.es/search-results?query=" + productQuery);

            pageSource = driver.getPageSource();

            driver.quit();

            Document document = Jsoup.parse(pageSource);

            Elements products = document.select(".product-cell");

            for (Element product : products) {
                name = product.select(".product-cell__description-name").text();

                String price = product.select(".product-price__unit-price").text();
                String priceNoDiscount = product.select(".product-price__previous-unit-price").text();
                String priceDiscount = product.select(".product-price__unit-price--discount").text();

                String quantity = product.select(".product-price__extra-price").text();
                String priceQuantity = product.select(".product-format").text();

                String imageUrl = product.select(".product-cell__image-wrapper img").attr("src");

                mercadonaProductList.add(
                        new MercadonaProduct(
                                name,
                                priceFormarter(price),
                                priceFormarter(priceQuantity),
                                priceQuantity,
                                priceFormarter(priceDiscount),
                                priceFormarter(priceNoDiscount),
                                imageUrl
                        )
                );
            }

        } while (pageSource.isEmpty() || name.isEmpty());

        return mercadonaProductList;
    }

    private Double priceFormarter(String price) {
        String[] priceParts = price.replace("€", "").replace(",", ".").trim().split("\\s+");
        try {
            return Double.parseDouble(priceParts[0]);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}