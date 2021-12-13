package net.lilydev.endothermia.world;

import net.minecraft.entity.damage.DamageSource;

public class EndothermiaDamageSource extends DamageSource {
    public static final EndothermiaDamageSource FROSTBITE = (EndothermiaDamageSource) new EndothermiaDamageSource("frostbite").setBypassesArmor();

    protected EndothermiaDamageSource(String name) {
        super(name);
    }
}
