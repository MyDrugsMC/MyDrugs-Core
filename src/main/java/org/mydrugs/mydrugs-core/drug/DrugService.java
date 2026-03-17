package org.mydrugs.mydrugs.core.drug;

import org.mydrugs.mydrugs.core.drug.effect.DrugEffect;
import org.mydrugs.mydrugs.core.drug.effect.EffectPort;

public class DrugService {
    private final EffectPort effectPort;

    public DrugService(EffectPort effectPort) {
        this.effectPort = effectPort;
    }

    public void consume(DrugModel drugModel) {
        for (DrugEffect effect : drugModel.getDrugEffects()) {
            effectPort.applyEffect(effect);
        }
    }
}
