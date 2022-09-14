CREATE OR REPLACE FUNCTION covoiturafpa.get_distance(latitude_1 double precision, longitude_1 double precision, latitude_2 double precision, longitude_2 double precision)
 RETURNS double precision
 LANGUAGE sql
 IMMUTABLE STRICT
RETURN sqrt((((latitude_1 - latitude_2) * (latitude_1 - latitude_2)) + ((longitude_1 - longitude_2) * (longitude_1 - longitude_2))));
GRANT ALL ON FUNCTION covoiturafpa.get_distance(float8, float8, float8, float8) TO "user";
