insert into category (name, description,creation_date) values ('CATEGORY 1', 'Category 1 description', current_timestamp );
insert into category (name, description,creation_date) values ('CATEGORY 2', 'Category 2 description', current_timestamp );

insert into product (name, description, category_id, price,creation_date) values ('Apple', 'Appl IPhone 6', 1, 500.20, current_timestamp );
insert into product (name, description, category_id, price,creation_date) values ('DELL', 'Dell XPS 15', 1, 2000.20, current_timestamp );

--insert into user (name, username, password, creation_date) values ('Ahmed Rabie', 'ahmed', TO_BASE64('ahmed'), current_timestamp );
--insert into user (name, username, password, creation_date) values ('Martin', 'martin', TO_BASE64('martin'), current_timestamp );
insert into user (name, username, password, creation_date) values ('Ahmed Rabie', 'ahmed', '$2a$10$kVIZNQ9GMnuQiv86kb3rF.8Ve9/iuYyQGW3lRdp9hSBlItV2jj.Wm', current_timestamp );
insert into user (name, username, password, creation_date) values ('Martin', 'martin', '$2a$10$NuaB/gMxHNyIo1AKSBaPWexzzKoJvIBvVAJ0gWgaQnvrdmioUJV7.', current_timestamp );
insert into user (name, username, password, creation_date) values ('Salma', 'salma', '$2a$10$wbAsLEAUA68s6HMEH2jBKO1cbltJ058gVmfOqt6fRuor0s5k6pCje', current_timestamp );

--insert into user (name, username, password, creation_date) values ('Ahmed Rabie', 'ahmed', 'ahmed', current_timestamp );
--insert into user (name, username, password, creation_date) values ('Martin', 'martin', 'martin', current_timestamp );
--insert into user (name, username, password, creation_date) values ('Salma', 'salma', 'salma', current_timestamp );


insert into user_role (name, description, user_id, creation_date) values ('ADMIN', 'admin user', 1, current_timestamp );
insert into user_role (name, description, user_id, creation_date) values ('USER', 'user user', 2, current_timestamp );


