use OrderProcessingSystem;
create schema OrderProcessingSystem;
use OrderProcessingSystem;

CREATE TABLE `OrderProcessingSystem`.`USER` (
	`UID` INT NOT NULL AUTO_INCREMENT,
	`UName` VARCHAR(100)  NOT NULL UNIQUE,
	`Upass` VARCHAR(100)  NOT NULL,
	`Email` VARCHAR(100)  NOT NULL UNIQUE,
	`FName` VARCHAR(100) NOT NULL,
    `LName` VARCHAR(100) NOT NULL,
	`ShippingAddress` VARCHAR(100) NOT NULL,
	PRIMARY KEY (`UID`)
);

CREATE TABLE `OrderProcessingSystem`.`BOOK` (
	`ISBN` VARCHAR(100) NOT NULL,
	`Title` VARCHAR(100)  NOT NULL,
	`PID` INT NOT NULL,
	`Year` date,
	`Price` DOUBLE NOT NULL,
    `Category` VARCHAR(100),
    `Stock` INT NOT NULL,
	`Threshold` INT NOT NULL,
	PRIMARY KEY (`ISBN`)
);

create index book_title on BOOK (Title);

create index book_category on BOOK (Category);


CREATE TABLE `OrderProcessingSystem`.`BOOK_AUTHORS` (
	`ISBN` VARCHAR(100) NOT NULL,
	`Author` VARCHAR(100)  NOT NULL,
	PRIMARY KEY (`ISBN` ,`Author`),
    FOREIGN KEY (`ISBN`) REFERENCES BOOK(`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `OrderProcessingSystem`.`BOOK_PUBLISHER` (
	`PID` INT NOT NULL AUTO_INCREMENT,
	`FName` VARCHAR(100)  NOT NULL,
	`LName` VARCHAR(100)  NOT NULL,
	`Address` VARCHAR(100),
	`Phone` VARCHAR(100),
	PRIMARY KEY (`PID`)
);

ALTER TABLE BOOK ADD fOREIGN KEY (`PID`) REFERENCES BOOK_PUBLISHER(`PID`);

CREATE TABLE `OrderProcessingSystem`.`BOOK_ORDERS` (
	`ISBN` VARCHAR(100) NOT NULL,
    `Quantity` INT NOT NULL,
	PRIMARY KEY (`ISBN`),
	FOREIGN KEY (`ISBN`) REFERENCES BOOK(`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `OrderProcessingSystem`.`MANAGER` (
	`MID` INT NOT NULL,
	PRIMARY KEY (`MID`),
	FOREIGN KEY (`MID`) REFERENCES `USER`(`UID`) ON DELETE CASCADE ON UPDATE CASCADE
);
select MID from manager where MID = 2;

CREATE TABLE `OrderProcessingSystem`.`BOOKS_SOLD` (
	`UID` INT NOT NULL,
	`ISBN` VARCHAR(100)  NOT NULL,
	`Date` Date NOT NULL,
    `Price` Double NOT NULL,
    `Quantity` INT NOT NULL,
	PRIMARY KEY (`ISBN` , `UID`),
	FOREIGN KEY (`ISBN`) REFERENCES BOOK(`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`UID`) REFERENCES `USER`(`UID`) ON DELETE CASCADE ON UPDATE CASCADE
);



SET SQL_SAFE_UPDATES = 0;

use OrderProcessingSystem;

DELIMITER $$

CREATE
	TRIGGER `book_after_update` AFTER update 
	ON `BOOK` 
	FOR EACH ROW BEGIN
	
		IF NEW.Stock<NEW.Threshold THEN
			SET @addQuantity = NEW.Threshold;
            SET @ISBN = NEW.ISBN;
            SET @PID = NEW.PID;
            
            INSERT INTO BOOK_ORDERS VALUES (@ISBN, @PID,@addQuantity);
		
		END IF;
    
    END$$
DELIMITER ;

DELIMITER $$

CREATE
	TRIGGER `book_after_insert` AFTER insert 
	ON `BOOK` 
	FOR EACH ROW BEGIN
	
		IF NEW.Stock<NEW.Threshold THEN
			SET @addQuantity = NEW.Threshold;
            SET @ISBN = NEW.ISBN;
            SET @PID = NEW.PID;
            
            INSERT INTO BOOK_ORDERS VALUES (@ISBN, @PID,@addQuantity);
		
		END IF;
    
    END$$
DELIMITER ;





DELIMITER $$

CREATE
	TRIGGER `book_order_before_delete` before delete 
	ON `BOOK_ORDERS` 
	FOR EACH ROW BEGIN
	
            Update BOOK
            SET Stock = Stock+OLD.Quantity
            where ISBN = OLD.ISBN;

    
    END$$
DELIMITER ;




delimiter $$

create trigger book_quantity_nonnegative before update on BOOK
for each row
begin
    if (new.Stock < 0) then
        signal sqlstate '45000' set message_text = 'stock cant be negative';
    end if;
end $$

delimiter ;

