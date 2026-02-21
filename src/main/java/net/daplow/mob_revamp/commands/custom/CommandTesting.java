package net.daplow.mob_revamp.commands.custom;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class CommandTesting {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(
                    LiteralArgumentBuilder.<ServerCommandSource>literal("daplow") // top-level command
                            .then(
                                    LiteralArgumentBuilder.<ServerCommandSource>literal("spawn") // subcommand
                                            .then(
                                                    LiteralArgumentBuilder.<ServerCommandSource>literal("ur_mom") // final action
                                                            .executes(context -> {
                                                                context.getSource().sendFeedback(
                                                                        Text.literal("Error: Too big to spawn").formatted(Formatting.RED),
                                                                        false
                                                                );
                                                                return 0;
                                                            })
                                            )
                            )
            );
        });
    }
}
