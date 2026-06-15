package com.tosterabgx.bettercopper.mixin;

import com.tosterabgx.bettercopper.CopperRailsConfig;
import com.tosterabgx.bettercopper.block.CopperRailBlock;
import com.tosterabgx.bettercopper.block.OxidizableCopperRailBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.vehicle.minecart.OldMinecartBehavior;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OldMinecartBehavior.class)
public abstract class OldMinecartBehaviorMixin extends MinecartBehaviorMixin {

    @Inject(method = "getSlowdownFactor", at = @At("HEAD"), cancellable = true)
    private void getSlowdownFactor(CallbackInfoReturnable<Double> cir) {
        BlockPos pos = this.minecart.getCurrentBlockPosOrRailBelow();
        BlockState state = this.level().getBlockState(pos);
        Block block = state.getBlock();

        if (block instanceof CopperRailBlock) {
            boolean hasPassenger = this.minecart.isVehicle();

            if (block instanceof OxidizableCopperRailBlock oxidizable) {
                double factor = switch (oxidizable.getAge()) {
                    case UNAFFECTED -> hasPassenger ? CopperRailsConfig.COPPER_RAIL_SLOWDOWN : CopperRailsConfig.COPPER_RAIL_SLOWDOWN - CopperRailsConfig.EMPTY_MINECART_SLOWDOWN_BONUS;
                    case EXPOSED -> hasPassenger ? CopperRailsConfig.EXPOSED_COPPER_RAIL_SLOWDOWN : CopperRailsConfig.EXPOSED_COPPER_RAIL_SLOWDOWN - CopperRailsConfig.EMPTY_MINECART_SLOWDOWN_BONUS;
                    case WEATHERED -> hasPassenger ? CopperRailsConfig.WEATHERED_COPPER_RAIL_SLOWDOWN : CopperRailsConfig.WEATHERED_COPPER_RAIL_SLOWDOWN - CopperRailsConfig.EMPTY_MINECART_SLOWDOWN_BONUS;
                    case OXIDIZED -> hasPassenger ? CopperRailsConfig.OXIDIZED_COPPER_RAIL_SLOWDOWN : CopperRailsConfig.OXIDIZED_COPPER_RAIL_SLOWDOWN - CopperRailsConfig.EMPTY_MINECART_SLOWDOWN_BONUS;
                };
                cir.setReturnValue(factor);
            } else {
                cir.setReturnValue(hasPassenger? CopperRailsConfig.WAXED_COPPER_RAIL_SLOWDOWN : CopperRailsConfig.WAXED_COPPER_RAIL_SLOWDOWN - CopperRailsConfig.EMPTY_MINECART_SLOWDOWN_BONUS);
            }
        }
    }
}