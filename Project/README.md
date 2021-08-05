BTC price volatility
====================

Agenda:
-------
The Bitcoin price keeps changing due to multiple factors that affects the market. 
To have a better picture, our goal is to understand its daily volatility for the year 2020.

Concept:
--------
We are maintaining 2 jobs
1. Job_1 - Reads the json data and stores into a postgres table.
2. Job_2 - Reads the postgres table data and performs transformations to calculate the standard deviation to get the price volatility

Methodology:
-----------
Assumption: To calculate the standard deviation price_close of every 6 minutes is considered.


Given that time interval between each price entry is 6 minutes, 1 day has 240 records.
1. Date column is created from "time_period_start".
2. Average of all the "price_close" is taken day wise (Grouping based on date).
3. Difference between each price_close and the average price is calculated and sqaured.
4. Sum of the squared differences is calculated day wise (Grouping based on date).
5. Calculate the variance i.e. Average of Sum of the squared differences.
6. Based on the variance, Standard Deviation is calculated which is square root if the variance.

Pre-requisites:
--------------
In order to run this spark job on your local machine, following setup is needed:
1. IntelliJ IDEA Editor (to read run the code, and change some hardcoded values if needed)
2. Git bash (to clone the project)
3. postgreSQL-42.2.4 (with user: "postgres" and password: "Carrom@123"). 
     Rest all the settings for database selection, connection URL etc are default
4. winutils.exe and set the environment variable named HADOOP_HOME to this file location
  

Steps to setup HADOOP_HOME:
---------------------------
1. Download winutils from https://github.com/steveloughran/winutils/blob/master/hadoop-2.7.1/bin/winutils.exe
   
   ![image](https://user-images.githubusercontent.com/13486101/125194098-68a6b300-e26d-11eb-9d88-353c12ef3790.png)
   
2. Create some recursive directories in C directory as  "BigDataLocalSetup" --> "hadoop" --> "bin"
3. Inside "bin" and paste the downloaded winutils.exe file
     
     ![image](https://user-images.githubusercontent.com/13486101/125194290-38134900-e26e-11eb-9899-34771695fcb1.png)
     
4. Go to environment variables and setup your HADOOP_HOME and also it to "path" variable as follows:
     
      ![image](https://user-images.githubusercontent.com/13486101/125194476-19618200-e26f-11eb-9ee6-5a6353668f01.png)
      
      ![image](https://user-images.githubusercontent.com/13486101/125194612-af95a800-e26f-11eb-8fc2-cd251aaeacdb.png)
      


Steps to clone and run the project:
-----------------------------------
1. Clone the project in your machine and open it in IntelliJ IDEA

2. Open the project on your IntelliJ by nevigating to the location same as Step 2

  ![image](https://user-images.githubusercontent.com/13486101/125193216-ef0cc600-e268-11eb-8883-4ba3fb698e82.png) 

3. After successful project cloning you will have the project structure as below:

     ![image](https://user-images.githubusercontent.com/13486101/125193351-97bb2580-e269-11eb-89d9-4e60e2c9684d.png)

4. In postgres create a table "input_btc" in default "postgres" database:
       -Click on the query tool as shown 
       -Write query to create the table 
         
         create table input_btc(
          time_period_start date,
          time_period_end date,
          time_open date,
          time_close date,
          price_open decimal,
          price_high decimal,
          price_low decimal,
          price_close decimal,
          volume_traded decimal,
          trades_count int
          );

    ![image](https://user-images.githubusercontent.com/13486101/125195433-3730e600-e273-11eb-87f2-46f7fff97542.png)

    
5. Run the 2 jobs from IntelliJ:

       1.  InputDataMain - right click and select run InputDataMain - to get the json data from URL to postgres table.
       2.  Main  - right click and select run Main - to get the standard deviation resutls.


   You can see the output data in "spark-warehouse" directory present in your project directory.
