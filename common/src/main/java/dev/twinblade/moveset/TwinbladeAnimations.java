package dev.twinblade.moveset;

import dev.twinblade.TwinbladeContent;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.AnimationManager.AnimationBuilder;
import yesman.epicfight.api.animation.AnimationManager.AnimationRegistryEvent;
import yesman.epicfight.api.animation.types.MovementAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Armatures;

/**
 * Twinblade animation accessors. JSON clips live under
 * {@code assets/twinblade/animmodels/animations/} and are keyed by the
 * accessor path (for example {@code twinblade.idle.json}).
 */
public final class TwinbladeAnimations {
    public static AnimationAccessor<StaticAnimation> IDLE;
    public static AnimationAccessor<MovementAnimation> WALK;
    public static AnimationAccessor<MovementAnimation> RUN;

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
    }
}
