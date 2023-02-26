CREATE TABLE role (
   id UUID NOT NULL,
   name VARCHAR(255) NOT NULL,
   CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE membership (
   id UUID NOT NULL,
   role_id UUID NOT NULL,
   user_id UUID NOT NULL,
   team_id UUID NOT NULL,
   CONSTRAINT pk_membership PRIMARY KEY (id)
);

ALTER TABLE membership ADD CONSTRAINT uc_54d311052ea2687bf98aa0712 UNIQUE (team_id, user_id);

ALTER TABLE membership ADD CONSTRAINT FK_MEMBERSHIP_ON_ROLE FOREIGN KEY (role_id) REFERENCES role (id);


ALTER TABLE role ADD CONSTRAINT uc_role_name UNIQUE (name);

insert into role(id, name)
values ('1b3c333b-36e7-4b64-aa15-c22ed5908ce4', 'Developer');
insert into role(id, name)
values ('25bbb7d2-26f3-11ec-9621-0242ac130002', 'Product Owner');
insert into role(id, name)
values ('37969e22-26f3-11ec-9621-0242ac130002', 'Tester');
commit;



--insert into membership(id,role_id, user_id,team_id)
--values ('98de61a0-b9e3-11ec-8422-0242ac120002','1b3c333b-36e7-4b64-aa15-c22ed5908ce4', 'fd282131-d8aa-4819-b0c8-d9e0bfb1b75c','7676a4bf-adfe-415c-941b-1739af07039b');


insert into membership(id,role_id, user_id,team_id)
values ('84ab96f2-b5ba-11ed-afa1-0242ac120002','25bbb7d2-26f3-11ec-9621-0242ac130002', '1b140966-5a01-49da-872e-71a769f98941','7cf0d32d-036f-40b6-86ea-2473d4ccaecd');

insert into membership(id,role_id, user_id,team_id)
values ('f8c7894c-b5ba-11ed-afa1-0242ac120002','37969e22-26f3-11ec-9621-0242ac130002', '0a7608be-05b6-400d-82b7-f074761d6ca5','a2c06cde-9872-42be-bfaa-db53312338b4');

commit;