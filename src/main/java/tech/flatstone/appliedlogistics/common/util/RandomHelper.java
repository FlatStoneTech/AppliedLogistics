package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class RandomHelper {
    private static Random rng = new Random();
    public static int CalculatePulverizer(float itemValue, float fortuneValue) {
        if (fortuneValue == 0) {
            return (int) Math.ceil(itemValue);
        } else {
            float random = MathHelper.randomFloatClamp(rng, itemValue, fortuneValue + itemValue);
            return (int) Math.ceil(random);
        }
    }
    
    public static int calculateYield(double itemchance, double fortunemultiplier) {
	if (itemchance % 1 > 0.0d) {
		double Bonuschance = (itemchance % 1) * 100;
		double result =  (rng.nextFloat() * fortunemultiplier * 1 / ((100.d - Bonuschance) / 100.0d))/(itemchance * fortunemultiplier);
		return (int) (Math.floor(itemchance) + result);
	}

    return (int) Math.floor(itemchance);

    }
}
