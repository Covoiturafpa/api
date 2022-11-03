SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;


CREATE SCHEMA covoiturafpa;


ALTER SCHEMA covoiturafpa OWNER TO jfrbnsrgohiorm;


CREATE TYPE covoiturafpa.car_type_name AS ENUM (
    'COMPACT',
    'BERLINE',
    'SUV',
    'MONOSPACE',
    'UTILITAIRE'
);


ALTER TYPE covoiturafpa.car_type_name OWNER TO postgres;


CREATE TYPE covoiturafpa.day_name AS ENUM (
    'MONDAY',
    'TUESDAY',
    'WEDNESDAY',
    'THURSDAY',
    'FRIDAY',
    'SATURDAY',
    'SUNDAY'
);


ALTER TYPE covoiturafpa.day_name OWNER TO postgres;


CREATE TYPE covoiturafpa.notification_type AS ENUM (
    'NEW_RESERVATION',
    'ACCEPTED_RESERVATION',
    'REJECTED_RESERVATION'
);


ALTER TYPE covoiturafpa.notification_type OWNER TO postgres;


CREATE TYPE covoiturafpa.status_type AS ENUM (
    'PENDING',
    'ACCEPTED',
    'FINISHED'
);


ALTER TYPE covoiturafpa.status_type OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;


CREATE TABLE covoiturafpa.car (
    id_car integer NOT NULL,
    model character varying(50),
    seats smallint,
    avg_fuel_consumption numeric(4,1),
    id_car_type integer NOT NULL,
    id_person integer NOT NULL
);


ALTER TABLE covoiturafpa.car OWNER TO postgres;


CREATE SEQUENCE covoiturafpa.car_id_car_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.car_id_car_seq OWNER TO postgres;


ALTER SEQUENCE covoiturafpa.car_id_car_seq OWNED BY covoiturafpa.car.id_car;


CREATE TABLE covoiturafpa.car_type (
    id_car_type integer NOT NULL,
    name covoiturafpa.car_type_name,
    avg_fuel_consumption numeric(4,1),
    id_fuel integer NOT NULL
);


ALTER TABLE covoiturafpa.car_type OWNER TO postgres;


CREATE SEQUENCE covoiturafpa.car_type_id_car_type_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.car_type_id_car_type_seq OWNER TO postgres;


ALTER SEQUENCE covoiturafpa.car_type_id_car_type_seq OWNED BY covoiturafpa.car_type.id_car_type;


CREATE TABLE covoiturafpa.centre (
    id_centre integer NOT NULL,
    name character varying(50),
    address character varying(255),
    latitude double precision,
    longitude double precision,
    phone_number character varying(20),
    contact_by_sms boolean NOT NULL
);


ALTER TABLE covoiturafpa.centre OWNER TO postgres;


CREATE SEQUENCE covoiturafpa.centre_id_centre_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.centre_id_centre_seq OWNER TO postgres;


ALTER SEQUENCE covoiturafpa.centre_id_centre_seq OWNED BY covoiturafpa.centre.id_centre;


CREATE TABLE covoiturafpa.city (
    id_city integer NOT NULL,
    name character varying(255)
);


ALTER TABLE covoiturafpa.city OWNER TO postgres;


CREATE SEQUENCE covoiturafpa.city_id_city_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.city_id_city_seq OWNER TO postgres;


ALTER SEQUENCE covoiturafpa.city_id_city_seq OWNED BY covoiturafpa.city.id_city;


CREATE TABLE covoiturafpa.day_timetable (
    id_day_timetable integer NOT NULL,
    day covoiturafpa.day_name,
    start_morning time without time zone,
    end_morning time without time zone,
    start_afternoon time without time zone,
    end_afternoon time without time zone,
    id_centre integer NOT NULL
);


ALTER TABLE covoiturafpa.day_timetable OWNER TO postgres;


CREATE SEQUENCE covoiturafpa.day_timetable_id_day_timetable_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.day_timetable_id_day_timetable_seq OWNER TO postgres;


ALTER SEQUENCE covoiturafpa.day_timetable_id_day_timetable_seq OWNED BY covoiturafpa.day_timetable.id_day_timetable;


CREATE TABLE covoiturafpa.day_week (
    id_day_week integer NOT NULL,
    name covoiturafpa.day_name
);


ALTER TABLE covoiturafpa.day_week OWNER TO postgres;


