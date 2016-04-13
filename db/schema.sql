CREATE DATABASE `resropoly` /*!40100 DEFAULT CHARACTER SET latin1 */;


CREATE TABLE `users` (
  `name` varchar(60) NOT NULL,
  `uri` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
