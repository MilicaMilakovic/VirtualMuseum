-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`MUSEUM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`MUSEUM` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(150) NOT NULL,
  `phone` VARCHAR(45) NULL,
  `city` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `image` VARCHAR(200) NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `latitude` DECIMAL(10,6) NULL,
  `longitude` DECIMAL(10,6) NULL,
  `description` MEDIUMTEXT NULL,
  `presentation` MEDIUMTEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TOUR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TOUR` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `museum_id` INT NOT NULL,
  `start` DATETIME NOT NULL,
  `duration` INT NOT NULL,
  `price` DECIMAL(5,2) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `ExhibitionID_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_EXHIBITION_MUSEUM_idx` (`museum_id` ASC) VISIBLE,
  CONSTRAINT `fk_EXHIBITION_MUSEUM`
    FOREIGN KEY (`museum_id`)
    REFERENCES `mydb`.`MUSEUM` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`USER` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `logged_in` TINYINT(1) NOT NULL DEFAULT 0,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `role` VARCHAR(5) NULL DEFAULT 'user',
  `token` VARCHAR(45) NULL,
  `approved` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idtable1_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TICKET`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TICKET` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `issue_date` DATETIME NULL,
  `tour_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_TICKET_EXHIBITION1_idx` (`tour_id` ASC) VISIBLE,
  INDEX `fk_TICKET_USER1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_TICKET_EXHIBITION1`
    FOREIGN KEY (`tour_id`)
    REFERENCES `mydb`.`TOUR` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TICKET_USER1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`USER` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CREDIT_CARD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CREDIT_CARD` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(16) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `expirationDate` VARCHAR(5) NOT NULL,
  `pin` VARCHAR(4) NOT NULL,
  `balance` DECIMAL(10,2) NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CREDIT CARD_USER1_idx` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE,
  CONSTRAINT `fk_CREDIT CARD_USER1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`USER` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TRANSACTION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TRANSACTION` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `card_id` INT NOT NULL,
  `amount` DECIMAL(5,2) NOT NULL,
  `timestamp` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_TRANSACTION_CREDIT_CARD1_idx` (`card_id` ASC) VISIBLE,
  CONSTRAINT `fk_TRANSACTION_CREDIT_CARD1`
    FOREIGN KEY (`card_id`)
    REFERENCES `mydb`.`CREDIT_CARD` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`LOG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`LOG` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `timestamp` DATETIME NULL,
  `online` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `mydb`.`MUSEUM`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (1, 'Louvre', 'Rue de Rivoli, 75001 Paris, France', '+33 1 40 20 50 50', 'Paris', 'France', 'Art museum and historic site', 'https://bit.ly/3jPUUpf', 1, 48.864824, 2.334595, 'The Louvre, or the Louvre Museum, is the world\'s largest art museum and a historic monument in Paris, France. A central landmark of the city, it is located on the Right Bank of the Seine in the city\'s 1st arrondissement. Approximately 38,000 objects from prehistory to the 21st century are exhibited over an area of 72,735 square metres. In 2017, the Louvre was the world\'s most visited art museum, receiving 8.1 million visitors.', 'https://www.youtube.com/embed/MufP60vnLnU');
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (2, 'Rijksmuseum', 'Museumstraat 1, 1071 XX Amsterdam, Netherlands', '+31 20 674 7000', 'Amsterdam', 'Netherlands', 'National museum, Art museum, History museum', 'https://bit.ly/3jJWjxG', 1, 52.360001, 4.885278, 'The Rijksmuseum is the museum of the Netherlands. Its world-famous masterworks from the Dutch Golden Age include the Milkmaid by Vermeer and Rembrandt\'s Night Watch. The Rijksmuseum itself is also a masterpiece. The collection is presented in a stunning building with amazing interior design. In 80 galleries 8,000 objects tell the story of 800 years of Dutch art and history, from the Middle Ages to Mondrian. Every year, over 2.5 million visitors travel through the ages and experience a feeling of beauty and sense of time.', 'https://www.youtube.com/embed/HL36lHVl4pA');
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (3, 'Vatican Museums', '00120 Vatican City', '+39 06 6988 4676', 'Vatican City', 'Italy', 'Art museum', 'https://bit.ly/37znTLe', 1, 41.906487, 12.453641, 'The Vatican Museums are the public museums of the Vatican City. They display works from the immense collection amassed by the Catholic Church and the papacy throughout the centuries, including several of the most renowned Roman sculptures and most important masterpieces of Renaissance art in the world. The museums contain roughly 70,000 works, of which 20,000 are on display, and currently employ 640 people who work in 40 different administrative, scholarly, and restoration departments.', 'https://www.youtube.com/embed/xg8SVfl40NU');
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (4, 'British Museum', 'Great Russell St, London WC1B 3DG, United Kingdom', '+44 20 7323 8299', 'London ', 'United Kingdom', 'Universal museum', 'https://bit.ly/3IoQlwa', 1, 51.518757, -0.126168, 'The British Museum’s remarkable collection spans over two million years of human history and culture. Over 6 million visitors every year experience the collection, including world-famous objects such as the Rosetta Stone, the Parthenon sculptures, and Egyptian mummies.', 'https://www.youtube.com/watch?v=UymdMZwF2kE');
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (5, 'Metropolitan Museum of Art', '1000 Fifth Avenue', '+1 212-535-7710', 'New York City', 'United States', 'Art museum', 'https://bit.ly/36agiT7', 1, 40.7794, -73.9631, 'The Met presents over 5,000 years of art from around the world for everyone to experience and enjoy. The Museum lives in three iconic sites in New York City—The Met Fifth Avenue, The Met Breuer, and The Met Cloisters. Millions of people also take part in The Met experience online.', 'https://www.youtube.com/watch?v=oNth9y0Yr_0');
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (6, 'National Museum of China', '16 E Chang\'an St, 前门 Dongcheng, China, 100051', '+86 10 6511 6400', 'Beijing', 'China', 'Art museum, history museum', 'https://bit.ly/3L1mPhU', 1, 39.905159, 116.400894, 'The National Museum of China flanks the eastern side of Tiananmen Square in Beijing, China. The museum\'s mission is to educate about the arts and history of China. It is directed by the Ministry of Culture and Tourism of the People\'s Republic of China.', 'https://www.youtube.com/watch?v=nZcthjgfEF4');
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (7, 'Musée d\'Orsay', '1 Rue de la Légion d\'Honneur, 75007 Paris, France', '+33 1 40 49 48 14', 'Paris', 'France', 'Art museum', 'https://bit.ly/3E4UIf8', 1, 48.85340118, 2.34859991, 'The history of the museum, of its building is quite unusual. In the centre of Paris on the banks of the Seine, opposite the Tuileries Gardens, the museum was installed in the former Orsay railway station, built for the Universal Exhibition of 1900. So the building itself could be seen as the first \\\"work of art\\\" in the Musee d\'Orsay, which displays collections of art from the period 1848 to 1914.', 'https://www.youtube.com/embed/9svKv8XnRQc');
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (8, 'Museo del Prado', 'C. de Ruiz de Alarcón, 23, 28014 Madrid, Spain', '+34 913 30 28 00', 'Madrid', 'Spain', 'Art museum', 'https://bit.ly/3NYI71R', 1, 40.402248, -3.710290, 'The Prado Museum, officially known as Museo Nacional del Prado, is the main Spanish national art museum, located in central Madrid. It is widely considered to house one of the world\'s finest collections of European art, \\ndating from the 12th century to the early 20th century, based on the former Spanish Royal Collection, and the single best collection of Spanish art. The Prado Museum is one of the most visited sites in the world, and is considered one of the greatest art museums in the world.', 'https://www.youtube.com/embed/KdQRjgvqxwg');
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (9, 'Russian Museum', 'Inzhenernaya St, 4, St Petersburg, Russia, 191186', '+7 812 595-42-48', 'Saint Petersburg', 'Russia', 'Art museum and Historic site', 'https://bit.ly/3MaPiC7', 1, 59.938742, 30.332385, 'The Russian Museum today is a unique depository of artistic treasures, a leading restoration center, an authoritative institute of academic research, a major educational center and the nucleus of a network of national museums of art.', 'https://www.youtube.com/watch?v=8R-ZdNE3AkI');
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (10, 'Uffizi Gallery', 'Piazzale degli Uffizi, 6, 50122 Firenze FI, Italy', '+39 055 294883', 'Firenze', 'Italy', 'Art museum, Design/Textile Museum', 'https://bit.ly/3Eha941', 1, 43.768639, 11.255214, 'The Uffizi was designed by Giorgio Vasari in 1560 for Cosimo I de\'Medici to house the Granducal Magistratures of Tuscany. Over time, the top floor loggia became an exhibition of the dynastic collection of ancient sculpture, artwork and artifacts.Moreover, the Gallery boasts an invaluable collection of ancient statues and busts from the Medici family, which adorns the corridors and consists of ancient Roman copies of lost Greek sculptures.', 'https://www.youtube.com/watch?v=7gLebmFJkLg');
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (11, 'Acropolis Museum', 'Dionysiou Areopagitou 15, Athina 117 42, Greece', '+30 21 0900 0900', 'Athens', 'Greece', 'Archaeological Museum', 'https://bit.ly/3xvNwaD', 1, 37.9677153958, 23.723754105, 'The Acropolis Museum is an archaeological site-specific museum, housing more than 3.000 famous artefacts from the Athenian Acropolis, the most significant sanctuary of the ancient city. Located in the historical area of Makriyianni, southeast of the Rock of the Acropolis, the Museum narrates the story of life on the Rock from prehistoric times until the end of Antiquity.', 'https://www.youtube.com/watch?v=49TsjfebAH0');
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (12, 'Tate Britain', 'Millbank, London SW1P 4RG, United Kingdom', '+44 20 7887 8888', 'London', 'United Kingdom', 'Art museum', 'https://bit.ly/3vgnxkW', 1, 51.490861, -0.128456, 'Tate Britain is an art museum on Millbank in the City of Westminster in London. It is part of the Tate network of galleries in England, with Tate Modern, Tate Liverpool and Tate St Ives. It is the oldest gallery in the network, having opened in 1897. It houses a substantial collection of the art of the United Kingdom since Tudor times, and in particular has large holdings of the works of J. M. W. Turner, who bequeathed all his own collection to the nation. It is one of the largest museums in the country.', 'https://www.youtube.com/watch?v=t_CYpZbzOuY');
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (13, 'Van Gogh Museum', 'Museumplein 6, 1071 DJ Amsterdam, Netherlands', '+31 20 570 5200', 'Amsterdam', 'Netherlands', 'Art museum', 'https://bit.ly/3M8xNCB', 1, 52.35836725, 4.88108997, 'The Van Gogh Museum in Amsterdam houses the largest collection of artworks by Vincent van Gogh (1853-1890) in the world. The permanent collection includes over 200 paintings by Vincent van Gogh, 500 drawings and more than 750 letters. The museum also presents exhibitions on various subjects from 19th-century art history.', 'https://www.youtube.com/embed/SRDEmb5Eo_Y');
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (14, 'Hermitage Museum', 'Palace Square, 2, St Petersburg, Russia, 190000', '+7 812 710-90-79', 'Saint Petersburg', 'Russia', 'Art museum', 'https://bit.ly/3JKWzXO', 1, 36.910384, -76.313124, 'State Hermitage is one of the largest museums in the world. Its exhibitions include over 3 million exhibits representing Antiquity, Western Europe, Middle East, Russia and many more epochs, countries and regions. Its foundation dates back to 1764, when Catherine the Great purchased a big collection of Western European paintings.', 'https://www.youtube.com/watch?v=m53NP_ydpmY');
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (15, 'Museo Reina Sofía', 'C. de Sta. Isabel, 52, 28012 Madrid, Spain', '+34 917 74 10 00', 'Madrid', 'Spain', 'National museum of art ', 'https://bit.ly/3JKXszC', 1, 40.407913, -3.694557, 'Founded in 1990 after originally being created as an art centre, Museo Reina Sofía is among the culminating events of the Spanish transition to democracy, recovering Pablo Picasso\'s Guernica as well as an outstanding representation of the international avant-gardes and neo-avant-gardes. I', 'https://www.youtube.com/watch?v=qfcwN3u5-Sw');
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (16, 'Tretyakov Gallery', 'Lavrushinsky Ln, 10, Moscow, Russia, 119017', '+7 499 230-77-88', 'Moscow', 'Russia', 'Art gallery', 'https://bit.ly/3uMCnAN', 1, 55.741486,  37.620346, 'The State Tretyakov Gallery History is the national treasury of Russian fine art and one of the greatest museums in the world. It is located in one of the oldest directs of Moscow - Zamoskvorechye, not far from the Kremlin. The Gallery\'s collection consists entirely of Russian art and artists who have made а contribution to the history of Russian art or been closely connected with it. The collection contains more than 180 000 works of painting, sculpture and graphics.', 'https://www.youtube.com/watch?v=lkfwLgew1aM');
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (17, 'National Museum of Korea', '137 Seobinggo-ro, Yongsan-gu, 서울특별시 South Korea', '+82 2-2077-9000', 'Seoul', 'South Korea', '	History and Art museum', 'https://bit.ly/38UV38U', 1, 37.523922, 126.98085, 'The National Museum of Korea is a place where you can explore the essence of Koreanarts and culture. The museum combines Korean history, life, and arts, from hand axes of the Paleolithic period, to celadons of the Goryeo dynasty, to paintings of the Joseondynasty, to modern photography.', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (18, 'Shanghai Museum', '201 Renmin Ave, People\'s Square, Huangpu, China, 200003', '+86 21 6372 3500', 'Shanghai', 'China', '	History and Art museum', 'https://bit.ly/3KO0jJy', 1, 31.228365, 121.47553, 'The Shanghai Museum is a museum of ancient Chinese art, situated on the People\'s Square in the Huangpu District of Shanghai, China. Rebuilt at its current location in 1996, it is considered one of China\'s first world-class modern museums.', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (19, 'Tokyo National Museum', '13-9 Uenokoen, Taito City, Tokyo 110-8712, Japan', '+81 50-5541-8600', 'Tokyo', 'Japan', 'Art museum', 'https://bit.ly/3EfwpeG', 1, 35.71877, 139.77638, 'The Tokyo National Museum collects, houses, and displays a comprehensive collection of art works and antiquities from Japan as well as other Asian countries. The museum also conducts research and investigations concerning its collection of books, rubbings, and photographs, related to fine art, and makes these items available to scholars.', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (20, 'Petit Palais', 'Av. Winston Churchill, 75008 Paris, France', '+33 1 53 43 40 00', 'Paris', 'France', 'Art museum', 'https://bit.ly/3EpgTgn', 1, -0.0606637, 44.9859936, 'The Petit Palais is an art museum in the 8th arrondissement of Paris, France.Built for the 1900 Exposition Universelle, it now houses the City of Paris Museum of Fine Arts. The Petit Palais is located across from the Grand Palais on Avenue Nicolas II, today Avenue Winston-Churchill.', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (21, 'Galleria dell\'Accademia', 'Via Ricasoli, 58/60, 50129 Firenze FI, Italy', '+39 055 098 7100', 'Florence', 'Italy', 'Art museum', 'https://bit.ly/3jJMfow', 1, 43.77676, 11.259147, 'The Galleria dell\'Accademia di Firenze, or \"Gallery of the Academy of Florence\", is an art museum in Florence, Italy. It is best known as the home of Michelangelo\'s sculpture David. It also has other sculptures by Michelangelo and a large collection of paintings by Florentine artists, mostly from the period 1300–1600, the Trecento to the Late Renaissance', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (22, 'Museum of Republika Srpska', 'Đure Daničića 1, Banja Luka 78000', '+38 751 215-973', 'Banja Luka', 'Bosnia and Herzegovina', 'History museum', 'https://bit.ly/3xwEx9a', 1, 44.769116, 17.191699, 'Museum of the Republic of Srpska was established in 1930. It represents the central institution of protection of movable cultural property of the Republic of Srpska. The permanent exhibition (archaeological, ethnological and natural) of 1500 m2, and thematic exhibitions can be visited in the RS Museum.', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (23, 'National Gallery of Art', 'Constitution Ave. NW, Washington, DC 20565, United States', '+1 202-737-4215', 'Washington, D.C.', 'United States', 'National art museum', 'https://bit.ly/3jKZGEw', 1, 38.891676, -77.020029, 'The National Gallery of Art, and its attached Sculpture Garden, is a national art museum in Washington, D.C., United States, located on the National Mall, between 3rd and 9th Streets, at Constitution Avenue NW.', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (24, 'Hungarian National Gallery', 'Budapest, Szent György tér 2, 1014 Hungary', '+36 20 439 7331', 'Budapest', 'Hungary', 'National art museum', 'https://bit.ly/3JMlvhy', 1, 47.496494, 19.040037, 'The Hungarian National Gallery, was established in 1957 as the national art museum. It is located in Buda Castle in Budapest, Hungary. Its collections cover Hungarian art in all genres, including the works of many nineteenth- and twentieth-century Hungarian artists who worked in Paris and other locations in the West.', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (25, 'Szépművészeti Múzeum', 'Budapest, Dózsa György út 41, 1146 Hungary', '+36 1 469 7100', 'Budapest', 'Hungary', 'Museum of Fine Arts', 'https://bit.ly/37nI3Z1', 1, 47.516106, 19.07206, 'The Museum of Fine Arts is a museum in Heroes\' Square, Budapest, Hungary, facing the Palace of Art. It was built by the plans of Albert Schickedanz and Fülöp Herzog in an eclectic-neoclassical style, between 1900 and 1906. ', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (26, 'National Gallery', 'Trafalgar Square, London WC2N 5DN, United Kingdom', '+44 20 7747 2885', 'London', 'England', 'Art museum', 'https://bit.ly/3KHkybL', 1, 51.508972, -0.128794, 'The National Gallery is an art museum in Trafalgar Square in the City of Westminster, in Central London. Founded in 1824, it houses a collection of over 2,300 paintings dating from the mid-13th century to 1900.', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (27, 'Louis Vuitton Foundation', '8 Av. du Mahatma Gandhi, 75016 Paris, France', '+33 1 40 69 96 00', 'Paris', 'France', 'French art museum', 'https://bit.ly/3OdUd7o', 1, 48.876637, 2.26341, 'The Louis Vuitton Foundation, previously Louis Vuitton Foundation for Creation, is a French art museum and cultural center sponsored by the group LVMH and its subsidiaries.', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (28, 'Kunsthistorisches Museum', 'Maria-Theresien-Platz, 1010 Wien, Austria', '+43 1 525240', 'Vienna', 'Austria', 'Art museum', 'https://bit.ly/36iKbk9', 1, 48.203995, 16.361427, 'The Kunsthistorisches Museum is an art museum in Vienna, Austria. Housed in its festive palatial building on Ringstraße, it is crowned with an octagonal dome. The term Kunsthistorisches Museum applies to both the institution and the main building.', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (29, 'Humboldt Forum', 'Schloßpl., 10178 Berlin, Germany', '+49 30 992118989', 'Berlin', 'Germany', 'Art museum', 'https://bit.ly/38WefTJ', 1, 52.517169, 13.40220, 'The Humboldt Forum is a museum of non-European art on the Museum Island in the historic centre of Berlin. ', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (30, 'Museo Egizio', 'Via Accademia delle Scienze, 6, 10123 Torino TO, Italy', '+39 011 440 6903', 'Turin', 'Italy', 'Archaeological museum', 'https://bit.ly/3uLSXAL', 1, 30.0723768, 31.346363 , 'The Museo Egizio is an archaeological museum in Turin, Piedmont, Italy, specializing in Egyptian archaeology and anthropology.', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (31, 'CaixaForum Madrid', 'P.º del Prado, 36, 28014 Madrid, Spain', '+34 913 30 73 00', 'Madrid', 'Spain', 'Sociocultural centre', 'https://bit.ly/3EkgZpJ', 1, 40.411114, -3.693571, 'CaixaForum Madrid is a museum and cultural center in Paseo del Prado 36, Madrid. It is sponsored by Caixa Bank.It was an old power station called Central Del Mediodía, from the 1900s. ', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (32, 'Albertina', 'Albertinapl. 1, 1010 Wien, Austria', '+43 1 53483', 'Vienna', 'Austria', 'Art museum', 'https://bit.ly/3rwH1km', 1, 48.20443 , 16.36823, 'The Albertina is a museum in the Innere Stadt of Vienna, Austria. It houses one of the largest and most important print rooms in the world with approximately 65,000 drawings and approximately 1 million old master prints, as well as more modern graphic works, photographs and architectural drawings. ', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (33, 'Österreichische Galerie Belvedere', 'Prinz Eugen-Straße 27, 1030 Wien, Austria', '+43 1 795570', 'Vienna', 'Austria', 'Art museum', 'https://bit.ly/3KS7T5S', 1, 48.191481, 16.38053, 'The Österreichische Galerie Belvedere is a museum housed in the Belvedere palace, in Vienna, Austria. The Belvedere palaces were the summer residence of Prince Eugene of Savoy.', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (34, 'Royal Palace of Milan', 'P.za del Duomo, 12, 20122 Milano MI, Italy', '+39 02 8846 5230', 'Milan', 'Italy', 'Cultural center', 'https://bit.ly/37Q4YMq', 1, 45.464664, 9.188540, 'The Royal Palace of Milan was the seat of government in the Italian city of Milan for many centuries. Today, it serves as a cultural center and it is home to international art exhibitions.', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (35, 'Saatchi Gallery', 'Duke of York\'s HQ, King\'s Rd, London SW3 4RY, United Kingdom', '+44 20 7811 3070', 'London', 'England', 'Gallery for contemporary art', 'https://bit.ly/3L088M4', 1, 51.490605, -0.158706, 'The Saatchi Gallery is a London gallery for contemporary art and an independent charity opened by Charles Saatchi in 1985. ', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (36, 'Museo Soumaya', ' Blvd. Miguel de Cervantes Saavedra, Granada, Miguel Hidalgo', '+52 55 1103 9800', 'Mexico City', 'Mexico', 'Private museum', 'https://bit.ly/3uNFxEp', 1, 19.440496, -99.20451, 'The Museo Soumaya is a private museum in Mexico City and a non-profit cultural institution with two museum buildings in Mexico City — Plaza Carso and Plaza Loreto. ', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (37, 'Städel Museum', 'Schaumainkai 63, 60596 Frankfurt am Main, Germany', '+49 69 605098200', 'Frankfurt', 'Germany', 'Art museum', 'https://bit.ly/3vlqu3Q', 1, 48.32944, 10.5976, 'The Städel is an art museum in Frankfurt, with one of the most important collections in Germany. The Städel Museum owns 3,100 paintings, 660 sculptures, more than 4,600 photographs and more than 100,000 drawings and prints. ', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (38, 'National Gallery of Denmark', 'Sølvgade 48-50, 1307 København K, Denmark', '+45 33 74 84 94', 'Copenhagen', 'Denmark', 'National gallery', 'https://bit.ly/3vsZwr8', 1, 55.676098, 12.568337, 'The National Gallery of Denmark is the Danish national gallery, located in the centre of Copenhagen. The museum collects, registers, maintains, researches and handles Danish and foreign art dating from the 14th century to the present day.', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (39, 'National Portrait Gallery', 'St. Martin\'s Pl, London WC2H 0HE, United Kingdom', '+44 20 7306 0055', 'London', 'England', 'Art museum', 'https://bit.ly/3jGHKeb', 1, 51.508972, -0.128794, 'The National Portrait Gallery is an art gallery in London housing a collection of portraits of historically important and famous British people. It was arguably the first national public gallery dedicated to portraits in the world when it opened in 1856.', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (40, 'Louvre Abu Dhabi', 'Saadiyat - Abu Dhabi - United Arab Emirates', '+971 600 565566', 'Abu Dhabi', 'United Arab Emirates', 'Art museum', 'https://bit.ly/3xuAzOs', 1, 24.530292, 54.445158, 'The Louvre Abu Dhabi is an art museum located on Saadiyat Island in Abu Dhabi, United Arab Emirates. It runs under an agreement between the UAE and France, signed in March 2007, that allows it to use the Louvre\'s name until 2037, and has been described by the Louvre as \"France’s largest cultural project abroad.\"', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (41, 'World Museum', ' William Brown St, Liverpool L3 8EN, United Kingdom', '+44 151 478 4393', 'Liverpool', 'England', 'Natural History Museum', 'https://bit.ly/3xACOjh', 1, 53.409801, -2.981763, 'World Museum is a large museum in Liverpool, England which has extensive collections covering archaeology, ethnology and the natural and physical sciences. Special attractions include the Natural History Centre and a planetarium.', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (42, 'Musée de l\'Orangerie', 'Jardin Tuileries, 75001 Paris, France', '+33 1 44 50 43 00', 'Paris', 'France', 'Art gallery', 'https://bit.ly/3M8DwIF', 1, 48.863773, 2.322875, 'The Musée de l\'Orangerie is an art gallery of impressionist and post-impressionist paintings located in the west corner of the Tuileries Gardens next to the Place de la Concorde in Paris.', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (43, 'Kunsthaus Zürich', 'Heimpl. 1, 8001 Zürich, Switzerland', '+41 44 253 84 84', 'Zürich', 'Switzerland', 'Art museum', 'https://bit.ly/3uKjdLz', 1, 47.368398 , 8.583532, 'The Kunsthaus Zürich is an art museum in Zürich, Switzerland. It houses one of the most important art collections in Switzerland, assembled over the years by the local art association called Zürcher Kunstgesellschaft. ', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (44, 'Musée des Arts décoratifs', '107 Rue de Rivoli, 75001 Paris, France', '+33 1 44 55 57 50', 'Paris', 'France', 'Art museum', 'https://bit.ly/3EkpxNd', 1, 48.857666 , 2.3337969, 'Les Arts décoratifs is a private, non-profit organization which manages museums of decorative arts located in Paris, France. The first museum dates to 1882, when collectors with an interest in the applied arts formed the initial organization. ', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (45, 'Musée du Quai Branly', '37 Quai Branly, 75007 Paris, France', '+33 1 56 61 70 00', 'Paris', 'France', 'Art museum', 'https://bit.ly/38VM8Ec', 1, 48.86093, 2.297663, 'The Musée du quai Branly – Jacques Chirac, located in Paris, France, is a museum designed by French architect Jean Nouvel to feature the indigenous art and cultures of Africa, Asia, Oceania, and the Americas. ', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (46, 'Gyeongju National Museum', '186 Iljeong-ro, Gyeongju-si, Gyeongsangbuk-do, South Korea', '+82 54-740-7500', 'Gyeongju', 'South Korea', 'Natural History Museum', 'https://bit.ly/3uKk2nD', 1, 35.82967, 129.22786, 'The Gyeongju National Museum is a museum in Gyeongju, North Gyeongsang Province, South Korea. Its holdings are largely devoted to relics of the Silla kingdom, of which Gyeongju was the capital.', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (47, 'Triennale di Milano', 'Viale Emilio Alemagna, 6, 20121 Milano MI, Italy', '+39 02 724341', 'Milan', 'Italy', 'Art museum', 'https://bit.ly/3EklMHJ', 1, 45.472207, 9.173604, 'The Triennale di Milano is a design and art museum in the Parco Sempione in Milan, in Lombardy in northern Italy. It is housed in the Palazzo dell\'Arte, which was designed by Giovanni Muzio', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (48, 'Getty Center', '1200 Getty Center Dr, Los Angeles, CA 90049, United States', '+1 310-440-7300', 'Los Angeles', 'California', 'Art museum', 'https://bit.ly/3OqC9XQ', 1, 34.079048, -118.47440, 'The Getty Center, in Los Angeles, California, is a campus of the Getty Museum and other programs of the Getty Trust. The $1.3 billion Center opened to the public on December 16, 1997 and is well known for its architecture, gardens, and views overlooking Los Angeles.', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (49, 'Tel Aviv Museum of Art', 'The Golda Meir Cultural and Art Center, Sderot Sha\'ul HaMelech 27, Tel Aviv-Yafo, Israel', '+972 3-607-7020', 'Tel Aviv', 'Israel', 'Art museum', 'https://bit.ly/3EgA2Ba', 1, 32.077255, 34.7866, 'Tel Aviv Museum of Art is an art museum in Tel Aviv, Israel. The museum is dedicated to the preservation and display of modern and contemporary art from Israel and around the world. ', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (50, 'Royal Academy of Arts', 'Burlington House, Piccadilly, London W1J 0BD, United Kingdom', '+44 20 7300 8090', 'London', 'England', 'Art museum', 'https://bit.ly/36lS8oQ', 1, 51.50958 , -0.13956, 'The Royal Academy of Arts is an art institution based in Burlington House on Piccadilly in London. Founded in 1768, it has a unique position as an independent, privately funded institution led by eminent artists and architects. ', NULL);
INSERT INTO `mydb`.`MUSEUM` (`id`, `name`, `address`, `phone`, `city`, `country`, `type`, `image`, `active`, `latitude`, `longitude`, `description`, `presentation`) VALUES (51, 'Museum of Modern Art', ' 11 W 53rd St, New York, NY 10019, United States', '+1 212-708-9400', 'New York City', 'United States', 'Art museum', 'https://bit.ly/3xEyUFY', 1, 40.761509, -73.978271, 'The Museum of Modern Art is an art museum located in Midtown Manhattan, New York City, on 53rd Street between Fifth and Sixth Avenues. It plays a major role in developing and collecting modern art, and is often identified as one of the largest and most influential museums of modern art in the world.', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`TOUR`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`TOUR` (`id`, `museum_id`, `start`, `duration`, `price`, `active`) VALUES (1, 1, '2022-04-19 10:00:00', 5, 9.99, 1);
INSERT INTO `mydb`.`TOUR` (`id`, `museum_id`, `start`, `duration`, `price`, `active`) VALUES (2, 2, '2022-04-18 9:00:00', 5, 9.99, 1);
INSERT INTO `mydb`.`TOUR` (`id`, `museum_id`, `start`, `duration`, `price`, `active`) VALUES (3, 7, '2022-04-20 15:00:00', 4, 14.99, 1);
INSERT INTO `mydb`.`TOUR` (`id`, `museum_id`, `start`, `duration`, `price`, `active`) VALUES (4, 8, '2022-04-20 16:00:00', 1, 19.99, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`USER`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`USER` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `logged_in`, `active`, `role`, `token`, `approved`) VALUES (1, 'Milica', 'Milakovic', 'milica', 'milica', 'milakovicmilica@yahoo.com', 0, 1, 'user', NULL, 1);
INSERT INTO `mydb`.`USER` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `logged_in`, `active`, `role`, `token`, `approved`) VALUES (2, 'Marko', 'Markovic', 'admin', 'admin', 'virtualmuseum.ip@gmail.com', 0, 1, 'admin', 'c84258e9c39059a89ab77d846ddab909', 1);
INSERT INTO `mydb`.`USER` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `logged_in`, `active`, `role`, `token`, `approved`) VALUES (3, 'Test', 'Test', 'test', 'test', 'test@mail.com', 0, 1, 'admin', '8ad8757baa8564dc136c1e07507f4a98', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`TICKET`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`TICKET` (`id`, `issue_date`, `tour_id`, `user_id`) VALUES (1, '2022-04-17 15:00:00', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`CREDIT_CARD`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`CREDIT_CARD` (`id`, `number`, `type`, `expirationDate`, `pin`, `balance`, `active`, `user_id`) VALUES (1, '1234567812345678', 'visa', '05/25', '1234', 100000, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`LOG`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (1, '2022-04-16 11:00:00', 0);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (2, '2022-04-16 12:00:00', 1);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (3, '2022-04-16 13:00:00', 2);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (4, '2022-04-16 14:00:00', 2);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (5, '2022-04-16 15:00:00', 1);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (6, '2022-04-16 16:00:00', 2);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (7, '2022-04-16 17:00:00', 0);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (8, '2022-04-16 18:00:00', 1);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (9, '2022-04-16 19:00:00', 0);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (10, '2022-04-16 20:00:00', 1);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (11, '2022-04-16 21:00:00', 0);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (12, '2022-04-16 22:00:00', 0);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (13, '2022-04-16 23:00:00', 0);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (14, '2022-04-17 0:00:00', 2);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (15, '2022-04-17 1:00:00', 3);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (16, '2022-04-17 2:00:00', 2);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (17, '2022-04-17 3:00:00', 3);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (18, '2022-04-17 4:00:00', 3);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (19, '2022-04-17 5:00:00', 2);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (20, '2022-04-17 6:00:00', 3);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (21, '2022-04-17 7:00:00', 2);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (22, '2022-04-17 8:00:00', 3);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (23, '2022-04-17 9:00:00', 3);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (24, '2022-04-17 10:00:00', 2);
INSERT INTO `mydb`.`LOG` (`id`, `timestamp`, `online`) VALUES (25, '2022-04-17 11:00:00', 2);

COMMIT;