CREATE SEQUENCE covoiturafpa.day_week_id_day_week_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.day_week_id_day_week_seq OWNER TO postgres;


ALTER SEQUENCE covoiturafpa.day_week_id_day_week_seq OWNED BY covoiturafpa.day_week.id_day_week;


CREATE TABLE covoiturafpa.destination (
    id_destination integer NOT NULL,
    latitude double precision,
    longitude double precision,
    is_from_afpa boolean,
    id_city integer
);


ALTER TABLE covoiturafpa.destination OWNER TO postgres;


CREATE SEQUENCE covoiturafpa.destination_id_destination_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.destination_id_destination_seq OWNER TO postgres;


ALTER SEQUENCE covoiturafpa.destination_id_destination_seq OWNED BY covoiturafpa.destination.id_destination;


CREATE TABLE covoiturafpa.employee (
    id_person integer NOT NULL,
    is_admin boolean,
    id_centre integer NOT NULL,
    is_teacher boolean
);


ALTER TABLE covoiturafpa.employee OWNER TO postgres;


CREATE TABLE covoiturafpa.formation (
    id_formation integer NOT NULL,
    name character varying(50),
    id_centre integer NOT NULL
);


ALTER TABLE covoiturafpa.formation OWNER TO postgres;


CREATE SEQUENCE covoiturafpa.formation_id_formation_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.formation_id_formation_seq OWNER TO postgres;


ALTER SEQUENCE covoiturafpa.formation_id_formation_seq OWNED BY covoiturafpa.formation.id_formation;


CREATE TABLE covoiturafpa.fuel (
    id_fuel integer NOT NULL,
    name character varying(20),
    price_by_unit numeric(5,2)
);


ALTER TABLE covoiturafpa.fuel OWNER TO postgres;


CREATE SEQUENCE covoiturafpa.fuel_id_fuel_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.fuel_id_fuel_seq OWNER TO postgres;


ALTER SEQUENCE covoiturafpa.fuel_id_fuel_seq OWNED BY covoiturafpa.fuel.id_fuel;


CREATE TABLE covoiturafpa.notification (
    id_notification integer NOT NULL,
    type covoiturafpa.notification_type,
    created_time timestamp without time zone,
    is_unread boolean,
    id_person integer NOT NULL,
    content varchar NOT NULL
);


ALTER TABLE covoiturafpa.notification OWNER TO postgres;


CREATE SEQUENCE covoiturafpa.notification_id_notification_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.notification_id_notification_seq OWNER TO postgres;


ALTER SEQUENCE covoiturafpa.notification_id_notification_seq OWNED BY covoiturafpa.notification.id_notification;


CREATE TABLE covoiturafpa.one_time (
    id_ride integer NOT NULL,
    departure_day date
);


ALTER TABLE covoiturafpa.one_time OWNER TO postgres;


CREATE TABLE covoiturafpa.partner (
    id_partner integer NOT NULL,
    name character varying(50),
    logo_picture_path character varying(255),
    id_centre integer NOT NULL
);


ALTER TABLE covoiturafpa.partner OWNER TO postgres;


CREATE SEQUENCE covoiturafpa.partner_id_partner_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.partner_id_partner_seq OWNER TO postgres;


ALTER SEQUENCE covoiturafpa.partner_id_partner_seq OWNED BY covoiturafpa.partner.id_partner;


CREATE TABLE covoiturafpa.person (
    id_person integer NOT NULL,
    email character varying(50),
    password character varying(255),
    surname character varying(50),
    first_name character varying(20),
    phone_number character varying(20),
    is_activated boolean,
    contact_by_sms boolean,
    contact_by_mail boolean,
    last_login timestamp without time zone,
    photo_path character varying(255),
    person_type character varying NOT NULL,
    start_activity date NOT NULL,
    end_activity date
);


ALTER TABLE covoiturafpa.person OWNER TO postgres;


CREATE SEQUENCE covoiturafpa.person_id_person_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.person_id_person_seq OWNER TO postgres;


ALTER SEQUENCE covoiturafpa.person_id_person_seq OWNED BY covoiturafpa.person.id_person;


CREATE TABLE covoiturafpa.recurring (
    id_ride integer NOT NULL,
    beginning date,
    ending date
);


ALTER TABLE covoiturafpa.recurring OWNER TO postgres;


CREATE TABLE covoiturafpa.recurring_days (
    id_ride integer NOT NULL,
    id_day_week integer NOT NULL
);


