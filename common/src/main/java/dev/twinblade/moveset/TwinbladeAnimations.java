package dev.twinblade.moveset;

import dev.twinblade.TwinbladeContent;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.AnimationManager.AnimationBuilder;
import yesman.epicfight.api.animation.AnimationManager.AnimationRegistryEvent;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackAnimationProperty;
import yesman.epicfight.api.animation.property.AnimationProperty.StaticAnimationProperty;
import yesman.epicfight.api.animation.types.ComboAttackAnimation;
import yesman.epicfight.api.animation.types.MovementAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Armatures;

/**
 * Twinblade animation accessors. JSON clips live under
 * {@code assets/twinblade/animmodels/animations/} and are keyed by the
 * accessor path (for example {@code twinblade.idle.json}).
 */
public final class TwinbladeAnimations {
    /** Authored clip length for auto.001–004. */
    private static final float AUTO_001_004_CLIP_SECONDS = 0.6F;
    /** Target real-time duration for each of auto.001–004. */
    private static final float AUTO_001_004_TARGET_SECONDS = 0.4F;

    public static AnimationAccessor<StaticAnimation> IDLE;
    public static AnimationAccessor<MovementAnimation> WALK;
    public static AnimationAccessor<MovementAnimation> RUN;

    public static AnimationAccessor<ComboAttackAnimation> AUTO_001;
    public static AnimationAccessor<ComboAttackAnimation> AUTO_002;
    public static AnimationAccessor<ComboAttackAnimation> AUTO_003;
    public static AnimationAccessor<ComboAttackAnimation> AUTO_004;
    public static AnimationAccessor<ComboAttackAnimation> AUTO_005;

    private TwinbladeAnimations() {
    }

    public static void register(AnimationRegistryEvent event) {
        event.newBuilder(TwinbladeContent.MOD_ID, TwinbladeAnimations::build);
    }

    private static void build(AnimationBuilder builder) {
        IDLE = builder.nextAccessor("twinblade.idle", accessor ->
                new StaticAnimation(true, accessor, Armatures.BIPED));
        WALK = builder.nextAccessor("twinblade.walk", accessor ->
                new MovementAnimation(true, accessor, Armatures.BIPED));
        RUN = builder.nextAccessor("twinblade.run", accessor ->
                new MovementAnimation(true, accessor, Armatures.BIPED));

        // Clip times stay in authored seconds; PLAY_SPEED_MODIFIER compresses
        // 001-004 to 0.4s real time. Hit windows scale with that playback.
        AUTO_001 = builder.nextAccessor("twinblade.auto.001", accessor ->
                fastAuto(accessor, 0.15F, 0.30F, 0.50F));
        AUTO_002 = builder.nextAccessor("twinblade.auto.002", accessor ->
                fastAuto(accessor, 0.15F, 0.30F, 0.50F));
        AUTO_003 = builder.nextAccessor("twinblade.auto.003", accessor ->
                fastAuto(accessor, 0.15F, 0.30F, 0.50F));
        AUTO_004 = builder.nextAccessor("twinblade.auto.004", accessor ->
                fastAuto(accessor, 0.15F, 0.30F, 0.50F));
        AUTO_005 = builder.nextAccessor("twinblade.auto.005", accessor ->
                combo(accessor, 0.20F, 0.40F, 0.65F));
    }

    private static ComboAttackAnimation fastAuto(
            AnimationAccessor<? extends ComboAttackAnimation> accessor,
            float antic,
            float contact,
            float recovery
    ) {
        float speed = AUTO_001_004_CLIP_SECONDS / AUTO_001_004_TARGET_SECONDS;
        return combo(accessor, antic, contact, recovery)
                .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER,
                        (self, entitypatch, baseSpeed, prevElapsedTime, elapsedTime) -> speed);
    }

    private static ComboAttackAnimation combo(
            AnimationAccessor<? extends ComboAttackAnimation> accessor,
            float antic,
            float contact,
            float recovery
    ) {
        return new ComboAttackAnimation(
                0.05F,
                antic,
                contact,
                recovery,
                null,
                Armatures.BIPED.get().toolR,
                accessor,
                Armatures.BIPED
        ).addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F);
    }
}
