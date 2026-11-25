package dev.twinblade.weapon;

import dev.twinblade.TwinbladeContent;
import yesman.epicfight.api.collider.MultiOBBCollider;
import yesman.epicfight.registry.deferred.holders.DeferredMoveset;
import yesman.epicfight.registry.entries.EpicFightSounds;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.Style;
import yesman.epicfight.world.capabilities.item.WeaponCapability;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.function.Predicate;

/**
 * Shared Twinblade weapon capability definition. Loader adapters only register it.
 */
public final class TwinbladeWeaponDefinitions {
    /**
     * Centered on Tool_R so both blades hit. Spear-style offsets push the OBB only
     * forward (−Z); twinblade needs ±Z coverage around the grip.
     * Half-extents 0.6×0.6×2.0 with center (0,0,0) → hit volume from z=-2 to z=+2.
     */
    public static final MultiOBBCollider TWINBLADE_COLLIDER =
            new MultiOBBCollider(3, 0.6D, 0.6D, 2.0D, 0.0D, 0.0D, 0.0D);

    private TwinbladeWeaponDefinitions() {
    }

    /**
     * @param useExtendedCombo when true for the player, selects the extended
     *                         moveset (auto.001–011); otherwise base (001–005)
     */
    public static WeaponCapability.Builder create(
            WeaponCategory category,
            DeferredMoveset baseMoveset,
            DeferredMoveset extendedMoveset,
            Predicate<PlayerPatch<?>> useExtendedCombo
    ) {
        return WeaponCapability.builder()
                .category(category)
                .collider(TWINBLADE_COLLIDER)
                .swingSound(EpicFightSounds.WHOOSH_ROD)
                .hitSound(EpicFightSounds.BLADE_HIT)
                .canBePlacedOffhand(false)
                .reach(TwinbladeContent.TWINBLADE_REACH)
                .styleProvider(patch -> resolveStyle(patch, useExtendedCombo))
                .addMoveset(Styles.TWO_HAND, baseMoveset)
                .addMoveset(TwinbladeStyles.EXTENDED_COMBO, extendedMoveset);
    }

    private static Style resolveStyle(
            LivingEntityPatch<?> patch,
            Predicate<PlayerPatch<?>> useExtendedCombo
    ) {
        if (patch instanceof PlayerPatch<?> player && useExtendedCombo.test(player)) {
            return TwinbladeStyles.EXTENDED_COMBO;
        }
        return Styles.TWO_HAND;
    }
}
