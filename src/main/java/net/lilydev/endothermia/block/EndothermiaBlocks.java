package net.lilydev.endothermia.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class EndothermiaBlocks {
    private static final HashMap<String, Block> entries = new HashMap<>();

    private static Block add(String name, Block block) {
        entries.put(name, block);
        return block;
    }

    public static final Tag.Identified<Block> FREEZABLE_BLOCKS = TagFactory.BLOCK.create(new Identifier("endothermia", "freezable_blocks"));

    public static final Block FROZEN_STONE = add("frozen_stone", new Block(FabricBlockSettings.copyOf(Blocks.STONE).sounds(BlockSoundGroup.GLASS).slipperiness(0.98F)));
    public static final Block FROZEN_PLANKS = add("frozen_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.GLASS).slipperiness(0.98F)));
    public static final EndothermicLandmineBlock ENDOTHERMIC_LANDMINE = (EndothermicLandmineBlock) add("endothermic_landmine", new EndothermicLandmineBlock());

    public static void register() {
        entries.forEach((name, block) -> {
            Registry.register(Registry.BLOCK, new Identifier("endothermia", name), block);
            Registry.register(Registry.ITEM, new Identifier("endothermia", name), new BlockItem(block, new FabricItemSettings()));
        });
    }
}
