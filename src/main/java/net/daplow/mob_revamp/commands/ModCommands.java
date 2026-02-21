package net.daplow.mob_revamp.commands;

import net.daplow.mob_revamp.commands.custom.CommandTesting;
import net.daplow.mob_revamp.commands.custom.FlowerTreeCommand;

public class ModCommands {

    public static void register() {

        FlowerTreeCommand.register();
        CommandTesting.register();

    }
}
