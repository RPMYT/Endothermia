package net.lilydev.endothermia.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class FrostburnerScreen extends CottonInventoryScreen<FrostburnerGuiDescription> {
    public FrostburnerScreen(FrostburnerGuiDescription description, PlayerEntity player, Text title) {
        super(description, player, title);
    }
}
