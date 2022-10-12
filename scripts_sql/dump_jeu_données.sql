CREATE SCHEMA "covoiturafpa";


--
-- TOC entry 916 (class 1247 OID 17538)
-- Name: car_type_name; Type: TYPE; Schema: covoiturafpa; Owner: -
--

CREATE TYPE "covoiturafpa".car_type_name AS ENUM (
    'COMPACT',
    'BERLINE',
    'SUV',
    'MONOSPACE',
    'UTILITAIRE'
);


--
-- TOC entry 925 (class 1247 OID 17566)
-- Name: day_name; Type: TYPE; Schema: covoiturafpa; Owner: -
--

CREATE TYPE "covoiturafpa".day_name AS ENUM (
    'MONDAY',
    'TUESDAY',
    'WEDNESDAY',
    'THURSDAY',
    'FRIDAY',
    'SATURDAY',
    'SUNDAY'
);


--
-- TOC entry 919 (class 1247 OID 17550)
-- Name: notification_type; Type: TYPE; Schema: covoiturafpa; Owner: -
--

CREATE TYPE "covoiturafpa".notification_type AS ENUM (
    'NEW_RESERVATION',
    'ACCEPTED_RESERVATION',
    'REJECTED_RESERVATION',
    'NEW_TRAINEE',
    'NEW_EMPLOYEE'
);


--
-- TOC entry 922 (class 1247 OID 17558)
-- Name: status_type; Type: TYPE; Schema: covoiturafpa; Owner: -
--

CREATE TYPE "covoiturafpa".status_type AS ENUM (
    'PENDING',
    'ACCEPTED',
    'FINISHED'
);


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 243 (class 1259 OID 17641)
-- Name: car; Type: TABLE; Schema: covoiturafpa; Owner: -
--

CREATE TABLE "covoiturafpa".car (
    id_car integer NOT NULL,
    model character varying(50),
    seats smallint,
    avg_fuel_consumption numeric(4,1),
    id_car_type integer NOT NULL,
    id_person integer NOT NULL
);


--
-- TOC entry 242 (class 1259 OID 17640)
-- Name: car_id_car_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: -
--

CREATE SEQUENCE "covoiturafpa".car_id_car_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3582 (class 0 OID 0)
-- Dependencies: 242
-- Name: car_id_car_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: -
--

ALTER SEQUENCE "covoiturafpa".car_id_car_seq OWNED BY "covoiturafpa".car.id_car;


--
-- TOC entry 241 (class 1259 OID 17629)
-- Name: car_type; Type: TABLE; Schema: covoiturafpa; Owner: -
--

CREATE TABLE "covoiturafpa".car_type (
    id_car_type integer NOT NULL,
    name "covoiturafpa".car_type_name,
    avg_fuel_consumption numeric(4,1),
    id_fuel integer NOT NULL
);


--
-- TOC entry 240 (class 1259 OID 17628)
-- Name: car_type_id_car_type_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: -
--

CREATE SEQUENCE "covoiturafpa".car_type_id_car_type_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3583 (class 0 OID 0)
-- Dependencies: 240
-- Name: car_type_id_car_type_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: -
--

ALTER SEQUENCE "covoiturafpa".car_type_id_car_type_seq OWNED BY "covoiturafpa".car_type.id_car_type;


--
-- TOC entry 245 (class 1259 OID 17658)
-- Name: centre; Type: TABLE; Schema: covoiturafpa; Owner: -
--

CREATE TABLE "covoiturafpa".centre (
    id_centre integer NOT NULL,
    name character varying(50),
    address character varying(255),
    latitude double precision,
    longitude double precision,
    phone_number character varying(20),
    contact_by_sms boolean
);


--
-- TOC entry 244 (class 1259 OID 17657)
-- Name: centre_id_centre_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: -
--

CREATE SEQUENCE "covoiturafpa".centre_id_centre_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3584 (class 0 OID 0)
-- Dependencies: 244
-- Name: centre_id_centre_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: -
--

ALTER SEQUENCE "covoiturafpa".centre_id_centre_seq OWNED BY "covoiturafpa".centre.id_centre;


--
-- TOC entry 237 (class 1259 OID 17615)
-- Name: city; Type: TABLE; Schema: covoiturafpa; Owner: -
--

CREATE TABLE "covoiturafpa".city (
    id_city integer NOT NULL,
    name character varying(255)
);


--
-- TOC entry 236 (class 1259 OID 17614)
-- Name: city_id_city_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: -
--

CREATE SEQUENCE "covoiturafpa".city_id_city_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3585 (class 0 OID 0)
-- Dependencies: 236
-- Name: city_id_city_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: -
--

ALTER SEQUENCE "covoiturafpa".city_id_city_seq OWNED BY "covoiturafpa".city.id_city;


--
-- TOC entry 249 (class 1259 OID 17684)
-- Name: day_timetable; Type: TABLE; Schema: covoiturafpa; Owner: -
--

CREATE TABLE "covoiturafpa".day_timetable (
    id_day_timetable integer NOT NULL,
    day "covoiturafpa".day_name,
    start_morning time without time zone,
    end_morning time without time zone,
    start_afternoon time without time zone,
    end_afternoon time without time zone,
    id_centre integer NOT NULL
);


--
-- TOC entry 248 (class 1259 OID 17683)
-- Name: day_timetable_id_day_timetable_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: -
--

CREATE SEQUENCE "covoiturafpa".day_timetable_id_day_timetable_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3586 (class 0 OID 0)
-- Dependencies: 248
-- Name: day_timetable_id_day_timetable_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: -
--

