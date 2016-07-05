drop table Employee if exists;
create table Employee (ID integer identity primary key, FirstName varchar(255), LastName varchar(255));

/*drop table LeaveHistory if exists;
CREATE TABLE LeaveHistory (
  ID integer identity primary key,
  Start DATE NOT NULL,
  isStartHalfDay TINYINT(1) NULL,
  End DATE NOT NULL,
  isEndHalfDay TINYINT(1) NULL,
  Duration FLOAT NOT NULL,
  DateFiled DATETIME NOT NULL,
  Reason TEXT NOT NULL,
  Status ENUM('Pending', 'SupervisorApproved', 'HRApproved', 'Cancelled', 'Not Taken') NOT NULL,
  LeaveType ENUM('SL', 'VACATION_LEAVE', 'EL', 'SPL', 'OFFSET') NOT NULL,
  `Employee_ID` INT NOT NULL);*/
  