create table disease (
  id                            bigint auto_increment not null,
  createtime                    timestamp,
  updatetime                    timestamp,
  name                          varchar(255),
  constraint pk_disease primary key (id)
);

create table principle (
  id                            bigint auto_increment not null,
  createtime                    timestamp,
  updatetime                    timestamp,
  diseaseid                     bigint,
  principleitemid               bigint,
  constraint pk_principle primary key (id)
);

create table principleitem (
  id                            bigint auto_increment not null,
  createtime                    timestamp,
  updatetime                    timestamp,
  adverb                        integer,
  type                          integer,
  target                        varchar(255),
  constraint pk_principleitem primary key (id)
);

