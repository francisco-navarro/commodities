CREATE TABLE `SECTION` (
  `ID` int(11) NOT NULL,
  `PARENT_ID` int(11) DEFAULT NULL,
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  `URL` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SECTION_SECTION_FK` (`PARENT_ID`),
  CONSTRAINT `SECTION_SECTION_FK` FOREIGN KEY (`PARENT_ID`) REFERENCES `SECTION` (`ID`)
) ;

CREATE TABLE `ITEM_MASTER` (
  `ID` int(11) NOT NULL,
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  `JSON` blob,
  `SECTION_ID` int(11) DEFAULT NULL,
  `URL` varchar(100) DEFAULT NULL,
  `ONE_YEAR_RANGE` varchar(30) DEFAULT NULL,
  `MONTH` date DEFAULT NULL,
  `CONTRACT_SIZE` varchar(30) DEFAULT NULL,
  `SETTLEMENT_TYPE` varchar(30) DEFAULT NULL,
  `LAST_TRADING_DAY` varchar(30) DEFAULT NULL,
  `POINT_VALUE` varchar(30) DEFAULT NULL,
  `TICK_SIZE` varchar(30) DEFAULT NULL,
  `TICK_VALUE` varchar(30) DEFAULT NULL,
  `BASE_SYMBOL` varchar(30) DEFAULT NULL,
  `MONTHS` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ITEM_MASTER_SECTION_FK_INDEX_F` (`SECTION_ID`),
  KEY `PRIMARY_KEY_2E` (`ID`),
  CONSTRAINT `ITEM_MASTER_SECTION_FK` FOREIGN KEY (`SECTION_ID`) REFERENCES `SECTION` (`ID`)
);

CREATE TABLE `ITEM_HISTORY` (
  `ITEM_ID` int(10) unsigned NOT NULL,
  `DAY` date NOT NULL DEFAULT '0000-00-00',
  `OPEN_VALUE` float DEFAULT NULL,
  `CLOSE_VALUE` float DEFAULT NULL,
  `VAR` float DEFAULT NULL,
  `MAX_VALUE` float DEFAULT NULL,
  `MIN_VALUE` float DEFAULT NULL,
  PRIMARY KEY (`ITEM_ID`,`DAY`)
);

CREATE TABLE `ITEM` (
  `ID` int(11) NOT NULL,
  `VALUE` double DEFAULT NULL,
  `DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`,`DATE`),
  KEY `ITEMS_ITEM_MASTER_FK_INDEX_4` (`ID`),
  KEY `PRIMARY_KEY_4` (`ID`,`DATE`),
  CONSTRAINT `ITEMS_ITEM_MASTER_FK` FOREIGN KEY (`ID`) REFERENCES `ITEM_MASTER` (`ID`)
);

CREATE TABLE `WORKER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CLASS` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ;

CREATE TABLE `WORKER_SECTION` (
  `SECTION_ID` int(11) NOT NULL,
  `WORKER_ID` int(11) NOT NULL,
  `PARAMS` varchar(200) DEFAULT NULL,
  `CRON_EXPRESSION` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SECTION_ID`,`WORKER_ID`),
  KEY `WORKER_SECTION_WORKER_FK` (`WORKER_ID`),
  CONSTRAINT `WORKER_SECTION_SECTION_FK` FOREIGN KEY (`SECTION_ID`) REFERENCES `SECTION` (`ID`),
  CONSTRAINT `WORKER_SECTION_WORKER_FK` FOREIGN KEY (`WORKER_ID`) REFERENCES `WORKER` (`ID`)
);

INSERT INTO sql288460.`SECTION`
(ID, PARENT_ID, DESCRIPTION, URL)
VALUES(17, 4, 'Tipos de cambio', 'http://es.investing.com/quotes/cotizacion-divisas');
INSERT INTO sql288460.`SECTION`
(ID, PARENT_ID, DESCRIPTION, URL)
VALUES(6, 2, 'Agrícolas', 'http://es.investing.com/commodities/perecederos');
INSERT INTO sql288460.`SECTION`
(ID, PARENT_ID, DESCRIPTION, URL)
VALUES(10, 3, 'España', 'http://es.investing.com/indices/spain-indices');
INSERT INTO sql288460.`SECTION`
(ID, PARENT_ID, DESCRIPTION, URL)
VALUES(13, 3, 'Europa', 'http://es.investing.com/indices/global-indices?majorIndices=on&additionalIndices=on&r_id=2');
INSERT INTO sql288460.`SECTION`
(ID, PARENT_ID, DESCRIPTION, URL)
VALUES(18, 4, 'Índice dolar', 'http://es.investing.com/quotes/us-dollar-index');
INSERT INTO sql288460.`SECTION`
(ID, PARENT_ID, DESCRIPTION, URL)
VALUES(4, 1, 'Divisas', '');
INSERT INTO sql288460.`SECTION`
(ID, PARENT_ID, DESCRIPTION, URL)
VALUES(9, 2, 'Granos', 'http://es.investing.com/commodities/granos');
INSERT INTO sql288460.`SECTION`
(ID, PARENT_ID, DESCRIPTION, URL)
VALUES(14, 3, 'Oriente Próximo', 'http://es.investing.com/indices/global-indices?majorIndices=on&additionalIndices=on&r_id=4');
INSERT INTO sql288460.`SECTION`
(ID, PARENT_ID, DESCRIPTION, URL)
VALUES(3, 1, 'Índices', NULL);
INSERT INTO sql288460.`SECTION`
(ID, PARENT_ID, DESCRIPTION, URL)
VALUES(15, 3, 'Asia/Pacífico', 'http://es.investing.com/indices/global-indices?majorIndices=on&additionalIndices=on&r_id=3');
INSERT INTO sql288460.`SECTION`
(ID, PARENT_ID, DESCRIPTION, URL)
VALUES(11, 3, 'Mundial', 'http://es.investing.com/indices/global-indices?majorIndices=on&additionalIndices=on');
INSERT INTO sql288460.`SECTION`
(ID, PARENT_ID, DESCRIPTION, URL)
VALUES(8, 2, 'Metales', 'http://es.investing.com/commodities/metales');
INSERT INTO sql288460.`SECTION`
(ID, PARENT_ID, DESCRIPTION, URL)
VALUES(2, 1, 'Materias primas', 'http://es.investing.com/commodities/');
INSERT INTO sql288460.`SECTION`
(ID, PARENT_ID, DESCRIPTION, URL)
VALUES(16, 3, 'África', 'http://es.investing.com/indices/global-indices?majorIndices=on&additionalIndices=on&r_id=5');
INSERT INTO sql288460.`SECTION`
(ID, PARENT_ID, DESCRIPTION, URL)
VALUES(12, 3, 'América', 'http://es.investing.com/indices/global-indices?majorIndices=on&additionalIndices=on&r_id=1');
INSERT INTO sql288460.`SECTION`
(ID, PARENT_ID, DESCRIPTION, URL)
VALUES(7, 2, 'Energía', 'http://es.investing.com/commodities/energ%C3%ADas');
INSERT INTO sql288460.`SECTION`
(ID, PARENT_ID, DESCRIPTION, URL)
VALUES(1, NULL, 'markets', NULL);
