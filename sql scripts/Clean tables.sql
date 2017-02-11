Drop table tv_info;

CREATE TABLE `tv_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `dayOfWeek` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;


Drop table episodes;

CREATE TABLE `episodes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tvId` int(11) NOT NULL,
  `episode` int(11) NOT NULL,
  `downloadDate` date NOT NULL,
  `dateCreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

