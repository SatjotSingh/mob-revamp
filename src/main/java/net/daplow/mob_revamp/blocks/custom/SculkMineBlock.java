package net.daplow.mob_revamp.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class SculkMineBlock extends Block {


    public SculkMineBlock(Settings settings) {
        super(settings);
    }


    // Gives Entity all effects for *sculk effect*
    private static void getSculked(Entity entity) {

        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addStatusEffect(
                    new StatusEffectInstance(StatusEffects.BLINDNESS,(8*20), 0));
            livingEntity.addStatusEffect(
                    new StatusEffectInstance(StatusEffects.NAUSEA,(15*20), 0));
            livingEntity.addStatusEffect(
                    new StatusEffectInstance(StatusEffects.DARKNESS,(15*20), 0));

        }

    }

    // At pos given, it will play sculk mine at that location
    private static void activateSculkMine(World world, BlockPos pos) {
        // give nearby entities sculk effect
        for (PlayerEntity playerEntity : world.getEntitiesByClass(
                PlayerEntity.class,
                new Box(pos).expand(3),
                playerEntity -> true)) {
            getSculked(playerEntity);
        }

        // kaboom sound
        world.playSound(
                null, // null = all nearby players hear it
                pos,
                SoundEvents.BLOCK_SCULK_SHRIEKER_SHRIEK,
                SoundCategory.BLOCKS,
                0.4F, // volume
                1.0F  // pitch
        );

        // some particles
        for (int i= 1; i<75; i++) {
            ((ServerWorld) world).spawnParticles(
                    ParticleTypes.SCULK_CHARGE_POP,
                    pos.getX() -3 + (Math.random()*6),
                    pos.getY() -3 + (Math.random()*6),
                    pos.getZ() -3 + (Math.random()*6),
                    1,
                    0,
                    0,
                    0,
                    0
            );
        }

        // break sculk trap block
        world.breakBlock(pos, false);

    }


    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);

        if(world.isClient()) return;

        // Spawns particles when stepping on sculk trap
        if (Math.random() < 0.4) {
            ((ServerWorld) world).spawnParticles(
                    ParticleTypes.SCULK_CHARGE_POP,
                    pos.getX() + Math.random(),
                    pos.getY() + 1,
                    pos.getZ() + Math.random(),
                    1,
                    0,
                    0,
                    0,
                    0
            );
        }

        if (Math.random() < 0.05) {
            world.playSound(
                    null,
                    pos,
                    SoundEvents.ENTITY_WARDEN_HEARTBEAT,
                    SoundCategory.BLOCKS,
                    0.1F,
                    1.0F
            );

        }
    }


    @Override
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        super.onBlockBreakStart(state, world, pos, player);

        if(world.isClient()) return;

        if(player.getMainHandStack().getItem() instanceof HoeItem) return;

        activateSculkMine(world, pos);

    }


    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);

        boolean powered = world.isReceivingRedstonePower(pos);

        if (powered) {
            // Allows Redstone to Work
            activateSculkMine(world, pos);
        }
    }
}

