package com.tosterabgx.bettercopper;

import com.tosterabgx.bettercopper.block.ModBlocks;
import com.tosterabgx.bettercopper.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterCopper implements ModInitializer {
    public static final String MOD_ID = "bettercopper";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.initialize();
        ModBlocks.initialize();
    }
}