ALTER SEQUENCE "covoiturafpa".day_timetable_id_day_timetable_seq OWNED BY "covoiturafpa".day_timetable.id_day_timetable;


--
-- TOC entry 239 (class 1259 OID 17622)
-- Name: day_week; Type: TABLE; Schema: covoiturafpa; Owner: -
--

CREATE TABLE "covoiturafpa".day_week (
    id_day_week integer NOT NULL,
    name "covoiturafpa".day_name
);


--
-- TOC entry 238 (class 1259 OID 17621)
-- Name: day_week_id_day_week_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: -
--

CREATE SEQUENCE "covoiturafpa".day_week_id_day_week_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3587 (class 0 OID 0)
-- Dependencies: 238
-- Name: day_week_id_day_week_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: -
--

ALTER SEQUENCE "covoiturafpa".day_week_id_day_week_seq OWNED BY "covoiturafpa".day_week.id_day_week;


--
-- TOC entry 253 (class 1259 OID 17708)
-- Name: destination; Type: TABLE; Schema: covoiturafpa; Owner: -
--

CREATE TABLE "covoiturafpa".destination (
    id_destination integer NOT NULL,
    latitude double precision,
    longitude double precision,
    is_from_afpa boolean,
    id_city integer
);


--
-- TOC entry 252 (class 1259 OID 17707)
-- Name: destination_id_destination_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: -
--

CREATE SEQUENCE "covoiturafpa".destination_id_destination_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3588 (class 0 OID 0)
-- Dependencies: 252
-- Name: destination_id_destination_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: -
--

ALTER SEQUENCE "covoiturafpa".destination_id_destination_seq OWNED BY "covoiturafpa".destination.id_destination;


--
-- TOC entry 255 (class 1259 OID 17734)
-- Name: employee; Type: TABLE; Schema: covoiturafpa; Owner: -
--

CREATE TABLE "covoiturafpa".employee (
    id_person integer NOT NULL,
    role character varying(50),
    is_admin boolean,
    is_teacher boolean,
    id_centre integer NOT NULL
);


--
-- TOC entry 247 (class 1259 OID 17672)
-- Name: formation; Type: TABLE; Schema: covoiturafpa; Owner: -
--

CREATE TABLE "covoiturafpa".formation (
    id_formation integer NOT NULL,
    name character varying(50),
    id_centre integer NOT NULL
);


--
-- TOC entry 246 (class 1259 OID 17671)
-- Name: formation_id_formation_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: -
--

CREATE SEQUENCE "covoiturafpa".formation_id_formation_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3589 (class 0 OID 0)
-- Dependencies: 246
-- Name: formation_id_formation_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: -
--

ALTER SEQUENCE "covoiturafpa".formation_id_formation_seq OWNED BY "covoiturafpa".formation.id_formation;


--
-- TOC entry 233 (class 1259 OID 17596)
-- Name: fuel; Type: TABLE; Schema: covoiturafpa; Owner: -
--

CREATE TABLE "covoiturafpa".fuel (
    id_fuel integer NOT NULL,
    name character varying(20),
    price_by_unit numeric(5,2)
);


--
-- TOC entry 232 (class 1259 OID 17595)
-- Name: fuel_id_fuel_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: -
--

CREATE SEQUENCE "covoiturafpa".fuel_id_fuel_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3590 (class 0 OID 0)
-- Dependencies: 232
-- Name: fuel_id_fuel_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: -
--

ALTER SEQUENCE "covoiturafpa".fuel_id_fuel_seq OWNED BY "covoiturafpa".fuel.id_fuel;


--
-- TOC entry 261 (class 1259 OID 17806)
-- Name: recurring_days; Type: TABLE; Schema: covoiturafpa; Owner: -
--

CREATE TABLE "covoiturafpa".recurring_days (
    id_ride integer NOT NULL,
    id_day_week integer NOT NULL
);



--
-- TOC entry 235 (class 1259 OID 17603)
-- Name: notification; Type: TABLE; Schema: covoiturafpa; Owner: -
--

CREATE TABLE "covoiturafpa".notification (
    id_notification integer NOT NULL,
    type "covoiturafpa".notification_type,
    created_time timestamp without time zone,
    is_unread boolean,
    id_person integer NOT NULL,
    content varchar
);


--
-- TOC entry 234 (class 1259 OID 17602)
-- Name: notification_id_notification_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: -
--

CREATE SEQUENCE "covoiturafpa".notification_id_notification_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3592 (class 0 OID 0)
-- Dependencies: 234
-- Name: notification_id_notification_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: -
--

ALTER SEQUENCE "covoiturafpa".notification_id_notification_seq OWNED BY "covoiturafpa".notification.id_notification;


--
-- TOC entry 259 (class 1259 OID 17781)
-- Name: one_time; Type: TABLE; Schema: covoiturafpa; Owner: -
--

CREATE TABLE "covoiturafpa".one_time (
    id_ride integer NOT NULL,
    departure_day date
);


--
-- TOC entry 251 (class 1259 OID 17696)
-- Name: partner; Type: TABLE; Schema: covoiturafpa; Owner: -
--

CREATE TABLE "covoiturafpa".partner (
    id_partner integer NOT NULL,
    name character varying(50),
    logo_picture_path character varying(255),
    id_centre integer NOT NULL
);


--
-- TOC entry 250 (class 1259 OID 17695)
-- Name: partner_id_partner_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: -
--

CREATE SEQUENCE "covoiturafpa".partner_id_partner_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3593 (class 0 OID 0)
-- Dependencies: 250
-- Name: partner_id_partner_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: -
--

ALTER SEQUENCE "covoiturafpa".partner_id_partner_seq OWNED BY "covoiturafpa".partner.id_partner;


