USE lbc;

DELIMITER //

CREATE PROCEDURE getAreas()
BEGIN
    SELECT *
    FROM areaView;
END //

CREATE PROCEDURE getAreaById (
    IN param_id INT
) BEGIN
    SELECT *
    FROM areaView
    WHERE area_id = param_id;
END //

CREATE PROCEDURE getRoles()
BEGIN
    SELECT *
    FROM roleView;
END //

CREATE PROCEDURE getRoleById (
    IN param_id INT
) BEGIN
    SELECT *
    FROM roleView
    WHERE role_id = param_id;
END //

CREATE PROCEDURE getPermissionsByRoleId (
    IN param_id INT
) BEGIN
    SELECT permission 
    FROM role_permission 
    WHERE role_id = param_id;
END //

CREATE PROCEDURE deleteRole (
    IN param_id INT
) BEGIN
    DELETE FROM role WHERE role_id = param_id;
END //

CREATE PROCEDURE updateRole(
    IN param_id INT,
    IN param_name VARCHAR(200),
    IN param_allowed_permissions VARCHAR(2000))
BEGIN
    DELETE FROM role_permission WHERE role_id = param_id;

    UPDATE role SET role_name = param_name WHERE role_id = param_id;

    IF param_allowed_permissions != "" THEN
        CREATE TEMPORARY TABLE converted_values (permission VARCHAR(50));
        SET @sqlvar = CONCAT("INSERT INTO converted_values (permission) VALUES ('", REPLACE(param_allowed_permissions, ",", "'),('"),"');");
        PREPARE insert_statement FROM @sqlvar;
        EXECUTE insert_statement;

        INSERT INTO role_permission (role_id, permission) SELECT param_id, permission FROM converted_values;
        DROP TEMPORARY TABLE converted_values;
    END IF;
END //

CREATE PROCEDURE createRole(
    IN param_name VARCHAR(200),
    IN param_allowed_permissions VARCHAR(2000))
BEGIN
    DECLARE param_role_id INT DEFAULT 0;
    INSERT INTO role(role_name) VALUES (param_name);

    SET param_role_id = LAST_INSERT_ID();

    IF param_allowed_permissions != "" THEN
        CREATE TEMPORARY TABLE converted_values (permission VARCHAR(50));
        SET @sqlvar = CONCAT("INSERT INTO converted_values (permission) VALUES ('", REPLACE(param_allowed_permissions, ",", "'),('"),"');");
        PREPARE insert_statement FROM @sqlvar;
        EXECUTE insert_statement;

        INSERT INTO role_permission (role_id, permission) SELECT param_role_id, permission FROM converted_values;
        DROP TEMPORARY TABLE converted_values;
    END IF;
END //

CREATE PROCEDURE createArea(
    IN param_name VARCHAR(255),
    IN param_longitude DECIMAL(9,6),
    IN param_latitude DECIMAL(9,6),
    IN param_radius INT)
BEGIN
    DECLARE param_geofence_id INT DEFAULT 0;

    INSERT INTO geofence(longitude, latitude, radius) VALUES (param_longitude, param_latitude, param_radius);

    SET param_geofence_id = LAST_INSERT_ID();
	
    INSERT INTO area (geofence_id, area_name) VALUES (param_geofence_id, param_name);
END //

CREATE PROCEDURE updateArea(
    IN param_id INT,
    IN param_name VARCHAR(255),
    IN param_longitude DECIMAL(9,6),
    IN param_latitude DECIMAL(9,6),
    IN param_radius INT)
BEGIN
    UPDATE geofence SET longitude = param_longitude, latitude = param_latitude, radius = param_radius WHERE geofence_id = (SELECT geofence_id FROM area WHERE area_id = param_id);
    UPDATE area SET area_name = param_name WHERE area_id = param_id;
END //

CREATE PROCEDURE getInterventions()
BEGIN
	SELECT *
    FROM interventionView;
END //

CREATE PROCEDURE getInterventionById (
    IN param_id INT
) BEGIN
	SELECT *
    FROM interventionView
	WHERE intervention_id = param_id;
END //

