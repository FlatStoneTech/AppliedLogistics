package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class RandomHelper {
    public static int CalculatePulverizer(float itemValue, float fortuneValue) {
        Random rng = new Random();
        if (fortuneValue == 0) {
            return (int)Math.ceil(itemValue);
        } else {
            float random = MathHelper.randomFloatClamp(rng, itemValue, fortuneValue + itemValue);
            return (int)Math.ceil(random);
        }
    }
}