ALTER TABLE covoiturafpa.recurring_days OWNER TO postgres;


CREATE TABLE covoiturafpa.ride (
    id_ride integer NOT NULL,
    is_active boolean,
    departure_time time without time zone,
    comment character varying(255),
    id_destination integer NOT NULL,
    id_car integer NOT NULL,
    price numeric(5,2),
    ride_type character varying NOT NULL
);


ALTER TABLE covoiturafpa.ride OWNER TO postgres;


CREATE SEQUENCE covoiturafpa.ride_id_ride_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.ride_id_ride_seq OWNER TO postgres;


ALTER SEQUENCE covoiturafpa.ride_id_ride_seq OWNED BY covoiturafpa.ride.id_ride;


CREATE TABLE covoiturafpa.ride_passenger (
    id_person integer NOT NULL,
    id_ride integer NOT NULL,
    status covoiturafpa.status_type,
    last_update timestamp without time zone,
    is_driver boolean
);


ALTER TABLE covoiturafpa.ride_passenger OWNER TO postgres;


CREATE TABLE covoiturafpa.teacher_of (
    id_formation integer NOT NULL,
    id_teacher integer NOT NULL
);


ALTER TABLE covoiturafpa.teacher_of OWNER TO postgres;


CREATE TABLE covoiturafpa.trainee (
    id_person integer NOT NULL,
    id_formation integer
);


ALTER TABLE covoiturafpa.trainee OWNER TO postgres;


ALTER TABLE ONLY covoiturafpa.car ALTER COLUMN id_car SET DEFAULT nextval('covoiturafpa.car_id_car_seq'::regclass);


ALTER TABLE ONLY covoiturafpa.car_type ALTER COLUMN id_car_type SET DEFAULT nextval('covoiturafpa.car_type_id_car_type_seq'::regclass);


ALTER TABLE ONLY covoiturafpa.centre ALTER COLUMN id_centre SET DEFAULT nextval('covoiturafpa.centre_id_centre_seq'::regclass);


ALTER TABLE ONLY covoiturafpa.city ALTER COLUMN id_city SET DEFAULT nextval('covoiturafpa.city_id_city_seq'::regclass);


ALTER TABLE ONLY covoiturafpa.day_timetable ALTER COLUMN id_day_timetable SET DEFAULT nextval('covoiturafpa.day_timetable_id_day_timetable_seq'::regclass);


ALTER TABLE ONLY covoiturafpa.day_week ALTER COLUMN id_day_week SET DEFAULT nextval('covoiturafpa.day_week_id_day_week_seq'::regclass);


ALTER TABLE ONLY covoiturafpa.destination ALTER COLUMN id_destination SET DEFAULT nextval('covoiturafpa.destination_id_destination_seq'::regclass);


ALTER TABLE ONLY covoiturafpa.formation ALTER COLUMN id_formation SET DEFAULT nextval('covoiturafpa.formation_id_formation_seq'::regclass);


ALTER TABLE ONLY covoiturafpa.fuel ALTER COLUMN id_fuel SET DEFAULT nextval('covoiturafpa.fuel_id_fuel_seq'::regclass);


ALTER TABLE ONLY covoiturafpa.notification ALTER COLUMN id_notification SET DEFAULT nextval('covoiturafpa.notification_id_notification_seq'::regclass);


ALTER TABLE ONLY covoiturafpa.partner ALTER COLUMN id_partner SET DEFAULT nextval('covoiturafpa.partner_id_partner_seq'::regclass);


ALTER TABLE ONLY covoiturafpa.person ALTER COLUMN id_person SET DEFAULT nextval('covoiturafpa.person_id_person_seq'::regclass);


ALTER TABLE ONLY covoiturafpa.ride ALTER COLUMN id_ride SET DEFAULT nextval('covoiturafpa.ride_id_ride_seq'::regclass);


