use lbc;

create table if not exists role (
    role_id INT(6) UNSIGNED auto_increment PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL
);

create table if not exists role_permission (
    role_id INT(6) UNSIGNED NOT NULL,
    permission VARCHAR(50) NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role(role_id) ON DELETE CASCADE,
    PRIMARY KEY(role_id, permission)
);

create table if not exists goal (
    goal_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    goal_name VARCHAR(100) NOT NULL
);

create table if not exists users (
    user_id INT(6) UNSIGNED auto_increment PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    role_id INT(6) UNSIGNED NOT NULL,
    goal_id INT(6) UNSIGNED NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role(role_id) ON DELETE CASCADE,
    FOREIGN KEY (goal_id) REFERENCES goal(goal_id) ON DELETE CASCADE
);

create table if not exists geofence (
    geofence_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    longitude DOUBLE NOT NULL,
    latitude DOUBLE NOT NULL,
    radius INT(6) NOT NULL
);



create table if not exists intervention (
    intervention_id INT(6) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    intervention_name VARCHAR(255) NOT NULL,
    intervention_type ENUM('command', 'question', 'questionnaire') NOT NULL
);

create table if not exists area (
    area_id INT(6) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    area_name VARCHAR(100) NOT NULL,
    geofence_id INT(6) UNSIGNED NOT NULL,
    FOREIGN KEY (geofence_id) REFERENCES geofence(geofence_id) ON DELETE CASCADE
);

create table if not exists participant (
    participant_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_id INT(6) UNSIGNED NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

create table if not exists route (
    route_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    participant_id INT(6) UNSIGNED NOT NULL,
    FOREIGN KEY (participant_id) REFERENCES participant(participant_id) ON DELETE CASCADE
);

create table if not exists breadcrumb (
    breadcrumb_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    route_id INT(6) UNSIGNED NOT NULL,
    datetime DATETIME NOT NULL,
    longitude DOUBLE NOT NULL,
    latitude DOUBLE NOT NULL,
    FOREIGN KEY (route_id) REFERENCES route(route_id) ON DELETE CASCADE
);

create table if not exists command (
    command_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    command VARCHAR(100) NOT NULL,
    intervention_id INT(6) UNSIGNED NOT NULL UNIQUE,
    FOREIGN KEY (intervention_id) REFERENCES intervention(intervention_id) ON DELETE CASCADE
);

create table if not exists franchise (
    franchise_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    franchise_name VARCHAR(100) NOT NULL
);

create table if not exists franchise_intervention (
    intervention_id INT(6) UNSIGNED NOT NULL,
    franchise_id INT(6) UNSIGNED NOT NULL,
    FOREIGN KEY (franchise_id) REFERENCES franchise(franchise_id) ON DELETE CASCADE,
    FOREIGN KEY (intervention_id) REFERENCES intervention(intervention_id) ON DELETE CASCADE
);

create table if not exists location (
    location_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    franchise_id INT(6) UNSIGNED NOT NULL,
    area_id INT(6) UNSIGNED NOT NULL,
    geofence_id INT(6) UNSIGNED NOT NULL,
    location_name VARCHAR(100) NOT NULL,
    delay INT(6) UNSIGNED NOT NULL,
    FOREIGN KEY (franchise_id) REFERENCES franchise(franchise_id) ON DELETE CASCADE,
    FOREIGN KEY (area_id) REFERENCES area(area_id) ON DELETE CASCADE,
    FOREIGN KEY (geofence_id) REFERENCES geofence(geofence_id) ON DELETE CASCADE
);

create table if not exists location_intervention (
    intervention_id INT(6) UNSIGNED NOT NULL,
    location_id INT(6) UNSIGNED NOT NULL,
    FOREIGN KEY (location_id) REFERENCES location(location_id) ON DELETE CASCADE,
    FOREIGN KEY (intervention_id) REFERENCES intervention(intervention_id) ON DELETE CASCADE
);

create table if not exists question (
    question_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    question varchar(255) NOT NULL,
    intervention_id INT(6) UNSIGNED NOT NULL,
    FOREIGN KEY (intervention_id) REFERENCES intervention(intervention_id) ON DELETE CASCADE
);

create table if not exists answer (
    answer_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    answer VARCHAR(255) NOT NULL,
    question_id INT(6) UNSIGNED NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question(question_id) ON DELETE CASCADE
);

create table if not exists followup_question (
    followup_question_id INT(6) UNSIGNED NOT NULL,
    original_answer_id INT(6) UNSIGNED NOT NULL
);

create table if not exists range_question (
    question_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    MIN INT NOT NULL,
    MAX INT NOT NULL,
    divisions INT NOT NULL
);

create table if not exists range_answer (
    question_id INT(6) UNSIGNED NOT NULL,
    answer_id INT(6) UNSIGNED NOT NULL,
    FOREIGN KEY (question_id) REFERENCES range_question(question_id) ON DELETE CASCADE,
    FOREIGN KEY (answer_id) REFERENCES answer(answer_id) ON DELETE CASCADE
);

create table if not exists choice_answer (
    answer_id INT(6) UNSIGNED NOT NULL
);

create table if not exists reaction (
    reaction_id INT(6) UNSIGNED PRIMARY KEY,
    intervention_id INT(6) UNSIGNED NOT NULL,
    breadcrumb_id INT(6) UNSIGNED NOT NULL,
    FOREIGN KEY (intervention_id) REFERENCES intervention(intervention_id) ON DELETE CASCADE,
    FOREIGN KEY (breadcrumb_id) REFERENCES breadcrumb(breadcrumb_id) ON DELETE CASCADE
);

create table if not exists reaction_answer (
    reaction_id INT(6) UNSIGNED NOT NULL,
    question_id INT(6) UNSIGNED NOT NULL,
    answer_id INT(6) UNSIGNED NOT NULL,
    FOREIGN KEY (answer_id) REFERENCES answer(answer_id) ON DELETE CASCADE
);

create table if not exists action (
    action_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    action VARCHAR(255) NOT NULL
);

create table if not exists sensitivity_action (
    action_id INT(6) UNSIGNED,
    question_id INT(6) UNSIGNED,
    FOREIGN KEY (action_id) REFERENCES action(action_id),
    FOREIGN KEY (question_id) REFERENCES question(question_id)
);

create table if not exists profile_question (
    profile_question_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    question VARCHAR(255) NOT NULL,
    goal_id INT(6) UNSIGNED NOT NULL,
    FOREIGN KEY (goal_id) REFERENCES goal(goal_id) ON DELETE CASCADE
);