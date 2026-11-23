package dev.twinblade.neoforge;

import dev.twinblade.TwinbladeContent;
import dev.twinblade.moveset.TwinbladeAnimations;
import dev.twinblade.weapon.TwinbladeWeaponCategories;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import yesman.epicfight.registry.entries.EpicFightCreativeTabs;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

@Mod(TwinbladeMod.MOD_ID)
public final class TwinbladeMod {
    public static final String MOD_ID = TwinbladeContent.MOD_ID;

    public TwinbladeMod(IEventBus modBus) {
        WeaponCategory.ENUM_MANAGER.registerEnumCls(MOD_ID, TwinbladeWeaponCategories.class);
        modBus.addListener(TwinbladeAnimations::register);
        modBus.addListener(TwinbladeMod::addCreativeTabItems);

        TwinbladeItems.REGISTRY.register(modBus);
        TwinbladeSkills.REGISTRY.register(modBus);
        TwinbladeMovesets.REGISTRY.register(modBus);
        TwinbladeWeaponPresets.REGISTRY.register(modBus);
    }

    private static void addCreativeTabItems(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == EpicFightCreativeTabs.ITEMS.get()) {
            event.accept(TwinbladeItems.TWINBLADE);
            event.accept(TwinbladeItems.WOODEN_TWINBLADE);
        }
    }
}
