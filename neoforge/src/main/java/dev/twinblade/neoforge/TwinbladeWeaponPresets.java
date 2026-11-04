package dev.twinblade.neoforge;

import dev.twinblade.common.TwinbladeContent;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.registry.deferred.ItemPresetRegister;
import yesman.epicfight.registry.deferred.holders.DeferredWeapon;
import yesman.epicfight.registry.entries.EpicFightProviderConditionals;
import yesman.epicfight.registry.entries.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

public final class TwinbladeWeaponPresets {
    public static final ItemPresetRegister REGISTRY = ItemPresetRegister.create(TwinbladeMod.MOD_ID);

    public static final DeferredWeapon TWINBLADE = REGISTRY.registerWeapon(TwinbladeContent.TWINBLADE.id(), () ->
            WeaponCapability.builder()
                    .category(TwinbladeWeaponCategories.TWINBLADE)
                    .collider(ColliderPreset.SPEAR)
                    .swingSound(EpicFightSounds.WHOOSH_ROD)
                    .hitSound(EpicFightSounds.BLADE_HIT)
                    .canBePlacedOffhand(false)
                    .reach(TwinbladeContent.TWINBLADE.reach())
                    .addMoveset(CapabilityItem.Styles.TWO_HAND, TwinbladeMovesets.TWINBLADE)
                    .addConditionals(EpicFightProviderConditionals.DEFAULT_2H_WIELD_STYLE)
    );

    private TwinbladeWeaponPresets() {
    }
}
