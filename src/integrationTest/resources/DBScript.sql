DROP SCHEMA IF EXISTS `clothes-shopTest`;

CREATE SCHEMA `clothes-shopTest`;

use `clothes-shopTest`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `login` varchar(70) NOT NULL UNIQUE,
  `password` varchar(70) NOT NULL,
  `coins` decimal(11, 2) DEFAULT 0,
  `admin_access` boolean DEFAULT FALSE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `total_price` decimal(11, 2) DEFAULT 0,
  `order_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `delivery_date` DATETIME DEFAULT NULL,
  `completed` boolean DEFAULT FALSE,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) 
    REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `order_items`;

CREATE TABLE `order_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`order_id`) 
    REFERENCES `orders` (`id`) ON DELETE CASCADE,
  FOREIGN KEY (`product_id`) 
    REFERENCES `products` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` varchar(45) NOT NULL,
  `code`  int(11) NOT NULL UNIQUE,
  `size` varchar(4) DEFAULT NULL,
  `price` decimal(9, 2) DEFAULT 0,
  `description` varchar(1024) DEFAULT NULL,
  `in_stock` boolean DEFAULT TRUE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;