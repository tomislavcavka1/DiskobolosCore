-- DROP SEQUENCE diskobolos.member_register_id_seq;

CREATE SEQUENCE diskobolos.location_id_seq
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1;

create table diskobolos.location (
	id int8 NOT NULL DEFAULT nextval('diskobolos.location_id_seq'::regclass),
	postal_code int4 not null unique,
	name varchar(100) not null,
	CONSTRAINT pk_location PRIMARY KEY (id)
)
WITH (
	OIDS=FALSE
);

CREATE SEQUENCE diskobolos.membership_category_id_seq
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1;

create table diskobolos.membership_category (
	id int8 NOT NULL DEFAULT nextval('diskobolos.membership_category_id_seq'::regclass),
	description varchar(100) not null,
	CONSTRAINT pk_membership_category PRIMARY KEY (id)
)
WITH (
	OIDS=FALSE
);

CREATE SEQUENCE diskobolos.member_register_id_seq
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1;

CREATE TABLE diskobolos.member_register (
	id int8 NOT NULL DEFAULT nextval('diskobolos.member_register_id_seq'::regclass),
	name varchar(100) not null,
	address varchar(80),
	location_id int8 not null,
	phone1 varchar(30),
	phone2 varchar(30),
	fax varchar(30),	
	identification_number varchar(15),
	oib varchar(15),
	register_number varchar(15),
	number_of_non_profit_org varchar(15),
	chairman varchar(100),
	secretary varchar(100),
	date_from date,
	date_to date,
	registration_date date,	
	membership_category integer not null,	
	CONSTRAINT pk_member_register PRIMARY KEY (id),
	CONSTRAINT fk_member_register_location FOREIGN KEY (location_id) REFERENCES diskobolos.location(id),
	CONSTRAINT fk_member_register_membership_category FOREIGN KEY (membership_category) REFERENCES diskobolos.membership_category(id)
)
WITH (
	OIDS=FALSE
);

CREATE SEQUENCE diskobolos.bank_account_id_seq
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1;

create table diskobolos.bank_account (
	id int8 NOT NULL DEFAULT nextval('diskobolos.bank_account_id_seq'::regclass),
	account_number varchar(35),
	account_type varchar(20),
	account_description varchar(50),
	member_register_id int8 not null,
	CONSTRAINT pk_bank_account PRIMARY KEY (id),
	CONSTRAINT fk_bank_account_member_register FOREIGN KEY (member_register_id) REFERENCES diskobolos.member_register(id)
);

CREATE SEQUENCE diskobolos.sport_id_seq
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1;

create table diskobolos.sport (
	id int8 NOT NULL DEFAULT nextval('diskobolos.sport_id_seq'::regclass),
	name varchar(100) not null,
	CONSTRAINT pk_sport PRIMARY KEY (id)
);

CREATE SEQUENCE diskobolos.nomenclature_of_sport_id_seq
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1;

create table diskobolos.nomenclature_of_sport (
	id int8 NOT NULL DEFAULT nextval('diskobolos.nomenclature_of_sport_id_seq'::regclass),
	sport_id int8 not null,
	category varchar(80),
	category_description varchar(100),
	value varchar(100),
	CONSTRAINT pk_nomenclature_of_sport PRIMARY KEY (id),
	CONSTRAINT fk_nomenclature_of_sport_sport FOREIGN KEY (sport_id) REFERENCES diskobolos.sport(id)	
);

CREATE SEQUENCE diskobolos.email_id_seq
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1;

create table diskobolos.email (
	id int8 NOT NULL DEFAULT nextval('diskobolos.email_id_seq'::regclass),
	email varchar(100),	
	member_register_id int8 not null,
	CONSTRAINT pk_email PRIMARY KEY (id),
	CONSTRAINT fk_email_member_register FOREIGN KEY (member_register_id) REFERENCES diskobolos.member_register(id)
);

CREATE SEQUENCE diskobolos.evaluation_question_def_id_seq
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1;

create table diskobolos.evaluation_question_def (
	id int8 NOT NULL DEFAULT nextval('diskobolos.evaluation_question_def_id_seq'::regclass),	
	question int8 not null unique,	
	value_type int8,
	mandatory boolean,
	default_value varchar(100),
	CONSTRAINT pk_evaluation_question_def PRIMARY KEY (id)
);

CREATE SEQUENCE diskobolos.question_choices_def_id_seq
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1;

create table diskobolos.question_choices_def (
	id int8 NOT NULL DEFAULT nextval('diskobolos.question_choices_def_id_seq'::regclass),
	question_id int8 not null,	
	value varchar(100),
	CONSTRAINT pk_question_choices_def PRIMARY KEY (id),
	CONSTRAINT fk_question_choices_def_evaluation_question_def FOREIGN KEY (question_id) REFERENCES diskobolos.evaluation_question_def(question)
);

CREATE SEQUENCE diskobolos.evaluation_answer_id_seq
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1;

create table diskobolos.evaluation_answer (
	id int8 NOT NULL DEFAULT nextval('diskobolos.evaluation_answer_id_seq'::regclass),
	member_register_id int8 not null,
	answer int8 not null,
	CONSTRAINT pk_evaluation_answer PRIMARY KEY (id),
	CONSTRAINT fk_evaluation_answer_question_choices_def FOREIGN KEY (answer) REFERENCES diskobolos.question_choices_def(id),
	CONSTRAINT fk_evaluation_answer_member_register FOREIGN KEY (member_register_id) REFERENCES diskobolos.member_register(id)
);

CREATE SEQUENCE diskobolos.users_id_seq
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1;

