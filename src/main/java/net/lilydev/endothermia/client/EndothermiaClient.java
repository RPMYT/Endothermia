package net.lilydev.endothermia.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.lilydev.endothermia.block.EndothermiaBlockEntities;
import net.lilydev.endothermia.gui.FrostburnerScreen;

@Environment(EnvType.CLIENT)
public class EndothermiaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(EndothermiaBlockEntities.FROSTBURNER_SCREEN, ((handler, inventory, title) -> new FrostburnerScreen(handler, inventory.player, title)));
    }
}
