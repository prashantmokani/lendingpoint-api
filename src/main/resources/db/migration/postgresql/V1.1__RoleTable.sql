create table role
(
    id            bigserial not null,
    created_by    varchar(255),
    created_date  timestamp,
    modified_by   varchar(255),
    modified_date timestamp,
    name          varchar(255),
    primary key (id)
);

create table role_aud
(
    id            int8 not null,
    rev           int4 not null,
    revtype       int2,
    created_by    varchar(255),
    created_date  timestamp,
    modified_by   varchar(255),
    modified_date timestamp,
    name          varchar(255),
    primary key (id, rev)
);

alter table if exists role_aud add constraint FKrks7qtsmup3w81fdp0d6omfk7 foreign key (rev) references revinfo;