create table diskobolos.users (
	id int8 NOT NULL DEFAULT nextval('diskobolos.users_id_seq'::regclass),
	username varchar(30) not null,
	password varchar(100) not null,
	email varchar(50) not null,
	enabled bool NOT NULL,	
	CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE SEQUENCE diskobolos.authorities_id_seq
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1;

create table diskobolos.authorities (
	id int8 NOT NULL DEFAULT nextval('diskobolos.authorities_id_seq'::regclass),
	role varchar(30) not null,
	user_id int8 not null,
	permission_level int2 not null,
	CONSTRAINT pk_authorities PRIMARY KEY (id),
	CONSTRAINT fk_authorities_users FOREIGN KEY (user_id) REFERENCES diskobolos.users(id)
);

-- inserting data into location
insert into location(postal_code, "name") values(23000, 'Zadar');
insert into location(postal_code, "name") values(10000, 'Zagreb');

-- inserting data into membership_category
insert into diskobolos.membership_category (description) values ( 'SPORT');
insert into diskobolos.membership_category (description) values ( 'SPORTSKA REKREACIJA ("SPORT ZA SVE")');
insert into diskobolos.membership_category (description) values ( 'ZDRAVSTVENA ZAŠTITA SPORTAŠA');
insert into diskobolos.membership_category (description) values ( 'SPORTSKE GRAĐEVINE');
insert into diskobolos.membership_category (description) values ( 'SPORT GLUHIH OSOBA');
insert into diskobolos.membership_category (description) values ( 'SPORT OSOBA S INVALIDITETOM');
insert into diskobolos.membership_category (description) values ( 'ŠKOLSKI SPORT');
insert into diskobolos.membership_category (description) values ( 'ZAHTJEV ZA PRIDRUŽENO ČLANSTVO');
insert into diskobolos.membership_category (description) values ( 'SPECIJALNA OLIMPIJADA');
insert into diskobolos.membership_category (description) values ( 'SPORTSKE AKTIVNOSTI DJECE');

--inserting data into member_register
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Aikido klub Zadar', 'Stomorica 7',1, '023/251-210','','023/251-210','3639975', '35182939735','13000118', '0073359', 'Nenad Vertovšek (091/337-77-76)', 'Vesna Vertovšek (091/512-65-87)',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Atletski športski klub Zadar', 'Edvina Androvića 2',1, '023/301-691','','023/301-691','03154912', '86624179675','13000308', '01128100', 'Emilijo Krstić (098/721-876)', 'Helena Vulić',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Športski aikido klub Donat', 'Julija Klovića 36',1, '','','','02411423', '40633085803','13001042', '0086495', 'Renata Ruić Funčić  (091/539-24-92)', 'Goran Funčić  (091/539-24-91)',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Atletski klub Alojzije Stepinac', 'Vinkovačka 35 f',1, '023/326-109','','023/326-109','02013517', '35240858683','13000826', '0200101', 'Mladen Peša', 'Gabrijela Miolović (098/133-70-36)',to_date(NULLIF('01.01.2010', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Atletski klub Olympionik', 'Ulica kralja S. Držislava 4',1, '023/213-899','','023/213-899','02464551', '57024317058','13001078', '0250249', 'Perica Korica (098/330 - 257)', 'Petar Korica (091/161-11-66)',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Atletski klub Start', 'Hrvatskog sabora 22',1, '023/315-523','','023/315-523','02791889', '36113911509','13001326', '0247539', 'Jurica Vuleta  (098/935-2793)', 'Ivan Mijolović',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.07.2014', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Klub atletskih veterana Zadar', 'Pašmanski prilaz 15',1, '','','','01930117', '02457723462','13000778', '0198958', 'Borislav Perica (098/909-51-62)', 'Danijel Tadić (098/196-86-70)',to_date(NULLIF('01.01.2009', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Automobilistički klub ZD tuning', 'Božidara Adžije 13',1, '','','','02434571', '07178578094','13001059', '0189089', 'Toni Perinić (095/525-88-19)', 'Jure Perić: (091/598-05-99)',to_date(NULLIF('01.01.2010', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Badminton klub Iader', 'Ante Starčevića 15d',1, '023/240-406','','','02570211', '85070743078','13001162', '0263324', 'Ernesto Šimac  (099/317-79-62)', 'Mario Murtić',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Baseball klub Donat', 'Put Murvice 99',1, '023/7305-409','','023/319-075','03414264', '10554665928','13000422', '0017507', 'Ivica Anić  (098/853 - 869)', 'Zoran Šijan (098/366 - 673)',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Biciklistički klub Sv. Donat', 'R.K. Jeretova 5',1, '023/315-632','','','01560913', '23423721111','13000210', '0217834', 'Denis Šepat', 'Ivan Krpina (095/199-19-45)',to_date(NULLIF('01.01.2010', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Biciklistički klub Zadar', 'Put Stanova 3',1, '023/315-632','','','03154882', '80916067371','13000315', '0136826', 'Božidar Kotlar', 'Neven Pavić  (098/177-18-01)',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Biciklistički klub Macaklin', 'Ugljanska 6a',1, '023/342-190','','','02480018', '21483252848','13001060', '0189521', 'Ivan Govorčin (095/900-83-08)', 'Daniel Filić (091/893-62-37)',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ), to_date(NULLIF('19.07.2014', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Biljar klub 11', 'Kraljice mira 3',1, '023/324-118','','0123/312-235','2194295', '76378932354','13000911', '0342154', 'Čedomir Perinčić ( 098/463-552)', 'Iva Perinčić',to_date(NULLIF('01.01.2001', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2005', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Boćarski klub Bili Brig', 'Put Pudarice 11f',1, '023/327-957','','023/327-957','03450848', '53640895488','13000381', '0217137', 'Goran Lijić (091/449-2922)', 'Zdenko Stipanić (098/950-73-32)',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Boćarski klub Brodarica', 'Put Dikla bb',1, '023/332-487','','023/332-487','03933644', '03283371919','13000205', '0306770', 'Jozo Jeličić (098/192-00-08)', 'Željko Sabalić (098/192-00-08)',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Boćarski klub Veteran', 'Put Dikla bb',1, '023/333-174','','','03418618', '69602095812','13000471', '', 'Luka Škara (092/306-40-96)', 'Marjan Nimac (098/449-819)',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Boćarski klub Zadar', '22. lipnja 1941. br. 9',1, '023/214-093','','023/214-093','03450848', 'O3836929990','13000253', '', 'Milivoj Colić (098/196-27-65)', 'Šime Burerin (091/789-78-64)',to_date(NULLIF('01.01.2008', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Boćarski klub Crvene kuće', 'Antuna Dobranića bb',1, '','','','02631920', '11984817013','13001207', '0216999', 'Ante Kardum (095/903-1759)', '',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Boksački klub Diabolik', 'Jakova Mikalje 22',1, '023/241-334','','023/241-334','00711233', '62256903451','13000342', '0128898', 'Damir Zrilić (098/643-238)', 'Mario Pešut (098/332-844)',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Boksački klub Zadar', 'Crno 40',1, '099/7637493','','','03173887', '14313344538','13000235', '0121468', 'Dragan Vidaić (099/76-37-493)', 'Branka Vidaić (098/888-585)',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Jedriličarski klub Uskok', 'Obala kneza Trpimira bb',1, '023/337-830','','023/333-888','03154840', '18816234272','13000046', '0121468', 'Ive Mustać (098/207-269)', 'Hrvoje Grdović (099/529-37-84)',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Judo klub Zadar', 'Edvina Androvića bb',1, '023/324-984','','023/324-984','00824682', '14954453186','13000050', '0042102', 'Ante Zanki (098/332-584)', 'Irena Zanki (099/214-88-33)',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Košarkaški klub Jazine - Arbanasi', 'Jakova Gotovca 18',1, '023/213-214','','023/224-488','0317585', '00054311768','13000210', '0137133', 'Branko Morić (098/730-042)', 'Miljan Ćustić (091/976-77-40)',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Košarkaški klub Zadar š.d.d.', 'Obala kralja Tomislava 1',1, '023/212-441','','023/212-441','03154955', '9051939443','', '', 'Boris Skroče (091/508-51-14)', 'Vana Dundov (098/415-598)',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Kuglački klub Donat zaštita', 'Andrije Hebranga 9a',1, '023/493-845','','023/493-845','01565362', '26478293512','13000526', '0189372', 'Marin Colić (091/528-93-33)', 'Jakov Krstić',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Kuglački klub Zadar', 'Brune Bušića 10',1, '023/240-122','','023/240-120','03197735', '59524922370','13000117', '0094625', 'Ivan Lulić (098/981-69-00)', 'Marija Lulić',to_date(NULLIF('01.01.2008', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Nogometni klub Zadar š.d.d.', 'Hrvoja Ćustića 2',1, '023/312-792','','023/312-802','04295030', '06621814362','', '', 'Josip Bajlo (091/530-44-66)', 'Mladen Baždarić (098/378-792)',to_date(NULLIF('01.01.2008', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Odbojkaški klub Zadar', 'Trg Petra Zoranića 1',1, '023/213-809','','023/213-403','03154777', '60567527217','13000241', '0073709', 'Robert Kresoja (098/330-231)', '',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Planinarsko društvo Paklenica', 'Majke Margarite bb',1, '023/301-636','','023/301-636','03154939', '92966614510','13000356', '0124394', 'Domagoj Diklić (099/213-12-22)', 'Vesna Dukić',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Plivački klub Zadar', 'Kolovare 2 a',1, '023/312-320','','023/312-320','03429644', '83018458598','13000252', '0162874', 'Darko Smirkinić (098/331-057)', 'Anja Jelinić (099/803-85-21)',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Ronilački klub "KPA"', 'Obala kneza Trpimira bb',1, '023/332-954','','023/332-954','03154831', '43628357665','13000343', '0016918', 'Slavica Čolak (098/184-22-14)', 'Marija Velemir (099/341-72-25)',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Stolnoteniski klub Donat', 'Benka Benkovića 14',1, '023/242-096','','023/242-096','03155072', '51868892731','13000322', '0086614', 'Davor Aras (098/214-215)', 'Ljubomir Letinić (091/886-56-12)',to_date(NULLIF('01.01.2008', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Streljački klub Zadar', 'Brune Bašića 10',1, '023/331-050','','023/331-050','03155048', '14707241415','13000309', '0125660', 'Jadranko Surać (098/499-334)', 'Davor Zubčić (098/226-139)',to_date(NULLIF('01.01.2008', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Šahovski klub Zadar', 'Brune Krnarutića 6',1, '023/251-632','','','03154998', '74633093402','13000232', '0163733', 'Josip Bubičić (098/968-79-99)', 'Krolo (098/734-388)',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Športsko ribolovno društvo Zubatac', 'Narodnog lista 2',1, '023/212-708','','023/214-441','03159922', '90755644642','13000341', '0120681', 'Ardena Bajlo (091/207-25-35)', 'Branko Šuljak (091/225-11-29)',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Ordinacija Športske medicine', 'Knezova Šubića bribirskih 16',1, '023/334-859','','023/334-859','80067247', '30089337645','', '', 'Doktor: dr. Marko Prenđa (091/125-02-32)', '',to_date(NULLIF('', ''),  'DD.MM.YYYY' ),to_date(NULLIF('', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Teniski klub Zadar 08', 'Sutomiška 1',1, '023/334-859','','023/334-859','03151492', '65605226586','13000168', '0164423', 'Lovro Rončević (098/172-0228)', 'Albert Radovniković (098/160-71-37)',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Udruga za šport i rekreaciju invalida Grada Zadra', 'Mate Balote 56 a',1, '023/333-477','','023/323-400','01230131', '82239066808','', '', 'Ivica Bratanović (099/509-67-65)', 'Ivan Anušić (098/191-24-60)',to_date(NULLIF('01.01.2008', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Veslački klub Jadran', 'Obala Kneza Branimira 6a',1, '023/316-929','','023/316-929','03154807', '48176749093','13000303', '0073717', 'Klaudijo Stipčević (091/750-20-77)', 'Darija Kraljević (098/734-374)',to_date(NULLIF('01.01.2009', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Javna ustanova za upravljanje športskim objektima - Zadarski šport d.o.o.', 'Brune Bušića bb',1, '023/331-197','','023/331-400','1366432', '98185775176','', '', 'Direktor: Jurica Dilber (091/591-19-77)', '',to_date(NULLIF('01.01.2010', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Ženski rukometni klub Zadar', 'Lukoranska 8',1, '023/341-034','','023/342-630','03154971', '35599539345','13000306', '0151330', 'Dražen Dajak (091/204-04-40)', 'Nela Mitrović (098/191-29-61)',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Športski plesni klub Samba', 'Tomislava Ivčića 7a',1, '023/312-518','','','01443615', '66124427579','13000395', '0097063', 'Zlatka Badel (098/650-873', 'Bernarda Kaurloto',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Društvo športske rekreacije Diklo', 'Krešimirova Obala 164',1, '023/337-038','','023/337-064','01619144', '59710612309','13000575', '0041793', 'Verino Ladiš (097/715-9866)', '',to_date(NULLIF('01.01.2007', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Ronilaćki klub Zadar', 'Put Petrića 43',1, '023/324-306','','023/324-306','01564005', '73191012166','13000534', '0132341', 'Duško Paulin (091/4848-588)', 'Vjekoslav Valčić (091/7306-39-69)',to_date(NULLIF('01.01.2009', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Stolnoteniski klub Jadera', 'Prolaz Opatice -vekenege 6',1, '023/250-020','','023/250-020','02433826', '69211850445','13001070', '0112305', 'Ivica Bujas (099/599-16-97)', 'Mario Kožul (091/508-98-33)',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Košarkaški klub Voštarnica', 'Vinkovačka 35 F',1, '023/326-176','','','01717081', '21803934153','13000661', '0188686', 'Tomislav Klarica (091/326-17-66)', 'Igor Dorontić',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Nogometni Klub Arbanasi', 'Stadionska 2',1, '023/311-759','','','01815598', '48701891138','13000727', '0120746', 'Edi Perović', 'Miodrag PAunović (091/733-46-06)',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Karate klub Croatia', 'Obala Kneza Trpimira 34 C ( Put Stanova 3)',1, '023/319-014','','023/250-048','03587967', '68920650220','13000152', '0041696', 'Hrvoje Mikecin (091/760-49-66)', '',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Plivački klub Jadera', 'Rivanjski prilaz 1',1, '023/334-933','','023/334-933','02230518', '80828570466','13000943', '0041831', 'Vitomir Burčul (098/166-01-13)', 'Marijan Čulina (098/976-35-23)',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Triatlon klub Zadar', 'Stjepana Radića 10',1, '','','','02564343', '79243095913','13001164', '0151891', 'Ivan Gobin (098/180-54-34)', 'Ivan Tuta (098/180-54-34)',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Taekwondo klub Zadar', 'Sv. Vinka Paulskog 19',1, '023/302-374','','023/302-374','03439330', '18649423025','1300032', '0127916', 'Mladen Uskok (098/303-311)', 'Krešimir Karamarko (099/311-49-17)',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Košarkaški klub Pet bunara - veterani', 'Veslačka 2',1, '','','','02823071', '57896242721','13001345', '0198799', 'Tonći Jerak (099/882-26-23)', 'Jure Jerak (091/348-07-37)',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Rukometni klub Zadar 1954', 'Franje Petrića 3',1, '023/34-772','','023/324-772','01856979', '39778663780','13000745', '0149344', 'Rade Šimićević  (098/98-464)', 'Igor Nikolić (098/944-48-00)',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Karate klub Zvonimir', 'Fra Ivana Zadranina 1B',1, '023/311-552','','023/311-552','02201844', '06972084253','13000932', '0085588', 'Tomislav Rogić (098/614-413)', 'Franjp Glavaš (098/614-413)',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Malonogometni klub Višnjik', 'Put Šimunova 4',1, '023/231-551','','023/231-551','01799746', '19104518057','130007000', '0271499', 'Ivica Jukić (095/198-12-43)', 'Matija Benini (095/914-89-69)',to_date(NULLIF('', ''),  'DD.MM.YYYY' ),to_date(NULLIF('', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Kuglački klub Donat Zadar - Žene', 'Brune Bušića bb',1, '023/334-811 ','023/351-504','','03189082', '22505058872','13000338', '0103128', 'Ante Lonić (099/572-71-86)', 'Nives Grabovac (098/248-379) Žaklina Jelača (091/788-47-68)',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Klub ritmičke gimnastike Sirena', 'Mile Gojsalić 4',1, '023/340-531','','023/340-531','02412411', '91916030955','13001049', '0116012', 'Iris Mijailović Bulj (095/830-50-70)', 'Irena Serdarević',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Automobilistički klub RTZ', 'Put Murvice 29',1, '','','023/778-895','02626209', '62512974921','13001198', '0218241', 'Goran Ban (095/808-47-75)', 'Iva Borošak',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Društvo Športske rekreacije Vitalnost', 'Vinkovačka 35d',1, '','','','02549336', '89335027474','13001140', '01011959', 'Ivana Stilinović (098/903-46-17)', 'Filip Simičić',to_date(NULLIF('01.01.2009', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Košarkaški klub ABC', 'Vukovarska 65',1, '023/326-176','','023/326-176','01462016', '50180654492','13000415', '0128093', 'Željko Pribanović (091/326-35-35)', 'Davor Rosan',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Športska škola košarke Zadar', 'Splitska 3',1, '023/230-750','','023/230-750','02273454', '80406652845','130400116', '0028584', 'Tonći Jerak (099/311-16-47)', 'Zdenko Perić',to_date(NULLIF('', ''),  'DD.MM.YYYY' ),to_date(NULLIF('', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Udruga za mali nogomet Zadarske županije', 'Ravnice 4',1, '023/314-521','','023/254-180','03417255', '09849416974','13000182', '0151224', 'Zvonimir Sorić (098/272-525)', 'Matetrbušić (095/889-46-93)',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Malonogometni klub Futsal', 'Put Murvice 8/6',1, '023/321-361','','023/321-361','02936470', '23999408148','', '0265341', 'Marko Šimurina (091/515-51-52)', '',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Malonogometni klub Puntamika', 'A. G. Matoša 26',1, '','','','01669435', '47719025627','13000628', '0151020', 'Alan Kociper (098/180-60-12)', 'Stela Dunić',to_date(NULLIF('01.01.2010', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Športsko rekreacijska udruga Sklek', 'Šibenska 3d',1, '023/326-682','','','02301644', '09909070165','13000983', '0150518', 'Vedrana Pleslić (095/909-3525)', '',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Streličarski klub Zadar', 'Trg Kardinala A. Stepinca',1, '','','','02671816', '65872367322','13001218', '0168838', 'Irena Stein (098/817-065)', 'Maja Pavković (098/315-082)',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Ragbi klub Zadar', 'Dr. Franje Tuđmana 30a',1, '023/305-428','','','02234645', '93943012649','13000926', '0189992', 'Mateo Mazija (091/201-45-83)', 'Radošević Ivan (091/957-46-18)',to_date(NULLIF('', ''),  'DD.MM.YYYY' ),to_date(NULLIF('', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Vaterpolski klub Zadar 1952', 'Kolovare bb',1, '023/237-614','','023/206-898','01675826', '33644431140','13000637', '0190617', 'Dražen Grgurović (098/981-69-00)', 'Goran Jovančević (099/323-15-62)',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Gimnastički klub Salto', 'Ivana Zadranina 2',1, '023/236-384','','023/350-390','02228416', '165952126666','13000939', '0041904', 'Marko Sutlović (099/255-77-70)', 'Ivana Ratković (099/813-44-31)',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Šahovski klub Casper', 'Put Stanova 7',1, '023/312-794','','023/254-020','01264818', '39897562507','13000236', '0112361', 'Veljko Šagi (091/170-21-40)', 'Margita Gurdulić',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Društvo športske rekreacije Silba', 'Sv. Vinka Paulskog 9',1, '023/323-861','','023/323-861','02331357', '09726047291','13000985', '0192857', 'Ante Žorrž (098/198-48-62)', 'Marko Žorž (095/902-04-80)',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Malonogometni klub Drakmar Sali', '23281 Sali',1, '','','','', '97831826928','', '', 'Šanto Basioli (099/833-83-97)', '',to_date(NULLIF('', ''),  'DD.MM.YYYY' ),to_date(NULLIF('', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Županijski savez školskog športa Zadarske županije', 'Dr. Franje Tuđmana bb',1, '023/312-289','','023/312-289','01661132', '44327231995','13000606', '0041572', 'Davor Vidaković (098/332-874)', 'Jadranka Duvančić (091/207-24-02)',to_date(NULLIF('05.11.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('05.11.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Hrvatsko planinarsko društvo Mala Rava', 'Mala Rava 16',1, '023/333-681','','023/333-681','02635640', '22514563488','13001204', '0188441', 'Zoran Simičić (095/887-6080)', 'Eduard Magazin',to_date(NULLIF('01.01.2010', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Kuglački klub Liburnija', 'Brune Bušića bb',1, '023/633-074','','','01388797', '12853339056','13000258', '0102963', 'Petar Ugarković (091/528-93-33)', 'Čedo Alić',to_date(NULLIF('01.01.2010', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Hrvački klub Zadar', 'Savarska 22',1, '023/332-680','','023/332-680','02750104', '00749599062','13001288', '0177980', 'Vladimir Menčik (098/957-97-49)', 'Mirela Menčik',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Društvo Športske Rekreacije Dite zadarsko', 'Cerodole 21',1, '023/250-275','','','025559889', '36453025190','13001143', '0101911', 'Venci Longin (098/175-01-40)', '',to_date(NULLIF('01.01.2009', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Odbojkaški klub Arbanasi', 'Trg Gospe Loretske 3',1, '023/250-120','','023/254-020','01802020', '10199002785','13000703', '0112272', 'Karlo Lisica (091/799-62479', '',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Klub daljinskog plivanja Donat', 'Đure Marušića 23',1, '023/300-444','','023/300-444','01944835', '18693109162','13000784', '0120399', 'Hrvoje Bajlo (091/564-02-12)', 'Siniša Pezelj',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Kiteboarding udruga Adrenalin', 'Poljana D. Domjanića 46',1, '023/334-414','','023/334-414','01477951', '50228536500','13000437', '0188755', 'Damir Veledar (099/513-42-18)', 'Maria Veledar',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Klub jedrenja na dasci Fortunal', 'Poljana D. Domjanića 46',1, '023/334-414','','023/334-414','01285122', '72975768959','13000201', '0188763', 'Damir Veledar (091/513-42-18)', 'Marinko Miočić (098/685-502)',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Društvo za športsku rekreaciju Relaks', 'Šibenska 4f',1, '023/326-225','','023/326-225','00769118', '72633484931','13000018', '0151453', 'Katja Pijaca (091/509-28-75)', '',to_date(NULLIF('01.01.2010', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Športski klub za skokove u vodu Arno', 'Savarska 22',1, '023/313-192','','023/313-192','2043025', '56403849809','13000864', '0151652', 'Veljko Bubić', 'Arno Longin (098/171-30-97)',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Kickboxing klub Sv. Krševan', 'Senjska 7',1, '023/318-401','','','01774433', '08024726070','13000392', '0192946', 'Neenad Mitrović (098/924-98-42)', 'Jurica Kvartuč',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Boksački klub Sv. Krševan', 'Senjska 7',1, '023/318-401','','','01433806', '97042242606','13000392', '0192959', 'Nenad Mitrović (098/924-98-42)', 'Jurica Kvartuč',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Malonogometni klub Varoš', 'Put Pudarice 34e',1, '023/327-372','','023/327-372','02561425', '29063643242','13001159', '0189548', 'Marko Jurin (099/736-85-49)', 'Marko Vukić (099/249-81-81)',to_date(NULLIF('01.01.2009', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Košarkaški klub Sonik Puntamika', 'A.G. Matoša bb',1, '023/335-735','','023/335-735','01373137', '94165900313','13000189', '0125060', 'Mirko Jošić (091/121-10-65)', 'Pero Perić (091/547-73-72)',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.07.2014', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Kickboxing klub Sv. Zoilo', 'Poljana Jurja Dragišića 40',1, '','','','02726050', '76521889373','13001245', '0164440', 'Tomislav Torić (095/8000-48-86)', 'Jure Čačić',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Društvo športske rekreacije Euforija', 'Dimitrija Demetra 6a',1, '','','','04044169', '15855531924','13001470', '0248415', 'Marina Tomas (098/6500-07)', 'Tina KAravida ((099/310-14-48)',to_date(NULLIF('', ''),  'DD.MM.YYYY' ),to_date(NULLIF('', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.07.2014', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Društvo za športsku rekreaciju Hula-hop', 'Put Petrića 40b',1, '023/323-552','','','04085558', '24161670928','13001484', '0251998', 'Radojka Antić (098/42-97-61)', '',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.07.2014', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Košarkaški klub Zara', 'Polačišće 11',1, '023/341-286','','023/236-384','04060938', '97422202178','13001477', '0249667', 'Marin Vrsaljko (098/221-448)', 'Ana Vrsaljko',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.07.2014', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Boksački klub Dijagora', 'Molatska bb',1, '023/316-114','','','01167513', '53085011457','13000608', '', 'Denis Gregov (091/907-23-81)', '',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.07.2014', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Športski centar Višnjik d.o.o.', 'Splitska 3',1, '023/449-860','','023/302-402','01759418', '79086303924','', '', 'Denis Karlović (098/369-515)', '',to_date(NULLIF('', ''),  'DD.MM.YYYY' ),to_date(NULLIF('', ''),  'DD.MM.YYYY' ), to_date(NULLIF('16.07.2013', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Ženski košarkaški klub Zadar', 'Splitska 3',1, '023/350-356','','023/350-355','00516376', '30110621801','13000159', '0117756', 'Branimir Perićić (095/910-71-79)', 'Josipa Sesar',to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2019', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.07.2014', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Jedriličarski klub Sv. Krševan', 'Grigora Viteza 12',1, '023/231-679','','023/231-679','01461834', '63499196019','130100112', '0130435', 'Edo Fantela (098/940-417-72)', 'Nives Letinić',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Karate  klub Zadar', 'Kreste Hegedušića 7',1, '','','','', '67131312087','13000302', '', 'Goran Glavan (098/272-848)', '',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Ju jitsu klub Zadar', 'Ivana Mažuranića 32',1, '','','','01662015', '18212327717','13000618', '0132381', 'Damir Šalina (091/721-47-37)', 'Vedran Šetka',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Ju jitsu klub Donat', 'Dubrovačka 20 a',1, '023/214-848','','','02515199', '19634543545','13001125', '0205449', 'Saša Stipanić (099/530-04-78)', 'Samir Sedić (098/429-863)',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Boksački klub Zlatna Rukavica', 'Ivana Mažuranića 10',1, '','','','02317460', '09173545142','13000977', '0127932', 'Šime Filipi (098/941-58-19)', 'Edi Milošević (095/395-84-98)',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Body Building klub Hulk', 'Franje Alfirevića 13',1, '','','','4010094', '62291466931','13001456', '', 'Zoran Tokić (095/819-91-92)', 'Viktor Begonja',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Taekwondo klub Plovanija', 'Ravska 3',1, '023/324-118','','023/312-235','02607867', '34802146621','13001187', '0140147', 'Antonio Čačić (098/928-94-56)', 'Danijela Sipina 095/9082610',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Gimnastički klub Zadar', 'Ravnice 2',1, '023/250-120','','023/250-120','03155056', '86639247638','9537800000', '0112299', 'Magdalena Brković (095/391-38-05)', 'Saša Brković',to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2019', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Konjički klub Epona', 'Hrvatskog sabora 1',1, '023/313-036','','023/313-036','01492152', '69853428287','13000460', '0273360', 'Edo Jelenković (099/673-71-06)', 'Marin Kožul',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Streljački klub Lovac', 'Andrije Hebranga 10 a',1, '023/230-000','','0237220-797','03154815', '35307886697','13000324', '0140365', 'Danijel Telesmanić (091/226-21-00)', 'Arden Dražević',to_date(NULLIF('', ''),  'DD.MM.YYYY' ),to_date(NULLIF('', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Klub za skokove u vodu Zadar', 'Kolovare bb',1, '','','','03429679', '71809427092','13000330', '0171087', 'Mirko Jurin (098/813-44-31)', '',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Rukometni klub Arbanasi', 'Vlahe Paljetka 2',1, '023/317-279','','','02378531', '91835955260','13001023', '0115934', 'Stipe Bjeliš (098/164-25-48)', 'Vatroslav Ivković',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Klub za športski ribolov Paprenica', 'Paprenica 615',1, '','','','02647774', '73892282141','13001205', '', 'Josip-Pero Granić (098/961-74-74)', 'Ljubomir Čurković',to_date(NULLIF('01.01.2009', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Športski ribolovni klub Donat', 'Pod bedemom 3',1, '023/233-766','','023/233-766','01432974', '03595482979','13000394', '0134892', 'Josip Gatara', 'Davorka Volarević',to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ),to_date(NULLIF('', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.07.2014', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Alpinistički klub Zadar', 'Bana Josipa Jelačića  3c',1, '053/679-112','','053/679-112','02490463', '45680132118','13001106', '0151216', 'Jana Mijailović (095/827-29-43)', 'Natalija Andačić',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Odbojkaški klub Donat', 'Put Pudarice 30 b',1, '023/327-265','','023/327-265','02217830', '23517960553','13000888', '0151445', 'Julja Kaleb (098/191-27-28)', 'Irena Kraljev Mesarić',to_date(NULLIF('01.01.2010', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Hrvatski nogometni klub Dalmatinac', 'Crno  145',1, '023/300-002','','023/300-002','1472712', '73680202956','13000426', '0338503', 'Mile Vukoša (098/273-807)', 'Jurica Grgurović (091/380-51-55)',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Udruga slijepih Zadarske Županije', 'Poljana požarišće 1',1, '023/250-546','','023/250-546','01075497', '85389451415','13000100', '0002348', 'Danijel Lončar (091/548-80-02)', 'Špiro Zrilić',to_date(NULLIF('', ''),  'DD.MM.YYYY' ),to_date(NULLIF('', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Športski savez gluhih Zadarske Županije', 'Ruđera Boškovića 6/3',1, '023/7213-442','','023/7213-442','04277708', '19460481165','13001350', '0285826', 'Natko Zelić', 'Miki Savić (091/764-70-47)',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Odbojkaški klub invalida Zadar', 'Grigora Viteza 3',1, '','','','02029715', '64155155306','13000838', '0062014', 'Ivan Bajlo (098/830-888)', 'Ante Baraba Knez (092/130-39-15)',to_date(NULLIF('01.01.2010', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Kuglački klub invalida Dišpet', 'Benka Benkovića 3',1, '023/323-400','','023/323-777','02496259', '53410477438','13001122', '0188409', 'Dražen Žilić (091/503-62-88)', '',to_date(NULLIF('', ''),  'DD.MM.YYYY' ),to_date(NULLIF('', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Plivački klub osoba s invaliditetom Sv. Nikola', 'Benka Benkovića 3',1, '023/323-400','','023/323-777','02430568', '38060095051','13001055', '0188384', 'Marin Raspović (099/578-40-46)', 'E. Šahinović (099/723-34-15)',to_date(NULLIF('01.01.2008', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Stolnoteniski klub osoba s invaliditetom Sv. Krševan', 'Benka Benkovića 3',1, '023/323-400','','023-323-777','02336251', '33677229477','13001003', '0188392', 'Miroslav Barić (092/159-81-76)', '',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Streljački klub osoba s invaliditetom Donat', 'Benka Benkovića 3',1, '023/323-400','','023/323-777','02180839', '76270962910','13000903', '0188417', 'Saša Grdović (098/417-401)', '',to_date(NULLIF('01.01.2010', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Ribolovni klub osoba s invaliditetom Škartoc', 'Benka Benkovića 3',1, '023/323-400','','023/323-777','02093146', '89102866393','13000869', '0188433', 'Zoran Radić (099/250-76-66)', 'Elfad Šahinović (099/337-34-15)',to_date(NULLIF('01.01.2010', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Športski savez osoba s invaliditetom Zadarske županije', 'Benka Benkovića 3',1, '023/323-400','','023/323-777','02438887', '79668532267','130000929', '0188425', 'Saša Grdović (098/417-401)', 'Dražen Žilić',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Kendo klub "Ouka"', 'Domovinskog rata 6',1, '098-665-752','','','01841823', '52434249167','13000740', '0329744', 'Marko Lukić (098/665-752)', 'Toni Tadić (099/835-05-08)',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Pikado Savez Zadarske župnije', 'Vukovarska 1/c',1, '023/316-400','','023/316-400','02939622', '82956603468','13001429', '0229577', 'Jure Jerak (091/433-23-66)', 'Ante Frleta (981/55-94-445)',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Udruga za "Down sindrom" Zadarske županije', 'Bartula Kašića 3',1, '023/636-603','','023/636-603','02238454', '93166915044','13000938', '0041759', 'Suzana Periša (095/906-3956)', 'Tanja Radman (099/218-74-90)',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Ronilački klub 2 dive', 'Branimirova obala 4 f',1, '','','','02349710', '712619004259','13001009', '0373883', 'Božidar Matković (091/500-0419)', 'Andrija Rogić (099/913-27-61)',to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2020', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Društvo športske rekreacije Brodarica', 'Ivana Gundulića 1D',1, '091/191-22-01','','','02804808', '94926662660','13001323', '0190254', 'Hrvoje Medved (091/191-22-01)', 'Vladimir Gambiraža',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Društvo športske rekreacije Zadar', 'Oko Vrulja 8',1, '092/304-67-63','','','4021096', '14511512212','13001460', '0283787', 'Roko Dujić (092-304-67-63)', '',to_date(NULLIF('', ''),  'DD.MM.YYYY' ),to_date(NULLIF('', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Društvo športske rekreacije Petrčane', 'Petrčane Ulica 18',1, '092/217-36-14','','','04027329', '09059794368','13001437', '0243751', 'Duje Stanišić (092/2173-614)', '',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Društvo športske rekreacije Osmijeh za-Dar', 'Braće Bilšić 4',1, '095/579-91-30','','','02434024', '69082966145','13001061', '0151554', 'Borut Gatara (095/ 579-91-30)', 'Sandra Profaca',to_date(NULLIF('', ''),  'DD.MM.YYYY' ),to_date(NULLIF('', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Društvo športske rekreacije Novi Bokanjac', 'Poljana Jaroslava Šidaka 17',1, '098/309-169','','','01696947', '74659664033','', '', 'Tomislav Dešpoja (098/309-164)', 'Antonio Zelić (091/238-52-48)  Kontakt osoba: Nikola Zrilić (098/843-593)',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Društvo športske rekreacije Kožino', 'Put Zgona 5',1, '023/343-421','','023/343-421','01882309', '32230828872','1300751', '0073504', 'Ivan Stručić (098-339-369)', 'Edi Tokić (091/2831-488)',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Društvo športske rekreacije Dragovoljac', 'Mihe Klaića 4',1, '023/315-546  ','098/330-095','023/315-546','4213157', '31607309251','0013001566', '0274802', 'Nino Rašin', 'Venci Kvartuč',to_date(NULLIF('', ''),  'DD.MM.YYYY' ),to_date(NULLIF('', ''),  'DD.MM.YYYY' ), to_date(NULLIF('', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Društvo športske rekreacije Crvene kuće', 'Antuna Dobronića 12',1, '095/905-28-81','','023/332-104','02640481', '05748485522','13001211', '0192491', 'Mate Marić (095/905-28-81)', 'Marko Tadić',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('29.04.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Društvo Bokanjac Zadar', 'Trg Sv. Šimuna i Tadije 1',1, '023/221-575','','023/221-575','014228812', '32014543847','13000375', '0151485', 'Ivica Mrkić (095/910-04-52)', '',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Društvo športske rekreacije Bili Brig', 'Put Pudarice 11 J',1, '092/4067-090','','','02240530', '76996713327','13000834', '0346123', 'Mario Barišić', 'Šime Bašić',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Društvo športske rekreacije Loptica', 'Put Stanova BB',1, '098/685-502','','','1736230', '17626883048','13000674', '0127961', 'Marinko Miočić', 'Ana Miočić',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Udruga mladih Ista', 'Ist 126',1, '098/967-04-72  ','097/791-05-72','','02345854', '33334206740','13000986', '0041734', 'Luka Kozulić (098/967-04-72)', 'Josipa Kozulić (098/913-44-13)',to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Zadarska plesna udruga Tornadele', 'Poljana Dragutina Gorjanovića Krambergera 28',1, '091/356-97-72','','','02644703', '68451911353','13001195', '0164471', 'Helena Dobrović', 'Valentina Beneta',to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2019', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Kyudo udruga Yumi - Zadar', 'Miroslava Krleže 16',1, '091/570-27-10','','','02489945', '33116958949','09000455', '0255149', 'Goran Blažević (091/570-27-10)', 'Alen Lonić (099/217-78-97)',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Ženski nogometni klub Donat', 'Put Bajla 4A',1, '098/429-608','','023/312-598','4401352', '56219903028','13001655', '0339839', 'Edvin Šimunov (098/429-608)', 'Doris Kalmeta (099/305-25-25)',to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2019', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Malonogometni klub Bili Brig', 'Put Pudarice 11A',1, '023/778791','','023/778791','02556472', '93488686922','13001133', '0308611', 'Josip Lončar (091/161-37-04)', 'Marko Jurjević Škopinić (098/966-52-77)',to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2019', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Malonogometni klub Plovanija', 'Nikole Tesle 63',1, '023/231-755','','023/231-755','02285690', '66570453161','13000980', '0073580', 'Martin Baričević (091/579-3351)', 'Željko Skukan (098/212-235)',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Malonogometni klub Voštarnica', 'Velebitska 20',1, '098/962-29-18','','','04276337', '19610029717','13001609', '0283228', 'Šime Erlić (098/962-29-18)', 'Ivan Erceg',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Malonogometni klub Ploča', 'Sv. Nikole Tavelića 8',1, '091/575-83-76','','','2264218', '60049621539','13000965', '', 'Željko Gravić  (091/575-83-76)', 'Marko Pilipović',to_date(NULLIF('01.01.2007', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Malonogometni klub Stanovi', 'Rine Aras 2',1, '023/318-339','','023/318-339','01818686', '98871200553','13000708', '0243756', 'Toni Lovrić (095/557-52-42)', 'Luka Zubčić (091/731-32-28)',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Malonogometni klub Sv. Ante', 'Kliška 12',1, '095/381-37-10','','','01375687', '47146690100','13000080', '0342073', 'Krešimir Zrilić', 'Ante Vukorepa',to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2019', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Malonogometni klub Zapuntel', 'Luke Botića 26',1, '023/316-526  ','095/912-11-94','','02261260', '29723903297','13000959', '0103986', 'Neven Petrović (095/912-11-94)', 'Marin Petričić (098/1853-919)',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Malonogometni klub Mala Rava', 'Mala Rava',1, '098/922-86-83  ','091/144-02-48','','02211483', '16335604791','13000935', '0186962', 'Vladimir Gambiraža (098/929-08-31)', 'Danijel Simičić',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Rukometni klub Zadar 2013', 'Andrje Maurovića 5',1, '095/860-92-01','','023/241909','04113225', '41853702244','13001506', '0254317', 'Anđelo Šare (095/ 860-92-01)', 'Barbara Kardum',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Jedriličarski klub Punta Bajlo', 'Put Bajla BB',1, '098/369-318','','','1602861', '69933257401','13000560', '0136670', 'Mladen Bajlo', 'Darko Smrkinić',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Plivački klub osoba s invaliditetom Frogo', 'Ivana Zadranina 3D',1, '095/359-70-55','','','04395549', '42484305521','13001652', '0331558', 'Sanja Sedić', 'Silva Žabo',to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2019', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Hrvatsko planinarsko društvo Zavrata', 'Molatska 11',1, '091/600-00-31  ','098/981-73-22','','04336593', '02298099199','13001627', '0342260', 'Nikola Stilinović', 'Ante Pušić',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Udruga Hrvatskih vojnih invalida domovinskog rata - Zadar', 'Benka Benkovića 3',1, '023/323-400','','023/3323-777','03926281', '07666214502','13000103', '0073669', 'Ivica Arbanas (099/222-75-00)', 'Denis Simičić (099/223-36-81)',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Aero klub Zadar', 'Put Bokanjca 26',1, '023/322-355','','023/322-355','03154823', '49059640059','13000131', '0073725', 'Bjanka Pavin (098/273-809)', 'Velid Jakupović (091/523-85-98)',to_date(NULLIF('01.01.2013', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2017', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Boćarski Športski klub Zadar', 'Put Pudarice 11 C',1, '023/214-093','','023/214-093','03450848', '03836929990','13000253', '0217137', 'Milivoj Colić (098/196-27-65)', 'Šime Buterin (098/175-14-97)',to_date(NULLIF('01.01.2008', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ), to_date(NULLIF('01.01.1996', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('GRADSKI OGRANAK UDRUGE HRVATSKIH DRAGOVOLJACA DOMOVINSKOG RATA GRADA ZADRA', 'Mihe Klaića 4',1, '023/315-546','','023/315-546','4213157', '31607309251','0013001566', '0274802', 'Nino Rašin (091/524-30-72)', 'Venci Kvartuč (098/330-095)',to_date(NULLIF('01.01.2014', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2018', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Malonogometni klub Brodarica', 'Iva Gundulića 1D',1, '098/-990-73-99','','','2458594', '08244505631','13001082', '', 'Hrvoje Medved  (098/-990-73-99)', 'Denis Virovac',to_date(NULLIF('01.01.2012', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2016', ''),  'DD.MM.YYYY' ), to_date(NULLIF('12.10.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Kick-Boxing klub Fight Factory', 'I. Mažuranića 32',1, '091/7859200','','','4489071', '13014500930','13001699', '0354938', 'Željko Dilber', 'Tajnik: Denis Kardum / Ovlaštena osoba: Ante Verunica (091/7859200)',to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2019', ''),  'DD.MM.YYYY' ), to_date(NULLIF('08.12.2015', ''),  'DD.MM.YYYY' ), '1');
insert into diskobolos.member_register (name, address, location_id, phone1, phone2, fax, identification_number, oib, register_number, number_of_non_profit_org, chairman, secretary, date_from, date_to, registration_date, membership_category) values ('Planinarsko društvo Sveti Bernard', 'Palih rodoljuba 12',1, '091/761 25 39','','','02851245', '04176260415','13001362', '0219130', 'Neven Zrilić (091/761-25-39 )', 'Šime Jukić (095/910-25-44)',to_date(NULLIF('01.01.2011', ''),  'DD.MM.YYYY' ),to_date(NULLIF('01.01.2015', ''),  'DD.MM.YYYY' ), to_date(NULLIF('', ''),  'DD.MM.YYYY' ), '1');

-- inserting data into bank_account
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR1524070001100044579', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 1);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR1524070001100043027', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 2);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR1023400091110344402', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 3);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3225000091101213227', ''), NULLIF('IBAN', ''),NULLIF('Addiko Bank d.d.', ''), 4);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR9724840081104990011', ''), NULLIF('IBAN', ''),NULLIF('Raiffeisenbank Austria d.d.', ''), 5);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR0924840081107053718', ''), NULLIF('IBAN ', ''),NULLIF('Raiffeisenbank Austria d.d.', ''), 6);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('2407000-1100043027', ''), NULLIF('ACCOUNT_NUMBER', ''),NULLIF('OTP banka d.d.', ''), 6);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR0524070001100144863', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 7);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR4524070001100334651', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 8);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR7124840081105264795', ''), NULLIF('IBAN', ''),NULLIF('Raiffeisenbank Austria d.d.', ''), 9);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3024840081104996552', ''), NULLIF('IBAN', ''),NULLIF('Raiffeisenbank Austria d.d.', ''), 10);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR6224850031100224679', ''), NULLIF('IBAN', ''),NULLIF('Croatia banka d.d.', ''), 11);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR6524070001100074234', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 12);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR9623400091110374670', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 13);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR4624070001100173128', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 14);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3925000091101026182', ''), NULLIF('IBAN', ''),NULLIF('Addiko Bank d.d.', ''), 15);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3724070001100086475', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 16);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3923300031100006743', ''), NULLIF('IBAN', ''),NULLIF('Societe Generale - Splitska banka d.d.', ''), 17);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR8124070001100044458', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 18);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR0323300031100437295', ''), NULLIF('IBAN', ''),NULLIF('Societe Generale - Splitska banka d.d.', ''), 19);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR8124070001100045137', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 20);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR8224070001100044925', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 21);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR5024840081100185574', ''), NULLIF('IBAN', ''),NULLIF('Raiffeisenbank Austria d.d.', ''), 22);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR8924070001100045090', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 23);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR4423400091110163952', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 24);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR2424070001100381007', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 25);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR5324070001100164369', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 26);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR9223300031100403295', ''), NULLIF('IBAN', ''),NULLIF('Societe Generale - Splitska banka d.d.', ''), 27);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR8325000091101299027', ''), NULLIF('IBAN', ''),NULLIF('Addiko Bank d.d.', ''), 28);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR0224070001100043746', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 29);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR8823400091110032792', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 30);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR4224070001100136437', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 31);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3724070001100043019', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 32);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR2724070001100043490', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 33);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR5124840081100207284', ''), NULLIF('IBAN', ''),NULLIF('Raiffeisenbank Austria d.d.', ''), 34);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR7724070001100043754', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 35);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR7724070001100043754', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 36);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR4223600001101392904', ''), NULLIF('IBAN', ''),NULLIF('ZAGREBAČKA BANKA D.D.', ''), 37);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR8723400091110337584', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 38);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR2023300031100016785', ''), NULLIF('IBAN', ''),NULLIF('Societe Generale - Splitska banka d.d.', ''), 39);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR0425000091101025454', ''), NULLIF('IBAN', ''),NULLIF('Addiko Bank d.d.', ''), 40);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR6023400091110024081', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 41);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR1424070001100043336', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 42);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3324070001100317407', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 43);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR8924070001100118907', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 44);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR5724070001100088055', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 45);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR5124920081100006956 ', ''), NULLIF('IBAN', ''),NULLIF('Imex banka d.d.', ''), 46);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR4524840081101651431', ''), NULLIF('IBAN', ''),NULLIF('Raiffeisenbank Austria d.d.', ''), 47);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('', ''), NULLIF('', ''),NULLIF('', ''), 48);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR5524070001100044538', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 49);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR5924070001100175319', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 50);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR8324070001100345316', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 51);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR1623400091110052033', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 52);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR0524850031100242777', ''), NULLIF('IBAN', ''),NULLIF('Croatia banka d.d.', ''), 52);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR0724070001100385678', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 53);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR5524070001100137270 ', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 54);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR9423400091110267909', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 55);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR9324840081102155258', ''), NULLIF('IBAN', ''),NULLIF('Raiffeisenbank Austria d.d.', ''), 56);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3324070001100043867', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 57);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR6424070001100332078', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 58);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR9823400091110431088', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 59);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3523400091110398466', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 60);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR7124020061100088942', ''), NULLIF('IBAN', ''),NULLIF('Erste & Steiermärkische Bank d.d.', ''), 61);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR5824020061100504821', ''), NULLIF('IBAN', ''),NULLIF('Erste & Steiermärkische Bank d.d.', ''), 62);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR9824070001100044152', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 63);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR1224840081106298131', ''), NULLIF('IBAN', ''),NULLIF('Raiffeisenbank Austria d.d.', ''), 64);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR8524020061100099748', ''), NULLIF('IBAN', ''),NULLIF('Erste & Steiermärkische Bank d.d.', ''), 65);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR8425000091101267872', ''), NULLIF('IBAN', ''),NULLIF('Addiko Bank d.d.', ''), 66);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR5124840081100207284', ''), NULLIF('IBAN', ''),NULLIF('Raiffeisenbank Austria d.d.', ''), 67);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR9023300031100414971', ''), NULLIF('IBAN', ''),NULLIF('Societe Generale - Splitska banka d.d.', ''), 68);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR7424840081101451768', ''), NULLIF('IBAN', ''),NULLIF('Raiffeisenbank Austria d.d.', ''), 69);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR9523400091110279919', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 70);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3824850031100223268', ''), NULLIF('IBAN', ''),NULLIF('Croatia banka d.d.', ''), 71);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3824070001100330509', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 72);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR9623300031152294440', ''), NULLIF('IBAN', ''),NULLIF('Societe Generale - Splitska banka d.d.', ''), 73);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR0224070001100122219', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 74);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR8624070001100350694', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 75);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3624070001100046335', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 76);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR2523400091110471590', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 77);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR9623400091110403188', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 78);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR4123300031100321375', ''), NULLIF('IBAN', ''),NULLIF('Societe Generale - Splitska banka d.d.', ''), 79);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR4624850031100255134', ''), NULLIF('IBAN', ''),NULLIF('Croatia banka d.d.', ''), 80);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR2124070001100004410', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 81);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR9024070001100045848', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 82);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR6324070001100045073', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 83);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR1724840081103487175', ''), NULLIF('IBAN', ''),NULLIF('Raiffeisenbank Austria d.d.', ''), 84);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR2124070001100130704', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 85);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR4624070001100002311', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 86);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR8324070001100345025', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 87);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR6124020061100532121', ''), NULLIF('IBAN', ''),NULLIF('Erste & Steiermärkische Bank d.d.', ''), 88);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR0323400091110463935', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 89);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR2124070001100392216', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 90);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR9724070001100393467', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 91);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR9823300031152948087', ''), NULLIF('IBAN', ''),NULLIF('Societe Generale - Splitska banka d.d.', ''), 92);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR6225000091101062240', ''), NULLIF('IBAN', ''),NULLIF('Addiko Bank d.d.', ''), 93);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3625000091101149911', ''), NULLIF('IBAN', ''),NULLIF('Addiko Bank d.d.', ''), 94);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR6223300031100080534', ''), NULLIF('IBAN', ''),NULLIF('Societe Generale - Splitska banka d.d.', ''), 95);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR4024070001100116976', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 96);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3724070001100043795', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 97);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3724070001100122171', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 98);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR4223300031100416223', ''), NULLIF('IBAN', ''),NULLIF('Societe Generale - Splitska banka d.d.', ''), 99);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR0825030071100050585', ''), NULLIF('IBAN', ''),NULLIF(' Sberbank d.d.', ''), 100);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR2323810091156000968 ', ''), NULLIF('IBAN', ''),NULLIF('', ''), 101);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR9723400091110419078', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 102);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3223400091110067362', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 103);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR8823300031100141952', ''), NULLIF('IBAN', ''),NULLIF('Societe Generale - Splitska banka d.d.', ''), 104);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR8924070001100043150', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 105);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR2425000091101025288', ''), NULLIF('IBAN', ''),NULLIF('Addiko Bank d.d.', ''), 106);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR6423400091110330626 ', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 107);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('2390001-1100401252', ''), NULLIF('ACCOUNT_NUMBER', ''),NULLIF('Hrvatska poštanska banka d.d.', ''), 108);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3723900011100401252', ''), NULLIF('IBAN', ''),NULLIF('Hrvatska poštanska banka d.d.', ''), 108);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR5224070001100076699', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 109);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR4024810001128001620', ''), NULLIF('IBAN', ''),NULLIF('Kreditna Banka Zagreb d.d.', ''), 110);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR2124020061100494041', ''), NULLIF('IBAN', ''),NULLIF('Erste & Steiermärkische Bank d.d.', ''), 111);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR0524070001100083171', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 112);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR1024070001100042729', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 113);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR1523400091110681096', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 114);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR9023400091110209587', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 115);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR5224070001100339957', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 116);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3424070001100333976', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 117);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR7724070001100333396', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 118);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR2424070001100330664', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 119);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR2724070001100164352', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 120);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR5124070001100335610', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 121);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR2024070001100137124', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 122);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR1623400091110562350', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 123);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR9723400091110283763', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 124);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('', ''), NULLIF('', ''),NULLIF('', ''), 125);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3824070001100367272', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 126);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR5124070001100392064', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 127);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR8523300031152809999', ''), NULLIF('IBAN', ''),NULLIF('Societe Generale - Splitska banka d.d.', ''), 128);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('', ''), NULLIF('', ''),NULLIF('', ''), 129);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR4324850031100259650', ''), NULLIF('IBAN', ''),NULLIF('Croatia banka d.d.', ''), 130);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR8224070001100139306', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 131);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR6723600001102419393', ''), NULLIF('IBAN', ''),NULLIF('Zagrebačka banka d.d.', ''), 132);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR1424070001100354124', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 133);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR6523900011100266403', ''), NULLIF('IBAN', ''),NULLIF('Hrvatska poštanska banka d.d.', ''), 134);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR4624070001100410972', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 135);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR4125030071100073174', ''), NULLIF('IBAN', ''),NULLIF(' Sberbank d.d.', ''), 136);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR5523400091110319245', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 137);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3025030071100061635', ''), NULLIF('IBAN', ''),NULLIF(' Sberbank d.d.', ''), 138);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR1323400091110447847', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 139);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR1224810001128003676', ''), NULLIF('IBAN', ''),NULLIF('Kreditna Banka Zagreb d.d.', ''), 140);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR3624020061100559149', ''), NULLIF('IBAN', ''),NULLIF('Erste & Steiermärkische Bank d.d.', ''), 141);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR2524070001100321140 ', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 142);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR4323400091110680786', ''), NULLIF('IBAN', ''),NULLIF('Privredna banka d.d.', ''), 143);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR1324840081104342302', ''), NULLIF('IBAN', ''),NULLIF('Raiffeisenbank Austria d.d.', ''), 144);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR0524070001100133514', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 145);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR2023900011100266340', ''), NULLIF('IBAN', ''),NULLIF('Hrvatska poštanska banka d.d.', ''), 146);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR9024840081104323553', ''), NULLIF('IBAN', ''),NULLIF('Raiffeisenbank Austria d.d.', ''), 147);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR6524070001100173562', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 148);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR2825000091101404001', ''), NULLIF('IBAN', ''),NULLIF('Addiko Bank d.d.', ''), 149);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR9124020061100082568', ''), NULLIF('IBAN', ''),NULLIF('Erste & Steiermärkische Bank d.d.', ''), 150);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR1024020061100744402', ''), NULLIF('IBAN', ''),NULLIF('Erste & Steiermärkische Bank d.d.', ''), 151);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR8124840081107220426', ''), NULLIF('IBAN', ''),NULLIF('Raiffeisenbank Austria d.d.', ''), 152);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR7824070001100044706', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 153);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR1524070001100043221', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 154);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR8124070001100044458', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 155);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR6723600001102419393', ''), NULLIF('IBAN', ''),NULLIF('Zagrebačka banka d.d.', ''), 156);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR2724020061100542045', ''), NULLIF('IBAN', ''),NULLIF('Erste & Steiermärkische Bank d.d.', ''), 157);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('HR0224070001100440282 ', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 158);
insert into diskobolos.bank_account (account_number, account_type, account_description, member_register_id) values (NULLIF('', ''), NULLIF('IBAN', ''),NULLIF('OTP banka d.d.', ''), 159);

