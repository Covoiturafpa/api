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


CREATE SCHEMA heroku_ext;


ALTER SCHEMA heroku_ext OWNER TO jfrbnsrgohiorm;


CREATE TYPE heroku_ext.car_type_name AS ENUM (
    'COMPACT',
    'BERLINE',
    'SUV',
    'MONOSPACE',
    'UTILITAIRE'
);


ALTER TYPE heroku_ext.car_type_name OWNER TO jfrbnsrgohiorm;


CREATE TYPE heroku_ext.day_name AS ENUM (
    'MONDAY',
    'TUESDAY',
    'WEDNESDAY',
    'THURSDAY',
    'FRIDAY',
    'SATURDAY',
    'SUNDAY'
);


ALTER TYPE heroku_ext.day_name OWNER TO jfrbnsrgohiorm;


CREATE TYPE heroku_ext.notification_type AS ENUM (
    'NEW_RESERVATION',
    'ACCEPTED_RESERVATION',
    'REJECTED_RESERVATION'
);


ALTER TYPE heroku_ext.notification_type OWNER TO jfrbnsrgohiorm;


CREATE TYPE heroku_ext.status_type AS ENUM (
    'PENDING',
    'ACCEPTED',
    'FINISHED'
);


ALTER TYPE heroku_ext.status_type OWNER TO jfrbnsrgohiorm;

SET default_tablespace = '';

SET default_table_access_method = heap;


CREATE TABLE heroku_ext.car (
    id_car integer NOT NULL,
    model character varying(50),
    seats smallint,
    avg_fuel_consumption numeric(4,1),
    id_car_type integer NOT NULL,
    id_person integer NOT NULL
);


ALTER TABLE heroku_ext.car OWNER TO jfrbnsrgohiorm;


CREATE SEQUENCE heroku_ext.car_id_car_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE heroku_ext.car_id_car_seq OWNER TO jfrbnsrgohiorm;


ALTER SEQUENCE heroku_ext.car_id_car_seq OWNED BY heroku_ext.car.id_car;


CREATE TABLE heroku_ext.car_type (
    id_car_type integer NOT NULL,
    name heroku_ext.car_type_name,
    avg_fuel_consumption numeric(4,1),
    id_fuel integer NOT NULL
);


ALTER TABLE heroku_ext.car_type OWNER TO jfrbnsrgohiorm;


CREATE SEQUENCE heroku_ext.car_type_id_car_type_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE heroku_ext.car_type_id_car_type_seq OWNER TO jfrbnsrgohiorm;


ALTER SEQUENCE heroku_ext.car_type_id_car_type_seq OWNED BY heroku_ext.car_type.id_car_type;


CREATE TABLE heroku_ext.centre (
    id_centre integer NOT NULL,
    name character varying(50),
    address character varying(255),
    latitude double precision,
    longitude double precision,
    phone_number character varying(20),
    contact_by_sms boolean NOT NULL
);


ALTER TABLE heroku_ext.centre OWNER TO jfrbnsrgohiorm;


CREATE SEQUENCE heroku_ext.centre_id_centre_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE heroku_ext.centre_id_centre_seq OWNER TO jfrbnsrgohiorm;


ALTER SEQUENCE heroku_ext.centre_id_centre_seq OWNED BY heroku_ext.centre.id_centre;


CREATE TABLE heroku_ext.city (
    id_city integer NOT NULL,
    name character varying(255)
);


ALTER TABLE heroku_ext.city OWNER TO jfrbnsrgohiorm;


CREATE SEQUENCE heroku_ext.city_id_city_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE heroku_ext.city_id_city_seq OWNER TO jfrbnsrgohiorm;


ALTER SEQUENCE heroku_ext.city_id_city_seq OWNED BY heroku_ext.city.id_city;


CREATE TABLE heroku_ext.day_timetable (
    id_day_timetable integer NOT NULL,
    day heroku_ext.day_name,
    start_morning time without time zone,
    end_morning time without time zone,
    start_afternoon time without time zone,
    end_afternoon time without time zone,
    id_centre integer NOT NULL
);


ALTER TABLE heroku_ext.day_timetable OWNER TO jfrbnsrgohiorm;


CREATE SEQUENCE heroku_ext.day_timetable_id_day_timetable_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE heroku_ext.day_timetable_id_day_timetable_seq OWNER TO jfrbnsrgohiorm;


