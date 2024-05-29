package com.tfg.supercomparator.infrastructure.adapter.hipercor;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tfg.supercomparator.domain.hipercor.HipercorProduct;
import com.tfg.supercomparator.infrastructure.scraper.DriverSupplier;
import com.tfg.supercomparator.infrastructure.scraper.hipercor.HipercorScraper;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class HipercorScraperImpl extends DriverSupplier implements HipercorScraper {

    private static final int TAMAÑO_IMAGEN = 325;
    private WebDriver driver = super.getDriver();

    private static double getPrecioPorUnidad(JsonObject price) {
        JsonObject pumPrice = price.getAsJsonObject("pum_price_v2");
        int intPrice = pumPrice.getAsJsonPrimitive("integerPart").getAsInt();
        int decimalPrice = pumPrice.getAsJsonPrimitive("decimalPart").getAsInt();
        return Double.parseDouble(intPrice + "." + decimalPrice);
    }

    private static String getImageUrl(JsonObject product) {
        String imageUrl = product.getAsJsonObject("media").get("thumbnail_url").getAsString();
        String productUrl = product.get("pdp_url").getAsString();
        productUrl = "https://www.hipercor.es" + productUrl;
        if (!imageUrl.isBlank()) {
            imageUrl = "https:" + imageUrl;
            imageUrl = imageUrl.replace("40x40", TAMAÑO_IMAGEN + "x" + TAMAÑO_IMAGEN);
        }
        return imageUrl;
    }

    private static String getProductUrl(JsonObject product) {
        String productUrl = product.get("pdp_url").getAsString();
        return "https://www.hipercor.es" + productUrl;
    }

    private static String getPrecioPorUnidadText(JsonObject price, double precioPorUnidad) {
        JsonObject pumPrice = price.getAsJsonObject("pum_price_v2");
        String priceType = pumPrice.getAsJsonPrimitive("type").getAsString().trim();
        return precioPorUnidad + " €" + priceType;
    }

    public List<HipercorProduct> searchProduct(String productQuery) {
        List<HipercorProduct> hipercorProducts = new ArrayList<>();

        driver.get("https://www.hipercor.es/alimentacion/api/catalog/supermercado/type_ahead/?question=" + productQuery + "&scope=supermarket&center=010MOH&results=24");

        String pageSource = driver.getPageSource();

        Pattern jsonPattern = Pattern.compile("<div id=\"json\">([^<]*)</div>");

        Matcher matcher = jsonPattern.matcher(pageSource);

        String jsonString = null;
        if (matcher.find()) {
            jsonString = matcher.group(1);
        }

        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

        JsonObject catalogResult = jsonObject.getAsJsonObject("catalog_result");
        JsonObject productsList = catalogResult.getAsJsonObject("products_list");
        JsonArray productObject = productsList.getAsJsonArray("items");

        for (JsonElement products : productObject) {
            JsonObject product = products.getAsJsonObject().getAsJsonObject("product");
            JsonObject price = product.getAsJsonObject("price");

            double precioPorUnidad = getPrecioPorUnidad(price);

            String imageUrl = getImageUrl(product);

            String productUrl = getProductUrl(product);

            String nombre = product.get("name").getAsString();

            double precio = price.get("seo_price").getAsDouble();
            Double precioSinOferta = price.has("seo_original_price") ? price.get("seo_original_price").getAsDouble() : null;

            String precioPorUnidadText = getPrecioPorUnidadText(price, precioPorUnidad);

            hipercorProducts.add(
                    new HipercorProduct(
                            nombre,
                            precio,
                            precioPorUnidad,
                            precioPorUnidadText,
                            precioSinOferta,
                            imageUrl,
                            productUrl
                    )
            );

        }
        return hipercorProducts;
    }
}
