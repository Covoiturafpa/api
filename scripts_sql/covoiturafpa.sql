CREATE TYPE car_type_name AS ENUM ('COMPACT', 'BERLINE', 'SUV', 'MONOSPACE', 'UTILITAIRE');
CREATE TYPE notification_type AS ENUM ('NEW_RESERVATION', 'ACCEPTED_RESERVATION', 'REJECTED_RESERVATION');
CREATE TYPE status_type AS ENUM ('PENDING', 'ACCEPTED', 'FINISHED');
CREATE TYPE day_name AS ENUM ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY');


CREATE TABLE person(
   id_person SERIAL,
   email VARCHAR(50),
   password VARCHAR(50),
   surname VARCHAR(50),
   first_name VARCHAR(20),
   phone_number VARCHAR(20),
   is_activated BOOLEAN,
   contact_by_sms BOOLEAN,
   contact_by_mail BOOLEAN,
   last_login TIMESTAMP,
   photo_path VARCHAR(255),
   person_type VARCHAR(1) NOT NULL, 
   PRIMARY KEY(id_person)
);

CREATE TABLE notif_config(
   id_notif_config SERIAL,
   contact_by_sms BOOLEAN,
   PRIMARY KEY(id_notif_config)
);

CREATE TABLE fuel(
   id_fuel SERIAL,
   name VARCHAR(20),
   price_by_unit NUMERIC(5,2),
   PRIMARY KEY(id_fuel)
);

CREATE TABLE notification(
   id_notification SERIAL,
   type notification_type,
   created_time TIMESTAMP,
   is_unread BOOLEAN,
   id_person INTEGER NOT NULL,
   PRIMARY KEY(id_notification),
   FOREIGN KEY(id_person) REFERENCES person(id_person)
);

CREATE TABLE city(
   id_city SERIAL,
   name VARCHAR(255),
   PRIMARY KEY(id_city)
);

CREATE TABLE day_week(
   id_day_week SERIAL,
   name VARCHAR(15),
   PRIMARY KEY(id_day_week)
);


CREATE TABLE car_type(
   id_car_type SERIAL,
   name car_type_name,
   avg_fuel_consumption NUMERIC(4,1),
   seats SMALLINT,
   id_fuel INTEGER NOT NULL,
   PRIMARY KEY(id_car_type),
   FOREIGN KEY(id_fuel) REFERENCES fuel(id_fuel)
);

CREATE TABLE car(
   id_car SERIAL,
   model VARCHAR(50),
   seats SMALLINT,
   avg_fuel_consumption NUMERIC(4,1),
   id_car_type INTEGER NOT NULL,
   id_person INTEGER NOT NULL,
   PRIMARY KEY(id_car),
   FOREIGN KEY(id_car_type) REFERENCES car_type(id_car_type),
   FOREIGN KEY(id_person) REFERENCES person(id_person)
);

CREATE TABLE centre(
   id_centre SERIAL,
   name VARCHAR(50),
   address VARCHAR(255) ,
   latitude DOUBLE PRECISION,
   longitude DOUBLE PRECISION,
   phone_number VARCHAR(20),
   id_notif_config INTEGER NOT NULL,
   PRIMARY KEY(id_centre),
   UNIQUE(id_notif_config),
   FOREIGN KEY(id_notif_config) REFERENCES notif_config(id_notif_config)
);

CREATE TABLE formation(
   id_formation SERIAL,
   name VARCHAR(50),
   id_centre INTEGER NOT NULL,
   PRIMARY KEY(id_formation),
   FOREIGN KEY(id_centre) REFERENCES centre(id_centre)
);

CREATE TABLE day_timetable(
   id_day_timetable SERIAL,
   day day_name,
   start_morning TIME,
   end_morning TIME,
   start_afternoon TIME,
   end_afternoon TIME,
   id_centre INTEGER NOT NULL,
   PRIMARY KEY(id_day_timetable),
   FOREIGN KEY(id_centre) REFERENCES centre(id_centre)
);

CREATE TABLE partner(
   id_partner SERIAL,
   name VARCHAR(50),
   logo_picture_path VARCHAR(255),
   id_centre INTEGER NOT NULL,
   PRIMARY KEY(id_partner),
   FOREIGN KEY(id_centre) REFERENCES centre(id_centre)
);

CREATE TABLE destination(
   id_destination SERIAL,
   latitude DOUBLE PRECISION,
   longitude DOUBLE PRECISION,
   is_from_afpa BOOLEAN,
   id_city INTEGER,
   PRIMARY KEY(id_destination),
   FOREIGN KEY(id_city) REFERENCES City(id_city)
);

CREATE TABLE trainee(
   id_person INTEGER,
   start_training DATE,
   end_training DATE,
   id_formation INTEGER,
   PRIMARY KEY(id_person),
   UNIQUE(id_person),
   FOREIGN KEY(id_formation) REFERENCES formation(id_formation),
   FOREIGN KEY(id_person) REFERENCES person(id_person)
);

CREATE TABLE employee(
   id_person INTEGER,
   role VARCHAR(50),
   is_admin BOOLEAN,
   start_contract DATE,
   end_contract DATE,
   id_centre INTEGER NOT NULL,
   PRIMARY KEY(id_person),
   UNIQUE(id_person),
   FOREIGN KEY(id_centre) REFERENCES centre(id_centre),
   FOREIGN KEY(id_person) REFERENCES person(id_person)
);

CREATE TABLE ride(
   id_ride SERIAL,
   is_active BOOLEAN,
   departure_time TIME,
   comment VARCHAR(255) ,
   id_destination INTEGER NOT NULL,
   id_person INTEGER NOT NULL,
   id_car INTEGER NOT NULL,
   price NUMERIC(5, 2),
   ride_type VARCHAR(1) NOT NULL,
   PRIMARY KEY(id_ride),
   FOREIGN KEY(id_destination) REFERENCES destination(id_destination),
   FOREIGN KEY(id_person) REFERENCES person(id_person),
   FOREIGN KEY(id_car) REFERENCES car(id_car)
);

CREATE TABLE recurring(
   id_ride INTEGER,
   beginning DATE,
   ending DATE,
   PRIMARY KEY(id_ride),
   UNIQUE(id_ride),
   FOREIGN KEY(id_ride) REFERENCES ride(id_ride)
);

CREATE TABLE one_time(
   id_ride INTEGER,
   departure_day DATE,
   PRIMARY KEY(id_ride),
   UNIQUE(id_ride),
   FOREIGN KEY(id_ride) REFERENCES ride(id_ride)
);

CREATE TABLE ride_passenger(
   id_person INTEGER,
   id_ride INTEGER,
   status status_type,
   last_update TIMESTAMP,
   PRIMARY KEY(id_person, id_ride),
   FOREIGN KEY(id_person) REFERENCES person(id_person),
   FOREIGN KEY(id_ride) REFERENCES ride(id_ride)
);

CREATE TABLE happen(
   id_ride INTEGER,
   id_day_week INTEGER,
   PRIMARY KEY(id_ride, id_day_week),
   FOREIGN KEY(id_ride) REFERENCES recurring(id_ride),
   FOREIGN KEY(id_day_week) REFERENCES day_week(id_day_week)
);