ALTER SEQUENCE heroku_ext.day_timetable_id_day_timetable_seq OWNED BY heroku_ext.day_timetable.id_day_timetable;


CREATE TABLE heroku_ext.day_week (
    id_day_week integer NOT NULL,
    name heroku_ext.day_name
);


ALTER TABLE heroku_ext.day_week OWNER TO jfrbnsrgohiorm;


CREATE SEQUENCE heroku_ext.day_week_id_day_week_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE heroku_ext.day_week_id_day_week_seq OWNER TO jfrbnsrgohiorm;


ALTER SEQUENCE heroku_ext.day_week_id_day_week_seq OWNED BY heroku_ext.day_week.id_day_week;


CREATE TABLE heroku_ext.destination (
    id_destination integer NOT NULL,
    latitude double precision,
    longitude double precision,
    is_from_afpa boolean,
    id_city integer
);


ALTER TABLE heroku_ext.destination OWNER TO jfrbnsrgohiorm;


CREATE SEQUENCE heroku_ext.destination_id_destination_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE heroku_ext.destination_id_destination_seq OWNER TO jfrbnsrgohiorm;


ALTER SEQUENCE heroku_ext.destination_id_destination_seq OWNED BY heroku_ext.destination.id_destination;


CREATE TABLE heroku_ext.employee (
    id_person integer NOT NULL,
    is_admin boolean,
    id_centre integer NOT NULL,
    is_teacher boolean
);


ALTER TABLE heroku_ext.employee OWNER TO jfrbnsrgohiorm;


CREATE TABLE heroku_ext.formation (
    id_formation integer NOT NULL,
    name character varying(50),
    id_centre integer NOT NULL
);


ALTER TABLE heroku_ext.formation OWNER TO jfrbnsrgohiorm;


CREATE SEQUENCE heroku_ext.formation_id_formation_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE heroku_ext.formation_id_formation_seq OWNER TO jfrbnsrgohiorm;


ALTER SEQUENCE heroku_ext.formation_id_formation_seq OWNED BY heroku_ext.formation.id_formation;


CREATE TABLE heroku_ext.fuel (
    id_fuel integer NOT NULL,
    name character varying(20),
    price_by_unit numeric(5,2)
);


ALTER TABLE heroku_ext.fuel OWNER TO jfrbnsrgohiorm;


CREATE SEQUENCE heroku_ext.fuel_id_fuel_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE heroku_ext.fuel_id_fuel_seq OWNER TO jfrbnsrgohior;


ALTER SEQUENCE heroku_ext.fuel_id_fuel_seq OWNED BY heroku_ext.fuel.id_fuel;


CREATE TABLE heroku_ext.notification (
    id_notification integer NOT NULL,
    type heroku_ext.notification_type,
    created_time timestamp without time zone,
    is_unread boolean,
    id_person integer NOT NULL,
    content varchar NOT NULL
);


ALTER TABLE heroku_ext.notification OWNER TO jfrbnsrgohior;


CREATE SEQUENCE heroku_ext.notification_id_notification_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE heroku_ext.notification_id_notification_seq OWNER TO jfrbnsrgohior;


ALTER SEQUENCE heroku_ext.notification_id_notification_seq OWNED BY heroku_ext.notification.id_notification;


CREATE TABLE heroku_ext.one_time (
    id_ride integer NOT NULL,
    departure_day date
);


ALTER TABLE heroku_ext.one_time OWNER TO jfrbnsrgohior;


CREATE TABLE heroku_ext.partner (
    id_partner integer NOT NULL,
    name character varying(50),
    logo_picture_path character varying(255),
    id_centre integer NOT NULL
);


ALTER TABLE heroku_ext.partner OWNER TO jfrbnsrgohior;


CREATE SEQUENCE heroku_ext.partner_id_partner_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE heroku_ext.partner_id_partner_seq OWNER TO jfrbnsrgohior;


ALTER SEQUENCE heroku_ext.partner_id_partner_seq OWNED BY heroku_ext.partner.id_partner;


