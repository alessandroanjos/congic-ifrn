# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table area_conhecimento (
  id                        integer auto_increment not null,
  nome                      varchar(255),
  constraint uq_area_conhecimento_nome unique (nome),
  constraint pk_area_conhecimento primary key (id))
;

create table area_especifica (
  id                        integer auto_increment not null,
  nome                      varchar(255),
  area_conhecimento_id      integer,
  constraint pk_area_especifica primary key (id))
;

create table artigo (
  id                        bigint auto_increment not null,
  autor_um_id               bigint,
  autor_dois                varchar(255),
  autor_tres                varchar(255),
  nome_orientador           varchar(255),
  grupo_pesquisa_id         bigint,
  campus_id                 bigint,
  area_conhecimento_id      integer,
  area_especifica_id        integer,
  titulo                    varchar(255),
  resumo                    longtext,
  introducao                longtext,
  fundamentacao_teorica     longtext,
  justificativa             longtext,
  objetivos                 longtext,
  metodologia               longtext,
  referencias               longtext,
  constraint pk_artigo primary key (id))
;

create table artigo_avaliado (
  id                        integer auto_increment not null,
  usuario_id                bigint,
  artigo_id                 bigint,
  situacao                  tinyint(1) default 0,
  constraint pk_artigo_avaliado primary key (id))
;

create table bolsa (
  id                        bigint auto_increment not null,
  nome                      varchar(255),
  constraint uq_bolsa_nome unique (nome),
  constraint pk_bolsa primary key (id))
;

create table campus (
  id                        bigint auto_increment not null,
  nome                      varchar(255),
  constraint uq_campus_nome unique (nome),
  constraint pk_campus primary key (id))
;

create table grupo_pesquisa (
  id                        bigint auto_increment not null,
  nome                      varchar(255),
  campus_id                 bigint,
  constraint uq_grupo_pesquisa_nome unique (nome),
  constraint pk_grupo_pesquisa primary key (id))
;

create table usuario (
  id                        bigint auto_increment not null,
  login                     varchar(255),
  senha                     varchar(255),
  nome                      varchar(255),
  cpf                       varchar(255),
  sexo                      varchar(255),
  data_nascimento           datetime,
  email                     varchar(255),
  telefone                  varchar(255),
  campus_id                 bigint,
  area_conhecimento_id      integer,
  area_especifica_id        integer,
  bolsa_id                  bigint,
  is_professor              tinyint(1) default 0,
  is_servidor               tinyint(1) default 0,
  is_administrador          tinyint(1) default 0,
  is_bolsista               tinyint(1) default 0,
  is_ativo                  tinyint(1) default 0,
  constraint uq_usuario_login unique (login),
  constraint uq_usuario_email unique (email),
  constraint pk_usuario primary key (id))
;

alter table area_especifica add constraint fk_area_especifica_areaConheci_1 foreign key (area_conhecimento_id) references area_conhecimento (id) on delete restrict on update restrict;
create index ix_area_especifica_areaConheci_1 on area_especifica (area_conhecimento_id);
alter table artigo add constraint fk_artigo_autorUm_2 foreign key (autor_um_id) references usuario (id) on delete restrict on update restrict;
create index ix_artigo_autorUm_2 on artigo (autor_um_id);
alter table artigo add constraint fk_artigo_grupoPesquisa_3 foreign key (grupo_pesquisa_id) references grupo_pesquisa (id) on delete restrict on update restrict;
create index ix_artigo_grupoPesquisa_3 on artigo (grupo_pesquisa_id);
alter table artigo add constraint fk_artigo_campus_4 foreign key (campus_id) references campus (id) on delete restrict on update restrict;
create index ix_artigo_campus_4 on artigo (campus_id);
alter table artigo add constraint fk_artigo_areaConhecimento_5 foreign key (area_conhecimento_id) references area_conhecimento (id) on delete restrict on update restrict;
create index ix_artigo_areaConhecimento_5 on artigo (area_conhecimento_id);
alter table artigo add constraint fk_artigo_areaEspecifica_6 foreign key (area_especifica_id) references area_especifica (id) on delete restrict on update restrict;
create index ix_artigo_areaEspecifica_6 on artigo (area_especifica_id);
alter table artigo_avaliado add constraint fk_artigo_avaliado_usuario_7 foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;
create index ix_artigo_avaliado_usuario_7 on artigo_avaliado (usuario_id);
alter table artigo_avaliado add constraint fk_artigo_avaliado_artigo_8 foreign key (artigo_id) references artigo (id) on delete restrict on update restrict;
create index ix_artigo_avaliado_artigo_8 on artigo_avaliado (artigo_id);
alter table grupo_pesquisa add constraint fk_grupo_pesquisa_campus_9 foreign key (campus_id) references campus (id) on delete restrict on update restrict;
create index ix_grupo_pesquisa_campus_9 on grupo_pesquisa (campus_id);
alter table usuario add constraint fk_usuario_campus_10 foreign key (campus_id) references campus (id) on delete restrict on update restrict;
create index ix_usuario_campus_10 on usuario (campus_id);
alter table usuario add constraint fk_usuario_areaConhecimento_11 foreign key (area_conhecimento_id) references area_conhecimento (id) on delete restrict on update restrict;
create index ix_usuario_areaConhecimento_11 on usuario (area_conhecimento_id);
alter table usuario add constraint fk_usuario_areaEspecifica_12 foreign key (area_especifica_id) references area_especifica (id) on delete restrict on update restrict;
create index ix_usuario_areaEspecifica_12 on usuario (area_especifica_id);
alter table usuario add constraint fk_usuario_bolsa_13 foreign key (bolsa_id) references bolsa (id) on delete restrict on update restrict;
create index ix_usuario_bolsa_13 on usuario (bolsa_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table area_conhecimento;

drop table area_especifica;

drop table artigo;

drop table artigo_avaliado;

drop table bolsa;

drop table campus;

drop table grupo_pesquisa;

drop table usuario;

SET FOREIGN_KEY_CHECKS=1;

