-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 19 juin 2020 à 09:15
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP :  7.2.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `esprit`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id` int(11) NOT NULL,
  `nom_categorie` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id`, `nom_categorie`, `description`) VALUES
(4, 'Emploie', 'Offre d\'emploiessss'),
(9, 'Emploie', 'offre de logement'),
(28, 'sfsd', 'sdf');

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `detail` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `categories`
--

INSERT INTO `categories` (`id`, `nom`, `detail`) VALUES
(1, 'vbn', 'ghj'),
(2, 'rghjk', 'rtyu'),
(3, 'fghjklm', 'dfghj');

-- --------------------------------------------------------

--
-- Structure de la table `chauffeur`
--

CREATE TABLE `chauffeur` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `num_permis` int(11) NOT NULL,
  `num_telephone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `chauffeur`
--

INSERT INTO `chauffeur` (`id`, `nom`, `prenom`, `num_permis`, `num_telephone`) VALUES
(147, 'fgh', 'fgh', 2145, 28015650);

-- --------------------------------------------------------

--
-- Structure de la table `demande`
--

CREATE TABLE `demande` (
  `id` int(11) NOT NULL,
  `demandeur_id` int(11) DEFAULT NULL,
  `donmateriel_id` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  `etat` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `demande`
--

INSERT INTO `demande` (`id`, `demandeur_id`, `donmateriel_id`, `date`, `etat`) VALUES
(1, 2, 1, '2020-02-25', 0),
(2, 2, 1, '2020-02-25', 0),
(3, 2, 3, '2020-02-26', 0),
(4, 2, 4, '2020-02-26', 0),
(5, 2, 1, '2020-02-26', 0);

-- --------------------------------------------------------

--
-- Structure de la table `donfinancier`
--

CREATE TABLE `donfinancier` (
  `id` int(11) NOT NULL,
  `secteur_id` int(11) DEFAULT NULL,
  `donneur_id` int(11) DEFAULT NULL,
  `montant` double NOT NULL,
  `rib` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `donfinancier`
--

INSERT INTO `donfinancier` (`id`, `secteur_id`, `donneur_id`, `montant`, `rib`) VALUES
(1, 2, 2, 500, 12345678),
(2, 2, 2, 700, 22334455);

-- --------------------------------------------------------

--
-- Structure de la table `donmateriel`
--

CREATE TABLE `donmateriel` (
  `id` int(11) NOT NULL,
  `secteur_id` int(11) DEFAULT NULL,
  `donneur_id` int(11) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nombre` int(11) NOT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `donmateriel`
--

INSERT INTO `donmateriel` (`id`, `secteur_id`, `donneur_id`, `type`, `image`, `nombre`, `title`, `description`) VALUES
(1, 2, 2, 'Vetements', 'b4efdddd2bd3ab74e062daabf2350d92.png', 9, 'Pullovers', 'azertyui'),
(2, 2, NULL, 'Meuble', 'b0cd560ceff4dc1a492d692f204612a6.png', 20, 'Couvertures', 'dfghjn'),
(3, 2, NULL, 'Vetements', 'cd666658996bd4afb1968baf3677aac9.png', 8, 'dsfg', 'DQSFVSFDG'),
(4, 2, 2, 'Livres', '9122840dcd9e151f85de677c2cde6c1d.png', 20, 'sqdfdf', 'qsdfqsdf'),
(5, 3, 2, 'Vetements', 'c006daba1da4055d60e2b80ddf616543.jpeg', 20, 'Pullovers', 'DQSFVSFDG');

-- --------------------------------------------------------

--
-- Structure de la table `events`
--

CREATE TABLE `events` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dateDebut` date NOT NULL,
  `dateFin` date NOT NULL,
  `nbPlaces` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `lieu` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `photo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `photo_updated_at` datetime DEFAULT NULL,
  `rate` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `events`
--

