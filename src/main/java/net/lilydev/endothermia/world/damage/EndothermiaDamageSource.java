package net.lilydev.endothermia.world.damage;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public abstract class EndothermiaDamageSource extends DamageSource {

    protected EndothermiaDamageSource(String name) {
        super(name);
    }
    public abstract void apply(LivingEntity entity);
}