--
-- TOC entry 229 (class 1259 OID 17582)
-- Name: person; Type: TABLE; Schema: covoiturafpa; Owner: -
--

CREATE TABLE "covoiturafpa".person (
    id_person integer NOT NULL,
    email character varying(50) UNIQUE,
    password character varying(255),
    surname character varying(50),
    first_name character varying(20),
    phone_number character varying(20),
    is_activated boolean,
    contact_by_sms boolean,
    contact_by_mail boolean,
    start_activity date,
    end_activity date,
    last_login timestamp without time zone,
    photo_path character varying(255),
    person_type character varying NOT NULL
);


--
-- TOC entry 228 (class 1259 OID 17581)
-- Name: person_id_person_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: -
--

CREATE SEQUENCE "covoiturafpa".person_id_person_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3594 (class 0 OID 0)
-- Dependencies: 228
-- Name: person_id_person_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: -
--

ALTER SEQUENCE "covoiturafpa".person_id_person_seq OWNED BY "covoiturafpa".person.id_person;


--
-- TOC entry 258 (class 1259 OID 17771)
-- Name: recurring; Type: TABLE; Schema: covoiturafpa; Owner: -
--

CREATE TABLE "covoiturafpa".recurring (
    id_ride integer NOT NULL,
    beginning date,
    ending date
);


--
-- TOC entry 257 (class 1259 OID 17750)
-- Name: ride; Type: TABLE; Schema: covoiturafpa; Owner: -
--

CREATE TABLE "covoiturafpa".ride (
    id_ride integer NOT NULL,
    is_active boolean,
    departure_time time without time zone,
    comment character varying(255),
    id_destination integer NOT NULL,
    id_car integer NOT NULL,
    price numeric(5,2),
    ride_type character varying NOT NULL
);


--
-- TOC entry 256 (class 1259 OID 17749)
-- Name: ride_id_ride_seq; Type: SEQUENCE; Schema: covoiturafpa; Owner: -
--

CREATE SEQUENCE "covoiturafpa".ride_id_ride_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3595 (class 0 OID 0)
-- Dependencies: 256
-- Name: ride_id_ride_seq; Type: SEQUENCE OWNED BY; Schema: covoiturafpa; Owner: -
--

ALTER SEQUENCE "covoiturafpa".ride_id_ride_seq OWNED BY "covoiturafpa".ride.id_ride;


--
-- TOC entry 260 (class 1259 OID 17791)
-- Name: ride_passenger; Type: TABLE; Schema: covoiturafpa; Owner: -
--

CREATE TABLE "covoiturafpa".ride_passenger (
    id_person integer NOT NULL,
    id_ride integer NOT NULL,
    status "covoiturafpa".status_type,
    last_update timestamp without time zone,
    is_driver boolean
);


--
-- TOC entry 254 (class 1259 OID 17719)
-- Name: trainee; Type: TABLE; Schema: covoiturafpa; Owner: -
--

CREATE TABLE "covoiturafpa".trainee (
    id_person integer NOT NULL,
    id_formation integer
);


--
-- TOC entry 3333 (class 2604 OID 17644)
-- Name: car id_car; Type: DEFAULT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".car ALTER COLUMN id_car SET DEFAULT nextval('"covoiturafpa".car_id_car_seq'::regclass);


--
-- TOC entry 3332 (class 2604 OID 17632)
-- Name: car_type id_car_type; Type: DEFAULT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".car_type ALTER COLUMN id_car_type SET DEFAULT nextval('"covoiturafpa".car_type_id_car_type_seq'::regclass);


--
-- TOC entry 3334 (class 2604 OID 17661)
-- Name: centre id_centre; Type: DEFAULT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".centre ALTER COLUMN id_centre SET DEFAULT nextval('"covoiturafpa".centre_id_centre_seq'::regclass);


--
-- TOC entry 3330 (class 2604 OID 17618)
-- Name: city id_city; Type: DEFAULT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".city ALTER COLUMN id_city SET DEFAULT nextval('"covoiturafpa".city_id_city_seq'::regclass);


--
-- TOC entry 3336 (class 2604 OID 17687)
-- Name: day_timetable id_day_timetable; Type: DEFAULT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".day_timetable ALTER COLUMN id_day_timetable SET DEFAULT nextval('"covoiturafpa".day_timetable_id_day_timetable_seq'::regclass);


--
-- TOC entry 3331 (class 2604 OID 17625)
-- Name: day_week id_day_week; Type: DEFAULT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".day_week ALTER COLUMN id_day_week SET DEFAULT nextval('"covoiturafpa".day_week_id_day_week_seq'::regclass);


--
-- TOC entry 3338 (class 2604 OID 17711)
-- Name: destination id_destination; Type: DEFAULT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".destination ALTER COLUMN id_destination SET DEFAULT nextval('"covoiturafpa".destination_id_destination_seq'::regclass);


--
-- TOC entry 3335 (class 2604 OID 17675)
-- Name: formation id_formation; Type: DEFAULT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".formation ALTER COLUMN id_formation SET DEFAULT nextval('"covoiturafpa".formation_id_formation_seq'::regclass);


--
-- TOC entry 3328 (class 2604 OID 17599)
-- Name: fuel id_fuel; Type: DEFAULT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".fuel ALTER COLUMN id_fuel SET DEFAULT nextval('"covoiturafpa".fuel_id_fuel_seq'::regclass);



--
-- TOC entry 3329 (class 2604 OID 17606)
-- Name: notification id_notification; Type: DEFAULT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".notification ALTER COLUMN id_notification SET DEFAULT nextval('"covoiturafpa".notification_id_notification_seq'::regclass);


