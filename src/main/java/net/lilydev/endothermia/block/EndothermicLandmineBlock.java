package net.lilydev.endothermia.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.lilydev.endothermia.world.EndothermicExplosion;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class EndothermicLandmineBlock extends Block {
    public EndothermicLandmineBlock() {
        super(FabricBlockSettings.copyOf(Blocks.SNOW_BLOCK).breakByHand(true).allowsSpawning((state, world, pos, type) -> false));
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);
        EndothermicExplosion explosion = new EndothermicExplosion(world, null, pos.getX(), pos.getY(), pos.getZ(), 2.5F);
        explosion.collectBlocksAndDamageEntities();
        explosion.affectWorld(true);
        world.removeBlock(pos, false);
    }
}