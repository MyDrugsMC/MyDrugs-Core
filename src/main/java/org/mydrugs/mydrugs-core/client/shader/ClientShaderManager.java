package org.mydrugs.mydrugs.core.client.shader;

import org.mydrugs.mydrugs.core.Core;
import org.mydrugs.mydrugs.core.client.ClientState;
import org.mydrugs.mydrugs.core.drug.effect.EffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Works ONLY ON CLIENT. NOT SERVER
public abstract class ClientShaderManager<T extends Shader> {

    private final ClientState clientState;
    private final Map<EffectType, T> shaders = new HashMap<>();
    private float ticksLeft = 0.0F;

    public ClientShaderManager(ClientState clientState) {
        this.clientState = clientState;
    }

    public void register(EffectType type, T shader) {
        shaders.put(type, shader);
    }

    public void tick() {
        if (ticksLeft <= 0) {
            return;
        }

        ticksLeft--;

        if (!clientState.hasShader()) {
            return;
        }

        if (ticksLeft <= 0) {
            clientState.setShader(null);
        }
    }

    public Shader getCurrentShader() {
        return clientState.getShader();
    }

    public void start(int durationTicks, EffectType type) {
        ticksLeft = durationTicks;

        T shader = shaders.get(type);

        if (shader == null) {
            Core.getLOGGER().warning("Shader " + type.name() + " is not initialized!");
            return;
        }

        if (clientState.hasShader()) {
            clientState.setShader(null);
        }

        clientState.setShader(shader);
    }

    public List<T> getShaders() {
        return new ArrayList<>(shaders.values());
    }
}