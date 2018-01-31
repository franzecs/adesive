INSERT INTO empresa (codigo, img, nomeimg, cnpj, dataadesao, email, endereco, nome, observacao, telefone, cliente, fornecedor) VALUES (1,null,'','616.498.643-53','2018-01-08','franzecs@gmail.com','Av. de Contorno Oeste, 405 - bloco 30- apto 22A - Nova Metrópole - Caucaia','Ikytus Sistemas LTDA','asdasdakslçkdalçskdçaklçsdklaçksdçlakçlsdkaçlksdçlakslçd','(85)98895-1038',NULL,NULL);

INSERT INTO grupo (codigo, descricao, nome) VALUES (1,'usuário master','ROLE_MASTERROOT'),(2,'Clientes','ROLE_CLIENTES'),(3,'Administradores locais','ROLE_ADMLOC');

INSERT INTO usuario (codigo, funcional, nome, senha, setor, empresa) VALUES (1,'65795-6','FRANCISCO JOSÉ ','$2a$10$YuXtpqOhq4v.9Gm0faB7/eQo34vib5E8i6uOYGVi3oI27P88oDnL.','ADMINISTRATIVO',1);

INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (1,1);