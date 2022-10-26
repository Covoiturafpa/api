INSERT INTO covoiturafpa.centre VALUES (28, 'AFPA Rohefort', '57 Avenue Bernadotte', 45.958593, -0.963835, '+33472864830', true);

INSERT INTO covoiturafpa.person VALUES (500, 'test1@mail.fr', 'movlejxx6c3fpybxlw6o', 'dupond', 'jacques', '+33511397860', true, false, false, '2022-01-01 00:00:00', 'https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/684.jpg', 'E', '2022-10-12', NULL);
INSERT INTO covoiturafpa.employee VALUES (57, 'employe1', true, 28, false);

INSERT INTO covoiturafpa.formation VALUES (1, 'Necessitserunt quo cumque', 28);
INSERT INTO covoiturafpa.person VALUES (510, 'test2@mail.fr', '$2a$10$ADYcx/WiB5kIo7.8/IiU8eJEBxCt/bPnAeNKTWrrAvJh3SHKFcDxu', 'robert', 'michel', '+33430830984', true, true, true, '2022-01-01 00:00:00', 'https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/390.jpg', 'T', '2022-10-12', NULL);
INSERT INTO covoiturafpa.trainee VALUES (51, 1);

INSERT INTO covoiturafpa.fuel VALUES (1, 'ESSENCE', 1.82);
INSERT INTO covoiturafpa.car_type VALUES (66, 'COMPACT', 7.3, 1);
INSERT INTO covoiturafpa.car VALUES (22, 'BMW ATS', 5, 1.8, 66, 500);

INSERT INTO covoiturafpa.city VALUES (30, 'Mirebeau');
INSERT INTO covoiturafpa.destination VALUES (40, 46.79087385, 0.2054939898438607, false, 30);

INSERT INTO covoiturafpa.ride VALUES (100, false, '08:00:00', 'lorem', 40, 22, 1.00, 'O');
INSERT INTO covoiturafpa.one_time VALUES (100, '2022-10-26');

INSERT INTO covoiturafpa.ride VALUES (101, false, '14:30:00', 'lorem', 40, 22, 2.00, 'O');
INSERT INTO covoiturafpa.one_time VALUES (101, '2022-10-26');

INSERT INTO covoiturafpa.ride VALUES (102, false, '12:00:10', 'lorem', 40, 22, 3.00, 'O');
INSERT INTO covoiturafpa.one_time VALUES (102, '2022-10-26');

INSERT INTO covoiturafpa.ride VALUES (103, false, '08:00:00', 'lorem', 40, 22, 1.00, 'R');
INSERT INTO covoiturafpa.recurring VALUES (103, '2022-10-26', '2022-11-26');
INSERT INTO covoiturafpa.recurring_days VALUES (3, 1);

INSERT INTO covoiturafpa.ride VALUES (104, false, '08:24:42', 'lorem', 40, 22, 2.00, 'R');
INSERT INTO covoiturafpa.recurring VALUES (104, '2022-10-26', '2022-12-26');
INSERT INTO covoiturafpa.recurring_days VALUES (3, 1);

INSERT INTO covoiturafpa.ride VALUES (105, false, '18:01:02', 'lorem', 40, 22, 3.00, 'R');
INSERT INTO covoiturafpa.recurring VALUES (105, '2022-10-26', '2023-01-26');
INSERT INTO covoiturafpa.recurring_days VALUES (3, 1);


