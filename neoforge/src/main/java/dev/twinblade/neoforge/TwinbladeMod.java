package dev.twinblade.neoforge;

import dev.twinblade.common.TwinbladeContent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(TwinbladeMod.MOD_ID)
public final class TwinbladeMod {
    public static final String MOD_ID = TwinbladeContent.MOD_ID;

    public TwinbladeMod(IEventBus modBus) {
        TwinbladeItems.REGISTRY.register(modBus);
        TwinbladeSkills.REGISTRY.register(modBus);
        TwinbladeMovesets.REGISTRY.register(modBus);
        TwinbladeWeaponPresets.REGISTRY.register(modBus);
    }
}
