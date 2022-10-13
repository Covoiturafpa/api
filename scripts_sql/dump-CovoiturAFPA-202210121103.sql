--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5
-- Dumped by pg_dump version 14.5

-- Started on 2022-10-12 11:03:14

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

--
-- TOC entry 6 (class 2615 OID 33295)
-- Name: covoiturafpa; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA covoiturafpa;


ALTER SCHEMA covoiturafpa OWNER TO postgres;

--
-- TOC entry 855 (class 1247 OID 33297)
-- Name: car_type_name; Type: TYPE; Schema: covoiturafpa; Owner: postgres
--

CREATE TYPE covoiturafpa.car_type_name AS ENUM (
    'COMPACT',
    'BERLINE',
    'SUV',
    'MONOSPACE',
    'UTILITAIRE'
);


ALTER TYPE covoiturafpa.car_type_name OWNER TO postgres;

--
-- TOC entry 858 (class 1247 OID 33308)
-- Name: day_name; Type: TYPE; Schema: covoiturafpa; Owner: postgres
--

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

--
-- TOC entry 861 (class 1247 OID 33324)
-- Name: notification_type; Type: TYPE; Schema: covoiturafpa; Owner: postgres
--

CREATE TYPE covoiturafpa.notification_type AS ENUM (
    'NEW_RESERVATION',
    'ACCEPTED_RESERVATION',
    'REJECTED_RESERVATION'
);


ALTER TYPE covoiturafpa.notification_type OWNER TO postgres;

--
-- TOC entry 864 (class 1247 OID 33332)
-- Name: status_type; Type: TYPE; Schema: covoiturafpa; Owner: postgres
--

CREATE TYPE covoiturafpa.status_type AS ENUM (
    'PENDING',
    'ACCEPTED',
    'FINISHED'
);


ALTER TYPE covoiturafpa.status_type OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 33339)
-- Name: car; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

CREATE TABLE covoiturafpa.car (
    id_car integer NOT NULL,
    model character varying(50),
    seats smallint,
    avg_fuel_consumption numeric(4,1),
    id_car_type integer NOT NULL,
    id_person integer NOT NULL
);


ALTER TABLE covoiturafpa.car OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 33342)
-- Name: car_id_car_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: postgres
--

CREATE SEQUENCE covoiturafpa.car_id_car_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.car_id_car_seq OWNER TO postgres;

--
-- TOC entry 3523 (class 0 OID 0)
-- Dependencies: 211
-- Name: car_id_car_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: postgres
--

ALTER SEQUENCE covoiturafpa.car_id_car_seq OWNED BY covoiturafpa.car.id_car;


--
-- TOC entry 212 (class 1259 OID 33343)
-- Name: car_type; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

CREATE TABLE covoiturafpa.car_type (
    id_car_type integer NOT NULL,
    name covoiturafpa.car_type_name,
    avg_fuel_consumption numeric(4,1),
    id_fuel integer NOT NULL
);


ALTER TABLE covoiturafpa.car_type OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 33346)
-- Name: car_type_id_car_type_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: postgres
--

CREATE SEQUENCE covoiturafpa.car_type_id_car_type_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.car_type_id_car_type_seq OWNER TO postgres;

--
-- TOC entry 3526 (class 0 OID 0)
-- Dependencies: 213
-- Name: car_type_id_car_type_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: postgres
--

ALTER SEQUENCE covoiturafpa.car_type_id_car_type_seq OWNED BY covoiturafpa.car_type.id_car_type;


--
-- TOC entry 214 (class 1259 OID 33347)
-- Name: centre; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

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

--
-- TOC entry 215 (class 1259 OID 33350)
-- Name: centre_id_centre_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: postgres
--

CREATE SEQUENCE covoiturafpa.centre_id_centre_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.centre_id_centre_seq OWNER TO postgres;

--
-- TOC entry 3529 (class 0 OID 0)
-- Dependencies: 215
-- Name: centre_id_centre_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: postgres
--

ALTER SEQUENCE covoiturafpa.centre_id_centre_seq OWNED BY covoiturafpa.centre.id_centre;


--
-- TOC entry 216 (class 1259 OID 33351)
-- Name: city; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

CREATE TABLE covoiturafpa.city (
    id_city integer NOT NULL,
    name character varying(255)
);


ALTER TABLE covoiturafpa.city OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 33354)
-- Name: city_id_city_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: postgres
--

CREATE SEQUENCE covoiturafpa.city_id_city_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.city_id_city_seq OWNER TO postgres;

--
-- TOC entry 3532 (class 0 OID 0)
-- Dependencies: 217
-- Name: city_id_city_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: postgres
--

ALTER SEQUENCE covoiturafpa.city_id_city_seq OWNED BY covoiturafpa.city.id_city;


--
-- TOC entry 218 (class 1259 OID 33355)
-- Name: day_timetable; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

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

--
-- TOC entry 219 (class 1259 OID 33358)
-- Name: day_timetable_id_day_timetable_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: postgres
--

CREATE SEQUENCE covoiturafpa.day_timetable_id_day_timetable_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.day_timetable_id_day_timetable_seq OWNER TO postgres;

--
-- TOC entry 3535 (class 0 OID 0)
-- Dependencies: 219
-- Name: day_timetable_id_day_timetable_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: postgres
--

ALTER SEQUENCE covoiturafpa.day_timetable_id_day_timetable_seq OWNED BY covoiturafpa.day_timetable.id_day_timetable;


--
-- TOC entry 220 (class 1259 OID 33359)
-- Name: day_week; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

CREATE TABLE covoiturafpa.day_week (
    id_day_week integer NOT NULL,
    name covoiturafpa.day_name
);


ALTER TABLE covoiturafpa.day_week OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 33362)
-- Name: day_week_id_day_week_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: postgres
--

CREATE SEQUENCE covoiturafpa.day_week_id_day_week_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.day_week_id_day_week_seq OWNER TO postgres;

--
-- TOC entry 3538 (class 0 OID 0)
-- Dependencies: 221
-- Name: day_week_id_day_week_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: postgres
--

