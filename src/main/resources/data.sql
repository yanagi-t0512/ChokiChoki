INSERT INTO site_user(id, siteuser_Name, password, email, age, role)
VALUES(1, 'admin', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', 'tanaka@email.com', 20, 'ADMIN');
INSERT INTO site_user(id, siteuser_Name, password, email, age, role)
VALUES(2, 'user', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', 'sato@email.com', 25, 'GENERAL');
INSERT INTO site_user(id, siteuser_Name, password, email, age, role)
VALUES(3, 'ippan', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', 'takahashi@email.com', 35, 'GENERAL');


INSERT INTO menu(id, kind, price)
VALUES(1, 'カット', 3000);
INSERT INTO menu(id, kind, price)
VALUES(2, 'カラー', 6000);
INSERT INTO menu(id, kind, price)
VALUES(3, 'マッサージ', 9500);

INSERT INTO staff(id, staff_Name, fee)
VALUES(1, '火炎剣　烈火', 700);
INSERT INTO staff(id, staff_Name, fee)
VALUES(2, '水勢剣　流', 1500);
INSERT INTO staff(id, staff_Name, fee)
VALUES(3, '雷鳴剣　雷', 5000);

/*VALUES(1, 1, 1, '2020-12-12', 'シャンプー強め希望');
INSERT INTO reservation(id, customer_Id, menu_Id, staff_Id, reserve_Date, request)
VALUES(2, 1, 2, 2, '2020-12-13', 'ヘアカラー');
INSERT INTO reservation(id, customer_Id, menu_Id, staff_Id, reserve_Date, request)
VALUES(3, 1, 3, 3, '2020-12-14', 'マッサージ');
*/

