package net.lilydev.endothermia.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.lilydev.endothermia.block.EndothermiaBlockEntities;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;

public class FrostburnerGuiDescription extends SyncedGuiDescription {
    public FrostburnerGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(EndothermiaBlockEntities.FROSTBURNER_SCREEN, syncId, playerInventory, getBlockInventory(context, 3), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel();
        this.setRootPanel(root);
        root.setSize(300, 200);
        root.setInsets(Insets.ROOT_PANEL);
    }
}
