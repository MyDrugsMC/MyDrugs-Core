package org.mydrugs.mydrugs.core.drug.effect;

public class DrugEffect {
    private final EffectType type;
    private final int baseDuration;
    private final int basePotency;

    public DrugEffect(EffectType type, int baseDuration, int basePotency) {
        this.type = type;
        this.baseDuration = baseDuration;
        this.basePotency = basePotency;
    }

    public DrugEffect(EffectType type) {
        this(type, 20 * 5, 1);
    }

    public EffectType getType() {
        return type;
    }

    public int getBaseDuration() {
        return baseDuration;
    }

    public int getBasePotency() {
        return basePotency;
    }
}
