package com.lotkey.villagerflesh.tab;

import java.util.List;

import com.lotkey.villagerflesh.VillagerFlesh;
import com.lotkey.villagerflesh.item.ModItems;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = VillagerFlesh.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CreativeTabs {
        public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister
                        .create(Registries.CREATIVE_MODE_TAB, VillagerFlesh.MOD_ID);

        public static final RegistryObject<CreativeModeTab> VILLAGER_FLESH_TAB = CREATIVE_TABS.register(
                        "villager_flesh_tab",
                        () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.villager_flesh"))
                                        .icon(() -> new ItemStack(ModItems.COOKED_VILLAGER_FLESH.get()))
                                        .displayItems((displayParameters, output) -> {
                                                getSortedCreativeTabs().forEach(output::accept);
                                        }).build());

        public static void register(IEventBus eventBus) {
                CREATIVE_TABS.register(eventBus);
        }

        @SubscribeEvent
        public static void buildContents(BuildCreativeModeTabContentsEvent event) {
                if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
                        getSortedCreativeTabs().forEach(event::accept);
                }
        }

        private static List<Item> getSortedCreativeTabs() {
                return ModItems.ITEMS.getEntries().stream()
                                .map(RegistryObject::get)
                                .sorted((item1, item2) -> item1.getDescriptionId()
                                                .compareTo(item2.getDescriptionId()))
                                .toList();
        }
}
