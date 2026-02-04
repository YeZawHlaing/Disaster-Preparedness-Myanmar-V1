CREATE TABLE role (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      name VARCHAR(255) NOT NULL,
                      status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',

                      CONSTRAINT pk_role PRIMARY KEY (id),
                      CONSTRAINT uk_role_name UNIQUE (name)
);


CREATE TABLE user (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role_id BIGINT NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       CONSTRAINT fk_user_role
                           FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE coordinates (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,

                             latitude DOUBLE NOT NULL,
                             longitude DOUBLE NOT NULL,

                             status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE'
);

CREATE TABLE address (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,

                         city VARCHAR(100),
                         township VARCHAR(100),
                         road VARCHAR(150),
                         street VARCHAR(150),

                         coordinate_id BIGINT UNIQUE,

                         status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

                         CONSTRAINT fk_address_coordinates
                             FOREIGN KEY (coordinate_id)
                                 REFERENCES coordinates(id)
                                 ON DELETE SET NULL
);
CREATE TABLE region (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,

                        name VARCHAR(100),

                        status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE'
);
CREATE TABLE profile (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,

                         full_name VARCHAR(150) NOT NULL,
                         contact VARCHAR(50) NOT NULL UNIQUE,
                         dob DATE NOT NULL,

                         social_url VARCHAR(255),
                         profile_pic VARCHAR(255),
                         gender VARCHAR(20),

                         nrc VARCHAR(50) NOT NULL UNIQUE,

                         user_id BIGINT NOT NULL UNIQUE,
                         address_id BIGINT,
                         region_id BIGINT,

                         status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

                         CONSTRAINT fk_profile_user
                             FOREIGN KEY (user_id)
                                 REFERENCES user(id),

                         CONSTRAINT fk_profile_address
                             FOREIGN KEY (address_id)
                                 REFERENCES address(id),

                         CONSTRAINT fk_profile_region
                             FOREIGN KEY (region_id)
                                 REFERENCES region(id)
);
CREATE TABLE report (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,

                        description TEXT,

                        contact VARCHAR(50) NOT NULL UNIQUE,

                        pic1 VARCHAR(255),
                        pic2 VARCHAR(255),
                        pic3 VARCHAR(255),

                        report_type VARCHAR(100),

                        report_status VARCHAR(30) NOT NULL,

                        address_id BIGINT,
                        profile_id BIGINT,

                        status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

                        CONSTRAINT fk_report_address
                            FOREIGN KEY (address_id)
                                REFERENCES address(id),

                        CONSTRAINT fk_report_profile
                            FOREIGN KEY (profile_id)
                                REFERENCES profile(id)
);
CREATE TABLE organization (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,

                              name VARCHAR(255),

                              sos VARCHAR(100) NOT NULL UNIQUE,

                              profile_pic VARCHAR(255),
                              social_url VARCHAR(255),

                              founded_year DATE NOT NULL,

                              description TEXT,

                              service_type VARCHAR(100) NOT NULL,

                              mission_statement TEXT,

                              user_id BIGINT NOT NULL UNIQUE,
                              address_id BIGINT,
                              region_id BIGINT,

                              status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

                              CONSTRAINT fk_organization_user
                                  FOREIGN KEY (user_id)
                                      REFERENCES user(id),

                              CONSTRAINT fk_organization_address
                                  FOREIGN KEY (address_id)
                                      REFERENCES address(id),

                              CONSTRAINT fk_organization_region
                                  FOREIGN KEY (region_id)
                                      REFERENCES region(id)
);

CREATE TABLE achievement (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,

                             title VARCHAR(255),
                             detail TEXT,

                             img1 VARCHAR(255),
                             img2 VARCHAR(255),
                             img3 VARCHAR(255),

                             organization_id BIGINT NOT NULL,

                             status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

                             CONSTRAINT fk_achievement_organization
                                 FOREIGN KEY (organization_id)
                                     REFERENCES organization(id)
);
