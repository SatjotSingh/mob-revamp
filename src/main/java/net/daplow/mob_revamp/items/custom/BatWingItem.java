package net.daplow.mob_revamp.items.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BatWingItem extends Item {

    private final double dashSpeed = 1.1f;

    public BatWingItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            Vec3d lookVec = user.getRotationVector();
            user.setVelocity(lookVec.x * dashSpeed, lookVec.y * dashSpeed, lookVec.z * dashSpeed);
            user.velocityModified = true;

            if (!user.isCreative()) {
                stack.decrement(1);
            }

            user.getItemCooldownManager().set(this, 15);
        } else {
            Vec3d lookVec = user.getRotationVector();
            for (int i = 0; i < 10; i++) {
                double offsetX = -lookVec.x * i * 0.2;
                double offsetY = -lookVec.y * i * 0.2 + 0.5;
                double offsetZ = -lookVec.z * i * 0.2;
                world.addParticle(
                        ParticleTypes.CLOUD,
                        user.getX() + offsetX,
                        user.getY() + offsetY,
                        user.getZ() + offsetZ,
                        0, 0, 0
                );
            }
        }

        return TypedActionResult.success(stack);
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.literal("§8- Can be used to"));
            tooltip.add(Text.literal("§8  craft the Wing Suit"));
            tooltip.add(Text.literal("§6Right-click§7 to get"));
            tooltip.add(Text.literal("§7a boost in speed."));

        } else {
            tooltip.add(Text.literal("§7Hold §eSHIFT§7 for more info"));
        }

        super.appendTooltip(stack, world, tooltip, context);
    }
}