ALTER SEQUENCE covoiturafpa.day_week_id_day_week_seq OWNED BY covoiturafpa.day_week.id_day_week;


--
-- TOC entry 222 (class 1259 OID 33363)
-- Name: destination; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

CREATE TABLE covoiturafpa.destination (
    id_destination integer NOT NULL,
    latitude double precision,
    longitude double precision,
    is_from_afpa boolean,
    id_city integer
);


ALTER TABLE covoiturafpa.destination OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 33366)
-- Name: destination_id_destination_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: postgres
--

CREATE SEQUENCE covoiturafpa.destination_id_destination_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.destination_id_destination_seq OWNER TO postgres;

--
-- TOC entry 3541 (class 0 OID 0)
-- Dependencies: 223
-- Name: destination_id_destination_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: postgres
--

ALTER SEQUENCE covoiturafpa.destination_id_destination_seq OWNED BY covoiturafpa.destination.id_destination;


--
-- TOC entry 224 (class 1259 OID 33367)
-- Name: employee; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

CREATE TABLE covoiturafpa.employee (
    id_person integer NOT NULL,
    service character varying(50),
    is_admin boolean,
    id_centre integer NOT NULL,
    is_teacher boolean
);


ALTER TABLE covoiturafpa.employee OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 33370)
-- Name: formation; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

CREATE TABLE covoiturafpa.formation (
    id_formation integer NOT NULL,
    name character varying(50),
    id_centre integer NOT NULL
);


ALTER TABLE covoiturafpa.formation OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 33373)
-- Name: formation_id_formation_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: postgres
--

CREATE SEQUENCE covoiturafpa.formation_id_formation_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.formation_id_formation_seq OWNER TO postgres;

--
-- TOC entry 3545 (class 0 OID 0)
-- Dependencies: 226
-- Name: formation_id_formation_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: postgres
--

ALTER SEQUENCE covoiturafpa.formation_id_formation_seq OWNED BY covoiturafpa.formation.id_formation;


--
-- TOC entry 227 (class 1259 OID 33374)
-- Name: fuel; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

CREATE TABLE covoiturafpa.fuel (
    id_fuel integer NOT NULL,
    name character varying(20),
    price_by_unit numeric(5,2)
);


ALTER TABLE covoiturafpa.fuel OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 33377)
-- Name: fuel_id_fuel_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: postgres
--

CREATE SEQUENCE covoiturafpa.fuel_id_fuel_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.fuel_id_fuel_seq OWNER TO postgres;

--
-- TOC entry 3548 (class 0 OID 0)
-- Dependencies: 228
-- Name: fuel_id_fuel_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: postgres
--

ALTER SEQUENCE covoiturafpa.fuel_id_fuel_seq OWNED BY covoiturafpa.fuel.id_fuel;


--
-- TOC entry 230 (class 1259 OID 33385)
-- Name: notification; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

CREATE TABLE covoiturafpa.notification (
    id_notification integer NOT NULL,
    type covoiturafpa.notification_type,
    created_time timestamp without time zone,
    is_unread boolean,
    id_person integer NOT NULL
);


ALTER TABLE covoiturafpa.notification OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 33388)
-- Name: notification_id_notification_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: postgres
--

CREATE SEQUENCE covoiturafpa.notification_id_notification_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.notification_id_notification_seq OWNER TO postgres;

--
-- TOC entry 3551 (class 0 OID 0)
-- Dependencies: 231
-- Name: notification_id_notification_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: postgres
--

ALTER SEQUENCE covoiturafpa.notification_id_notification_seq OWNED BY covoiturafpa.notification.id_notification;


--
-- TOC entry 232 (class 1259 OID 33389)
-- Name: one_time; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

CREATE TABLE covoiturafpa.one_time (
    id_ride integer NOT NULL,
    departure_day date
);


ALTER TABLE covoiturafpa.one_time OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 33392)
-- Name: partner; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

CREATE TABLE covoiturafpa.partner (
    id_partner integer NOT NULL,
    name character varying(50),
    logo_picture_path character varying(255),
    id_centre integer NOT NULL
);


ALTER TABLE covoiturafpa.partner OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 33395)
-- Name: partner_id_partner_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: postgres
--

CREATE SEQUENCE covoiturafpa.partner_id_partner_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.partner_id_partner_seq OWNER TO postgres;

--
-- TOC entry 3555 (class 0 OID 0)
-- Dependencies: 234
-- Name: partner_id_partner_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: postgres
--

ALTER SEQUENCE covoiturafpa.partner_id_partner_seq OWNED BY covoiturafpa.partner.id_partner;


--
-- TOC entry 235 (class 1259 OID 33396)
-- Name: person; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

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

--
-- TOC entry 236 (class 1259 OID 33401)
-- Name: person_id_person_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: postgres
--

CREATE SEQUENCE covoiturafpa.person_id_person_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.person_id_person_seq OWNER TO postgres;

--
-- TOC entry 3558 (class 0 OID 0)
-- Dependencies: 236
-- Name: person_id_person_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: postgres
--

ALTER SEQUENCE covoiturafpa.person_id_person_seq OWNED BY covoiturafpa.person.id_person;


--
-- TOC entry 237 (class 1259 OID 33402)
-- Name: recurring; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

CREATE TABLE covoiturafpa.recurring (
    id_ride integer NOT NULL,
    beginning date,
    ending date
);


ALTER TABLE covoiturafpa.recurring OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 33378)
-- Name: recurring_days; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

CREATE TABLE covoiturafpa.recurring_days (
    id_ride integer NOT NULL,
    id_day_week integer NOT NULL
);


ALTER TABLE covoiturafpa.recurring_days OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 33405)
-- Name: ride; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

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

--
-- TOC entry 239 (class 1259 OID 33410)
-- Name: ride_id_ride_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: postgres
--

CREATE SEQUENCE covoiturafpa.ride_id_ride_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE covoiturafpa.ride_id_ride_seq OWNER TO postgres;

--
-- TOC entry 3563 (class 0 OID 0)
-- Dependencies: 239
-- Name: ride_id_ride_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: postgres
--

