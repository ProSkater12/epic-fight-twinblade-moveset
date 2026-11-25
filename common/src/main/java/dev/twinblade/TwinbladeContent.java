package dev.twinblade;

/**
 * Loader-neutral definition of all Twinblade gameplay content.
 *
 * Platform modules only translate this definition into their loader's item and
 * Epic Fight registry APIs.
 */
public final class TwinbladeContent {
    public static final String MOD_ID = "twinblade";
    public static final String WEAPON_CATEGORY_ID = "twinblade";
    public static final String WEAPON_PRESET_ID = "twinblade";

    /** Capability reach shared by every twinblade tier. */
    public static final float TWINBLADE_REACH = 2.0F;

    public static final float ATTACK_SPEED = -2.6F;

    public static final Weapon WOODEN_TWINBLADES = weapon("wooden-twinblades", 3, 2.0F, 1.0F);
    public static final Weapon STONE_TWINBLADES = weapon("stone-twinblades", 4, 3.0F, 1.2F);
    public static final Weapon GOLD_TWINBLADES = weapon("gold-twinblades", 3, 2.0F, 1.0F);
    public static final Weapon IRON_TWINBLADES = weapon("iron-twinblades", 5, 5.0F, 1.3F);
    public static final Weapon DIAMOND_TWINBLADES = weapon("diamond-twinblades", 6, 8.0F, 1.5F);
    public static final Weapon NETHERITE_TWINBLADES = weapon("netherite-twinblades", 7, 10.0F, 1.6F);

    public static final Weapon[] TWINBLADE_ITEMS = {
            WOODEN_TWINBLADES,
            STONE_TWINBLADES,
            GOLD_TWINBLADES,
            IRON_TWINBLADES,
            DIAMOND_TWINBLADES,
            NETHERITE_TWINBLADES
    };

    public static final Skill TWINBLADE_DISCIPLINE = new Skill(
            "twinblade_discipline",
            TwinbladeBalance.ATTACK_SPEED_BONUS
    );

    /** Learnable passive: unlocks auto.006–011 on twinblades once learned. */
    public static final String EXTENDED_COMBO_SKILL_ID = "extended_combo";

    public static final String EXTENDED_COMBO_SKILLBOOK_ID = "extended_combo_skillbook";

    public static final Moveset TWINBLADE_MOVESET = new Moveset(
            "twinblade",
            "twinblade-auto+spear-fallback",
            TWINBLADE_DISCIPLINE.id()
    );

    public static final String TWINBLADE_EXTENDED_MOVESET_ID = "twinblade_extended";

    private TwinbladeContent() {
    }

    private static Weapon weapon(String id, int damage, float armorNegation, float impact) {
        return new Weapon(id, damage, ATTACK_SPEED, armorNegation, impact, 2, TWINBLADE_REACH);
    }

    public record Weapon(
            String id,
            int vanillaAttackDamage,
            float vanillaAttackSpeed,
            float armorNegation,
            float impact,
            int maxStrikes,
            float reach
    ) {
    }

    public record Skill(String id, double attackSpeedBonus) {
    }

    /**
     * {@code fallbackAnimationFamily} identifies the temporary Epic Fight
     * animation family until original Twinblade animations are exported.
     */
    public record Moveset(String id, String fallbackAnimationFamily, String passiveSkillId) {
    }
}
