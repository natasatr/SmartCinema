insert into adresa (Mjesto, Ulica, Broj, Uklonjeno)  values ('Banja Luka','Milana Tepica', 1, 0);
insert into adresa (Mjesto, Ulica, Broj, Uklonjeno)  values ('Banja Luka','Lovcenska', 27, 0);
insert into adresa  (Mjesto, Ulica, Broj, Uklonjeno) values ('Banja Luka','Dunavska', 132, 0);
insert into adresa (Mjesto, Ulica, Broj, Uklonjeno)  values ('Prijedor','Djure Jaksica', 12, 0);
insert into adresa (Mjesto, Ulica, Broj, Uklonjeno)  values ('Prijedor','Gavrila Principa', 86, 0);

insert into kino values (1,'Kino Banja Luka', 'kino1@gmail.com','051899558', 1,0);

insert into vrsta_sjedista values (1, 'Regularna sjedista');
insert into vrsta_sjedista values (2, 'Ljubavna sjedista');
insert into vrsta_sjedista values (3, 'VIP sjedista');
insert into vrsta_sjedista values (4, 'Love box sjedista');
insert into vrsta_sjedista values (5, 'VIP relax sjedista');
insert into vrsta_sjedista values (6, 'Premium lounge fotelje');


insert into dodatna_ponuda values (1, 'Kokice male' , 2.00);
insert into dodatna_ponuda values (2, 'Kokice srednje' , 3.00);
insert into dodatna_ponuda values (3, 'Kokice velike' , 4.00);
insert into dodatna_ponuda values (4, 'Coca cola' , 2.50);
insert into dodatna_ponuda values (5, 'Ledeni caj' , 2.50);
insert into dodatna_ponuda values (6, 'Voda' , 1.50);
insert into dodatna_ponuda values (7, 'Cips mali' , 1.00);
insert into dodatna_ponuda values (8, 'Cips srednji' , 2.00);
insert into dodatna_ponuda values (9, 'Cips veliki' , 3.00);


insert into sala values (1, 1, 30, 0, 1);
insert into sala values (2, 2, 25, 0, 1);
insert into sala values (3, 3, 30, 0, 1);
insert into sala values (4, 4 , 50, 0, 1);


-- sala 1 kino 1
insert into sjediste values (1, 1, 1, 0, 0, 1, 1);
insert into sjediste values (2, 2, 1, 0, 0, 1, 1);
insert into sjediste values (3, 3, 1, 0, 0, 1, 1);
insert into sjediste values (4, 4, 1, 0, 0, 1, 1);
insert into sjediste values (5, 5, 1, 0, 0, 1, 1);
insert into sjediste values (6, 6, 1, 0, 0, 1, 1);

insert into sjediste values (7, 1, 2, 0, 0, 1, 1);
insert into sjediste values (8, 2, 2, 0, 0, 1, 1);
insert into sjediste values (9, 3, 2, 0, 0, 1, 1);
insert into sjediste values (10, 4, 2, 0, 0, 1, 1);
insert into sjediste values (11, 5, 2, 0, 0, 1, 1);
insert into sjediste values (12, 6, 2, 0, 0, 1, 1);


insert into sjediste values (13, 1, 3, 0, 0, 1, 1);
insert into sjediste values (14, 2, 3, 0, 0, 1, 1);
insert into sjediste values (15, 3, 3, 0, 0, 1, 1);
insert into sjediste values (16, 4, 3, 0, 0, 1, 1);
insert into sjediste values (17, 5, 3, 0, 0, 1, 1);
insert into sjediste values (18, 6, 3, 0, 0, 1, 1);

insert into sjediste values (19, 1, 4, 0, 0, 1, 1);
insert into sjediste values (20, 2, 4, 0, 0, 1, 1);
insert into sjediste values (21, 3, 4, 0, 0, 1, 1);
insert into sjediste values (22, 4, 4, 0, 0, 1, 1);
insert into sjediste values (23, 5, 4, 0, 0, 1, 1);
insert into sjediste values (24, 6, 4, 0, 0, 1, 1);

insert into sjediste values (25, 1, 5, 0, 0, 1, 1);
insert into sjediste values (26, 2, 5, 0, 0, 1, 1);
insert into sjediste values (27, 3, 5, 0, 0, 1, 1);
insert into sjediste values (28, 4, 5, 0, 0, 1, 1);
insert into sjediste values (29, 5, 5, 0, 0, 1, 1);
insert into sjediste values (30, 6, 5, 0, 0, 1, 1);

