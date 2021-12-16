use lbc;


INSERT INTO role (role_name)
VALUES 
    ("beheerder"),
    ("gebruiker");

INSERT INTO users (username, password, email, role_id) VALUES ("ivan", "hashhashhash", "ivan@hp2.nl", 1);
INSERT INTO users (username, password, email, role_id) VALUES ("wur", "password", "info@wur.nl", 1);
INSERT INTO users (username, password, email, role_id) VALUES ("Bart", "$2a$10$hMGclFII4roSvokU7PQJeuWecWo1/DmXVloWzETgZjS1JzgzoyO7C", "bart@han.nl", 2);
INSERT INTO users (username, password, email, role_id) VALUES ("Tim", "hashhashhashtim", "tim@han.nl", 2);

INSERT INTO geofence (latitude, longitude, radius) VALUES(51.8425, 5.85278, 600);
INSERT INTO area(area_name, geofence_id) VALUES ("Nijmegen", 1);
INSERT INTO franchise(franchise_name) VALUES ("McDonalds");

INSERT INTO intervention(intervention_name, intervention_type) VALUES ("Vragenlijst", 'command');
INSERT INTO intervention(intervention_name, intervention_type) VALUES ("Saladebar", 'questionnaire');
INSERT INTO intervention(intervention_name, intervention_type) VALUES ("Opdrukken", 'command');
INSERT INTO intervention(intervention_name, intervention_type) VALUES ("Kerk bekijken", 'question');
INSERT INTO intervention(intervention_name, intervention_type) VALUES ("Volkoren brood", 'command');

INSERT INTO franchise_intervention(intervention_id, franchise_id) VALUES (1, 1);
INSERT INTO location(franchise_id, location_name, area_id, geofence_id) VALUES (1, "Mcdonalds Molenstraat", 1, 1);
INSERT INTO location_intervention(location_id, intervention_id) VALUES (1,1);

INSERT INTO question (question, intervention_id) VALUES ("Is de kerk groot?", 3);

INSERT INTO command(command, intervention_id) VALUES ("eet een salade", 4);

INSERT INTO question (question, intervention_id) VALUES ("vraag a", 1);
INSERT INTO question (question, intervention_id) VALUES ("vraag b", 1);
INSERT INTO question (question, intervention_id) VALUES ("vraag c", 1);

INSERT INTO command (command, intervention_id) VALUES ("Welkom op kantoor!", 2);
INSERT INTO command (command, intervention_id) VALUES ("Welkom bij de Subway, kiest u voor een volkoren broodje?", 5);

INSERT INTO answer (answer, question_id) VALUES ("Ja", 1);
INSERT INTO answer (answer, question_id) VALUES ("antwoord a1", 2);
INSERT INTO answer (answer, question_id) VALUES ("antwoord a2", 2);
INSERT INTO answer (answer, question_id) VALUES ("antwoord a3", 2);
INSERT INTO answer (answer, question_id) VALUES ("antwoord b", 3);
INSERT INTO answer (answer, question_id) VALUES ("antwoord c", 4);


INSERT INTO geofence (latitude, longitude, radius) VALUES(51.9691868, 5.6653948, 2350);
INSERT INTO area(area_name, geofence_id) VALUES ("Wageningen", 2);
INSERT INTO franchise(franchise_name) VALUES ("Gaia_building (office)");
INSERT INTO franchise(franchise_name) VALUES ("Subway");
INSERT INTO franchise_intervention(franchise_id) VALUES (2);
INSERT INTO franchise_intervention(franchise_id) VALUES (3);
INSERT INTO geofence (latitude, longitude, radius) VALUES(51.987491838000039, 5.666409706000024, 100);
INSERT INTO geofence (latitude, longitude, radius) VALUES(51.983383490000051, 5.664095127000053, 100);
INSERT INTO location(franchise_id, location_name, area_id, geofence_id) VALUES (2, "Gaia_building", 2, 3);
INSERT INTO location(franchise_id, location_name, area_id, geofence_id) VALUES (3, "Subway", 2, 4);


INSERT INTO geofence (latitude, longitude, radius) VALUES(52.092876, 5.104480, 3500);
INSERT INTO area(area_name, geofence_id) VALUES ("Utrecht", 5);
INSERT INTO franchise (franchise_name) VALUES ("HP2 Kantoor");
INSERT INTO franchise_intervention(franchise_id) VALUES (4);
INSERT INTO geofence (latitude, longitude, radius) VALUES(52.0672984, 5.1088383, 100);
INSERT INTO location (franchise_id, location_name, area_id, geofence_id) VALUES (3, "Subway Utrecht", 3, 6);
INSERT INTO geofence (latitude, longitude, radius) VALUES(52.0709499, 5.1100019, 100);
INSERT INTO location (franchise_id, location_name, area_id, geofence_id) VALUES (4, "HP2 Kantoor", 3, 7);


CALL createLocation("Danny's autopaleis", 10, 21.3221, 3.321, 100, 3, null, "");

INSERT INTO goal(goal_name) VALUES ("Afvallen");
INSERT INTO goal(goal_name) VALUES ("Geld Besparen");