INSERT INTO covoiturafpa.car_type VALUES (1, 'COMPACT', 7.3, 1);
INSERT INTO covoiturafpa.car_type VALUES (2, 'COMPACT', 7.3, 2);
INSERT INTO covoiturafpa.car_type VALUES (3, 'COMPACT', 5.2, 3);
INSERT INTO covoiturafpa.car_type VALUES (4, 'COMPACT', 7.8, 4);
INSERT INTO covoiturafpa.car_type VALUES (5, 'COMPACT', 9.5, 5);
INSERT INTO covoiturafpa.car_type VALUES (6, 'BERLINE', 7.5, 1);
INSERT INTO covoiturafpa.car_type VALUES (7, 'BERLINE', 7.5, 2);
INSERT INTO covoiturafpa.car_type VALUES (8, 'BERLINE', 6.8, 3);
INSERT INTO covoiturafpa.car_type VALUES (9, 'BERLINE', 15.6, 4);
INSERT INTO covoiturafpa.car_type VALUES (10, 'BERLINE', 12.2, 5);
INSERT INTO covoiturafpa.car_type VALUES (11, 'SUV', 8.0, 1);
INSERT INTO covoiturafpa.car_type VALUES (12, 'SUV', 8.0, 2);
INSERT INTO covoiturafpa.car_type VALUES (13, 'SUV', 7.2, 3);
INSERT INTO covoiturafpa.car_type VALUES (14, 'SUV', 16.0, 4);
INSERT INTO covoiturafpa.car_type VALUES (15, 'SUV', 16.0, 5);
INSERT INTO covoiturafpa.car_type VALUES (16, 'MONOSPACE', 7.0, 1);
INSERT INTO covoiturafpa.car_type VALUES (17, 'MONOSPACE', 7.0, 2);
INSERT INTO covoiturafpa.car_type VALUES (18, 'MONOSPACE', 6.2, 3);
INSERT INTO covoiturafpa.car_type VALUES (19, 'MONOSPACE', 15.6, 4);
INSERT INTO covoiturafpa.car_type VALUES (20, 'MONOSPACE', 10.5, 5);
INSERT INTO covoiturafpa.car_type VALUES (21, 'UTILITAIRE', 14.3, 3);
INSERT INTO covoiturafpa.car_type VALUES (22, 'UTILITAIRE', 14.3, 2);
INSERT INTO covoiturafpa.car_type VALUES (23, 'UTILITAIRE', 11.6, 3);
INSERT INTO covoiturafpa.car_type VALUES (24, 'UTILITAIRE', 25.0, 4);
INSERT INTO covoiturafpa.car_type VALUES (25, 'UTILITAIRE', 18.6, 5);


INSERT INTO covoiturafpa.centre VALUES (28, 'Centre Afpa de Rochefort', '57 Avenue Bernadotte', 45.958593, -0.963835, '+33472864830', true);


INSERT INTO covoiturafpa.day_timetable VALUES (1, 'MONDAY', '09:00:00', '12:00:00', '13:00:00', '18:00:00', 28);
INSERT INTO covoiturafpa.day_timetable VALUES (2, 'TUESDAY', '08:00:00', '12:00:00', '13:00:00', '18:00:00', 28);
INSERT INTO covoiturafpa.day_timetable VALUES (3, 'WEDNESDAY', '08:00:00', '12:00:00', '13:00:00', '18:00:00', 28);
INSERT INTO covoiturafpa.day_timetable VALUES (4, 'THURSDAY', '08:00:00', '12:00:00', '13:00:00', '18:00:00', 28);
INSERT INTO covoiturafpa.day_timetable VALUES (5, 'FRIDAY', '08:00:00', '12:00:00', '13:00:00', '18:00:00', 28);
INSERT INTO covoiturafpa.day_timetable VALUES (6, 'SATURDAY', NULL, NULL, NULL, NULL, 28);
INSERT INTO covoiturafpa.day_timetable VALUES (7, 'SUNDAY', NULL, NULL, NULL, NULL, 28);


INSERT INTO covoiturafpa.day_week VALUES (1, 'MONDAY');
INSERT INTO covoiturafpa.day_week VALUES (2, 'TUESDAY');
INSERT INTO covoiturafpa.day_week VALUES (3, 'WEDNESDAY');
INSERT INTO covoiturafpa.day_week VALUES (4, 'THURSDAY');
INSERT INTO covoiturafpa.day_week VALUES (5, 'FRIDAY');
INSERT INTO covoiturafpa.day_week VALUES (6, 'SATURDAY');
INSERT INTO covoiturafpa.day_week VALUES (7, 'SUNDAY');