ALTER SEQUENCE covoiturafpa.ride_id_ride_seq OWNED BY covoiturafpa.ride.id_ride;


--
-- TOC entry 240 (class 1259 OID 33411)
-- Name: ride_passenger; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

CREATE TABLE covoiturafpa.ride_passenger (
    id_person integer NOT NULL,
    id_ride integer NOT NULL,
    status covoiturafpa.status_type,
    last_update timestamp without time zone,
    is_driver boolean
);


ALTER TABLE covoiturafpa.ride_passenger OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 49160)
-- Name: teacher_of; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

CREATE TABLE covoiturafpa.teacher_of (
    id_formation integer NOT NULL,
    id_teacher integer NOT NULL
);


ALTER TABLE covoiturafpa.teacher_of OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 33414)
-- Name: trainee; Type: TABLE; Schema: covoiturafpa; Owner: postgres
--

CREATE TABLE covoiturafpa.trainee (
    id_person integer NOT NULL,
    id_formation integer
);


ALTER TABLE covoiturafpa.trainee OWNER TO postgres;

--
-- TOC entry 3265 (class 2604 OID 33417)
-- Name: car id_car; Type: DEFAULT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.car ALTER COLUMN id_car SET DEFAULT nextval('covoiturafpa.car_id_car_seq'::regclass);


--
-- TOC entry 3266 (class 2604 OID 33418)
-- Name: car_type id_car_type; Type: DEFAULT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.car_type ALTER COLUMN id_car_type SET DEFAULT nextval('covoiturafpa.car_type_id_car_type_seq'::regclass);


--
-- TOC entry 3267 (class 2604 OID 33419)
-- Name: centre id_centre; Type: DEFAULT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.centre ALTER COLUMN id_centre SET DEFAULT nextval('covoiturafpa.centre_id_centre_seq'::regclass);


--
-- TOC entry 3268 (class 2604 OID 33420)
-- Name: city id_city; Type: DEFAULT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.city ALTER COLUMN id_city SET DEFAULT nextval('covoiturafpa.city_id_city_seq'::regclass);


--
-- TOC entry 3269 (class 2604 OID 33421)
-- Name: day_timetable id_day_timetable; Type: DEFAULT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.day_timetable ALTER COLUMN id_day_timetable SET DEFAULT nextval('covoiturafpa.day_timetable_id_day_timetable_seq'::regclass);


--
-- TOC entry 3270 (class 2604 OID 33422)
-- Name: day_week id_day_week; Type: DEFAULT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.day_week ALTER COLUMN id_day_week SET DEFAULT nextval('covoiturafpa.day_week_id_day_week_seq'::regclass);


--
-- TOC entry 3271 (class 2604 OID 33423)
-- Name: destination id_destination; Type: DEFAULT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.destination ALTER COLUMN id_destination SET DEFAULT nextval('covoiturafpa.destination_id_destination_seq'::regclass);


--
-- TOC entry 3272 (class 2604 OID 33424)
-- Name: formation id_formation; Type: DEFAULT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.formation ALTER COLUMN id_formation SET DEFAULT nextval('covoiturafpa.formation_id_formation_seq'::regclass);


--
-- TOC entry 3273 (class 2604 OID 33425)
-- Name: fuel id_fuel; Type: DEFAULT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.fuel ALTER COLUMN id_fuel SET DEFAULT nextval('covoiturafpa.fuel_id_fuel_seq'::regclass);


--
-- TOC entry 3274 (class 2604 OID 33427)
-- Name: notification id_notification; Type: DEFAULT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.notification ALTER COLUMN id_notification SET DEFAULT nextval('covoiturafpa.notification_id_notification_seq'::regclass);


--
-- TOC entry 3275 (class 2604 OID 33428)
-- Name: partner id_partner; Type: DEFAULT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.partner ALTER COLUMN id_partner SET DEFAULT nextval('covoiturafpa.partner_id_partner_seq'::regclass);


--
-- TOC entry 3276 (class 2604 OID 33429)
-- Name: person id_person; Type: DEFAULT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.person ALTER COLUMN id_person SET DEFAULT nextval('covoiturafpa.person_id_person_seq'::regclass);


--
-- TOC entry 3277 (class 2604 OID 33430)
-- Name: ride id_ride; Type: DEFAULT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.ride ALTER COLUMN id_ride SET DEFAULT nextval('covoiturafpa.ride_id_ride_seq'::regclass);


--
-- TOC entry 3483 (class 0 OID 33339)
-- Dependencies: 210
-- Data for Name: car; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--

INSERT INTO covoiturafpa.car VALUES (3, 'Chrysler Land Cruiser', 6, 6.3, 1, 45);
INSERT INTO covoiturafpa.car VALUES (4, 'Porsche Taurus', 6, 4.2, 4, 51);
INSERT INTO covoiturafpa.car VALUES (2, 'BMW ATS', 5, 1.8, 3, 51);


--
-- TOC entry 3485 (class 0 OID 33343)
-- Dependencies: 212
-- Data for Name: car_type; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--

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


--
-- TOC entry 3487 (class 0 OID 33347)
-- Dependencies: 214
-- Data for Name: centre; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--

INSERT INTO covoiturafpa.centre VALUES (28, 'Centre Afpa de Rochefort', '57 Avenue Bernadotte', 45.958593, -0.963835, '+33472864830', true);


--
-- TOC entry 3489 (class 0 OID 33351)
-- Dependencies: 216
-- Data for Name: city; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--

INSERT INTO covoiturafpa.city VALUES (10, 'La Rochelle');
INSERT INTO covoiturafpa.city VALUES (20, 'Chatellerault');
INSERT INTO covoiturafpa.city VALUES (30, 'Mirebeau');
INSERT INTO covoiturafpa.city VALUES (4, 'Lencloitre');
INSERT INTO covoiturafpa.city VALUES (5, 'Paris');


--
-- TOC entry 3491 (class 0 OID 33355)
-- Dependencies: 218
-- Data for Name: day_timetable; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--

