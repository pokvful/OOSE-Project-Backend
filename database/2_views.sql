USE lbc;

CREATE VIEW locationView
AS (
    SELECT 
        location_id, 
        location_name, 
        delay, 
        location_geofence.longitude, 
        location_geofence.latitude, 
        location_geofence.radius, 
        area.area_id AS area_id, 
        area.area_name AS area_name, 
        franchise.franchise_id AS franchise_id, 
        franchise.franchise_name AS franchise_name, 
        area_geofence.latitude AS area_latitude, 
        area_geofence.longitude AS area_longitude, 
        area_geofence.radius AS area_radius, 
        (SELECT GROUP_CONCAT(intervention_id) FROM location_intervention WHERE location_intervention.location_id = location_id) AS linked_interventions
    FROM location
    LEFT OUTER JOIN geofence AS location_geofence ON location_geofence.geofence_id = location.geofence_id
    LEFT OUTER JOIN area ON location.area_id = area.area_id
    LEFT OUTER JOIN franchise ON location.franchise_id = franchise.franchise_id
    LEFT OUTER JOIN geofence AS area_geofence ON area_geofence.geofence_id = area.geofence_id
);

CREATE VIEW interventionView
AS (
    SELECT
        intervention.intervention_id,
        intervention.intervention_name,
        intervention.intervention_type,
        command.command,
        CASE WHEN COUNT(question.question_id) = 1 THEN MIN(question.question_id) ELSE NULL END AS question_id,
        CASE WHEN COUNT(question.question_id) = 1 THEN MIN(question.question) ELSE NULL END AS question
    FROM intervention
    LEFT OUTER JOIN question ON question.intervention_id = intervention.intervention_id
    LEFT OUTER JOIN command ON command.intervention_id = intervention.intervention_id
    GROUP BY intervention_id
);

CREATE VIEW areaView
AS (
    SELECT
        a.area_name,
        a.area_id,
        g.longitude,
        g.latitude,
        g.radius
    FROM area a
    JOIN geofence g ON a.geofence_id = g.geofence_id
);

CREATE VIEW userView
AS (
    SELECT user_id, username, password, email, users.role_id, role.role_name 
    FROM users
    LEFT OUTER JOIN role ON role.role_id = users.role_id
);

CREATE VIEW roleView
AS (
    SELECT role_id, role_name
    FROM role
);

CREATE VIEW franchiseView
AS (
    SELECT franchise_id, franchise_name
    FROM franchise
);

CREATE VIEW goalView
AS (
    SELECT goal_id, goal_name
    FROM goal
);