CREATE ROLE "user" WITH
	LOGIN
	NOSUPERUSER
	NOCREATEDB
	NOCREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'usermdp'; 
COMMENT ON ROLE "user" IS 'Classic user';

GRANT USAGE ON SCHEMA "covoiturafpa" TO "user"; 

GRANT ALL ON TABLE "covoiturafpa".car TO "user";
GRANT ALL ON TABLE "covoiturafpa".car_type TO "user";
GRANT ALL ON TABLE "covoiturafpa".centre TO "user";
GRANT ALL ON TABLE "covoiturafpa".city TO "user";
GRANT ALL ON TABLE "covoiturafpa".day_timetable TO "user";
GRANT ALL ON TABLE "covoiturafpa".destination TO "user";
GRANT ALL ON TABLE "covoiturafpa".employee TO "user";
GRANT ALL ON TABLE "covoiturafpa".formation TO "user";
GRANT ALL ON TABLE "covoiturafpa".fuel TO "user";
GRANT ALL ON TABLE "covoiturafpa".notif_config TO "user";
GRANT ALL ON TABLE "covoiturafpa".notification TO "user";
GRANT ALL ON TABLE "covoiturafpa".one_time TO "user";
GRANT ALL ON TABLE "covoiturafpa".partner TO "user";
GRANT ALL ON TABLE "covoiturafpa".person TO "user";
GRANT ALL ON TABLE "covoiturafpa".recurring TO "user";
GRANT ALL ON TABLE "covoiturafpa".ride TO "user";
GRANT ALL ON TABLE "covoiturafpa".ride_passenger TO "user";
GRANT ALL ON TABLE "covoiturafpa".trainee TO "user";
GRANT ALL ON TABLE "covoiturafpa".day_week TO "user";
GRANT ALL ON TABLE "covoiturafpa".happen TO "user";

GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA "covoiturafpa" TO "user";