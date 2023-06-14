create database verify_email_app;

create table role
(
  id tinyint not null primary key,
  role varchar(45) null
);

create table user
(
  id int auto_increment primary key,
  user_name varchar(100) not null,
  password varchar(255) not null,
  name varchar(100) null,
  email varchar(45) not null,
  activated tinyint(1) default 0 null,
  role tinyint(1) default 1 null,
  constraint fk_user_role foreign key (role) references role (id)
);

create table activation_key
(
  id int auto_increment primary key,
  user_id int null,
  active_key varchar(255) null,
  expired_at datetime null,
  constraint fk_activation_user
    foreign key (user_id) references user (id)
);

INSERT INTO `verify_email_app`.`role` (`id`, `role`) VALUES ('1', 'ROLE_USER');
INSERT INTO `verify_email_app`.`role` (`id`, `role`) VALUES ('2', 'ROLE_ADMIN');
