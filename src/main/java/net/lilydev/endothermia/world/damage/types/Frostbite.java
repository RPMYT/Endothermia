package net.lilydev.endothermia.world.damage.types;

import net.lilydev.endothermia.world.damage.EndothermiaDamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class Frostbite extends EndothermiaDamageSource {

    public Frostbite() {
        super("frostbite");
        setBypassesArmor();
    }

    @Override
    public void apply(LivingEntity entity) {
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 2, true, false));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 300, 1, true, false));
        entity.setFrozenTicks(125);
    }
    
}
