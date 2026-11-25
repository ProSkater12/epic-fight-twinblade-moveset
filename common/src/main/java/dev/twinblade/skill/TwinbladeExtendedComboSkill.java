package dev.twinblade.skill;

import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.passive.PassiveSkill;

/**
 * Learnable passive. Learning it (via Skill Book) unlocks twinblade auto hits
 * {@code auto.006}–{@code auto.011}. The skill itself has no runtime tick effect;
 * weapon style resolution checks {@code PlayerSkills.hasLearned}.
 */
public final class TwinbladeExtendedComboSkill extends PassiveSkill {
    public TwinbladeExtendedComboSkill(SkillBuilder<?> builder) {
        super(builder);
    }
}
