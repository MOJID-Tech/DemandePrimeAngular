-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  sam. 04 juil. 2020 à 22:21
-- Version du serveur :  10.1.36-MariaDB
-- Version de PHP :  7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `remuneration_database2`
--

-- --------------------------------------------------------

--
-- Structure de la table `appartient`
--

CREATE TABLE `appartient` (
  `id` bigint(20) NOT NULL,
  `datedebut` date DEFAULT NULL,
  `datefin` date DEFAULT NULL,
  `estrespo` bit(1) NOT NULL,
  `equipe_id` bigint(20) DEFAULT NULL,
  `salarie_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `appartient`
--

INSERT INTO `appartient` (`id`, `datedebut`, `datefin`, `estrespo`, `equipe_id`, `salarie_id`) VALUES
(1, '2019-01-01', NULL, b'1', 1, 4),
(2, '2019-01-01', NULL, b'0', 1, 1),
(3, '2019-02-01', NULL, b'0', 1, 2),
(5, '2019-01-01', NULL, b'1', 3, 4),
(6, '2019-01-01', NULL, b'1', 4, 5),
(7, '2019-01-01', NULL, b'0', 4, 6),
(10, '2019-08-01', NULL, b'0', 4, 3),
(11, '2019-03-01', NULL, b'0', 3, 10),
(12, '2019-01-01', NULL, b'0', 3, 11),
(13, '2019-01-01', NULL, b'0', 3, 12);

-- --------------------------------------------------------

--
-- Structure de la table `budget`
--

CREATE TABLE `budget` (
  `id` bigint(20) NOT NULL,
  `consommation` double NOT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `montant` double NOT NULL,
  `pourcentage` double NOT NULL,
  `societe_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `budget`
--

INSERT INTO `budget` (`id`, `consommation`, `date_debut`, `date_fin`, `montant`, `pourcentage`, `societe_id`) VALUES
(1, 0, '2020-01-01', NULL, 1000, 50, 1),
(35, 0, '2020-06-25', NULL, 600, 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `budget_departement`
--

CREATE TABLE `budget_departement` (
  `id` bigint(20) NOT NULL,
  `consommation` double NOT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `montant` double NOT NULL,
  `pourcentage` double NOT NULL,
  `budget_id` bigint(20) DEFAULT NULL,
  `departement_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `budget_departement`
--

INSERT INTO `budget_departement` (`id`, `consommation`, `date_debut`, `date_fin`, `montant`, `pourcentage`, `budget_id`, `departement_id`) VALUES
(1, 900, '2020-01-01', '2020-06-25', 1000, 0.5, 1, 1),
(36, 0, NULL, '2020-07-02', 600, 0.3, 35, 2);

-- --------------------------------------------------------

--
-- Structure de la table `budget_equipe`
--

CREATE TABLE `budget_equipe` (
  `id` int(11) NOT NULL,
  `consommation_horsmanager` double NOT NULL,
  `consommation_manager` double NOT NULL,
  `date_debut` date DEFAULT NULL,
  `datefin` date DEFAULT NULL,
  `montant_horsmanager` double NOT NULL,
  `montant_manager` double NOT NULL,
  `pourcentage_horsmanager` double NOT NULL,
  `pourcentage_manager` double NOT NULL,
  `budgetdepartement_id` bigint(20) DEFAULT NULL,
  `equipe_id` bigint(20) DEFAULT NULL,
  `date_fin` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `budget_equipe`
--

INSERT INTO `budget_equipe` (`id`, `consommation_horsmanager`, `consommation_manager`, `date_debut`, `datefin`, `montant_horsmanager`, `montant_manager`, `pourcentage_horsmanager`, `pourcentage_manager`, `budgetdepartement_id`, `equipe_id`, `date_fin`) VALUES
(41, 0, 0, '2020-06-28', '2020-06-28', 200, 400, 0.3333333333333333, 0.6666666666666666, 1, 1, NULL),
(42, 0, 0, '2020-06-28', '2020-06-28', 100, 200, 0.3333333333333333, 0.6666666666666666, 1, 3, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `demande`
--

CREATE TABLE `demande` (
  `id` int(11) NOT NULL,
  `pourcentage_contribution` double DEFAULT NULL,
  `date_debut` datetime DEFAULT NULL,
  `date_fin` datetime DEFAULT NULL,
  `date_validation` datetime DEFAULT NULL,
  `montant_brut` double DEFAULT NULL,
  `montant_net` double DEFAULT NULL,
  `prime_finale` double DEFAULT NULL,
  `prime_manager` double DEFAULT NULL,
  `prime_maximale` double DEFAULT NULL,
  `prime_pdg` double DEFAULT NULL,
  `validedg` bit(1) DEFAULT NULL,
  `validem` bit(1) DEFAULT NULL,
  `salarie_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `demande`
--

INSERT INTO `demande` (`id`, `pourcentage_contribution`, `date_debut`, `date_fin`, `date_validation`, `montant_brut`, `montant_net`, `prime_finale`, `prime_manager`, `prime_maximale`, `prime_pdg`, `validedg`, `validem`, `salarie_id`) VALUES
(1, 87, '2020-06-14 01:22:00', '2020-06-18 01:59:46', '2020-06-18 01:59:46', 0, 258, 100, 100, 277.78, 0, b'1', b'1', 1),
(3, 0.167, '2020-06-14 01:55:27', NULL, NULL, 0, 895, 0, 120, 277.78, 100, b'1', b'0', 1),
(6, 0.169, '2020-06-14 23:46:15', '2020-06-18 01:55:25', NULL, NULL, 1993, 0, 0, 277.78, 0, b'1', b'1', 1),
(9, 0.16666666666666669, '2020-06-17 22:45:19', NULL, NULL, NULL, 1298, 0, 0, 477.77777777777777, 0, b'0', b'0', 1),
(11, 0.16666666666666669, '2020-06-17 22:46:48', NULL, NULL, NULL, 1998, 0, 0, 277.77777777777777, 0, b'0', b'1', 1),
(13, 0, '2020-06-18 01:50:22', '2020-06-18 21:34:25', NULL, NULL, 50000, 0, 0, 0, 0, b'1', b'1', 2),
(43, 0, '2020-06-30 14:35:31', NULL, NULL, NULL, 1000, 0, 0, 0, 0, b'0', b'0', 3);

-- --------------------------------------------------------

--
-- Structure de la table `departement`
--

CREATE TABLE `departement` (
  `id` bigint(20) NOT NULL,
  `nom_depar` varchar(255) DEFAULT NULL,
  `societe_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `departement`
--

INSERT INTO `departement` (`id`, `nom_depar`, `societe_id`) VALUES
(1, 'Informatique', 1),
(2, 'Vente', 1),
(3, 'Administration', 1),
(4, 'Médias et Communication', 1),
(5, 'Production', 1);

-- --------------------------------------------------------

--
-- Structure de la table `equipe`
--

CREATE TABLE `equipe` (
  `id` bigint(20) NOT NULL,
  `nom_equipe` varchar(255) DEFAULT NULL,
  `departement_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `equipe`
--

INSERT INTO `equipe` (`id`, `nom_equipe`, `departement_id`) VALUES
(1, 'EQ3 INF', 1),
(2, 'Design', 4),
(3, 'EQ1 INF', 1),
(4, 'EQ2 INF', 1),
(5, 'EQ1 Vente', 2),
(6, 'EQ2 Vente', 2);

-- --------------------------------------------------------

--
-- Structure de la table `etat`
--

CREATE TABLE `etat` (
  `id` int(11) NOT NULL,
  `etdm` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `etat`
--

INSERT INTO `etat` (`id`, `etdm`) VALUES
(1, 'En Cour de traitement'),
(2, 'Validée par le Manager'),
(3, 'Revalidée par le Manager'),
(4, 'Refusée par le Manager'),
(5, 'Validée par le DG'),
(6, 'Premier refus par DG'),
(7, 'Deuxième refus par DG');

-- --------------------------------------------------------

--
-- Structure de la table `etat_demande`
--

CREATE TABLE `etat_demande` (
  `id` int(11) NOT NULL,
  `date_etat` datetime DEFAULT NULL,
  `demande_id` int(11) DEFAULT NULL,
  `etat_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `etat_demande`
--

INSERT INTO `etat_demande` (`id`, `date_etat`, `demande_id`, `etat_id`) VALUES
(2, '2020-06-14 01:22:00', 1, 1),
(4, '2020-06-14 01:55:27', 3, 1),
(7, '2020-06-14 23:46:15', 6, 1),
(10, '2020-06-17 22:45:19', 9, 1),
(12, '2020-06-17 22:46:48', 11, 1),
(14, '2020-06-18 01:50:22', 13, 1),
(15, '2020-06-18 01:55:14', 1, 2),
(16, '2020-06-18 01:55:22', 3, 2),
(17, '2020-06-18 01:55:25', 6, 4),
(18, '2020-06-18 01:59:46', 1, 5),
(19, '2020-06-18 02:00:17', 3, 6),
(20, '2020-06-18 02:12:29', 3, 3),
(22, '2020-06-18 19:42:37', 6, 2),
(23, '2020-06-18 19:42:41', 3, 3),
(25, '2020-06-18 19:42:42', 3, 3),
(28, '2020-06-18 21:34:25', 13, 4),
(29, '2020-06-18 21:36:00', 3, 3),
(30, '2020-06-18 22:50:25', 11, 2),
(44, '2020-06-30 14:35:31', 43, 1);

-- --------------------------------------------------------

--
-- Structure de la table `fonction`
--

CREATE TABLE `fonction` (
  `id` bigint(20) NOT NULL,
  `nom_fonction` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `fonction`
--

INSERT INTO `fonction` (`id`, `nom_fonction`) VALUES
(1, 'Développeur Back-end'),
(2, 'Technicien'),
(3, 'DBA'),
(4, 'Développeur Back-FrontEnd'),
(5, 'Concepteur Informatique '),
(7, 'Vendeur'),
(8, 'Comptable'),
(9, 'Responsable de Marketing');

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(45),
(45),
(45),
(45),
(45),
(45),
(45),
(45),
(45),
(45),
(45),
(45),
(45),
(45),
(45),
(45),
(45);

-- --------------------------------------------------------

--
-- Structure de la table `responsable`
--

CREATE TABLE `responsable` (
  `id` bigint(20) NOT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `departement_id` bigint(20) DEFAULT NULL,
  `salarie_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `nom_role` varchar(255) DEFAULT NULL,
  `code_role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `nom_role`, `code_role`) VALUES
(2, 'Manager', '447\r\n'),
(3, 'Admin', '589'),
(5, 'DG', '584'),
(6, 'User', '78');

-- --------------------------------------------------------

--
-- Structure de la table `salarie`
--

CREATE TABLE `salarie` (
  `id` bigint(20) NOT NULL,
  `datenaissance_salarie` date DEFAULT NULL,
  `emailsalarie` varchar(255) DEFAULT NULL,
  `nomsalarie` varchar(255) DEFAULT NULL,
  `prenom_salarie` varchar(255) DEFAULT NULL,
  `tel_salarie` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `salarie`
--

INSERT INTO `salarie` (`id`, `datenaissance_salarie`, `emailsalarie`, `nomsalarie`, `prenom_salarie`, `tel_salarie`) VALUES
(1, '1990-01-01', 'Mojid.Soukaina@gmail.com', 'Mojid', 'Soukaina', NULL),
(2, '1998-07-02', 'imane98@gmail.com', 'Najahi', 'imane', NULL),
(3, '1987-04-10', 'Charkaoui-Salim@gmail.com', 'Charkaoui', 'Salim', NULL),
(4, '1998-06-01', 'ELjazouli.Omar@gmail.com', 'ELjazouli', 'Omar', NULL),
(5, '1990-05-25', 'Aroua.Hadoui@gmail.com', 'Hadoui', 'Aroua', NULL),
(6, '1997-04-01', 'Assafi.najwa@gmail.com', 'Assafi', 'Najwa', NULL),
(7, '1987-10-02', 'Qaroui.Oussam@gmail.com', 'Quaroui', 'Oussama', NULL),
(8, '1990-10-02', 'Erregui.Taha@gmail.com', 'Erregui', 'Taha', NULL),
(9, '1991-07-02', 'Ahmed.Moubarik@gmail.com', 'Moubarik', 'Ahmed', NULL),
(10, '1990-10-02', 'mojid.fati@gmail.com', 'Boulmane', 'Jamila', NULL),
(11, '1990-10-02', 'Erregui.Taha@gmail.com', 'Erregui', 'Taha', NULL),
(12, '1991-07-02', 'Ahmed.Moubarik@gmail.com', 'Moubarik', 'Ahmed', NULL),
(13, '1998-10-06', 'mojid.fati@gmail.com', 'Mojid', 'Fatima zahra', '0628484673');

-- --------------------------------------------------------

--
-- Structure de la table `salarie_fonction`
--

CREATE TABLE `salarie_fonction` (
  `id` bigint(20) NOT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `fonction_id` bigint(20) DEFAULT NULL,
  `salarie_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `societe`
--

CREATE TABLE `societe` (
  `id_societe` bigint(20) NOT NULL,
  `date_lancement` date DEFAULT NULL,
  `quartier` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `societe`
--

INSERT INTO `societe` (`id_societe`, `date_lancement`, `quartier`, `type`) VALUES
(1, '2017-08-15', 'casa', 'Developement');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `active` bit(1) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salarie_id` bigint(20) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `active`, `login`, `password`, `salarie_id`, `token`) VALUES
(1, b'1', 'Manager', '{bcrypt}$2a$10$.Mix7m5bCeSJTLP2P5sZTubTe3hAUZib2EWxx72J39Hws0zFtQ0L2', 13, 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNYW5hZ2VyIiwicm9sZXMiOlsiTWFuYWdlciJdLCJpYXQiOjE1OTM4OTE2MzksImV4cCI6MTU5NDc1NTYzOX0.PPEHX3zPLko0_c4B67mJG4Fx3gZC_fZnzRBl7mCE-gc'),
(2, b'1', 'User', '{bcrypt}$2a$10$KDQI0oy.WcA4Z4ZPQinWWe7sLTdVcxDXyy1aPjIvXjBo1/Ps9cNnG', 10, 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIiwicm9sZXMiOlsiVXNlciJdLCJpYXQiOjE1OTMwMDU5OTAsImV4cCI6MTU5Mzg2OTk5MH0.Zf2faXM-SHYi0TgVllYMlJKOb960AHZAnwxT810Lac8'),
(3, b'1', 'Directeur', '{bcrypt}$2a$10$KDQI0oy.WcA4Z4ZPQinWWe7sLTdVcxDXyy1aPjIvXjBo1/Ps9cNnG', 3, 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJEaXJlY3RldXIiLCJyb2xlcyI6WyJBZG1pbiIsIkRHIiwiTWFuYWdlciJdLCJpYXQiOjE1OTM4ODQ4MjYsImV4cCI6MTU5NDc0ODgyNn0.uuEaQecAco5pRqjrlcG4iMo9COmfjkC5UU1rbGTPGgw'),
(32, b'0', 'saraerr', '{bcrypt}$2a$10$vYmZGreq1CwzQZ8PxgMs5.A8NVyfM.OLas2whZQM73WAMTmfQp5OS', 1, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `user_role`
--

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user_role`
--

INSERT INTO `user_role` (`id`, `role_id`, `user_id`) VALUES
(1, 2, 1),
(2, 3, 3),
(4, 5, 3),
(6, 6, 2),
(9, 2, 3),
(33, 3, 32);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `appartient`
--
ALTER TABLE `appartient`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKj7t95t36493pfn6qlp5us0squ` (`equipe_id`),
  ADD KEY `FKgh0ym6wwxv4dk69v2wohxy456` (`salarie_id`);

--
-- Index pour la table `budget`
--
ALTER TABLE `budget`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKc7ba16k0hvdpcdsh8np6c15fm` (`societe_id`);

--
-- Index pour la table `budget_departement`
--
ALTER TABLE `budget_departement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKemyts5olulkf9uh8uc1lq26tf` (`budget_id`),
  ADD KEY `FK6gd10lkipiaaokphyua951j4v` (`departement_id`);

--
-- Index pour la table `budget_equipe`
--
ALTER TABLE `budget_equipe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmh4ur7kows5w6kd5kkosmpjti` (`budgetdepartement_id`),
  ADD KEY `FK4d6mu0mi1mawjvkeiks2qiywu` (`equipe_id`);

--
-- Index pour la table `demande`
--
ALTER TABLE `demande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9018swbll1b3et1a2vd2kme3n` (`salarie_id`);

--
-- Index pour la table `departement`
--
ALTER TABLE `departement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK19rqlav1x7b02sp05j4py2v11` (`societe_id`);

--
-- Index pour la table `equipe`
--
ALTER TABLE `equipe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs9ennof8jcccfe8fdaisf83t8` (`departement_id`);

--
-- Index pour la table `etat`
--
ALTER TABLE `etat`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `etat_demande`
--
ALTER TABLE `etat_demande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrh351ua2o3c7fjgdntx494yj5` (`demande_id`),
  ADD KEY `FKfg99uctjf9idaycmnsolbn3ap` (`etat_id`);

--
-- Index pour la table `fonction`
--
ALTER TABLE `fonction`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `responsable`
--
ALTER TABLE `responsable`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5muujdmb3ht0fca899fq4owi4` (`departement_id`),
  ADD KEY `FKix123guq7w39mot4cwjpvu5h0` (`salarie_id`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `salarie`
--
ALTER TABLE `salarie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `salarie_fonction`
--
ALTER TABLE `salarie_fonction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjhbdeaa62eqt5jmqlwgdf0byt` (`fonction_id`),
  ADD KEY `FKn2ja2s5vg3dekutci8fofvktu` (`salarie_id`);

--
-- Index pour la table `societe`
--
ALTER TABLE `societe`
  ADD PRIMARY KEY (`id_societe`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrknl8mdmxnvcpx1pcqxvrbbp5` (`salarie_id`);

--
-- Index pour la table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  ADD KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `appartient`
--
ALTER TABLE `appartient`
  ADD CONSTRAINT `FKgh0ym6wwxv4dk69v2wohxy456` FOREIGN KEY (`salarie_id`) REFERENCES `salarie` (`id`),
  ADD CONSTRAINT `FKj7t95t36493pfn6qlp5us0squ` FOREIGN KEY (`equipe_id`) REFERENCES `equipe` (`id`);

--
-- Contraintes pour la table `budget`
--
ALTER TABLE `budget`
  ADD CONSTRAINT `FKc7ba16k0hvdpcdsh8np6c15fm` FOREIGN KEY (`societe_id`) REFERENCES `societe` (`id_societe`);

--
-- Contraintes pour la table `budget_departement`
--
ALTER TABLE `budget_departement`
  ADD CONSTRAINT `FK6gd10lkipiaaokphyua951j4v` FOREIGN KEY (`departement_id`) REFERENCES `departement` (`id`),
  ADD CONSTRAINT `FKemyts5olulkf9uh8uc1lq26tf` FOREIGN KEY (`budget_id`) REFERENCES `budget` (`id`);

--
-- Contraintes pour la table `budget_equipe`
--
ALTER TABLE `budget_equipe`
  ADD CONSTRAINT `FK4d6mu0mi1mawjvkeiks2qiywu` FOREIGN KEY (`equipe_id`) REFERENCES `equipe` (`id`),
  ADD CONSTRAINT `FKmh4ur7kows5w6kd5kkosmpjti` FOREIGN KEY (`budgetdepartement_id`) REFERENCES `budget_departement` (`id`);

--
-- Contraintes pour la table `demande`
--
ALTER TABLE `demande`
  ADD CONSTRAINT `FK9018swbll1b3et1a2vd2kme3n` FOREIGN KEY (`salarie_id`) REFERENCES `salarie` (`id`);

--
-- Contraintes pour la table `departement`
--
ALTER TABLE `departement`
  ADD CONSTRAINT `FK19rqlav1x7b02sp05j4py2v11` FOREIGN KEY (`societe_id`) REFERENCES `societe` (`id_societe`);

--
-- Contraintes pour la table `equipe`
--
ALTER TABLE `equipe`
  ADD CONSTRAINT `FKs9ennof8jcccfe8fdaisf83t8` FOREIGN KEY (`departement_id`) REFERENCES `departement` (`id`);

--
-- Contraintes pour la table `etat_demande`
--
ALTER TABLE `etat_demande`
  ADD CONSTRAINT `FKfg99uctjf9idaycmnsolbn3ap` FOREIGN KEY (`etat_id`) REFERENCES `etat` (`id`),
  ADD CONSTRAINT `FKrh351ua2o3c7fjgdntx494yj5` FOREIGN KEY (`demande_id`) REFERENCES `demande` (`id`);

--
-- Contraintes pour la table `responsable`
--
ALTER TABLE `responsable`
  ADD CONSTRAINT `FK5muujdmb3ht0fca899fq4owi4` FOREIGN KEY (`departement_id`) REFERENCES `departement` (`id`),
  ADD CONSTRAINT `FKix123guq7w39mot4cwjpvu5h0` FOREIGN KEY (`salarie_id`) REFERENCES `salarie` (`id`);

--
-- Contraintes pour la table `salarie_fonction`
--
ALTER TABLE `salarie_fonction`
  ADD CONSTRAINT `FKjhbdeaa62eqt5jmqlwgdf0byt` FOREIGN KEY (`fonction_id`) REFERENCES `fonction` (`id`),
  ADD CONSTRAINT `FKn2ja2s5vg3dekutci8fofvktu` FOREIGN KEY (`salarie_id`) REFERENCES `salarie` (`id`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKrknl8mdmxnvcpx1pcqxvrbbp5` FOREIGN KEY (`salarie_id`) REFERENCES `salarie` (`id`);

--
-- Contraintes pour la table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
