package dev.twinblade.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Contract test shared by every loader implementation.
 * Fabric and Forge implementations must use the same value when they are added.
 */
class TwinbladeBalanceTest {
    @Test
    void attackSpeedSkillIncreasesBaseSpeedByTwentyPercent() {
        assertEquals(4.8D, TwinbladeBalance.applyAttackSpeedBonus(4.0D), 0.00001D);
    }

    @Test
    void contentDefinitionsReferenceTheSamePassiveSkillAndBalanceValue() {
        assertEquals(TwinbladeContent.TWINBLADE_DISCIPLINE.id(), TwinbladeContent.TWINBLADE_MOVESET.passiveSkillId());
        assertEquals(0.20D, TwinbladeContent.TWINBLADE_DISCIPLINE.attackSpeedBonus(), 0.00001D);
    }
}
