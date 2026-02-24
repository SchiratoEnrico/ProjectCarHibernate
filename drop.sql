
    set client_min_messages = WARNING;

    alter table if exists biciclette 
       drop constraint if exists FKi4e2ii6pf8yicufxmjtxr0uhw;

    alter table if exists biciclette 
       drop constraint if exists FKql7mhtcmt9sv5ve9d1yp19fx0;

    alter table if exists biciclette 
       drop constraint if exists FKrjqihljxxpydep024s0tusq2l;

    alter table if exists macchine 
       drop constraint if exists FKqttmt8yqk5vj8g3tn94ovrbay;

    alter table if exists moto 
       drop constraint if exists FKt01inn1jct6p6irfuar0pt15d;

    alter table if exists veicoli 
       drop constraint if exists FKfnj6d8ledmyr1pj05o2aunbga;

    alter table if exists veicoli 
       drop constraint if exists FK9fby65ukehcfx0sd0pmaewyry;

    alter table if exists veicoli 
       drop constraint if exists FKiv5idk7drutlxyxhc5663nwey;

    alter table if exists veicoli 
       drop constraint if exists FK4up3dthh5qgmlf9twr26yo9tm;

    alter table if exists veicoli 
       drop constraint if exists FK1hg9ov4pnip3yxdm5ew3bbi57;

    drop table if exists biciclette cascade;

    drop table if exists categorie cascade;

    drop table if exists colori cascade;

    drop table if exists macchine cascade;

    drop table if exists marche cascade;

    drop table if exists moto cascade;

    drop table if exists tipi_alimentazione cascade;

    drop table if exists tipi_freno cascade;

    drop table if exists tipi_sospensione cascade;

    drop table if exists tipi_veicolo cascade;

    drop table if exists veicoli cascade;
