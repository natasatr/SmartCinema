insert into adresa values (1,'Milana Tepica', 1, 0);
insert into adresa values (2,'Lovcenska', 27, 0);
insert into adresa values (3,'Dunavska', 132, 0);
insert into adresa values (4,'Djure Jaksica', 12, 0);
insert into adresa values (5,'Gavrila Principa', 86, 0);

insert into kino values (1,'Kino 1', 'kino1@gmail.com','051899558', 1,0);
insert into kino values (2,'Kino 2', 'kino2@gmail.com','051895748', 2,0);
insert into kino values (3,'Kino 3', 'kino3@gmail.com','051145236', 3,0);
insert into kino values (4,'Kino 4', 'kino4@gmail.com','051784178', 4,0);

insert into vrsta_sjedista values (1, 'Regularna sjedista', 0);
insert into vrsta_sjedista values (2, 'Ljubavna sjedista', 0);
insert into vrsta_sjedista values (3, 'VIP sjedista', 0);
insert into vrsta_sjedista values (4, 'Love box sjedista', 0);
insert into vrsta_sjedista values (5, 'VIP relax sjedista', 0);
insert into vrsta_sjedista values (6, 'Premium lounge fotelje', 0);

insert into posjetilac values (1, 'Natasa', 'Trivuncevic');
insert into posjetilac values (2, 'Ivona', 'Marmat');
insert into posjetilac values (3, 'Milovan', 'Bosancic');
insert into posjetilac values (4, 'Katarina', 'Antesevic');

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

insert into sala values (4, 1, 30, 0, 2);
insert into sala values (5, 2, 25, 0, 2);
insert into sala values (6, 3, 25, 0, 2);

insert into sala values (7, 1, 30, 0, 3);
insert into sala values (8, 2, 30, 0, 3);
insert into sala values (9, 3, 25, 0, 3);
insert into sala values (10, 4, 25, 0, 3);

insert into sala values (11, 1, 25, 0, 4);
insert into sala values (12, 2, 25, 0, 4);
insert into sala values (13, 3, 25, 0, 4);


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
insert into sjediste values (75, 3, 4, 0, 0, 3, 1);
insert into sjediste values (77, 4, 4, 0, 0, 3, 1);
insert into sjediste values (78, 5, 4, 0, 0, 3, 1);
insert into sjediste values (79, 6, 4, 0, 0, 3, 1);

insert into sjediste values (80, 1, 5, 0, 0, 3, 5);
insert into sjediste values (81, 2, 5, 0, 0, 3, 5);
insert into sjediste values (82, 3, 5, 0, 0, 3, 5);
insert into sjediste values (83, 4, 5, 0, 0, 3, 5);
insert into sjediste values (84, 5, 5, 0, 0, 3, 5);
insert into sjediste values (85, 6, 5, 0, 0, 3, 5);

-- sala 1 kino 2 id sale je 4
insert into sjediste values (86, 1, 1, 0, 0, 4, 1);
insert into sjediste values (87, 2, 1, 0, 0, 4, 1);
insert into sjediste values (89, 3, 1, 0, 0, 4, 1);
insert into sjediste values (90, 4, 1, 0, 0, 4, 1);
insert into sjediste values (91, 5, 1, 0, 0, 4, 1);
insert into sjediste values (92, 6, 1, 0, 0, 4, 1);

insert into sjediste values (93, 1, 2, 0, 0, 4, 1);
insert into sjediste values (94, 2, 2, 0, 0, 4, 1);
insert into sjediste values (95, 3, 2, 0, 0, 4, 1);
insert into sjediste values (96, 4, 2, 0, 0, 4, 1);
insert into sjediste values (97, 5, 2, 0, 0, 4, 1);
insert into sjediste values (98, 6, 2, 0, 0, 4, 1);

