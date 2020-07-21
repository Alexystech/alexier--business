create table empresa
(
    codigo int         not null
        primary key,
    nombre varchar(30) null
);

create table tipo_producto
(
    dni                  int         not null
        primary key,
    nombre_tipo_producto varchar(30) null
);

create table bebida_alcoholica
(
    codigo_bebida int         not null
        primary key,
    dni           int         null,
    nombre_bebida varchar(30) null,
    precio        double      null,
    constraint bebida_alcoholica_ibfk_1
        foreign key (dni) references tipo_producto (dni)
);

create index dni
    on bebida_alcoholica (dni);

create table bebida_energetica
(
    codigo_bebida int         not null
        primary key,
    dni           int         null,
    nombre_bebida varchar(30) null,
    precio        double      null,
    constraint bebida_energetica_ibfk_1
        foreign key (dni) references tipo_producto (dni)
);

create index dni
    on bebida_energetica (dni);

create table bebida_soda
(
    codigo_bebida int         not null
        primary key,
    dni           int         null,
    nombre_bebida varchar(30) null,
    precio        double      null,
    constraint bebida_soda_ibfk_1
        foreign key (dni) references tipo_producto (dni)
);

create index dni
    on bebida_soda (dni);

create table galletas
(
    codigo_galletas int         not null
        primary key,
    dni             int         null,
    nombre_galletas varchar(30) null,
    precio          double      null,
    constraint galletas_ibfk_1
        foreign key (dni) references tipo_producto (dni)
);

create index dni
    on galletas (dni);

create table lacteos
(
    codigo_lacteo int         not null
        primary key,
    dni           int         null,
    nombre_lacteo varchar(30) null,
    precio        double      null,
    constraint lacteos_ibfk_1
        foreign key (dni) references tipo_producto (dni)
);

create index dni
    on lacteos (dni);

create table ventas
(
    codigo        int         not null
        primary key,
    empresa       varchar(30) null,
    tipo_producto varchar(30) null,
    producto      varchar(30) null,
    precio        double      null
);


