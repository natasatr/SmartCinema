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
-- Table `movie`.`ADRESA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`ADRESA` ;

CREATE TABLE IF NOT EXISTS `movie`.`ADRESA` (
  `AdresaID` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  `Broj` INT NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`AdresaID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`NALOG`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`NALOG` ;

CREATE TABLE IF NOT EXISTS `movie`.`NALOG` (
  `IDNaloga` INT NOT NULL,
  `KorisnickoIme` VARCHAR(45) NOT NULL,
  `Lozinka` VARCHAR(45) NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`IDNaloga`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`BANKA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`BANKA` ;

CREATE TABLE IF NOT EXISTS `movie`.`BANKA` (
  `BankaID` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  `ADRESA_AdresaID` INT NOT NULL,
  PRIMARY KEY (`BankaID`),
  INDEX `fk_BANKA_ADRESA1_idx` (`ADRESA_AdresaID` ASC) VISIBLE,
  CONSTRAINT `fk_BANKA_ADRESA1`
    FOREIGN KEY (`ADRESA_AdresaID`)
    REFERENCES `movie`.`ADRESA` (`AdresaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`BANKOVNI_RACUN`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`BANKOVNI_RACUN` ;

CREATE TABLE IF NOT EXISTS `movie`.`BANKOVNI_RACUN` (
  `RacunID` INT NOT NULL AUTO_INCREMENT,
  `BrojRacuna` VARCHAR(45) NOT NULL,
  `BANKA_BankaID` INT NOT NULL,
  PRIMARY KEY (`RacunID`),
  INDEX `fk_RACUN_BANKA1_idx` (`BANKA_BankaID` ASC) VISIBLE,
  CONSTRAINT `fk_RACUN_BANKA1`
    FOREIGN KEY (`BANKA_BankaID`)
    REFERENCES `movie`.`BANKA` (`BankaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
  `ADRESA_AdresaID` INT NOT NULL,
  `NALOG_IDNaloga` INT NOT NULL,
  `RACUN_RacunID` INT NULL,
  PRIMARY KEY (`ZaposleniID`),
  INDEX `fk_ZAPOSLENI_ADRESA1_idx` (`ADRESA_AdresaID` ASC) VISIBLE,
  INDEX `fk_ZAPOSLENI_NALOG1_idx` (`NALOG_IDNaloga` ASC) VISIBLE,
  INDEX `fk_ZAPOSLENI_RACUN1_idx` (`RACUN_RacunID` ASC) VISIBLE,
  CONSTRAINT `fk_ZAPOSLENI_ADRESA1`
    FOREIGN KEY (`ADRESA_AdresaID`)
    REFERENCES `movie`.`ADRESA` (`AdresaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ZAPOSLENI_NALOG1`
    FOREIGN KEY (`NALOG_IDNaloga`)
    REFERENCES `movie`.`NALOG` (`IDNaloga`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ZAPOSLENI_RACUN1`
    FOREIGN KEY (`RACUN_RacunID`)
    REFERENCES `movie`.`BANKOVNI_RACUN` (`RacunID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`VRSTA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`VRSTA` ;

CREATE TABLE IF NOT EXISTS `movie`.`VRSTA` (
  `VrstaID` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`VrstaID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`ADMINISTRATOR`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`ADMINISTRATOR` ;

CREATE TABLE IF NOT EXISTS `movie`.`ADMINISTRATOR` (
  `AdministratorID` INT NOT NULL,
  `VRSTA_VrstaID` INT NOT NULL,
  PRIMARY KEY (`AdministratorID`),
  INDEX `fk_ADMINISTRATOR_VRSTA1_idx` (`VRSTA_VrstaID` ASC) VISIBLE,
  CONSTRAINT `fk_ADMINISTRATOR_ZAPOSLENI1`
    FOREIGN KEY (`AdministratorID`)
    REFERENCES `movie`.`ZAPOSLENI` (`ZaposleniID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ADMINISTRATOR_VRSTA1`
    FOREIGN KEY (`VRSTA_VrstaID`)
    REFERENCES `movie`.`VRSTA` (`VrstaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`SLUZBENIK`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`SLUZBENIK` ;

CREATE TABLE IF NOT EXISTS `movie`.`SLUZBENIK` (
  `SluzbenikiID` INT NOT NULL,
  PRIMARY KEY (`SluzbenikiID`),
  CONSTRAINT `fk_SLUZBENIK_ZAPOSLENI`
    FOREIGN KEY (`SluzbenikiID`)
    REFERENCES `movie`.`ZAPOSLENI` (`ZaposleniID`)
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
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`VrstaSjedistaID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`SJEDISTE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`SJEDISTE` ;

CREATE TABLE IF NOT EXISTS `movie`.`SJEDISTE` (
  `SjedisteID` INT NOT NULL AUTO_INCREMENT,
  `Broj` INT NOT NULL,
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
  `Trajanje` TIME NOT NULL,
  `GodinaSnimanja` YEAR NOT NULL,
  `Reziser` VARCHAR(45) NOT NULL,
  `Opis` VARCHAR(1000) NOT NULL,
  `URepetoaru` TINYINT NOT NULL,
  `DatumPrvogPrikazivanja` DATE NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  `INFORMACIJE_InformacijeID` INT NOT NULL,
  `FILMcol` VARCHAR(45) NULL,
  PRIMARY KEY (`FilmID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`GLUMCI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`GLUMCI` ;

CREATE TABLE IF NOT EXISTS `movie`.`GLUMCI` (
  `GlumciID` INT NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(45) NOT NULL,
  `Prezime` VARCHAR(45) NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`GlumciID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`ZANR`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`ZANR` ;

CREATE TABLE IF NOT EXISTS `movie`.`ZANR` (
  `ZanrID` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(45) NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`ZanrID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`GLUMCI_FILM`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`GLUMCI_FILM` ;

CREATE TABLE IF NOT EXISTS `movie`.`GLUMCI_FILM` (
  `GLUMCI_GlumciID` INT NOT NULL,
  `FILM_FilmID` INT NOT NULL,
  PRIMARY KEY (`GLUMCI_GlumciID`, `FILM_FilmID`),
  INDEX `fk_GLUMCI_has_FILM_FILM1_idx` (`FILM_FilmID` ASC) VISIBLE,
  INDEX `fk_GLUMCI_has_FILM_GLUMCI1_idx` (`GLUMCI_GlumciID` ASC) VISIBLE,
  CONSTRAINT `fk_GLUMCI_has_FILM_GLUMCI1`
    FOREIGN KEY (`GLUMCI_GlumciID`)
    REFERENCES `movie`.`GLUMCI` (`GlumciID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_GLUMCI_has_FILM_FILM1`
    FOREIGN KEY (`FILM_FilmID`)
    REFERENCES `movie`.`FILM` (`FilmID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`ZANR_FILM`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`ZANR_FILM` ;

CREATE TABLE IF NOT EXISTS `movie`.`ZANR_FILM` (
  `ZANR_ZanrID` INT NOT NULL,
  `FILM_FilmID` INT NOT NULL,
  PRIMARY KEY (`ZANR_ZanrID`, `FILM_FilmID`),
  INDEX `fk_ZANR_has_FILM_FILM1_idx` (`FILM_FilmID` ASC) VISIBLE,
  INDEX `fk_ZANR_has_FILM_ZANR1_idx` (`ZANR_ZanrID` ASC) VISIBLE,
  CONSTRAINT `fk_ZANR_has_FILM_ZANR1`
    FOREIGN KEY (`ZANR_ZanrID`)
    REFERENCES `movie`.`ZANR` (`ZanrID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ZANR_has_FILM_FILM1`
    FOREIGN KEY (`FILM_FilmID`)
    REFERENCES `movie`.`FILM` (`FilmID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`PRIKAZIVANJE_FILMA_U_SALI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`PRIKAZIVANJE_FILMA_U_SALI` ;

CREATE TABLE IF NOT EXISTS `movie`.`PRIKAZIVANJE_FILMA_U_SALI` (
  `SALA_SalaID` INT NOT NULL,
  `FILM_FilmID` INT NOT NULL,
  `Termin` DATETIME NOT NULL,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`SALA_SalaID`, `FILM_FilmID`),
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
  `Cijena` DECIMAL NOT NULL,
  `InformacijeOFIlmu` VARCHAR(45) NOT NULL,
  `BrojSjedista` INT NOT NULL,
  `TerminPrikazivanjaFilma` DATETIME NOT NULL,
  `VrijemeKupovine` DATETIME NULL,
  `Prodano` TINYINT NOT NULL DEFAULT 0,
  `Uklonjeno` TINYINT NOT NULL DEFAULT 0,
  `SalaID` INT NOT NULL,
  PRIMARY KEY (`KartaID`),
  INDEX `fk_KARTA_PRIKAZIVANJE_FILMA_U_SALI1_idx` (`SalaID` ASC) VISIBLE,
  CONSTRAINT `fk_KARTA_PRIKAZIVANJE_FILMA_U_SALI1`
    FOREIGN KEY (`SalaID`)
    REFERENCES `movie`.`PRIKAZIVANJE_FILMA_U_SALI` (`SALA_SalaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`POSJETILAC`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`POSJETILAC` ;

CREATE TABLE IF NOT EXISTS `movie`.`POSJETILAC` (
  `PosjetilacID` INT NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(45) NOT NULL,
  `Prezime` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`PosjetilacID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`REZERVACIJA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`REZERVACIJA` ;

CREATE TABLE IF NOT EXISTS `movie`.`REZERVACIJA` (
  `RezervacijaID` INT NOT NULL AUTO_INCREMENT,
  `odDatuma` DATE NOT NULL,
  `doDatuma` VARCHAR(45) NOT NULL,
  `POSJETILAC_PosjetilacID` INT NOT NULL,
  `SLUZBENIK_SluzbenikiID` INT NOT NULL,
  `KARTA_KartaID` INT NOT NULL,
  PRIMARY KEY (`RezervacijaID`),
  INDEX `fk_REZERVACIJA_POSJETILAC1_idx` (`POSJETILAC_PosjetilacID` ASC) VISIBLE,
  INDEX `fk_REZERVACIJA_SLUZBENIK1_idx` (`SLUZBENIK_SluzbenikiID` ASC) VISIBLE,
  INDEX `fk_REZERVACIJA_KARTA1_idx` (`KARTA_KartaID` ASC) VISIBLE,
  CONSTRAINT `fk_REZERVACIJA_POSJETILAC1`
    FOREIGN KEY (`POSJETILAC_PosjetilacID`)
    REFERENCES `movie`.`POSJETILAC` (`PosjetilacID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_REZERVACIJA_SLUZBENIK1`
    FOREIGN KEY (`SLUZBENIK_SluzbenikiID`)
    REFERENCES `movie`.`SLUZBENIK` (`SluzbenikiID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
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
  `Cijena` DECIMAL NOT NULL,
  PRIMARY KEY (`DodatnaPonudaID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `movie`.`RACUN`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `movie`.`RACUN` ;

CREATE TABLE IF NOT EXISTS `movie`.`RACUN` (
  `RacunID` INT NOT NULL AUTO_INCREMENT,
  `UkupanIznos` VARCHAR(45) NOT NULL,
  `SLUZBENIK_SluzbenikiID` INT NOT NULL,
  `POSJETILAC_PosjetilacID` INT NOT NULL,
  PRIMARY KEY (`RacunID`),
  INDEX `fk_RACUN_ZA_KARTU_SLUZBENIK1_idx` (`SLUZBENIK_SluzbenikiID` ASC) VISIBLE,
  INDEX `fk_RACUN_ZA_KARTU_POSJETILAC1_idx` (`POSJETILAC_PosjetilacID` ASC) VISIBLE,
  CONSTRAINT `fk_RACUN_ZA_KARTU_SLUZBENIK1`
    FOREIGN KEY (`SLUZBENIK_SluzbenikiID`)
    REFERENCES `movie`.`SLUZBENIK` (`SluzbenikiID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RACUN_ZA_KARTU_POSJETILAC1`
    FOREIGN KEY (`POSJETILAC_PosjetilacID`)
    REFERENCES `movie`.`POSJETILAC` (`PosjetilacID`)
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