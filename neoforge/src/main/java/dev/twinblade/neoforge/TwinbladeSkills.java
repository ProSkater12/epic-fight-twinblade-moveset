package dev.twinblade.neoforge;

import dev.twinblade.common.TwinbladeContent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import yesman.epicfight.registry.EpicFightRegistries;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.passive.PassiveSkill;

public final class TwinbladeSkills {
    public static final DeferredRegister<Skill> REGISTRY =
            DeferredRegister.create(EpicFightRegistries.Keys.SKILL, TwinbladeMod.MOD_ID);

    public static final DeferredHolder<Skill, TwinbladeDisciplineSkill> TWINBLADE_DISCIPLINE =
            REGISTRY.register(TwinbladeContent.TWINBLADE_DISCIPLINE.id(), key ->
                    PassiveSkill.createPassiveBuilder(TwinbladeDisciplineSkill::new).build(key)
            );

    private TwinbladeSkills() {
    }
}
