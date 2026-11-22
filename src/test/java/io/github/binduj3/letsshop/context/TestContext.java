package io.github.binduj3.letsshop.context;

import java.util.ArrayList;
import java.util.List;

/**
 * Data shared between step classes within ONE scenario.
 * PicoContainer creates a new TestContext per scenario, so nothing leaks
 * between scenarios or threads. No static fields here.
 */
public class TestContext {

    private final List<String> orderIds = new ArrayList<>();
    private String registeredEmail;

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
}