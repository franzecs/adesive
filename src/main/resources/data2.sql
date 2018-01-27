/*
-- Query: select * from grupo
LIMIT 0, 1000

-- Date: 2017-08-13 12:36
*/
INSERT INTO `grupo` (`codigo`,`descricao`,`nome`) VALUES (1,'usuário master','ROLE_MASTER');
INSERT INTO `grupo` (`codigo`,`descricao`,`nome`) VALUES (2,'Clientes','ROLE_CLIENTES');
INSERT INTO `grupo` (`codigo`,`descricao`,`nome`) VALUES (3,'Administradores locais','ROLE_ADMLOC');

INSERT INTO `usuario` (`codigo`,`funcional`,`nome`,`senha`,`setor`) VALUES (1,'65795-6','FRANCISCO JOSÉ ','$2a$10$lZSTJbc3VWzRLHh2bSosdORNuE.pVR9oe/6I02wTHCM.IANuH7yAi','IKTUS');

INSERT INTO `usuario_grupo` (`codigo`,`usuario_id`,`grupo_id`) VALUES (1,1,1);