-- inserting data into sport
insert into diskobolos.sport (name) values ( 'ATLETIKA');
insert into diskobolos.sport (name) values ( 'AIKIDO');
insert into diskobolos.sport (name) values ( 'AKROBATSKI ROCK''N''ROLL');
insert into diskobolos.sport (name) values ( 'AMERIČKI NOGOMET (FOOTBALL)');
insert into diskobolos.sport (name) values ( 'AUTOMOBILIZAM');
insert into diskobolos.sport (name) values ( 'BADMINTON');
insert into diskobolos.sport (name) values ( 'BANDY');
insert into diskobolos.sport (name) values ( 'BASEBALL');
insert into diskobolos.sport (name) values ( 'BIATLON');
insert into diskobolos.sport (name) values ( 'BICIKLIZAM');
insert into diskobolos.sport (name) values ( 'BILJAR');
insert into diskobolos.sport (name) values ( 'BOB');
insert into diskobolos.sport (name) values ( 'BOĆANJE');
insert into diskobolos.sport (name) values ( 'BODY BUILDING');
insert into diskobolos.sport (name) values ( 'BOKS');
insert into diskobolos.sport (name) values ( 'BRIDŽ');
insert into diskobolos.sport (name) values ( 'CASTING');
insert into diskobolos.sport (name) values ( 'CHEER/CHEERLEADING/NAVIJANJE');

