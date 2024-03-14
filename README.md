# StudentManagement
Description

This project is to connect andinteract with the PostgreSQL database named "StudentManagement". It includes functionalities to retrive all
students, add students to the database, update a student's email address and deleting students.

Author: Mike Fang(#101196805)

Environment requirements
- Java JDK 8 or later.
- PostgreSQL 10 or later.
- Maven for dependency management.
- Java IDE (IntelliJ Recommended)

Instruction
- Have all the apps required installed.

- Create a database named "StudentManagement", add following table to the database

		CREATE TABLE students (
  			student_id SERIAL PRIMARY KEY,
  			first_name TEXT NOT NULL,
			last_name TEXT NOT NULL,
			email TEXT NOT NULL UNIQUE,
			enrollment_date DATE
		);

- Open StudentManagement folder using IntelliJ, open StudentMgmt.java.

- In main function, create a new instance of StudentMgmt with the database's port number. The code for creating StudentMgmt is given in line 136, replace 
  the parameter with your database's port number.

		StudentMgmt sm = new StudentMgmt(3005);

- Add corresponding functions to main and run the file:
	- getAllStudents(): Retrieves and displays all records from the students table.
  
	- addStudent(first_name, last_name, email, enrollment_date): Inserts a new student record into the students table.
  
	- updateStudentEmail(student_id, new_email): Updates the email address for a student with the specified student_id.
  
	- deleteStudent(student_id): Deletes the record of the student with the specified student_id.
