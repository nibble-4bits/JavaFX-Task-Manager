CREATE DATABASE Proyecto_Final_Aspectos_TercerSemestre;

USE Proyecto_Final_Aspectos_TercerSemestre;

CREATE TABLE `proyecto_final_aspectos_tercersemestre`.`department` (
  `IdDepartment` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`IdDepartment`)
);

CREATE TABLE `proyecto_final_aspectos_tercersemestre`.`user` (
  `IdUser` INT NOT NULL AUTO_INCREMENT,
  `IdDepartment` INT NOT NULL,
  `Email` VARCHAR(255) NOT NULL,
  `Name` VARCHAR(255) NOT NULL,
  `LastName` VARCHAR(255) NOT NULL,
  `DateOfBirth` DATE NOT NULL,
  `HireDate` DATE NOT NULL,
  `Password` VARCHAR(255) NOT NULL,
  `SecurityQuestion` VARCHAR(255) NOT NULL,
  `SecurityAnswer` VARCHAR(255) NOT NULL,
  `Type` TINYINT NOT NULL DEFAULT 1, -- 0 admin, 1 employee
  PRIMARY KEY (`IdUser`),
  FOREIGN KEY (`IdDepartment`) REFERENCES `department` (`IdDepartment`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE
);

CREATE TABLE `proyecto_final_aspectos_tercersemestre`.`task` (
  `IdTask` INT NOT NULL AUTO_INCREMENT,
  `IdUser` INT NOT NULL,
  `Title` VARCHAR(255) NOT NULL,
  `Description` VARCHAR(255) NOT NULL,
  `Status` TINYINT NOT NULL DEFAULT 0, -- 0 pending, 1 WIP, 2 finished
  FOREIGN KEY (`IdUser`) REFERENCES `user` (`IdUser`),
  PRIMARY KEY (`IdTask`)
);

CREATE TABLE `proyecto_final_aspectos_tercersemestre`.`token` (
  `IdToken` INT NOT NULL AUTO_INCREMENT,
  `Token` CHAR(5) NOT NULL,
  `Email` VARCHAR(255) NOT NULL,
  `ExpirationDate` TIMESTAMP NOT NULL,
  PRIMARY KEY (`IdToken`)
);