-- inserting data into nomenclature_of_sport
insert into diskobolos.nomenclature_of_sport (sport_id, category, category_description, value) values ( (select id from diskobolos.sport where name = 'ATLETIKA'),  'NATIONAL_SPORTS_FEDERATION', 'NACIONALNI SPORTSKI SAVEZ/članstvo u HOO-u', 'Hrvatski atletski savez');
insert into diskobolos.nomenclature_of_sport (sport_id, category, category_description, value) values ( (select id from diskobolos.sport where name = 'ATLETIKA'),  'NATIONAL_SPORTS_FEDERATION', 'NACIONALNI SPORTSKI SAVEZ/članstvo u HOO-u', 'HOO');
insert into diskobolos.nomenclature_of_sport (sport_id, category, category_description, value) values ( (select id from diskobolos.sport where name = 'ATLETIKA'),  'INTERNATIONAL_FEDERATION', 'MEĐUNARODNA FEDERACIJA', 'IAAF');
insert into diskobolos.nomenclature_of_sport (sport_id, category, category_description, value) values ( (select id from diskobolos.sport where name = 'ATLETIKA'),  'IOC_SPORTACCORD', 'Priznati od IOC-a, članovi SPORTACCORD-a', 'IOC');
insert into diskobolos.nomenclature_of_sport (sport_id, category, category_description, value) values ( (select id from diskobolos.sport where name = 'ATLETIKA'),  'IOC_SPORTACCORD', 'Priznati od IOC-a, članovi SPORTACCORD-a', 'SPORRTACCORD');
insert into diskobolos.nomenclature_of_sport (sport_id, category, category_description, value) values ( (select id from diskobolos.sport where name = 'AIKIDO'),  'NATIONAL_SPORTS_FEDERATION', 'NACIONALNI SPORTSKI SAVEZ/članstvo u HOO-u', 'Hrvatski aikido savez');
insert into diskobolos.nomenclature_of_sport (sport_id, category, category_description, value) values ( (select id from diskobolos.sport where name = 'AIKIDO'),  'INTERNATIONAL_FEDERATION', 'MEĐUNARODNA FEDERACIJA', 'IAF');
insert into diskobolos.nomenclature_of_sport (sport_id, category, category_description, value) values ( (select id from diskobolos.sport where name = 'AIKIDO'),  'IOC_SPORTACCORD', 'Priznati od IOC-a, članovi SPORTACCORD-a', 'SPORRTACCORD');
insert into diskobolos.nomenclature_of_sport (sport_id, category, category_description, value) values ( (select id from diskobolos.sport where name = 'AKROBATSKI ROCK''N''ROLL'),  'NATIONAL_SPORTS_FEDERATION', 'NACIONALNI SPORTSKI SAVEZ/članstvo u HOO-u', 'Hrvatski rock''n''roll savez');
insert into diskobolos.nomenclature_of_sport (sport_id, category, category_description, value) values ( (select id from diskobolos.sport where name = 'AKROBATSKI ROCK''N''ROLL'),  'NATIONAL_SPORTS_FEDERATION', 'NACIONALNI SPORTSKI SAVEZ/članstvo u HOO-u', 'HOO');
insert into diskobolos.nomenclature_of_sport (sport_id, category, category_description, value) values ( (select id from diskobolos.sport where name = 'AKROBATSKI ROCK''N''ROLL'),  'INTERNATIONAL_FEDERATION', 'MEĐUNARODNA FEDERACIJA', 'WRRC');
insert into diskobolos.nomenclature_of_sport (sport_id, category, category_description, value) values ( (select id from diskobolos.sport where name = 'AKROBATSKI ROCK''N''ROLL'),  'INTERNATIONAL_FEDERATION', 'MEĐUNARODNA FEDERACIJA', 'IDSF');
insert into diskobolos.nomenclature_of_sport (sport_id, category, category_description, value) values ( (select id from diskobolos.sport where name = 'AKROBATSKI ROCK''N''ROLL'),  'IOC_SPORTACCORD', 'Priznati od IOC-a, članovi SPORTACCORD-a', 'IOC');
insert into diskobolos.nomenclature_of_sport (sport_id, category, category_description, value) values ( (select id from diskobolos.sport where name = 'AKROBATSKI ROCK''N''ROLL'),  'IOC_SPORTACCORD', 'Priznati od IOC-a, članovi SPORTACCORD-a', 'SPORRTACCORD');

