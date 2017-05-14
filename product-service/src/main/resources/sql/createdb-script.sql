CREATE TABLE category (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  creation_date datetime DEFAULT NULL,
  description varchar(256) DEFAULT NULL,
  last_updated_date datetime DEFAULT NULL,
  name varchar(128) DEFAULT NULL,
  parent_category_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (parent_category_id) REFERENCES category (id)
);

CREATE TABLE product (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  creation_date datetime DEFAULT NULL,
  description varchar(256) DEFAULT NULL,
  last_updated_date datetime DEFAULT NULL,
  name varchar(128) DEFAULT NULL,
  on_sale tinyint(1) DEFAULT '0',
  price double NOT NULL,
  sale_price double DEFAULT NULL,
  category_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (category_id) REFERENCES category (id)
);