CREATE TABLE heroku_ext.person (
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


ALTER TABLE heroku_ext.person OWNER TO jfrbnsrgohior;


CREATE SEQUENCE heroku_ext.person_id_person_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE heroku_ext.person_id_person_seq OWNER TO jfrbnsrgohior;


ALTER SEQUENCE heroku_ext.person_id_person_seq OWNED BY heroku_ext.person.id_person;


CREATE TABLE heroku_ext.recurring (
    id_ride integer NOT NULL,
    beginning date,
    ending date
);


ALTER TABLE heroku_ext.recurring OWNER TO jfrbnsrgohior;


CREATE TABLE heroku_ext.recurring_days (
    id_ride integer NOT NULL,
    id_day_week integer NOT NULL
);


ALTER TABLE heroku_ext.recurring_days OWNER TO jfrbnsrgohior;


CREATE TABLE heroku_ext.ride (
    id_ride integer NOT NULL,
    is_active boolean,
    departure_time time without time zone,
    comment character varying(255),
    id_destination integer NOT NULL,
    id_car integer NOT NULL,
    price numeric(5,2),
    ride_type character varying NOT NULL
);


ALTER TABLE heroku_ext.ride OWNER TO jfrbnsrgohior;


CREATE SEQUENCE heroku_ext.ride_id_ride_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE heroku_ext.ride_id_ride_seq OWNER TO jfrbnsrgohior;


ALTER SEQUENCE heroku_ext.ride_id_ride_seq OWNED BY heroku_ext.ride.id_ride;


CREATE TABLE heroku_ext.ride_passenger (
    id_person integer NOT NULL,
    id_ride integer NOT NULL,
    status heroku_ext.status_type,
    last_update timestamp without time zone,
    is_driver boolean
);


ALTER TABLE heroku_ext.ride_passenger OWNER TO jfrbnsrgohior;


CREATE TABLE heroku_ext.teacher_of (
    id_formation integer NOT NULL,
    id_teacher integer NOT NULL
);


ALTER TABLE heroku_ext.teacher_of OWNER TO jfrbnsrgohior;


CREATE TABLE heroku_ext.trainee (
    id_person integer NOT NULL,
    id_formation integer
);


ALTER TABLE heroku_ext.trainee OWNER TO jfrbnsrgohior;


ALTER TABLE ONLY heroku_ext.car ALTER COLUMN id_car SET DEFAULT nextval('heroku_ext.car_id_car_seq'::regclass);


ALTER TABLE ONLY heroku_ext.car_type ALTER COLUMN id_car_type SET DEFAULT nextval('heroku_ext.car_type_id_car_type_seq'::regclass);


ALTER TABLE ONLY heroku_ext.centre ALTER COLUMN id_centre SET DEFAULT nextval('heroku_ext.centre_id_centre_seq'::regclass);


ALTER TABLE ONLY heroku_ext.city ALTER COLUMN id_city SET DEFAULT nextval('heroku_ext.city_id_city_seq'::regclass);


ALTER TABLE ONLY heroku_ext.day_timetable ALTER COLUMN id_day_timetable SET DEFAULT nextval('heroku_ext.day_timetable_id_day_timetable_seq'::regclass);


ALTER TABLE ONLY heroku_ext.day_week ALTER COLUMN id_day_week SET DEFAULT nextval('heroku_ext.day_week_id_day_week_seq'::regclass);


ALTER TABLE ONLY heroku_ext.destination ALTER COLUMN id_destination SET DEFAULT nextval('heroku_ext.destination_id_destination_seq'::regclass);


ALTER TABLE ONLY heroku_ext.formation ALTER COLUMN id_formation SET DEFAULT nextval('heroku_ext.formation_id_formation_seq'::regclass);


ALTER TABLE ONLY heroku_ext.fuel ALTER COLUMN id_fuel SET DEFAULT nextval('heroku_ext.fuel_id_fuel_seq'::regclass);


ALTER TABLE ONLY heroku_ext.notification ALTER COLUMN id_notification SET DEFAULT nextval('heroku_ext.notification_id_notification_seq'::regclass);


ALTER TABLE ONLY heroku_ext.partner ALTER COLUMN id_partner SET DEFAULT nextval('heroku_ext.partner_id_partner_seq'::regclass);


ALTER TABLE ONLY heroku_ext.person ALTER COLUMN id_person SET DEFAULT nextval('heroku_ext.person_id_person_seq'::regclass);


ALTER TABLE ONLY heroku_ext.ride ALTER COLUMN id_ride SET DEFAULT nextval('heroku_ext.ride_id_ride_seq'::regclass);


INSERT INTO heroku_ext.car_type VALUES (1, 'COMPACT', 7.3, 1);
INSERT INTO heroku_ext.car_type VALUES (2, 'COMPACT', 7.3, 2);
INSERT INTO heroku_ext.car_type VALUES (3, 'COMPACT', 5.2, 3);
INSERT INTO heroku_ext.car_type VALUES (4, 'COMPACT', 7.8, 4);
INSERT INTO heroku_ext.car_type VALUES (5, 'COMPACT', 9.5, 5);
INSERT INTO heroku_ext.car_type VALUES (6, 'BERLINE', 7.5, 1);
INSERT INTO heroku_ext.car_type VALUES (7, 'BERLINE', 7.5, 2);
INSERT INTO heroku_ext.car_type VALUES (8, 'BERLINE', 6.8, 3);
INSERT INTO heroku_ext.car_type VALUES (9, 'BERLINE', 15.6, 4);
INSERT INTO heroku_ext.car_type VALUES (10, 'BERLINE', 12.2, 5);
INSERT INTO heroku_ext.car_type VALUES (11, 'SUV', 8.0, 1);
INSERT INTO heroku_ext.car_type VALUES (12, 'SUV', 8.0, 2);
INSERT INTO heroku_ext.car_type VALUES (13, 'SUV', 7.2, 3);
INSERT INTO heroku_ext.car_type VALUES (14, 'SUV', 16.0, 4);
INSERT INTO heroku_ext.car_type VALUES (15, 'SUV', 16.0, 5);
INSERT INTO heroku_ext.car_type VALUES (16, 'MONOSPACE', 7.0, 1);
INSERT INTO heroku_ext.car_type VALUES (17, 'MONOSPACE', 7.0, 2);
INSERT INTO heroku_ext.car_type VALUES (18, 'MONOSPACE', 6.2, 3);
INSERT INTO heroku_ext.car_type VALUES (19, 'MONOSPACE', 15.6, 4);
INSERT INTO heroku_ext.car_type VALUES (20, 'MONOSPACE', 10.5, 5);
INSERT INTO heroku_ext.car_type VALUES (21, 'UTILITAIRE', 14.3, 3);
INSERT INTO heroku_ext.car_type VALUES (22, 'UTILITAIRE', 14.3, 2);
INSERT INTO heroku_ext.car_type VALUES (23, 'UTILITAIRE', 11.6, 3);
INSERT INTO heroku_ext.car_type VALUES (24, 'UTILITAIRE', 25.0, 4);
INSERT INTO heroku_ext.car_type VALUES (25, 'UTILITAIRE', 18.6, 5);


INSERT INTO heroku_ext.centre VALUES (28, 'Centre Afpa de Rochefort', '57 Avenue Bernadotte', 45.958593, -0.963835, '+33472864830', true);


INSERT INTO heroku_ext.day_timetable VALUES (1, 'MONDAY', '09:00:00', '12:00:00', '13:00:00', '18:00:00', 28);
INSERT INTO heroku_ext.day_timetable VALUES (2, 'TUESDAY', '08:00:00', '12:00:00', '13:00:00', '18:00:00', 28);
INSERT INTO heroku_ext.day_timetable VALUES (3, 'WEDNESDAY', '08:00:00', '12:00:00', '13:00:00', '18:00:00', 28);
INSERT INTO heroku_ext.day_timetable VALUES (4, 'THURSDAY', '08:00:00', '12:00:00', '13:00:00', '18:00:00', 28);
INSERT INTO heroku_ext.day_timetable VALUES (5, 'FRIDAY', '08:00:00', '12:00:00', '13:00:00', '18:00:00', 28);
INSERT INTO heroku_ext.day_timetable VALUES (6, 'SATURDAY', NULL, NULL, NULL, NULL, 28);
INSERT INTO heroku_ext.day_timetable VALUES (7, 'SUNDAY', NULL, NULL, NULL, NULL, 28);


INSERT INTO heroku_ext.day_week VALUES (1, 'MONDAY');
INSERT INTO heroku_ext.day_week VALUES (2, 'TUESDAY');
INSERT INTO heroku_ext.day_week VALUES (3, 'WEDNESDAY');
INSERT INTO heroku_ext.day_week VALUES (4, 'THURSDAY');
INSERT INTO heroku_ext.day_week VALUES (5, 'FRIDAY');
INSERT INTO heroku_ext.day_week VALUES (6, 'SATURDAY');
INSERT INTO heroku_ext.day_week VALUES (7, 'SUNDAY');


INSERT INTO heroku_ext.fuel VALUES (1, 'ESSENCE', 1.82);
INSERT INTO heroku_ext.fuel VALUES (2, 'ESSENCE SUPERTHANOL', 0.82);
INSERT INTO heroku_ext.fuel VALUES (3, 'GAZOLE', 1.82);
INSERT INTO heroku_ext.fuel VALUES (4, ' ELECTRIQUE', 0.17);
INSERT INTO heroku_ext.fuel VALUES (5, 'GPL', 0.87);


INSERT INTO heroku_ext.partner VALUES (1, 'Afaq_9001', 'Afaq_9001.png', 28);
INSERT INTO heroku_ext.partner VALUES (2, 'France Relance', 'france-relance.png', 28);
INSERT INTO heroku_ext.partner VALUES (3, 'fse', 'fse.jpg', 28);
INSERT INTO heroku_ext.partner VALUES (6, 'Synofdes', 'synofdes.png', 28);
INSERT INTO heroku_ext.partner VALUES (5, 'region Nouvelle-Aquitaine', 'region.jpg', 28);
INSERT INTO heroku_ext.partner VALUES (4, 'opqf', 'opqf.png', 28);


SELECT pg_catalog.setval('heroku_ext.car_id_car_seq', 1, false);


SELECT pg_catalog.setval('heroku_ext.car_type_id_car_type_seq', 1, true);


SELECT pg_catalog.setval('heroku_ext.centre_id_centre_seq', 1, false);


SELECT pg_catalog.setval('heroku_ext.city_id_city_seq', 1, true);


SELECT pg_catalog.setval('heroku_ext.day_timetable_id_day_timetable_seq', 1, true);


SELECT pg_catalog.setval('heroku_ext.day_week_id_day_week_seq', 1, false);


SELECT pg_catalog.setval('heroku_ext.destination_id_destination_seq', 15, true);


SELECT pg_catalog.setval('heroku_ext.formation_id_formation_seq', 1, false);


SELECT pg_catalog.setval('heroku_ext.fuel_id_fuel_seq', 1, false);


SELECT pg_catalog.setval('heroku_ext.notification_id_notification_seq', 1, false);


SELECT pg_catalog.setval('heroku_ext.partner_id_partner_seq', 1, false);


SELECT pg_catalog.setval('heroku_ext.person_id_person_seq', 1, false);


SELECT pg_catalog.setval('heroku_ext.ride_id_ride_seq', 1, true);


ALTER TABLE ONLY heroku_ext.car
ADD CONSTRAINT car_pkey PRIMARY KEY (id_car);


ALTER TABLE ONLY heroku_ext.car_type
ADD CONSTRAINT car_type_pkey PRIMARY KEY (id_car_type);


ALTER TABLE ONLY heroku_ext.centre
ADD CONSTRAINT centre_pkey PRIMARY KEY (id_centre);


ALTER TABLE ONLY heroku_ext.city
ADD CONSTRAINT city_pkey PRIMARY KEY (id_city);


ALTER TABLE ONLY heroku_ext.city
ADD CONSTRAINT city_un UNIQUE (name);


ALTER TABLE ONLY heroku_ext.day_timetable
ADD CONSTRAINT day_timetable_pkey PRIMARY KEY (id_day_timetable);


ALTER TABLE ONLY heroku_ext.day_week
ADD CONSTRAINT day_week_pkey PRIMARY KEY (id_day_week);


ALTER TABLE ONLY heroku_ext.destination
ADD CONSTRAINT destination_pkey PRIMARY KEY (id_destination);


ALTER TABLE ONLY heroku_ext.person
ADD CONSTRAINT email_un UNIQUE (email);


ALTER TABLE ONLY heroku_ext.employee
ADD CONSTRAINT employee_pkey PRIMARY KEY (id_person);


ALTER TABLE ONLY heroku_ext.formation
ADD CONSTRAINT formation_pkey PRIMARY KEY (id_formation);


ALTER TABLE ONLY heroku_ext.fuel
ADD CONSTRAINT fuel_pkey PRIMARY KEY (id_fuel);


ALTER TABLE ONLY heroku_ext.recurring_days
ADD CONSTRAINT happen_pkey PRIMARY KEY (id_ride, id_day_week);


ALTER TABLE ONLY heroku_ext.notification
ADD CONSTRAINT notification_pkey PRIMARY KEY (id_notification);


ALTER TABLE ONLY heroku_ext.one_time
ADD CONSTRAINT one_time_pkey PRIMARY KEY (id_ride);


ALTER TABLE ONLY heroku_ext.partner
ADD CONSTRAINT partner_pkey PRIMARY KEY (id_partner);


ALTER TABLE ONLY heroku_ext.person
ADD CONSTRAINT person_pkey PRIMARY KEY (id_person);


ALTER TABLE ONLY heroku_ext.recurring
ADD CONSTRAINT recurring_pkey PRIMARY KEY (id_ride);


ALTER TABLE ONLY heroku_ext.ride_passenger
ADD CONSTRAINT ride_passenger_pkey PRIMARY KEY (id_person, id_ride);


ALTER TABLE ONLY heroku_ext.ride
ADD CONSTRAINT ride_pkey PRIMARY KEY (id_ride);


ALTER TABLE ONLY heroku_ext.teacher_of
ADD CONSTRAINT teacher_of_pkey PRIMARY KEY (id_formation, id_teacher);


ALTER TABLE ONLY heroku_ext.trainee
ADD CONSTRAINT trainee_pkey PRIMARY KEY (id_person);


ALTER TABLE ONLY heroku_ext.car
ADD CONSTRAINT car_id_car_type_fkey FOREIGN KEY (id_car_type) REFERENCES heroku_ext.car_type(id_car_type);


ALTER TABLE ONLY heroku_ext.car
ADD CONSTRAINT car_id_person_fkey FOREIGN KEY (id_person) REFERENCES heroku_ext.person(id_person);


ALTER TABLE ONLY heroku_ext.car_type
ADD CONSTRAINT car_type_id_fuel_fkey FOREIGN KEY (id_fuel) REFERENCES heroku_ext.fuel(id_fuel);


ALTER TABLE ONLY heroku_ext.day_timetable
ADD CONSTRAINT day_timetable_id_centre_fkey FOREIGN KEY (id_centre) REFERENCES heroku_ext.centre(id_centre);


ALTER TABLE ONLY heroku_ext.destination
ADD CONSTRAINT destination_id_city_fkey FOREIGN KEY (id_city) REFERENCES heroku_ext.city(id_city) ON DELETE CASCADE;


ALTER TABLE ONLY heroku_ext.employee
ADD CONSTRAINT employee_id_centre_fkey FOREIGN KEY (id_centre) REFERENCES heroku_ext.centre(id_centre);


ALTER TABLE ONLY heroku_ext.employee
ADD CONSTRAINT employee_id_person_fkey FOREIGN KEY (id_person) REFERENCES heroku_ext.person(id_person);


ALTER TABLE ONLY heroku_ext.formation
ADD CONSTRAINT formation_id_centre_fkey FOREIGN KEY (id_centre) REFERENCES heroku_ext.centre(id_centre);


ALTER TABLE ONLY heroku_ext.recurring_days
ADD CONSTRAINT happen_id_day_week_fkey FOREIGN KEY (id_day_week) REFERENCES heroku_ext.day_week(id_day_week);


ALTER TABLE ONLY heroku_ext.recurring_days
ADD CONSTRAINT happen_id_ride_fkey FOREIGN KEY (id_ride) REFERENCES heroku_ext.recurring(id_ride);


ALTER TABLE ONLY heroku_ext.notification
ADD CONSTRAINT notification_id_person_fkey FOREIGN KEY (id_person) REFERENCES heroku_ext.person(id_person);


ALTER TABLE ONLY heroku_ext.one_time
ADD CONSTRAINT one_time_id_ride_fkey FOREIGN KEY (id_ride) REFERENCES heroku_ext.ride(id_ride) ON DELETE CASCADE;


ALTER TABLE ONLY heroku_ext.partner
ADD CONSTRAINT partner_id_centre_fkey FOREIGN KEY (id_centre) REFERENCES heroku_ext.centre(id_centre);


ALTER TABLE ONLY heroku_ext.recurring
ADD CONSTRAINT recurring_id_ride_fkey FOREIGN KEY (id_ride) REFERENCES heroku_ext.ride(id_ride) ON DELETE CASCADE;


ALTER TABLE ONLY heroku_ext.ride
ADD CONSTRAINT ride_id_car_fkey FOREIGN KEY (id_car) REFERENCES heroku_ext.car(id_car);


ALTER TABLE ONLY heroku_ext.ride
ADD CONSTRAINT ride_id_destination_fkey FOREIGN KEY (id_destination) REFERENCES heroku_ext.destination(id_destination) ON DELETE CASCADE;


ALTER TABLE ONLY heroku_ext.ride_passenger
ADD CONSTRAINT ride_passenger_id_person_fkey FOREIGN KEY (id_person) REFERENCES heroku_ext.person(id_person);


ALTER TABLE ONLY heroku_ext.ride_passenger
ADD CONSTRAINT ride_passenger_id_ride_fkey FOREIGN KEY (id_ride) REFERENCES heroku_ext.ride(id_ride) ON DELETE CASCADE;


ALTER TABLE ONLY heroku_ext.teacher_of
ADD CONSTRAINT teacher_of_id_formation_fkey FOREIGN KEY (id_formation) REFERENCES heroku_ext.formation(id_formation);


ALTER TABLE ONLY heroku_ext.teacher_of
ADD CONSTRAINT teacher_of_id_teacher_fkey FOREIGN KEY (id_teacher) REFERENCES heroku_ext.employee(id_person);


ALTER TABLE ONLY heroku_ext.trainee
ADD CONSTRAINT trainee_id_formation_fkey FOREIGN KEY (id_formation) REFERENCES heroku_ext.formation(id_formation);


ALTER TABLE ONLY heroku_ext.trainee
ADD CONSTRAINT trainee_id_person_fkey FOREIGN KEY (id_person) REFERENCES heroku_ext.person(id_person);




CREATE extension cube SCHEMA heroku_ext;
CREATE extension earthdistance SCHEMA heroku_ext;

CREATE OR REPLACE FUNCTION heroku_ext.get_distance(latitude_1 double precision, longitude_1 double precision, latitude_2 double precision, longitude_2 double precision)
RETURNS double precision
LANGUAGE sql
IMMUTABLE STRICT
RETURN heroku_ext.earth_distance(heroku_ext.ll_to_earth(latitude_1, longitude_1), heroku_ext.ll_to_earth(latitude_2, longitude_2)) / 1000;




CREATE ROLE "afpaUser" WITH
	LOGIN
	NOSUPERUSER
	NOCREATEDB
	NOCREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'Afpa4apfA!'; 
COMMENT ON ROLE "afpaUser" IS 'Classic User for heroku_ext';

GRANT USAGE ON SCHEMA "heroku_ext" TO "afpaUser"; 

GRANT ALL ON TABLE "heroku_ext".car TO "afpaUser";
GRANT ALL ON TABLE "heroku_ext".car_type TO "afpaUser";
GRANT ALL ON TABLE "heroku_ext".centre TO "afpaUser";
GRANT ALL ON TABLE "heroku_ext".city TO "afpaUser";
GRANT ALL ON TABLE "heroku_ext".day_timetable TO "afpaUser";
GRANT ALL ON TABLE "heroku_ext".destination TO "afpaUser";
GRANT ALL ON TABLE "heroku_ext".employee TO "afpaUser";
GRANT ALL ON TABLE "heroku_ext".formation TO "afpaUser";
GRANT ALL ON TABLE "heroku_ext".fuel TO "afpaUser";
GRANT ALL ON TABLE "heroku_ext".notification TO "afpaUser";
GRANT ALL ON TABLE "heroku_ext".one_time TO "afpaUser";
GRANT ALL ON TABLE "heroku_ext".partner TO "afpaUser";
GRANT ALL ON TABLE "heroku_ext".person TO "afpaUser";
GRANT ALL ON TABLE "heroku_ext".recurring TO "afpaUser";
GRANT ALL ON TABLE "heroku_ext".ride TO "afpaUser";
GRANT ALL ON TABLE "heroku_ext".ride_passenger TO "afpaUser";
GRANT ALL ON TABLE "heroku_ext".trainee TO "afpaUser";
GRANT ALL ON TABLE "heroku_ext".day_week TO "afpaUser";
GRANT ALL ON TABLE "heroku_ext".recurring_days TO "afpaUser";
GRANT ALL ON TABLE "heroku_ext".teacher_of TO "afpaUser";

GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA "heroku_ext" TO "afpaUser";
GRANT EXECUTE ON FUNCTION heroku_ext.get_distance(float8, float8, float8, float8) TO "afpaUser";