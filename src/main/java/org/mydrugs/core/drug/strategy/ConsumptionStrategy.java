package org.mydrugs.core.drug.strategy;

import org.mydrugs.core.drug.effect.DrugEffect;

public interface ConsumptionStrategy {
    int getNewPotency(DrugEffect drugEffect);

    int getNewDuration(DrugEffect drugEffect);
}
