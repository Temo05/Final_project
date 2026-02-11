# Final Project

## Group Members
- Temuri Jibgashvili
- Giorgi Gegenava

## Test Case Distribution

### Temuri Jibgashvili (10 Tests)
**UI Tests:**
- Test Case 1: Register User
- Test Case 4: Logout User
- Test Case 6: Contact Us Form
- Test Case 21: Add review on product
- Test Case 22: Add to cart from Recommended items

**API Tests:**
- API 1: Get All Products List
- API 2: POST To All Products List
- API 8: POST To Verify Login without email parameter
- API 3: Get All Brands List
- API 9: DELETE To Verify Login

### Giorgi Gegenava (10 Tests)
**UI Tests:**
- Test Case 25: Verify Scroll Up using 'Arrow' button and Scroll Down functionality
- Test Case 11: Verify Subscription in Cart page
- Test Case 9: Search Product
- Test Case 10: Verify Subscription in home page
- Test Case 3: Login User with incorrect email and password

**API Tests:**
- API 5: POST To Search Product
- API 4: PUT To All Brands List
- API 6: POST To Search Product without search_product parameter
- API 7: POST To Verify Login with valid details
- API 11: POST To Create/Register User Account

## Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Tests by Team Member
```bash
# Temuri Jibgashvili's tests
mvn test -Dtest="TemuriJibgashviliTests,TemuriJibgashviliAPITests"

# Giorgi Gegenava's tests
mvn test -Dtest="GiorgiGegenavaTests,GiorgiGegenavaAPITests"
```

## Generating Reports

### Generate and View Allure Report
```bash
mvn allure:serve
```

### Generate Allure Report Only
```bash
mvn allure:report
```

The report will be available in `target/site/allure-maven-plugin/` directory.
