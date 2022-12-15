
-- -----------------------------------------------------
-- Schema bookStoreDb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bookStoreDb` ;

-- -----------------------------------------------------
-- Schema bookStoreDb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bookStoreDb` DEFAULT CHARACTER SET utf8 ;
USE `bookStoreDb` ;

-- -----------------------------------------------------
-- Table `bookStoreDb`.`Escritor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookStoreDb`.`Escritor` (
  `idEscritor` INT NOT NULL AUTO_INCREMENT,
  `primeiroNome` VARCHAR(45) NULL,
  `sobrenome` VARCHAR(45) NULL,
  PRIMARY KEY (`idEscritor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookStoreDb`.`Editora`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookStoreDb`.`Editora` (
  `idEditora` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEditora`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookStoreDb`.`livros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookStoreDb`.`livros` (
  `idlivros` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NULL,
  `genero` VARCHAR(45) NULL,
  `ISBN` VARCHAR(17) NULL,
  `preco` DOUBLE NULL,
  `idEscritor` INT NOT NULL,
  `idEditora` INT NULL,
  PRIMARY KEY (`idlivros`),
  INDEX `fk_livros_Escritor1_idx` (`idEscritor` ASC) VISIBLE,
  INDEX `fk_livros_Editora1_idx` (`idEditora` ASC) VISIBLE,
  CONSTRAINT `fk_livros_Escritor1`
    FOREIGN KEY (`idEscritor`)
    REFERENCES `bookStoreDb`.`Escritor` (`idEscritor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_livros_Editora1`
    FOREIGN KEY (`idEditora`)
    REFERENCES `bookStoreDb`.`Editora` (`idEditora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bookStoreDb`.`inventorio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookStoreDb`.`inventorio` (
  `idlivros` INT NOT NULL AUTO_INCREMENT,
  `quantidade` INT NULL,
  PRIMARY KEY (`idlivros`),
  CONSTRAINT `fk_inventorio_livros`
    FOREIGN KEY (`idlivros`)
    REFERENCES `bookStoreDb`.`livros` (`idlivros`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
