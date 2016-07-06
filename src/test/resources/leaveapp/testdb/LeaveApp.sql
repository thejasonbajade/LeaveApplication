drop table Employee if exists;
drop table LeaveApplication if exists;
drop table Department if exists;

create table Employee (id integer identity primary key, FirstName varchar(45) not null, LastName varchar(45) not null, Email varchar(100) not null, ContactNo varchar(11), EmploymentDate DATE, Position varchar(45), EmploymentStatus varchar(15), RegularizationDate DATE, isSoloParent integer, VLCredits float, SLCredits float, ELCredits float, SPCredits float, OffsetCredits float, Department_ID integer, isSupervisor boolean, isAdmin boolean, isHR boolean);

create table LeaveApplication (ID integer identity primary key, StartDate DATE, isStartHalfDay INTEGER, EndDate DATE, 
isEndHalfDay INTEGER,
DateFiled DATE, 
Reason VARCHAR(255), 
Duration FLOAT,
Status varchar(50),
LeaveType varchar(30),
Employee_ID INTEGER,
Supervisor_ID INTEGER);
	

create table Department (id integer identity primary key, DepartmentName varchar(45) not null);
