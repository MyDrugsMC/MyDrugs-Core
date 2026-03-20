package org.mydrugs.core.drug.strategy;

import org.mydrugs.core.drug.effect.DrugEffect;

public class JointSmokingStrategy implements ConsumptionStrategy {
    @Override
    public int getNewPotency(DrugEffect drugEffect) {
        return (int) Math.round(drugEffect.getBasePotency() * 0.7);
    }

    @Override
    public int getNewDuration(DrugEffect drugEffect) {
        return (int) Math.round(drugEffect.getBaseDuration() * 0.7);
    }
}
