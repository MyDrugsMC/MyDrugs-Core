package org.mydrugs.core.drug;
import org.junit.jupiter.api.Test;
import org.mydrugs.core.drug.effect.DrugEffect;
import org.mydrugs.core.drug.effect.EffectType;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mydrugs.core.drug.DrugRegistry.addDrug;

public final class DrugsClassTest {

    @Test
    public void add_new_drug() {
        DrugRegistry registry = new DrugRegistry();

        addDrug(new DrugModel.Builder()
                .setId(DrugId.ALCOHOL)
                .setCategory(DrugCategory.OTHER)
                .addEffect(new DrugEffect(EffectType.VOID_PULSE, 20 * 8, 1))
                .build()
        );

        assertNotNull(DrugRegistry.getDrug(DrugId.ALCOHOL), "This drug has been added");
        assertNull(DrugRegistry.getDrug(DrugId.COCAINE), "This drug is not supposed to exist");
    }
}