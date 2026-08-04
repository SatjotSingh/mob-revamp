package net.daplow.mob_revamp.commands;

import net.daplow.mob_revamp.commands.custom.CommandTesting;
import net.daplow.mob_revamp.commands.custom.FlowerTreeCommand;
import net.daplow.mob_revamp.commands.custom.GuardianEyeCommand;
import net.daplow.mob_revamp.commands.custom.GuardianStaffCommand;

public class ModCommands {

    public static void register() {

        FlowerTreeCommand.register();

        CommandTesting.register();

        GuardianEyeCommand.register();

        GuardianStaffCommand.register();

    }
}
