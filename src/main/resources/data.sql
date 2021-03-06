create table fruit (
    id bigint generated by default as identity,
    price float not null,
    type varchar(255),
    weight float not null,
    primary key (id)
);



INSERT INTO FRUIT (type, price, weight) VALUES ('Grape', 3, 1000);
INSERT INTO FRUIT (type, price, weight) VALUES ('Orange', 1, 900);
INSERT INTO FRUIT (type, price, weight) VALUES ('Pineapple', 5, 300);
INSERT INTO FRUIT (type, price, weight) VALUES ('Melon', 4, 250);
INSERT INTO FRUIT (type, price, weight) VALUES ('Banana', 0.75, 30);
INSERT INTO FRUIT (type, price, weight) VALUES ('Watermelon', 6, 450);
INSERT INTO FRUIT (type, price, weight) VALUES ('Blueberry', 4.50, 100);
INSERT INTO FRUIT (type, price, weight) VALUES ('Strawberry', 2.50, 300);
INSERT INTO FRUIT (type, price, weight) VALUES ('Mango', 3.50, 100);
INSERT INTO FRUIT (type, price, weight) VALUES ('Apple', 2, 50);