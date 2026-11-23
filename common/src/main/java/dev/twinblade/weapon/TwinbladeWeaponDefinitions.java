package dev.twinblade.weapon;

import dev.twinblade.TwinbladeContent;
import yesman.epicfight.api.collider.MultiOBBCollider;
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
    /**
     * Spear preset was {@code MultiOBBCollider(3, 0.6, 0.6, 1.0, 0, 0, -1.0)}.
     * Z half-extent and center offset are doubled so the hit volume reaches twice as far.
     */
    public static final MultiOBBCollider TWINBLADE_COLLIDER =
            new MultiOBBCollider(3, 0.6D, 0.6D, 2.0D, 0.0D, 0.0D, -2.0D);

    private TwinbladeWeaponDefinitions() {
    }

    public static WeaponCapability.Builder create(WeaponCategory category, DeferredMoveset moveset) {
        return WeaponCapability.builder()
                .category(category)
                .collider(TWINBLADE_COLLIDER)
                .swingSound(EpicFightSounds.WHOOSH_ROD)
                .hitSound(EpicFightSounds.BLADE_HIT)
                .canBePlacedOffhand(false)
                .reach(TwinbladeContent.TWINBLADE_REACH)
                .addMoveset(CapabilityItem.Styles.TWO_HAND, moveset)
                .addConditionals(EpicFightProviderConditionals.DEFAULT_2H_WIELD_STYLE);
    }
}
