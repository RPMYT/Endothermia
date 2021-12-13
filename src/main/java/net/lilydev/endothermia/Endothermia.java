package net.lilydev.endothermia;

import net.fabricmc.api.ModInitializer;
import net.lilydev.endothermia.block.EndothermiaBlocks;

public class Endothermia implements ModInitializer {
    @Override
    public void onInitialize() {
        EndothermiaBlocks.register();
    }
}
