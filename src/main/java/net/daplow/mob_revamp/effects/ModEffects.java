package net.daplow.mob_revamp.effects;

import net.daplow.mob_revamp.effects.type.EcholocationEffect;
import net.daplow.mob_revamp.effects.type.InkEffect;
import net.daplow.mob_revamp.effects.type.RabbitGraceEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.daplow.mob_revamp.DaplowMain.MOD_ID;

public class ModEffects {
    public static final StatusEffect BAT_POTION = new EcholocationEffect();

    public static final StatusEffect SQUID_POTION = new InkEffect();

    public static final StatusEffect RABBIT_POTION = new RabbitGraceEffect();

    public static void register() {
        Registry.register(Registry.STATUS_EFFECT, new Identifier(MOD_ID, "echolocation"), BAT_POTION);

        Registry.register(Registry.STATUS_EFFECT, new Identifier(MOD_ID, "inked"), SQUID_POTION);

        Registry.register(Registry.STATUS_EFFECT, new Identifier(MOD_ID, "rabbit_grace"), RABBIT_POTION);

    }
}