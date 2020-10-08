CREATE SCHEMA system_setting;
CREATE TABLE system_setting.users
(
    uuid       UUID         NOT NULL DEFAULT gen_random_uuid(),
    username   VARCHAR(50)  NOT NULL,
    password   VARCHAR(255) NOT NULL,
    registered TIMESTAMP             DEFAULT now(),
    active     BOOLEAN      NOT NULL DEFAULT TRUE,
    CONSTRAINT pk_system_setting_users PRIMARY KEY (uuid),
    CONSTRAINT uk_system_setting_users_username UNIQUE (username)
);

CREATE TABLE system_setting.roles
(
    uuid UUID         NOT NULL DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_system_setting_roles PRIMARY KEY (uuid),
    CONSTRAINT uk_system_setting_roles_name UNIQUE (name)
);

CREATE TABLE system_setting.users_roles
(
    user_uuid UUID NOT NULL,
    role_uuid UUID NOT NULL,
    CONSTRAINT pk_system_setting_users_roles PRIMARY KEY (user_uuid, role_uuid),
    CONSTRAINT fk_system_setting_users_roles_users FOREIGN KEY (user_uuid)
        REFERENCES system_setting.users (uuid),
    CONSTRAINT fk_system_setting_users_roles_roles FOREIGN KEY (role_uuid)
        REFERENCES system_setting.roles (uuid)
);

CREATE SCHEMA map;
CREATE TABLE map.maps
(
    uuid UUID         NOT NULL DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_map_map PRIMARY KEY (uuid),
    CONSTRAINT uk_map_map_name UNIQUE (name)
);

CREATE TABLE map.points_groups
(
    uuid UUID         NOT NULL DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_map_points_map PRIMARY KEY (uuid),
    CONSTRAINT uk_map_points_group_name UNIQUE (name)
);

CREATE TABLE map.points
(
    uuid              UUID         NOT NULL DEFAULT gen_random_uuid(),
    points_group_uuid UUID         NOT NULL,
    text              VARCHAR      NULL,
    x                 VARCHAR(255) NOT NULL,
    y                 VARCHAR(255) NOT NULL,
    CONSTRAINT pk_map_points PRIMARY KEY (uuid),
    CONSTRAINT fk_map_points_points_group FOREIGN KEY (points_group_uuid) REFERENCES map.points_groups (uuid) ON DELETE CASCADE
);

CREATE TABLE map.maps_points_groups
(
    map_uuid          UUID NOT NULL,
    points_group_uuid UUID NOT NULL,
    CONSTRAINT pk_map_maps_points_groups PRIMARY KEY (map_uuid, points_group_uuid),
    CONSTRAINT fk_map_maps_points_groups_maps FOREIGN KEY (map_uuid) REFERENCES map.maps (uuid) ON DELETE CASCADE,
    CONSTRAINT fk_map_maps_points_groups_points_groups FOREIGN KEY (points_group_uuid) REFERENCES map.points_groups (uuid) ON DELETE CASCADE
);