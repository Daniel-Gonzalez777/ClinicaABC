create database clinicaabc;
use clinicaabc;

create table personas (
    documento varchar(20) primary key,
    nombre varchar(100) not null,
    telefono varchar(20)
);

create table mascotas (
    nombre varchar(100) not null,
    raza varchar(50),
    sexo varchar(10) not null,
    id_dueño varchar(20),
    foreign key (id_dueño) references personas(documento) on delete cascade
);

select * from personas;
select * from mascotas;



