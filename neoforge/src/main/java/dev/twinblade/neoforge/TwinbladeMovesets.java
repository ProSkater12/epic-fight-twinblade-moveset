package dev.twinblade.neoforge;

import dev.twinblade.TwinbladeContent;
import dev.twinblade.moveset.TwinbladeMovesetDefinitions;
import yesman.epicfight.registry.deferred.MovesetRegister;
import yesman.epicfight.registry.deferred.holders.DeferredMoveset;

public final class TwinbladeMovesets {
    public static final MovesetRegister REGISTRY = MovesetRegister.create(TwinbladeMod.MOD_ID);

    public static final DeferredMoveset TWINBLADE = REGISTRY.registerMoveset(
            TwinbladeContent.TWINBLADE_MOVESET.id(),
            () -> TwinbladeMovesetDefinitions.create(TwinbladeSkills.TWINBLADE_DISCIPLINE)
    );

    private TwinbladeMovesets() {
    }
}