-- inserting data into email
insert into diskobolos.email (email, member_register_id) values (NULLIF('info@aikido-zadar.hr', ''), 1);
insert into diskobolos.email (email, member_register_id) values (NULLIF('nenad.vertovsek@gmail.com ', ''), 1);
insert into diskobolos.email (email, member_register_id) values (NULLIF('vesna.vertovsek@gmail.com', ''), 1);
insert into diskobolos.email (email, member_register_id) values (NULLIF('zad@has.hr', ''), 2);
insert into diskobolos.email (email, member_register_id) values (NULLIF('emilijo.krstic@xnet.hr', ''), 2);
insert into diskobolos.email (email, member_register_id) values (NULLIF('helena.vulic@yahoo.com', ''), 2);
insert into diskobolos.email (email, member_register_id) values (NULLIF('memory.trade@zd.t-com.hr', ''), 2);
insert into diskobolos.email (email, member_register_id) values (NULLIF('renata.ruic@zd.t-com.hr', ''), 3);
insert into diskobolos.email (email, member_register_id) values (NULLIF('akastepinac@net.hr', ''), 4);
insert into diskobolos.email (email, member_register_id) values (NULLIF('pere.korica@gmail.com', ''), 5);
insert into diskobolos.email (email, member_register_id) values (NULLIF('sportskaskola.start@gmail.com', ''), 6);
insert into diskobolos.email (email, member_register_id) values (NULLIF('kavzadar@net.hr', ''), 7);
insert into diskobolos.email (email, member_register_id) values (NULLIF('info@ak-zdtuning.hr', ''), 8);
insert into diskobolos.email (email, member_register_id) values (NULLIF('iaderbk@gmail.com', ''), 9);
insert into diskobolos.email (email, member_register_id) values (NULLIF('bkdonat@gmail.com', ''), 10);
insert into diskobolos.email (email, member_register_id) values (NULLIF('donat.cycling@gmail.com', ''), 11);
insert into diskobolos.email (email, member_register_id) values (NULLIF('neven.pavic1@zd.t-com.hr', ''), 12);
insert into diskobolos.email (email, member_register_id) values (NULLIF(' info@bkzadar.hr', ''), 12);
insert into diskobolos.email (email, member_register_id) values (NULLIF('info@bk-macaklin.hr', ''), 13);
insert into diskobolos.email (email, member_register_id) values (NULLIF('cedomir.perincic@yahoo.com', ''), 14);
insert into diskobolos.email (email, member_register_id) values (NULLIF('bk.bilibrig@gmail.com', ''), 15);
insert into diskobolos.email (email, member_register_id) values (NULLIF('goran.lijic@zd.t-com.hr', ''), 15);
insert into diskobolos.email (email, member_register_id) values (NULLIF('bocarski.savez.zdz@vip.hr', ''), 16);
insert into diskobolos.email (email, member_register_id) values (NULLIF('marija-apartmani@net.hr', ''), 17);
insert into diskobolos.email (email, member_register_id) values (NULLIF('', ''), 18);
insert into diskobolos.email (email, member_register_id) values (NULLIF('', ''), 19);
insert into diskobolos.email (email, member_register_id) values (NULLIF('info@boks-diabolik.hr', ''), 20);
insert into diskobolos.email (email, member_register_id) values (NULLIF('damir.zrilic@zd.t-com.hr', ''), 20);
insert into diskobolos.email (email, member_register_id) values (NULLIF('dragan.vidaic@optinet.hr', ''), 21);
insert into diskobolos.email (email, member_register_id) values (NULLIF('borislav.mikulic@zd.t-com.hr', ''), 21);
insert into diskobolos.email (email, member_register_id) values (NULLIF('office@jkuskok.hr', ''), 22);
insert into diskobolos.email (email, member_register_id) values (NULLIF('tajnik@jkuskok.hr', ''), 22);
insert into diskobolos.email (email, member_register_id) values (NULLIF('tonko@globalnet.hr', ''), 22);
insert into diskobolos.email (email, member_register_id) values (NULLIF('antezanki@net.hr', ''), 23);
insert into diskobolos.email (email, member_register_id) values (NULLIF('marinovic@zd.t-com.hr', ''), 24);
insert into diskobolos.email (email, member_register_id) values (NULLIF('k.k.zadar@zd.t-com.hr', ''), 25);
insert into diskobolos.email (email, member_register_id) values (NULLIF('kk.donatzastita@gmail.com', ''), 26);
insert into diskobolos.email (email, member_register_id) values (NULLIF('marin.colic.19@gmail.com', ''), 26);
insert into diskobolos.email (email, member_register_id) values (NULLIF('ivan.lulic.mehanizacija@dealer.renault.hr', ''), 27);
insert into diskobolos.email (email, member_register_id) values (NULLIF('ms.odzakovic@gmail.com', ''), 27);
insert into diskobolos.email (email, member_register_id) values (NULLIF('nk-zadar1@zd.hinet.hr', ''), 28);
insert into diskobolos.email (email, member_register_id) values (NULLIF('robertkresoja@yahoo.com', ''), 29);
insert into diskobolos.email (email, member_register_id) values (NULLIF('planinari.paklenica@gmail.com', ''), 30);
insert into diskobolos.email (email, member_register_id) values (NULLIF('info@pkzadar.hr', ''), 31);
insert into diskobolos.email (email, member_register_id) values (NULLIF('tajnik@pkzadar.hr', ''), 31);
insert into diskobolos.email (email, member_register_id) values (NULLIF('kpa.zadar1@zd.t-com.hr', ''), 32);
insert into diskobolos.email (email, member_register_id) values (NULLIF('kpazadar@gmail.com', ''), 32);
insert into diskobolos.email (email, member_register_id) values (NULLIF('dakarlov72@gmail.com', ''), 33);
insert into diskobolos.email (email, member_register_id) values (NULLIF('streljacki.klub.zadar.@zd.t-com.hr', ''), 34);
insert into diskobolos.email (email, member_register_id) values (NULLIF('sk.zadar@gmail.com', ''), 35);
insert into diskobolos.email (email, member_register_id) values (NULLIF('zubatac.zadar@gmail.com', ''), 36);
insert into diskobolos.email (email, member_register_id) values (NULLIF('', ''), 37);
insert into diskobolos.email (email, member_register_id) values (NULLIF('lovro_r@yahoo.com', ''), 38);
insert into diskobolos.email (email, member_register_id) values (NULLIF('ibratano@inet.hr', ''), 39);
insert into diskobolos.email (email, member_register_id) values (NULLIF('jadran-zadar@net.hr', ''), 40);
insert into diskobolos.email (email, member_register_id) values (NULLIF('zd.sport@gmail.com', ''), 41);
insert into diskobolos.email (email, member_register_id) values (NULLIF('zrk.zadar@gmail.com', ''), 42);
insert into diskobolos.email (email, member_register_id) values (NULLIF('nelamitrovic18@gmail.com', ''), 42);
insert into diskobolos.email (email, member_register_id) values (NULLIF('zlatka.badel@zd.t-com.hr', ''), 43);
insert into diskobolos.email (email, member_register_id) values (NULLIF('zgon@dsr-diklo.hr', ''), 44);
insert into diskobolos.email (email, member_register_id) values (NULLIF('dusko.paulin@zd.t-com.hr', ''), 45);
insert into diskobolos.email (email, member_register_id) values (NULLIF('mkozul1@net.hr', ''), 46);
insert into diskobolos.email (email, member_register_id) values (NULLIF('tklarica@yahoo.com', ''), 47);
insert into diskobolos.email (email, member_register_id) values (NULLIF('mpaunovic@gmail.com', ''), 48);
insert into diskobolos.email (email, member_register_id) values (NULLIF('gogahorvatic@yahoo.com', ''), 49);
insert into diskobolos.email (email, member_register_id) values (NULLIF('info@pk-jadera.hr', ''), 50);
insert into diskobolos.email (email, member_register_id) values (NULLIF('tkzadar@gmail.com', ''), 51);
insert into diskobolos.email (email, member_register_id) values (NULLIF('taekwondozadar@gmail.com', ''), 52);
insert into diskobolos.email (email, member_register_id) values (NULLIF('kkv.petbunara@gmail.com', ''), 53);
insert into diskobolos.email (email, member_register_id) values (NULLIF('rukometni.klub.zadar@zd.t-com.hr', ''), 54);
insert into diskobolos.email (email, member_register_id) values (NULLIF('igo.nikolic@gmail.com', ''), 54);
insert into diskobolos.email (email, member_register_id) values (NULLIF('franjo.glavas@optinet.hr', ''), 55);
insert into diskobolos.email (email, member_register_id) values (NULLIF('', ''), 56);
insert into diskobolos.email (email, member_register_id) values (NULLIF('zaklinajelaca@yahoo.com', ''), 57);
insert into diskobolos.email (email, member_register_id) values (NULLIF('krgsirenazd@gmail.com', ''), 58);
insert into diskobolos.email (email, member_register_id) values (NULLIF('autoklubrtz@gmail.com', ''), 59);
insert into diskobolos.email (email, member_register_id) values (NULLIF('olib888@net.hr', ''), 60);
insert into diskobolos.email (email, member_register_id) values (NULLIF('gaga03@net.hr', ''), 61);
insert into diskobolos.email (email, member_register_id) values (NULLIF('skola.kosarke.zadar@zd.t-com.hr', ''), 62);
insert into diskobolos.email (email, member_register_id) values (NULLIF('', ''), 63);
insert into diskobolos.email (email, member_register_id) values (NULLIF('martino@email.t-com.hr', ''), 64);
insert into diskobolos.email (email, member_register_id) values (NULLIF('mnkpuntamika2002@gmail.com', ''), 65);
insert into diskobolos.email (email, member_register_id) values (NULLIF('vedrana.plesic@zd.htnet.hr', ''), 66);
insert into diskobolos.email (email, member_register_id) values (NULLIF('strelicarski.klub.zadar@gmail.com', ''), 67);
insert into diskobolos.email (email, member_register_id) values (NULLIF('zadarrugby@gmail.com', ''), 68);
insert into diskobolos.email (email, member_register_id) values (NULLIF('goran.jovancevic@hac-onc.hr', ''), 69);
insert into diskobolos.email (email, member_register_id) values (NULLIF('msutlovic1@yahoo.com', ''), 70);
insert into diskobolos.email (email, member_register_id) values (NULLIF('casper_zd@net.hr', ''), 71);
insert into diskobolos.email (email, member_register_id) values (NULLIF('', ''), 72);
insert into diskobolos.email (email, member_register_id) values (NULLIF('', ''), 73);
insert into diskobolos.email (email, member_register_id) values (NULLIF('jadranka.duvancic@zd.t-com.hr', ''), 74);
insert into diskobolos.email (email, member_register_id) values (NULLIF('hpdmalarava@gmail.com', ''), 75);
insert into diskobolos.email (email, member_register_id) values (NULLIF('emagazin@gmail.com', ''), 75);
insert into diskobolos.email (email, member_register_id) values (NULLIF('slobodan.erslan@liburnija-zadar.hr', ''), 76);
insert into diskobolos.email (email, member_register_id) values (NULLIF('vladimir.mencik@gmail.com', ''), 77);
insert into diskobolos.email (email, member_register_id) values (NULLIF('ditezadarsko@yahoo.com', ''), 78);
insert into diskobolos.email (email, member_register_id) values (NULLIF('okdonat@gmail.com', ''), 79);
insert into diskobolos.email (email, member_register_id) values (NULLIF('info@kdpdonat.hr', ''), 80);
insert into diskobolos.email (email, member_register_id) values (NULLIF('damir.veledar@gmail.com', ''), 81);
insert into diskobolos.email (email, member_register_id) values (NULLIF('damir.veledar@ozonsport.hr', ''), 82);
insert into diskobolos.email (email, member_register_id) values (NULLIF('relax.katja@gmail.com', ''), 83);
insert into diskobolos.email (email, member_register_id) values (NULLIF('arno.longin@yahoo.com', ''), 84);
insert into diskobolos.email (email, member_register_id) values (NULLIF('sv.krsevan@yahoo.com', ''), 85);
insert into diskobolos.email (email, member_register_id) values (NULLIF('sv.krsevan@yahoo.com', ''), 86);
insert into diskobolos.email (email, member_register_id) values (NULLIF('marko.jurin@gmail.com', ''), 87);
insert into diskobolos.email (email, member_register_id) values (NULLIF('kk-borikpuntamika@zd.t-com.hr', ''), 88);
insert into diskobolos.email (email, member_register_id) values (NULLIF('petraa55@hotmail.com', ''), 88);
insert into diskobolos.email (email, member_register_id) values (NULLIF('tomislavtoric@net.hr', ''), 89);
insert into diskobolos.email (email, member_register_id) values (NULLIF('euforijazadar@gmail.com', ''), 90);
insert into diskobolos.email (email, member_register_id) values (NULLIF('rada6@gmail.com', ''), 91);
insert into diskobolos.email (email, member_register_id) values (NULLIF('košarkaškiklubzara@gmail.com', ''), 92);
insert into diskobolos.email (email, member_register_id) values (NULLIF('dijagora@gmail.com', ''), 93);
insert into diskobolos.email (email, member_register_id) values (NULLIF('info@višnjik.hr', ''), 94);
insert into diskobolos.email (email, member_register_id) values (NULLIF('branimir.pericic@gmail.com', ''), 95);
insert into diskobolos.email (email, member_register_id) values (NULLIF('edo.fantela@grad-zadar.hr', ''), 96);
insert into diskobolos.email (email, member_register_id) values (NULLIF('domus-dmg@zd.t-com.hr', ''), 97);
insert into diskobolos.email (email, member_register_id) values (NULLIF('dsalina@net.hr', ''), 98);
insert into diskobolos.email (email, member_register_id) values (NULLIF('samirsedic@gmail.com', ''), 99);
insert into diskobolos.email (email, member_register_id) values (NULLIF('sasa.stipanic@yahoo.com', ''), 99);
insert into diskobolos.email (email, member_register_id) values (NULLIF('zlatnarukavica@gmail.com', ''), 100);
insert into diskobolos.email (email, member_register_id) values (NULLIF('zorantoki@yahoo.com', ''), 101);
insert into diskobolos.email (email, member_register_id) values (NULLIF('taekwondo.plovanija@gmail.com', ''), 102);
insert into diskobolos.email (email, member_register_id) values (NULLIF('dal-maring@zd.t-com.hr', ''), 103);
insert into diskobolos.email (email, member_register_id) values (NULLIF('eponazadar@gmail.com', ''), 104);
insert into diskobolos.email (email, member_register_id) values (NULLIF('unik@unik.hr', ''), 105);
insert into diskobolos.email (email, member_register_id) values (NULLIF('ksvzadar@ksvzadar.hr', ''), 106);
insert into diskobolos.email (email, member_register_id) values (NULLIF('arbanasirk@gmail.com', ''), 107);
insert into diskobolos.email (email, member_register_id) values (NULLIF('stipe.bjelis@gmail.com', ''), 107);
insert into diskobolos.email (email, member_register_id) values (NULLIF('ksr.paprenica@yahoo.com', ''), 108);
insert into diskobolos.email (email, member_register_id) values (NULLIF('', ''), 109);
insert into diskobolos.email (email, member_register_id) values (NULLIF('alpinklub.zadar@gmail.com', ''), 110);
insert into diskobolos.email (email, member_register_id) values (NULLIF('okdonat@gmail.com', ''), 111);
insert into diskobolos.email (email, member_register_id) values (NULLIF('alen.vukosa@zd.t-com.hr', ''), 112);
insert into diskobolos.email (email, member_register_id) values (NULLIF('udruga.slijepih.zadarske-zupanije@zd.t-com.hr', ''), 113);
insert into diskobolos.email (email, member_register_id) values (NULLIF('udruga.osoba.ostecenog.sluha@zd.t-com.hr', ''), 114);
insert into diskobolos.email (email, member_register_id) values (NULLIF('oki.zadar@gmail.com', ''), 115);
insert into diskobolos.email (email, member_register_id) values (NULLIF('', ''), 116);
insert into diskobolos.email (email, member_register_id) values (NULLIF('pkoi.sv.nikola@gmail.com', ''), 117);
insert into diskobolos.email (email, member_register_id) values (NULLIF('', ''), 118);
insert into diskobolos.email (email, member_register_id) values (NULLIF('', ''), 119);
insert into diskobolos.email (email, member_register_id) values (NULLIF('mico.elfad@gmail.com', ''), 120);
insert into diskobolos.email (email, member_register_id) values (NULLIF('ssoi.zdz@gmail.com', ''), 121);
insert into diskobolos.email (email, member_register_id) values (NULLIF('sasagrovic@gmail.com', ''), 121);
insert into diskobolos.email (email, member_register_id) values (NULLIF('mlukic@unizd.hr', ''), 122);
insert into diskobolos.email (email, member_register_id) values (NULLIF('kendo-zadar@info', ''), 122);
insert into diskobolos.email (email, member_register_id) values (NULLIF('pikado.savez.zd@gmail.com', ''), 123);
insert into diskobolos.email (email, member_register_id) values (NULLIF('info@zadar-21.hr', ''), 124);
insert into diskobolos.email (email, member_register_id) values (NULLIF('bozo@2dive.hr', ''), 125);
insert into diskobolos.email (email, member_register_id) values (NULLIF('anterubesa@yahoo.com', ''), 126);
insert into diskobolos.email (email, member_register_id) values (NULLIF('', ''), 127);
insert into diskobolos.email (email, member_register_id) values (NULLIF('stanisic.gigolo@gmail.com', ''), 128);
insert into diskobolos.email (email, member_register_id) values (NULLIF('dsrosmijehzadar@net.hr', ''), 129);
insert into diskobolos.email (email, member_register_id) values (NULLIF('tomislavdespoja@gmail.com', ''), 130);
insert into diskobolos.email (email, member_register_id) values (NULLIF('etokic@inet.hr', ''), 131);
insert into diskobolos.email (email, member_register_id) values (NULLIF('uhddr.zadar@gmail.com', ''), 132);
insert into diskobolos.email (email, member_register_id) values (NULLIF('harikaric@net.hr', ''), 133);
insert into diskobolos.email (email, member_register_id) values (NULLIF('', ''), 134);
insert into diskobolos.email (email, member_register_id) values (NULLIF('bareto1007@gmail.com', ''), 135);
insert into diskobolos.email (email, member_register_id) values (NULLIF('lopticazd@gmail.com', ''), 136);
insert into diskobolos.email (email, member_register_id) values (NULLIF('info@umit.hr', ''), 137);
insert into diskobolos.email (email, member_register_id) values (NULLIF('udruga.tornadele@gmail.com', ''), 138);
insert into diskobolos.email (email, member_register_id) values (NULLIF('kyudo-klub-yumi@hotmail.com', ''), 139);
insert into diskobolos.email (email, member_register_id) values (NULLIF('znk.donat@gmail.com', ''), 140);
insert into diskobolos.email (email, member_register_id) values (NULLIF('mnkbilibrigzadar@gmail.com', ''), 141);
insert into diskobolos.email (email, member_register_id) values (NULLIF('wax.zd@vodatel.net', ''), 142);
insert into diskobolos.email (email, member_register_id) values (NULLIF('sime.erlic@grad-zadar.hr', ''), 143);
insert into diskobolos.email (email, member_register_id) values (NULLIF('zeljko.gravic@maraska.hr', ''), 144);
insert into diskobolos.email (email, member_register_id) values (NULLIF('toni.lovric@windowslive.com', ''), 145);
insert into diskobolos.email (email, member_register_id) values (NULLIF('ivan.prendja@seraphilus.hr', ''), 146);
insert into diskobolos.email (email, member_register_id) values (NULLIF('info@mnk-zapuntel.hr', ''), 147);
insert into diskobolos.email (email, member_register_id) values (NULLIF('vladogambiraza@yahoo.com', ''), 148);
insert into diskobolos.email (email, member_register_id) values (NULLIF('barbara.kardum20@gmail.com', ''), 149);
insert into diskobolos.email (email, member_register_id) values (NULLIF('mladen.bajlo@optinet.hr', ''), 150);
insert into diskobolos.email (email, member_register_id) values (NULLIF('frogocentar@gmail.com', ''), 151);
insert into diskobolos.email (email, member_register_id) values (NULLIF('hpdzavrata@gmail.com', ''), 152);
insert into diskobolos.email (email, member_register_id) values (NULLIF('hvidra.zadar@yahoo.com', ''), 153);
insert into diskobolos.email (email, member_register_id) values (NULLIF('aeroklubzadar@gmail.com', ''), 154);
insert into diskobolos.email (email, member_register_id) values (NULLIF('amblem.zd@gmail.com', ''), 155);
insert into diskobolos.email (email, member_register_id) values (NULLIF('uhddr.zadar@gmail.com', ''), 156);
insert into diskobolos.email (email, member_register_id) values (NULLIF('venci.kvartuc@visnjik.hr', ''), 156);
insert into diskobolos.email (email, member_register_id) values (NULLIF('hmedved35@gmail.com', ''), 157);
insert into diskobolos.email (email, member_register_id) values (NULLIF('anteverunica@gmail.com', ''), 158);
insert into diskobolos.email (email, member_register_id) values (NULLIF('zrilicneven@gmail.com', ''), 159);
insert into diskobolos.email (email, member_register_id) values (NULLIF('sime_jukic@net.hr', ''), 159);

