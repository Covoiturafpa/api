CREATE extension cube SCHEMA covoiturafpa;
CREATE extension earthdistance SCHEMA covoiturafpa;

CREATE OR REPLACE FUNCTION covoiturafpa.get_distance(latitude_1 double precision, longitude_1 double precision, latitude_2 double precision, longitude_2 double precision)
RETURNS double precision
LANGUAGE sql
IMMUTABLE STRICT
RETURN covoiturafpa.earth_distance(covoiturafpa.ll_to_earth(latitude_1, longitude_1), covoiturafpa.ll_to_earth(latitude_2, longitude_2)) / 1000;