insert into sjediste values (99, 1, 3, 0, 0, 4, 1);
insert into sjediste values (100, 2, 3, 0, 0, 4, 1);
insert into sjediste values (101, 3, 3, 0, 0, 4, 1);
insert into sjediste values (102, 4, 3, 0, 0, 4, 1);
insert into sjediste values (103, 5, 3, 0, 0, 4, 1);
insert into sjediste values (104, 6, 3, 0, 0, 4, 1);

insert into sjediste values (105, 1, 4, 0, 0, 4, 4);
insert into sjediste values (106, 2, 4, 0, 0, 4, 4);
insert into sjediste values (107, 3, 4, 0, 0, 4, 4);
insert into sjediste values (108, 4, 4, 0, 0, 4, 4);
insert into sjediste values (109, 5, 4, 0, 0, 4, 4);
insert into sjediste values (110, 6, 4, 0, 0, 4, 4);

insert into sjediste values (111, 1, 5, 0, 0, 4, 5);
insert into sjediste values (112, 2, 5, 0, 0, 4, 5);
insert into sjediste values (113, 3, 5, 0, 0, 4, 5);
insert into sjediste values (114, 4, 5, 0, 0, 4, 5);
insert into sjediste values (115, 5, 5, 0, 0, 4, 5);
insert into sjediste values (116, 6, 5, 0, 0, 4, 5);

-- sala 2 kina 2 id sale je 5
insert into sjediste values (117, 1, 1, 0, 0, 5, 1);
insert into sjediste values (118, 2, 1, 0, 0, 5, 1);
insert into sjediste values (119, 3, 1, 0, 0, 5, 1);
insert into sjediste values (120, 4, 1, 0, 0, 5, 1);
insert into sjediste values (121, 5, 1, 0, 0, 5, 1);

insert into sjediste values (122, 1, 2, 0, 0, 5, 1);
insert into sjediste values (123, 2, 2, 0, 0, 5, 1);
insert into sjediste values (124, 3, 2, 0, 0, 5, 1);
insert into sjediste values (125, 4, 2, 0, 0, 5, 1);
insert into sjediste values (126, 5, 2, 0, 0, 5, 1);

insert into sjediste values (127, 1, 3, 0, 0, 5, 1);
insert into sjediste values (128, 2, 3, 0, 0, 5, 1);
insert into sjediste values (129, 3, 3, 0, 0, 5, 1);
insert into sjediste values (130, 4, 3, 0, 0, 5, 1);
insert into sjediste values (131, 5, 3, 0, 0, 5, 1);

insert into sjediste values (132, 1, 4, 0, 0, 5, 6);
insert into sjediste values (133, 2, 4, 0, 0, 5, 6);
insert into sjediste values (134, 3, 4, 0, 0, 5, 6);
insert into sjediste values (135, 4, 4, 0, 0, 5, 6);
insert into sjediste values (136, 5, 4, 0, 0, 5, 6);

insert into sjediste values (137, 1, 5, 0, 0, 5, 3);
insert into sjediste values (138, 2, 5, 0, 0, 5, 3);
insert into sjediste values (139, 3, 5, 0, 0, 5, 3);
insert into sjediste values (140, 4, 5, 0, 0, 5, 3);
insert into sjediste values (142, 5, 5, 0, 0, 5, 3);

-- sala 3 kina 2 id sale je 6
insert into sjediste values (143, 1, 1, 0, 0, 6, 1);
insert into sjediste values (144, 2, 1, 0, 0, 6, 1);
insert into sjediste values (145, 3, 1, 0, 0, 6, 1);
insert into sjediste values (146, 4, 1, 0, 0, 6, 1);
insert into sjediste values (147, 5, 1, 0, 0, 6, 1);

insert into sjediste values (148, 1, 2, 0, 0, 6, 1);
insert into sjediste values (149, 2, 2, 0, 0, 6, 1);
insert into sjediste values (150, 3, 2, 0, 0, 6, 1);
insert into sjediste values (151, 4, 2, 0, 0, 6, 1);
insert into sjediste values (152, 5, 2, 0, 0, 6, 1);

