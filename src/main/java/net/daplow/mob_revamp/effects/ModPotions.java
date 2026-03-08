package net.daplow.mob_revamp.effects;

import net.daplow.mob_revamp.DaplowMain;
import net.daplow.mob_revamp.effects.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModPotions {

    public static final Potion BAT_POTION =
            Registry.register(
                    Registry.POTION,
                    new Identifier(DaplowMain.MOD_ID, "bat_potion"),
                    new Potion(new StatusEffectInstance(
                            ModEffects.BAT_POTION,
                            20 * 90
                    ))
            );


    public static final Potion SQUID_POTION =
            Registry.register(
                    Registry.POTION,
                    new Identifier(DaplowMain.MOD_ID, "squid_potion"),
                    new Potion(new StatusEffectInstance(
                            ModEffects.SQUID_POTION,
                            20 * 10,
                            0,
                            false,
                            false,
                            true
                    ))
            );


    public static void register() {}
}