INSERT INTO covoiturafpa.day_timetable VALUES (2, 'TUESDAY', '08:00:00', '12:00:00', '13:00:00', '18:00:00', 28);
INSERT INTO covoiturafpa.day_timetable VALUES (3, 'WEDNESDAY', '08:00:00', '12:00:00', '13:00:00', '18:00:00', 28);
INSERT INTO covoiturafpa.day_timetable VALUES (4, 'THURSDAY', '08:00:00', '12:00:00', '13:00:00', '18:00:00', 28);
INSERT INTO covoiturafpa.day_timetable VALUES (5, 'FRIDAY', '08:00:00', '12:00:00', '13:00:00', '18:00:00', 28);
INSERT INTO covoiturafpa.day_timetable VALUES (6, 'SATURDAY', NULL, NULL, NULL, NULL, 28);
INSERT INTO covoiturafpa.day_timetable VALUES (7, 'SUNDAY', NULL, NULL, NULL, NULL, 28);
INSERT INTO covoiturafpa.day_timetable VALUES (1, 'MONDAY', '09:00:00', '12:00:00', '13:00:00', '18:00:00', 28);


--
-- TOC entry 3493 (class 0 OID 33359)
-- Dependencies: 220
-- Data for Name: day_week; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--

INSERT INTO covoiturafpa.day_week VALUES (1, 'MONDAY');
INSERT INTO covoiturafpa.day_week VALUES (2, 'TUESDAY');
INSERT INTO covoiturafpa.day_week VALUES (3, 'WEDNESDAY');
INSERT INTO covoiturafpa.day_week VALUES (4, 'THURSDAY');
INSERT INTO covoiturafpa.day_week VALUES (5, 'FRIDAY');
INSERT INTO covoiturafpa.day_week VALUES (6, 'SATURDAY');
INSERT INTO covoiturafpa.day_week VALUES (7, 'SUNDAY');


--
-- TOC entry 3495 (class 0 OID 33363)
-- Dependencies: 222
-- Data for Name: destination; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--

INSERT INTO covoiturafpa.destination VALUES (10000, 59.9211, 81.1344, false, 10);
INSERT INTO covoiturafpa.destination VALUES (20000, 59.9211, 81.1344, true, 20);
INSERT INTO covoiturafpa.destination VALUES (30000, 46.79087385, 0.2054939898438607, false, 30);
INSERT INTO covoiturafpa.destination VALUES (1, 46.79087385, 0.2054939898438607, false, 30);
INSERT INTO covoiturafpa.destination VALUES (4, 46.79087385, 0.2054939898438607, false, 30);
INSERT INTO covoiturafpa.destination VALUES (5, 46.79087385, 0.2054939898438607, false, 30);
INSERT INTO covoiturafpa.destination VALUES (3, 46.79087385, 0.2054939898438607, false, 30);
INSERT INTO covoiturafpa.destination VALUES (2, 46.79087385, 0.2054939898438607, false, 30);


--
-- TOC entry 3497 (class 0 OID 33367)
-- Dependencies: 224
-- Data for Name: employee; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--

INSERT INTO covoiturafpa.employee VALUES (97, 'restauration', true, 28, false);
INSERT INTO covoiturafpa.employee VALUES (57, 'administration', true, 28, false);
INSERT INTO covoiturafpa.employee VALUES (51, 'formateur', true, 28, true);


--
-- TOC entry 3498 (class 0 OID 33370)
-- Dependencies: 225
-- Data for Name: formation; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--

INSERT INTO covoiturafpa.formation VALUES (1, 'Necessitserunt quo cumque', 28);
INSERT INTO covoiturafpa.formation VALUES (4, 'Ullam doloribus praesentium quisqu', 28);
INSERT INTO covoiturafpa.formation VALUES (7, 'Molestias iusto veniam ut cum.', 28);
INSERT INTO covoiturafpa.formation VALUES (2, 'Et quia eerum quo alias nisi magni.', 28);
INSERT INTO covoiturafpa.formation VALUES (5, 'Voluptatrecusandae nisi.', 28);


--
-- TOC entry 3500 (class 0 OID 33374)
-- Dependencies: 227
-- Data for Name: fuel; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--

INSERT INTO covoiturafpa.fuel VALUES (1, 'ESSENCE', 1.82);
INSERT INTO covoiturafpa.fuel VALUES (2, 'ESSENCE SUPERTHANOL', 0.82);
INSERT INTO covoiturafpa.fuel VALUES (3, 'GAZOLE', 1.82);
INSERT INTO covoiturafpa.fuel VALUES (4, ' ELECTRIQUE', 0.17);
INSERT INTO covoiturafpa.fuel VALUES (5, 'GPL', 0.87);


--
-- TOC entry 3503 (class 0 OID 33385)
-- Dependencies: 230
-- Data for Name: notification; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--

