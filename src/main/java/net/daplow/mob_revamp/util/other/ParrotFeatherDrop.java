package net.daplow.mob_revamp.util.other;

import net.daplow.mob_revamp.items.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

import java.util.Random;

// For parrots to randomly drop the feather
public class ParrotFeatherDrop {

    private static final Random RANDOM = new Random();
    private static final int DROP_INTERVAL_TICKS = 1200; // 60 seconds at 20 TPS

    private static int tickCounter = 0;

    public static void register() {
        // Run every server tick
        ServerTickEvents.END_SERVER_TICK.register((MinecraftServer server) -> {
            tickCounter++;
            if (tickCounter < DROP_INTERVAL_TICKS) return; // only check every 60 seconds
            tickCounter = 0;

            for (ServerWorld world : server.getWorlds()) {
                dropFeathersFromParrots(world);
            }
        });
    }

    private static void dropFeathersFromParrots(ServerWorld world) {
        // Loop all loaded entities
        for (var entity : world.iterateEntities()) {
            if (entity instanceof ParrotEntity parrot) {
                tryDropParrotFeather(parrot);
            }
        }
    }

    private static void tryDropParrotFeather(ParrotEntity parrot) {
        if (parrot.world.isClient) return; // only on server

        // 1 in 5 chance every interval (can adjust)
        if (RANDOM.nextInt(5) == 0) {
            var pos = parrot.getBlockPos();
            ItemEntity feather = new ItemEntity(
                    parrot.world,
                    pos.getX() + 0.5,
                    pos.getY(),
                    pos.getZ() + 0.5,
                    new ItemStack(ModItems.PARROT_FEATHER)
            );
            parrot.world.spawnEntity(feather);
        }
    }
}