INSERT INTO `events` (`id`, `nom`, `dateDebut`, `dateFin`, `nbPlaces`, `description`, `lieu`, `photo`, `photo_updated_at`, `rate`) VALUES
(42, 'Refuge Hope', '2020-06-10', '2020-06-13', 496, 'Refuges need us', 'Amesterdam', 'C:\\Users\\jihed hajlaoui\\Desktop\\amine.PNG', '2020-06-10 16:31:40', 8),
(43, 'women help', '2021-07-08', '2021-10-12', 697, 'women need help', 'Berlin', 'C:\\Users\\jihed hajlaoui\\Desktop\\amine.PNG', '2020-06-10 16:33:20', 5);

-- --------------------------------------------------------

--
-- Structure de la table `events_sponsor`
--

CREATE TABLE `events_sponsor` (
  `events_id` int(11) NOT NULL,
  `sponsor_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `fos_user`
--

CREATE TABLE `fos_user` (
  `id` int(11) NOT NULL,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `fos_user`
--

INSERT INTO `fos_user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`) VALUES
(1, 'test', 'test', 'test@test.com', 'test@test.com', 1, NULL, '$2y$13$XaDbHDCx4U4H3pvU0oESHOqphUjT4/R9X8kwkcZtxyKGVpAIp2/P6', NULL, NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_REFUGE\";}'),
(2, 'jihed', 'jihed', 'jihed.hajlaoui@esprit.tn', 'jihed.hajlaoui@esprit.tn', 1, NULL, '$2y$13$ISsTeQPAA/R/zn2t2ZLDfOf8v.VaA0crOLfINozBWjeqYOGVYtfkC', '2020-06-18 18:27:22', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}'),
(3, 'issam', 'issam', 'issam.benmoussa@esprit.tn', 'issam.benmoussa@esprit.tn', 1, NULL, '$2y$13$IjwFduLsSQGdkHBqZ/UDDOZFoANy8yFt4AYYT2DviDhwLQJV86Pky', NULL, NULL, NULL, 'a:0:{}'),
(4, 'issam1', 'issam1', 'issam.benmoussa96@gmail.com', 'issam.benmoussa96@gmail.com', 1, NULL, '$2y$13$kyhJKeD1DnC9aHxh88k30.eJNxY353HkOCHshDvYjaiu88McaZtsO', NULL, NULL, NULL, 'a:0:{}'),
(5, 'amjed', 'amjed', 'amjed.bouallegui@gmail.com', 'amjed.bouallegui@gmail.com', 1, NULL, '$2y$13$WgQu3KRffigiwQeCGzYiFesbNmpjpR5UpNLEPG84TTRlN.JFaLptW', '2020-02-25 15:24:07', NULL, NULL, 'a:0:{}'),
(6, 'zack', 'zack', 'zack@gmail.com', 'zack@gmail.com', 1, NULL, '$2y$13$1mSComWkOST0IzVNtRJXE.do/epCcbjCj1hzj67xKwubsJpzQa61q', '2020-02-25 15:28:39', NULL, NULL, 'a:0:{}'),
(7, 'hamza', 'hamza', 'hamza@gmail.com', 'hamza@gmail.com', 1, NULL, '$2y$13$H8EZ/g.4PBPzflV7GFYb8eCix4XID.pgLt2fa/apF.0KZvoOsLnXy', '2020-02-26 01:57:27', NULL, NULL, 'a:0:{}'),
(8, 'admin', 'admin', 'admin@test.com', 'admin@test.com', 1, NULL, '$2y$13$KXbGhXYLP.XoUub/pFEcE.GlDT4nS3eIkk9cqCg/Y/5un4ITpKD/C', NULL, NULL, NULL, 'a:1:{i:0;s:16:\"ROLE_SUPER_ADMIN\";}'),
(9, 'test2', 'test2', 'test2@test.com', 'test2@test.com', 1, NULL, '$2y$13$YKzg/UDJdYcPs/xYUJV2ee6eUSabonqPzpn9ezZAoDELRZiAwHiQu', NULL, NULL, NULL, 'a:0:{}');

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `thread_id` int(11) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `body` longtext COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `message`
--

INSERT INTO `message` (`id`, `thread_id`, `sender_id`, `body`, `created_at`) VALUES
(1, 1, 6, 'qsdfghvcvbjnb', '2020-02-25 21:26:25');

-- --------------------------------------------------------

--
-- Structure de la table `message_metadata`
--

CREATE TABLE `message_metadata` (
  `id` int(11) NOT NULL,
  `message_id` int(11) DEFAULT NULL,
  `participant_id` int(11) DEFAULT NULL,
  `is_read` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `message_metadata`
--

INSERT INTO `message_metadata` (`id`, `message_id`, `participant_id`, `is_read`) VALUES
(1, 1, 2, 0),
(2, 1, 6, 1);

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci NOT NULL,
  `icon` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `route` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `route_parameters` longtext COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '(DC2Type:array)',
  `notification_date` datetime NOT NULL,
  `seen` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `notification`
--

INSERT INTO `notification` (`id`, `title`, `description`, `icon`, `route`, `route_parameters`, `notification_date`, `seen`) VALUES
(1, 'Nouvelle Demande', 'Une nouvelle demande sur azertyui', NULL, 'dashboard', 'N;', '2020-02-25 13:45:14', 0),
(2, 'Nouvelle Demande', 'Une nouvelle demande sur azertyui', NULL, 'dashboard', 'N;', '2020-02-25 13:49:24', 0),
(3, 'Nouvelle Demande', 'Une nouvelle demande sur DQSFVSFDG', NULL, 'dashboard', 'N;', '2020-02-26 12:02:13', 0),
(4, 'Nouvelle Demande', 'Une nouvelle demande sur qsdfqsdf', NULL, 'dashboard', 'N;', '2020-02-26 12:05:29', 0),
(5, 'Nouvelle Demande', 'Une nouvelle demande sur azertyui', NULL, 'dashboard', 'N;', '2020-02-26 12:09:21', 0);

-- --------------------------------------------------------

--
-- Structure de la table `opportunite`
--

CREATE TABLE `opportunite` (
  `id` int(11) NOT NULL,
  `categorie_id` int(11) DEFAULT NULL,
  `addresse` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nb_place` int(11) DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description_opportunite` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `etat` tinyint(1) NOT NULL DEFAULT 1,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `opportunite`
--

INSERT INTO `opportunite` (`id`, `categorie_id`, `addresse`, `nb_place`, `image`, `date`, `description_opportunite`, `etat`, `id_user`) VALUES
(203, 4, 'nourja3efer@gmail.com', 2979, 'C:\\xampp\\tmp\\php3570.tmp', '2024-01-01', 'chambre s +3', 1, 0),
(247, 9, 'aaa', 3, 'C:\\Users\\jihed hajlaoui\\Desktop\\gang.jpg', '2020-06-17', 'aaa', 1, NULL),
(248, 9, 'aa', 333, 'C:\\Users\\jihed hajlaoui\\Desktop\\gang.jpg', '2020-06-24', 'aa', 1, NULL),
(252, 9, 'dsfsdf', 33, 'C:\\Users\\jihed hajlaoui\\Desktop\\solidairtyy.jpg', '2020-06-17', 'qsdqsd', 1, NULL),
(253, 9, 'sdfsdf', 3333, 'C:\\Users\\jihed hajlaoui\\Desktop\\solidairtyy.jpg', '2020-06-24', 'qsdqsdqsd', 0, NULL),
(255, 9, 'jihed.hajlaoui@esprit.tn', 2, 'b376a0800ba5b3ee9bfc5d34744b4bfc.jpeg', '12-15-2020', 'offre de logement', 0, NULL),
(256, 4, 'jihed.hajlaoui@esprit.tn', 1, '09aaa02ed817bdfbbe70dade995c8bb5.jpeg', '12-15-2020', 'chambre s +3', 0, NULL),
(258, 4, 'jihed.hajlaoui@esprit.tn', 11, '4234abc150d82753fa6233b239fd3437.png', '12/09/2020', 'emplois ailleurs', 1, NULL),
(259, 4, 'test', 5, 'C:\\Users\\jihed hajlaoui\\Desktop\\amine.PNG', '2020-06-10', 'test', 0, NULL),
(260, 4, 'tt', 3, 'C:\\Users\\jihed hajlaoui\\Desktop\\p3.jpg', '2020-06-26', 'tt', 0, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `participation`
--

CREATE TABLE `participation` (
  `id` int(11) NOT NULL,
  `id_Events` int(11) DEFAULT NULL,
  `id_User` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `postulation`
--

CREATE TABLE `postulation` (
  `id` int(11) NOT NULL,
  `opportunite_id` int(11) DEFAULT NULL,
  `date` varchar(255) COLLATE utf8_unicode_ci DEFAULT current_timestamp(),
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `postulation`
--

INSERT INTO `postulation` (`id`, `opportunite_id`, `date`, `user_id`) VALUES
(36, 203, 'Sat Jun 13 00:11:01 WAT 2020', 7),
(37, 203, 'Sat Jun 13 00:12:08 WAT 2020', 7),
(38, 203, 'Sat Jun 13 00:12:32 WAT 2020', 7),
(39, 203, 'Sat Jun 13 00:12:49 WAT 2020', 7),
(45, 203, NULL, 7),
(54, 203, 'Mon Jun 15 20:02:32 WAT 2020', 7),
(55, 203, 'Mon Jun 15 20:02:37 WAT 2020', 7),
(56, 203, 'Mon Jun 15 20:02:38 WAT 2020', 7),
(58, 203, 'Tue Jun 16 14:37:11 WAT 2020', 7),
(59, 247, NULL, 7),
(60, 247, NULL, 7),
(61, 258, NULL, 7),
(62, 203, 'Fri Jun 19 03:12:17 WAT 2020', 7);

-- --------------------------------------------------------

--
-- Structure de la table `rating`
--

CREATE TABLE `rating` (
  `id` int(11) NOT NULL,
  `rate` int(11) NOT NULL,
  `id_Event` int(11) DEFAULT NULL,
  `id_User` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `rating`
--

INSERT INTO `rating` (`id`, `rate`, `id_Event`, `id_User`) VALUES
(52, 5, 42, 2),
(53, 9, 43, 2),
(55, 7, 42, 2),
(56, 6, 42, 2),
(57, 9, 42, 2),
(0, 9, 42, 2);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id` int(11) NOT NULL,
  `categorie_id` int(11) DEFAULT NULL,
  `titre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id`, `categorie_id`, `titre`, `description`, `etat`, `date`) VALUES
