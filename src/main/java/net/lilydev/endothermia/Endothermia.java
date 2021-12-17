package net.lilydev.endothermia;

import net.fabricmc.api.ModInitializer;
import net.lilydev.endothermia.block.EndothermiaBlockEntities;
import net.lilydev.endothermia.block.EndothermiaBlocks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Endothermia implements ModInitializer {
    public static final String MODID = "endothermia";
    public static final Logger LOGGER = LogManager.getLogger("Endothermia");

    @Override
    public void onInitialize() {
        EndothermiaBlocks.register();
        EndothermiaBlockEntities.setup();
    }
}