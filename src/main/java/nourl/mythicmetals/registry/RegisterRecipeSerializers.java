package nourl.mythicmetals.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import nourl.mythicmetals.item.tools.DrillUpgradeSmithingRecipe;
import nourl.mythicmetals.item.tools.MidasSmithingRecipe;
import nourl.mythicmetals.item.tools.TippedRuniteArrowRecipe;
import nourl.mythicmetals.misc.RegistryHelper;

public class RegisterRecipeSerializers {

    public static void init() {
        Registry.register(Registries.RECIPE_SERIALIZER, RegistryHelper.id("fold_midas_smithing_recipe"), new MidasSmithingRecipe.Serializer());
        Registry.register(Registries.RECIPE_SERIALIZER, RegistryHelper.id("mythril_drill_smithing_recipe"), new DrillUpgradeSmithingRecipe.Serializer());
        Registry.register(Registries.RECIPE_SERIALIZER, RegistryHelper.id("runite_tipped_arrow_recipe"), TippedRuniteArrowRecipe.INSTANCE);
    }
}