(1, 2, 'fghjk', 'rtyu', 'annuler', '2015-01-01'),
(3, 1, 'azer', 'test', '2', '2015-01-01'),
(4, 1, 'urgent', '111', 'non traite', '2020-01-01'),
(5, 1, 'eya', 'maryem', 'non traite', '2015-01-01'),
(6, 1, 'echec', 'fefe', 'non traite', '2015-01-01'),
(7, 1, 'okoko', 'ddffffefe', 'non traite', '2015-01-01'),
(8, 1, 'fraude', 'fraydee', 'annuler', '2020-01-01'),
(44, 2, 'fr', 'testfr', 'resolu', '2020-06-18'),
(255, 1, 'fr', 'testfr', 'resolu', '2020-06-18');

-- --------------------------------------------------------

--
-- Structure de la table `reclamations`
--

CREATE TABLE `reclamations` (
  `id` int(11) NOT NULL,
  `categorie_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `titre` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `etat` smallint(6) NOT NULL DEFAULT 1,
  `date` date NOT NULL,
  `dtype` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `reclamations`
--

INSERT INTO `reclamations` (`id`, `categorie_id`, `user_id`, `titre`, `description`, `etat`, `date`, `dtype`) VALUES
(4, NULL, 1, 'test mat', 'test', 1, '2020-06-13', 'reclamationtransport'),
(5, NULL, 1, 'dsfkslj', 'test', 1, '2020-06-14', 'reclamation'),
(6, NULL, 1, 'ezhkjh', 'dskh', 1, '2020-06-14', 'reclamationtransport'),
(7, NULL, 1, 'kcdhsckdhj', 'cdskjh', 1, '2020-06-14', 'reclamationfraude'),
(8, NULL, 3, 'test user2', 'test', 1, '2020-06-14', 'reclamation'),
(9, NULL, 1, 'ttt', 'ttt', 1, '2020-06-19', 'reclamation'),
(10, NULL, 1, 'ttt', 'ttt', 1, '2020-06-19', 'reclamation'),
(11, NULL, 1, 'tt', 'tt', 1, '2020-06-19', 'reclamation'),
(12, NULL, 1, 'tt', 'tt', 1, '2020-06-19', 'reclamation'),
(13, NULL, 1, 'tt', 'tt', 1, '2020-06-19', 'reclamation'),
(14, NULL, 1, 'tt', 'tt', 1, '2020-06-19', 'reclamation'),
(15, NULL, 1, '0', 'tt', 1, '2020-06-19', 'fraude'),
(16, NULL, 1, '0', 't', 1, '2020-06-19', 'fraude'),
(17, NULL, 1, '0', 'tt', 1, '2020-06-19', 'fraude'),
(18, NULL, 1, '0', 'tttt', 1, '2020-06-19', 'fraude'),
(19, NULL, 1, '0', 'tt', 1, '2020-06-19', 'fraude'),
(20, NULL, 1, '0', 'tttt', 1, '2020-06-19', 'transport');

-- --------------------------------------------------------

--
-- Structure de la table `reclamation_fraude`
--

CREATE TABLE `reclamation_fraude` (
  `id` int(11) NOT NULL,
  `chauffeur_id` int(11) NOT NULL,
  `rec_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `reclamation_fraude`
--

INSERT INTO `reclamation_fraude` (`id`, `chauffeur_id`, `rec_id`) VALUES
(1, 1, 1),
(3, 2, 13),
(4, 3, 14),
(5, 1, 16),
(6, 147, 17),
(7, 147, 18),
(8, 147, 19);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation_transport`
--

CREATE TABLE `reclamation_transport` (
  `id` int(11) NOT NULL,
  `matricule` varchar(20) NOT NULL,
  `rec_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `reclamation_transport`
--

INSERT INTO `reclamation_transport` (`id`, `matricule`, `rec_id`) VALUES
(3, 'mat02', 11),
(4, '325TUN1478', 15),
(5, '147', 20);

-- --------------------------------------------------------

--
-- Structure de la table `secteur`
--

CREATE TABLE `secteur` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `adresse` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pays` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `secteur`
--

INSERT INTO `secteur` (`id`, `nom`, `adresse`, `pays`) VALUES
(2, 'Ariana essoghra', 'L\'Ariana', 'Tunisie'),
(3, 'Test', 'test', 'test'),
(4, 'aa', 'aa', 'aa');

-- --------------------------------------------------------

--
-- Structure de la table `sponsor`
--

CREATE TABLE `sponsor` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `photo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `photo_updated_at` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `sponsor`
--

INSERT INTO `sponsor` (`id`, `nom`, `photo`, `photo_updated_at`, `description`) VALUES
(3, 'Coca Cola', NULL, NULL, 'qqqqqqqqqqqqq'),
(8, 'k', NULL, NULL, 'hajlaoui');

-- --------------------------------------------------------

--
-- Structure de la table `thread`
--

CREATE TABLE `thread` (
  `id` int(11) NOT NULL,
  `created_by_id` int(11) DEFAULT NULL,
  `subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `is_spam` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `thread`
--

INSERT INTO `thread` (`id`, `created_by_id`, `subject`, `created_at`, `is_spam`) VALUES
(1, 6, 'dfgh', '2020-02-25 21:26:25', 0);

-- --------------------------------------------------------

--
-- Structure de la table `thread_metadata`
--

CREATE TABLE `thread_metadata` (
  `id` int(11) NOT NULL,
  `thread_id` int(11) DEFAULT NULL,
  `participant_id` int(11) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `last_participant_message_date` datetime DEFAULT NULL,
  `last_message_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `thread_metadata`
--

INSERT INTO `thread_metadata` (`id`, `thread_id`, `participant_id`, `is_deleted`, `last_participant_message_date`, `last_message_date`) VALUES
(1, 1, 2, 0, NULL, '2020-02-25 21:26:25'),
(2, 1, 6, 0, '2020-02-25 21:26:25', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `transport`
--

CREATE TABLE `transport` (
  `id` int(11) NOT NULL,
  `Moyen` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Destination` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `matricule_id` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `x` double DEFAULT NULL,
  `y` double DEFAULT NULL,
  `nb` int(11) NOT NULL,
  `Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `transport`
--

INSERT INTO `transport` (`id`, `Moyen`, `Destination`, `matricule_id`, `rating`, `x`, `y`, `nb`, `Date`) VALUES
(1, 'bus', 'tunis', 147, 4, 36.809465, 10.175941, 10, '2015-01-01'),
(2, 'dfg', 'gafsa', 147, 0, 34.432126, 8.7819, 5, '2020-01-01');

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

CREATE TABLE `vehicule` (
  `id_user` int(255) NOT NULL,
  `first_Name` varchar(255) DEFAULT NULL,
  `last_Name` varchar(255) DEFAULT NULL,
  `user_Name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` int(11) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `CIN` int(11) DEFAULT NULL,
  `account_Date` varchar(255) DEFAULT NULL,
  `Image_user` varchar(255) DEFAULT NULL,
  `age` int(70) DEFAULT NULL,
  `don` varchar(255) DEFAULT 'financier',
  `level` varchar(255) DEFAULT 'level_1',
  `usernameCanonical` varchar(255) DEFAULT NULL,
  `emailCanonical` varchar(255) DEFAULT NULL,
  `enabled` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `plainPassword` varchar(255) DEFAULT NULL,
  `lastLogin` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `id_` int(11) DEFAULT 5
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `vehicule`
--

INSERT INTO `vehicule` (`id_user`, `first_Name`, `last_Name`, `user_Name`, `password`, `email`, `phone_number`, `gender`, `CIN`, `account_Date`, `Image_user`, `age`, `don`, `level`, `usernameCanonical`, `emailCanonical`, `enabled`, `salt`, `plainPassword`, `lastLogin`, `role`, `id_`) VALUES
(1, 'eya', 'mejri', 'eyaadmin', '111', 'charf@esprit.tn', 54319018, 'Homme', 7227174, NULL, 'homme3.jpg', 22, 'materiel', 'level_1', NULL, NULL, 'null', 'null', NULL, 'null', 'admin', 6),
(20, 'test', 'test', 'test', 'test', 'eee@esprit.tn', 2222, 'Homme', 3333, '2020-02-22', 'homme4.jpg', 22, 'materiel', 'level_1', '1', 'NULL', '1', 'NULL', 'NULL', 'null', 'refuge', 1),
(25, 'foulen', 'test', 'foulen', '123', 'foulen.benfoulen@esprit.tn', 12345678, 'Homme', 12345678, '2020-04-08', 'homme3.jpg', 20, 'materiel', 'level_1', NULL, NULL, 'null', 'null', NULL, 'null', 'admin', 12),
(42, 'ella', 'mejri', 'eyamejri', '123', 'eyamejri@esprit.tn', 12345678, 'Femme', 12345678, '2020-06-17', 'null', 22, 'null', 'level_1', 'null', 'null', 'null', 'null', 'null', 'null', '', 5),
(37, 'zaki', 'bouzidi', 'zaki', '123', 'zaki@esprit.tn', 25369741, 'Femme', 25147963, '2020-06-11', 'homme3.jpg', 20, 'materiel', 'level_3', NULL, NULL, 'null', 'null', NULL, 'null', 'volentaire', 5);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `chauffeur`
--
ALTER TABLE `chauffeur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `demande`
--
ALTER TABLE `demande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_2694D7A595A6EE59` (`demandeur_id`),
  ADD KEY `IDX_2694D7A5D12572CB` (`donmateriel_id`);

--
-- Index pour la table `donfinancier`
--
ALTER TABLE `donfinancier`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_4A6981409F7E4405` (`secteur_id`),
  ADD KEY `IDX_4A6981409789825B` (`donneur_id`);

--
-- Index pour la table `donmateriel`
--
ALTER TABLE `donmateriel`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_42373E659F7E4405` (`secteur_id`),
  ADD KEY `IDX_42373E659789825B` (`donneur_id`);

--
-- Index pour la table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `events_sponsor`
--
ALTER TABLE `events_sponsor`
  ADD PRIMARY KEY (`events_id`,`sponsor_id`),
  ADD KEY `IDX_D7EDB0CE9D6A1065` (`events_id`),
  ADD KEY `IDX_D7EDB0CE12F7FB51` (`sponsor_id`);

--
-- Index pour la table `fos_user`
--
ALTER TABLE `fos_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_957A647992FC23A8` (`username_canonical`),
  ADD UNIQUE KEY `UNIQ_957A6479A0D96FBF` (`email_canonical`),
  ADD UNIQUE KEY `UNIQ_957A6479C05FB297` (`confirmation_token`);

--
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_B6BD307FE2904019` (`thread_id`),
  ADD KEY `IDX_B6BD307FF624B39D` (`sender_id`);

--
-- Index pour la table `message_metadata`
--
ALTER TABLE `message_metadata`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_4632F005537A1329` (`message_id`),
  ADD KEY `IDX_4632F0059D1C3019` (`participant_id`);

--
-- Index pour la table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `opportunite`
--
ALTER TABLE `opportunite`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_97889F98BCF5E72D` (`categorie_id`);

--
-- Index pour la table `participation`
--
ALTER TABLE `participation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_AB55E24F721CE791` (`id_Events`),
  ADD KEY `IDX_AB55E24FA6816575` (`id_User`);

--
-- Index pour la table `postulation`
--
ALTER TABLE `postulation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_DA7D4E9B80FBB128` (`opportunite_id`),
  ADD KEY `IDX_DA7D4E9BA76ED395` (`user_id`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_CE606404BCF5E72D` (`categorie_id`);

--
-- Index pour la table `reclamations`
--
ALTER TABLE `reclamations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_CE606404BCF5E72D` (`categorie_id`),
  ADD KEY `IDX_CE606404A76ED395` (`user_id`);

--
-- Index pour la table `reclamation_fraude`
--
ALTER TABLE `reclamation_fraude`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_fraude_chauf` (`chauffeur_id`),
  ADD KEY `fk_fraude` (`rec_id`);

--
-- Index pour la table `reclamation_transport`
--
ALTER TABLE `reclamation_transport`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_trans` (`rec_id`);

--
-- Index pour la table `secteur`
--
ALTER TABLE `secteur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `sponsor`
--
ALTER TABLE `sponsor`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `thread`
--
ALTER TABLE `thread`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_31204C83B03A8386` (`created_by_id`);

--
-- Index pour la table `thread_metadata`
--
ALTER TABLE `thread_metadata`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_40A577C8E2904019` (`thread_id`),
  ADD KEY `IDX_40A577C89D1C3019` (`participant_id`);

--
-- Index pour la table `transport`
--
ALTER TABLE `transport`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_66AB212E9AAADC05` (`matricule_id`);

--
-- Index pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT pour la table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `demande`
--
ALTER TABLE `demande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `donfinancier`
--
ALTER TABLE `donfinancier`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `donmateriel`
--
ALTER TABLE `donmateriel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `events`
--
ALTER TABLE `events`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT pour la table `fos_user`
--
ALTER TABLE `fos_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `message_metadata`
--
ALTER TABLE `message_metadata`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `opportunite`
--
ALTER TABLE `opportunite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=261;

--
-- AUTO_INCREMENT pour la table `participation`
--
ALTER TABLE `participation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `postulation`
--
ALTER TABLE `postulation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=256;

--
-- AUTO_INCREMENT pour la table `reclamations`
--
ALTER TABLE `reclamations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `reclamation_fraude`
--
ALTER TABLE `reclamation_fraude`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `reclamation_transport`
--
ALTER TABLE `reclamation_transport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `secteur`
--
ALTER TABLE `secteur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `sponsor`
--
ALTER TABLE `sponsor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `thread`
--
ALTER TABLE `thread`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `thread_metadata`
--
ALTER TABLE `thread_metadata`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `transport`
--
ALTER TABLE `transport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `vehicule`
--
ALTER TABLE `vehicule`
  MODIFY `id_user` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `demande`
--
ALTER TABLE `demande`
  ADD CONSTRAINT `FK_2694D7A595A6EE59` FOREIGN KEY (`demandeur_id`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `FK_2694D7A5D12572CB` FOREIGN KEY (`donmateriel_id`) REFERENCES `donmateriel` (`id`);

--
-- Contraintes pour la table `donfinancier`
--
ALTER TABLE `donfinancier`
  ADD CONSTRAINT `FK_4A6981409789825B` FOREIGN KEY (`donneur_id`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `FK_4A6981409F7E4405` FOREIGN KEY (`secteur_id`) REFERENCES `secteur` (`id`);

--
-- Contraintes pour la table `donmateriel`
--
ALTER TABLE `donmateriel`
  ADD CONSTRAINT `FK_42373E659789825B` FOREIGN KEY (`donneur_id`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `FK_42373E659F7E4405` FOREIGN KEY (`secteur_id`) REFERENCES `secteur` (`id`);

--
-- Contraintes pour la table `events_sponsor`
--
ALTER TABLE `events_sponsor`
  ADD CONSTRAINT `FK_D7EDB0CE12F7FB51` FOREIGN KEY (`sponsor_id`) REFERENCES `sponsor` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_D7EDB0CE9D6A1065` FOREIGN KEY (`events_id`) REFERENCES `events` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK_B6BD307FE2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`),
  ADD CONSTRAINT `FK_B6BD307FF624B39D` FOREIGN KEY (`sender_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `message_metadata`
--
ALTER TABLE `message_metadata`
  ADD CONSTRAINT `FK_4632F005537A1329` FOREIGN KEY (`message_id`) REFERENCES `message` (`id`),
  ADD CONSTRAINT `FK_4632F0059D1C3019` FOREIGN KEY (`participant_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `opportunite`
--
ALTER TABLE `opportunite`
  ADD CONSTRAINT `FK1` FOREIGN KEY (`categorie_id`) REFERENCES `categorie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `participation`
--
ALTER TABLE `participation`
  ADD CONSTRAINT `FK_AB55E24F721CE791` FOREIGN KEY (`id_Events`) REFERENCES `events` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_AB55E24FA6816575` FOREIGN KEY (`id_User`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `postulation`
--
ALTER TABLE `postulation`
  ADD CONSTRAINT `FK_DA7D4E9B80FBB128` FOREIGN KEY (`opportunite_id`) REFERENCES `opportunite` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_DA7D4E9BA76ED395` FOREIGN KEY (`user_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `FK_CE606404BCF5E72D` FOREIGN KEY (`categorie_id`) REFERENCES `categories` (`id`);

--
-- Contraintes pour la table `thread`
--
ALTER TABLE `thread`
  ADD CONSTRAINT `FK_31204C83B03A8386` FOREIGN KEY (`created_by_id`) REFERENCES `fos_user` (`id`);

--
-- Contraintes pour la table `thread_metadata`
--
ALTER TABLE `thread_metadata`
  ADD CONSTRAINT `FK_40A577C89D1C3019` FOREIGN KEY (`participant_id`) REFERENCES `fos_user` (`id`),
  ADD CONSTRAINT `FK_40A577C8E2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`);

--
-- Contraintes pour la table `transport`
--
ALTER TABLE `transport`
  ADD CONSTRAINT `FK_66AB212E9AAADC05` FOREIGN KEY (`matricule_id`) REFERENCES `chauffeur` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
