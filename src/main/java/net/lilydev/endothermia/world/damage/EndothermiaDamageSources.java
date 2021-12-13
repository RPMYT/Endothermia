package net.lilydev.endothermia.world.damage;

import net.lilydev.endothermia.world.damage.types.Frostbite;

public enum EndothermiaDamageSources {
    FROSTBITE(new Frostbite())
    ;
    private EndothermiaDamageSource source;
    private EndothermiaDamageSources(EndothermiaDamageSource source) {
        this.source = source;
    }
    public EndothermiaDamageSource getSource() {
        return this.source;
    }
    public static EndothermiaDamageSource getSourceByName(String s) {
        for (EndothermiaDamageSources source : EndothermiaDamageSources.values()) {
            if (source.getSource().getName() == s) {
                return source.getSource();
            }
        }
        return null;
    }
}
