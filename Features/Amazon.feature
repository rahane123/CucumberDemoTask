Feature: Automate amazon website
@Regression
Scenario Outline: Check login functionality
Given Launch the browser and enter url
And  User click on Sign In button
When User enter "<UserID>" and User enter "<Password>"
And  Click on Sign In button
Then Verify login 

  Examples: 
  |UserID|Password|
  |9021866046|9021866046|
  
 
@Regression
Scenario: Select item and add to cart
Given User serach the item
When  User click on item
Then  Add to cart
@Regression
Scenario: Check your items add in cart
Given User click on cart button
And   Verify your item added to the cart 
Then  Check quantity as per requirement 

@Regression
Scenario: Buy Selected Item
Given User click on Procced To Buy button 
When  Verify Address Details 
Then  Click on Deliver To Address button

@Regression
Scenario: Make Payment for selected items 
Given User click on Payment method 
When  Add details for payment 
And  Click on continue button
Then Close Browser





