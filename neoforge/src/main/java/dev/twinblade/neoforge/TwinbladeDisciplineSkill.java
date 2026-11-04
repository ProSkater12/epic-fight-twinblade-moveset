package dev.twinblade.neoforge;

import dev.twinblade.common.TwinbladeContent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import yesman.epicfight.api.event.EntityEventListener;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;

/**
 * An equipable Epic Fight passive. The multiplier is applied while the skill is
 * equipped and reliably removed when it is unequipped.
 */
public final class TwinbladeDisciplineSkill extends PassiveSkill {
    private static final ResourceLocation ATTACK_SPEED_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath(TwinbladeMod.MOD_ID, "twinblade_discipline_attack_speed");

    public TwinbladeDisciplineSkill(SkillBuilder<?> builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container, EntityEventListener eventListener) {
        super.onInitiate(container, eventListener);
        AttributeInstance attackSpeed = container.getExecutor().getOriginal().getAttribute(Attributes.ATTACK_SPEED);
        if (attackSpeed != null && !attackSpeed.hasModifier(ATTACK_SPEED_MODIFIER_ID)) {
            attackSpeed.addTransientModifier(new AttributeModifier(
                    ATTACK_SPEED_MODIFIER_ID,
                    TwinbladeContent.TWINBLADE_DISCIPLINE.attackSpeedBonus(),
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            ));
        }
    }

    @Override
    public void onRemoved(SkillContainer container) {
        AttributeInstance attackSpeed = container.getExecutor().getOriginal().getAttribute(Attributes.ATTACK_SPEED);
        if (attackSpeed != null) {
            attackSpeed.removeModifier(ATTACK_SPEED_MODIFIER_ID);
        }
        super.onRemoved(container);
    }
}
