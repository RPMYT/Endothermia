package net.lilydev.endothermia.block.frostburner;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.lilydev.endothermia.Endothermia;
import net.lilydev.endothermia.block.EndothermiaBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EndothermiaBlockEntities {
    public static final BlockEntityType<FrostburnerBlockEntity> FROSTBURNER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
            new Identifier(Endothermia.MODID, "frostburner_block_entity"),
            FabricBlockEntityTypeBuilder.create(FrostburnerBlockEntity::new, EndothermiaBlocks.BlockEntry.FROSTBURNER.getBlock()).build());

    public static void setup() {
        // dummy
    }
}
