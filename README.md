# individual-project-shravya-k

## Shop Hash 
### System Introduction:
1. ShopHash is a shopping application which allows users to place orders and get the order information. This application is built in Java using HashMaps to have an in built static database.
2. User can add all the items they wish to order into an input file of `csv` format and feed it to the application to place an order. Once the order is placed, depending on the item details(quantity requested) the order is validated and an output file is generated. If the order placed has invalid, output_\*.txt file is generated. Whereas if the order contains valid items, output_\*.csv file is generated. <br />
**Order Input File Path:** IndividualProject_UsingJava/data/input_\*.csv<br />
**Order confirmation Output File Path:** IndividualProject_UsingJava/data/output_\*.csv
3. Each order requires payment card details which must be provided in the input file along with the order quantity and item name. If the card is present in the internal database then order will be processed without additional steps. However, if the card is not present card number is added to Cards.csv file. <br />
**Payment Card details File Path**: IndividualProject_UsingJava/data/Cards.csv
4. Each order item's quantity is checked against the available quantity in the inventory and if the quantity requsted is more than available, the order is categorised as invalid.
**The item details present in inventory can be found at:** IndividualProject_UsingJava/data/Dataset.csv

### Steps for running the application:
1. Need to have Java 8 installed in the system that is running the program.
2. Run the program in an IDE lets say Eclipse or IntelliJ.
3. Open the file *IndividualProject_UsingJava/src/Billing.Java* and Run it on Eclipse.
4. Enter Order input file: data/InputFileName.csv
   <Enter File path here>
5. Choose a number between <1,2,3> to pick an order
6. Message is displayed depending on successful or unsuccessful order
  
### Choice of Design Pattern:
**1. Singleton:** Since Singleton serves the purpose when the task can be done using a single class, it is chosen for the implementation of static InMemory Database which is common and single across the application.<br />

**2. Chain Of Responsibilities:** Since there are three categories, having chain of responsibilities will server the purpose of simultaneously checking input validity for the categories: Luxury,Essential,Misc and acting upon corresponding error handling.<br />
**Strategy:** Each order after its being placed but be checked to see if the order is valid or not depending on the prerequisite conditions provided on the item quantity, cap of category etc. Strategy will server the purpose of outlining a check point for clearing the order validity.<br />
  
  
  