-- sala 2 kino 1
insert into sjediste values (31, 1, 1, 0, 0, 2, 1);
insert into sjediste values (32, 2, 1, 0, 0, 2, 1);
insert into sjediste values (33, 3, 1, 0, 0, 2, 1);
insert into sjediste values (34, 4, 1, 0, 0, 2, 1);
insert into sjediste values (35, 5, 1, 0, 0, 2, 1);

insert into sjediste values (36, 1, 2, 0, 0, 2, 1);
insert into sjediste values (37, 2, 2, 0, 0, 2, 1);
insert into sjediste values (38, 3, 2, 0, 0, 2, 1);
insert into sjediste values (39, 4, 2, 0, 0, 2, 1);
insert into sjediste values (40, 5, 2, 0, 0, 2, 1);

insert into sjediste values (41, 1, 3, 0, 0, 2, 1);
insert into sjediste values (42, 2, 3, 0, 0, 2, 1);
insert into sjediste values (43, 3, 3, 0, 0, 2, 1);
insert into sjediste values (44, 4, 3, 0, 0, 2, 1);
insert into sjediste values (45, 5, 3, 0, 0, 2, 1);

insert into sjediste values (46, 1, 4, 0, 0, 2, 6);
insert into sjediste values (47, 2, 4, 0, 0, 2, 6);
insert into sjediste values (48, 3, 4, 0, 0, 2, 6);
insert into sjediste values (49, 4, 4, 0, 0, 2, 6);
insert into sjediste values (50, 5, 4, 0, 0, 2, 6);

insert into sjediste values (51, 1, 5, 0, 0, 2, 3);
insert into sjediste values (52, 2, 5, 0, 0, 2, 3);
insert into sjediste values (53, 3, 5, 0, 0, 2, 3);
insert into sjediste values (54, 4, 5, 0, 0, 2, 3);
insert into sjediste values (55, 5, 5, 0, 0, 2, 3);

-- sala 3 kino 1
insert into sjediste values (56, 1, 1, 0, 0, 3, 1);
insert into sjediste values (57, 2, 1, 0, 0, 3, 1);
insert into sjediste values (58, 3, 1, 0, 0, 3, 1);
insert into sjediste values (59, 4, 1, 0, 0, 3, 1);
insert into sjediste values (60, 5, 1, 0, 0, 3, 1);
insert into sjediste values (61, 6, 1, 0, 0, 3, 1);

insert into sjediste values (62, 1, 2, 0, 0, 3, 1);
insert into sjediste values (63, 2, 2, 0, 0, 3, 1);
insert into sjediste values (64, 3, 2, 0, 0, 3, 1);
insert into sjediste values (65, 4, 2, 0, 0, 3, 1);
insert into sjediste values (66, 5, 2, 0, 0, 3, 1);
insert into sjediste values (67, 6, 2, 0, 0, 3, 1);


insert into sjediste values (68, 1, 3, 0, 0, 3, 1);
insert into sjediste values (69, 2, 3, 0, 0, 3, 1);
insert into sjediste values (70, 3, 3, 0, 0, 3, 1);
insert into sjediste values (71, 4, 3, 0, 0, 3, 1);
insert into sjediste values (72, 5, 3, 0, 0, 3, 1);
insert into sjediste values (73, 6, 3, 0, 0, 3, 1);

insert into sjediste values (74, 1, 4, 0, 0, 3, 1);
insert into sjediste values (75, 2, 4, 0, 0, 3, 1);
insert into sjediste values (76, 3, 4, 0, 0, 3, 1);
insert into sjediste values (77, 4, 4, 0, 0, 3, 1);
insert into sjediste values (78, 5, 4, 0, 0, 3, 1);
insert into sjediste values (79, 6, 4, 0, 0, 3, 1);

insert into sjediste values (80, 1, 5, 0, 0, 3, 5);
insert into sjediste values (81, 2, 5, 0, 0, 3, 5);
insert into sjediste values (82, 3, 5, 0, 0, 3, 5);
insert into sjediste values (83, 4, 5, 0, 0, 3, 5);
insert into sjediste values (84, 5, 5, 0, 0, 3, 5);
insert into sjediste values (85, 6, 5, 0, 0, 3, 5);

-- sala 4 kino 1

insert into sjediste values (86, 1 , 1, 0 , 0 , 4, 1);
insert into sjediste values (87, 2 , 1, 0 , 0 , 4, 1);
insert into sjediste values (88, 3 , 1, 0 , 0 , 4, 1);
insert into sjediste values (89, 4 , 1, 0 , 0 , 4, 1);
insert into sjediste values (90, 5 , 1, 0 , 0 , 4, 1);
insert into sjediste values (91, 6 , 1, 0 , 0 , 4, 1);
insert into sjediste values (92, 7 , 1, 0 , 0 , 4, 1);
insert into sjediste values (93, 8 , 1, 0 , 0 , 4, 1);
insert into sjediste values (94, 9 , 1, 0 , 0 , 4, 1);
insert into sjediste values (95, 10 , 1, 0 , 0 , 4, 1);

