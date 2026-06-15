package com.tosterabgx.bettercopper.mixin;

import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import net.minecraft.world.entity.vehicle.minecart.MinecartBehavior;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MinecartBehavior.class)
public abstract class MinecartBehaviorMixin {

    @Shadow
    @Final
    protected AbstractMinecart minecart;

    @Shadow
    public abstract Level level();
}
