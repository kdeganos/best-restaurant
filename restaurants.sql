--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: cuisines; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE cuisines (
    id integer NOT NULL,
    type character varying
);


ALTER TABLE cuisines OWNER TO "Guest";

--
-- Name: cuisines_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE cuisines_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cuisines_id_seq OWNER TO "Guest";

--
-- Name: cuisines_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE cuisines_id_seq OWNED BY cuisines.id;


--
-- Name: restaurants; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE restaurants (
    id integer NOT NULL,
    name character varying,
    city character varying,
    cuisine_id integer
);


ALTER TABLE restaurants OWNER TO "Guest";

--
-- Name: restaurants_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE restaurants_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE restaurants_id_seq OWNER TO "Guest";

--
-- Name: restaurants_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE restaurants_id_seq OWNED BY restaurants.id;


--
-- Name: reviews; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE reviews (
    id integer NOT NULL,
    review character varying,
    rating integer,
    restaurant_id integer,
    reviewer character varying
);


ALTER TABLE reviews OWNER TO "Guest";

--
-- Name: reviews_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE reviews_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE reviews_id_seq OWNER TO "Guest";

--
-- Name: reviews_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE reviews_id_seq OWNED BY reviews.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY cuisines ALTER COLUMN id SET DEFAULT nextval('cuisines_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY restaurants ALTER COLUMN id SET DEFAULT nextval('restaurants_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY reviews ALTER COLUMN id SET DEFAULT nextval('reviews_id_seq'::regclass);


--
-- Data for Name: cuisines; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY cuisines (id, type) FROM stdin;
39	Chinese
40	Thai
41	Mexican
\.


--
-- Name: cuisines_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('cuisines_id_seq', 41, true);


--
-- Data for Name: restaurants; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY restaurants (id, name, city, cuisine_id) FROM stdin;
31	Golden Dragon	Ogden	39
32	Thai City	Las Vegas	40
\.


--
-- Name: restaurants_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('restaurants_id_seq', 32, true);


--
-- Data for Name: reviews; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY reviews (id, review, rating, restaurant_id, reviewer) FROM stdin;
7	Test	4	27	Reviewer 2
8	Review Test	4	27	Jeff
9	Was not actually served by a golden dragon... Very disappointing!	1	31	Jeff
10	Was actually served by golden dragon. Above review must have gone on dragons day off!	5	31	Rick
11	Average food. Good price.	3	32	Jeff
\.


--
-- Name: reviews_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('reviews_id_seq', 11, true);


--
-- Name: cuisines_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY cuisines
    ADD CONSTRAINT cuisines_pkey PRIMARY KEY (id);


--
-- Name: restaurants_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY restaurants
    ADD CONSTRAINT restaurants_pkey PRIMARY KEY (id);


--
-- Name: reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

