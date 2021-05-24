
INSERT INTO RapportVisite (vis_matricule, rap_date_visite, rap_bilan, pra_num, rap_coef_confiance, rap_lu, numero_motif, date_redaction) VALUES('b16', '2020-05-14', 'Satisfaisant', 3, 1,1, 1, '2021-14-27' );
INSERT INTO RapportVisite (vis_matricule, rap_date_visite, rap_bilan, pra_num, rap_coef_confiance, rap_lu, numero_motif, date_redaction) VALUES('b16', '2020-05-14', 'Tout est ok', 10, 2,1, 4, '2021-04-27' );
INSERT INTO RapportVisite (vis_matricule, rap_date_visite, rap_bilan, pra_num, rap_coef_confiance, rap_lu, numero_motif, date_redaction) VALUES('b16', '2020-05-14', 'Echantillon proposé', 2, 3,1, 4, '2021-13-27' );
INSERT INTO RapportVisite (vis_matricule, rap_date_visite, rap_bilan, pra_num, rap_coef_confiance, rap_lu, numero_motif, date_redaction) VALUES('b16', '2020-05-14', 'Praticien agréable', 6, 3,1, 3, '2021-04-27' );
INSERT INTO RapportVisite (vis_matricule, rap_date_visite, rap_bilan, pra_num, rap_coef_confiance, rap_lu, numero_motif, date_redaction) VALUES('b16', '2020-05-14', 'Satisfaisant', 7, 3,1, 2, '2021-04-27' );
INSERT INTO RapportVisite (vis_matricule, rap_date_visite, rap_bilan, pra_num, rap_coef_confiance, rap_lu, numero_motif, date_redaction) VALUES('b16', '2020-05-14', 'Visite effectué', 3, 2,1, 1, '2021-02-27' );
INSERT INTO RapportVisite (vis_matricule, rap_date_visite, rap_bilan, pra_num, rap_coef_confiance, rap_lu, numero_motif, date_redaction) VALUES('b16', '2020-05-14', 'Praticien hésitant', 2, 2,1, 2, '2021-12-27' );


| vis_matricule | rap_num | rap_date_visite | rap_bilan                               | pra_num | rap_coef_confiance | rap_lu | numero_motif | date_redaction |
+---------------+---------+-----------------+-----------------------------------------+---------+--------------------+--------+--------------+----------------+
| a131          |       3 | 2020-08-14      | Satisfaisant                            |       2 |                  3 |      1 |            1 | 2021-02-27     |
| b16           |       8 | 2020-02-03      | Praticien hésitant...                   |      10 |                  1 |      0 |            1 | 2021-02-27     |
| b16           |       9 | 2020-05-03      | Tout est ok                             |      12 |                  5 |      1 |            1 | 2021-02-27     |
| b25           |       6 | 2020-01-02      | Praticien agréable                      |       4 |                  4 |      1 |            1 | 2021-02-27     |
| b34           |       1 | 2020-05-03      | Tout est ok                             |       3 |                  4 |      0 |            1 | 2021-02-27     |
| b34           |       7 | 2020-05-29      | Echantillon proposé                     |       9 |                  2 |      0 |            1 | 2021-02-27     |
| c14           |       2 | 2020-06-03      | Praticien agréable                      |      15 |                  5 |      0 |            1 | 2021-02-27     |
| d13           |       4 | 2020-10-03      | Visite effectuée , en attente réponse   |       3 |                  4 |      0 |            1 | 2021-02-27     |



CREATE TABLE `RapportVisite` (
  `vis_matricule` varchar(20) NOT NULL DEFAULT '',
  `rap_num` int(11) NOT NULL auto_increment,
  `rap_date_visite` date NOT NULL,
  `rap_bilan` varchar(510) DEFAULT '',
  `pra_num` int(11) DEFAULT NULL,
  `rap_coef_confiance` int(11) NOT NULL,
  `rap_lu` int(11) NOT NULL,
  `numero_motif` int(11) NOT NULL DEFAULT '0',
  `date_redaction` date NOT NULL,
  PRIMARY KEY (`rap_num`),
  KEY `FK_RAPPORT_VISITE_PRATICIEN` (`pra_num`),
  KEY `FK_Motif_RapVisite` (`numero_motif`),
  CONSTRAINT `FK_Motif_RapVisite` FOREIGN KEY (`numero_motif`) REFERENCES `Motif` (`numero_motif`),
  CONSTRAINT `FK_RAPPORT_VISITE_PRATICIEN` FOREIGN KEY (`pra_num`) REFERENCES `Praticien` (`pra_num`),
  CONSTRAINT `FK_RAPPORT_VISITE_VISITEUR` FOREIGN KEY (`vis_matricule`) REFERENCES `Visiteur` (`vis_matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
