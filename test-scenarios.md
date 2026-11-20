**Registration page**
1. Successful registration with valid data 
2. All the fields are displayed and register button is enabled
3. Try registration with empty values it should reject registration
4. Phone number validation with boundary values , strings 
5. Email validation
6. Password & confirm password validation
7. Register with existing email 
8. Password strength rules

## Registration

| ID | Scenario | Type | Priority | Layer | Status | Defect ID |
|---|---|---|---|---|---|---|
| REG-01 | All fields are displayed and the Register button is enabled | Positive | P3 | UI | Not run | |
| REG-02 | Register with valid data → account is created and "Account created" success message is shown | Positive | P1 | UI | Not run | |
| REG-03 | Register with an existing email → error message is shown | Negative | P2 | UI | Not run | |
| REG-04 | Register with all fields empty → error messages are shown | Negative | P2 | UI | Not run | |
| REG-05 | Register with an invalid email → error message is shown | Negative | P2 | UI | Not run | |
| REG-06 | Register with mismatched Password and Confirm Password → error message is shown | Negative | P2 | UI | Not run | |
| REG-07 | Register with a weak password → error message is shown on Register click. Rule: at least 8 characters, 1 uppercase, 1 lowercase, 1 digit, 1 special character | Negative | P2 | UI | Not run | |
| REG-08 | Register with non-numeric characters in phone number → error message is shown | Negative | P2| UI | Not run | |
| REG-09 | Phone number boundary validation: 9 digits → error · 10 digits → accepted · 11 digits → error | Edge | P2 | UI | Not run | |

**Login Page**
1. Successful login with valid credentials
2. Username, password, login button  is displayed 
3. Try to log in with invalid credentials , system should reject
4. Try login with empty username and password combinations , system should show appropriate error messages
5. Password is masked
6. access control open dashboardUrl without logging in
7. Email in different cases , should succeed (Defect)

 ID | Scenario | Type | Priority | Layer | Status | Defect ID |Comments |
|---|---|---|---|---|---|---|--|
| LOG-01 | Email, password, login are displayed and LOGIN button is enabled | Positive | P3 | UI | Not run | | |
| LOG-02 | Login with valid credentials ,and redirected to dashboard page | Positive | P1 | UI | Not run | ||
| LOG-03 | Login with invalid credentials, shows error message | Negative | P2 | UI | Not run | ||
| LOG-04 | Login with all fields empty ,error messages are shown | Negative | P2 | UI | Not run | ||
| LOG-05 | Password is masked | Positive | P2 | UI | Not run | ||
| LOG-06 | Access control open dashboardUrl without logging in, should be redirected to login page | Negative | P2 | UI | Not run | ||
| LOG-07 |  Email in different cases , should succeed | Positive | P2 | UI | Failed | DEF_01 | System doesnt login|


**Forgot Password Page**
1. Email,password,confirm password and save button is displayed
2. Enter valid email, updated password and confirm password, new password is updated and Relogin with updated new password, it should be successful
3. Validation mismatch for password and confirm validation 
4. Try with empty fields system should reject with appropriate messages
5. Unregistered email system should reject
6. Try log in in with old password system should reject
7. Save new password with a weak password ,error message is shown on save new password click. Rule: at least 8 characters, 1 uppercase, 1 lowercase, 1 digit, 1 special character

ID | Scenario | Type | Priority | Layer | Status | Defect ID |Comments |
|---|---|---|---|---|---|---|--|
| FOR-01 | Email, password, confirm password are displayed and save new password button is enabled | Positive | P3 | UI | Not run | | |
| FOR-02 | Enter valid email, updated password and confirm password, new password is updated and Relogin with updated new password, it should be successful | Positive | P1 | UI | Not run | | |
| FOR-03 | Save new password with  mismatched Password and Confirm Password , error message is shown | Negative | P2 | UI | Not run | | |
| FOR-04 | Save new password with all fields empty,error messages are shown | Negative | P2 | UI | Not run | | |
| FOR-05 | Save new password with unregistered email, system should reject and show error message | Negative | P2 | UI | Not run | | |
| FOR-06 | Verify login with old password, should show error message | Negative | P2 | UI | Not run | | |
| FOR-07 | Save new password with a weak password ,error message is shown on save new password  click. Rule: at least 8 characters, 1 uppercase, 1 lowercase, 1 digit, 1 special character | Negative | P2 | UI | Not run | ||

**Dashboard Page**
1. Verify dashboard page displays least one item
2. Add 2 items to cart and verify cart badge shows the count
3. Click view and verify it shows details for the appropriate product
4. Search for a product(Defect)
5. Filter by price (Defect)
6. Add same product twice, badge count should not increase
7. Filter that matches nothing , shows no product 

ID | Scenario | Type | Priority | Layer | Status | Defect ID |Comments|
|---|---|---|---|---|---|---|---|
| DAS-01 | Verify dashboard page displays least one item | Positive | P3 | UI | Not run | ||
| DAS-02 | Add 2 items to cart and verify cart badge shows the count | Positive | P1 | UI | Not run | | |
| DAS-03 | Click view and verify it shows details for the appropriate product | Positive | P2 | UI | Not run | ||
| DAS-04 | Search for a product,matching products are shown | Positive | P2 | UI | Failed |DEF_02 | Search is not working |
| DAS-05 | Filter with a min and max price , only products within that range are shown. | Positive | P2 | UI | Failed  |DEF_03 | Price filter is not working|
| DAS-06 | Add same product twice, badge count should not increase | Positive | P2 | UI | Not run | |Assumption: badge counts unique products. Confirm with the product owner.|
| DAS-07 | Filter that matches nothing , shows no product  | Positive | P2 | UI | Not run | ||

