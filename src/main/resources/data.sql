CREATE TABLE Usuario (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255),
  email VARCHAR(255),
  senha VARCHAR(255)
);
CREATE TABLE Perfil (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255)
);

CREATE TABLE Usuario_Perfil (
  usuario_id BIGINT,
  perfil_id BIGINT,
  FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
  FOREIGN KEY (perfil_id) REFERENCES Perfil(id)
);


insert into Perfil (id, nome) values (1, 'ROLE_USER');
insert into Perfil (id, nome) values (2, 'ROLE_ADMIN');
INSERT INTO Usuario (nome, email, senha) VALUES ('Admin', 'email@admin.com', '$2a$12$23A4Gd/OuAxE/fxRSl/lJ.6KF6wsJa4MC1a03dQewZrlxsJDpro96');
INSERT INTO Usuario_Perfil(usuario_id, perfil_id) VALUES (1,2);