insert into sjediste values (96, 1 , 2, 0 , 0 , 4, 1);
insert into sjediste values (97, 2 , 2, 0 , 0 , 4, 1);
insert into sjediste values (98, 3 , 2, 0 , 0 , 4, 1);
insert into sjediste values (99, 4 , 2, 0 , 0 , 4, 1);
insert into sjediste values (100, 5 , 2, 0 , 0 , 4, 1);
insert into sjediste values (101, 6 , 2, 0 , 0 , 4, 1);
insert into sjediste values (102, 7 , 2, 0 , 0 , 4, 1);
insert into sjediste values (103, 8 , 2, 0 , 0 , 4, 1);
insert into sjediste values (104, 9 , 2, 0 , 0 , 4, 1);
insert into sjediste values (105, 10 ,2, 0 , 0 , 4, 1);

insert into sjediste values (106, 1 , 3, 0 , 0 , 4, 1);
insert into sjediste values (107, 2 , 3, 0 , 0 , 4, 1);
insert into sjediste values (108, 3 , 3, 0 , 0 , 4, 1);
insert into sjediste values (109, 4 , 3, 0 , 0 , 4, 1);
insert into sjediste values (110, 5 , 3, 0 , 0 , 4, 1);
insert into sjediste values (111, 6 , 3, 0 , 0 , 4, 1);
insert into sjediste values (112, 7 , 3, 0 , 0 , 4, 1);
insert into sjediste values (113, 8 , 3, 0 , 0 , 4, 1);
insert into sjediste values (114, 9 , 3, 0 , 0 , 4, 1);
insert into sjediste values (115, 10 ,3, 0 , 0 , 4, 1);

insert into sjediste values (116, 1 , 4, 0 , 0 , 4, 3);
insert into sjediste values (117, 2 , 4, 0 , 0 , 4, 3);
insert into sjediste values (118, 3 , 4, 0 , 0 , 4, 3);
insert into sjediste values (119, 4 , 4, 0 , 0 , 4, 3);
insert into sjediste values (120, 5 , 4, 0 , 0 , 4, 3);
insert into sjediste values (121, 6 , 4, 0 , 0 , 4, 3);
insert into sjediste values (122, 7 , 4, 0 , 0 , 4, 3);
insert into sjediste values (123, 8 , 4, 0 , 0 , 4, 3);
insert into sjediste values (124, 9 , 4, 0 , 0 , 4, 3);
insert into sjediste values (125, 10 ,4, 0 , 0 , 4, 3);

insert into sjediste values (126, 1 , 5, 0 , 0 , 4, 5);
insert into sjediste values (127, 2 , 5, 0 , 0 , 4, 5);
insert into sjediste values (128, 3 , 5, 0 , 0 , 4, 5);
insert into sjediste values (129, 4 , 5, 0 , 0 , 4, 5);
insert into sjediste values (130, 5 , 5, 0 , 0 , 4, 5);
insert into sjediste values (131, 6 , 5, 0 , 0 , 4, 5);
insert into sjediste values (132, 7 , 5, 0 , 0 , 4, 5);
insert into sjediste values (133, 8 , 5, 0 , 0 , 4, 5);
insert into sjediste values (134, 9 , 5, 0 , 0 , 4, 5);
insert into sjediste values (135, 10 ,5, 0 , 0 , 4, 5);

insert into rola values (1,'Administrator1');
insert into rola values (2,'Administrator2');
insert into rola values (3,'Sluzbenik');

insert into nalog values (1,'korisnik1','$2a$12$EkazOUR.cmIlUrC7UYRqL.gITTendUcW2Y.sJ.ZvjWZdl5QWck7OW',0,1);
insert into nalog values (2,'korisnik2','$2a$12$Ph1IO2P5XclpMbw7v2sPy.bKCfxzJFGi1gdT1YlqKo7Ajb2oCpFnO',0,2);
insert into nalog values (3,'korisnik3','$2a$12$mucCHXv5BYobH93jx49h1.o.kAIr1fEcZMdLpnSd6Tpx.4yutX6gm',0,3);

insert into zaposleni values (1,'0101981123418','Svjetlana','Ljubičić',1800,'svjetlana.ljubicic@gmail.com',0,1,2);
insert into zaposleni values (2,'082987145235','Biljana','Marković',1800,'biljana.markovic@gmail.com',0,2,3);
insert into zaposleni values (3,'0205978124748','Martin','Blagojević',1600,'martin.blagojevic@gmail.com',0,3,4);