-- inserting data into evaluation_question_def
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 0,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 1,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 2,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 3,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 4,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 5,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 6,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 7,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 8,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 9,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 10,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 11,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 12,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 13,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 14,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 15,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 16,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 17,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 18,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 19,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 20,  0, TRUE, null);
insert into diskobolos.evaluation_question_def (question, value_type, mandatory, default_value) values ( 21,  0, TRUE, null);

-- inserting data into question_choices_def
insert into diskobolos.question_choices_def(question_id, value) values (0, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (0, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (1, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (1, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (2, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (2, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (3, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (3, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (4, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (4, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (5, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (5, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (6, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (6, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (7, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (7, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (8, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (8, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (9, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (9, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (10, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (10, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (11, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (11, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (12, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (12, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (13, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (13, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (14, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (14, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (15, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (15, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (16, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (16, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (17, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (17, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (18, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (18, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (19, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (19, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (20, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (20, 'no');
insert into diskobolos.question_choices_def(question_id, value) values (21, 'yes');
insert into diskobolos.question_choices_def(question_id, value) values (21, 'no');

-- inserting data into users table
insert into diskobolos.users(username, password, email, enabled) values ('test', '$2a$04$PGghSlPUBJtDCs8pMXrj0OAbJN3deO1FVUKcgheGuyQgnMN65lx5m', 'test@gmail.com', true);

-- inserting data into authorities table
insert into diskobolos.authorities(role, user_id, permission_level) values ('ROLE_USER', 1, 2);
insert into diskobolos.authorities(role, user_id, permission_level) values ('ROLE_ADMIN', 1, 0);