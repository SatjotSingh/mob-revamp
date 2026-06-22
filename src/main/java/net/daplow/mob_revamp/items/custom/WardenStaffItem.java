package net.daplow.mob_revamp.items.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class WardenStaffItem extends Item {
    public WardenStaffItem(Settings settings) {
        super(settings);
    }


    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        user.setCurrentHand(hand); // start charging

        return TypedActionResult.consume(stack);
    }


    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 92000;
    }



    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);

        if (!world.isClient()) return;

        int useTime = this.getMaxUseTime(stack) - remainingUseTicks;

        // Not instant
        if (useTime < 3) return;

        Vec3d start = user.getEyePos();
        Vec3d look = user.getRotationVec(1.0f);

        Vec3d side = look.crossProduct(new Vec3d(0,1,0).normalize());
        Vec3d up = look.crossProduct(side).normalize();

        float radius = 0.15f;

        int RANGE = 20;



        for (double i = 2; i< (double) RANGE /2; i+=0.5) {
            if(Math.random() < 0.1) {
                Vec3d beamPos = start.add(look.multiply(i));

                double angle = (useTime * 0.3) + i + 0.5;

                double x = Math.cos(angle) * radius;
                double y = Math.sin(angle) * radius;

                Vec3d spiralOffset = side.multiply(x).add(up.multiply(y));
                Vec3d particlePos = beamPos.add(spiralOffset);

                world.addParticle(
                        ParticleTypes.SCULK_SOUL,
                        particlePos.x,
                        particlePos.y,
                        particlePos.z,
                        0,
                        0,
                        0
                );
            }

        }


    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        super.onStoppedUsing(stack, world, user, remainingUseTicks);

        if (!(user instanceof PlayerEntity player)) return;

        int useTime = this.getMaxUseTime(stack) - remainingUseTicks;


        int USETIME = 30;

        if (useTime < USETIME) return;

        if (!world.isClient) {


            int RANGE = 20;


            Vec3d start = user.getEyePos();
            Vec3d look = user.getRotationVec(1.0f);
            Vec3d end  = start.add(look.multiply(RANGE));

            Box box = user.getBoundingBox().stretch(look.multiply(RANGE)).expand(1.0);

            List<Entity> entities = world.getOtherEntities(
                    user,
                    box,
                    entity -> entity instanceof LivingEntity && entity.isAlive()
            );
            //System.out.println("Right Clicked on Guardian Staff");

            for (int i = 0; i < RANGE; i++) {

                Vec3d pos = start.add(look.multiply(i*0.5));

                ((ServerWorld)world).spawnParticles(
                        ParticleTypes.SCULK_CHARGE_POP,
                        pos.x,
                        pos.y,
                        pos.z,
                        1,
                        0,
                        0,
                        0,
                        0
                );

            }

            // check if enemyHit is a entity
            for (Entity entity : entities) {

                if (entity != null) {
                    Vec3d toEntity = entity.getPos().subtract(start);

                    // how far along the beam the entity is
                    double projection = toEntity.dotProduct(look);

                    // ignore entities behind player or past range
                    if (projection < 0 || projection > RANGE) continue;

                    // closest point on the beam
                    Vec3d closestPoint = start.add(look.multiply(projection));

                    // distance from beam center
                    double distance = entity.getPos().distanceTo(closestPoint);

                    if (distance > 1.0) continue; // beam thickness

                    float DAMAGE = 15;
                    if (entity.isTouchingWater()) {
                        DAMAGE *= 1.3F;
                    }


                    entity.damage(DamageSource.mob(user), DAMAGE);


                    //System.out.println("Lazer Shot!!");

                }


            }
            world.playSound(
                    null,
                    user.getBlockPos(),
                    SoundEvents.ENTITY_GUARDIAN_ATTACK,
                    SoundCategory.PLAYERS,
                    1.0f,
                    1.0f
            );

            int COOLDOWN = 20;
            player.getItemCooldownManager().set(this, COOLDOWN);
            }
        stack.damage(1, user, (e) -> {
                    e.sendToolBreakStatus(user.getActiveHand());
                });



        //return TypedActionResult.success(stack);

    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }


}
