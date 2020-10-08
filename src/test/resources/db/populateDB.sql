DELETE
FROM map.maps_points_groups;
DELETE
FROM map.points_groups;
DELETE
FROM map.maps;
DELETE
FROM map.points_groups;
DELETE
FROM system_setting.users_roles;
DELETE
FROM system_setting.roles;
DELETE
FROM system_setting.users;

INSERT INTO system_setting.roles (uuid, name)
VALUES ('1782f915-4d0d-4e66-81d4-e1846e7dddf5', 'ROLE_ADMIN');

--1233211
INSERT INTO system_setting.users(uuid, username, password, registered, active)
VALUES ('bf326830-9703-4ffa-9bd5-78897dabb46b', 'user',
        '$2y$12$xCJAPWcGM.jl.w71q9ZTWuBGjARdfgJbfbXCmoMN0/1VNn6mGpNjS', '2020-10-07 10:00', true);

INSERT INTO map.maps (uuid, name)
VALUES ('0b37c9df-7bbe-483b-a851-a8f5e83fbb0f', 'Карта точек стоянки');
INSERT INTO map.maps (uuid, name)
VALUES ('cf35844f-a49f-4075-8ea5-a093ca01945a', 'Карта интересных мест');
INSERT INTO map.maps (uuid, name)
VALUES ('d9c872a3-85f2-47a8-a1ea-410a6b2d11b1', 'Просто обновленная карта');

INSERT INTO map.points_groups (uuid, name)
VALUES ('811fcacc-5403-434b-83a4-9162861bb90c', 'Просто группа');
INSERT INTO map.points_groups (uuid, name)
VALUES ('ad1f4342-d60b-4b66-aafe-ef2e2716cc85', 'Непросто группа');

INSERT INTO map.maps_points_groups (map_uuid, points_group_uuid)
VALUES ('d9c872a3-85f2-47a8-a1ea-410a6b2d11b1', '811fcacc-5403-434b-83a4-9162861bb90c');
INSERT INTO map.maps_points_groups (map_uuid, points_group_uuid)
VALUES ('0b37c9df-7bbe-483b-a851-a8f5e83fbb0f', 'ad1f4342-d60b-4b66-aafe-ef2e2716cc85');
INSERT INTO map.maps_points_groups (map_uuid, points_group_uuid)
VALUES ('cf35844f-a49f-4075-8ea5-a093ca01945a', 'ad1f4342-d60b-4b66-aafe-ef2e2716cc85');

INSERT INTO map.points (uuid, points_group_uuid, text, x, y)
VALUES ('adac3f47-0b5c-47ae-9e91-51735fcc8a2d', '811fcacc-5403-434b-83a4-9162861bb90c', 'Просто точка', '1', '1');
INSERT INTO map.points (uuid, points_group_uuid, text, x, y)
VALUES ('8f29eda8-797b-4ac8-ae46-59b3ef91f45e', '811fcacc-5403-434b-83a4-9162861bb90c', 'Просто точка 55', '55', '55');