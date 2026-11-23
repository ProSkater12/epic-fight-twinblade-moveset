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

    /** Capability reach (was 1.0; doubled for longer twinblade hit distance). */
    public static final float TWINBLADE_REACH = 2.0F;

    public static final Weapon TWINBLADE = new Weapon(
            "twinblade",
            5,
            -2.6F,
            8.0F,
            1.5F,
            2,
            TWINBLADE_REACH
    );

    /** Test wooden twinblade for trying the moveset in-game. */
    public static final Weapon WOODEN_TWINBLADE = new Weapon(
            "wooden-twinblade",
            3,
            -2.6F,
            2.0F,
            1.0F,
            2,
            TWINBLADE_REACH
    );

    public static final Skill TWINBLADE_DISCIPLINE = new Skill(
            "twinblade_discipline",
            TwinbladeBalance.ATTACK_SPEED_BONUS
    );

    public static final Moveset TWINBLADE_MOVESET = new Moveset(
            "twinblade",
            "twinblade-auto+spear-fallback",
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
