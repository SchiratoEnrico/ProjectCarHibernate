package com.betacom.car.utilities;

import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.betacom.car.models.Bicicletta;
import com.betacom.car.models.Categoria;
import com.betacom.car.models.Colore;
import com.betacom.car.models.Macchina;
import com.betacom.car.models.Marca;
import com.betacom.car.models.Moto;
import com.betacom.car.models.TipoAlimentazione;
import com.betacom.car.models.TipoFreno;
import com.betacom.car.models.TipoSospensione;
import com.betacom.car.models.TipoVeicolo;

@Component
public class ObjectDTOMapper {
    private final Map<Class<?>, Function<Object, ?>> registry;

    public ObjectDTOMapper() {
        this.registry = Map.of(
            Moto.class, v -> Utils.buildMotoDTO((Moto) v),
            Macchina.class, v -> Utils.buildMacchinaDTO((Macchina) v),
            Bicicletta.class, v -> Utils.buildBiciclettaDTO((Bicicletta) v),
            Colore.class, v -> Utils.buildColoreDTO((Colore) v),
            Categoria.class, v -> Utils.buildCategoriaDTO((Categoria) v),
            Marca.class, v -> Utils.buildMarcaDTO((Marca) v),
            TipoAlimentazione.class, v -> Utils.buildTipoAlimentazioneDTO((TipoAlimentazione) v),
            TipoFreno.class, v -> Utils.buildTipoFrenoDTO((TipoFreno) v),
            TipoSospensione.class, v -> Utils.buildTipoSospensioneDTO((TipoSospensione) v),
            TipoVeicolo.class, v -> Utils.buildTipoVeicoloDTO((TipoVeicolo) v)
        );
    }

    public Object map(Object v) {
        return registry.get(v.getClass()).apply(v);
    }
}