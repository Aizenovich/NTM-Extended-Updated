package com.hbm.blocks.gas;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGasExplosive extends BlockGasFlammable {

	private static final float EXPLOSIVE_GAS_POWER = 2.0F;
	private static final boolean EXPLOSIVE_GAS_CAUSES_FIRE = false;
	private static final boolean EXPLOSIVE_GAS_BREAKS_BLOCKS = false;

	public BlockGasExplosive(String s) {
		super(s);
	}

	@Override
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
			safeRemoveGas(world, p);
			world.newExplosion(null, p.getX() + 0.5, p.getY() + 0.5, p.getZ() + 0.5, EXPLOSIVE_GAS_POWER, EXPLOSIVE_GAS_CAUSES_FIRE, EXPLOSIVE_GAS_BREAKS_BLOCKS);
		} finally {
			GAS_COMBUSTING.set(Boolean.FALSE);
		}
	}
}
