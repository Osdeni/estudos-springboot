INSERT INTO usuario (nome, email, senha) VALUES ('Osdeni', 'osdeni@gmail.com', '$2y$12$G1dhGzoZBRBJ8UQme4V4leng7kaWVtAqkyP2DuRrQ9gL5siZKJy9K');

INSERT INTO perfil (nome) VALUES ('ADMIN');

INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (1, 1);

INSERT INTO clientes (estado, cidade) VALUES ('SC', 'Criciúma');
INSERT INTO clientes (estado, cidade) VALUES ('SC', 'Içara');
INSERT INTO clientes (estado, cidade) VALUES ('SC', 'Forquilhinha');
INSERT INTO clientes (estado, cidade) VALUES ('SC', 'Araranguá');

INSERT INTO cliente_dados (tipo, chave, valor, cliente_Id) VALUES ('CONTATO', 'Celular', '(DDD) NNNNN-NNNN', 1);
INSERT INTO cliente_dados (tipo, chave, valor, cliente_Id) VALUES ('CONTATO', 'E-mail', 'osdeni@gmail.com', 1);
INSERT INTO cliente_dados (tipo, chave, valor, cliente_Id) VALUES ('ACESSO', 'SSH', 'ssh root@ip.com', 1);
INSERT INTO cliente_dados (tipo, chave, valor, cliente_Id) VALUES ('ACESSO', 'Redis', 'AUTH 123456', 1);