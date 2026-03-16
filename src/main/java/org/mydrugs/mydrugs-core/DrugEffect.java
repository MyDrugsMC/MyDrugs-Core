package org.mydrugs.mydrugs.core;

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
        this(type, 10*5, 1);
    }
}
