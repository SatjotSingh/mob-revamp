package net.daplow.mob_revamp.items.custom;

import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.ProjectileDamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class GuardianStaffItem extends Item {

    public GuardianStaffItem(Settings settings) {
        super(settings);
    }


    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        if (this.isIn(group)) {

            stacks.add(createStaff("blue", "Deep Blue Guardian Staff", Formatting.BLUE));     // Does Nothing
            stacks.add(createStaff("red", "Strong Red Guardian Staff", Formatting.RED));      // Does 50% more damage
            stacks.add(createStaff("green", "Healthy Green Guardian Staff", Formatting.GREEN));    // More Health
            stacks.add(createStaff("purple", "Ranged Purple Guardian Staff", Formatting.DARK_PURPLE));   // 50% more distance
            stacks.add(createStaff("yellow", "Speedy Yellow Guardian Staff", Formatting.YELLOW));   // Less Cooldown
            stacks.add(createStaff("orange", "Fast Orange Guardian Staff", Formatting.GOLD));   // No wait Cooldown


        }
    }
    private ItemStack createStaff(String variant, String name, Formatting color) {
        ItemStack stack = new ItemStack(this);
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putString("Eye_Variant", variant);
        stack.setCustomName(Text.literal(name)
                .styled(style -> style.withItalic(false))
                .formatted(color)
        );

        return stack;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    /*

    */
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 92000;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        user.setCurrentHand(hand); // start charging

        return TypedActionResult.consume(stack);
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
        NbtCompound nbt = stack.getNbt();
        if (Objects.equals(nbt.getString("Eye_Variant"), "purple")) {
            RANGE = 30;
        }


        for (double i = 0; i< (double) RANGE /2; i+=0.5) {
            Vec3d beamPos = start.add(look.multiply(i));

            double angle = (useTime * 0.3) + i + 0.5;

            double x = Math.cos(angle) * radius;
            double y = Math.sin(angle) * radius;

            Vec3d spiralOffset = side.multiply(x).add(up.multiply(y));
            Vec3d particlePos = beamPos.add(spiralOffset);


            world.addParticle(
                    ParticleTypes.BUBBLE,
                    particlePos.x,
                    particlePos.y,
                    particlePos.z,
                    0,
                    0,
                    0
            );


        }


    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        super.onStoppedUsing(stack, world, user, remainingUseTicks);

        if (!(user instanceof PlayerEntity player)) return;

        int useTime = this.getMaxUseTime(stack) - remainingUseTicks;

        NbtCompound nbt = stack.getNbt();

        int USETIME = 30;
        if (Objects.equals(nbt.getString("Eye_Variant"), "yellow")) {
            USETIME = 20;
        }
        if (useTime < USETIME) return;

        if (!world.isClient) {


            int RANGE = 20;

            if (Objects.equals(nbt.getString("Eye_Variant"), "purple")) {
                RANGE = 30;
            }
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
                        ParticleTypes.END_ROD,
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
                    if (Objects.equals(nbt.getString("Eye_Variant"), "red")) {
                        DAMAGE = 20;
                    }
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
            if (!Objects.equals(nbt.getString("Eye_Variant"), "orange")) {
                player.getItemCooldownManager().set(this, COOLDOWN);
            }
            if (!(Objects.equals(nbt.getString("Eye_Variant"), "green") && world.random.nextFloat() < 0.5f)) {
                stack.damage(1, user, (e) -> {
                    e.sendToolBreakStatus(user.getActiveHand());
                });
            }

        }
        //return TypedActionResult.success(stack);

    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }


}
