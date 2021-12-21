use lbc;

INSERT INTO role (role_name)
VALUES 
    ("Beheerder"),
    ("Gebruiker");

INSERT INTO role_permission(role_id, permission) VALUES
(1, "CREATE_ROLES"),
(1, "UPDATE_ROLES"),
(1, "GET_ROLES"),
(1, "DELETE_ROLES");

INSERT INTO users (username, password, email, role_id) VALUES ("ivan", "hashhashhash", "ivan@hp2.nl", 1);
INSERT INTO users (username, password, email, role_id) VALUES ("wur", "password", "info@wur.nl", 1);
INSERT INTO users (username, password, email, role_id) VALUES ("Bart", "$2a$10$hMGclFII4roSvokU7PQJeuWecWo1/DmXVloWzETgZjS1JzgzoyO7C", "bart@han.nl", 1);
INSERT INTO users (username, password, email, role_id) VALUES ("Tim", "hashhashhashtim", "tim@han.nl", 2);

INSERT INTO geofence (latitude, longitude, radius) VALUES(51.8425, 5.85278, 600);
INSERT INTO area(area_name, geofence_id) VALUES ("Nijmegen", 1);
INSERT INTO franchise(franchise_name) VALUES ("McDonalds");

CALL createCommand("Volkoren brood", "Welkom bij de Subway, kiest u voor een volkoren broodje?");
CALL createCommand("Salade", "Koop een salade");
CALL createCommand("Fruitteler", "Koop een zak appels");

CALL createQuestion("Kerk", "Hoe hoog is de kerktoren?");
    SET @question_id = LAST_INSERT_ID();
    CALL addAnswerToQuestion(@question_id, "100 meter");
    CALL addAnswerToQuestion(@question_id, "30 meter");
    CALL addAnswerToQuestion(@question_id, "55 meter");

CALL createQuestion("Bami", "Wat zit in een bamischijf?");
    SET @question_id = LAST_INSERT_ID();
    CALL addAnswerToQuestion(@question_id, "Rijst");
    CALL addAnswerToQuestion(@question_id, "Bami");
    CALL addAnswerToQuestion(@question_id, "Beide");

CALL createQuestion("Water", "Wat is water?");
    SET @question_id = LAST_INSERT_ID();
    CALL addAnswerToQuestion(@question_id, "letterlijk water");
    CALL addAnswerToQuestion(@question_id, "olie");
    CALL addAnswerToQuestion(@question_id, "De wetenschap is daar nog niet over uit");


CALL createQuestionnaire("Wiskunde quiz");
    SET @questionnaire_id = LAST_INSERT_ID();
    CALL addQuestionToQuestionnaire(@questionnaire_id, "5 x 5");
        SET @question_id = LAST_INSERT_ID();
        CALL addAnswerToQuestion(@question_id, "12");
        CALL addAnswerToQuestion(@question_id, "25");
        CALL addAnswerToQuestion(@question_id, "14");
    CALL addQuestionToQuestionnaire(@questionnaire_id, "100 / 40");
        SET @question_id = LAST_INSERT_ID();
        CALL addAnswerToQuestion(@question_id, "13");
        CALL addAnswerToQuestion(@question_id, "14 1/2");
        CALL addAnswerToQuestion(@question_id, "2 1/2");
    CALL addQuestionToQuestionnaire(@questionnaire_id, "Is 313 een priemgetal");
        SET @question_id = LAST_INSERT_ID();
        CALL addAnswerToQuestion(@question_id, "Ja");
        CALL addAnswerToQuestion(@question_id, "Nee");

CALL createQuestionnaire("Konijnen");
    SET @questionnaire_id = LAST_INSERT_ID();
    CALL addQuestionToQuestionnaire(@questionnaire_id, "Wat is het belangrijkste voedsel voor een konijn?");
        SET @question_id = LAST_INSERT_ID();
        CALL addAnswerToQuestion(@question_id, "Hooi");
        CALL addAnswerToQuestion(@question_id, "Voer");
        CALL addAnswerToQuestion(@question_id, "Blaadjes");
    CALL addQuestionToQuestionnaire(@questionnaire_id, "Wie denkt de baas te zijn in de relatie konijn-eigenaar?");
        SET @question_id = LAST_INSERT_ID();
        CALL addAnswerToQuestion(@question_id, "Het konijn");
        CALL addAnswerToQuestion(@question_id, "De eigenaar");
        CALL addAnswerToQuestion(@question_id, "Beide");
    CALL addQuestionToQuestionnaire(@questionnaire_id, "Welk konijn heeft een eigen strip in de Donald Duck?");
        SET @question_id = LAST_INSERT_ID();
        CALL addAnswerToQuestion(@question_id, "Lodewyck");
        CALL addAnswerToQuestion(@question_id, "Sammy");
        CALL addAnswerToQuestion(@question_id, "Broer konijn");


INSERT INTO franchise_intervention(intervention_id, franchise_id) VALUES (1, 1);
INSERT INTO location(franchise_id, location_name, area_id, geofence_id) VALUES (1, "Mcdonalds Molenstraat", 1, 1);
INSERT INTO location_intervention(location_id, intervention_id) VALUES (1,1);

INSERT INTO geofence (latitude, longitude, radius) VALUES(51.9691868, 5.6653948, 2350);
INSERT INTO area(area_name, geofence_id) VALUES ("Wageningen", 2);
INSERT INTO franchise(franchise_name) VALUES ("Gaia_building (office)");
INSERT INTO franchise(franchise_name) VALUES ("Subway");
INSERT INTO geofence (latitude, longitude, radius) VALUES(51.987491838000039, 5.666409706000024, 100);
INSERT INTO geofence (latitude, longitude, radius) VALUES(51.983383490000051, 5.664095127000053, 100);
INSERT INTO location(franchise_id, location_name, area_id, geofence_id) VALUES (2, "Gaia_building", 2, 3);
INSERT INTO location(franchise_id, location_name, area_id, geofence_id) VALUES (3, "Subway", 2, 4);


INSERT INTO geofence (latitude, longitude, radius) VALUES(52.092876, 5.104480, 3500);
INSERT INTO area(area_name, geofence_id) VALUES ("Utrecht", 5);
INSERT INTO franchise (franchise_name) VALUES ("HP2 Kantoor");
INSERT INTO geofence (latitude, longitude, radius) VALUES(52.0672984, 5.1088383, 100);
INSERT INTO location (franchise_id, location_name, area_id, geofence_id) VALUES (3, "Subway Utrecht", 3, 6);
INSERT INTO geofence (latitude, longitude, radius) VALUES(52.0709499, 5.1100019, 100);
INSERT INTO location (franchise_id, location_name, area_id, geofence_id) VALUES (4, "HP2 Kantoor", 3, 7);


CALL createLocation("Danny's autopaleis", 10, 21.3221, 3.321, 100, 3, 4, "");

INSERT INTO goal(goal_name) VALUES ("Afvallen");
INSERT INTO goal(goal_name) VALUES ("Geld Besparen");