--
-- TOC entry 3337 (class 2604 OID 17699)
-- Name: partner id_partner; Type: DEFAULT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".partner ALTER COLUMN id_partner SET DEFAULT nextval('"covoiturafpa".partner_id_partner_seq'::regclass);


--
-- TOC entry 3326 (class 2604 OID 17585)
-- Name: person id_person; Type: DEFAULT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".person ALTER COLUMN id_person SET DEFAULT nextval('"covoiturafpa".person_id_person_seq'::regclass);


--
-- TOC entry 3339 (class 2604 OID 17753)
-- Name: ride id_ride; Type: DEFAULT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".ride ALTER COLUMN id_ride SET DEFAULT nextval('"covoiturafpa".ride_id_ride_seq'::regclass);


--
-- TOC entry 3557 (class 0 OID 17641)
-- Dependencies: 243
-- Data for Name: car; Type: TABLE DATA; Schema: covoiturafpa; Owner: -
--

INSERT INTO "covoiturafpa".car VALUES (4, 'Porsche Taurus', 6, 4.2, 4, 43);
INSERT INTO "covoiturafpa".car VALUES (2, 'BMW ATS', 5, 1.8, 3, 43);
INSERT INTO "covoiturafpa".car VALUES (3, 'Chrysler Land Cruiser', 6, 6.3, 1, 45);


--
-- TOC entry 3555 (class 0 OID 17629)
-- Dependencies: 241
-- Data for Name: car_type; Type: TABLE DATA; Schema: covoiturafpa; Owner: -
--

INSERT INTO "covoiturafpa".car_type VALUES (1, 'COMPACT', 7.3, 1);
INSERT INTO "covoiturafpa".car_type VALUES (2, 'COMPACT', 7.3, 2);
INSERT INTO "covoiturafpa".car_type VALUES (3, 'COMPACT', 5.2, 3);
INSERT INTO "covoiturafpa".car_type VALUES (4, 'COMPACT', 7.8, 4);
INSERT INTO "covoiturafpa".car_type VALUES (5, 'COMPACT', 9.5, 5);
INSERT INTO "covoiturafpa".car_type VALUES (6, 'BERLINE', 7.5, 1);
INSERT INTO "covoiturafpa".car_type VALUES (7, 'BERLINE', 7.5, 2);
INSERT INTO "covoiturafpa".car_type VALUES (8, 'BERLINE', 6.8, 3);
INSERT INTO "covoiturafpa".car_type VALUES (9, 'BERLINE', 15.6, 4);
INSERT INTO "covoiturafpa".car_type VALUES (10, 'BERLINE', 12.2, 5);
INSERT INTO "covoiturafpa".car_type VALUES (11, 'SUV', 8.0, 1);
INSERT INTO "covoiturafpa".car_type VALUES (12, 'SUV', 8.0, 2);
INSERT INTO "covoiturafpa".car_type VALUES (13, 'SUV', 7.2, 3);
INSERT INTO "covoiturafpa".car_type VALUES (14, 'SUV', 16.0, 4);
INSERT INTO "covoiturafpa".car_type VALUES (15, 'SUV', 16.0, 5);
INSERT INTO "covoiturafpa".car_type VALUES (16, 'MONOSPACE', 7.0, 1);
INSERT INTO "covoiturafpa".car_type VALUES (17, 'MONOSPACE', 7.0, 2);
INSERT INTO "covoiturafpa".car_type VALUES (18, 'MONOSPACE', 6.2, 3);
INSERT INTO "covoiturafpa".car_type VALUES (19, 'MONOSPACE', 15.6, 4);
INSERT INTO "covoiturafpa".car_type VALUES (20, 'MONOSPACE', 10.5, 5);
INSERT INTO "covoiturafpa".car_type VALUES (21, 'UTILITAIRE', 14.3, 3);
INSERT INTO "covoiturafpa".car_type VALUES (22, 'UTILITAIRE', 14.3, 2);
INSERT INTO "covoiturafpa".car_type VALUES (23, 'UTILITAIRE', 11.6, 3);
INSERT INTO "covoiturafpa".car_type VALUES (24, 'UTILITAIRE', 25.0, 4);
INSERT INTO "covoiturafpa".car_type VALUES (25, 'UTILITAIRE', 18.6, 5);


--
-- TOC entry 3559 (class 0 OID 17658)
-- Dependencies: 245
-- Data for Name: centre; Type: TABLE DATA; Schema: covoiturafpa; Owner: -
--

INSERT INTO "covoiturafpa".centre VALUES (28, 'Velit explicabo minima rem', '56606 bld Verdun Charlotte', 51.4139, -2.5798, '+33472864830', 13);


--
-- TOC entry 3551 (class 0 OID 17615)
-- Dependencies: 237
-- Data for Name: city; Type: TABLE DATA; Schema: covoiturafpa; Owner: -
--

INSERT INTO "covoiturafpa".city VALUES (1, 'La Rochelle');
INSERT INTO "covoiturafpa".city VALUES (2, 'Chatellerault');


--
-- TOC entry 3563 (class 0 OID 17684)
-- Dependencies: 249
-- Data for Name: day_timetable; Type: TABLE DATA; Schema: covoiturafpa; Owner: -
--

INSERT INTO "covoiturafpa".day_timetable VALUES (1, 'MONDAY', '00:00:00', '00:00:00', '00:00:00', '00:00:00', 28);


--
-- TOC entry 3553 (class 0 OID 17622)
-- Dependencies: 239
-- Data for Name: day_week; Type: TABLE DATA; Schema: covoiturafpa; Owner: -
--

