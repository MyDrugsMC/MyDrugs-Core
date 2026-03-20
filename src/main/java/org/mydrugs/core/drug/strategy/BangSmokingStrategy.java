package org.mydrugs.core.drug.strategy;


import org.mydrugs.core.drug.effect.DrugEffect;

public class BangSmokingStrategy implements ConsumptionStrategy {
    @Override
    public int getNewPotency(DrugEffect drugEffect) {
        return (int) Math.round(drugEffect.getBasePotency() * 0.9);
    }

    @Override
    public int getNewDuration(DrugEffect drugEffect) {
        return (int) Math.round(drugEffect.getBaseDuration() * 1.0);
    }
}
