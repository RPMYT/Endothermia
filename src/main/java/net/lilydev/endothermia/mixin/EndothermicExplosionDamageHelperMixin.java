package net.lilydev.endothermia.mixin;

import net.lilydev.endothermia.world.damage.EndothermiaDamageSource;
import net.lilydev.endothermia.world.damage.EndothermiaDamageSources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;

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
        if (source instanceof EndothermiaDamageSource) {
            EndothermiaDamageSources.getSourceByName(source.getName()).apply((LivingEntity) (Object) this);
        }
    }
}