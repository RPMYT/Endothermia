package net.lilydev.endothermia.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.lilydev.endothermia.Endothermia;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class EndothermiaBlocks {
    public static final Tag.Identified<Block> FREEZABLE_BLOCKS = TagFactory.BLOCK.create(new Identifier("endothermia", "freezable_blocks"));

    public static void register() {
        BlockEntry.setup();
    }

    public static enum BlockEntry {
        FROZEN_STONE("frozen_stone", new Block(FabricBlockSettings.copyOf(Blocks.STONE).sounds(BlockSoundGroup.GLASS).slipperiness(0.98F))),
        FROZEN_PLANKS("frozen_planks", new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).sounds(BlockSoundGroup.GLASS).slipperiness(0.98F))),
        ENDOTHERMIC_LANDMINE("endothermic_landmine", new EndothermicLandmineBlock())
        ;
        private Identifier identifier;
        private Block block;
        private BlockItem blockItem;
        public <T extends BlockItem> T getBlockItem() {
            if (blockItem == null) {
                return null;
            }
            try {
                return (T) blockItem;
            } catch (ClassCastException e) {
                return null;
            }
        }
        public <T extends Block> T getBlock() {
            try {
                return (T) block;
            } catch (ClassCastException e) {
                return null;
            }
        }
        public Identifier getIdentifier() {
            return this.identifier;
        }
        private BlockEntry(String name, Block block, BlockItem item) {
            this.identifier = new Identifier(Endothermia.MODID, name);
            this.blockItem = item;
            this.block = block;
        }
        private BlockEntry(String name, Block block) {
            this(name, block, new BlockItem(block, new FabricItemSettings()));
        }
        protected static void setup() {
            for (BlockEntry b : BlockEntry.values()) {
                Registry.register(Registry.BLOCK, b.getIdentifier(), b.getBlock());
                if (b.getBlockItem() != null) {
                    Registry.register(Registry.ITEM, b.getIdentifier(), b.getBlockItem());
                }
            }
        }
    }
}
