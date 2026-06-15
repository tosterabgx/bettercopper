package com.tosterabgx.bettercopper;

import com.tosterabgx.bettercopper.block.ModBlocks;
import com.tosterabgx.bettercopper.item.ModItems;
import net.fabricmc.api.ModInitializer;

public class BetterCopper implements ModInitializer {
    public static final String MOD_ID = "bettercopper";

    @Override
    public void onInitialize() {
        ModItems.initialize();
        ModBlocks.initialize();
    }
}