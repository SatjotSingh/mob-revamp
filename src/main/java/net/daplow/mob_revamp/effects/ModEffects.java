package net.daplow.mob_revamp.effects;

import net.daplow.mob_revamp.DaplowMain;
import net.daplow.mob_revamp.effects.type.EcholocationEffect;
import net.daplow.mob_revamp.effects.type.InkEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEffects {
    public static final StatusEffect BAT_POTION = new EcholocationEffect();

    public static final StatusEffect SQUID_POTION = new InkEffect();

    public static void register() {
        Registry.register(Registry.STATUS_EFFECT, new Identifier(DaplowMain.MOD_ID, "echolocation"), BAT_POTION);


        Registry.register(Registry.STATUS_EFFECT, new Identifier(DaplowMain.MOD_ID, "inked"), SQUID_POTION);

    }
}