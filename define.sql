
    create table biciclette (
        id integer not null,
        id_freno integer,
        id_sospensione integer,
        numero_marce integer not null,
        pieghevole bit not null,
        primary key (id)
    ) engine=InnoDB;

    create table categorie (
        id integer not null auto_increment,
        categoria varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table colori (
        id integer not null auto_increment,
        colore varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table macchine (
        cc integer not null,
        id integer not null,
        numero_porte integer not null,
        targa varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table marche (
        id integer not null auto_increment,
        marca varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table moto (
        cc integer not null,
        id integer not null,
        numero_marce integer not null,
        targa varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table tipi_alimentazione (
        id integer not null auto_increment,
        tipo_alimentazione varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table tipi_freno (
        id integer not null auto_increment,
        tipo_freno varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table tipi_sospensione (
        id integer not null auto_increment,
        tipo_sospensione varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table tipi_veicolo (
        id integer not null auto_increment,
        tipo_veicolo varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table veicoli (
        anno_produzione integer not null,
        id integer not null auto_increment,
        id_alimentazione integer,
        id_categoria integer,
        id_colore integer,
        id_marca integer,
        id_tipo integer,
        numero_ruote integer not null,
        primary key (id)
    ) engine=InnoDB;

    alter table macchine 
       add constraint UKgxt0l1uuapmsn7rybuq808dsr unique (targa);

    alter table moto 
       add constraint UK2i3efrjuh6fy8isx13wtioxpr unique (targa);

    alter table biciclette 
       add constraint FKi4e2ii6pf8yicufxmjtxr0uhw 
       foreign key (id_freno) 
       references tipi_freno (id);

    alter table biciclette 
       add constraint FKql7mhtcmt9sv5ve9d1yp19fx0 
       foreign key (id_sospensione) 
       references tipi_sospensione (id);

    alter table biciclette 
       add constraint FKrjqihljxxpydep024s0tusq2l 
       foreign key (id) 
       references veicoli (id);

    alter table macchine 
       add constraint FKqttmt8yqk5vj8g3tn94ovrbay 
       foreign key (id) 
       references veicoli (id);

    alter table moto 
       add constraint FKt01inn1jct6p6irfuar0pt15d 
       foreign key (id) 
       references veicoli (id);

    alter table veicoli 
       add constraint FKfnj6d8ledmyr1pj05o2aunbga 
       foreign key (id_categoria) 
       references categorie (id);

    alter table veicoli 
       add constraint FK9fby65ukehcfx0sd0pmaewyry 
       foreign key (id_colore) 
       references colori (id);

    alter table veicoli 
       add constraint FKiv5idk7drutlxyxhc5663nwey 
       foreign key (id_marca) 
       references marche (id);

    alter table veicoli 
       add constraint FK4up3dthh5qgmlf9twr26yo9tm 
       foreign key (id_alimentazione) 
       references tipi_alimentazione (id);

    alter table veicoli 
       add constraint FK1hg9ov4pnip3yxdm5ew3bbi57 
       foreign key (id_tipo) 
       references tipi_veicolo (id);
