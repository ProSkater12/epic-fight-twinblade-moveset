package dev.twinblade.moveset;

import dev.twinblade.TwinbladeContent;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.AnimationManager.AnimationBuilder;
import yesman.epicfight.api.animation.AnimationManager.AnimationRegistryEvent;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackAnimationProperty;
import yesman.epicfight.api.animation.property.AnimationProperty.StaticAnimationProperty;
import yesman.epicfight.api.animation.types.AttackAnimation.Phase;
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

    /**
     * Last keyframe time in {@code twinblade.auto.011.json} (~94 frames @ 60fps).
     * ComboAttacks becomes doable again after {@code Phase.recovery}; keep that
     * near the end so spam cannot cut the finisher short.
     */
    private static final float AUTO_011_END = 1.5667F;

    public static AnimationAccessor<StaticAnimation> IDLE;
    public static AnimationAccessor<MovementAnimation> WALK;
    public static AnimationAccessor<MovementAnimation> RUN;

    public static AnimationAccessor<ComboAttackAnimation> AUTO_001;
    public static AnimationAccessor<ComboAttackAnimation> AUTO_002;
    public static AnimationAccessor<ComboAttackAnimation> AUTO_003;
    public static AnimationAccessor<ComboAttackAnimation> AUTO_004;
    public static AnimationAccessor<ComboAttackAnimation> AUTO_005;
    public static AnimationAccessor<ComboAttackAnimation> AUTO_006;
    public static AnimationAccessor<ComboAttackAnimation> AUTO_007;
    public static AnimationAccessor<ComboAttackAnimation> AUTO_008;
    public static AnimationAccessor<ComboAttackAnimation> AUTO_009;
    public static AnimationAccessor<ComboAttackAnimation> AUTO_010;
    public static AnimationAccessor<ComboAttackAnimation> AUTO_011;

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
        AUTO_006 = builder.nextAccessor("twinblade.auto.006", accessor ->
                combo(accessor, 0.20F, 0.40F, 0.65F));
        AUTO_007 = builder.nextAccessor("twinblade.auto.007", accessor ->
                combo(accessor, 0.20F, 0.40F, 0.65F));
        AUTO_008 = builder.nextAccessor("twinblade.auto.008", accessor ->
                combo(accessor, 0.20F, 0.40F, 0.65F));
        AUTO_009 = builder.nextAccessor("twinblade.auto.009", accessor ->
                combo(accessor, 0.20F, 0.40F, 0.65F));
        AUTO_010 = builder.nextAccessor("twinblade.auto.010", accessor ->
                combo(accessor, 0.20F, 0.40F, 0.65F));
        AUTO_011 = builder.nextAccessor("twinblade.auto.011", TwinbladeAnimations::finisher011);
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

    /**
     * Multi-phase finisher: one hit window per spin “barrel”, then a landing hit.
     * Last phase {@code recovery} is near {@link #AUTO_011_END} so
     * {@code COMBO_ATTACKS_DOABLE} stays false until the clip nearly finishes.
     * Barrel timings are estimates — tune against the authored clip.
     */
    private static ComboAttackAnimation finisher011(
            AnimationAccessor<? extends ComboAttackAnimation> accessor
    ) {
        var toolR = Armatures.BIPED.get().toolR;
        return new ComboAttackAnimation(
                0.05F,
                accessor,
                Armatures.BIPED,
                // start, antic, contact, recovery, end
                new Phase(0.00F, 0.12F, 0.22F, 0.38F, 0.40F, toolR, null),
                new Phase(0.40F, 0.50F, 0.60F, 0.78F, 0.80F, toolR, null),
                new Phase(0.80F, 0.90F, 1.00F, 1.13F, 1.15F, toolR, null),
                new Phase(1.15F, 1.28F, 1.40F, 1.55F, AUTO_011_END, toolR, null)
        ).addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F);
    }
}
