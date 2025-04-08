CREATE TABLE webdyn_S4_users(
    id int primary key auto_increment,
    login varchar(30),
    mdp varchar(10)
);

INSERT INTO webdyn_S4_users (login, mdp) VALUES 
('alice', 'pass123'),
('bob', 'secret01'),
('charlie', 'azerty12'),
('diana', 'qwerty34'),
('eve', 'admin007');


CREATE TABLE webdyn_S4_credit(
    id int primary key auto_increment,
    libelle varchar (50),
    montant decimal(10,2)
);

CREATE TABLE webdyn_S4_depense (
    id int primary key auto_increment,
    id_credit int references webdyn_S4_credit(id),
    montant decimal(10,2),
    date DATE
);



