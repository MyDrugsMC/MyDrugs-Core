package org.mydrugs.core.drug;

import org.mydrugs.core.drug.strategy.ConsumptionStrategy;

public interface DrugHolder {
    DrugModel getDrugModel();

    ConsumptionStrategy getConsumptionStrategy();

    boolean isCrushable();
}
