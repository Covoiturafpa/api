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
GRANT ALL ON TABLE "covoiturafpa".notif_config TO "afpaUser";
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
