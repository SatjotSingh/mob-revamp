package net.daplow.mob_revamp.util.other;

import net.minecraft.util.math.random.Random;

public class ScreenShake {

    public static int ticks;
    public static float strength;
    public static final Random RANDOM = Random.create();

    public static void start(int duration, float amount) {
        ticks = duration;
        strength = amount;
        System.out.println("STARTED SCREEN SHAKE!");

    }

    public static void tick() {
        if (ticks > 0) {
            ticks--;
            strength *= 0.90F;
        }
    }
}
