package net.daplow.main;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaplowMain implements ModInitializer {

    public static final String MOD_ID = "daplow-changename"; //Change name
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    /*
    * Welcome to Daplow's tutorial by Daplow for Daplow.
    *
    * TO DO 1:
    * make a new folder in in java.net.daplow called your mod
    * move both DaplowClient and Daplow Main in that folder
    * update fabric.mod.json's  entrypoints and mixin. to new thing
    * update daplow-changename.mixins.json to new thing
    * change gradle.properties's maven_group to be new thing
    * Delete main folder
    *
    * TO DO 2:
    * make new modID, should be "daplow-(what mod is about)"
    * in DaplowMain, change "daplow-changename" to new modID.
    * in resources, change both assets and data from "daplow-changename" to new modID
    * in resources, change daplow-changename.mixins.json to new modID.mixins.json
    * in fabric.mod.json change mixin to modID, and change icon to assets/modID/icon.png and id (at top) to modID
    * in gradle.properties, change archives_base_name to modID
    *
    *
    * Now make your mod!
    *
    * */

    @Override
    public void onInitialize() {
        LOGGER.info("WAKE UP DAPLOW!!!");  //Keep this to be



    }
}
