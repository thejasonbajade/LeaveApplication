insert into Department (id, DepartmentName) values (1, 'Party');
insert into Employee (id, FirstName, LastName, Email, ContactNo, EmploymentDate, Position, EmploymentStatus, RegularizationDate, isSoloParent, VLCredits, SLCredits, ELCredits, SPCredits, OffsetCredits, Department_ID) 
	values (1, 'John', 'Cena', 'youcantseeme@orangeandbronze.com', '09171234567', DATE '2011-03-12', 'vice-president', 'REGULAR', DATE '2011-07-12', 0, 15, 15, 3, 0, 0, 1);

insert into LeaveApplication (ID, StartDate, isStartHalfDay, EndDate, isEndHalfDay, DateFiled, Reason, Duration, Status, LeaveType, Employee_ID, Supervisor_ID)
	values (1, DATE '2016-7-1', 0, DATE '2016-7-4', 0, DATE '2016-6-30', 'I am Sick', 4, 'PENDING','SICK_LEAVE', 1, 2)