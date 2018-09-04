CREATE TABLE IF NOT EXISTS Kilpailu (
kilpailuId integer auto_increment PRIMARY KEY,
nimi varchar(40),
pvm date,
paikka varchar(100)
);

CREATE TABLE IF NOT EXISTS OsaAlue(
osaAlueId integer auto_increment PRIMARY KEY,
nimi varchar(40),
painoarvo integer,
kilpailuId integer,
FOREIGN KEY (kilpailuId) REFERENCES Kilpailu(kilpailuId)
);

CREATE TABLE IF NOT EXISTS Kriteeri(
kriteeriId integer auto_increment PRIMARY KEY,
teksti varchar(400),
OsaAlueId integer,
FOREIGN KEY (OsaAlueId) REFERENCES OsaAlue(OsaAlueId)
);


CREATE TABLE IF NOT EXISTS Kilpailija(
KilpailijaId integer auto_increment PRIMARY KEY,
etunimi varchar(40),
sukunimi varchar(100),
kilpailijanro integer,
kilpailuId integer,
FOREIGN KEY (kilpailuId) REFERENCES Kilpailu(kilpailuId),
koulu varchar(100)
);

CREATE TABLE IF NOT EXISTS Tuomari(
tuomari_id integer auto_increment PRIMARY KEY,
tuomarinro integer,
kilpailuId integer,
FOREIGN KEY(kilpailuId) REFERENCES Kilpailu(kilpailuId)
);

CREATE TABLE IF NOT EXISTS Ostaja(
ostaja_id integer auto_increment PRIMARY KEY,
Ostajan_nimi varchar(100),
kilpailuId integer,
FOREIGN KEY (kilpailuId) REFERENCES Kilpailu(kilpailuId)
);

CREATE TABLE IF NOT EXISTS Arviointi(
arviointiId integer auto_increment PRIMARY KEY,
tuomarinro integer,
KilpailijaId integer,
FOREIGN KEY (KilpailijaId) REFERENCES Kilpailija(KilpailijaId),
pvm date,
kilpailuId integer,
FOREIGN KEY (kilpailuId) REFERENCES Kilpailu(kilpailuId)
);

CREATE TABLE IF NOT EXISTS OsaAluepiste(
OsaAluepisteId integer auto_increment PRIMARY KEY,
Keskiarvo decimal(5,2),
arviointiId integer,
FOREIGN KEY (arviointiId) REFERENCES Arviointi(arviointiId)
);

CREATE TABLE IF NOT EXISTS Kriteeripiste(
kriteeripiste_id integer auto_increment PRIMARY KEY,
arvo integer,
kriteeriId integer,
FOREIGN KEY (kriteeriId) REFERENCES Kriteeri(kriteeriId),
arviointiId integer,
FOREIGN KEY (arviointiId) REFERENCES Arviointi(arviointiId)
);

