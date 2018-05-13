use OrderProcessingSystem;

/* Adding users*/
delete from USER;
insert into USER
values(1,'youssef_darwish','25jan','youssef@gmail.com','youssef','darwish','kafr abdou');
insert into USER
values(2,'aya_aly','12345','aya@gmail.com','aya','aly','miami');
insert into USER
values(3,'amira_haiba','amira96','amira96@gmail.com','amira','nabil','Raml station');
insert into USER
values(4,'ahmed_hamdy','10024','hamdy10024@gmail.com','ahmed','shafeek','smouha');
insert into USER
values(5,'ahmed_walid','liverpool','a_walid@gmail.com','ahmed','walid','victoria');
insert into USER
values(6,'omar_shawky','codeforces','elshaba7@gmail.com','omar','shawky','roshdy');
insert into USER
values(7,'anas_harby','marquess','anas_commrad@gmail.com','Anas','Harby','Glem');
insert into USER
values(8,'aboelhamd','brock_lesnar','abolehamd@gmail.com','aboelhamd','aly','bakoos');
insert into USER
values(9,'hesham_elsawaf','exrepresentative','sawaf@gmail.com','hesham','sawaf','agamy');
insert into USER
values(10,'fares_mehanna','macpro','mehanna@gmail.com','fares','osman','damnhour');
select * from USER;

set FOREIGN_KEY_CHECKS=0;

delete from BOOK_PUBLISHER;
/*Adding publishers */

insert into BOOK_PUBLISHER
values (1,'ahmed','mohamed','zahran','0101223456');
insert into BOOK_PUBLISHER
values (2,'hamada','zaki','ibrahimya','0121723336');
insert into BOOK_PUBLISHER
values (3,'gamal','mohamed','smouha','0101222222');
insert into BOOK_PUBLISHER
values (4,'khaled','mohamed','smouha el moderya','0101223444');
insert into BOOK_PUBLISHER
values (5,'mahmoud','mohamed','elasafra','0101223555');
insert into BOOK_PUBLISHER
values (6,'hussein','mohamed','abou eir','0101223777');
select * from BOOK_PUBLISHER;


/*I am the sudo xD*/
insert into MANAGER values(1);

select * from USER join MANAGER
on USER.UID=MANAGER.MID;

LOAD DATA local infile '/home/youssef/Desktop/DB/cmake-build-debug/BOOK.txt' INTO TABLE BOOK FIELDS TERMINATED BY ','  LINES STARTING BY 'aaa'