CREATE PROCEDURE getInterventionsByLocationId(
    IN param_location_id INT
)
BEGIN
    SELECT *
    FROM interventionView
    LEFT OUTER JOIN location_intervention ON location_intervention.intervention_id = interventionView.intervention_id
    WHERE location_intervention.location_id = param_location_id;
END //

CREATE PROCEDURE createCommand (
    IN param_name VARCHAR(255),
    IN param_command VARCHAR(255)
) BEGIN
	INSERT INTO intervention (intervention_type, intervention_name)
	VALUES ('command', param_name);
	
	INSERT INTO command (intervention_id, command)
	VALUES (LAST_INSERT_ID(), param_command);
END //

CREATE PROCEDURE updateCommand (
    IN param_id INT,
    IN param_name VARCHAR(255),
    IN param_command VARCHAR(255)
) BEGIN
    UPDATE intervention
    LEFT OUTER JOIN command ON command.intervention_id = intervention.intervention_id
    SET intervention.intervention_name = param_name,
        command.command = param_command
    WHERE intervention.intervention_id = param_id;
END //

CREATE PROCEDURE createQuestion (
    IN param_name VARCHAR(255),
    IN param_question VARCHAR(255)
) BEGIN
	INSERT INTO intervention (intervention_type, intervention_name)
	VALUES ('question', param_name);
		
	INSERT INTO question (intervention_id, question)
	VALUES (LAST_INSERT_ID(), param_question);
	
	SELECT LAST_INSERT_ID() AS question_id;
END //

CREATE PROCEDURE updateQuestion (
    IN param_id INT,
    IN param_name VARCHAR(255),
    IN param_question VARCHAR(255)
) BEGIN
	UPDATE intervention
	LEFT OUTER JOIN question ON question.intervention_id = intervention.intervention_id
	SET intervention.intervention_name = param_name,
		question.question = param_question
	WHERE intervention.intervention_id = param_id;

    DELETE answer
    FROM answer
    LEFT OUTER JOIN question ON question.question_id = answer.question_id
    WHERE question.intervention_id = param_id;
    
    SELECT question_id 
    FROM question
    WHERE question.intervention_id = param_id;
END //

CREATE PROCEDURE createQuestionnaire (
    IN param_name VARCHAR(255)
) BEGIN
	INSERT INTO intervention (intervention_type, intervention_name)
	VALUES ('questionnaire', param_name);
	
	SELECT LAST_INSERT_ID() AS intervention_id;
END //

CREATE PROCEDURE updateQuestionnaire (
    IN param_id INT,
    IN param_name VARCHAR(255)
) BEGIN
	UPDATE intervention
	SET intervention_name = param_name
	WHERE intervention_id = param_id;
	
	DELETE FROM question
	WHERE intervention_id = param_id;
END //

CREATE PROCEDURE deleteIntervention (
    IN param_intervention_id INT
) BEGIN
    DELETE FROM intervention 
	WHERE intervention_id = param_intervention_id;
END //

CREATE PROCEDURE addQuestionToQuestionnaire (
    IN param_intervention_id INT,
    IN param_question VARCHAR(255)
) BEGIN
	INSERT INTO question (intervention_id, question)
	VALUES (param_intervention_id, param_question);

	SELECT LAST_INSERT_ID() AS question_id;
END //

CREATE PROCEDURE addAnswerToQuestion (
    IN param_question_id INT,
    IN param_answer VARCHAR(255)
) BEGIN
	INSERT INTO answer (question_id, answer)
	VALUES (param_question_id, param_answer);
END //

CREATE PROCEDURE getAnswersByQuestionId(
    IN param_question_id INT
) BEGIN
	SELECT answer,answer_id
	FROM answer
	WHERE answer.question_id = param_question_id;
END //

CREATE PROCEDURE getQuestionsByQuestionnaireInterventionId(
    IN param_intervention_id INT
) BEGIN
    SELECT question, question_id
    FROM question
    WHERE intervention_id = param_intervention_id;
END //

