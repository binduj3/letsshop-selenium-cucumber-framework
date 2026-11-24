Feature: Purchase journey
  A logged-in customer can buy products and see the order in their history.

  @e2e @smoke @E2E-01
  Scenario: Customer buys two products and sees them in order history
    Given the user is logged in
    When the user adds the following products to the cart
      |ADIDAS ORIGINAL|
      |ZARA COAT 3|
    Then the cart badge shows the number of products added
    When the user opens the cart
    And the cart shows the selected products with the correct total
    When the user checks out to "India" and places the order
    Then the order confirmation shows one order ID per product
    And the orders appear in the order history


