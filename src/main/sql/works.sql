
create table customer(
  id int not null auto_increment,
  name varchar(30) not null,
  phone varchar(30),
  address varchar(50),
  primary key(id)
);

create table supply(
  id int not null auto_increment,
  name varchar(30) not null,
  phone varchar(30),
  address varchar(50),
  primary key(id)
);

create table product(
  id int not null auto_increment,
  name varchar(30) not null,
  supplyName varchar(30) not null,
  describes varchar(500),
  size varchar(10),
  cost double,
  prices double,
  origin varchar(50),
  unit varchar(10),
  foreign key(supplyId) references supply(id),
  primary key(id)
);

create table inlet(
  id int not null auto_increment,
  productId int not null,
  inletDate Date,
  number int,
  foreign key(productId) references product(id),
  primary key(id)
);

create table store(
  id int not null auto_increment,
  productId int not null,
  storeDate Date,
  number int,
  foreign key(productId) references product(id),
  primary key(id)
);

create table shelves(
  id int not null auto_increment,
  productId int not null,
  shelvesDate Date,
  number int,
  foreign key(productId) references product(id),
  primary key(id)
);

create table sold(
  id int not null auto_increment,
  productId int not null,
  customerId int not null,
  soldDate Date,
  number int,
  foreign key(productId) references product(id),
  foreign key(customerId) references customer(id),
  primary key(id)
);
