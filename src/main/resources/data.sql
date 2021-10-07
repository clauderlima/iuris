INSERT IGNORE INTO `permissao` (`id`, `nome_permissao`) VALUES
	(1, 'CLIENTE_INCLUIR'),
	(2, 'CLIENTE_VER'),
	(3, 'CONTRATO_INCLUIR'),
	(4, 'CONTRATO_VER'),
	(5, 'GRUPO_INCLUIR'),
	(6, 'GRUPO_VER'),
	(7, 'PERMISSAO_INCLUIR'),
	(8, 'PERMISSAO_VER'),
	(9, 'USUARIO_INCLUIR'),
	(10, 'USUARIO_VER'),
	(11, 'FUNCIONARIO_INCLUIR'),
	(12, 'FUNCIONARIO_VER'),
	(13, 'HISTORICO_INCLUIR'),
	(14, 'HISTORICO_VER'),
	(15, 'TITULO_INCLUIR'),
	(16, 'TITULO_VER'),
	(17, 'ESCRITORIO_INCLUIR'),
	(18, 'ESCRITORIO_VER'),
	(19, 'CLIENTE_STATUS');

INSERT IGNORE INTO `grupo` (`id`, `nome_grupo`) VALUES
	(1, 'Administrador'),
	(2, 'Advogado'),
	(3, 'Gerente'),
	(4, 'Consultor'),
	(5, 'Telefonista'),
	(6, 'Cliente');
	
INSERT IGNORE INTO `grupo_permissao` (`grupo_id`, `permissao_id`) VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(1, 4),
	(1, 5),
	(1, 6),
	(1, 7),
	(1, 8),
	(1, 9),
	(1, 10),
	(1, 11),
	(1, 12),
	(1, 13),
	(1, 14),
	(1, 15),
	(1, 16),
	(1, 17),
	(1, 18);

INSERT IGNORE INTO `pessoa` (`id`, `cargo_funcionario`, `cpf`, `data_nascimento`, `email`, `estado_civil`, `nome`, `orgao_expedidor`, `profissao`, `rg`,  `sexo`, `tipo_pessoa`, `contrato_id`, `endereco_id`, `escritorio_id`, `telefone_celular_id`, `telefone_fixo_id`) VALUES
	(1, 3, '504.502.981-91', '1972-11-13', 'clauderlima@gmail.com', 'Solteiro', 'Clauder Costa de Lima', 'SSP-DF', 'Desenvolvedor', '1.244.183', 'Masculino', 1, NULL, NULL, NULL, 1, NULL);

INSERT IGNORE INTO `telefone` (`id`, `numero`) VALUES
	(1, '(61) 99841-1973');

INSERT IGNORE INTO `usuario` (`id`, `ativo`, `senha`, `pessoa_id`) VALUES
	(1, NULL, '$2a$10$LdAGAQm0aYHNWhczz9G7QewDv.J9lTm3gs59UhcLD7pUg375xefcm', 1);

INSERT IGNORE INTO `usuario_grupo` (`usuario_id`, `grupo_id`) VALUES
	(1, 1);
