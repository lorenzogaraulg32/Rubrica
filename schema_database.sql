CREATE DATABASE IF NOT EXISTS `rubrica_db`;
USE `rubrica_db`;

DROP TABLE IF EXISTS `persone`;
DROP TABLE IF EXISTS `utenti`;

CREATE TABLE `utenti` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_username` (`username`)
);

CREATE TABLE `persone` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `cognome` varchar(100) NOT NULL,
  `indirizzo` varchar(100) NOT NULL,
  `telefono` varchar(30) NOT NULL,
  `eta` int NOT NULL,
  `utente_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `utente_id` (`utente_id`),
  CONSTRAINT `fk_persone_utenti`
    FOREIGN KEY (`utente_id`) REFERENCES `utenti` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

INSERT INTO `utenti` (`id`, `username`, `password`) VALUES
(1, 'mario', '1234'),
(2, 'luigi', '1234');

INSERT INTO `persone` (`id`, `nome`, `cognome`, `indirizzo`, `telefono`, `eta`, `utente_id`) VALUES
(1, 'Mario', 'Rossi', 'Via Roma 12', '3331234567', 34, 1),
(2, 'Luigi', 'Bianchi', 'Corso Torino 45', '3209876543', 28, 1),
(3, 'Elena', 'Conti', 'Corso Francia 101', '3395556677', 37, 1),
(4, 'Luca', 'Moretti', 'Via Dante 6', '3284445556', 30, 2),
(5, 'Sara', 'Gallo', 'Via Po 18', '3457778889', 23, 2),
(6, 'Paolo', 'Marino', 'Viale Europa 77', '3661239876', 46, 2),
(7, 'Francesca', 'Greco', 'Via Cavour 14', '3429090909', 39, 2);