package com.yanny.age.stone.items;

import com.yanny.ages.api.items.IAdditionalProperties;

import java.util.Random;

public class StoneTierProperties implements IAdditionalProperties {
    private static final Random random = new Random(System.currentTimeMillis());

    @Override
    public float getAttackDamage() {
        return random.nextFloat() * 1.5f - 0.5f;
    }

    @Override
    public float getAttackSpeed() {
        return random.nextFloat() * 0.7f - 0.3f;
    }

    @Override
    public float getEfficiency() {
        return random.nextFloat() * 1.0f - 0.5f;
    }
}
