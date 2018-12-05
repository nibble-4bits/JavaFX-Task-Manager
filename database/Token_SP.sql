-- TOKEN PROCEDURES
USE Proyecto_Final_Aspectos_TercerSemestre;

DELIMITER //
CREATE PROCEDURE saveToken(IN tokChars CHAR(5), IN mail VARCHAR(255))
BEGIN
	INSERT INTO `token`(`Token`, `Email`, `ExpirationDate`)
    VALUES(tokChars, mail, TIMESTAMPADD(MINUTE, 1, CURRENT_TIMESTAMP()));
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE checkIfValid(IN tokChars CHAR(5))
BEGIN
	DECLARE expDate TIMESTAMP;
    
    SET expDate = IFNULL((SELECT `ExpirationDate` FROM `token` WHERE `token`.`Token` LIKE tokChars), '1970-01-01 00:00:01');
    
	IF expDate > CURRENT_TIMESTAMP()
	THEN
		SELECT `Email` FROM `token` WHERE `token`.`Token` LIKE tokChars; -- token valid
	ELSE
		SELECT 'INVALID'; -- token expired and invalid
	END IF;
END//
DELIMITER ;