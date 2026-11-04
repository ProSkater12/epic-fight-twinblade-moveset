package dev.twinblade.neoforge;

import dev.twinblade.common.TwinbladeContent;
import net.minecraft.network.chat.Component;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

/**
 * NeoForge registration adapter for the loader-neutral Twinblade category.
 */
public enum TwinbladeWeaponCategories implements WeaponCategory {
    TWINBLADE;

    private final int id;

    TwinbladeWeaponCategories() {
        this.id = WeaponCategory.ENUM_MANAGER.assign(this);
    }

    @Override
    public int universalOrdinal() {
        return this.id;
    }

    @Override
    public Component getTranslatable() {
        return Component.translatable("weapon_category.%s.%s".formatted(
                TwinbladeMod.MOD_ID,
                TwinbladeContent.WEAPON_CATEGORY_ID
        ));
    }
}