insert into sjediste values (153, 1, 3, 0, 0, 6, 1);
insert into sjediste values (154, 2, 3, 0, 0, 6, 1);
insert into sjediste values (155, 3, 3, 0, 0, 6, 1);
insert into sjediste values (156, 4, 3, 0, 0, 6, 1);
insert into sjediste values (157, 5, 3, 0, 0, 6, 1);

insert into sjediste values (158, 1, 4, 0, 0, 6, 6);
insert into sjediste values (159, 2, 4, 0, 0, 6, 6);
insert into sjediste values (160, 3, 4, 0, 0, 6, 6);
insert into sjediste values (161, 4, 4, 0, 0, 6, 6);
insert into sjediste values (162, 5, 4, 0, 0, 6, 6);

insert into sjediste values (163, 1, 5, 0, 0, 6, 3);
insert into sjediste values (164, 2, 5, 0, 0, 6, 3);
insert into sjediste values (165, 3, 5, 0, 0, 6, 3);
insert into sjediste values (166, 4, 5, 0, 0, 6, 3);
insert into sjediste values (167, 5, 5, 0, 0, 6, 3);

-- sala 1 kina 3 id sale je 7
insert into sjediste values (168, 1, 1, 0, 0, 7, 1);
insert into sjediste values (169, 2, 1, 0, 0, 7, 1);
insert into sjediste values (170, 3, 1, 0, 0, 7, 1);
insert into sjediste values (171, 4, 1, 0, 0, 7, 1);
insert into sjediste values (172, 5, 1, 0, 0, 7, 1);
insert into sjediste values (173, 6, 1, 0, 0, 7, 1);

insert into sjediste values (174, 1, 2, 0, 0, 7, 1);
insert into sjediste values (175, 2, 2, 0, 0, 7, 1);
insert into sjediste values (176, 3, 2, 0, 0, 7, 1);
insert into sjediste values (177, 4, 2, 0, 0, 7, 1);
insert into sjediste values (178, 5, 2, 0, 0, 7, 1);
insert into sjediste values (179, 6, 2, 0, 0, 7, 1);


insert into sjediste values (180, 1, 3, 0, 0, 7, 1);
insert into sjediste values (181, 2, 3, 0, 0, 7, 1);
insert into sjediste values (182, 3, 3, 0, 0, 7, 1);
insert into sjediste values (183, 4, 3, 0, 0, 7, 1);
insert into sjediste values (184, 5, 3, 0, 0, 7, 1);
insert into sjediste values (185, 6, 3, 0, 0, 7, 1);

insert into sjediste values (186, 1, 4, 0, 0, 7, 1);
insert into sjediste values (187, 2, 4, 0, 0, 7, 1);
insert into sjediste values (188, 3, 4, 0, 0, 7, 1);
insert into sjediste values (189, 4, 4, 0, 0, 7, 1);
insert into sjediste values (190, 5, 4, 0, 0, 7, 1);
insert into sjediste values (191, 6, 4, 0, 0, 7, 1);

insert into sjediste values (192, 1, 5, 0, 0, 7, 5);
insert into sjediste values (193, 2, 5, 0, 0, 7, 5);
insert into sjediste values (194, 3, 5, 0, 0, 7, 5);
insert into sjediste values (195, 4, 5, 0, 0, 7, 5);
insert into sjediste values (196, 5, 5, 0, 0, 7, 5);
insert into sjediste values (197, 6, 5, 0, 0, 7, 5);

-- sala 2 kina 3 id sale je 8
insert into sjediste values (198, 1, 1, 0, 0, 8, 1);
insert into sjediste values (199, 2, 1, 0, 0, 8, 1);
insert into sjediste values (200, 3, 1, 0, 0, 8, 1);
insert into sjediste values (201, 4, 1, 0, 0, 8, 1);
insert into sjediste values (202, 5, 1, 0, 0, 8, 1);
insert into sjediste values (203, 6, 1, 0, 0, 8, 1);

