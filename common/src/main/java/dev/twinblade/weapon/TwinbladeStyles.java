package dev.twinblade.weapon;

import yesman.epicfight.world.capabilities.item.Style;

/**
 * Twinblade-only weapon styles. {@link #EXTENDED_COMBO} selects the longer
 * auto-attack moveset after the player has learned the extended-combo skill.
 */
public enum TwinbladeStyles implements Style {
    EXTENDED_COMBO(false);

    final boolean canUseOffhand;
    final int id;

    TwinbladeStyles(boolean canUseOffhand) {
        this.id = Style.ENUM_MANAGER.assign(this);
        this.canUseOffhand = canUseOffhand;
    }

    @Override
    public int universalOrdinal() {
        return this.id;
    }

    @Override
    public boolean canUseOffhand() {
        return this.canUseOffhand;
    }
}
