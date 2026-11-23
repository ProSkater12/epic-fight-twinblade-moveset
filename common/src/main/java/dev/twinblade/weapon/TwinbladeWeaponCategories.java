package dev.twinblade.weapon;

import dev.twinblade.TwinbladeContent;
import net.minecraft.network.chat.Component;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

/**
 * Shared Epic Fight weapon category used by every loader adapter.
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
                TwinbladeContent.MOD_ID,
                TwinbladeContent.WEAPON_CATEGORY_ID
        ));
    }
}
