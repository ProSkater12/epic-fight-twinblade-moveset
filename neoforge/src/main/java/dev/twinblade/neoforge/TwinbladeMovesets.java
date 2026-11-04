package dev.twinblade.neoforge;

import dev.twinblade.common.TwinbladeContent;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.ex_cap.data.Moveset;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.registry.deferred.MovesetRegister;
import yesman.epicfight.registry.deferred.holders.DeferredMoveset;

/**
 * The first iteration uses Epic Fight's spear animations so it is playable
 * before the bespoke Twinblade animation assets are supplied.
 */
public final class TwinbladeMovesets {
    public static final MovesetRegister REGISTRY = MovesetRegister.create(TwinbladeMod.MOD_ID);

    public static final DeferredMoveset TWINBLADE = REGISTRY.registerMoveset(TwinbladeContent.TWINBLADE_MOVESET.id(), () ->
            Moveset.builder()
                    .addLivingMotionsRecursive(
                            Animations.BIPED_HOLD_SPEAR,
                            LivingMotions.IDLE, LivingMotions.SWIM, LivingMotions.JUMP,
                            LivingMotions.KNEEL, LivingMotions.SNEAK
                    )
                    .addLivingMotionsRecursive(
                            Animations.BIPED_WALK_SPEAR,
                            LivingMotions.WALK, LivingMotions.CHASE
                    )
                    .addLivingMotionModifier(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR)
                    .addLivingMotionModifier(LivingMotions.BLOCK, Animations.SPEAR_GUARD)
                    .addComboAttacks(
                            Animations.SPEAR_TWOHAND_AUTO1,
                            Animations.SPEAR_TWOHAND_AUTO2,
                            Animations.SPEAR_DASH,
                            Animations.SPEAR_TWOHAND_AIR_SLASH
                    )
                    .addMountAttacks(Animations.SPEAR_MOUNT_ATTACK)
                    .setPassiveSkill(TwinbladeSkills.TWINBLADE_DISCIPLINE)
    );

    private TwinbladeMovesets() {
    }
}