**Cart Page**
1. Verify the added 2 items are shown in cart page, verify names and prices and total price
2. Remove item should remove the item and total price updated accordingly
3. Clicking buy now should take to check out page
4. Clicking continue shopping should take to dashboard page
5. Empty cart message(state)
6. Added cart items should persist after a refresh

ID | Scenario | Type | Priority | Layer | Status | Defect ID |Comments|
|---|---|---|---|---|---|---|---|
| CAR-01 | Verify the added 2 items are shown in cart page, verify names and prices and total price | Positive | P1 | UI | Not run | | |
| CAR-02 | Remove item should remove the item and total price updated accordingly | Positive | P2| UI | Not run | | |
| CAR-03 | Clicking buy now should take to check out page | Positive | P1 | UI | Not run | ||
| CAR-04 | Clicking continue shopping should take to dashboard page | Positive | P2 | UI | Not run | |  |
| CAR-05 | if no items then Empty cart message is shown| Positive | P2 | UI | Not run  || |
| CAR-06 | Added cart items should persist after a refresh | Positive | P2 | UI | Not run | ||

**Checkout Page**
1. Enter all valid data and click place order , it should take to confirm order page with details 
2. Coupon Valid
3. Coupon invalid & empty shows error message
4. Place order with required fields missing, system should block with error messages
5. Country auto suggests with invalid country
6. Prices and products match the cart
7. Payment fields: card number, CVV, expiry and name are required (defect)
8. Email is pre-filled with the logged-in user’s email

ID | Scenario | Type | Priority | Layer | Status | Defect ID |Comments|
|---|---|---|---|---|---|---|---|
| CHECK-01 | Enter all valid data and click place order button , it should take to confirm order page with details and the order confirmation message is shown| Positive | P1 | UI | Not run | | |
| CHECK-02 | Place order with required fields missing, system should block with error messages | Negative | P2| UI | Not run | | |
| CHECK-03 | Enter a country that doesn't exist (e.g. "Xyz") → no suggestions appear, and placing the order shows an error | Negative | P2 | UI | Not run | |||
| CHECK-04 | Prices and products in this page match the cart  | Positive | P2 | UI | Not run |||
| CHECK-05 | Payment fields: card number, CVV, expiry and name are empty error message is shown, card number empty → error · CVV empty → error · expiry empty → error · name empty → error | Negative | P2 | UI | Failed |DEF_05| These fields are not mandatory|
| CHECK-06 | Email is pre-filled with the logged-in user’s email | Positive | P2 | UI | Not run | ||
| CHECK-07 | Invalid or empty coupon shows error message | Negative | P2 | UI | Not run | ||
| CHECK-08 | Valid coupon should apply the discounts,apply coupon message is shown | Positive | P2 | UI | Not run | ||

**Confirm Order Page** 
1. Verify the order confirmation message 
2. Ordering 2 product creates 2 order ids

ID | Scenario | Type | Priority | Layer | Status | Defect ID |Comments|
|---|---|---|---|---|---|---|---|
| CONF-01 | “Order 2 products → 2 order IDs are shown, one per product, each unique. | Positive | P2| UI | Not run | | |

**Order History Page**
1. Confirm the order Ids appear in history page
2. View order details price & product
3. delete an order , list should update accordingly
4. New users with no order shows an empty state message
5. User A cant see user B's orders (api)

| ID | Scenario | Type | Priority | Layer | Status | Defect ID | Comments |
|---|---|---|---|---|---|---|---|
| HIST-01 | Order IDs from the confirmation page appear in Order History | Positive | P2 | UI | Not run | | |
| HIST-02 | View order details → product name and price match the order placed | Positive | P2 | UI | Not run | | |
| HIST-03 | Delete an order → it is removed from the list | Positive | P2 | UI | Not run | | |
| HIST-04 | New user with no orders → empty-state message is shown | Edge | P2 | UI | Not run | | |
| HIST-05 | Request User A's order with User B's token → access denied, no order data returned | Negative | P1 | API | Not run | | Security: exposes other users' data |

**SignOut**
1. Click sign out menu it should sign out the user and login page is displayed
2. Token is cleared from local storage after sign out
3. The back button after sign-out stays on log in page

| ID | Scenario | Type | Priority | Layer | Status | Defect ID | Comments |
|---|---|---|---|---|---|---|---|
| SIGOUT-01 | Click Sign out → user is logged out and the login page is shown | Positive | P2 | UI | Not run | | |
| SIGOUT-02 | Sign out → auth token is removed from localStorage | Positive | P2 | UI | Not run | | |
| SIGOUT-03 | After sign-out, press the browser Back button → login page stays; dashboard is not shown | Negative | P2 | UI | Not run | | |


**End-end flows**
1. Register-login-dashboard-add to cart-checkout-confirm-order history-delete order-sign out
2. Login-add to cart -logout-login-cart items are retained


## End-to-end flows

| ID | Scenario | Type | Priority | Layer | Status | Defect ID | Comments |
|---|---|---|---|---|---|---|---|
| E2E-01 | Register a new user → log in → add 2 products to cart → cart shows both with correct total → Buy now → checkout with valid data → place order → confirmation shows order IDs → order IDs appear in Order History → delete one order → sign out | Positive | P1 | UI | Not run | | Main smoke test. Needs a unique email per run (see Day 13) |
| E2E-02 | Log in → add 2 products to cart → sign out → log in again → the same 2 products are still in the cart | Positive | P2 | UI | Not run | | Assumption: the cart is saved per user. Confirm with the product owner |

