package net.daplow.mob_revamp.util.Screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.daplow.mob_revamp.DaplowMain.MOD_ID;

public class ModScreenHandler {

    public static ExtendedScreenHandlerType<PiggyScreenHandler> CUSTOM_PIGGY_SCREEN;

    public static void register() {
        CUSTOM_PIGGY_SCREEN = Registry.register(
                Registry.SCREEN_HANDLER,
                new Identifier(MOD_ID, "custom_piggy_screen"),
                new ExtendedScreenHandlerType<>((syncId, inv, buf) ->
                        new PiggyScreenHandler(syncId, inv, buf.readEnumConstant(Hand.class))
                )
        );
    }
}
