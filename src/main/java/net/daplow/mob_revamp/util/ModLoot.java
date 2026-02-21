package net.daplow.mob_revamp.util;

import net.daplow.mob_revamp.items.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.condition.RandomChanceLootCondition;

public class ModLoot {
    public static void registerLoot() {
        // Bat Mod
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (id.equals(new Identifier("minecraft", "entities/bat"))) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .with(ItemEntry.builder(ModItems.BAT_WING))
                        .conditionally(RandomChanceLootCondition.builder(1)) // % chance
                        .rolls(ConstantLootNumberProvider.create(1));  // # items dropped

                tableBuilder.pool(poolBuilder);
            }
        });

        // Parrot Mod
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {

            if (id.equals(new Identifier("minecraft", "entities/parrot"))) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .with(ItemEntry.builder(ModItems.PARROT_FEATHER))
                        .conditionally(RandomChanceLootCondition.builder(1)) // % chance
                        .rolls(UniformLootNumberProvider.create(1.0f, 2.0f));  // # items dropped

                tableBuilder.pool(poolBuilder);
            }
        });

        // Squid Mod
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {

            if (id.equals(new Identifier("minecraft", "entities/squid"))) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .with(ItemEntry.builder(ModItems.SQUID_PEARL))
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) // % chance
                        .rolls(ConstantLootNumberProvider.create(1));  // # items dropped

                tableBuilder.pool(poolBuilder);
            }
        });

        // Piglin Brute Mod
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {

            if (id.equals(new Identifier("minecraft", "entities/piglin_brute"))) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .with(ItemEntry.builder(ModItems.PIGLIN_CHESTPLATE))
                        .conditionally(RandomChanceLootCondition.builder(0.05f)) // % chance
                        .rolls(ConstantLootNumberProvider.create(1));  // # items dropped

                tableBuilder.pool(poolBuilder);
            }
        });
    }
}