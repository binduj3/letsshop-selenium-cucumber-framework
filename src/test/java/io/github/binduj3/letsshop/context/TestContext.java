package io.github.binduj3.letsshop.context;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Data shared between step classes within ONE scenario.
 * PicoContainer creates a new TestContext per scenario, so nothing leaks
 * between scenarios or threads. No static fields here.
 */
public class TestContext {

    private final List<String> orderIds = new ArrayList<>();
    private String registeredEmail;
    public final Map<String, BigDecimal> productPrices = new LinkedHashMap<>();

    // --- order IDs (set on the confirmation page, checked in Order History)
    public void addOrderIds(List<String> ids) {
        orderIds.addAll(ids);
    }

    public List<String> getOrderIds() {
        return List.copyOf(orderIds);   // read-only copy: callers can't change the list by accident
    }

    // --- email of the user registered in this scenario
    public void setRegisteredEmail(String email) {
        this.registeredEmail = email;
    }

    public String getRegisteredEmail() {
        if (registeredEmail == null) {
            throw new IllegalStateException("No email registered yet in this scenario");
        }
        return registeredEmail;
    }

    public void addProduct(String productName, BigDecimal price) {
        productPrices.put(productName, price);
        System.out.println(productPrices);
    }

    public List<String> getProducts() {
        return List.copyOf(productPrices.keySet());
    }

    public BigDecimal getPrice(String productName) {
        BigDecimal price = productPrices.get(productName);
        if (price == null) {
            throw new IllegalStateException("No price saved for '" + productName + "'");
        }
        return price;
    }

    public BigDecimal getExpectedTotal() {
        return productPrices.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}