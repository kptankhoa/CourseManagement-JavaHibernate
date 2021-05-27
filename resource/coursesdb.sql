/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

CREATE TABLE `account`
(
    `username` varchar(45)  NOT NULL,
    `password` varchar(100) NOT NULL,
    `type`     varchar(45)  NOT NULL,
    PRIMARY KEY (`username`),
    UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `class`
(
    `class_id`     varchar(45) NOT NULL,
    `total`        int         NOT NULL,
    `total_male`   int         NOT NULL,
    `total_female` int         NOT NULL,
    PRIMARY KEY (`class_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `student`
(
    `student_id` varchar(20)                                                  NOT NULL,
    `name`       varchar(100)                                                 NOT NULL,
    `gender`     varchar(20)                                                  NOT NULL,
    `dob`        date                                                         NOT NULL,
    `username`   varchar(45)                                                  NOT NULL,
    `class_id`   varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    PRIMARY KEY (`student_id`),
    KEY `fk_student_account_idx` (`username`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `semester`
(
    `semester_id` int         NOT NULL,
    `name`        varchar(45) NOT NULL,
    `school_year` varchar(45) NOT NULL,
    `start_date`  date        NOT NULL,
    `end_day`     varchar(45) NOT NULL,
    PRIMARY KEY (`semester_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `shift`
(
    `id`   int         NOT NULL AUTO_INCREMENT,
    `time` varchar(19) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 29
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `subject`
(
    `subject_id` varchar(45)                                                   NOT NULL,
    `name`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `credits`    int                                                           NOT NULL,
    PRIMARY KEY (`subject_id`),
    UNIQUE KEY `subject_id_UNIQUE` (`subject_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `course`
(
    `course_id`   varchar(45)                                                   NOT NULL,
    `subject_id`  varchar(45)                                                   NOT NULL,
    `semester_id` int                                                           NOT NULL,
    `lecturer`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `room`        varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `shift_id`    int                                                           NOT NULL,
    PRIMARY KEY (`course_id`),
    KEY `fk_course_subject_idx` (`subject_id`),
    KEY `fk_course_semester_idx` (`semester_id`),
    KEY `fk_course_shift_idx` (`shift_id`),
    CONSTRAINT `fk_course_semester` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`semester_id`),
    CONSTRAINT `fk_course_shift` FOREIGN KEY (`shift_id`) REFERENCES `shift` (`id`),
    CONSTRAINT `fk_course_subject` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `registration`
(
    `student_id`        varchar(45) NOT NULL,
    `course_id`         varchar(45) NOT NULL,
    `subject_id`        varchar(45) NOT NULL,
    `registration_time` datetime    NOT NULL,
    PRIMARY KEY (`student_id`, `course_id`),
    KEY `fk_registration_course_idx` (`course_id`),
    KEY `fk_registration_subject_idx` (`subject_id`),
    CONSTRAINT `fk_registration_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
    CONSTRAINT `fk_registration_student` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
    CONSTRAINT `fk_registration_subject` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `registration_sesstion`
(
    `session_id` int         NOT NULL,
    `course_id`  varchar(45) NOT NULL,
    `start_date` date        NOT NULL,
    `end_date`   date        NOT NULL,
    PRIMARY KEY (`session_id`),
    KEY `fk_session_course_idx` (`course_id`),
    CONSTRAINT `fk_session_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO `account` (`username`, `password`, `type`)
VALUES ('admin', 'admin', 'ministry');


INSERT INTO `class` (`class_id`, `total`, `total_male`, `total_female`)
VALUES ('17CTT4', 100, 90, 10);


INSERT INTO `course` (`course_id`, `subject_id`, `semester_id`, `lecturer`, `room`, `shift_id`)
VALUES ('1', 'CSC13102', 1, 'Nguyễn Văn Khiết', 'F202', 15);


INSERT INTO `registration` (`student_id`, `course_id`, `subject_id`, `registration_time`)
VALUES ('1712537', '1', 'CSC13102', '2021-03-06 12:05:37');


INSERT INTO `registration_sesstion` (`session_id`, `course_id`, `start_date`, `end_date`)
VALUES (1, '1', '2021-03-01', '2021-03-07');


INSERT INTO `semester` (`semester_id`, `name`, `school_year`, `start_date`, `end_day`)
VALUES (1, 'HK2', '2020-2021', '2021-03-09', '2021-06-18');


INSERT INTO `shift` (`id`, `time`)
VALUES (1, 'SUN-1'),
       (2, 'SUN-2'),
       (3, 'SUN-3'),
       (4, 'SUN-4'),
       (5, 'MON-1'),
       (6, 'MON-2'),
       (7, 'MON-3'),
       (8, 'MON-4'),
       (9, 'TUE-1'),
       (10, 'TUE-2'),
       (11, 'TUE-3'),
       (12, 'TUE-4'),
       (13, 'WED-1'),
       (14, 'WED-2'),
       (15, 'WED-3'),
       (16, 'WED-4'),
       (17, 'THU-1'),
       (18, 'THU-2'),
       (19, 'THU-3'),
       (20, 'THU-4'),
       (21, 'FRI-1'),
       (22, 'FRI-2'),
       (23, 'FRI-3'),
       (24, 'FRI-4'),
       (25, 'SAT-1'),
       (26, 'SAT-2'),
       (27, 'SAT-3'),
       (28, 'SAT-4');

INSERT INTO `student` (`student_id`, `name`, `gender`, `dob`, `username`, `class_id`)
VALUES ('1712537', 'Khoa Phan', 'Male', '1999-05-04', '1712537', '17CTT4');


INSERT INTO `subject` (`subject_id`, `name`, `credits`)
VALUES ('CSC13102', 'Lập trình ứng dụng Java', 4);



/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;