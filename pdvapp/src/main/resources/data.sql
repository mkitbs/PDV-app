-- permissions
insert into permission (id, name) values (1, 'CREATE');
insert into permission (id, name) values (2, 'READ');

-- roles
insert into roles (id, name) values (1, 'ROLE_SUPER_ADMIN');
insert into roles (id, name) values (2, 'ROLE_PROCES_OWNER');
insert into roles (id, name) values (3, 'ROLE_MANAGER');
insert into roles (id, name) values (4, 'ROLE_NORMAL_USER');
insert into roles (id, name) values (5, 'ROLE_EX_USER');

-- users
insert into users (id, name, surname, email, enabled, password, non_locked, date_of_birth, telephone, bukrs2access) 
values (1, 'Nemanja', 'Dime', 'nemanja@gmail.com',  1, '$2a$10$tlHY4ACO1oM5KR5eFRNdaOcIfOR0ZQXEnCB0TjnBgHPIOWHIfZN7K', 1, '1996-10-07', '060696969', 'MKBS;1000;5200');

insert into user_roles (user_id, role_id) values (1,1);


insert into role_permissions (role_id, permission_id) values (1,1);

--insert into pdv1 (bukrs, last_changed_by, name, year) values ("AGUN", "ndimsic", "PDV1", "2019");
--insert into pdv2 (bukrs, last_changed_by, name, year) values ("AGUN", "ndimsic", "PDV2", "2019");

--insert into pdv1values (pdv1_id, value, month) values (1, 105, "January");
--insert into pdv1values (pdv1_id, value, month) values (1, 105, "February");
--insert into pdv1values (pdv1_id, value, month) values (1, 105, "March");
--insert into pdv1values (pdv1_id, value, month) values (1, 105, "April");
--insert into pdv1values (pdv1_id, value, month) values (1, 105, "June");
--insert into pdv1values (pdv1_id, value, month) values (1, 105, "July");
--insert into pdv1values (pdv1_id, value, month) values (1, 105, "August");
--insert into pdv1values (pdv1_id, value, month) values (1, 105, "September");
--insert into pdv1values (pdv1_id, value, month) values (1, 105, "October");

--insert into pdv2values (pdv2_id, value, month) values (1, 106, "February");
--insert into pdv2values (pdv2_id, value, month) values (1, 107, "March");
--insert into pdv2values (pdv2_id, value, month) values (1, 108, "January");

INSERT INTO company (company_code, company_name) VALUES
('1000','Agroglobe d.o.o., Novi Sad'),
('1100','Žito Bačka doo, Kula'),
('2000','MK Mountain resort d.o.o.'),
('2200','MK Aviation Services'),
('2300','MKAS d.o.o.'),
('4026','Energobalkan - pridruženo'),
('4029','MK Fintel Wind Holding - pridruženo'),
('AIKB','AIK banka a.d.'),
('4024','MK Fintel Wind - pridruženo'),
('5100','Banat Seme d.o.o., Zrenjanin'),
('4037','Carnex d.o.o., Vrbas'),
('PIKB','PIK Becej d.o.o., Becej'),
('5200','MK Panonija'),
('5300','MK Fintel Wind Develop.'),
('AGUN','Agrounija doo, Inđija'),
('GRAN','Granexport ad, Pančevo'),
('MKAG','MK Agriculture d.o.o.'),
('MKC','MK Group d.o.o., Beograd'),
('MKH','MK Holding'),
('SUN','Sunoko d.o.o., Novi Sad'),
('VPD1','Vetropark Dunav 1 - pridruženo'),
('VPD3','Vetropark Dunav 3 - pridruženo'),
('VPKU','Vetropark Kula - pridruženo'),
('VPRA','Vetropark RAM - pridruženo'),
('VPTO','Vetropark Torak  - pridruženo'),
('VPKO','Vetropark Košava 2 - pridruženo'),
('4043','Sojaprotein'),
('4044','Victoria Group'),
('MKBS','MK IT Business Solutions'),
('4046','Victoria Logistics');


--insert into field (inid, value, year, bukrs, last_changed_by) values (3, 12345, "2019", "MKBS", "ndimsic");
--insert into field (inid, value, year, bukrs, last_changed_by) values (4, 123456, "2019", "MKBS", "ndimsic");
