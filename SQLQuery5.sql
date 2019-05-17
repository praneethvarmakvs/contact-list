use Contacts_1
select * from [dbo].[Contacts];
DROP TABLE IF EXISTS CONTACT;
CREATE TABLE CONTACT (
    Contact_id int IDENTITY(1,1) PRIMARY KEY,
    Fname varchar(50),
    Mname varchar(50),
	Lname varchar(50)
);

DROP TABLE IF EXISTS ADDRESS;
CREATE TABLE ADDRESS (
    Address_id int IDENTITY(1,1) PRIMARY KEY,
    Contact_id int,
    Address_type varchar(50),
	Address varchar(50),
	City varchar(50),
	State varchar(50),
	Zip varchar(50),
	FOREIGN KEY (Contact_id) REFERENCES CONTACT(Contact_id)
);

DROP TABLE IF EXISTS PHONE;
CREATE TABLE PHONE (
    Phone_id int IDENTITY(1,1) PRIMARY KEY,
    Contact_id int,
    Phone_type varchar(50),
	Area_code varchar(50),
	Number varchar(50),
	FOREIGN KEY (Contact_id) REFERENCES CONTACT(Contact_id)
);

DROP TABLE IF EXISTS DATE;
CREATE TABLE DATE (
    Date_id int IDENTITY(1,1) PRIMARY KEY,
    Contact_id int,
    Date_type varchar(50),
	Date varchar(50),
	FOREIGN KEY (Contact_id) REFERENCES CONTACT(Contact_id)
);

SET IDENTITY_INSERT CONTACT ON
INSERT INTO CONTACT (Contact_id, Fname, Mname, Lname)
SELECT contact_id, first_name, middle_name, last_name
FROM [dbo].[Contacts];

INSERT INTO ADDRESS (Contact_id, Address_type, Address, City, State, Zip)
SELECT contact_id, 'Home', home_address, home_city, home_state, home_zip
FROM [dbo].[Contacts];
INSERT INTO ADDRESS (Contact_id, Address_type, Address, City, State, Zip)
SELECT contact_id, 'Work', work_address, work_city, work_state, work_zip
FROM [dbo].[Contacts];

INSERT INTO PHONE(Contact_id, Phone_type, Area_code, Number)
SELECT contact_id, 'Home', SUBSTRING(home_phone, 1, 3), home_phone
FROM [dbo].[Contacts];
INSERT INTO PHONE (Contact_id, Phone_type, Area_code, Number)
SELECT contact_id, 'Work', SUBSTRING(work_phone, 1, 3), work_phone
FROM [dbo].[Contacts];
INSERT INTO PHONE(Contact_id, Phone_type, Area_code, Number)
SELECT contact_id, 'Cell', SUBSTRING(cell_phone, 1, 3), cell_phone
FROM [dbo].[Contacts];

INSERT INTO DATE(Contact_id, Date_type,Date)
SELECT contact_id, 'Birth', birth_date
FROM [dbo].[Contacts];