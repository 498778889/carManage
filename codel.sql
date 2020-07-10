
create table CAR_USER
(
  id       NUMBER not null primary key,
  username VARCHAR2(50),
  pwd      VARCHAR2(50),
  nickname VARCHAR2(50),
  realname VARCHAR2(50),
  sex      NUMBER
);

create table CAR
(
  id              NUMBER not null primary key,
  brand           VARCHAR2(50),
  color           VARCHAR2(20),
  sites           NUMBER,
  fuelconsumption NUMBER(10,1),
  dailyrent       NUMBER,
  addperson       VARCHAR2(50)
);

create sequence seq_car_user;
create sequence seq_car;
create sequence seq_car_brand;
create sequence seq_car_record;


select * from CAR_USER;
select * from CAR;
select * from CAR_Brand;
select * from CAR_Record;
insert into CAR_USER values(seq_car_user.nextval,'ncy','123456','ncy','ncy',1);
insert into CAR values(seq_car.nextval,'BMWx6','white',5,11.3,1000,'ncy');
commit;

select CAR.*,CAR_USER.NICKNAME from Car,CAR_USER where car.addperson=car_user.username and car.id=1;

create table Car_Brand
(
 id          NUMBER not null primary key,
 brand       VARCHAR2(50) 
);

create table Car_Record
(
id           Number not null primary key,
username     VARCHAR2(50),
operation_type int,
operate_time date 
);

alter table CAR add addtime date;
alter table CAR
    add productionDate varchar2(20);