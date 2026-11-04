package dev.twinblade.common;

/**
 * Loader-independent balance values. Platform modules apply these values through
 * their respective Epic Fight APIs.
 */
public final class TwinbladeBalance {
    public static final double ATTACK_SPEED_BONUS = 0.20D;

    private TwinbladeBalance() {
    }

    public static double applyAttackSpeedBonus(double baseAttackSpeed) {
        return baseAttackSpeed * (1.0D + ATTACK_SPEED_BONUS);
    }
}