INSERT INTO covoiturafpa.fuel VALUES (1, 'ESSENCE', 1.82);
INSERT INTO covoiturafpa.fuel VALUES (2, 'ESSENCE SUPERTHANOL', 0.82);
INSERT INTO covoiturafpa.fuel VALUES (3, 'GAZOLE', 1.82);
INSERT INTO covoiturafpa.fuel VALUES (4, ' ELECTRIQUE', 0.17);
INSERT INTO covoiturafpa.fuel VALUES (5, 'GPL', 0.87);


INSERT INTO covoiturafpa.partner VALUES (1, 'Afaq_9001', 'Afaq_9001.png', 28);
INSERT INTO covoiturafpa.partner VALUES (2, 'France Relance', 'france-relance.png', 28);
INSERT INTO covoiturafpa.partner VALUES (3, 'fse', 'fse.jpg', 28);
INSERT INTO covoiturafpa.partner VALUES (6, 'Synofdes', 'synofdes.png', 28);
INSERT INTO covoiturafpa.partner VALUES (5, 'region Nouvelle-Aquitaine', 'region.jpg', 28);
INSERT INTO covoiturafpa.partner VALUES (4, 'opqf', 'opqf.png', 28);


SELECT pg_catalog.setval('covoiturafpa.car_id_car_seq', 1, false);


SELECT pg_catalog.setval('covoiturafpa.car_type_id_car_type_seq', 1, true);


SELECT pg_catalog.setval('covoiturafpa.centre_id_centre_seq', 1, false);


SELECT pg_catalog.setval('covoiturafpa.city_id_city_seq', 1, true);


SELECT pg_catalog.setval('covoiturafpa.day_timetable_id_day_timetable_seq', 1, true);


SELECT pg_catalog.setval('covoiturafpa.day_week_id_day_week_seq', 1, false);


SELECT pg_catalog.setval('covoiturafpa.destination_id_destination_seq', 15, true);


SELECT pg_catalog.setval('covoiturafpa.formation_id_formation_seq', 1, false);


SELECT pg_catalog.setval('covoiturafpa.fuel_id_fuel_seq', 1, false);


SELECT pg_catalog.setval('covoiturafpa.notification_id_notification_seq', 1, false);


SELECT pg_catalog.setval('covoiturafpa.partner_id_partner_seq', 1, false);


SELECT pg_catalog.setval('covoiturafpa.person_id_person_seq', 1, false);


SELECT pg_catalog.setval('covoiturafpa.ride_id_ride_seq', 1, true);


ALTER TABLE ONLY covoiturafpa.car
ADD CONSTRAINT car_pkey PRIMARY KEY (id_car);


ALTER TABLE ONLY covoiturafpa.car_type
ADD CONSTRAINT car_type_pkey PRIMARY KEY (id_car_type);


ALTER TABLE ONLY covoiturafpa.centre
ADD CONSTRAINT centre_pkey PRIMARY KEY (id_centre);


ALTER TABLE ONLY covoiturafpa.city
ADD CONSTRAINT city_pkey PRIMARY KEY (id_city);


ALTER TABLE ONLY covoiturafpa.city
ADD CONSTRAINT city_un UNIQUE (name);


ALTER TABLE ONLY covoiturafpa.day_timetable
ADD CONSTRAINT day_timetable_pkey PRIMARY KEY (id_day_timetable);


ALTER TABLE ONLY covoiturafpa.day_week
ADD CONSTRAINT day_week_pkey PRIMARY KEY (id_day_week);


ALTER TABLE ONLY covoiturafpa.destination
ADD CONSTRAINT destination_pkey PRIMARY KEY (id_destination);


ALTER TABLE ONLY covoiturafpa.person
ADD CONSTRAINT email_un UNIQUE (email);


ALTER TABLE ONLY covoiturafpa.employee
ADD CONSTRAINT employee_pkey PRIMARY KEY (id_person);


ALTER TABLE ONLY covoiturafpa.formation
ADD CONSTRAINT formation_pkey PRIMARY KEY (id_formation);


ALTER TABLE ONLY covoiturafpa.fuel
ADD CONSTRAINT fuel_pkey PRIMARY KEY (id_fuel);


ALTER TABLE ONLY covoiturafpa.recurring_days
ADD CONSTRAINT happen_pkey PRIMARY KEY (id_ride, id_day_week);


ALTER TABLE ONLY covoiturafpa.notification
ADD CONSTRAINT notification_pkey PRIMARY KEY (id_notification);


ALTER TABLE ONLY covoiturafpa.one_time
ADD CONSTRAINT one_time_pkey PRIMARY KEY (id_ride);


