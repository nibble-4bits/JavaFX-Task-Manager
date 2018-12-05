-- TASK PROCEDURES
USE Proyecto_Final_Aspectos_TercerSemestre;

DELIMITER //
CREATE PROCEDURE getTasksByUserEmail(IN p_email VARCHAR(255))
BEGIN
	SELECT `task`.* 
    FROM `task` INNER JOIN `user`
    ON `task`.IdUser = `user`.IdUser
    WHERE `user`.Email LIKE p_email;
END//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE advanceTask(IN id INT)
BEGIN
	DECLARE taskStatus INT;
    DECLARE nextStatus INT;
    
	SET taskStatus = (SELECT `Status` FROM `task` WHERE `IdTask` = 3 LIMIT 1);
    SELECT taskStatus;
	
    SET nextStatus = 2;
    
	IF taskStatus = 0
	THEN
		SET nextStatus = 1;
	ELSEIF taskStatus = 1
    THEN
		SET nextStatus = 2;
    END IF;

	UPDATE `task`
    SET `Status` = nextStatus
	WHERE `IdTask` = id;
END//
DELIMITER ;

DROP PROCEDURE advanceTask;
CALL getTasksByUserEmail('andiyasha16@gmail.com');
CALL advanceTask(3);