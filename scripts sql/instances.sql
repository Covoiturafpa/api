with customer_json (doc) as (
   values 
    ('[
        {
            "id_person": 43,
            "email": "MadgeSmith@mail.fr",
            "password": "b4lnkw50s5kvt9cx01m7",
            "surname": "Bergnaum",
            "first_name": "Karson",
            "phone_number": "+33672728649",
            "is_activated": false,
            "contact_by_sms": true,
            "contact_by_mail": true,
            "last_login": "01/01/2022",
            "photo_path": "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/1223.jpg"
        },
        {
            "id_person": 45,
            "email": "KrystelRath@mail.fr",
            "password": "v60zs9egqj9n65aec37j",
            "surname": "Howell",
            "first_name": "Godfrey",
            "phone_number": "+33430830984",
            "is_activated": false,
            "contact_by_sms": true,
            "contact_by_mail": true,
            "last_login": "01/01/2022",
            "photo_path": "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/390.jpg"
        },
        {
            "id_person": 97,
            "email": "OdaBoyle@mail.fr",
            "password": "bnbhmg8jufhon5zc6z8j",
            "surname": "Leffler",
            "first_name": "Billie",
            "phone_number": "+33458210191",
            "is_activated": false,
            "contact_by_sms": false,
            "contact_by_mail": true,
            "last_login": "01/01/2022",
            "photo_path": "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/658.jpg"
        },
        {
            "id_person": 51,
            "email": "MohammadGreenfelder@mail.fr",
            "password": "6sjngbrc3t1tigidn826",
            "surname": "Bailey",
            "first_name": "Wava",
            "phone_number": "+33983567107",
            "is_activated": true,
            "contact_by_sms": false,
            "contact_by_mail": false,
            "last_login": "01/01/2022",
            "photo_path": "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/420.jpg"
        },
        {
            "id_person": 57,
            "email": "CristopherWolf@mail.fr",
            "password": "movlejxx6c3fpybxlw6o",
            "surname": "Weber",
            "first_name": "Casper",
            "phone_number": "+33511397860",
            "is_activated": false,
            "contact_by_sms": false,
            "contact_by_mail": false,
            "last_login": "01/01/2022",
            "photo_path": "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/684.jpg"
        }
    ]'::json)
)
insert into person  
select p.*
from customer_json l
cross join lateral json_populate_recordset(null::person, doc) as p;



with customer_json (doc) as (
   values 
    ('[{
        "id_notif_config": "13",
        "contact_by_sms": "false"
      }
    ]'::json)
)
insert into notif_config
select p.*
from customer_json l
cross join lateral json_populate_recordset(null::notif_config, doc) as p;



with customer_json (doc) as (
   values 
    ('[
        {
            "id_fuel": "1",
            "name": "Gasoline",
            "price_by_unit": "2.3"
        },
        {
            "id_fuel": "2",
            "name": "Electric",
            "price_by_unit": "4.1"
        },
        {
            "id_fuel": "3",
            "name": "Hybrid",
            "price_by_unit": "11.2"
        }
    ]'::json)
)
insert into fuel  
select p.*
from customer_json l
cross join lateral json_populate_recordset(null::fuel, doc) as p;



with customer_json (doc) as (
   values 
    ('[
        {
            "id_notification": "82",
            "contact_by_sms": "false",
            "created_time": "2022-01-01 00:00:00.000",
            "is_unread": "false",
            "id_person": "97"
        },
        {
            "id_notification": "78",
            "contact_by_sms": "true",
            "created_time": "2022-01-01 00:00:00.000",
            "is_unread": "false",
            "id_person": "97"
        },
        {
            "id_notification": "32",
            "contact_by_sms": "false",
            "created_time": "2022-01-01 00:00:00.000",
            "is_unread": "false",
            "id_person": "97"
        },
        {
            "id_notification": "93",
            "contact_by_sms": "false",
            "created_time": "2022-01-01 00:00:00.000",
            "is_unread": "false",
            "id_person": "97"
        },
        {
            "id_notification": "73",
            "contact_by_sms": "true",
            "created_time": "2022-01-01 00:00:00.000",
            "is_unread": "true",
            "id_person": "43"
        },
        {
            "id_notification": "8",
            "contact_by_sms": "false",
            "created_time": "2022-01-01 00:00:00.000",
            "is_unread": "true",
            "id_person": "45"
        },
        {
            "id_notification": "30",
            "contact_by_sms": "true",
            "created_time": "2022-01-01 00:00:00.000",
            "is_unread": "false",
            "id_person": "43"
        },
        {
            "id_notification": "76",
            "contact_by_sms": "false",
            "created_time": "2022-01-01 00:00:00.000",
            "is_unread": "true",
            "id_person": "45"
        },
        {
            "id_notification": "5",
            "contact_by_sms": "true",
            "created_time": "2022-01-01 00:00:00.000",
            "is_unread": "false",
            "id_person": "57"
        },
        {
            "id_notification": "25",
            "contact_by_sms": "false",
            "created_time": "2022-01-01 00:00:00.000",
            "is_unread": "true",
            "id_person": "51"
        }
    ]'::json)
)
insert into notification 
select p.*
from customer_json l
cross join lateral json_populate_recordset(null::notification, doc) as p;
UPDATE notification  set type  = 'ACCEPTED_RESERVATION';



