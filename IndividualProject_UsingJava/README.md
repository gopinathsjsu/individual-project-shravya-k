# individual-project-hetbrahmbhatt

## Spartan Market -> Automated Purchasing System

### Assumptions
- I have a data folder which contains the Dataset and Cards CSV
- If one wants to update either the Dataset or Cards, update the files named Dataset.csv or Cards.csv
- In that data folder, I will have the output generated
- Hard coded the values of category cap
- Did not attach the screenshot of Cards in test input 2 because the order failed.

## Running the system


- Need to have Java 8 installed in the system that is running the program.
- Run the program in an IDE lets say Eclipse or IntelliJ
- After that, to place the order one should follow the given convention -> Order,filePath
  - For example, if the relative path is data/Sample_Input_File.csv, then the command should be `"Order,data/Sample_Input_file.csv"`
- The sample o/p will be generated in data folder. The naming convention for the same are:-
- If the output is correct -> `output_timestamp.csv`
  - If the output is incorrect -> `error_timestamp.txt`
  - By default the output path will be in the data folder.

## Step wise instructions to run 

- Download and unzip the project and open it up in your workspace.
- The data folder contains necessary files such as Dataset.csv and Cards.csv
- If you want to update the database then overwrite this files
- After the database and the cards are set up in the system, run `Client.java` which contains the main file.
- User will be prompted with an i/p
- Give, "Order,data/Sample_Input_File.csv" where data/Sample_Input_File.csv is the relative path of the input file "Sample_Input_File.csv"
- The error file(.txt) or the csv file will be generated in the data folder itself with timestamp in it to track the order of files if there are multiple orders in one run-time.


## Design Pattern

- Singleton -> For InMemory Database
- Chain Of Responsibilities -> Luxury,Essential,Misc for Error Handler
- Strategy -> Deciding the strategy for output, whether it is valid or invalid

### Singleton:

- Singleton Pattern restricts the instantiation of a class to a single instance
- For this project, I used Singleton manage my inventory.
- Upon calling the database instance, i.e. in one runtime the database will only have one instance.

### Chain Of Responsibilities:

- It is a behavioral design pattern that lets you pass requests along a chain of handlers. Upon receiving a request, each handler decided either to process the request or to pass it to the next handler in the chain
- I have used Chain Of Responsibilities to determine if ordered is processed correctly or not.
- The following classes are used.

````
- Class EssentialHandler
- Class LuxuryHandler
- Class MiscHandler
- Interface OrderHandler

````

### Strategy:
- It is a behavioral software design pattern that enables selecting an algorithm at runtime
- To decide what the output is, I used strategy to decide the output.

````
- Class InvalidOutput
- Class ValidOutput
- Interface OutputHandler

````

Screenshots attached in `"test_screenshots"` Folder
  




  
   
  
