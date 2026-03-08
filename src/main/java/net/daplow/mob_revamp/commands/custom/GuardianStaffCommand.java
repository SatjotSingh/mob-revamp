package net.daplow.mob_revamp.commands.custom;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.daplow.mob_revamp.items.ModItems;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.minecraft.server.command.CommandManager.argument;

public class GuardianStaffCommand {

    private static final String[] GUARDIAN_VARIENTS = {
            "blue", "red", "green", "purple", "yellow", "orange"
    };

    private static final String[] GUARDIAN_NAMES = {
            "Deep Blue Guardian Staff",
            "Strong Red Guardian Staff",
            "Healthy Green Guardian Staff",
            "Ranged Purple Guardian Staff",
            "Speedy Yellow Guardian Staff",
            "Fast Orange Guardian Staff"
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


        RequiredArgumentBuilder<ServerCommandSource, String> variantArg =
                argument("variant", StringArgumentType.word())
                        .suggests((context, builder) -> {
                            String typed = builder.getRemaining().toLowerCase();
                            for (String s : GUARDIAN_VARIENTS) if (s.startsWith(typed)) builder.suggest(s);
                            return builder.buildFuture();
                        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(
                    LiteralArgumentBuilder.<ServerCommandSource>literal("daplow") // top-level command
                            .then(
                                    LiteralArgumentBuilder.<ServerCommandSource>literal("give") // subcommand
                                            .then(
                                                    LiteralArgumentBuilder.<ServerCommandSource>literal("guardian_staff") // final action
                                                            .then(variantArg
                                                                    .executes(context -> {

                                                                        // Command Start
                                                                        String variant = StringArgumentType.getString(context, "variant");

                                                                        int index = -1;
                                                                        for (int i=0; i<GUARDIAN_VARIENTS.length;i++) {
                                                                            if (GUARDIAN_VARIENTS[i].equals(variant)) {
                                                                                index = i;
                                                                                break;
                                                                            }
                                                                        }
                                                                        if (index == -1) {
                                                                            context.getSource().sendError(Text.literal("Invalid variant: " + variant));
                                                                            return 0;
                                                                        }

                                                                        Formatting color = GUARDIAN_COLORS[index];
                                                                        String name = GUARDIAN_NAMES[index];


                                                                        ItemStack stack = createStaff(
                                                                                variant,
                                                                                name,
                                                                                color
                                                                                );

                                                                        ServerCommandSource source = context.getSource();
                                                                        source.getPlayer().getInventory().insertStack(stack);
                                                                        source.sendFeedback(Text.literal("Gave " + name + "!").formatted(Formatting.GREEN), false);

                                                                        // Command End
                                                                        return 1;
                                                                    }
                                                                )
                                                            )
                                            )
                            )
            );
        });
    }

    private static ItemStack createStaff( String variant, String name, Formatting color) {
        Item guardian_eye = ModItems.GUARDIAN_STAFF;
        ItemStack stack = guardian_eye.getDefaultStack();
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putString("Eye_Variant", variant);
        stack.setCustomName(Text.literal(name)
                .styled(style -> style.withItalic(false))
                .formatted(color)
        );

        return stack;
    }

}
