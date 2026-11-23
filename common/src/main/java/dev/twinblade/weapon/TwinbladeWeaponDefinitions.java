package dev.twinblade.weapon;

import dev.twinblade.TwinbladeContent;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.registry.deferred.holders.DeferredMoveset;
import yesman.epicfight.registry.entries.EpicFightProviderConditionals;
import yesman.epicfight.registry.entries.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

/**
 * Shared Twinblade weapon capability definition. Loader adapters only register it.
 */
public final class TwinbladeWeaponDefinitions {
    private TwinbladeWeaponDefinitions() {
    }

    public static WeaponCapability.Builder create(WeaponCategory category, DeferredMoveset moveset) {
        return WeaponCapability.builder()
                .category(category)
                .collider(ColliderPreset.SPEAR)
                .swingSound(EpicFightSounds.WHOOSH_ROD)
                .hitSound(EpicFightSounds.BLADE_HIT)
                .canBePlacedOffhand(false)
                .reach(TwinbladeContent.TWINBLADE.reach())
                .addMoveset(CapabilityItem.Styles.TWO_HAND, moveset)
                .addConditionals(EpicFightProviderConditionals.DEFAULT_2H_WIELD_STYLE);
    }
}
