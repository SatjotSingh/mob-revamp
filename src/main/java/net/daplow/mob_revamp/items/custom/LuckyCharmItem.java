package net.daplow.mob_revamp.items.custom;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LuckyCharmItem extends Item {
    public LuckyCharmItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack stack = user.getStackInHand(hand);

        if (world.isClient) {
            for (int i = 0; i < 20; i++) {
                double offsetX = (world.random.nextDouble() - 0.5) * 1;
                double offsetY = world.random.nextDouble() * 0.5 * 1.5;
                double offsetZ = (world.random.nextDouble() - 0.5) * 1;
                world.addParticle(
                        ParticleTypes.ENTITY_EFFECT,
                        user.getX() + offsetX,
                        user.getY() + offsetY,
                        user.getZ() + offsetZ,
                        (world.random.nextDouble() - 0.5)*0.2, 1, (world.random.nextDouble() - 0.5)*0.2
                );
            }
        } else {


            // give potion effect Luck
            user.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.LUCK,
                    6000,  // tick (20 * seconds) (6000 = 5 minutes)
                    1,
                    false,
                    false,
                    true
            ));

            // Play sound
            world.playSound(
                    null,
                    user.getBlockPos(),
                    SoundEvents.ENTITY_FIREWORK_ROCKET_LAUNCH,
                    SoundCategory.PLAYERS,
                    1.0f,
                    1.0f
            );

            // remove 1 item
            if (!user.isCreative()) {
                stack.decrement(1);
            }

            // cooldown
            user.getItemCooldownManager().set(this, 15);

        }
        return TypedActionResult.success(stack, world.isClient);

    }
}
