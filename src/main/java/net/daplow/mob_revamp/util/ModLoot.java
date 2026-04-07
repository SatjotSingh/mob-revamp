package net.daplow.mob_revamp.util;

import net.daplow.mob_revamp.items.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.function.SetNameLootFunction;
import net.minecraft.loot.function.SetNbtLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.condition.RandomChanceLootCondition;


@SuppressWarnings("deprecation")
public class ModLoot {

    // Guardian Mod
    private static final String[] GUARDIAN_VARIENTS = {
            "blue", "red", "green", "purple", "yellow", "orange"
    };

    private static final String[] GUARDIAN_NAMES = {
            "Deep Blue Guardian Eye",
            "Strong Red Guardian Eye",
            "Healthy Green Guardian Eye",
            "Ranged Purple Guardian Eye",
            "Speedy Yellow Guardian Eye",
            "Fast Orange Guardian Eye"
    };

    private static final Formatting[] GUARDIAN_COLORS = {
            Formatting.BLUE,
            Formatting.RED,
            Formatting.GREEN,
            Formatting.DARK_PURPLE,
            Formatting.YELLOW,
            Formatting.GOLD
    };

    public static void register() {
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
                tableBuilder.pool(LootPool.builder()
                        .with(ItemEntry.builder(ModItems.PARROT_FEATHER))
                        .conditionally(RandomChanceLootCondition.builder(1)) // % chance
                        .rolls(UniformLootNumberProvider.create(1.0f, 2.0f)));  // # items dropped

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
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {

            if (id.equals(new Identifier("minecraft", "entities/glow_squid"))) {
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

        // Guardian Mod
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {

            if (id.equals(new Identifier("minecraft", "entities/guardian"))) {

                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.05f)); // 5% chance for **the pool**
                ;

                for (int i = 0; i < 6; i++) {

                    String variant = GUARDIAN_VARIENTS[i];
                    String name = GUARDIAN_NAMES[i];
                    Formatting color = GUARDIAN_COLORS[i];

                    NbtCompound nbt = new NbtCompound();
                    nbt.putString("Eye_Variant", variant);

                    ItemEntry.Builder entry = ItemEntry.builder(ModItems.GUARDIAN_EYE)
                            .apply(SetNbtLootFunction.builder(nbt))
                            .apply(SetNameLootFunction.builder(
                                    Text.literal(name)
                                            .styled(style -> style.withItalic(false))
                                            .formatted(color)
                            ))
                            .weight(1);

                    poolBuilder.with(entry);
                }

                tableBuilder.pool(poolBuilder);
            }
        });

        // Wither Skeleton Mod
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {

            if (id.equals(new Identifier("minecraft", "entities/wither_skeleton"))) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .with(ItemEntry.builder(ModItems.WITHER_BONE))
                        .conditionally(RandomChanceLootCondition.builder(1f)) // % chance
                        .rolls(UniformLootNumberProvider.create(1.0f, 2.0f));  // # items dropped

                tableBuilder.pool(poolBuilder);


            }
        });



        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {

            if (id.equals(new Identifier("minecraft", "entities/wither"))) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .with(ItemEntry.builder(ModItems.WITHER_BONE))
                        .conditionally(RandomChanceLootCondition.builder(1f)) // % chance
                        .rolls(UniformLootNumberProvider.create(5.0f, 10.0f));  // # items dropped


                tableBuilder.pool(poolBuilder);
            }
        });

    }
}