with customer_json (doc) as (
   values 
    ('[
        {
            "id_city": "1",
            "name": "La Rochelle"
        },
{
            "id_city": "2",
            "name": "Chatellerault"
        }
    ]'::json)
)
insert into city  
select p.*
from customer_json l
cross join lateral json_populate_recordset(null::city, doc) as p;



with customer_json (doc) as (
   values 
    ('[
        {
            "id_car_type": 3,
            "name_car_type": "UTILITAIRE",
            "avg_fuel_consumption": 5.5,
            "seats": 8,
            "id_fuel": 1
        },
        {
            "id_car_type": 5,
            "name_car_type": "UTILITAIRE",
            "avg_fuel_consumption": 5.4,
            "seats": 8,
            "id_fuel": 2
        },
        {
            "id_car_type": 4,
            "name_car_type": "BERLINE",
            "avg_fuel_consumption": 7.1,
            "seats": 4,
            "id_fuel": 2
        },
        {
            "id_car_type": 1,
            "name_car_type": "COMPACT",
            "avg_fuel_consumption": 1.2,
            "seats": 2,
            "id_fuel": 3
        }
    ]'::json)
)
insert into car_type  
select p.*
from customer_json l
cross join lateral json_populate_recordset(null::car_type, doc) as p;
UPDATE car_type SET name = 'COMPACT';



with customer_json (doc) as (
   values 
    ('[
        {
            "id_car": 4,
            "model": "Porsche Taurus",
            "seats": 6,
            "avg_fuel_consumption": 4.2,
            "id_car_type": 4,
        "id_person": 43
        },
        {
            "id_car": 2,
            "model": "BMW ATS",
            "seats": 5,
            "avg_fuel_consumption": 1.8,
            "id_car_type": 3,
        "id_person": 43
        },
        {
            "id_car": 3,
            "model": "Chrysler Land Cruiser",
            "seats": 6,
            "avg_fuel_consumption": 6.3,
            "id_car_type": 1,
        "id_person": 45
        }
    ]'::json)
)
insert into car  
select p.*
from customer_json l
cross join lateral json_populate_recordset(null::car, doc) as p;



with customer_json (doc) as (
   values 
    ('[{
        "id_centre": "28",
        "name": "Velit explicabo minima rem",
        "address": "56606 bld Verdun Charlotte",
        "latitude": "51.4139",
        "longitude": "-2.5798",
        "phone_number": "+33472864830",
        "id_notif_config": "13"
    }
    ]'::json)
)
insert into centre 
select p.*
from customer_json l
cross join lateral json_populate_recordset(null::centre, doc) as p;



with customer_json (doc) as (
   values 
    ('[{
        "id_destination": 1,
        "latitude": 59.9211,
        "longitude": 81.1344,
        "is_from_afpa": false,
        "id_city": 1
    }]'::json)
)
insert into destination  
select p.*
from customer_json l
cross join lateral json_populate_recordset(null::destination, doc) as p;



with customer_json (doc) as (
   values 
    ('[
        {
            "id_formation": "1",
            "name": "Necessitserunt quo cumque",
            "id_centre": 28
        },
        {
            "id_formation": "4",
            "name": "Ullam doloribus praesentium quisqu",
            "id_centre": 28
        },
        {
            "id_formation": "7",
            "name": "Molestias iusto veniam ut cum.",
            "id_centre": 28
        },
        {
            "id_formation": "2",
            "name": "Et quia eerum quo alias nisi magni.",
            "id_centre": 28
        },
        {
            "id_formation": "5",
            "name": "Voluptatrecusandae nisi.",
            "id_centre": 28
        }
    ]'::json)
)
insert into formation  
select p.*
from customer_json l
cross join lateral json_populate_recordset(null::formation, doc) as p;



