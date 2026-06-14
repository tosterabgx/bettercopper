package com.tosterabgx.bettercopper.item;

import com.tosterabgx.bettercopper.BetterCopper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {
    public static void initialize() {}

    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(BetterCopper.MOD_ID, name));

        T item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

}