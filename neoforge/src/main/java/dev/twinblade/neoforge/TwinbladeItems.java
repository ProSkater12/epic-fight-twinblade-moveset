package dev.twinblade.neoforge;

import dev.twinblade.TwinbladeContent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import yesman.epicfight.registry.entries.EpicFightDataComponentTypes;
import yesman.epicfight.world.item.SkillBookItem;

public final class TwinbladeItems {
    public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(TwinbladeMod.MOD_ID);

    public static final DeferredItem<Item> WOODEN_TWINBLADES =
            register(TwinbladeContent.WOODEN_TWINBLADES, Tiers.WOOD);
    public static final DeferredItem<Item> STONE_TWINBLADES =
            register(TwinbladeContent.STONE_TWINBLADES, Tiers.STONE);
    public static final DeferredItem<Item> GOLD_TWINBLADES =
            register(TwinbladeContent.GOLD_TWINBLADES, Tiers.GOLD);
    public static final DeferredItem<Item> IRON_TWINBLADES =
            register(TwinbladeContent.IRON_TWINBLADES, Tiers.IRON);
    public static final DeferredItem<Item> DIAMOND_TWINBLADES =
            register(TwinbladeContent.DIAMOND_TWINBLADES, Tiers.DIAMOND);
    public static final DeferredItem<Item> NETHERITE_TWINBLADES =
            register(TwinbladeContent.NETHERITE_TWINBLADES, Tiers.NETHERITE);

    /**
     * EF Skill Book pre-loaded with {@link TwinbladeSkills#EXTENDED_COMBO}.
     * Right-click → learn like any Epic Fight skill book.
     */
    public static final DeferredItem<SkillBookItem> EXTENDED_COMBO_SKILLBOOK =
            REGISTRY.registerItem(
                    TwinbladeContent.EXTENDED_COMBO_SKILLBOOK_ID,
                    properties -> new SkillBookItem(
                            properties
                                    .rarity(Rarity.RARE)
                                    .stacksTo(1)
                                    .component(
                                            EpicFightDataComponentTypes.SKILL.get(),
                                            TwinbladeSkills.EXTENDED_COMBO
                                    )
                    )
            );

    public static final DeferredItem<?>[] ALL = {
            WOODEN_TWINBLADES,
            STONE_TWINBLADES,
            GOLD_TWINBLADES,
            IRON_TWINBLADES,
            DIAMOND_TWINBLADES,
            NETHERITE_TWINBLADES,
            EXTENDED_COMBO_SKILLBOOK
    };

    private TwinbladeItems() {
    }

    private static DeferredItem<Item> register(TwinbladeContent.Weapon weapon, Tier tier) {
        return REGISTRY.registerItem(
                weapon.id(),
                properties -> new SwordItem(
                        tier,
                        properties.attributes(SwordItem.createAttributes(
                                tier,
                                weapon.vanillaAttackDamage(),
                                weapon.vanillaAttackSpeed()
                        ))
                )
        );
    }
}
