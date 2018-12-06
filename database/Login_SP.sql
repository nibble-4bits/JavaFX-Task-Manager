-- LOGIN PROCEDURES
USE Proyecto_Final_Aspectos_TercerSemestre;

DELIMITER //
CREATE PROCEDURE authenticateUser(IN p_email VARCHAR(255), IN p_pass VARCHAR(255))
proc:BEGIN
	/* Checking whether the user exists */
    SET @userExists := IFNULL((SELECT `Email` FROM `user` WHERE `Email` LIKE p_email), 1);
    
    IF @userExists = 1
    THEN
		SELECT @userExists; -- return 1 if the user DOES NOT EXIST
        LEAVE proc;
    END IF;
    
    /* Checking whether the provided password matches the stored password */
    SET @userPassword := (SELECT `Password` FROM `user` WHERE `Email` LIKE p_email);
    IF @userPassword NOT LIKE p_pass
    THEN
		SELECT 2; -- return 2 if the passwords DO NOT MATCH
        LEAVE proc;
	ELSE
		SELECT * FROM `user` WHERE `Email` LIKE p_email; -- return the whole user if the user exists and the passwords match
        LEAVE proc;
    END IF;
    
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE getSecurityFields(IN p_email VARCHAR(255))
BEGIN
	SELECT `SecurityQuestion`, `SecurityAnswer`
    FROM `user`
    WHERE `Email` LIKE p_email;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE getPasswordByEmail(IN p_email VARCHAR(255))
BEGIN
	SELECT `Password`
    FROM `user`
    WHERE `Email` LIKE p_email;
END//
DELIMITER ;