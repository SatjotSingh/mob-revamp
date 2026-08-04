package net.daplow.mob_revamp.items.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.Random;

public class BambooFluteItem extends Item {

    public BambooFluteItem(Settings settings) {
        super(settings);
    }

    private static final Random RANDOM = new Random();

    // Array of hostile mob sounds
    private static final SoundEvent[] HOSTILE_SOUNDS = new SoundEvent[]{
            SoundEvents.ENTITY_PARROT_IMITATE_ZOMBIE,
            SoundEvents.ENTITY_PARROT_IMITATE_SPIDER
    };

    // Called when right-clicking in the air
    // Start using the item (War Horn style animation)
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand); // start using animation
        if (!world.isClient) {
            // Play sound immediately (or could schedule a tick delay)
            SoundEvent sound = HOSTILE_SOUNDS[RANDOM.nextInt(HOSTILE_SOUNDS.length)];
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    sound, net.minecraft.sound.SoundCategory.PLAYERS, 1.0f, 1.0f);

            // Cooldown so you can't spam
            user.getItemCooldownManager().set(this, 20);
        }
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
    }

    // Animation type (Goat Horn uses SPEAR)
    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;  // best

    }

    // Duration of the use animation
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 40; // 2 seconds
    }


}