insert into sjediste values (204, 1, 2, 0, 0, 7, 1);
insert into sjediste values (205, 2, 2, 0, 0, 7, 1);
insert into sjediste values (206, 3, 2, 0, 0, 7, 1);
insert into sjediste values (207, 4, 2, 0, 0, 7, 1);
insert into sjediste values (208, 5, 2, 0, 0, 7, 1);
insert into sjediste values (209, 6, 2, 0, 0, 7, 1);


insert into sjediste values (210, 1, 3, 0, 0, 8, 1);
insert into sjediste values (211, 2, 3, 0, 0, 8, 1);
insert into sjediste values (212, 3, 3, 0, 0, 8, 1);
insert into sjediste values (213, 4, 3, 0, 0, 8, 1);
insert into sjediste values (214, 5, 3, 0, 0, 8, 1);
insert into sjediste values (215, 6, 3, 0, 0, 8, 1);

insert into sjediste values (216, 1, 4, 0, 0, 8, 3);
insert into sjediste values (217, 2, 4, 0, 0, 8, 3);
insert into sjediste values (218, 3, 4, 0, 0, 8, 3);
insert into sjediste values (219, 4, 4, 0, 0, 8, 3);
insert into sjediste values (220, 5, 4, 0, 0, 8, 3);
insert into sjediste values (221, 6, 4, 0, 0, 8, 3);

insert into sjediste values (222, 1, 5, 0, 0, 8, 5);
insert into sjediste values (223, 2, 5, 0, 0, 8, 5);
insert into sjediste values (224, 3, 5, 0, 0, 8, 5);
insert into sjediste values (225, 4, 5, 0, 0, 8, 5);
insert into sjediste values (226, 5, 5, 0, 0, 8, 5);
insert into sjediste values (227, 6, 5, 0, 0, 8, 5);

-- sala 3 kina 3 id sale je 9
insert into sjediste values (228, 1, 1, 0, 0, 9, 1);
insert into sjediste values (229, 2, 1, 0, 0, 9, 1);
insert into sjediste values (230, 3, 1, 0, 0, 9, 1);
insert into sjediste values (231, 4, 1, 0, 0, 9, 1);
insert into sjediste values (232, 5, 1, 0, 0, 9, 1);

insert into sjediste values (233, 1, 2, 0, 0, 9, 1);
insert into sjediste values (234, 2, 2, 0, 0, 9, 1);
insert into sjediste values (235, 3, 2, 0, 0, 9, 1);
insert into sjediste values (236, 4, 2, 0, 0, 9, 1);
insert into sjediste values (237, 5, 2, 0, 0, 9, 1);

insert into sjediste values (238, 1, 3, 0, 0, 9, 1);
insert into sjediste values (239, 2, 3, 0, 0, 9, 1);
insert into sjediste values (240, 3, 3, 0, 0, 9, 1);
insert into sjediste values (241, 4, 3, 0, 0, 9, 1);
insert into sjediste values (242, 5, 3, 0, 0, 9, 1);

insert into sjediste values (243, 1, 4, 0, 0, 9, 6);
insert into sjediste values (244, 2, 4, 0, 0, 9, 6);
insert into sjediste values (245, 3, 4, 0, 0, 9, 6);
insert into sjediste values (246, 4, 4, 0, 0, 9, 6);
insert into sjediste values (247, 5, 4, 0, 0, 9, 6);

insert into sjediste values (248, 1, 5, 0, 0, 9, 3);
insert into sjediste values (249, 2, 5, 0, 0, 9, 3);
insert into sjediste values (250, 3, 5, 0, 0, 9, 3);
insert into sjediste values (251, 4, 5, 0, 0, 9, 3);
insert into sjediste values (252, 5, 5, 0, 0, 9, 3);

