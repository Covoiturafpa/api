CREATE ROLE "user" WITH
	LOGIN
	NOSUPERUSER
	NOCREATEDB
	NOCREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'xxxxxx'; --"userpassword"
COMMENT ON ROLE "user" IS 'Classic user';

ALTER ROLE "user"
	SUPERUSER
	CREATEDB
	CREATEROLE;

ALTER DEFAULT PRIVILEGES FOR ROLE postgres
GRANT INSERT, SELECT, UPDATE, DELETE ON TABLES TO "user";

GRANT ALL ON TABLE "CovoiturAFPA".car TO "user";
GRANT ALL ON TABLE "CovoiturAFPA".car_type TO "user";
GRANT ALL ON TABLE "CovoiturAFPA".centre TO "user";
GRANT ALL ON TABLE "CovoiturAFPA".city TO "user";
GRANT ALL ON TABLE "CovoiturAFPA".day_timetable TO "user";
GRANT ALL ON TABLE "CovoiturAFPA".destination TO "user";
GRANT ALL ON TABLE "CovoiturAFPA".employee TO "user";
GRANT ALL ON TABLE "CovoiturAFPA".formation TO "user";
GRANT ALL ON TABLE "CovoiturAFPA".fuel TO "user";
GRANT ALL ON TABLE "CovoiturAFPA".notif_config TO "user";
GRANT ALL ON TABLE "CovoiturAFPA".notification TO "user";
GRANT ALL ON TABLE "CovoiturAFPA".one_time TO "user";
GRANT ALL ON TABLE "CovoiturAFPA".partner TO "user";
GRANT ALL ON TABLE "CovoiturAFPA".person TO "user";
GRANT ALL ON TABLE "CovoiturAFPA".recurring TO "user";
GRANT ALL ON TABLE "CovoiturAFPA".ride TO "user";
GRANT ALL ON TABLE "CovoiturAFPA".ride_passenger TO "user";
GRANT ALL ON TABLE "CovoiturAFPA".trainee TO "user";