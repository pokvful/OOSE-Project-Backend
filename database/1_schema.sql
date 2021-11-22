use lbc;

create table if not exists role (
role VARCHAR(255) PRIMARY KEY
);

create table if not exists users (
user_id INT(6) UNSIGNED auto_increment PRIMARY KEY,
username VARCHAR(100),
password VARCHAR(100),
email VARCHAR(100),
`role` VARCHAR(255),
FOREIGN KEY (`role`) REFERENCES `role`(`role`)
);

create table if not exists token (
token varchar(255),
user_id INT(6) UNSIGNED,
FOREIGN KEY (user_id) REFERENCES users(user_id)
);

create table if not exists geofence (
geofence_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
longitude DOUBLE NOT NULL,
latitude DOUBLE NOT NULL,
radius INT(6)
);

create table if not exists area (
area_id INT(6) UNSIGNED AUTO_INCREMENT,
area_name VARCHAR(100) NOT NULL,
geofence_id INT(6) UNSIGNED,
PRIMARY KEY (area_id),
FOREIGN KEY (geofence_id) REFERENCES geofence(geofence_id)
);

create table if not exists participant (
participant_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
user_id INT(6) UNSIGNED,
FOREIGN KEY (user_id) REFERENCES users(user_id)
);

create table if not exists route (
route_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
participant_id INT(6) UNSIGNED,
FOREIGN KEY (participant_id) REFERENCES participant(participant_id)
);

create table if not exists breadcrumb (
breadcrumb_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
route_id INT(6) UNSIGNED,
datetime DATETIME,
longitude DOUBLE,
latitude DOUBLE,
FOREIGN KEY (route_id) REFERENCES route(route_id)
);

create table if not exists command (
command_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
command VARCHAR(100) 
);

create table if not exists franchise (
franchise_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
franchise_name VARCHAR(100)
);

create table if not exists intervention(
intervention_id INT(6) UNSIGNED PRIMARY KEY
);

create table if not exists franchise_intervention (
intervention_id INT(6) UNSIGNED,
franchise_id INT(6) UNSIGNED,
FOREIGN KEY (franchise_id) REFERENCES franchise(franchise_id),
FOREIGN KEY (intervention_id) REFERENCES intervention(intervention_id)
);

create table if not exists command_in_intervention (
command_id INT(6) UNSIGNED,
intervention_id INT(6) UNSIGNED,
FOREIGN KEY (intervention_id) REFERENCES intervention(intervention_id),
FOREIGN KEY (command_id) REFERENCES command(command_id)
);

create table if not exists location (
location_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
franchise_id INT(6) UNSIGNED,
area_id INT(6) UNSIGNED,
geofence_id INT(6) UNSIGNED,
location_name VARCHAR(100),
delay INT(6) UNSIGNED,
FOREIGN KEY (franchise_id) REFERENCES franchise(franchise_id),
FOREIGN KEY (area_id) REFERENCES area(area_id),
FOREIGN KEY (geofence_id) REFERENCES geofence(geofence_id)
);

create table if not exists location_intervention (
intervention_id INT(6) UNSIGNED,
location_id INT(6) UNSIGNED,
FOREIGN KEY (location_id) REFERENCES location(location_id),
FOREIGN KEY (intervention_id) REFERENCES intervention(intervention_id)
);

create table if not exists question (
question_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
question varchar(100),
type varchar(25),
question_text varchar(100)
);

create table if not exists answer (
answer_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
answer VARCHAR(255),
question_id INT(6) UNSIGNED,
FOREIGN KEY (question_id) REFERENCES question(question_id)
);

create table if not exists answers (
answer_id INT(6) UNSIGNED,
answer VARCHAR(255),
FOREIGN KEY (answer_id) REFERENCES answer(answer_id)
);

create table if not exists answer_to_question (
question_id INT(6) UNSIGNED,
answer_id INT(6) UNSIGNED,
FOREIGN KEY (answer_id) REFERENCES answer(answer_id)
);

create table if not exists followup_question (
followup_question_id INT(6) UNSIGNED,
original_answer_id INT(6) UNSIGNED
);

create table if not exists range_question (
question_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
MIN INT,
MAX INT,
divisions INT
);

create table if not exists range_answer (
question_id INT(6) UNSIGNED,
answer_id INT(6) UNSIGNED,
FOREIGN KEY (question_id) REFERENCES range_question(question_id),
FOREIGN KEY (answer_id) REFERENCES answer(answer_id)
);

create table if not exists choice_answer (
answer_id INT(6) UNSIGNED
);

create table if not exists reaction (
reaction_id INT(6) UNSIGNED PRIMARY KEY,
intervention_id INT(6) UNSIGNED,
breadcrumb_id INT(6) UNSIGNED,
FOREIGN KEY (intervention_id) REFERENCES intervention(intervention_id),
FOREIGN KEY (breadcrumb_id) REFERENCES breadcrumb(breadcrumb_id)
);


create table if not exists reaction_answer (
reaction_id INT(6) UNSIGNED,
question_id INT(6) UNSIGNED,
answer_id INT(6) UNSIGNED,
FOREIGN KEY (answer_id) REFERENCES answer(answer_id)
);

create table if not exists question_in_intervention (
intervention_id INT(6) UNSIGNED,
question_id INT(6) UNSIGNED,
FOREIGN KEY (intervention_id) REFERENCES intervention(intervention_id),
FOREIGN KEY (question_id) REFERENCES question(question_id)
);

create table if not exists action (
action_id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
action VARCHAR(255)
);

create table if not exists sensitivity_action (
action_id INT(6) UNSIGNED,
question_id INT(6) UNSIGNED,
FOREIGN KEY (action_id) REFERENCES action(action_id),
FOREIGN KEY (question_id) REFERENCES question(question_id)
);