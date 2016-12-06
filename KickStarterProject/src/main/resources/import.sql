INSERT INTO users VALUES (NULL, 500.0, 'admin', 'admin'), (NULL, 500.0, 'password', 'smithy12'),(NULL, 500.0, 'pass', 'user'),(NULL, 100.0, 'password', 'murph12'),(NULL, 1000.0, 'password', 'jonesy12'),(NULL, 400.0, 'password', 'damo12');

INSERT INTO projects VALUES (NULL, timestamp'2016-09-09 09:30:25 GMT', '2016-12-15','Description 1', 1200.00, '../images/money_tree.jpg', 'Money Tree', 1, 1);
INSERT INTO projects VALUES (NULL, timestamp'2016-10-10 09:30:25 GMT', '2016-12-25','Description 2', 1249.00, '../images/cats_eyes.png', 'Cat Contact Lenses',  1, 2);
INSERT INTO projects VALUES (NULL, timestamp'2016-11-11 09:30:25 GMT', '2016-12-31','Description 3', 3000.00, '../images/tea_bags.jpg', 'Washable Teabags', 1,  3);
INSERT INTO projects VALUES (NULL, timestamp'2016-12-1 09:30:25 GMT', '2017-02-1','Description 4', 6000.00, '../images/toliet_tissue.jpg', 'Re-usable Toilet Paper', 0, 4);


INSERT INTO user_roles VALUES (NULL,'admin', 1), (NULL,'user', 0), (NULL,'user', 2), (NULL,'user', 3);

INSERT INTO pledges VALUES (NULL, 15.0, timestamp'2016-10-29 09:30:25 GMT', 2, 4), (NULL, 120.0, timestamp'2016-11-09 09:30:25 GMT', 1, 2), (NULL, 75.0, timestamp'2016-10-30 09:30:25 GMT', 4, 2);
INSERT INTO pledges VALUES (NULL, 25.0, timestamp'2016-12-01 09:30:25 GMT', 2, 3), (NULL, 20.0, timestamp'2016-12-01 09:30:25 GMT', 1, 3), (NULL, 50.0, timestamp'2016-12-01 09:30:25 GMT', 4, 3);