package dev.twinblade.neoforge;

import dev.twinblade.TwinbladeContent;
import dev.twinblade.moveset.TwinbladeMovesetDefinitions;
import yesman.epicfight.registry.deferred.MovesetRegister;
import yesman.epicfight.registry.deferred.holders.DeferredMoveset;

public final class TwinbladeMovesets {
    public static final MovesetRegister REGISTRY = MovesetRegister.create(TwinbladeMod.MOD_ID);

    public static final DeferredMoveset TWINBLADE = REGISTRY.registerMoveset(
            TwinbladeContent.TWINBLADE_MOVESET.id(),
            () -> TwinbladeMovesetDefinitions.createBase(TwinbladeSkills.TWINBLADE_DISCIPLINE)
    );

    public static final DeferredMoveset TWINBLADE_EXTENDED = REGISTRY.registerMoveset(
            TwinbladeContent.TWINBLADE_EXTENDED_MOVESET_ID,
            () -> TwinbladeMovesetDefinitions.createExtended(TwinbladeSkills.TWINBLADE_DISCIPLINE)
    );

    private TwinbladeMovesets() {
    }
}
