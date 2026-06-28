package net.daplow.mob_revamp.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.particle.ParticleTypes;
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
public abstract class WardenEntityMixin {

    private static int slamDuration = 200;

    @Unique
    private int slamCooldown = 0;

    @Unique
    private int slamCharge = 0;

    @Unique
    private boolean chargingSlam = false;

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
                warden.distanceTo(target) <= 6) {

            chargingSlam = true;
            slamCharge = 20;

        }

        if (chargingSlam) {

            slamCharge--;

            warden.getNavigation().stop();
            warden.swingHand(Hand.MAIN_HAND);
            warden.swingHand(Hand.OFF_HAND);

            if (slamCharge <= 0) {

                chargingSlam = false;
                slamCooldown = slamDuration; // 200
                performSlam(warden);
            }
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


    }
}

