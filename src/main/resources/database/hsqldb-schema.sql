

create table people (
    firstname varchar(80) not null,
    lastname varchar(80) not null,
    constraint pk_person primary key (firstname)
);

CREATE TABLE ITEM_MASTER (
  ID SMALLINT NOT NULL,
  DESCRIPTION varchar(100) DEFAULT NULL,
  JSON  BINARY(10),
  SECTION_ID SMALLINT DEFAULT NULL,
  constraint pk_item_master PRIMARY KEY (ID)
) ;

CREATE TABLE ITEM (
  ID SMALLINT NOT NULL,
  VALUE double DEFAULT NULL,
  DATE timestamp NOT NULL,
  PRIMARY KEY (ID,DATE)
);

CREATE TABLE SECTION (
  ID SMALLINT NOT NULL,
  PARENT_ID SMALLINT DEFAULT NULL,
  DESCRIPTION varchar(100) DEFAULT NULL,
  URL varchar(200) DEFAULT NULL,
  PRIMARY KEY (ID),
  CONSTRAINT SECTION_SECTION_FK FOREIGN KEY (PARENT_ID) REFERENCES SECTION (ID)
);

CREATE TABLE WORKER (
  ID SMALLINT NOT NULL,
  CLASS varchar(100) NOT NULL,
  DESCRIPTION varchar(100) DEFAULT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE WORKER_SECTION (
  SECTION_ID SMALLINT NOT NULL,
  WORKER_ID SMALLINT NOT NULL,
  PARAMS varchar(200) DEFAULT NULL,
  CRON_EXPRESSION varchar(50) DEFAULT NULL,
  PRIMARY KEY (SECTION_ID,WORKER_ID),
  CONSTRAINT WORKER_SECTION_SECTION_FK FOREIGN KEY (SECTION_ID) REFERENCES SECTION (ID),
  CONSTRAINT WORKER_SECTION_WORKER_FK FOREIGN KEY (WORKER_ID) REFERENCES WORKER (ID)
) ;