package net.daplow.mob_revamp.mixin;

import net.daplow.mob_revamp.util.ModPackets;
import net.daplow.mob_revamp.util.other.SmashAnimationAccess;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(WardenEntity.class)
public abstract class WardenEntityMixin implements SmashAnimationAccess {

    private static int slamCooldownDuration = 150;  //keep at 200

    private static int slamDuration = 40;

    @Unique
    private int slamCooldown = 0;

    @Unique
    private int slamCharge = 0;

    @Unique
    private final AnimationState smashAnimationState = new AnimationState();

    @Unique
    private boolean chargingSlam = false;

    @Unique
    private static final byte SMASH_ANIMATION = 100;


    @Override
    public AnimationState getSmashAnimationState() {
        return smashAnimationState;
    }

    @Inject(method = "mobTick", at = @At("TAIL"))
    private void tickSlam(CallbackInfo ci) {

        WardenEntity warden = (WardenEntity)(Object)this;

        if (warden.getWorld().isClient())
            return;

        LivingEntity target = warden.getTarget();

        if (slamCooldown > 0)
            slamCooldown--;

        if (target == null)
            return;

        // Start charging

        if (!chargingSlam &&
                slamCooldown <= 0 &&
                warden.distanceTo(target) <= 6
                && !warden.chargingSonicBoomAnimationState.isRunning()

        )
        {

            chargingSlam = true;
            slamCharge = slamDuration;

            warden.getNavigation().stop();

        }

        if (slamCharge == slamDuration-15) {
            warden.getWorld().sendEntityStatus(warden, SMASH_ANIMATION);
        }

        if (chargingSlam) {

            slamCharge--;

            warden.getNavigation().stop();
            warden.swingHand(Hand.MAIN_HAND);
            warden.swingHand(Hand.OFF_HAND);

            if (slamCharge <= 0) {

                chargingSlam = false;
                slamCooldown = slamCooldownDuration; // 200
                performSlam(warden);
            }
        }
    }


    @Inject(method = "handleStatus", at = @At("TAIL"))
    private void handleSmashStatus(byte status, CallbackInfo ci) {

        WardenEntity warden = (WardenEntity)(Object)this;

        if (status == SMASH_ANIMATION) {
            smashAnimationState.start(warden.age);
        }
    }


    @Unique
    private void performSlam(WardenEntity warden) {

        World world = warden.getWorld();

        world.playSound(
                null,
                warden.getBlockPos(),
                SoundEvents.ENTITY_WARDEN_ATTACK_IMPACT,
                SoundCategory.HOSTILE,
                3F,
                0.8F
        );

        ServerWorld server = (ServerWorld)world;

        server.spawnParticles(
                ParticleTypes.EXPLOSION,
                warden.getX(),
                warden.getY(),
                warden.getZ(),
                10,
                0,
                0,
                0,
                0
        );

        server.spawnParticles(
                ParticleTypes.SCULK_SOUL,
                warden.getX(),
                warden.getY() + 0.2,
                warden.getZ(),
                80,
                2,
                0.2,
                2,
                0
        );


        List<LivingEntity> entities =
                world.getEntitiesByClass(
                        LivingEntity.class,
                        warden.getBoundingBox().expand(6),
                        e -> e != warden
                );

        for (LivingEntity entity : entities) {

            entity.damage(
                    DamageSource.mob(warden),
                    12F
            );

            Vec3d dir = entity.getPos()
                    .subtract(warden.getPos())
                    .normalize();

            entity.addVelocity(
                    dir.x * 1.2,
                    0.8,
                    dir.z * 1.2
            );

            entity.velocityModified = true;
        }


            for (ServerPlayerEntity player : server.getPlayers()) {

                if (player.squaredDistanceTo(warden) < 20 * 20) {
                    ServerPlayNetworking.send(
                            player,
                            ModPackets.WARDEN_SLAM_SHAKE,
                            PacketByteBufs.empty()
                    );
                }
            }

        List<LivingEntity> far_entities =
                world.getEntitiesByClass(
                        LivingEntity.class,
                        warden.getBoundingBox().expand(12),
                        e -> e != warden
                );

        for (LivingEntity entity : far_entities) {
            if (entity instanceof PlayerEntity player) {
                float health = player.getHealth();

                player.damage(DamageSource.mob(warden), 0.01F);
                player.setHealth(health);
            }
        }



    }

}

