create schema if not exists negocio collate utf8mb4_0900_ai_ci;

use negocio;

create table if not exists empresa
(
	codigo int not null
		primary key,
	nombre varchar(30) null
);

create table if not exists tipo_producto
(
	dni int not null
		primary key,
	nombre_tipo_producto varchar(30) null
);

create table if not exists bebida_alcoholica
(
	codigo_bebida int not null
		primary key,
	dni int null,
	nombre_bebida varchar(30) null,
	precio double null,
	constraint bebida_alcoholica_ibfk_1
		foreign key (dni) references tipo_producto (dni)
);

create index dni
	on bebida_alcoholica (dni);

create table if not exists bebida_energetica
(
	codigo_bebida int not null
		primary key,
	dni int null,
	nombre_bebida varchar(30) null,
	precio double null,
	constraint bebida_energetica_ibfk_1
		foreign key (dni) references tipo_producto (dni)
);

create index dni
	on bebida_energetica (dni);

create table if not exists bebida_soda
(
	codigo_bebida int not null
		primary key,
	dni int null,
	nombre_bebida varchar(30) null,
	precio double null,
	constraint bebida_soda_ibfk_1
		foreign key (dni) references tipo_producto (dni)
);

create index dni
	on bebida_soda (dni);

create table if not exists galletas
(
	codigo_galletas int not null
		primary key,
	dni int null,
	nombre_galletas varchar(30) null,
	precio double null,
	constraint galletas_ibfk_1
		foreign key (dni) references tipo_producto (dni)
);

create index dni
	on galletas (dni);

create table if not exists lacteos
(
	codigo_lacteo int not null
		primary key,
	dni int null,
	nombre_lacteo varchar(30) null,
	precio double null,
	constraint lacteos_ibfk_1
		foreign key (dni) references tipo_producto (dni)
);

create index dni
	on lacteos (dni);

create table if not exists ventas
(
	codigo int not null
		primary key,
	empresa varchar(30) null,
	tipo_producto varchar(30) null,
	producto varchar(30) null,
	precio double null
);
