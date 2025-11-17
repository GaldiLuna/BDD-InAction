Scenario: The Frequent Flyers site encourages members to apply for the new credit card
  Given Joe is a Flying High Frequent Flyer
    And Joe has a valid LDAP account
    And Joe has application permissions for application X
  When Joe logs on
    And Joe views his account home page
  Then Joe should be given access to the application
    And he should be able to apply for a Flying High Credit Card

Scenario: Joe is eligible for a Flying High Credit Card
Given Joe is a Flying High Frequent Flyer eligible for automatic Credit Card approval
When Joe applies for a Flying High Credit Card
Then Joe should be informed that his application was successful
And Joe should receive a confirmation email
And Joe's application should be queued for approval

Scenario: Joe is not eligible for a Flying High Credit Card
Given Joe is a Flying High Frequent Flyer who is not eligible for automatic Credit Card approval
When Joe applies for a Flying High Credit Card
Then Joe should be informed that Flying High will be in touch
And Joe's application should be queued for manual processing

Scenario Outline: Credit card eligibility based on income
Given Joe is a regular Frequent Flyer earning <income>
When Joe applies for a Flying High Credit Card
Then his application should be <result>
Example:
| income | result    | notes                |
| 120000 | automatic | Income >= 120000     |
| 100000 | manual    |                      |
| 49999  | declined  | Income < 50000       |

Scenario Outline: Credit card eligibility based on income and status
Given Joe is a <status> Frequent Flyer earning <income>
When Joe applies for a Flying High Credit Card
Then his application should be <result>
Example:
| status | income | result    | notes                                |
| gold   | 80000  | automatic | Automatically approved over $80000   |
| gold   | 79999  | manual    |                                      |
| gold   | 49999  | declined  |                                      |
| silver | 100000 | automatic | Automatically approved over $100000  |
| silver | 99999  | manual    |                                      |
| bronze | 110000 | automatic | Automatically approved over $110000  |
| bronze | 109999 | manual    |                                      |