INSERT INTO "covoiturafpa".day_week VALUES (1, 'MONDAY');
INSERT INTO "covoiturafpa".day_week VALUES (2, 'TUESDAY');
INSERT INTO "covoiturafpa".day_week VALUES (3, 'WEDNESDAY');
INSERT INTO "covoiturafpa".day_week VALUES (4, 'THURSDAY');
INSERT INTO "covoiturafpa".day_week VALUES (5, 'FRIDAY');
INSERT INTO "covoiturafpa".day_week VALUES (6, 'SATURDAY');
INSERT INTO "covoiturafpa".day_week VALUES (7, 'SUNDAY');


--
-- TOC entry 3567 (class 0 OID 17708)
-- Dependencies: 253
-- Data for Name: destination; Type: TABLE DATA; Schema: covoiturafpa; Owner: -
--

INSERT INTO "covoiturafpa".destination VALUES (1, 59.9211, 81.1344, false, 1);
INSERT INTO "covoiturafpa".destination VALUES (2, 59.9211, 81.1344, true, 2);


--
-- TOC entry 3569 (class 0 OID 17734)
-- Dependencies: 255
-- Data for Name: employee; Type: TABLE DATA; Schema: covoiturafpa; Owner: -
--

INSERT INTO "covoiturafpa".employee VALUES (57, 'administration', false, true,  28);
INSERT INTO "covoiturafpa".employee VALUES (51, 'formateur', false, true, 28);
INSERT INTO "covoiturafpa".employee VALUES (97, 'restauration', true, false, 28);


--
-- TOC entry 3561 (class 0 OID 17672)
-- Dependencies: 247
-- Data for Name: formation; Type: TABLE DATA; Schema: covoiturafpa; Owner: -
--

INSERT INTO "covoiturafpa".formation VALUES (1, 'Necessitserunt quo cumque', 28);
INSERT INTO "covoiturafpa".formation VALUES (4, 'Ullam doloribus praesentium quisqu', 28);
INSERT INTO "covoiturafpa".formation VALUES (7, 'Molestias iusto veniam ut cum.', 28);
INSERT INTO "covoiturafpa".formation VALUES (2, 'Et quia eerum quo alias nisi magni.', 28);
INSERT INTO "covoiturafpa".formation VALUES (5, 'Voluptatrecusandae nisi.', 28);


--
-- TOC entry 3547 (class 0 OID 17596)
-- Dependencies: 233
-- Data for Name: fuel; Type: TABLE DATA; Schema: covoiturafpa; Owner: -
--

INSERT INTO "covoiturafpa".fuel VALUES (1, 'ESSENCE', 1.82);
INSERT INTO "covoiturafpa".fuel VALUES (2, 'ESSENCE SUPERTHANOL', 0.82);
INSERT INTO "covoiturafpa".fuel VALUES (3, 'GAZOLE', 1.82);
INSERT INTO "covoiturafpa".fuel VALUES (4, ' ELECTRIQUE', 0.17);
INSERT INTO "covoiturafpa".fuel VALUES (5, 'GPL', 0.87);


--
-- TOC entry 3575 (class 0 OID 17806)
-- Dependencies: 261
-- Data for Name: recurring_days; Type: TABLE DATA; Schema: covoiturafpa; Owner: -
--

INSERT INTO "covoiturafpa".recurring_days VALUES (3, 1);
INSERT INTO "covoiturafpa".recurring_days VALUES (3, 2);
INSERT INTO "covoiturafpa".recurring_days VALUES (3, 3);
INSERT INTO "covoiturafpa".recurring_days VALUES (3, 4);



--
-- TOC entry 3549 (class 0 OID 17603)
-- Dependencies: 235
-- Data for Name: notification; Type: TABLE DATA; Schema: covoiturafpa; Owner: -
--

