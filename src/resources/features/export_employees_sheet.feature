Scenario: Export staff birthdays as an Excel spreadsheet
Given the following staff members:
| Name | Birthday     |
| Jane | 10-Mar-1980  |
| Jill | 18-Dec-1965  |
| Jack | 20-Dec-1965  |
| Joan | 20-Nov-1991  |
And today is 16-Dec-2013
When I export this week's birthday list
Then I should obtain a spreadsheet containing the following:
| Name | Birthday     |
| Jill | 18-Dec-1965  |
| Jack | 20-Dec-1965  |