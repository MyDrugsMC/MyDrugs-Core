package org.mydrugs.mydrugs.core.drug;

import org.mydrugs.mydrugs.core.drug.effect.DrugEffect;
import org.mydrugs.mydrugs.core.drug.effect.EffectType;

import java.util.HashMap;
import java.util.Map;

public class DrugRegistry {

    private static Map<DrugId, DrugModel> drugs = null;

    private DrugRegistry() {
    }

    public static void registerDrugs() {
        addDrug(new DrugModel.Builder()
                .setId(DrugId.WEED)
                .addEffect(new DrugEffect(EffectType.SLOWNESS))
                .addEffect(new DrugEffect(EffectType.FOG))
                .build()
        );

        addDrug(new DrugModel.Builder()
                .setId(DrugId.METH)
                .addEffect(new DrugEffect(EffectType.VOID_PULSE))
                .build()
        );
        addDrug(new DrugModel.Builder()
                .setId(DrugId.LSD)
                .addEffect(new DrugEffect(EffectType.ACID_WARP))
                .build()
        );
        addDrug(new DrugModel.Builder()
                .setId(DrugId.MUSHROOMS)
                .build()
        );

        addDrug(new DrugModel.Builder()
                .setId(DrugId.HEROINE)
                .build()
        );

        addDrug(new DrugModel.Builder()
                .setId(DrugId.ALCOHOL)
                .addEffect(new DrugEffect(EffectType.NAUSEA))
                .build()
        );

        addDrug(new DrugModel.Builder()
                .setId(DrugId.TABACCO)
                .build()
        );

        addDrug(new DrugModel.Builder()
                .setId(DrugId.COFFEE)
                .build()
        );
    }

    private static DrugModel addDrug(DrugModel model) {
        if (drugs == null) drugs = new HashMap<>();
        if (getDrug(model.getId()) != null) {
            System.err.println("Drug " + model.getId().name() + " was tried to be registered twice !");
            return getDrug(model.getId());
        }
        drugs.put(model.getId(), model);
        return model;
    }

    public static DrugModel getDrug(DrugId id) {
        return drugs.get(id);
    }
}