INSERT INTO covoiturafpa.notification VALUES (82, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', false, 97);
INSERT INTO covoiturafpa.notification VALUES (78, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', false, 97);
INSERT INTO covoiturafpa.notification VALUES (32, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', false, 97);
INSERT INTO covoiturafpa.notification VALUES (93, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', false, 97);
INSERT INTO covoiturafpa.notification VALUES (73, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', true, 43);
INSERT INTO covoiturafpa.notification VALUES (8, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', true, 45);
INSERT INTO covoiturafpa.notification VALUES (30, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', false, 43);
INSERT INTO covoiturafpa.notification VALUES (76, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', true, 45);
INSERT INTO covoiturafpa.notification VALUES (5, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', false, 57);
INSERT INTO covoiturafpa.notification VALUES (25, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', true, 51);


--
-- TOC entry 3505 (class 0 OID 33389)
-- Dependencies: 232
-- Data for Name: one_time; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--

INSERT INTO covoiturafpa.one_time VALUES (10, '2022-10-16');
INSERT INTO covoiturafpa.one_time VALUES (11, '2022-10-16');


--
-- TOC entry 3506 (class 0 OID 33392)
-- Dependencies: 233
-- Data for Name: partner; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--

INSERT INTO covoiturafpa.partner VALUES (1, 'Afaq_9001', 'Afaq_9001.png', 28);
INSERT INTO covoiturafpa.partner VALUES (2, 'France Relance', 'france-relance.png', 28);
INSERT INTO covoiturafpa.partner VALUES (3, 'fse', 'fse.jpg', 28);
INSERT INTO covoiturafpa.partner VALUES (6, 'Synofdes', 'synofdes.png', 28);
INSERT INTO covoiturafpa.partner VALUES (5, 'region Nouvelle-Aquitaine', 'region.jpg', 28);
INSERT INTO covoiturafpa.partner VALUES (4, 'opqf', 'opqf.png', 28);


--
-- TOC entry 3508 (class 0 OID 33396)
-- Dependencies: 235
-- Data for Name: person; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--

INSERT INTO covoiturafpa.person VALUES (57, 'CristopherWolf@mail.fr', 'movlejxx6c3fpybxlw6o', 'Weber', 'Casper', '+33511397860', true, false, false, '2022-01-01 00:00:00', 'https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/684.jpg', 'E', '2022-10-12', NULL);
INSERT INTO covoiturafpa.person VALUES (45, 'KrystelRath@mail.fr', '$2a$10$ADYcx/WiB5kIo7.8/IiU8eJEBxCt/bPnAeNKTWrrAvJh3SHKFcDxu', 'Howell', 'Godfrey', '+33430830984', true, true, true, '2022-01-01 00:00:00', 'https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/390.jpg', 'T', '2022-10-12', NULL);
INSERT INTO covoiturafpa.person VALUES (43, 'MadgeSmith@mail.fr', 'b4lnkw50s5kvt9cx01m7', 'Bergnaum', 'Karson', '+33672728649', true, true, true, '2022-01-01 00:00:00', 'https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/1223.jpg', 'T', '2022-10-12', '2023-01-01');
INSERT INTO covoiturafpa.person VALUES (97, 'OdaBoyle@mail.fr', '$2a$10$5o6/3c5r9fwa1yvBZy9ZMuf.oWf1ktHAyf88Ugiu3rTl3iu8FKZSe', 'Leffler', 'Billie', '+33458210191', true, false, true, '2022-01-01 00:00:00', 'https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/658.jpg', 'E', '2022-10-12', '2023-10-12');
INSERT INTO covoiturafpa.person VALUES (51, 'MohammadGreenfelder@mail.fr', '$2y$10$Ae8fxwlNSLuODT86ktLBHeDkKXFDOUT6GzqlP85.Yl/TOueXrnuT.', 'Bailey', 'Wava', '+33983567107', true, false, false, '2022-01-01 00:00:00', 'https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/420.jpg', 'E', '2022-10-12', '2023-12-12');


--
-- TOC entry 3510 (class 0 OID 33402)
-- Dependencies: 237
-- Data for Name: recurring; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--

INSERT INTO covoiturafpa.recurring VALUES (3, '2022-01-01', '2022-03-01');


--
-- TOC entry 3502 (class 0 OID 33378)
-- Dependencies: 229
-- Data for Name: recurring_days; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--

INSERT INTO covoiturafpa.recurring_days VALUES (3, 1);
INSERT INTO covoiturafpa.recurring_days VALUES (3, 2);
INSERT INTO covoiturafpa.recurring_days VALUES (3, 3);
INSERT INTO covoiturafpa.recurring_days VALUES (3, 4);


--
-- TOC entry 3511 (class 0 OID 33405)
-- Dependencies: 238
-- Data for Name: ride; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--

INSERT INTO covoiturafpa.ride VALUES (3, true, '00:00:00', 'Et doloribus velit rem. Quidem dolore consequatur voluptate excepturi aut accusamus a.', 20000, 2, 15.00, 'R');
INSERT INTO covoiturafpa.ride VALUES (10, false, '08:00:00', 'lorem', 3, 4, 7.00, 'O');
INSERT INTO covoiturafpa.ride VALUES (11, false, '08:00:00', 'lorem', 4, 4, 7.00, 'O');


--
-- TOC entry 3513 (class 0 OID 33411)
-- Dependencies: 240
-- Data for Name: ride_passenger; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--

INSERT INTO covoiturafpa.ride_passenger VALUES (57, 3, 'PENDING', '2022-03-01 00:00:00', false);
INSERT INTO covoiturafpa.ride_passenger VALUES (43, 3, 'PENDING', '2022-03-01 00:00:00', true);
INSERT INTO covoiturafpa.ride_passenger VALUES (51, 3, 'PENDING', '2022-03-01 00:00:00', false);


--
-- TOC entry 3515 (class 0 OID 49160)
-- Dependencies: 242
-- Data for Name: teacher_of; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--



--
-- TOC entry 3514 (class 0 OID 33414)
-- Dependencies: 241
-- Data for Name: trainee; Type: TABLE DATA; Schema: covoiturafpa; Owner: postgres
--

INSERT INTO covoiturafpa.trainee VALUES (43, 1);
INSERT INTO covoiturafpa.trainee VALUES (45, 4);


--
-- TOC entry 3568 (class 0 OID 0)
-- Dependencies: 211
-- Name: car_id_car_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: postgres
--

SELECT pg_catalog.setval('covoiturafpa.car_id_car_seq', 1, false);


--
-- TOC entry 3569 (class 0 OID 0)
-- Dependencies: 213
-- Name: car_type_id_car_type_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: postgres
--

SELECT pg_catalog.setval('covoiturafpa.car_type_id_car_type_seq', 1, true);


--
-- TOC entry 3570 (class 0 OID 0)
-- Dependencies: 215
-- Name: centre_id_centre_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: postgres
--

SELECT pg_catalog.setval('covoiturafpa.centre_id_centre_seq', 1, false);


--
-- TOC entry 3571 (class 0 OID 0)
-- Dependencies: 217
-- Name: city_id_city_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: postgres
--

SELECT pg_catalog.setval('covoiturafpa.city_id_city_seq', 10, true);


--
-- TOC entry 3572 (class 0 OID 0)
-- Dependencies: 219
-- Name: day_timetable_id_day_timetable_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: postgres
--

SELECT pg_catalog.setval('covoiturafpa.day_timetable_id_day_timetable_seq', 1, true);


--
-- TOC entry 3573 (class 0 OID 0)
-- Dependencies: 221
-- Name: day_week_id_day_week_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: postgres
--

SELECT pg_catalog.setval('covoiturafpa.day_week_id_day_week_seq', 1, false);


--
-- TOC entry 3574 (class 0 OID 0)
-- Dependencies: 223
-- Name: destination_id_destination_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: postgres
--

SELECT pg_catalog.setval('covoiturafpa.destination_id_destination_seq', 15, true);


--
-- TOC entry 3575 (class 0 OID 0)
-- Dependencies: 226
-- Name: formation_id_formation_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: postgres
--

SELECT pg_catalog.setval('covoiturafpa.formation_id_formation_seq', 1, false);


--
-- TOC entry 3576 (class 0 OID 0)
-- Dependencies: 228
-- Name: fuel_id_fuel_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: postgres
--

SELECT pg_catalog.setval('covoiturafpa.fuel_id_fuel_seq', 1, false);


--
-- TOC entry 3577 (class 0 OID 0)
-- Dependencies: 231
-- Name: notification_id_notification_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: postgres
--

SELECT pg_catalog.setval('covoiturafpa.notification_id_notification_seq', 1, false);


--
-- TOC entry 3578 (class 0 OID 0)
-- Dependencies: 234
-- Name: partner_id_partner_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: postgres
--

SELECT pg_catalog.setval('covoiturafpa.partner_id_partner_seq', 1, false);


--
-- TOC entry 3579 (class 0 OID 0)
-- Dependencies: 236
-- Name: person_id_person_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: postgres
--

SELECT pg_catalog.setval('covoiturafpa.person_id_person_seq', 1, false);


--
-- TOC entry 3580 (class 0 OID 0)
-- Dependencies: 239
-- Name: ride_id_ride_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: postgres
--

SELECT pg_catalog.setval('covoiturafpa.ride_id_ride_seq', 19, true);


--
-- TOC entry 3279 (class 2606 OID 33432)
-- Name: car car_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.car
    ADD CONSTRAINT car_pkey PRIMARY KEY (id_car);


--
-- TOC entry 3281 (class 2606 OID 33434)
-- Name: car_type car_type_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.car_type
    ADD CONSTRAINT car_type_pkey PRIMARY KEY (id_car_type);


--
-- TOC entry 3283 (class 2606 OID 33438)
-- Name: centre centre_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.centre
    ADD CONSTRAINT centre_pkey PRIMARY KEY (id_centre);


--
-- TOC entry 3285 (class 2606 OID 33440)
-- Name: city city_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id_city);


--
-- TOC entry 3287 (class 2606 OID 49206)
-- Name: city city_un; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.city
    ADD CONSTRAINT city_un UNIQUE (name);


--
-- TOC entry 3289 (class 2606 OID 33442)
-- Name: day_timetable day_timetable_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.day_timetable
    ADD CONSTRAINT day_timetable_pkey PRIMARY KEY (id_day_timetable);


--
-- TOC entry 3291 (class 2606 OID 33444)
-- Name: day_week day_week_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.day_week
    ADD CONSTRAINT day_week_pkey PRIMARY KEY (id_day_week);


--
-- TOC entry 3293 (class 2606 OID 33446)
-- Name: destination destination_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.destination
    ADD CONSTRAINT destination_pkey PRIMARY KEY (id_destination);


--
-- TOC entry 3309 (class 2606 OID 40967)
-- Name: person email_un; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.person
    ADD CONSTRAINT email_un UNIQUE (email);


--
-- TOC entry 3295 (class 2606 OID 33448)
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id_person);


--
-- TOC entry 3297 (class 2606 OID 33450)
-- Name: formation formation_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.formation
    ADD CONSTRAINT formation_pkey PRIMARY KEY (id_formation);


--
-- TOC entry 3299 (class 2606 OID 33452)
-- Name: fuel fuel_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.fuel
    ADD CONSTRAINT fuel_pkey PRIMARY KEY (id_fuel);


--
-- TOC entry 3301 (class 2606 OID 33454)
-- Name: recurring_days happen_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.recurring_days
    ADD CONSTRAINT happen_pkey PRIMARY KEY (id_ride, id_day_week);


--
-- TOC entry 3303 (class 2606 OID 33458)
-- Name: notification notification_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.notification
    ADD CONSTRAINT notification_pkey PRIMARY KEY (id_notification);


--
-- TOC entry 3305 (class 2606 OID 33460)
-- Name: one_time one_time_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.one_time
    ADD CONSTRAINT one_time_pkey PRIMARY KEY (id_ride);


--
-- TOC entry 3307 (class 2606 OID 33462)
-- Name: partner partner_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.partner
    ADD CONSTRAINT partner_pkey PRIMARY KEY (id_partner);


--
-- TOC entry 3311 (class 2606 OID 33464)
-- Name: person person_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id_person);


--
-- TOC entry 3313 (class 2606 OID 33466)
-- Name: recurring recurring_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.recurring
    ADD CONSTRAINT recurring_pkey PRIMARY KEY (id_ride);


--
-- TOC entry 3317 (class 2606 OID 33468)
-- Name: ride_passenger ride_passenger_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.ride_passenger
    ADD CONSTRAINT ride_passenger_pkey PRIMARY KEY (id_person, id_ride);


--
-- TOC entry 3315 (class 2606 OID 33470)
-- Name: ride ride_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.ride
    ADD CONSTRAINT ride_pkey PRIMARY KEY (id_ride);


--
-- TOC entry 3321 (class 2606 OID 49164)
-- Name: teacher_of teacher_of_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.teacher_of
    ADD CONSTRAINT teacher_of_pkey PRIMARY KEY (id_formation, id_teacher);


--
-- TOC entry 3319 (class 2606 OID 33472)
-- Name: trainee trainee_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.trainee
    ADD CONSTRAINT trainee_pkey PRIMARY KEY (id_person);


--
-- TOC entry 3322 (class 2606 OID 33473)
-- Name: car car_id_car_type_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.car
    ADD CONSTRAINT car_id_car_type_fkey FOREIGN KEY (id_car_type) REFERENCES covoiturafpa.car_type(id_car_type);


--
-- TOC entry 3323 (class 2606 OID 33478)
-- Name: car car_id_person_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.car
    ADD CONSTRAINT car_id_person_fkey FOREIGN KEY (id_person) REFERENCES covoiturafpa.person(id_person);


--
-- TOC entry 3324 (class 2606 OID 33483)
-- Name: car_type car_type_id_fuel_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.car_type
    ADD CONSTRAINT car_type_id_fuel_fkey FOREIGN KEY (id_fuel) REFERENCES covoiturafpa.fuel(id_fuel);


--
-- TOC entry 3325 (class 2606 OID 33493)
-- Name: day_timetable day_timetable_id_centre_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.day_timetable
    ADD CONSTRAINT day_timetable_id_centre_fkey FOREIGN KEY (id_centre) REFERENCES covoiturafpa.centre(id_centre);


--
-- TOC entry 3326 (class 2606 OID 49200)
-- Name: destination destination_id_city_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.destination
    ADD CONSTRAINT destination_id_city_fkey FOREIGN KEY (id_city) REFERENCES covoiturafpa.city(id_city) ON DELETE CASCADE;


--
-- TOC entry 3327 (class 2606 OID 33503)
-- Name: employee employee_id_centre_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.employee
    ADD CONSTRAINT employee_id_centre_fkey FOREIGN KEY (id_centre) REFERENCES covoiturafpa.centre(id_centre);


--
-- TOC entry 3328 (class 2606 OID 33508)
-- Name: employee employee_id_person_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.employee
    ADD CONSTRAINT employee_id_person_fkey FOREIGN KEY (id_person) REFERENCES covoiturafpa.person(id_person);


--
-- TOC entry 3329 (class 2606 OID 33513)
-- Name: formation formation_id_centre_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.formation
    ADD CONSTRAINT formation_id_centre_fkey FOREIGN KEY (id_centre) REFERENCES covoiturafpa.centre(id_centre);


--
-- TOC entry 3330 (class 2606 OID 33518)
-- Name: recurring_days happen_id_day_week_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.recurring_days
    ADD CONSTRAINT happen_id_day_week_fkey FOREIGN KEY (id_day_week) REFERENCES covoiturafpa.day_week(id_day_week);


--
-- TOC entry 3331 (class 2606 OID 33523)
-- Name: recurring_days happen_id_ride_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.recurring_days
    ADD CONSTRAINT happen_id_ride_fkey FOREIGN KEY (id_ride) REFERENCES covoiturafpa.recurring(id_ride);


--
-- TOC entry 3332 (class 2606 OID 33528)
-- Name: notification notification_id_person_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.notification
    ADD CONSTRAINT notification_id_person_fkey FOREIGN KEY (id_person) REFERENCES covoiturafpa.person(id_person);


--
-- TOC entry 3333 (class 2606 OID 49180)
-- Name: one_time one_time_id_ride_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.one_time
    ADD CONSTRAINT one_time_id_ride_fkey FOREIGN KEY (id_ride) REFERENCES covoiturafpa.ride(id_ride) ON DELETE CASCADE;


--
-- TOC entry 3334 (class 2606 OID 33538)
-- Name: partner partner_id_centre_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.partner
    ADD CONSTRAINT partner_id_centre_fkey FOREIGN KEY (id_centre) REFERENCES covoiturafpa.centre(id_centre);


--
-- TOC entry 3335 (class 2606 OID 49175)
-- Name: recurring recurring_id_ride_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.recurring
    ADD CONSTRAINT recurring_id_ride_fkey FOREIGN KEY (id_ride) REFERENCES covoiturafpa.ride(id_ride) ON DELETE CASCADE;


--
-- TOC entry 3336 (class 2606 OID 33548)
-- Name: ride ride_id_car_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.ride
    ADD CONSTRAINT ride_id_car_fkey FOREIGN KEY (id_car) REFERENCES covoiturafpa.car(id_car);


--
-- TOC entry 3337 (class 2606 OID 49195)
-- Name: ride ride_id_destination_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.ride
    ADD CONSTRAINT ride_id_destination_fkey FOREIGN KEY (id_destination) REFERENCES covoiturafpa.destination(id_destination) ON DELETE CASCADE;


--
-- TOC entry 3338 (class 2606 OID 33558)
-- Name: ride_passenger ride_passenger_id_person_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.ride_passenger
    ADD CONSTRAINT ride_passenger_id_person_fkey FOREIGN KEY (id_person) REFERENCES covoiturafpa.person(id_person);


--
-- TOC entry 3339 (class 2606 OID 49190)
-- Name: ride_passenger ride_passenger_id_ride_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.ride_passenger
    ADD CONSTRAINT ride_passenger_id_ride_fkey FOREIGN KEY (id_ride) REFERENCES covoiturafpa.ride(id_ride) ON DELETE CASCADE;


--
-- TOC entry 3343 (class 2606 OID 49170)
-- Name: teacher_of teacher_of_id_formation_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.teacher_of
    ADD CONSTRAINT teacher_of_id_formation_fkey FOREIGN KEY (id_formation) REFERENCES covoiturafpa.formation(id_formation);


--
-- TOC entry 3342 (class 2606 OID 49165)
-- Name: teacher_of teacher_of_id_teacher_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.teacher_of
    ADD CONSTRAINT teacher_of_id_teacher_fkey FOREIGN KEY (id_teacher) REFERENCES covoiturafpa.employee(id_person);


--
-- TOC entry 3340 (class 2606 OID 33568)
-- Name: trainee trainee_id_formation_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.trainee
    ADD CONSTRAINT trainee_id_formation_fkey FOREIGN KEY (id_formation) REFERENCES covoiturafpa.formation(id_formation);


--
-- TOC entry 3341 (class 2606 OID 33573)
-- Name: trainee trainee_id_person_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: postgres
--

ALTER TABLE ONLY covoiturafpa.trainee
    ADD CONSTRAINT trainee_id_person_fkey FOREIGN KEY (id_person) REFERENCES covoiturafpa.person(id_person);


--
-- TOC entry 3521 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA covoiturafpa; Type: ACL; Schema: -; Owner: postgres
--

GRANT USAGE ON SCHEMA covoiturafpa TO "user";


--
-- TOC entry 3522 (class 0 OID 0)
-- Dependencies: 210
-- Name: TABLE car; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.car TO "user";


--
-- TOC entry 3524 (class 0 OID 0)
-- Dependencies: 211
-- Name: SEQUENCE car_id_car_seq; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE covoiturafpa.car_id_car_seq TO "user";


--
-- TOC entry 3525 (class 0 OID 0)
-- Dependencies: 212
-- Name: TABLE car_type; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.car_type TO "user";


--
-- TOC entry 3527 (class 0 OID 0)
-- Dependencies: 213
-- Name: SEQUENCE car_type_id_car_type_seq; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE covoiturafpa.car_type_id_car_type_seq TO "user";


--
-- TOC entry 3528 (class 0 OID 0)
-- Dependencies: 214
-- Name: TABLE centre; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.centre TO "user";


--
-- TOC entry 3530 (class 0 OID 0)
-- Dependencies: 215
-- Name: SEQUENCE centre_id_centre_seq; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE covoiturafpa.centre_id_centre_seq TO "user";


--
-- TOC entry 3531 (class 0 OID 0)
-- Dependencies: 216
-- Name: TABLE city; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.city TO "user";


--
-- TOC entry 3533 (class 0 OID 0)
-- Dependencies: 217
-- Name: SEQUENCE city_id_city_seq; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE covoiturafpa.city_id_city_seq TO "user";


--
-- TOC entry 3534 (class 0 OID 0)
-- Dependencies: 218
-- Name: TABLE day_timetable; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.day_timetable TO "user";


--
-- TOC entry 3536 (class 0 OID 0)
-- Dependencies: 219
-- Name: SEQUENCE day_timetable_id_day_timetable_seq; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE covoiturafpa.day_timetable_id_day_timetable_seq TO "user";


--
-- TOC entry 3537 (class 0 OID 0)
-- Dependencies: 220
-- Name: TABLE day_week; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.day_week TO "user";


--
-- TOC entry 3539 (class 0 OID 0)
-- Dependencies: 221
-- Name: SEQUENCE day_week_id_day_week_seq; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE covoiturafpa.day_week_id_day_week_seq TO "user";


--
-- TOC entry 3540 (class 0 OID 0)
-- Dependencies: 222
-- Name: TABLE destination; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.destination TO "user";


--
-- TOC entry 3542 (class 0 OID 0)
-- Dependencies: 223
-- Name: SEQUENCE destination_id_destination_seq; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE covoiturafpa.destination_id_destination_seq TO "user";


--
-- TOC entry 3543 (class 0 OID 0)
-- Dependencies: 224
-- Name: TABLE employee; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.employee TO "user";


--
-- TOC entry 3544 (class 0 OID 0)
-- Dependencies: 225
-- Name: TABLE formation; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.formation TO "user";


--
-- TOC entry 3546 (class 0 OID 0)
-- Dependencies: 226
-- Name: SEQUENCE formation_id_formation_seq; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE covoiturafpa.formation_id_formation_seq TO "user";


--
-- TOC entry 3547 (class 0 OID 0)
-- Dependencies: 227
-- Name: TABLE fuel; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.fuel TO "user";


--
-- TOC entry 3549 (class 0 OID 0)
-- Dependencies: 228
-- Name: SEQUENCE fuel_id_fuel_seq; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE covoiturafpa.fuel_id_fuel_seq TO "user";


--
-- TOC entry 3550 (class 0 OID 0)
-- Dependencies: 230
-- Name: TABLE notification; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.notification TO "user";


--
-- TOC entry 3552 (class 0 OID 0)
-- Dependencies: 231
-- Name: SEQUENCE notification_id_notification_seq; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE covoiturafpa.notification_id_notification_seq TO "user";


--
-- TOC entry 3553 (class 0 OID 0)
-- Dependencies: 232
-- Name: TABLE one_time; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.one_time TO "user";


--
-- TOC entry 3554 (class 0 OID 0)
-- Dependencies: 233
-- Name: TABLE partner; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.partner TO "user";


--
-- TOC entry 3556 (class 0 OID 0)
-- Dependencies: 234
-- Name: SEQUENCE partner_id_partner_seq; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE covoiturafpa.partner_id_partner_seq TO "user";


--
-- TOC entry 3557 (class 0 OID 0)
-- Dependencies: 235
-- Name: TABLE person; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.person TO "user";


--
-- TOC entry 3559 (class 0 OID 0)
-- Dependencies: 236
-- Name: SEQUENCE person_id_person_seq; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE covoiturafpa.person_id_person_seq TO "user";


--
-- TOC entry 3560 (class 0 OID 0)
-- Dependencies: 237
-- Name: TABLE recurring; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.recurring TO "user";


--
-- TOC entry 3561 (class 0 OID 0)
-- Dependencies: 229
-- Name: TABLE recurring_days; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.recurring_days TO "user";


--
-- TOC entry 3562 (class 0 OID 0)
-- Dependencies: 238
-- Name: TABLE ride; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.ride TO "user";


--
-- TOC entry 3564 (class 0 OID 0)
-- Dependencies: 239
-- Name: SEQUENCE ride_id_ride_seq; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT SELECT,USAGE ON SEQUENCE covoiturafpa.ride_id_ride_seq TO "user";


--
-- TOC entry 3565 (class 0 OID 0)
-- Dependencies: 240
-- Name: TABLE ride_passenger; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.ride_passenger TO "user";


--
-- TOC entry 3566 (class 0 OID 0)
-- Dependencies: 242
-- Name: TABLE teacher_of; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.teacher_of TO "user";


--
-- TOC entry 3567 (class 0 OID 0)
-- Dependencies: 241
-- Name: TABLE trainee; Type: ACL; Schema: covoiturafpa; Owner: postgres
--

GRANT ALL ON TABLE covoiturafpa.trainee TO "user";


-- Completed on 2022-10-12 11:03:14

--
-- PostgreSQL database dump complete
--

