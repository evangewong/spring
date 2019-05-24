DROP DATABASE IF EXISTS MemeChan;
CREATE DATABASE MemeChan;
USE MemeChan;
CREATE TABLE Products(
    
        id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        url VARCHAR(100) NOT NULL,
        title VARCHAR(30) NOT NULL,
        origin VARCHAR(30) NOT NULL,
        price INT NOT NULL,
        description VARCHAR(100) NOT NULL
    
);

CREATE TABLE Types(
        id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        name varchar(100)
);

CREATE TABLE Product_Type(
        productId INT(6),
        typeId INT(6)
);


/*Order Info*/

CREATE TABLE sale (
    id int PRIMARY KEY AUTO_INCREMENT,
    productId INT(6) NOT NULL REFERENCES Products(id),
    quantity int NOT NULL,
    shippingMethod varchar(30) NOT NULL,
    firstName varchar(50) NOT NULL, 
    lastName varchar(50) NOT NULL,
    ccId int NOT NULL REFERENCES creditcards(id),
    address varchar(200) NOT NULL,
    phoneNumber varchar(12) NOT NULL
);


CREATE TABLE creditcards (
    id int PRIMARY KEY,
    cardNumber varchar(50) NOT NULL, 
    type varchar(50) NOT NULL,
    cvv varchar(50) NOT NULL,
    expiration VARCHAR(50) NOT NULL
);


/*End Ordr info*/










INSERT INTO Products ( url, title, origin, price, description )
   VALUES
   ("https://imgflip.com/s/meme/Mocking-Spongebob.jpg", "Mocking SpongeBob", "SpongeBob Squarepants", 50, "Also known as Spongemock. Used to show a mocking tone towards a topic." );


INSERT INTO Products ( url, title, origin, price, description )
   VALUES
   ("https://imgflip.com/s/meme/Put-It-Somewhere-Else-Patrick.jpg","Push It Somewhere Else Patrick", "SpongeBob Squarepants", 50, "Also known as Pushing Patrick. Captions usually use the form We should take X and put it in Y or We should X and Y as a way to show an alternative approach to a specific problem. " );


INSERT INTO Products ( url, title, origin, price, description )
   VALUES
   ("https://i.imgflip.com/2bc2vf.jpg","Burning Paper SpongeBob", "SpongeBob Squarepants", 50, "Text goes at the top left panel of the image. Used to show an idea which is better off being destroyed by fire." );

INSERT INTO Products ( url, title, origin, price, description )
   VALUES
   ("https://i.imgflip.com/14xj5v.jpg","Caveman Spongebob", "SpongeBob Squarepants", 50, "Also known as Primitive Sponge. Used as a reaction image to awkward situations." );

INSERT INTO Products ( url, title, origin, price, description )
   VALUES
   ("https://i.imgflip.com/27ctrj.jpg","Tired Spongebob", "SpongeBob Squarepants", 50, "Paired with a caption to convey exhaustion." );

INSERT INTO Products ( url, title, origin, price, description )
   VALUES
   ("https://i.imgflip.com/2xaye3.png" ,"A Fellow Man of Culture Pooh", "Winnie the Pooh", 50, "Also known as Tuxedo Winnie the Pooh. Used to contrast a regular situation with a ~fancy~ situation." );

INSERT INTO Products ( url, title, origin, price, description )
   VALUES
   ("https://imgflip.com/s/meme/Evil-Kermit.jpg" ,"Evil Kermit", "Kermit the Frog", 50, "Used to portray a Me to Me situation, in which your other, evil self gives terrible advice." );


INSERT INTO Products ( url, title, origin, price, description )
   VALUES
   ("https://imgflip.com/s/meme/Unsettled-Tom.jpg","Unsettled Tom", "Tom and Jerry", 50, "Also known as Traumatized Tom or Concerned Tom. Used to ex[ress concern or shock." );

INSERT INTO Products ( url, title, origin, price, description )
   VALUES
   ("https://imgflip.com/s/meme/Is-This-A-Pigeon.jpg", "Is This a Pigeon?", "The Brave Fighter of Sun Fighbird", 50, " memorable quote by a character from The Brave Fighter of Sun Fighbird which is used to show complete confusion." );


INSERT INTO Products ( url, title, origin, price, description )
   VALUES
   ("https://imgflip.com/s/meme/Surprised-Pikachu.jpg", "Surprised Pikachu", "Pokemon", 50, "Used to show surprise and shock. But adorably, because Pikachu is the cutest thing ever." );


 INSERT INTO Types(name)
 	VALUES
 	("Caption");

 INSERT INTO Types(name)
 	VALUES
 	("Reaction");

  INSERT INTO Types(name)
 	VALUES
 	("Template");


 	INSERT INTO Product_Type(productId, typeId)
 		VALUES
 		(1, 1);
	INSERT INTO Product_Type(productId, typeId)
 		VALUES
 		(1, 2);


 	INSERT INTO Product_Type(productId, typeId)
 		VALUES
 		(2, 1);


	INSERT INTO Product_Type(productId, typeId)
 		VALUES
 		(3, 2);


 	INSERT INTO Product_Type(productId, typeId)
 		VALUES
 		(4, 1);


 	INSERT INTO Product_Type(productId, typeId)
 		VALUES
 		(5, 1);


 	INSERT INTO Product_Type(productId, typeId)
 		VALUES
 		(6, 3);


 	INSERT INTO Product_Type(productId, typeId)
 		VALUES
 		(7, 1);


  	INSERT INTO Product_Type(productId, typeId)
 		VALUES
 		(8, 1);

  	INSERT INTO Product_Type(productId, typeId)
 		VALUES
 		(8, 2);


  	INSERT INTO Product_Type(productId, typeId)
 		VALUES
 		(9, 1);


  	INSERT INTO Product_Type(productId, typeId)
 		VALUES
 		(10, 1);

  	INSERT INTO Product_Type(productId, typeId)
 		VALUES
 		(10, 2);