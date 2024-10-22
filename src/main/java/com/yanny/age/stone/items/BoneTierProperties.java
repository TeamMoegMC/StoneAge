package com.yanny.age.stone.items;

//import com.yanny.ages.api.items.IAdditionalProperties;

import java.util.Random;

public class BoneTierProperties  {
    private static final Random random = new Random(System.currentTimeMillis());


    public float getAttackDamage() {
        return random.nextFloat() * 1.3f - 0.3f;
    }


    public float getAttackSpeed() {
        return random.nextFloat() * 0.5f - 0.2f;
    }


    public float getEfficiency() {
        return random.nextFloat() * 0.7f - 0.3f;
    }
}
