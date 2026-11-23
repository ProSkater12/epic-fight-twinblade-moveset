package dev.twinblade.neoforge;

import dev.twinblade.TwinbladeContent;
import dev.twinblade.weapon.TwinbladeWeaponCategories;
import dev.twinblade.weapon.TwinbladeWeaponDefinitions;
import yesman.epicfight.registry.deferred.ItemPresetRegister;
import yesman.epicfight.registry.deferred.holders.DeferredWeapon;

public final class TwinbladeWeaponPresets {
    public static final ItemPresetRegister REGISTRY = ItemPresetRegister.create(TwinbladeMod.MOD_ID);

    public static final DeferredWeapon TWINBLADE = REGISTRY.registerWeapon(
            TwinbladeContent.TWINBLADE.id(),
            () -> TwinbladeWeaponDefinitions.create(TwinbladeWeaponCategories.TWINBLADE, TwinbladeMovesets.TWINBLADE)
    );

    private TwinbladeWeaponPresets() {
    }
}
