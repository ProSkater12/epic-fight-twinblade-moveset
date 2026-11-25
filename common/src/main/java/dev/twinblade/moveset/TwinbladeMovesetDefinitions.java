package dev.twinblade.moveset;

import net.minecraft.core.Holder;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.ex_cap.data.Moveset;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.Skill;

/**
 * Shared Twinblade moveset definition. Loader adapters only register the result.
 *
 * Locomotion and auto combo use custom Twinblade clips. Dash, air slash, mount,
 * and remaining living motions still fall back to Epic Fight spear animations
 * until those Twinblade assets are wired.
 *
 * Epic Fight's basic-attack skill treats {@code addComboAttacks} as:
 * {@code [auto...], dash, airSlash} — the last two slots are reserved.
 *
 * Base moveset: auto.001–005. Extended moveset: auto.001–011 (unlocked when the
 * player has learned {@code twinblade:extended_combo}).
 */
public final class TwinbladeMovesetDefinitions {
    private TwinbladeMovesetDefinitions() {
    }

    public static Moveset.Builder createBase(Holder<Skill> passiveSkill) {
        return livingAndMount(passiveSkill)
                .addComboAttacks(
                        TwinbladeAnimations.AUTO_001,
                        TwinbladeAnimations.AUTO_002,
                        TwinbladeAnimations.AUTO_003,
                        TwinbladeAnimations.AUTO_004,
                        TwinbladeAnimations.AUTO_005,
                        Animations.SPEAR_DASH,
                        Animations.SPEAR_TWOHAND_AIR_SLASH
                );
    }

    public static Moveset.Builder createExtended(Holder<Skill> passiveSkill) {
        return livingAndMount(passiveSkill)
                .addComboAttacks(
                        TwinbladeAnimations.AUTO_001,
                        TwinbladeAnimations.AUTO_002,
                        TwinbladeAnimations.AUTO_003,
                        TwinbladeAnimations.AUTO_004,
                        TwinbladeAnimations.AUTO_005,
                        TwinbladeAnimations.AUTO_006,
                        TwinbladeAnimations.AUTO_007,
                        TwinbladeAnimations.AUTO_008,
                        TwinbladeAnimations.AUTO_009,
                        TwinbladeAnimations.AUTO_010,
                        TwinbladeAnimations.AUTO_011,
                        Animations.SPEAR_DASH,
                        Animations.SPEAR_TWOHAND_AIR_SLASH
                );
    }

    /** @deprecated use {@link #createBase} */
    @Deprecated
    public static Moveset.Builder create(Holder<Skill> passiveSkill) {
        return createBase(passiveSkill);
    }

    private static Moveset.Builder livingAndMount(Holder<Skill> passiveSkill) {
        return Moveset.builder()
                .addLivingMotionModifier(LivingMotions.IDLE, TwinbladeAnimations.IDLE)
                .addLivingMotionsRecursive(
                        Animations.BIPED_HOLD_SPEAR,
                        LivingMotions.SWIM, LivingMotions.JUMP,
                        LivingMotions.KNEEL, LivingMotions.SNEAK
                )
                .addLivingMotionsRecursive(
                        TwinbladeAnimations.WALK,
                        LivingMotions.WALK, LivingMotions.CHASE
                )
                .addLivingMotionModifier(LivingMotions.RUN, TwinbladeAnimations.RUN)
                .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SPEAR_GUARD)
                .addMountAttacks(Animations.SPEAR_MOUNT_ATTACK)
                .setPassiveSkill(passiveSkill);
    }
}
