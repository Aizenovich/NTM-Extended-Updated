package com.hbm.blocks.gas;

import java.util.Random;

import com.hbm.interfaces.Untested;
import com.hbm.lib.ForgeDirection;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGasFlammable extends BlockGasBase {

	protected static final int MAX_GAS_COMBUSTIONS_PER_TICK = 64;

	protected static long lastGasCombustionTick = Long.MIN_VALUE;
	protected static int gasCombustionsThisTick = 0;

	protected static final ThreadLocal<Boolean> GAS_COMBUSTING = new ThreadLocal<Boolean>() {
		@Override
		protected Boolean initialValue() {
			return Boolean.FALSE;
		}
	};

	public BlockGasFlammable(String s) {
		super(0.8F, 0.8F, 0.2F, s);
	}

	@Override
	public ForgeDirection getFirstDirection(World world, int x, int y, int z) {

		if(world.rand.nextInt(3) == 0)
			return ForgeDirection.getOrientation(world.rand.nextInt(2));

		return this.randomHorizontal(world);
	}

	@Override
	public ForgeDirection getSecondDirection(World world, int x, int y, int z) {
		return this.randomHorizontal(world);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if(world.isRemote) {
			return;
		}

		if(state.getBlock() != this || world.getBlockState(pos).getBlock() != this) {
			return;
		}

		if(!world.isChunkGeneratedAt(pos.getX() >> 4, pos.getZ() >> 4) || !world.isAreaLoaded(pos, 1)) {
			return;
		}

		MutableBlockPos posN = new BlockPos.MutableBlockPos();

		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			posN.setPos(pos.getX() + dir.offsetX, pos.getY() + dir.offsetY, pos.getZ() + dir.offsetZ);

			if(!world.isBlockLoaded(posN)) {
				continue;
			}

			IBlockState b = world.getBlockState(posN);

			if(isFireSource(b)) {
				combust(world, pos);
				return;
			}
		}

		super.updateTick(world, pos, state, rand);

		if(world.getBlockState(pos).getBlock() == this && rand.nextInt(20) == 0 && world.isAirBlock(pos.down())) {
			safeRemoveGas(world, pos);
		}
	}

	@Untested
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if(world.isRemote || GAS_COMBUSTING.get()) {
			return;
		}

		if(state.getBlock() != this || world.getBlockState(pos).getBlock() != this) {
			return;
		}

		if(!world.isAreaLoaded(pos, 1)) {
			return;
		}

		MutableBlockPos posN = new BlockPos.MutableBlockPos();

		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			posN.setPos(pos.getX() + dir.offsetX, pos.getY() + dir.offsetY, pos.getZ() + dir.offsetZ);

			if(!world.isBlockLoaded(posN)) {
				continue;
			}

			IBlockState b = world.getBlockState(posN);

			if(isFireSource(b)) {
				world.scheduleUpdate(pos, this, getDelay(world));
				return;
			}
		}
	}

	protected void combust(World world, BlockPos p) {
		if(world.isRemote || !world.isBlockLoaded(p)) {
			return;
		}

		if(world.getBlockState(p).getBlock() != this) {
			return;
		}

		if(GAS_COMBUSTING.get() || !reserveGasCombustion(world)) {
			safeRemoveGas(world, p);
			return;
		}

		GAS_COMBUSTING.set(Boolean.TRUE);

		try {
			world.setBlockState(p, Blocks.FIRE.getDefaultState(), 2);
		} finally {
			GAS_COMBUSTING.set(Boolean.FALSE);
		}
	}

	protected static boolean reserveGasCombustion(World world) {
		long worldTick = world.getTotalWorldTime();

		synchronized(BlockGasFlammable.class) {
			if(lastGasCombustionTick != worldTick) {
				lastGasCombustionTick = worldTick;
				gasCombustionsThisTick = 0;
			}

			if(gasCombustionsThisTick >= MAX_GAS_COMBUSTIONS_PER_TICK) {
				return false;
			}

			gasCombustionsThisTick++;
			return true;
		}
	}

	protected static void safeRemoveGas(World world, BlockPos pos) {
		if(!world.isRemote && world.isBlockLoaded(pos)) {
			world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
		}
	}

	public boolean isFireSource(IBlockState b) {
		return b.getMaterial() == Material.FIRE || b.getMaterial() == Material.LAVA || b.getBlock() == Blocks.TORCH;
	}

	@Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return true;
	}

	@Override
	public int getDelay(World world) {
		return world.rand.nextInt(5) + 16;
	}
}