CREATE PROCEDURE deleteArea(
    IN param_id INT
) BEGIN
    DELETE FROM location_intervention WHERE location_id IN (SELECT location_id FROM location WHERE area_id = param_id);
    DELETE FROM location WHERE area_id = param_id;
    DELETE FROM area WHERE area_id = param_id;
	DELETE FROM geofence WHERE geofence_id = (SELECT geofence_id FROM area WHERE area_id = param_id);
END //

CREATE PROCEDURE createLocation(
    IN param_name VARCHAR(255),
    IN param_delay INT,
    IN param_longitude DECIMAL(9,6),
    IN param_latitude DECIMAL(9, 6),
    IN param_radius INT,
    IN param_areaId INT,
    IN param_franchiseId INT,
    IN param_intervention_ids VARCHAR(500)) 
BEGIN
    DECLARE param_geofence_id INT DEFAULT 0;
    DECLARE last_insert_id INT DEFAULT 0;

    INSERT INTO geofence (longitude, latitude, radius)
    VALUES (param_longitude, param_latitude, param_radius);

    SET param_geofence_id = LAST_INSERT_ID();

    INSERT INTO location (area_id, geofence_id, location_name, delay, franchise_id)
    VALUES (param_areaId, param_geofence_id, param_name, param_delay, param_franchiseId);

    SET last_insert_id = LAST_INSERT_ID();

    IF param_intervention_ids != "" THEN
        CREATE TEMPORARY TABLE converted_values (id int(11));
        SET @sqlvar = CONCAT("INSERT INTO converted_values (id) VALUES ('", REPLACE(param_intervention_ids, ",", "'),('"),"');");
        PREPARE insert_statement FROM @sqlvar;
        EXECUTE insert_statement;

        INSERT INTO location_intervention (location_id, intervention_id) SELECT last_insert_id, id FROM converted_values;
        DROP TEMPORARY TABLE converted_values;
    END IF;
END //

CREATE PROCEDURE getLocationById(
    IN id INT
) BEGIN
    SELECT *
    FROM locationView
    WHERE location_id = id;
END //

CREATE PROCEDURE getLocations()
BEGIN
    SELECT *
    FROM locationView;
END //

CREATE PROCEDURE updateLocation (
    IN param_id INT,
    IN param_name VARCHAR(255),
    IN param_delay INT,
    IN param_longitude DECIMAL(9, 6),
    IN param_latitude DECIMAL(9, 6),
    IN param_radius INT,
    IN param_areaId INT,
    IN param_franchiseId INT,
    IN param_intervention_ids VARCHAR(500)
) BEGIN

    UPDATE geofence
    SET longitude=param_longitude, latitude=param_latitude, radius=param_radius
    WHERE geofence_id = (
        SELECT geofence_id
        FROM location
        WHERE location_id = param_id
    );

    UPDATE location
    SET area_id=param_areaId, location_name=param_name, delay = param_delay, franchise_id = param_franchiseId
    WHERE location_id = param_id;

    DELETE FROM location_intervention WHERE location_id = param_id;

    IF param_intervention_ids != "" THEN
        CREATE TEMPORARY TABLE converted_values (id int(11));
        SET @sqlvar = CONCAT("INSERT INTO converted_values (id) VALUES ('", REPLACE(param_intervention_ids, ",", "'),('"),"');");
        PREPARE insert_statement FROM @sqlvar;
        EXECUTE insert_statement;

        INSERT INTO location_intervention (location_id, intervention_id) SELECT param_id, id FROM converted_values;
        DROP TEMPORARY TABLE converted_values;
    END IF;
END //

CREATE PROCEDURE deleteLocation(
    IN param_id INT
) BEGIN
    DELETE FROM location_intervention WHERE location_id = param_id;
    DELETE FROM location WHERE location_id = param_id;
	DELETE FROM geofence WHERE geofence_id = (SELECT geofence_id FROM location WHERE location_id = param_id);
END //

CREATE PROCEDURE getFranchiseById(
    IN id INT
) BEGIN
    SELECT *
    FROM franchiseView
    WHERE franchise_id = id;
END //

CREATE PROCEDURE getFranchises()
BEGIN
    SELECT *
    FROM franchiseView;
END //

