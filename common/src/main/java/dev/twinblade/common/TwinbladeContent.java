package dev.twinblade.common;

/**
 * Loader-neutral definition of all Twinblade gameplay content.
 *
 * Platform modules only translate this definition into their loader's item and
 * Epic Fight registry APIs.
 */
public final class TwinbladeContent {
    public static final String MOD_ID = "twinblade";
    public static final String WEAPON_CATEGORY_ID = "twinblade";

    public static final Weapon TWINBLADE = new Weapon(
            "twinblade",
            5,
            -2.6F,
            8.0F,
            1.5F,
            2,
            1.0F
    );

    public static final Skill TWINBLADE_DISCIPLINE = new Skill(
            "twinblade_discipline",
            TwinbladeBalance.ATTACK_SPEED_BONUS
    );

    public static final Moveset TWINBLADE_MOVESET = new Moveset(
            "twinblade",
            "spear",
            TWINBLADE_DISCIPLINE.id()
    );

    private TwinbladeContent() {
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
