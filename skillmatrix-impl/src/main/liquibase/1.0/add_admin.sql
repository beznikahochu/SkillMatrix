INSERT INTO users (login, password)
VALUES ('admin', '$2a$05$caCIRY7U9bhNAsn7ccY3Vuu03RMXU6QCwGnhw3jFq.z6apbkQAL5q');
/*
login: admin
password: admin
*/

INSERT INTO users_roles (user_id, role_id) VALUES (
(SELECT id FROM users WHERE users.login = 'admin'),
(SELECT id FROM roles WHERE roles.name = 'USER')
);

INSERT INTO users_roles (user_id, role_id) VALUES (
(SELECT id FROM users WHERE users.login = 'admin'),
(SELECT id FROM roles WHERE roles.name = 'MANAGER')
);

INSERT INTO users_roles (user_id, role_id) VALUES (
(SELECT id FROM users WHERE users.login = 'admin'),
(SELECT id FROM roles WHERE roles.name = 'ADMIN')
);

