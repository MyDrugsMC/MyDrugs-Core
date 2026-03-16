package org.mydrugs.mydrugs.core;

import java.util.ArrayList;
import java.util.List;

public class DrugModel {
    private final DrugId id;
    private final List<DrugEffect> drugEffects;

    private DrugModel(DrugId id) {
        this(id, new ArrayList<>());
    }

    protected DrugModel(DrugId id, List<DrugEffect> effects) {
        this.id = id;
        this.drugEffects = effects;
    }

    public DrugId getId() {
        return id;
    }

    public List<DrugEffect> getDrugEffects() {
        return drugEffects;
    }

    public static class Builder {
        private DrugId id = null;
        private List<DrugEffect> effects = new ArrayList<>();

        public Builder setId(DrugId id) {
            this.id = id;
            return this;
        }

        public Builder addEffect(DrugEffect effect) {
            effects.add(effect);
            return this;
        }

        public DrugModel build() {
            return new DrugModel(id, effects);
        }
    }
}
