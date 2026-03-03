-- Kategorien anlegen
INSERT INTO category (id, name) VALUES (1, 'Motorboot-Recht');
INSERT INTO category (id, name) VALUES (2, 'Navigation');
INSERT INTO category (id, name) VALUES (3, 'Technik & Umwelt');

-- Kategorie 1: Motorboot-Recht
INSERT INTO question (id, text, correct_answer_index, category_id) VALUES (1, 'Ab welcher Motorleistung ist auf deutschen Binnenschifffahrtsstraßen ein Sportbootführerschein erforderlich?', 2, 1);
INSERT INTO question_options (question_id, options) VALUES (1, 'Ab 5 PS (3,68 kW)'), (1, 'Ab 10 PS (7,35 kW)'), (1, 'Ab 15 PS (11,03 kW)'), (1, 'Es ist nie ein Schein erforderlich');

INSERT INTO question (id, text, correct_answer_index, category_id) VALUES (2, 'Was bedeutet das Gebot der allgemeinen Sorgfaltspflicht auf dem Wasser?', 0, 1);
INSERT INTO question_options (question_id, options) VALUES (2, 'Gefährdung von Menschenleben und Sachschäden vermeiden'), (2, 'Immer so schnell wie möglich fahren'), (2, 'Nur bei gutem Wetter auslaufen'), (2, 'Den Anker immer zwei Tage im Voraus setzen');

INSERT INTO question (id, text, correct_answer_index, category_id) VALUES (3, 'Welche Seite eines Schiffes wird als "Backbord" bezeichnet?', 1, 1);
INSERT INTO question_options (question_id, options) VALUES (3, 'Die rechte Seite'), (3, 'Die linke Seite'), (3, 'Die hintere Seite'), (3, 'Die Seite, auf der gekocht wird');

INSERT INTO question (id, text, correct_answer_index, category_id) VALUES (4, 'Welches Schallsignal gibt ein Fahrzeug ab, das seinen Kurs nach Steuerbord (rechts) ändert?', 3, 1);
INSERT INTO question_options (question_id, options) VALUES (4, 'Zwei kurze Töne'), (4, 'Drei kurze Töne'), (4, 'Ein langer Ton'), (4, 'Ein kurzer Ton');

-- Kategorie 2: Navigation
INSERT INTO question (id, text, correct_answer_index, category_id) VALUES (3, 'Wie wird die Geschwindigkeit eines Schiffes im Wasser gemessen?', 3, 2);
INSERT INTO question_options (question_id, options) VALUES (3, 'In Kilometern pro Stunde (km/h)'), (3, 'In Meilen pro Tag'), (3, 'In Litern pro Sekunde'), (3, 'In Knoten (kn)');

INSERT INTO question (id, text, correct_answer_index, category_id) VALUES (4, 'Was gibt die "Missweisung" auf einer Seekarte an?', 1, 2);
INSERT INTO question_options (question_id, options) VALUES (4, 'Den Abstand zum nächsten Hafen'), (4, 'Winkel zwischen geografischem und magnetischem Nordpol'), (4, 'Die Tiefe des Wassers bei Ebbe'), (4, 'Die maximale Wellenhöhe');

-- Kategorie 3: Technik & Umwelt
INSERT INTO question (id, text, correct_answer_index, category_id) VALUES (5, 'Warum darf kein Altöl in das Bilgewasser gelangen?', 0, 3);
INSERT INTO question_options (question_id, options) VALUES (5, 'Weil es beim Lenzen die Umwelt schwer belastet'), (5, 'Weil das Öl den Motor verstopft'), (5, 'Weil das Boot sonst schwerer wird'), (5, 'Es darf hineingelangen, Öl reinigt Wasser');

INSERT INTO question (id, text, correct_answer_index, category_id) VALUES (6, 'Was ist die Hauptaufgabe des Impellers in einem Außenbordmotor?', 2, 3);
INSERT INTO question_options (question_id, options) VALUES (6, 'Die Zündkerzen reinigen'), (6, 'Den Anker lichten'), (6, 'Kühlwasser durch den Motor fördern'), (6, 'Die Schraube schneller drehen lassen');