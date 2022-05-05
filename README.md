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
**Resaon**<br />
- Singleton Pattern would necessiate the instantiation of a class to a single instance<br />
- In this case, the class representing Sengleton design pattern would be implemented to manage the inventory.<br />
-  Every time we place and order the item check is performed using the commong inventory which is called once.<br />
Below are the classes representing this design pattern<br /> 
Inventory Class
Class Diagram:
  <img width="1211" alt="Screen Shot 2022-05-05 at 2 54 22 PM" src="https://user-images.githubusercontent.com/12899997/167033348-99bf5e1a-5444-4cda-a9f2-b49e7f13953d.png">
 
   <br />
### 2. Strategy: Each order after its being placed but be checked to see if the order is valid or not depending on the prerequisite conditions provided on the item quantity, cap of category etc. Strategy will server the purpose of outlining a check point for clearing the order validity.<br />
**Resaon**<br />
- Strategy accomodates in choosing a logic which would layout the decisions and actions depending on them. Here, a Strategy design pattern is used to check the input order and apply the logic to verify the quantity and category of the requested order and decide accordingly to produce output.csv output.txt file.<br />
Below are the classes representing this design pattern<br /> 
- InvalidOutput Class
- ValidOutput Class
- Interface OutputHandler Class
   
**3. Chain Of Responsibilities:** Since there are three categories, having chain of responsibilities will server the purpose of simultaneously checking input validity for the categories: Luxury,Essential,Misc and acting upon corresponding error handling.<br />
**Resaon**<br />
This pattern lets you pass requests along a chain of handlers. Upon receiving a request, each handler decided either to process the request or to pass it to the next handler in the chain. In this case the responsibility of error handling has been made to pass in a chain to the list of handlers.
Below are the classes representing this design pattern<br /> 
The following classes are used.
- Class EssentialHandler
- Class LuxuryHandler
- Class MiscHandler
- Interface OrderHandler<br />
### Class Diagram
   (attached as classdiagram.pdf to the repo)


**Sample input file contents are present in input_2.csv**
![input_2file](https://user-images.githubusercontent.com/12899997/166874848-baf56853-e330-4289-adac-368aef8ba5ee.png)
<br /><br />
![errfile1](https://user-images.githubusercontent.com/12899997/166885724-dcd0856c-d6e3-412d-9925-64255a873bba.png)
![err2](https://user-images.githubusercontent.com/12899997/166885748-b4a3de50-47c5-42ef-8482-25b94fce63c1.png)

<br /><br />
**Below is the error file which is generated after input_2.csv file is fed as order inout**

<br /><br />
**input_1.csv file has a valid order items followed by sucessful order confirmation saved as output.csv**
![orderfrom_input_1csv](https://user-images.githubusercontent.com/12899997/166616398-c5eb5328-454f-4e67-8236-7803b06b42fb.png)

![ordersuccessoutput](https://user-images.githubusercontent.com/12899997/166616423-5328db40-c312-4c8a-9bec-4b7c7c421733.png)
<br /><br />
**input_2.csv has order quantity which is more than the allowed cap for essentials category.**
![Orderquantityexceedscap](https://user-images.githubusercontent.com/12899997/166616719-ad1d95eb-c4e7-40a6-9c33-2ebc6c74ce8d.png)
 <br /><br />
**Output.txt file is generated for the above order to display the error quantity and item name**
![errorfileinvalidorder](https://user-images.githubusercontent.com/12899997/166617008-79c60f8c-88ac-46b5-a471-cf43a5cb3f8a.png)
<br /><br />
**Order is placed with item of quantity more than that present in inventory**
![quantityexceedsinventory](https://user-images.githubusercontent.com/12899997/166617196-de6ca2e7-e474-44db-ad34-7f770e894a97.png)
<br /><br />
**Error message is displayed in output.txt file for the above order:**
![outputerrorquantityexceeds](https://user-images.githubusercontent.com/12899997/166617257-f69ec9ad-60ad-4821-8659-a4babaa8b2e7.png)
**Order is placed with a card number which is not present in the cards inventory(Cards.csv):**
![ordernewcard](https://user-images.githubusercontent.com/12899997/166617371-4070660d-03b0-4fed-af8c-09b9bf6f5719.png)
   <br /><br />
**After the order is placed the new card number is added to the inventory:**
![ordernewcard](https://user-images.githubusercontent.com/12899997/166617606-6988ef42-0242-42ab-999a-1c7a91237e21.png)
![newcardisaddedtoinventory](https://user-images.githubusercontent.com/12899997/166617624-d55dc2d1-0bde-4fd6-a429-eb501a94d23e.png)
 

   

