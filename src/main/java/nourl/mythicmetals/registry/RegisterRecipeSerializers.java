package nourl.mythicmetals.registry;

import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.tools.SpecialSmithingRecipe;
import nourl.mythicmetals.utils.RegistryHelper;

public class RegisterRecipeSerializers {

    public static void init() {
        Registry.register(Registry.RECIPE_SERIALIZER, RegistryHelper.id("special_smithing_recipe"), new SpecialSmithingRecipe.Serializer());
    }
}
