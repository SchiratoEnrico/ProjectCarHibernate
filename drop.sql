
    alter table biciclette 
       drop 
       foreign key FKi4e2ii6pf8yicufxmjtxr0uhw;

    alter table biciclette 
       drop 
       foreign key FKql7mhtcmt9sv5ve9d1yp19fx0;

    alter table biciclette 
       drop 
       foreign key FKrjqihljxxpydep024s0tusq2l;

    alter table macchine 
       drop 
       foreign key FKqttmt8yqk5vj8g3tn94ovrbay;

    alter table moto 
       drop 
       foreign key FKt01inn1jct6p6irfuar0pt15d;

    alter table veicoli 
       drop 
       foreign key FKfnj6d8ledmyr1pj05o2aunbga;

    alter table veicoli 
       drop 
       foreign key FK9fby65ukehcfx0sd0pmaewyry;

    alter table veicoli 
       drop 
       foreign key FKiv5idk7drutlxyxhc5663nwey;

    alter table veicoli 
       drop 
       foreign key FK4up3dthh5qgmlf9twr26yo9tm;

    alter table veicoli 
       drop 
       foreign key FK1hg9ov4pnip3yxdm5ew3bbi57;

    drop table if exists biciclette;

    drop table if exists categorie;

    drop table if exists colori;

    drop table if exists macchine;

    drop table if exists marche;

    drop table if exists moto;

    drop table if exists tipi_alimentazione;

    drop table if exists tipi_freno;

    drop table if exists tipi_sospensione;

    drop table if exists tipi_veicolo;

    drop table if exists veicoli;
