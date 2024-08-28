insert into curso (id, descricao) values (1, 'Matemática');
insert into curso (id, descricao) values (2, 'Lingua Portuguesa');
insert into curso (id, descricao) values (3, 'Ciências');
insert into curso (id, descricao) values (4, 'Geografia');
insert into curso (id, descricao) values (5, 'Historia');
insert into curso (id, descricao) values (6, 'Artes');
insert into curso (id, descricao) values (7, 'Educação Física');
insert into curso (id, descricao) values (8, 'Filosofia');
insert into curso (id, descricao) values (9, 'Sociologia');
insert into curso (id, descricao) values (10, 'Física');
insert into curso (id, descricao) values (11, 'Quimica');
insert into curso (id, descricao) values (12, 'Inglês');
insert into curso (id, descricao) values (13, 'Biologia');
insert into curso (id, descricao) values (14, 'Redação');
insert into curso (id, descricao) values (15, 'Espanhol');

insert into professor (id, nome, cpf, data_entrada, curso_id, tipo) values (1, 'Marcia Abreu', '137.880.850-96', '2010/01/01', 1, 'EM');
insert into professor (id, nome, cpf, data_entrada, curso_id, tipo) values (2, 'Adriana Montenegro', '137.880.850-96', '2012/07/04', 2, 'EM');
insert into professor (id, nome, cpf, data_entrada, curso_id, tipo) values (3, 'Eduardo Aviz', '137.880.850-96', '2008/12/03', 3, 'Fundamental');
insert into professor (id, nome, cpf, data_entrada, curso_id, tipo) values (4, 'Mônica Gomes', '137.880.850-96', '2008/12/08', 5, 'Fundamental');
insert into professor (id, nome, cpf, data_entrada, curso_id, tipo) values (5, 'Marisa Pimenta', '137.880.850-96', '2007/11/21', 4, 'EM');

INSERT INTO aluno (id, nome, data_entrada, tipo) VALUES (1, 'Arthus Gabriel', '2023-01-01', 'EM');
INSERT INTO aluno (id, nome, data_entrada, tipo) VALUES (2, 'Giuberto Abreu', '2022-01-04', 'EM');
INSERT INTO aluno (id, nome, data_entrada, tipo) VALUES (3, 'Carla Maria', '2020-12-03', 'Fundamental');
INSERT INTO aluno (id, nome, data_entrada, tipo) VALUES (4, 'Ana Beatriz', '2019-12-08', 'Fundamental');
INSERT INTO aluno (id, nome, data_entrada, tipo) VALUES (5, 'Emma Suki', '2019-11-21', 'EM');

INSERT INTO aluno_curso (aluno_id, curso_id) VALUES (1, 1);
INSERT INTO aluno_curso (aluno_id, curso_id) VALUES (1, 3);
INSERT INTO aluno_curso (aluno_id, curso_id) VALUES (1, 5);

INSERT INTO aluno_curso (aluno_id, curso_id) VALUES (2, 2);
INSERT INTO aluno_curso (aluno_id, curso_id) VALUES (2, 5);
INSERT INTO aluno_curso (aluno_id, curso_id) VALUES (2, 8);

INSERT INTO aluno_curso (aluno_id, curso_id) VALUES (3, 3);
INSERT INTO aluno_curso (aluno_id, curso_id) VALUES (3, 2);
INSERT INTO aluno_curso (aluno_id, curso_id) VALUES (3, 4);

INSERT INTO aluno_curso (aluno_id, curso_id) VALUES (4, 5);
INSERT INTO aluno_curso (aluno_id, curso_id) VALUES (4, 2);
INSERT INTO aluno_curso (aluno_id, curso_id) VALUES (4, 4);

INSERT INTO aluno_curso (aluno_id, curso_id) VALUES (5, 4);
INSERT INTO aluno_curso (aluno_id, curso_id) VALUES (5, 10);
INSERT INTO aluno_curso (aluno_id, curso_id) VALUES (5, 12);
