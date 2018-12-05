-- ADMIN PROCEDURES
USE Proyecto_Final_Aspectos_TercerSemestre;

DELIMITER //
CREATE PROCEDURE getAllEmployees()
BEGIN
	SELECT `user`.*, `department`.`Name` AS DepartmentName
	FROM `user` INNER JOIN `department`
	ON `user`.IdDepartment = `department`.IdDepartment
    WHERE `user`.`Type` = 1;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE getAllDepartments()
BEGIN
	SELECT *
    FROM `department`;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE addEmployee(IN departmentid INT, IN email VARCHAR(255), IN p_name VARCHAR(255), IN lastname VARCHAR(255), IN dob DATE, IN hiredate DATE, IN pass VARCHAR(255), IN securityquestion VARCHAR(255), IN securityanswer VARCHAR(255))
BEGIN
	INSERT INTO `user` 
	(`IdDepartment`, `Email`, `Name`, `LastName`, `DateOfBirth`, `HireDate`, `Password`, `SecurityQuestion`, `SecurityAnswer`, `Type`) 
	VALUES (departmentid, email, p_name, lastname, dob, 
	hiredate, pass, securityquestion, securityanswer, 1);
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE addDepartment(IN d_name VARCHAR(255))
BEGIN
	INSERT INTO `department`(`Name`) 
    VALUES (d_name);
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE updateEmployee(IN id INT, IN departmentid INT, IN email VARCHAR(255), IN p_name VARCHAR(255), IN lastname VARCHAR(255), IN dob DATE, IN hiredate DATE, IN pass VARCHAR(255), IN securityquestion VARCHAR(255), IN securityanswer VARCHAR(255))
BEGIN
	UPDATE `user`
    SET `IdDepartment` = departmentid,
		`Email` = email, 
        `Name` = p_name, 
        `LastName` = lastname, 
        `DateOfBirth` = dob, 
        `HireDate` = hiredate, 
        `Password` = pass, 
        `SecurityQuestion` = securityquestion, 
        `SecurityAnswer` = securityanswer 
	WHERE `IdUser` = id;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE assignTask(IN userid INT, IN title VARCHAR(255), IN descrip VARCHAR(255))
BEGIN
	INSERT INTO `task`(`IdUser`, `Title`, `Description`, `Status`)
    VALUES(userid, title, descrip, 0);
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE getUserTasksByStatus(IN userid INT)
BEGIN
	SELECT `Status`, COUNT(`Status`) AS `Count`
	FROM `task`
	WHERE `IdUser` = userid
	GROUP BY `Status`;
END//
DELIMITER ;