package net.lilydev.endothermia.mixin;

import net.lilydev.endothermia.world.EndothermiaDamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class EndothermicExplosionDamageHelperMixin {
    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Inject(method = "damage", at = @At("HEAD"))
    private void freeze(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source instanceof EndothermiaDamageSource && source.name.equals("frostbite")) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 2));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 300, 1));

        }
    }
}