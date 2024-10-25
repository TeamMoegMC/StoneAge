package com.yanny.age.stone.items;


import java.util.Random;

public class StoneTierProperties  {
    private static final Random random = new Random(System.currentTimeMillis());


    public float getAttackDamage() {
        return random.nextFloat() * 1.5f - 0.5f;
    }


    public float getAttackSpeed() {
        return random.nextFloat() * 0.7f - 0.3f;
    }


    public float getEfficiency() {
        return random.nextFloat() - 0.5f;
    }
}
