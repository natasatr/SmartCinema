-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema movie
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema movie
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `movie` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `movie` ;

-- -----------------------------------------------------
-- Table `movie`.`ROLA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`ROLA` ;

CREATE TABLE IF NOT EXISTS `movie`.`ROLA` (
  `RolaID` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`RolaID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`NALOG`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`NALOG` ;

CREATE TABLE IF NOT EXISTS `movie`.`NALOG` (
  `NalogID` INT NOT NULL AUTO_INCREMENT,
  `KorisnickoIme` VARCHAR(45) NOT NULL,
  `Lozinka` CHAR(60) CHARACTER SET 'ascii' NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  `ROLA_RolaID` INT NOT NULL,
  PRIMARY KEY (`NalogID`),
  INDEX `fk_NALOG_ROLA1_idx` (`ROLA_RolaID` ASC) VISIBLE,
  CONSTRAINT `fk_NALOG_ROLA1`
    FOREIGN KEY (`ROLA_RolaID`)
    REFERENCES `movie`.`ROLA` (`RolaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`ADRESA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`ADRESA` ;

CREATE TABLE IF NOT EXISTS `movie`.`ADRESA` (
  `AdresaID` INT NOT NULL AUTO_INCREMENT,
  `Mjesto` VARCHAR(45) NOT NULL,
  `Ulica` VARCHAR(45) NOT NULL,
  `Broj` INT NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`AdresaID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`ZAPOSLENI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`ZAPOSLENI` ;

CREATE TABLE IF NOT EXISTS `movie`.`ZAPOSLENI` (
  `ZaposleniID` INT NOT NULL AUTO_INCREMENT,
  `JMB` CHAR(13) NOT NULL,
  `Ime` VARCHAR(45) NOT NULL,
  `Prezime` VARCHAR(45) NOT NULL,
  `Plata` DECIMAL NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  `NALOG_NalogID` INT NOT NULL,
  `ADRESA_AdresaID` INT NOT NULL,
  PRIMARY KEY (`ZaposleniID`),
  INDEX `fk_ZAPOSLENI_NALOG2_idx` (`NALOG_NalogID` ASC) VISIBLE,
  INDEX `fk_ZAPOSLENI_ADRESA2_idx` (`ADRESA_AdresaID` ASC) VISIBLE,
  CONSTRAINT `fk_ZAPOSLENI_NALOG2`
    FOREIGN KEY (`NALOG_NalogID`)
    REFERENCES `movie`.`NALOG` (`NalogID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ZAPOSLENI_ADRESA2`
    FOREIGN KEY (`ADRESA_AdresaID`)
    REFERENCES `movie`.`ADRESA` (`AdresaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`KINO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`KINO` ;

CREATE TABLE IF NOT EXISTS `movie`.`KINO` (
  `KinoID` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Telefon` VARCHAR(45) NOT NULL,
  `ADRESA_AdresaID` INT NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`KinoID`),
  INDEX `fk_KINO_ADRESA1_idx` (`ADRESA_AdresaID` ASC) VISIBLE,
  CONSTRAINT `fk_KINO_ADRESA1`
    FOREIGN KEY (`ADRESA_AdresaID`)
    REFERENCES `movie`.`ADRESA` (`AdresaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`SALA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`SALA` ;

CREATE TABLE IF NOT EXISTS `movie`.`SALA` (
  `SalaID` INT NOT NULL AUTO_INCREMENT,
  `Broj` INT NOT NULL,
  `Kapacitet` INT NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  `KINO_KinoID` INT NOT NULL,
  PRIMARY KEY (`SalaID`),
  INDEX `fk_SALA_KINO1_idx` (`KINO_KinoID` ASC) VISIBLE,
  CONSTRAINT `fk_SALA_KINO1`
    FOREIGN KEY (`KINO_KinoID`)
    REFERENCES `movie`.`KINO` (`KinoID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`VRSTA_SJEDISTA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`VRSTA_SJEDISTA` ;

CREATE TABLE IF NOT EXISTS `movie`.`VRSTA_SJEDISTA` (
  `VrstaSjedistaID` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`VrstaSjedistaID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`SJEDISTE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`SJEDISTE` ;

CREATE TABLE IF NOT EXISTS `movie`.`SJEDISTE` (
  `SjedisteID` INT NOT NULL AUTO_INCREMENT,
  `Broj` INT NOT NULL,
  `Red` INT NOT NULL,
  `Zauzeto` TINYINT NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  `SALA_SalaID` INT NOT NULL,
  `VRSTA_SJEDISTA_VrstaSjedistaID` INT NOT NULL,
  PRIMARY KEY (`SjedisteID`),
  INDEX `fk_SJEDISTE_SALA1_idx` (`SALA_SalaID` ASC) VISIBLE,
  INDEX `fk_SJEDISTE_VRSTA_SJEDISTA1_idx` (`VRSTA_SJEDISTA_VrstaSjedistaID` ASC) VISIBLE,
  CONSTRAINT `fk_SJEDISTE_SALA1`
    FOREIGN KEY (`SALA_SalaID`)
    REFERENCES `movie`.`SALA` (`SalaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SJEDISTE_VRSTA_SJEDISTA1`
    FOREIGN KEY (`VRSTA_SJEDISTA_VrstaSjedistaID`)
    REFERENCES `movie`.`VRSTA_SJEDISTA` (`VrstaSjedistaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`FILM`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`FILM` ;

CREATE TABLE IF NOT EXISTS `movie`.`FILM` (
  `FilmID` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  `Trajanje` VARCHAR(10) NOT NULL,
  `GodinaSnimanja` YEAR NOT NULL,
  `Reziser` VARCHAR(45) NOT NULL,
  `Opis` VARCHAR(1000) NOT NULL,
  `URepetoaru` TINYINT NOT NULL,
  `DatumPrvogPrikazivanja` DATE NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`FilmID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`GLUMCI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`GLUMCI` ;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema movie
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema movie
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `movie` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `movie` ;

-- -----------------------------------------------------
-- Table `movie`.`ROLA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`ROLA` ;

CREATE TABLE IF NOT EXISTS `movie`.`ROLA` (
  `RolaID` INT NOT NULL,
  `Naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`RolaID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`NALOG`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`NALOG` ;

CREATE TABLE IF NOT EXISTS `movie`.`NALOG` (
  `NalogID` INT NOT NULL AUTO_INCREMENT,
  `KorisnickoIme` VARCHAR(45) NOT NULL,
  `Lozinka` VARCHAR(200) NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  `ROLA_RolaID` INT NOT NULL,
  PRIMARY KEY (`NalogID`),
  INDEX `fk_NALOG_ROLA1_idx` (`ROLA_RolaID` ASC) VISIBLE,
  CONSTRAINT `fk_NALOG_ROLA1`
    FOREIGN KEY (`ROLA_RolaID`)
    REFERENCES `movie`.`ROLA` (`RolaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`ADRESA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`ADRESA` ;

CREATE TABLE IF NOT EXISTS `movie`.`ADRESA` (
  `AdresaID` INT NOT NULL AUTO_INCREMENT,
  `Mjesto` VARCHAR(45) NOT NULL,
  `Ulica` VARCHAR(45) NOT NULL,
  `Broj` INT NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`AdresaID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`ZAPOSLENI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`ZAPOSLENI` ;

CREATE TABLE IF NOT EXISTS `movie`.`ZAPOSLENI` (
  `ZaposleniID` INT NOT NULL AUTO_INCREMENT,
  `JMB` VARCHAR(45) NOT NULL,
  `Ime` VARCHAR(45) NOT NULL,
  `Prezime` VARCHAR(45) NOT NULL,
  `Plata` DECIMAL NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  `NALOG_NalogID` INT NOT NULL,
  `ADRESA_AdresaID` INT NOT NULL,
  PRIMARY KEY (`ZaposleniID`),
  INDEX `fk_ZAPOSLENI_NALOG2_idx` (`NALOG_NalogID` ASC) VISIBLE,
  INDEX `fk_ZAPOSLENI_ADRESA2_idx` (`ADRESA_AdresaID` ASC) VISIBLE,
  CONSTRAINT `fk_ZAPOSLENI_NALOG2`
    FOREIGN KEY (`NALOG_NalogID`)
    REFERENCES `movie`.`NALOG` (`NalogID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ZAPOSLENI_ADRESA2`
    FOREIGN KEY (`ADRESA_AdresaID`)
    REFERENCES `movie`.`ADRESA` (`AdresaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`KINO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`KINO` ;

CREATE TABLE IF NOT EXISTS `movie`.`KINO` (
  `KinoID` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Telefon` VARCHAR(45) NOT NULL,
  `ADRESA_AdresaID` INT NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`KinoID`),
  INDEX `fk_KINO_ADRESA1_idx` (`ADRESA_AdresaID` ASC) VISIBLE,
  CONSTRAINT `fk_KINO_ADRESA1`
    FOREIGN KEY (`ADRESA_AdresaID`)
    REFERENCES `movie`.`ADRESA` (`AdresaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`SALA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`SALA` ;

CREATE TABLE IF NOT EXISTS `movie`.`SALA` (
  `SalaID` INT NOT NULL AUTO_INCREMENT,
  `Broj` INT NOT NULL,
  `Kapacitet` INT NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  `KINO_KinoID` INT NOT NULL,
  PRIMARY KEY (`SalaID`),
  INDEX `fk_SALA_KINO1_idx` (`KINO_KinoID` ASC) VISIBLE,
  CONSTRAINT `fk_SALA_KINO1`
    FOREIGN KEY (`KINO_KinoID`)
    REFERENCES `movie`.`KINO` (`KinoID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`VRSTA_SJEDISTA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`VRSTA_SJEDISTA` ;

CREATE TABLE IF NOT EXISTS `movie`.`VRSTA_SJEDISTA` (
  `VrstaSjedistaID` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`VrstaSjedistaID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`SJEDISTE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`SJEDISTE` ;

CREATE TABLE IF NOT EXISTS `movie`.`SJEDISTE` (
  `SjedisteID` INT NOT NULL AUTO_INCREMENT,
  `Broj` INT NOT NULL,
  `Red` INT NOT NULL,
  `Zauzeto` TINYINT NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  `SALA_SalaID` INT NOT NULL,
  `VRSTA_SJEDISTA_VrstaSjedistaID` INT NOT NULL,
  PRIMARY KEY (`SjedisteID`),
  INDEX `fk_SJEDISTE_SALA1_idx` (`SALA_SalaID` ASC) VISIBLE,
  INDEX `fk_SJEDISTE_VRSTA_SJEDISTA1_idx` (`VRSTA_SJEDISTA_VrstaSjedistaID` ASC) VISIBLE,
  CONSTRAINT `fk_SJEDISTE_SALA1`
    FOREIGN KEY (`SALA_SalaID`)
    REFERENCES `movie`.`SALA` (`SalaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SJEDISTE_VRSTA_SJEDISTA1`
    FOREIGN KEY (`VRSTA_SJEDISTA_VrstaSjedistaID`)
    REFERENCES `movie`.`VRSTA_SJEDISTA` (`VrstaSjedistaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`FILM`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`FILM` ;

CREATE TABLE IF NOT EXISTS `movie`.`FILM` (
  `FilmID` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  `Trajanje` VARCHAR(10) NOT NULL,
  `GodinaSnimanja` INT NOT NULL,
  `Reziser` VARCHAR(45) NOT NULL,
  `Opis` VARCHAR(1000) NOT NULL,
  `URepetoaru` VARCHAR(5) NOT NULL,
  `DatumPrvogPrikazivanja` VARCHAR(45) NOT NULL,
  `Glumci` VARCHAR(2000) NOT NULL,
  `Zanr` VARCHAR(2000) NOT NULL,
  PRIMARY KEY (`FilmID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`PRIKAZIVANJE_FILMA_U_SALI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`PRIKAZIVANJE_FILMA_U_SALI` ;

CREATE TABLE IF NOT EXISTS `movie`.`PRIKAZIVANJE_FILMA_U_SALI` (
  `SALA_SalaID` INT NOT NULL,
  `FILM_FilmID` INT NOT NULL,
  `Termin` VARCHAR(45) NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  `TerminID` INT NOT NULL,
  PRIMARY KEY (`SALA_SalaID`, `FILM_FilmID`, `TerminID`),
  INDEX `fk_SALA_has_FILM_FILM1_idx` (`FILM_FilmID` ASC) VISIBLE,
  INDEX `fk_SALA_has_FILM_SALA1_idx` (`SALA_SalaID` ASC) VISIBLE,
  CONSTRAINT `fk_SALA_has_FILM_SALA1`
    FOREIGN KEY (`SALA_SalaID`)
    REFERENCES `movie`.`SALA` (`SalaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SALA_has_FILM_FILM1`
    FOREIGN KEY (`FILM_FilmID`)
    REFERENCES `movie`.`FILM` (`FilmID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`KARTA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`KARTA` ;

CREATE TABLE IF NOT EXISTS `movie`.`KARTA` (
  `KartaID` INT NOT NULL AUTO_INCREMENT,
  `Cijena` DECIMAL(5,2) NOT NULL,
  `VrijemeKupovine` TIMESTAMP NOT NULL,
  `Prodano` TINYINT NOT NULL DEFAULT 0,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  `SJEDISTE_SjedisteID` INT NOT NULL,
  `ZAPOSLENI_ZaposleniID` INT NOT NULL,
  `PRIKAZIVANJE_FILMA_U_SALI_SALA_SalaID` INT NOT NULL,
  `PRIKAZIVANJE_FILMA_U_SALI_FILM_FilmID` INT NOT NULL,
  `PRIKAZIVANJE_FILMA_U_SALI_TerminID` INT NOT NULL,
  PRIMARY KEY (`KartaID`),
  INDEX `fk_KARTA_SJEDISTE1_idx` (`SJEDISTE_SjedisteID` ASC) VISIBLE,
  INDEX `fk_KARTA_ZAPOSLENI1_idx` (`ZAPOSLENI_ZaposleniID` ASC) VISIBLE,
  INDEX `fk_KARTA_PRIKAZIVANJE_FILMA_U_SALI1_idx` (`PRIKAZIVANJE_FILMA_U_SALI_SALA_SalaID` ASC, `PRIKAZIVANJE_FILMA_U_SALI_FILM_FilmID` ASC, `PRIKAZIVANJE_FILMA_U_SALI_TerminID` ASC) VISIBLE,
  CONSTRAINT `fk_KARTA_SJEDISTE1`
    FOREIGN KEY (`SJEDISTE_SjedisteID`)
    REFERENCES `movie`.`SJEDISTE` (`SjedisteID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_KARTA_ZAPOSLENI1`
    FOREIGN KEY (`ZAPOSLENI_ZaposleniID`)
    REFERENCES `movie`.`ZAPOSLENI` (`ZaposleniID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_KARTA_PRIKAZIVANJE_FILMA_U_SALI1`
    FOREIGN KEY (`PRIKAZIVANJE_FILMA_U_SALI_SALA_SalaID` , `PRIKAZIVANJE_FILMA_U_SALI_FILM_FilmID` , `PRIKAZIVANJE_FILMA_U_SALI_TerminID`)
    REFERENCES `movie`.`PRIKAZIVANJE_FILMA_U_SALI` (`SALA_SalaID` , `FILM_FilmID` , `TerminID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`REZERVACIJA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`REZERVACIJA` ;

CREATE TABLE IF NOT EXISTS `movie`.`REZERVACIJA` (
  `RezervacijaID` INT NOT NULL AUTO_INCREMENT,
  `odDatuma` TIMESTAMP NOT NULL,
  `doDatuma` VARCHAR(45) NOT NULL,
  `Ime` VARCHAR(45) NOT NULL,
  `Prezime` VARCHAR(45) NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  `KARTA_KartaID` INT NOT NULL,
  PRIMARY KEY (`RezervacijaID`),
  INDEX `fk_REZERVACIJA_KARTA1_idx` (`KARTA_KartaID` ASC) VISIBLE,
  CONSTRAINT `fk_REZERVACIJA_KARTA1`
    FOREIGN KEY (`KARTA_KartaID`)
    REFERENCES `movie`.`KARTA` (`KartaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`DODATNA_PONUDA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`DODATNA_PONUDA` ;

CREATE TABLE IF NOT EXISTS `movie`.`DODATNA_PONUDA` (
  `DodatnaPonudaID` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  `Cijena` DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (`DodatnaPonudaID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`RACUN`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`RACUN` ;

CREATE TABLE IF NOT EXISTS `movie`.`RACUN` (
  `RacunID` INT NOT NULL AUTO_INCREMENT,
  `UkupanIznos` VARCHAR(45) NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  `ZAPOSLENI_ZaposleniID` INT NOT NULL,
  PRIMARY KEY (`RacunID`),
  INDEX `fk_RACUN_ZAPOSLENI1_idx` (`ZAPOSLENI_ZaposleniID` ASC) VISIBLE,
  CONSTRAINT `fk_RACUN_ZAPOSLENI1`
    FOREIGN KEY (`ZAPOSLENI_ZaposleniID`)
    REFERENCES `movie`.`ZAPOSLENI` (`ZaposleniID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`STAVKA_RACUNA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`STAVKA_RACUNA` ;

CREATE TABLE IF NOT EXISTS `movie`.`STAVKA_RACUNA` (
  `RACUN_RacunID` INT NOT NULL,
  `DODATNA_PONUDA_DodatnaPonudaID` INT NOT NULL,
  PRIMARY KEY (`RACUN_RacunID`, `DODATNA_PONUDA_DodatnaPonudaID`),
  INDEX `fk_RACUN_has_DODATNA_PONUDA_DODATNA_PONUDA1_idx` (`DODATNA_PONUDA_DodatnaPonudaID` ASC) VISIBLE,
  INDEX `fk_RACUN_has_DODATNA_PONUDA_RACUN1_idx` (`RACUN_RacunID` ASC) VISIBLE,
  CONSTRAINT `fk_RACUN_has_DODATNA_PONUDA_RACUN1`
    FOREIGN KEY (`RACUN_RacunID`)
    REFERENCES `movie`.`RACUN` (`RacunID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RACUN_has_DODATNA_PONUDA_DODATNA_PONUDA1`
    FOREIGN KEY (`DODATNA_PONUDA_DodatnaPonudaID`)
    REFERENCES `movie`.`DODATNA_PONUDA` (`DodatnaPonudaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
