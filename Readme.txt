System prerequisites: 
Microsoft SQL server 2017 
Eclipse IDE 
Sqljdbc connector

Installation of Microsoft SQL server 2017 : 

1. Click on the link "https://www.microsoft.com/en-us/sql-server/sql-server-downloads"
2. Install the setup file, run the setup file and install SQL server with default settings. 
3. Once the sql server is installed, install the Sql Server Management studio
   by opening the link "https://docs.microsoft.com/en-us/sql/ssms/download-sql-server-management-studio-ssms?view=sql-server-2017"
4. Install the necessary setup file based on the system configuration. 


Eclipse installation: 

1. Click on the link "https://www.eclipse.org/downloads/" and download the file based on system configuration.
2. Install the setup file based on the system configuration.
3. Jdbc connector is used to connect to the database. 


Accessing the Contact List database through UI: 

1. Open the Sql Server Management Studio on the local machine and restore the "Contacts_1.bak" database in the SSMS.
2. Instead of step1, we can directly open the "SQLQuery5" file from SSMS if we have Contacts.csv data file.
2. Open Eclipse and open the project, it contains two files DB_connect.java and contact_list.java at "SQL_Project_1\contacts\src\contacts".
4. DB_connect.java includes the code required for making connections with the database.
5. Run contact_list.java file to open the windows application window that has the Contact List Homepage and follow the Quick
   User guide to interact with the database.