-- sala 4 kina 3 id sale je 10
insert into sjediste values (253, 1, 1, 0, 0, 10, 1);
insert into sjediste values (254, 2, 1, 0, 0, 10, 1);
insert into sjediste values (255, 3, 1, 0, 0, 10, 1);
insert into sjediste values (256, 4, 1, 0, 0, 10, 1);
insert into sjediste values (257, 5, 1, 0, 0, 10, 1);

insert into sjediste values (258, 1, 2, 0, 0, 10, 1);
insert into sjediste values (259, 2, 2, 0, 0, 10, 1);
insert into sjediste values (260, 3, 2, 0, 0, 10, 1);
insert into sjediste values (261, 4, 2, 0, 0, 10, 1);
insert into sjediste values (262, 5, 2, 0, 0, 10, 1);

insert into sjediste values (263, 1, 3, 0, 0, 10, 1);
insert into sjediste values (264, 2, 3, 0, 0, 10, 1);
insert into sjediste values (265, 3, 3, 0, 0, 10, 1);
insert into sjediste values (266, 4, 3, 0, 0, 10, 1);
insert into sjediste values (267, 5, 3, 0, 0, 10, 1);

insert into sjediste values (268, 1, 4, 0, 0, 10, 6);
insert into sjediste values (269, 2, 4, 0, 0, 10, 6);
insert into sjediste values (270, 3, 4, 0, 0, 10, 6);
insert into sjediste values (271, 4, 4, 0, 0, 10, 6);
insert into sjediste values (272, 5, 4, 0, 0, 10, 6);

insert into sjediste values (273, 1, 5, 0, 0, 10, 3);
insert into sjediste values (274, 2, 5, 0, 0, 10, 3);
insert into sjediste values (275, 3, 5, 0, 0, 10, 3);
insert into sjediste values (276, 4, 5, 0, 0, 10, 3);
insert into sjediste values (277, 5, 5, 0, 0, 10, 3);

-- sala 1 kina 4 id sale je 11
insert into sjediste values (278, 1, 1, 0, 0, 11, 1);
insert into sjediste values (279, 2, 1, 0, 0, 11, 1);
insert into sjediste values (280, 3, 1, 0, 0, 11, 1);
insert into sjediste values (281, 4, 1, 0, 0, 11, 1);
insert into sjediste values (282, 5, 1, 0, 0, 11, 1);

insert into sjediste values (283, 1, 2, 0, 0, 11, 1);
insert into sjediste values (284, 2, 2, 0, 0, 11, 1);
insert into sjediste values (285, 3, 2, 0, 0, 11, 1);
insert into sjediste values (286, 4, 2, 0, 0, 11, 1);
insert into sjediste values (287, 5, 2, 0, 0, 11, 1);

insert into sjediste values (288, 1, 3, 0, 0, 11, 1);
insert into sjediste values (289, 2, 3, 0, 0, 11, 1);
insert into sjediste values (290, 3, 3, 0, 0, 11, 1);
insert into sjediste values (291, 4, 3, 0, 0, 11, 1);
insert into sjediste values (292, 5, 3, 0, 0, 11, 1);

insert into sjediste values (293, 1, 4, 0, 0, 11, 6);
insert into sjediste values (294, 2, 4, 0, 0, 11, 6);
insert into sjediste values (295, 3, 4, 0, 0, 11, 6);
insert into sjediste values (296, 4, 4, 0, 0, 11, 6);
insert into sjediste values (297, 5, 4, 0, 0, 11, 6);

insert into sjediste values (298, 1, 5, 0, 0, 11, 2);
insert into sjediste values (299, 2, 5, 0, 0, 11, 2);
insert into sjediste values (300, 3, 5, 0, 0, 11, 2);
insert into sjediste values (301, 4, 5, 0, 0, 11, 2);
insert into sjediste values (302, 5, 5, 0, 0, 11, 2);