INSERT INTO "covoiturafpa".notification VALUES (82, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', false, 97);
INSERT INTO "covoiturafpa".notification VALUES (78, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', false, 97);
INSERT INTO "covoiturafpa".notification VALUES (32, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', false, 97);
INSERT INTO "covoiturafpa".notification VALUES (93, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', false, 97);
INSERT INTO "covoiturafpa".notification VALUES (73, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', true, 43);
INSERT INTO "covoiturafpa".notification VALUES (8, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', true, 45);
INSERT INTO "covoiturafpa".notification VALUES (30, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', false, 43);
INSERT INTO "covoiturafpa".notification VALUES (76, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', true, 45);
INSERT INTO "covoiturafpa".notification VALUES (5, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', false, 57);
INSERT INTO "covoiturafpa".notification VALUES (25, 'ACCEPTED_RESERVATION', '2022-01-01 00:00:00', true, 51);


--
-- TOC entry 3573 (class 0 OID 17781)
-- Dependencies: 259
-- Data for Name: one_time; Type: TABLE DATA; Schema: covoiturafpa; Owner: -
--

INSERT INTO "covoiturafpa".one_time VALUES (6, '2022-01-01');


--
-- TOC entry 3565 (class 0 OID 17696)
-- Dependencies: 251
-- Data for Name: partner; Type: TABLE DATA; Schema: covoiturafpa; Owner: -
--

INSERT INTO "covoiturafpa".partner VALUES (5, 'Wolff', 'https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/634.jpg', 28);
INSERT INTO "covoiturafpa".partner VALUES (4, 'Botsford', 'https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/119.jpg', 28);


--
-- TOC entry 3543 (class 0 OID 17582)
-- Dependencies: 229
-- Data for Name: person; Type: TABLE DATA; Schema: covoiturafpa; Owner: -
--

INSERT INTO "covoiturafpa".person VALUES (43, 'MadgeSmith@mail.fr', 'b4lnkw50s5kvt9cx01m7', 'Bergnaum', 'Karson', '+33672728649', false, true, true, '2022-01-01', '2022-01-01', '2022-01-01 00:00:00', 'https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/1223.jpg', 'T');
INSERT INTO "covoiturafpa".person VALUES (45, 'KrystelRath@mail.fr', 'v60zs9egqj9n65aec37j', 'Howell', 'Godfrey', '+33430830984', false, true, true, '2022-01-01', '2022-01-01', '2022-01-01 00:00:00', 'https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/390.jpg', 'T');
INSERT INTO "covoiturafpa".person VALUES (97, 'OdaBoyle@mail.fr', 'bnbhmg8jufhon5zc6z8j', 'Leffler', 'Billie', '+33458210191', false, false, true, '2022-01-01', '2022-01-01', '2022-01-01 00:00:00', 'https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/658.jpg', 'E');
INSERT INTO "covoiturafpa".person VALUES (51, 'MohammadGreenfelder@mail.fr', '6sjngbrc3t1tigidn826', 'Bailey', 'Wava', '+33983567107', true, false, false, '2022-01-01', '2022-01-01', '2022-01-01 00:00:00', 'https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/420.jpg', 'E');
INSERT INTO "covoiturafpa".person VALUES (57, 'CristopherWolf@mail.fr', 'movlejxx6c3fpybxlw6o', 'Weber', 'Casper', '+33511397860', false, false, false, '2022-01-01', '2022-01-01', '2022-01-01 00:00:00', 'https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/684.jpg', 'E');


--
-- TOC entry 3572 (class 0 OID 17771)
-- Dependencies: 258
-- Data for Name: recurring; Type: TABLE DATA; Schema: covoiturafpa; Owner: -
--

INSERT INTO "covoiturafpa".recurring VALUES (3, '2022-01-01', '2022-03-01');


--
-- TOC entry 3571 (class 0 OID 17750)
-- Dependencies: 257
-- Data for Name: ride; Type: TABLE DATA; Schema: covoiturafpa; Owner: -
--

INSERT INTO "covoiturafpa".ride VALUES (6, true, '00:00:00', 'Es. Re ullam accusantium neque odio repudiandae natus. Inventore numquam accusantium sed sint.', 1, 4, 10.00, 'O');
INSERT INTO "covoiturafpa".ride VALUES (3, true, '00:00:00', 'Et doloribus velit rem. Quidem dolore consequatur voluptate excepturi aut accusamus a.', 2, 2, 15.00, 'R');


--
-- TOC entry 3574 (class 0 OID 17791)
-- Dependencies: 260
-- Data for Name: ride_passenger; Type: TABLE DATA; Schema: covoiturafpa; Owner: -
--

INSERT INTO "covoiturafpa".ride_passenger VALUES (51, 3, 'PENDING', '2022-03-01 00:00:00', false);
INSERT INTO "covoiturafpa".ride_passenger VALUES (57, 3, 'PENDING', '2022-03-01 00:00:00', false);
INSERT INTO "covoiturafpa".ride_passenger VALUES (51, 6, 'PENDING', '2022-03-01 00:00:00', false);
INSERT INTO "covoiturafpa".ride_passenger VALUES (43, 3, 'ACCEPTED', '2022-03-01 00:00:00', true);
INSERT INTO "covoiturafpa".ride_passenger VALUES (43, 6, 'ACCEPTED', '2022-03-01 00:00:00', true);


--
-- TOC entry 3568 (class 0 OID 17719)
-- Dependencies: 254
-- Data for Name: trainee; Type: TABLE DATA; Schema: covoiturafpa; Owner: -
--

INSERT INTO "covoiturafpa".trainee VALUES (43, 1);
INSERT INTO "covoiturafpa".trainee VALUES (45, 4);


--
-- TOC entry 3596 (class 0 OID 0)
-- Dependencies: 242
-- Name: car_id_car_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: -
--

SELECT pg_catalog.setval('"covoiturafpa".car_id_car_seq', 1, false);


--
-- TOC entry 3597 (class 0 OID 0)
-- Dependencies: 240
-- Name: car_type_id_car_type_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: -
--

SELECT pg_catalog.setval('"covoiturafpa".car_type_id_car_type_seq', 1, true);


--
-- TOC entry 3598 (class 0 OID 0)
-- Dependencies: 244
-- Name: centre_id_centre_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: -
--

SELECT pg_catalog.setval('"covoiturafpa".centre_id_centre_seq', 1, false);


--
-- TOC entry 3599 (class 0 OID 0)
-- Dependencies: 236
-- Name: city_id_city_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: -
--

SELECT pg_catalog.setval('"covoiturafpa".city_id_city_seq', 1, false);


--
-- TOC entry 3600 (class 0 OID 0)
-- Dependencies: 248
-- Name: day_timetable_id_day_timetable_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: -
--

SELECT pg_catalog.setval('"covoiturafpa".day_timetable_id_day_timetable_seq', 1, false);


--
-- TOC entry 3601 (class 0 OID 0)
-- Dependencies: 238
-- Name: day_week_id_day_week_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: -
--

SELECT pg_catalog.setval('"covoiturafpa".day_week_id_day_week_seq', 1, false);


--
-- TOC entry 3602 (class 0 OID 0)
-- Dependencies: 252
-- Name: destination_id_destination_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: -
--

SELECT pg_catalog.setval('"covoiturafpa".destination_id_destination_seq', 1, false);


--
-- TOC entry 3603 (class 0 OID 0)
-- Dependencies: 246
-- Name: formation_id_formation_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: -
--

SELECT pg_catalog.setval('"covoiturafpa".formation_id_formation_seq', 1, false);


--
-- TOC entry 3604 (class 0 OID 0)
-- Dependencies: 232
-- Name: fuel_id_fuel_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: -
--

SELECT pg_catalog.setval('"covoiturafpa".fuel_id_fuel_seq', 1, false);



--
-- TOC entry 3606 (class 0 OID 0)
-- Dependencies: 234
-- Name: notification_id_notification_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: -
--

SELECT pg_catalog.setval('"covoiturafpa".notification_id_notification_seq', 1, false);


--
-- TOC entry 3607 (class 0 OID 0)
-- Dependencies: 250
-- Name: partner_id_partner_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: -
--

SELECT pg_catalog.setval('"covoiturafpa".partner_id_partner_seq', 1, false);


--
-- TOC entry 3608 (class 0 OID 0)
-- Dependencies: 228
-- Name: person_id_person_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: -
--

SELECT pg_catalog.setval('"covoiturafpa".person_id_person_seq', 1, false);


--
-- TOC entry 3609 (class 0 OID 0)
-- Dependencies: 256
-- Name: ride_id_ride_seq; Type: SEQUENCE SET; Schema: covoiturafpa; Owner: -
--

SELECT pg_catalog.setval('"covoiturafpa".ride_id_ride_seq', 1, false);


--
-- TOC entry 3355 (class 2606 OID 17646)
-- Name: car car_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".car
    ADD CONSTRAINT car_pkey PRIMARY KEY (id_car);


--
-- TOC entry 3353 (class 2606 OID 17634)
-- Name: car_type car_type_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".car_type
    ADD CONSTRAINT car_type_pkey PRIMARY KEY (id_car_type);



--
-- TOC entry 3359 (class 2606 OID 17663)
-- Name: centre centre_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".centre
    ADD CONSTRAINT centre_pkey PRIMARY KEY (id_centre);


--
-- TOC entry 3349 (class 2606 OID 17620)
-- Name: city city_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id_city);


--
-- TOC entry 3363 (class 2606 OID 17689)
-- Name: day_timetable day_timetable_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".day_timetable
    ADD CONSTRAINT day_timetable_pkey PRIMARY KEY (id_day_timetable);


--
-- TOC entry 3351 (class 2606 OID 17627)
-- Name: day_week day_week_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".day_week
    ADD CONSTRAINT day_week_pkey PRIMARY KEY (id_day_week);


--
-- TOC entry 3367 (class 2606 OID 17713)
-- Name: destination destination_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".destination
    ADD CONSTRAINT destination_pkey PRIMARY KEY (id_destination);


--
-- TOC entry 3371 (class 2606 OID 17738)
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id_person);


--
-- TOC entry 3361 (class 2606 OID 17677)
-- Name: formation formation_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".formation
    ADD CONSTRAINT formation_pkey PRIMARY KEY (id_formation);


--
-- TOC entry 3345 (class 2606 OID 17601)
-- Name: fuel fuel_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".fuel
    ADD CONSTRAINT fuel_pkey PRIMARY KEY (id_fuel);


--
-- TOC entry 3381 (class 2606 OID 17810)
-- Name: recurring_days recurring_days_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".recurring_days
    ADD CONSTRAINT recurring_days_pkey PRIMARY KEY (id_ride, id_day_week);


--
-- TOC entry 3347 (class 2606 OID 17608)
-- Name: notification notification_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".notification
    ADD CONSTRAINT notification_pkey PRIMARY KEY (id_notification);


--
-- TOC entry 3377 (class 2606 OID 17785)
-- Name: one_time one_time_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".one_time
    ADD CONSTRAINT one_time_pkey PRIMARY KEY (id_ride);


--
-- TOC entry 3365 (class 2606 OID 17701)
-- Name: partner partner_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".partner
    ADD CONSTRAINT partner_pkey PRIMARY KEY (id_partner);


--
-- TOC entry 3341 (class 2606 OID 17587)
-- Name: person person_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id_person);


--
-- TOC entry 3375 (class 2606 OID 17775)
-- Name: recurring recurring_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".recurring
    ADD CONSTRAINT recurring_pkey PRIMARY KEY (id_ride);


--
-- TOC entry 3379 (class 2606 OID 17795)
-- Name: ride_passenger ride_passenger_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".ride_passenger
    ADD CONSTRAINT ride_passenger_pkey PRIMARY KEY (id_person, id_ride);


--
-- TOC entry 3373 (class 2606 OID 17755)
-- Name: ride ride_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".ride
    ADD CONSTRAINT ride_pkey PRIMARY KEY (id_ride);


--
-- TOC entry 3369 (class 2606 OID 17723)
-- Name: trainee trainee_pkey; Type: CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".trainee
    ADD CONSTRAINT trainee_pkey PRIMARY KEY (id_person);


--
-- TOC entry 3384 (class 2606 OID 17647)
-- Name: car car_id_car_type_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".car
    ADD CONSTRAINT car_id_car_type_fkey FOREIGN KEY (id_car_type) REFERENCES "covoiturafpa".car_type(id_car_type);


--
-- TOC entry 3385 (class 2606 OID 17652)
-- Name: car car_id_person_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".car
    ADD CONSTRAINT car_id_person_fkey FOREIGN KEY (id_person) REFERENCES "covoiturafpa".person(id_person);


--
-- TOC entry 3383 (class 2606 OID 17635)
-- Name: car_type car_type_id_fuel_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".car_type
    ADD CONSTRAINT car_type_id_fuel_fkey FOREIGN KEY (id_fuel) REFERENCES "covoiturafpa".fuel(id_fuel);




--
-- TOC entry 3388 (class 2606 OID 17690)
-- Name: day_timetable day_timetable_id_centre_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".day_timetable
    ADD CONSTRAINT day_timetable_id_centre_fkey FOREIGN KEY (id_centre) REFERENCES "covoiturafpa".centre(id_centre);


--
-- TOC entry 3390 (class 2606 OID 17714)
-- Name: destination destination_id_city_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".destination
    ADD CONSTRAINT destination_id_city_fkey FOREIGN KEY (id_city) REFERENCES "covoiturafpa".city(id_city);


--
-- TOC entry 3393 (class 2606 OID 17739)
-- Name: employee employee_id_centre_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".employee
    ADD CONSTRAINT employee_id_centre_fkey FOREIGN KEY (id_centre) REFERENCES "covoiturafpa".centre(id_centre);


--
-- TOC entry 3394 (class 2606 OID 17744)
-- Name: employee employee_id_person_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".employee
    ADD CONSTRAINT employee_id_person_fkey FOREIGN KEY (id_person) REFERENCES "covoiturafpa".person(id_person);


--
-- TOC entry 3387 (class 2606 OID 17678)
-- Name: formation formation_id_centre_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".formation
    ADD CONSTRAINT formation_id_centre_fkey FOREIGN KEY (id_centre) REFERENCES "covoiturafpa".centre(id_centre);


--
-- TOC entry 3402 (class 2606 OID 17816)
-- Name: recurring_days recurring_days_id_day_week_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".recurring_days
    ADD CONSTRAINT recurring_days_id_day_week_fkey FOREIGN KEY (id_day_week) REFERENCES "covoiturafpa".day_week(id_day_week);


--
-- TOC entry 3401 (class 2606 OID 17811)
-- Name: recurring_days recurring_days_id_ride_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".recurring_days
    ADD CONSTRAINT recurring_days_id_ride_fkey FOREIGN KEY (id_ride) REFERENCES "covoiturafpa".recurring(id_ride);


--
-- TOC entry 3382 (class 2606 OID 17609)
-- Name: notification notification_id_person_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".notification
    ADD CONSTRAINT notification_id_person_fkey FOREIGN KEY (id_person) REFERENCES "covoiturafpa".person(id_person);


--
-- TOC entry 3398 (class 2606 OID 17786)
-- Name: one_time one_time_id_ride_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".one_time
    ADD CONSTRAINT one_time_id_ride_fkey FOREIGN KEY (id_ride) REFERENCES "covoiturafpa".ride(id_ride);


--
-- TOC entry 3389 (class 2606 OID 17702)
-- Name: partner partner_id_centre_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".partner
    ADD CONSTRAINT partner_id_centre_fkey FOREIGN KEY (id_centre) REFERENCES "covoiturafpa".centre(id_centre);


--
-- TOC entry 3397 (class 2606 OID 17776)
-- Name: recurring recurring_id_ride_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".recurring
    ADD CONSTRAINT recurring_id_ride_fkey FOREIGN KEY (id_ride) REFERENCES "covoiturafpa".ride(id_ride);


--
-- TOC entry 3396 (class 2606 OID 17766)
-- Name: ride ride_id_car_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".ride
    ADD CONSTRAINT ride_id_car_fkey FOREIGN KEY (id_car) REFERENCES "covoiturafpa".car(id_car);


--
-- TOC entry 3395 (class 2606 OID 17756)
-- Name: ride ride_id_destination_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".ride
    ADD CONSTRAINT ride_id_destination_fkey FOREIGN KEY (id_destination) REFERENCES "covoiturafpa".destination(id_destination);


--
-- TOC entry 3399 (class 2606 OID 17796)
-- Name: ride_passenger ride_passenger_id_person_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".ride_passenger
    ADD CONSTRAINT ride_passenger_id_person_fkey FOREIGN KEY (id_person) REFERENCES "covoiturafpa".person(id_person);


--
-- TOC entry 3400 (class 2606 OID 17801)
-- Name: ride_passenger ride_passenger_id_ride_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".ride_passenger
    ADD CONSTRAINT ride_passenger_id_ride_fkey FOREIGN KEY (id_ride) REFERENCES "covoiturafpa".ride(id_ride);


--
-- TOC entry 3391 (class 2606 OID 17724)
-- Name: trainee trainee_id_formation_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".trainee
    ADD CONSTRAINT trainee_id_formation_fkey FOREIGN KEY (id_formation) REFERENCES "covoiturafpa".formation(id_formation);


--
-- TOC entry 3392 (class 2606 OID 17729)
-- Name: trainee trainee_id_person_fkey; Type: FK CONSTRAINT; Schema: covoiturafpa; Owner: -
--

ALTER TABLE ONLY "covoiturafpa".trainee
    ADD CONSTRAINT trainee_id_person_fkey FOREIGN KEY (id_person) REFERENCES "covoiturafpa".person(id_person);


CREATE TABLE "covoiturafpa".teacher_of (
    id_formation integer NOT NULL,
    id_teacher integer NOT NULL
);

ALTER TABLE ONLY "covoiturafpa".teacher_of
    ADD CONSTRAINT teacher_of_pkey PRIMARY KEY (id_formation, id_teacher);
ALTER TABLE ONLY "covoiturafpa".teacher_of
    ADD CONSTRAINT teacher_of_id_teacher_fkey FOREIGN KEY (id_teacher) REFERENCES "covoiturafpa".employee(id_person);
ALTER TABLE ONLY "covoiturafpa".teacher_of
    ADD CONSTRAINT teacher_of_id_formation_fkey FOREIGN KEY (id_formation) REFERENCES "covoiturafpa".formation(id_formation);