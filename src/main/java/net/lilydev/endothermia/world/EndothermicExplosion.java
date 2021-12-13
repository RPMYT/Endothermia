package net.lilydev.endothermia.world;

import net.lilydev.endothermia.block.EndothermiaBlocks;
import net.lilydev.endothermia.block.EndothermiaBlocks.BlockEntry;
import net.lilydev.endothermia.world.damage.EndothermiaDamageSources;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.ArrayList;

public class EndothermicExplosion extends Explosion {
    private final World world;
    private final Vec3d pos;
    private final float power;

    public EndothermicExplosion(World world, @Nullable Entity entity, double x, double y, double z, float power) {
        super(world, entity, x, y, z, power, false, DestructionType.DESTROY);
        this.world = world;
        this.power = power;
        this.pos = new Vec3d(x, y, z);
    }

    @Override
    public DamageSource getDamageSource() {
        return EndothermiaDamageSources.FROSTBITE.getSource();
    }

    @Override
    public List<BlockPos> getAffectedBlocks() {
        int l;
        int k;
        ArrayList<BlockPos> blocks = new ArrayList<>();
        for (int j = 0; j < 16; ++j) {
            for (k = 0; k < 16; ++k) {
                block2: for (l = 0; l < 16; ++l) {
                    if (j != 0 && j != 15 && k != 0 && k != 15 && l != 0 && l != 15)
                        continue;
                    double d = (float) j / 15.0f * 2.0f - 1.0f;
                    double e = (float) k / 15.0f * 2.0f - 1.0f;
                    double f = (float) l / 15.0f * 2.0f - 1.0f;
                    double g = Math.sqrt(d * d + e * e + f * f);
                    d /= g;
                    e /= g;
                    f /= g;
                    double m = this.pos.x;
                    double n = this.pos.y;
                    double o = this.pos.z;
                    for (float h = this.power
                            * (0.7f + this.world.random.nextFloat() * 0.6f); h > 0.0f; h -= 0.22500001f) {
                        BlockPos blockPos = new BlockPos(m, n, o);
                        BlockState blockState = this.world.getBlockState(blockPos);
                        if (!this.world.isInBuildLimit(blockPos))
                            continue block2;
                        if (blockState.isIn(EndothermiaBlocks.FREEZABLE_BLOCKS)) {
                            blocks.add(blockPos);
                        }
                        m += d * (double) 0.3f;
                        n += e * (double) 0.3f;
                        o += f * (double) 0.3f;
                    }
                }
            }
        }

        return blocks;
    }

    @Override
    public void affectWorld(boolean particles) {
        if (this.world.isClient) {
            this.world.playSound(this.pos.x, this.pos.y, this.pos.z, SoundEvents.ENTITY_GENERIC_EXPLODE,
                    SoundCategory.BLOCKS, 4.0f,
                    (1.0f + (this.world.random.nextFloat() - this.world.random.nextFloat()) * 0.2f) * 0.7f, false);
        } else {
            this.getAffectedBlocks().forEach(pos -> {
                FreezeBlockMaps.INSTANCE.runAll(pos, this);
            });
        }
    }

    public static class FreezeBlockMaps {
        public static FreezeBlockMaps INSTANCE = new FreezeBlockMaps();

        public FreezeBlockMaps() {
            // add these anywhere
            addMap((pos, state, explosion) -> {
                if (state.isIn(BlockTags.PLANKS)) {
                    explosion.world.setBlockState(pos, BlockEntry.FROZEN_PLANKS.getBlock().getDefaultState());
                }
            });
            addMap((pos, state, explosion) -> {
                Block block = state.getBlock();
                if (block == Blocks.STONE) {
                    explosion.world.setBlockState(pos, BlockEntry.FROZEN_STONE.getBlock().getDefaultState());
                }
            });
            addMap((pos, state, explosion) -> {
                Block block = state.getBlock();
                if (block == Blocks.WATER) {
                    explosion.world.setBlockState(pos, Blocks.ICE.getDefaultState());
                }
            });
        }

        public static interface FreezeBlockMap {
            public void checkBlock(BlockPos pos, BlockState state, EndothermicExplosion explosion);
        }

        private ArrayList<FreezeBlockMap> maps = new ArrayList<>();

        public void addMap(FreezeBlockMap map) {
            this.maps.add(map);
        }

        public void runAll(BlockPos pos, EndothermicExplosion explosion) {
            for (FreezeBlockMap map : this.maps) {
                map.checkBlock(pos, explosion.world.getBlockState(pos), explosion);
            }
        }
    }
}