package com.lotkey.villagerflesh.item;

import com.lotkey.villagerflesh.VillagerFlesh;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
                        VillagerFlesh.MOD_ID);

        public static final RegistryObject<Item> VILLAGER_FLESH = ITEMS.register(
                        "villager_flesh", () -> new Item(new Item.Properties().food(Foods.VILLAGER_FLESH)));
        public static final RegistryObject<Item> COOKED_VILLAGER_FLESH = ITEMS.register(
                        "cooked_villager_flesh",
                        () -> new Item(new Item.Properties().food(Foods.COOKED_VILLAGER_FLESH)));

        public static class Foods {
                public static final FoodProperties VILLAGER_FLESH = new FoodProperties.Builder().nutrition(3)
                                .saturationMod(0.3F).meat()
                                .effect(() -> new MobEffectInstance(MobEffects.BAD_OMEN, -1, 1), 0.05f)
                                .build();
                public static final FoodProperties COOKED_VILLAGER_FLESH = new FoodProperties.Builder().nutrition(8)
                                .saturationMod(0.8F).meat()
                                .effect(() -> new MobEffectInstance(MobEffects.BAD_OMEN, -1, 1), 0.025f).build();
        }

        public static void register(IEventBus eventBus) {
                ITEMS.register(eventBus);
        }
}