ALTER TABLE ONLY covoiturafpa.partner
ADD CONSTRAINT partner_pkey PRIMARY KEY (id_partner);


ALTER TABLE ONLY covoiturafpa.person
ADD CONSTRAINT person_pkey PRIMARY KEY (id_person);


ALTER TABLE ONLY covoiturafpa.recurring
ADD CONSTRAINT recurring_pkey PRIMARY KEY (id_ride);


ALTER TABLE ONLY covoiturafpa.ride_passenger
ADD CONSTRAINT ride_passenger_pkey PRIMARY KEY (id_person, id_ride);


ALTER TABLE ONLY covoiturafpa.ride
ADD CONSTRAINT ride_pkey PRIMARY KEY (id_ride);


ALTER TABLE ONLY covoiturafpa.teacher_of
ADD CONSTRAINT teacher_of_pkey PRIMARY KEY (id_formation, id_teacher);


ALTER TABLE ONLY covoiturafpa.trainee
ADD CONSTRAINT trainee_pkey PRIMARY KEY (id_person);


ALTER TABLE ONLY covoiturafpa.car
ADD CONSTRAINT car_id_car_type_fkey FOREIGN KEY (id_car_type) REFERENCES covoiturafpa.car_type(id_car_type);


ALTER TABLE ONLY covoiturafpa.car
ADD CONSTRAINT car_id_person_fkey FOREIGN KEY (id_person) REFERENCES covoiturafpa.person(id_person);


ALTER TABLE ONLY covoiturafpa.car_type
ADD CONSTRAINT car_type_id_fuel_fkey FOREIGN KEY (id_fuel) REFERENCES covoiturafpa.fuel(id_fuel);


ALTER TABLE ONLY covoiturafpa.day_timetable
ADD CONSTRAINT day_timetable_id_centre_fkey FOREIGN KEY (id_centre) REFERENCES covoiturafpa.centre(id_centre);


ALTER TABLE ONLY covoiturafpa.destination
ADD CONSTRAINT destination_id_city_fkey FOREIGN KEY (id_city) REFERENCES covoiturafpa.city(id_city) ON DELETE CASCADE;


ALTER TABLE ONLY covoiturafpa.employee
ADD CONSTRAINT employee_id_centre_fkey FOREIGN KEY (id_centre) REFERENCES covoiturafpa.centre(id_centre);


ALTER TABLE ONLY covoiturafpa.employee
ADD CONSTRAINT employee_id_person_fkey FOREIGN KEY (id_person) REFERENCES covoiturafpa.person(id_person);


ALTER TABLE ONLY covoiturafpa.formation
ADD CONSTRAINT formation_id_centre_fkey FOREIGN KEY (id_centre) REFERENCES covoiturafpa.centre(id_centre);


ALTER TABLE ONLY covoiturafpa.recurring_days
ADD CONSTRAINT happen_id_day_week_fkey FOREIGN KEY (id_day_week) REFERENCES covoiturafpa.day_week(id_day_week);


ALTER TABLE ONLY covoiturafpa.recurring_days
ADD CONSTRAINT happen_id_ride_fkey FOREIGN KEY (id_ride) REFERENCES covoiturafpa.recurring(id_ride);


ALTER TABLE ONLY covoiturafpa.notification
ADD CONSTRAINT notification_id_person_fkey FOREIGN KEY (id_person) REFERENCES covoiturafpa.person(id_person);


ALTER TABLE ONLY covoiturafpa.one_time
ADD CONSTRAINT one_time_id_ride_fkey FOREIGN KEY (id_ride) REFERENCES covoiturafpa.ride(id_ride) ON DELETE CASCADE;


ALTER TABLE ONLY covoiturafpa.partner
ADD CONSTRAINT partner_id_centre_fkey FOREIGN KEY (id_centre) REFERENCES covoiturafpa.centre(id_centre);


ALTER TABLE ONLY covoiturafpa.recurring
ADD CONSTRAINT recurring_id_ride_fkey FOREIGN KEY (id_ride) REFERENCES covoiturafpa.ride(id_ride) ON DELETE CASCADE;


ALTER TABLE ONLY covoiturafpa.ride
ADD CONSTRAINT ride_id_car_fkey FOREIGN KEY (id_car) REFERENCES covoiturafpa.car(id_car);


