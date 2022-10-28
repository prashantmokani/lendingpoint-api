create table revinfo
(
    rev      int4 not null,
    revtstmp int8,
    primary key (rev)
);

create sequence hibernate_sequence start 1 increment 1;