-- sala 2 kina 4 id sale je 12
insert into sjediste values (303, 1, 1, 0, 0, 12, 1);
insert into sjediste values (304, 2, 1, 0, 0, 12, 1);
insert into sjediste values (305, 3, 1, 0, 0, 12, 1);
insert into sjediste values (306, 4, 1, 0, 0, 12, 1);
insert into sjediste values (307, 5, 1, 0, 0, 12, 1);

insert into sjediste values (308, 1, 2, 0, 0, 12, 1);
insert into sjediste values (310, 2, 2, 0, 0, 12, 1);
insert into sjediste values (311, 3, 2, 0, 0, 12, 1);
insert into sjediste values (312, 4, 2, 0, 0, 12, 1);
insert into sjediste values (313, 5, 2, 0, 0, 12, 1);

insert into sjediste values (314, 1, 3, 0, 0, 12, 3);
insert into sjediste values (315, 2, 3, 0, 0, 12, 3);
insert into sjediste values (316, 3, 3, 0, 0, 12, 3);
insert into sjediste values (317, 4, 3, 0, 0, 12, 3);
insert into sjediste values (318, 5, 3, 0, 0, 12, 3);

insert into sjediste values (319, 1, 4, 0, 0, 12, 6);
insert into sjediste values (320, 2, 4, 0, 0, 12, 6);
insert into sjediste values (321, 3, 4, 0, 0, 12, 6);
insert into sjediste values (322, 4, 4, 0, 0, 12, 6);
insert into sjediste values (323, 5, 4, 0, 0, 12, 6);

insert into sjediste values (324, 1, 5, 0, 0, 12, 2);
insert into sjediste values (325, 2, 5, 0, 0, 12, 2);
insert into sjediste values (326, 3, 5, 0, 0, 12, 2);
insert into sjediste values (327, 4, 5, 0, 0, 12, 2);
insert into sjediste values (328, 5, 5, 0, 0, 12, 2);

-- sala 3 kina 4 id sale je 13
insert into sjediste values (329, 1, 1, 0, 0, 13, 1);
insert into sjediste values (330, 2, 1, 0, 0, 13, 1);
insert into sjediste values (331, 3, 1, 0, 0, 13, 1);
insert into sjediste values (332, 4, 1, 0, 0, 13, 1);
insert into sjediste values (333, 5, 1, 0, 0, 13, 1);

insert into sjediste values (334, 1, 2, 0, 0, 13, 1);
insert into sjediste values (335, 2, 2, 0, 0, 13, 1);
insert into sjediste values (336, 3, 2, 0, 0, 13, 1);
insert into sjediste values (337, 4, 2, 0, 0, 13, 1);
insert into sjediste values (338, 5, 2, 0, 0, 13, 1);

insert into sjediste values (339, 1, 4, 0, 0, 13,5);
insert into sjediste values (340, 2, 4, 0, 0, 13,5);
insert into sjediste values (341, 3, 4, 0, 0, 13,5);
insert into sjediste values (342, 4, 4, 0, 0, 13,5);
insert into sjediste values (343, 5, 4, 0, 0, 13,5);

insert into sjediste values (344, 1, 5, 0, 0, 13, 6);
insert into sjediste values (345, 2, 5, 0, 0, 13, 6);
insert into sjediste values (346, 3, 5, 0, 0, 13, 6);
insert into sjediste values (347, 4, 5, 0, 0, 13, 6);
insert into sjediste values (348, 5, 5, 0, 0, 13, 6);

insert into sjediste values (349, 1, 3, 0, 0, 13, 3);
insert into sjediste values (350, 2, 3, 0, 0, 13, 3);
insert into sjediste values (351, 3, 3, 0, 0, 13, 3);
insert into sjediste values (352, 4, 3, 0, 0, 13, 3);
insert into sjediste values (353, 5, 3, 0, 0, 13, 3);