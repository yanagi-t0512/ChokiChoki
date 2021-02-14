INSERT INTO site_user(id, siteuser_Name, password, email, age, role)
VALUES
(1, 'admin', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', 'tanaka@email.com', 20, 'ADMIN'),
(2, 'user1', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', 'sato@email.com', 25, 'GENERAL'),
(3, 'user2', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', 'takahashi@email.com', 35, 'GENERAL'),
(4, 'user3', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', 'tanaka@email.com', 40, 'GENERAL');


INSERT INTO menu(id, kind, price)
VALUES
(-1, '', 0),
(1, 'カット', 3000),
(2, 'カラー', 6000),
(3, 'マッサージ', 9500);

INSERT INTO staff(id, staff_Name, fee)
VALUES
(-1, '', 0),
(1, '飛電　或人', 700),
(2, '不破　諌', 1500),
(3, '刃　唯阿', 5000);

INSERT INTO reservation(id, site_user_Id, menu_Id, staff_Id, reserve_Date, request)
VALUES
(1, 2, 1, 1, '2020-12-12', '短め'),
(2, 2, 2, 1, '2020-12-13', 'シャンプー強めでお願いします'),
(3, 3, 3, 3, '2020-12-14', '腰が痛いです'),
(4, 4, 1, 2, '2020-12-16', '前髪のみ'),
(5, 2, 3, 3, '2020-12-18', ''),
(6, 3, 1, 2, '2020-12-20', ''),
(7, 4, 2, 2, '2020-12-21', '');


