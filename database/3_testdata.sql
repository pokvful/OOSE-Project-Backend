use lbc;

INSERT INTO users (username, password) VALUES ("ivan", "test123");
INSERT INTO token (token, user_id) VALUES ("123", 1);
INSERT INTO geofence (latitude, longitude, radius) VALUES(51.8425, 5.85278, 600);
INSERT INTO area(area_name, geofence_id) VALUES ("Nijmegen", 1);
INSERT INTO franchise(franchise_name) VALUES ("McDonalds");
INSERT INTO intervention(intervention_name) VALUES ("Saladebar");
INSERT INTO intervention(intervention_name) VALUES ("Hardlopen");
INSERT INTO intervention(intervention_name) VALUES ("Opdrukken");
INSERT INTO intervention(intervention_name) VALUES ("Kerk bekijken");
INSERT INTO franchise_intervention(intervention_id, franchise_id) VALUES (1, 1);
INSERT INTO location(franchise_id, location_name, area_id, geofence_id) VALUES (1, "Mcdonalds Molenstraat", 1, 1);
INSERT INTO command(command) VALUES ("Koop Big Mac");
INSERT INTO command_in_intervention(command_id, intervention_id) VALUES (1,1);
INSERT INTO location_intervention(location_id, intervention_id) VALUES (1,1);
INSERT INTO question (type, question_text, question) VALUES ("question", "Is de kerk groot?", "Is de kerk groot?");
INSERT INTO answer (answer, question_id) VALUES ("Ja", 1);
INSERT INTO question_in_intervention(intervention_id, question_id) VALUES (1, 1);



-- WU
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

INSERT INTO command(command) VALUES ("Welkom op kantoor!");
INSERT INTO command_in_intervention(command_id, intervention_id) VALUES (2,2);
INSERT INTO command(command) VALUES ("Welkom bij de Subway, kiest u voor een volkoren broodje?");
INSERT INTO command_in_intervention(command_id, intervention_id) VALUES (3,3);


INSERT INTO geofence (latitude, longitude, radius) VALUES(52.092876, 5.104480, 3500);
INSERT INTO area(area_name, geofence_id) VALUES ("Utrecht", 5);
INSERT INTO franchise(franchise_name) VALUES ("HP2 Kantoor");
INSERT INTO franchise_intervention(franchise_id) VALUES (4);
INSERT INTO geofence (latitude, longitude, radius) VALUES(52.0672984, 5.1088383, 100);
INSERT INTO location(franchise_id, location_name, area_id, geofence_id) VALUES (3, "Subway Utrecht", 3, 6);
INSERT INTO geofence (latitude, longitude, radius) VALUES(52.0709499, 5.1100019, 100);
INSERT INTO location(franchise_id, location_name, area_id, geofence_id) VALUES (4, "HP2 Kantoor", 3, 7);

INSERT INTO command_in_intervention(command_id, intervention_id) VALUES (2,4);

INSERT INTO users (username, password) VALUES ("wur", "password");

INSERT INTO answers (answer_id, answer) VALUES (1, "Is de kerk groot?");

INSERT INTO intervention (intervention_name) VALUES 
    ("saladebar"),
    ("hardlopen"),
    ("tellen");

CALL createLocation("Danny's autopaleis", 10, 21.3221, 3.321, 100, 3, 1, "");


-- SELECT * FROM answer;

-- select * from area;
-- select * from franchise;
-- select * from geofence;
-- select * from intervention;
-- select * from token
-- select * from question
-- select * from answer


-- SELECT area.area_id, area.area_name, area.geofence_id, geofence.longitude, geofence.latitude, geofence.radius FROM `area` INNER JOIN `geofence` ON area.geofence_id=geofence.geofence_id;


-- WITH cats AS (SELECT franchise.franchise_id AS franchise_id FROM franchise WHERE franchise.franchise_id IN (SELECT location.franchise_id FROM location WHERE location.location_id=1)),
-- ints AS (SELECT intervention.intervention_id FROM intervention WHERE intervention.franchise_id IN (SELECT * FROM cats)),
-- qii as (SELECT ints.intervention_id, question_in_intervention.question_id FROM question_in_intervention INNER JOIN ints ON question_in_intervention.intervention_id=ints.intervention_id),
-- questions as (SELECT question.question_id, question.question, question.type, qii.intervention_id FROM question INNER JOIN qii ON question.question_id=qii.question_id)
-- SELECT * FROM ints LEFT OUTER JOIN questions LEFT OUTER JOIN range_question ON questions.question_id=range_question.question_id ON ints.intervention_id=questions.intervention_id;



-- SELECT location.location_id, location.area_id,  location.location_name, geofence.geofence_id, geofence.longitude, geofence.latitude, geofence.radius FROM `location` INNER JOIN geofence
--                      ON location.geofence_id=geofence.geofence_id 
--                      WHERE location.area_id=2;
                     
                     
-- WITH answers AS (SELECT * FROM answer_to_question WHERE answer_to_question.question_id=1 ),
-- completeanswers AS (SELECT answers.* FROM answers INNER JOIN answer ON answers.answer_id=answer.answer_id)
--                      SELECT * FROM completeanswers 
--                      LEFT OUTER JOIN followup_question
--                      ON completeanswers.answer_id=followup_question.original_answer_id
--                      LEFT OUTER JOIN choice_answer
--                      ON choice_answer.answer_id=completeanswers.answer_id
--                      LEFT OUTER JOIN range_answer
--                      ON range_answer.answer_id=completeanswers.answer_id
--                      LEFT OUTER JOIN sensitivity_action
--                      ON sensitivity_action.question_id=completeanswers.question_id
--                      LEFT OUTER JOIN action
--                      ON action.action_id=sensitivity_action.action_id;
                     
-- SELECT area.area_id, area.area_name, area.geofence_id, geofence.longitude, geofence.latitude, geofence.radius FROM `area`
-- 		INNER JOIN `geofence` ON area.geofence_id=geofence.geofence_id
        
        
--         select * from geofence