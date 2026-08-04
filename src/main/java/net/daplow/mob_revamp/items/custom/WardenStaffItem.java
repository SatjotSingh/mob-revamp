package net.daplow.mob_revamp.items.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
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
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        super.onStoppedUsing(stack, world, user, remainingUseTicks);

        if (!(user instanceof PlayerEntity player)) return;

        int useTime = this.getMaxUseTime(stack) - remainingUseTicks;


        int USETIME = 30;

        if (useTime < USETIME) return;

        if (!world.isClient) {


            int RANGE = 35;


            Vec3d start = user.getEyePos();
            Vec3d look = user.getRotationVec(1.0f);
            Vec3d end  = start.add(look.multiply(RANGE));

            Box box = user.getBoundingBox().stretch(look.multiply(RANGE)).expand(2.0);

            List<Entity> entities = world.getOtherEntities(
                    user,
                    box,
                    entity -> entity instanceof LivingEntity && entity.isAlive()
            );

            for (int i = 2; i < RANGE; i++) {

                Vec3d pos = start.add(look.multiply(i));
                // Sculk Charge Pop, or Sonic Boom
                ((ServerWorld)world).spawnParticles(
                        ParticleTypes.SONIC_BOOM,
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
                    Optional<Vec3d> hit =
                            entity.getBoundingBox()
                                    .expand(0.5)
                                    .raycast(start, end);

                    if (hit.isEmpty()) {
                        continue;
                    }

                    float DAMAGE = 35;
                    long time = world.getTime() % 24000;

                    if (time >= 13000 && time < 23000){
                        DAMAGE *= 1.3F;
                    }


                    Vec3d dir = entity.getPos().subtract(player.getPos()).normalize();

                    entity.setVelocity(
                            dir.x * 0.5,
                            0.75,
                            dir.z * 0.5
                    );
                    entity.velocityModified = true;


                    // DAMAGE TURN ON
                    entity.damage(DamageSource.mob(user), DAMAGE);



                }


            }
            world.playSound(
                    null,
                    user.getBlockPos(),
                    SoundEvents.ENTITY_WARDEN_ROAR,
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


    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.literal("§8- The most POWERFUL Staff"));
            tooltip.add(Text.literal("§8  in Minecraft..."));
            tooltip.add(Text.literal("§7Hold §6Right-click§7 to"));
            tooltip.add(Text.literal("§7release a Sonic Boom."));

        } else {
            tooltip.add(Text.literal("§7Hold §eSHIFT§7 for more info"));
        }

        super.appendTooltip(stack, world, tooltip, context);
    }

}
