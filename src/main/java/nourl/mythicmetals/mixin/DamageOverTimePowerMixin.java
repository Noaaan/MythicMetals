package nourl.mythicmetals.mixin;

//@Pseudo
//@Mixin(value = DamageOverTimePower.class, remap = false)
public class DamageOverTimePowerMixin /*extends Power*/ {
//    @Shadow @Final private Enchantment protectingEnchantment;
//
//    public DamageOverTimePowerMixin(PowerType<?> type, LivingEntity entity) {
//        super(type, entity);
//    }
//
//    @Inject(method = "getProtection", at = @At("TAIL"), cancellable = true)
//    private void fakeWaterProtection(CallbackInfoReturnable<Integer> cir) {
//        var amount = cir.getReturnValue();
//        int change = 0;
//        for (var itemStack : protectingEnchantment.getEquipment(entity).values()) {
//            if (Abilities.WATER_PROTECTION.getItems().contains(itemStack.getItem())) {
//                change += Abilities.WATER_PROTECTION.getLevel() + 1;
//            }
//            if (Abilities.BETTER_WATER_PROTECTION.getItems().contains(itemStack.getItem())) {
//                change += Abilities.BETTER_WATER_PROTECTION.getLevel() + 1;
//            }
//        }
//        if (change != 0)
//            cir.setReturnValue(amount + change);
//    }
}
