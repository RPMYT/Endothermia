package net.lilydev.endothermia.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.lilydev.endothermia.Endothermia;
import net.lilydev.endothermia.block.frostburner.FrostburnerBlockEntity;
import net.lilydev.endothermia.gui.FrostburnerGuiDescription;
import net.lilydev.endothermia.recipe.FrostburnerRecipe;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EndothermiaBlockEntities {
    public static final BlockEntityType<FrostburnerBlockEntity> FROSTBURNER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
            new Identifier(Endothermia.MODID, "frostburner_block_entity"),
            FabricBlockEntityTypeBuilder.create(FrostburnerBlockEntity::new, EndothermiaBlocks.BlockEntry.FROSTBURNER.getBlock()).build());

    public static final ScreenHandlerType<FrostburnerGuiDescription> FROSTBURNER_SCREEN = ScreenHandlerRegistry.registerSimple(new Identifier(Endothermia.MODID, "frostburner"),
            ((syncId, inventory) -> new FrostburnerGuiDescription(syncId, inventory, ScreenHandlerContext.EMPTY)));

    public static void setup() {
        Registry.register(Registry.RECIPE_SERIALIZER, FrostburnerRecipe.Serializer.IDENTIFIER, FrostburnerRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(Endothermia.MODID, FrostburnerRecipe.Type.NAME), FrostburnerRecipe.Type.INSTANCE);
    }
}
