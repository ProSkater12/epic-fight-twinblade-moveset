package dev.twinblade.neoforge;

import dev.twinblade.common.TwinbladeContent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class TwinbladeItems {
    public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(TwinbladeMod.MOD_ID);

    public static final DeferredItem<Item> TWINBLADE = REGISTRY.registerItem(
            TwinbladeContent.TWINBLADE.id(),
            properties -> new SwordItem(
                    Tiers.DIAMOND,
                    properties.attributes(SwordItem.createAttributes(
                            Tiers.DIAMOND,
                            TwinbladeContent.TWINBLADE.vanillaAttackDamage(),
                            TwinbladeContent.TWINBLADE.vanillaAttackSpeed()
                    ))
            )
    );

    private TwinbladeItems() {
    }
}