CREATE PROCEDURE getUserCountByRoleId(
    IN param_id INT
) BEGIN
    SELECT COUNT(*) AS count
    FROM users
    WHERE role_id = param_id;
END //

CREATE PROCEDURE getUserCountByGoalId(
    IN param_id INT
) BEGIN
    SELECT COUNT(*) AS count
    FROM users
    WHERE goal_id = param_id;
END //

CREATE PROCEDURE deleteFranchise(
    IN param_id INT
) BEGIN
    DELETE FROM location_intervention WHERE location_id IN (SELECT location_id FROM location WHERE franchise_id = param_id);
    DELETE FROM location WHERE franchise_id = param_id;
    DELETE FROM franchise_intervention WHERE franchise_id = param_id;
    DELETE FROM franchise WHERE franchise_id = param_id;
END //

CREATE PROCEDURE updateFranchise(
    IN param_id INT,
    IN param_name VARCHAR(255)
) BEGIN
    UPDATE franchise
    SET franchise_name = param_name
    WHERE franchise_id = param_id;
END //

CREATE PROCEDURE createFranchise(
    IN param_name VARCHAR(255)
) BEGIN
    INSERT INTO franchise(franchise_name) VALUES (param_name);
END //

CREATE PROCEDURE getGoalById(
    IN param_id INT
) BEGIN
    SELECT *
    FROM goalView
    WHERE goal_id = param_id;
END //

CREATE PROCEDURE getGoals()
BEGIN
    SELECT *
    FROM goalView;
END //

CREATE PROCEDURE createGoal(
    IN param_name VARCHAR(255)
) BEGIN
    INSERT INTO goal(goal_name)
    VALUES (param_name);

    SELECT LAST_INSERT_ID() AS goal_id;
END //

CREATE PROCEDURE deleteGoal(
    IN param_id INT
) BEGIN
    DELETE FROM goal
    WHERE goal_id = param_id;
END //

CREATE PROCEDURE addProfileQuestionToGoal (
    IN param_goal_id INT,
    IN param_question VARCHAR(255)
) BEGIN
    INSERT INTO profile_question (goal_id, question)
    VALUES (param_goal_id, param_question);
END //

CREATE PROCEDURE getProfileQuestionsByGoalId (
    IN param_goal_id INT
) BEGIN
    SELECT *
    FROM profileQuestionView
    WHERE goal_id = param_goal_id;
END //

CREATE PROCEDURE updateGoal(
    IN param_id INT,
    IN param_name VARCHAR(255)
) BEGIN
    UPDATE goal
    SET goal_name = param_name
    WHERE goal_id = param_id;

    DELETE
    FROM profile_question
    WHERE goal_id = param_id;
END //

CREATE PROCEDURE createUser(
    IN param_name VARCHAR(255),
    IN param_email VARCHAR(255),
    IN param_password VARCHAR(255),
    IN param_role_id INT,
    IN param_goal_id INT
) BEGIN
    INSERT INTO users(username, password, email, role_id, goal_id)
        VALUES(param_name, param_password, param_email, param_role_id, param_goal_id);
END //

CREATE PROCEDURE getUsers()
BEGIN
    SELECT *
    FROM userView;
END //

CREATE PROCEDURE getUserById (
    IN param_user_id INT
) BEGIN
    SELECT *
    FROM userView
    WHERE user_id = param_user_id;
END //

CREATE PROCEDURE getUserByUsername (
    IN param_user_name VARCHAR(200)
) BEGIN
    SELECT *
    FROM userView
    WHERE username = param_user_name;
END //

CREATE PROCEDURE updateUser (
    IN param_id INT,
    IN param_username VARCHAR(255),
    IN param_password VARCHAR(255),
    IN param_email VARCHAR(255),
    IN param_role_id INT,
    IN param_goal_id INT
) BEGIN
    UPDATE users
    SET 
        username = param_username,
        password = param_password,
        email = param_email,
        role_id = param_role_id,
        goal_id = param_goal_id
    WHERE user_id = param_id;
END //

CREATE PROCEDURE deleteUser (
    IN param_id INT
) BEGIN
    DELETE FROM users WHERE user_id = param_id;
END //

DELIMITER ;