ALTER TABLE ONLY covoiturafpa.ride
ADD CONSTRAINT ride_id_destination_fkey FOREIGN KEY (id_destination) REFERENCES covoiturafpa.destination(id_destination) ON DELETE CASCADE;


ALTER TABLE ONLY covoiturafpa.ride_passenger
ADD CONSTRAINT ride_passenger_id_person_fkey FOREIGN KEY (id_person) REFERENCES covoiturafpa.person(id_person);


ALTER TABLE ONLY covoiturafpa.ride_passenger
ADD CONSTRAINT ride_passenger_id_ride_fkey FOREIGN KEY (id_ride) REFERENCES covoiturafpa.ride(id_ride) ON DELETE CASCADE;


ALTER TABLE ONLY covoiturafpa.teacher_of
ADD CONSTRAINT teacher_of_id_formation_fkey FOREIGN KEY (id_formation) REFERENCES covoiturafpa.formation(id_formation);


ALTER TABLE ONLY covoiturafpa.teacher_of
ADD CONSTRAINT teacher_of_id_teacher_fkey FOREIGN KEY (id_teacher) REFERENCES covoiturafpa.employee(id_person);


ALTER TABLE ONLY covoiturafpa.trainee
ADD CONSTRAINT trainee_id_formation_fkey FOREIGN KEY (id_formation) REFERENCES covoiturafpa.formation(id_formation);


ALTER TABLE ONLY covoiturafpa.trainee
ADD CONSTRAINT trainee_id_person_fkey FOREIGN KEY (id_person) REFERENCES covoiturafpa.person(id_person);




CREATE extension cube SCHEMA covoiturafpa;
CREATE extension earthdistance SCHEMA covoiturafpa;

CREATE OR REPLACE FUNCTION covoiturafpa.get_distance(latitude_1 double precision, longitude_1 double precision, latitude_2 double precision, longitude_2 double precision)
RETURNS double precision
LANGUAGE sql
IMMUTABLE STRICT
RETURN covoiturafpa.earth_distance(covoiturafpa.ll_to_earth(latitude_1, longitude_1), covoiturafpa.ll_to_earth(latitude_2, longitude_2)) / 1000;




CREATE ROLE "afpaUser" WITH
	LOGIN
	NOSUPERUSER
	NOCREATEDB
	NOCREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'Afpa4apfA!'; 
COMMENT ON ROLE "afpaUser" IS 'Classic User for CovoiturAfpa';

GRANT USAGE ON SCHEMA "covoiturafpa" TO "afpaUser"; 

GRANT ALL ON TABLE "covoiturafpa".car TO "afpaUser";
GRANT ALL ON TABLE "covoiturafpa".car_type TO "afpaUser";
GRANT ALL ON TABLE "covoiturafpa".centre TO "afpaUser";
GRANT ALL ON TABLE "covoiturafpa".city TO "afpaUser";
GRANT ALL ON TABLE "covoiturafpa".day_timetable TO "afpaUser";
GRANT ALL ON TABLE "covoiturafpa".destination TO "afpaUser";
GRANT ALL ON TABLE "covoiturafpa".employee TO "afpaUser";
GRANT ALL ON TABLE "covoiturafpa".formation TO "afpaUser";
GRANT ALL ON TABLE "covoiturafpa".fuel TO "afpaUser";
GRANT ALL ON TABLE "covoiturafpa".notification TO "afpaUser";
GRANT ALL ON TABLE "covoiturafpa".one_time TO "afpaUser";
GRANT ALL ON TABLE "covoiturafpa".partner TO "afpaUser";
GRANT ALL ON TABLE "covoiturafpa".person TO "afpaUser";
GRANT ALL ON TABLE "covoiturafpa".recurring TO "afpaUser";
GRANT ALL ON TABLE "covoiturafpa".ride TO "afpaUser";
GRANT ALL ON TABLE "covoiturafpa".ride_passenger TO "afpaUser";
GRANT ALL ON TABLE "covoiturafpa".trainee TO "afpaUser";
GRANT ALL ON TABLE "covoiturafpa".day_week TO "afpaUser";
GRANT ALL ON TABLE "covoiturafpa".recurring_days TO "afpaUser";
GRANT ALL ON TABLE "covoiturafpa".teacher_of TO "afpaUser";

GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA "covoiturafpa" TO "afpaUser";
GRANT EXECUTE ON FUNCTION covoiturafpa.get_distance(float8, float8, float8, float8) TO "afpaUser";