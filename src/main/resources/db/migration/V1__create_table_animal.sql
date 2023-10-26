-- adocoes.animal definition

CREATE TABLE `animal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_entrada` datetime(6) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `data_saida` datetime(6) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `status` enum('ADOTADO','AVALIACAO','DISPONIVEL','RETORNADO') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;