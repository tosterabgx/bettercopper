package com.tosterabgx.bettercopper.block;

import com.tosterabgx.bettercopper.BetterCopper;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {
    public static final Block COPPER_RAIL = register("copper_rail", props -> new OxidizableCopperRailBlock(WeatheringCopper.WeatherState.UNAFFECTED, props), BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL), true);
    public static final Block EXPOSED_COPPER_RAIL = register("exposed_copper_rail", props -> new OxidizableCopperRailBlock(WeatheringCopper.WeatherState.EXPOSED, props), BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL), true);
    public static final Block WEATHERED_COPPER_RAIL = register("weathered_copper_rail", props -> new OxidizableCopperRailBlock(WeatheringCopper.WeatherState.WEATHERED, props), BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL), true);
    public static final Block OXIDIZED_COPPER_RAIL = register("oxidized_copper_rail", props -> new OxidizableCopperRailBlock(WeatheringCopper.WeatherState.OXIDIZED, props), BlockBehaviour.Properties.ofFullCopy(Blocks.RAIL), true);
    public static final Block WAXED_COPPER_RAIL = register("waxed_copper_rail", CopperRailBlock::new, BlockBehaviour.Properties.ofFullCopy(ModBlocks.COPPER_RAIL), true);
    public static final Block WAXED_EXPOSED_COPPER_RAIL = register("waxed_exposed_copper_rail", CopperRailBlock::new, BlockBehaviour.Properties.ofFullCopy(ModBlocks.EXPOSED_COPPER_RAIL), true);
    public static final Block WAXED_WEATHERED_COPPER_RAIL = register("waxed_weathered_copper_rail", CopperRailBlock::new, BlockBehaviour.Properties.ofFullCopy(ModBlocks.WEATHERED_COPPER_RAIL), true);
    public static final Block WAXED_OXIDIZED_COPPER_RAIL = register("waxed_oxidized_copper_rail", CopperRailBlock::new, BlockBehaviour.Properties.ofFullCopy(ModBlocks.OXIDIZED_COPPER_RAIL), true);

    public static void initialize() {
        putInRedstoneTab(ModBlocks.COPPER_RAIL);
        putInRedstoneTab(ModBlocks.EXPOSED_COPPER_RAIL);
        putInRedstoneTab(ModBlocks.WEATHERED_COPPER_RAIL);
        putInRedstoneTab(ModBlocks.OXIDIZED_COPPER_RAIL);

        OxidizableBlocksRegistry.registerNextStage(ModBlocks.COPPER_RAIL, ModBlocks.EXPOSED_COPPER_RAIL);
        OxidizableBlocksRegistry.registerNextStage(ModBlocks.EXPOSED_COPPER_RAIL, ModBlocks.WEATHERED_COPPER_RAIL);
        OxidizableBlocksRegistry.registerNextStage(ModBlocks.WEATHERED_COPPER_RAIL, ModBlocks.OXIDIZED_COPPER_RAIL);
        OxidizableBlocksRegistry.registerWaxable(ModBlocks.COPPER_RAIL, ModBlocks.WAXED_COPPER_RAIL);
        OxidizableBlocksRegistry.registerWaxable(ModBlocks.EXPOSED_COPPER_RAIL, ModBlocks.WAXED_EXPOSED_COPPER_RAIL);
        OxidizableBlocksRegistry.registerWaxable(ModBlocks.WEATHERED_COPPER_RAIL, ModBlocks.WAXED_WEATHERED_COPPER_RAIL);
        OxidizableBlocksRegistry.registerWaxable(ModBlocks.OXIDIZED_COPPER_RAIL, ModBlocks.WAXED_OXIDIZED_COPPER_RAIL);
    }

    private static void putInRedstoneTab(Block block) {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.REDSTONE_BLOCKS).register((creativeTab) -> {
            creativeTab.accept(block.asItem());
        });
    }

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties, boolean shouldRegisterItem) {
        ResourceKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(properties.setId(blockKey));

        if (shouldRegisterItem) {
            ResourceKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(BetterCopper.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(BetterCopper.MOD_ID, name));
    }
}