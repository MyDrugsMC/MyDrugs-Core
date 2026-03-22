package org.mydrugs.core.drug.effect;

import org.mydrugs.core.drug.strategy.ConsumptionStrategy;

public interface EffectPort {
    void applyEffect(DrugEffect effect, ConsumptionStrategy strategy);
}