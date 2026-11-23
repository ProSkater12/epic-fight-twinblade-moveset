package dev.twinblade.neoforge;

import dev.twinblade.TwinbladeContent;
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

    public static final DeferredItem<Item> WOODEN_TWINBLADE = REGISTRY.registerItem(
            TwinbladeContent.WOODEN_TWINBLADE.id(),
            properties -> new SwordItem(
                    Tiers.WOOD,
                    properties.attributes(SwordItem.createAttributes(
                            Tiers.WOOD,
                            TwinbladeContent.WOODEN_TWINBLADE.vanillaAttackDamage(),
                            TwinbladeContent.WOODEN_TWINBLADE.vanillaAttackSpeed()
                    ))
            )
    );

    private TwinbladeItems() {
    }
}
