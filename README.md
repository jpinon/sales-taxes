TODO:

* Upload to github
* link to Travis CI

#Sales Taxes

This code has been written to solve an interview challenge:

Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical
products that are exempt. Import duty is an additional sales tax applicable on all imported goods
at a rate of 5%, with no exemptions.

When I purchase items I receive a receipt which lists the name of all the items and their price
(including tax), finishing with the total cost of the items, and the total amounts of sales taxes
paid. The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains
(np/100 rounded up to the nearest 0.05) amount of sales tax.

Write an application that prints out the receipt details for these shopping baskets...

**All the rights for this problem statement goes to its authors, please contact me in order to get it removed**.

### Has been tested with
* Ubuntu 18.04
* Java 8.0.181-oracle
* Apache Maven 3.5.2

### How to build
* go to project directory
* run `mvn clean package`

### How to run
* go to project directory
* run `java -jar main/target/sales-taxes-0.1.0-SNAPSHOT.jar`