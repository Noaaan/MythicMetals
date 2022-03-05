package nourl.mythicmetals.mixin;

import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.Map;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {

    @Inject(method = "apply*", at = @At("HEAD"))
    private void deserialize(Map<Identifier, JsonObject> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
        // Removes recipes from the recipe map if a mod is not present
        Iterator<Map.Entry<Identifier, JsonObject>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Identifier, JsonObject> entry = iterator.next();
            JsonObject json = entry.getValue();

            isModLoaded("techreborn", json, iterator);
            isModLoaded("modern_industrialization", json, iterator);
        }
    }

    private void isModLoaded(String string, JsonObject json, Iterator<Map.Entry<Identifier, JsonObject>> iterator) {
        if (!FabricLoader.getInstance().isModLoaded(string)) {
            String type = JsonHelper.getString(json, "type");
            if (type.contains(string)) {
                iterator.remove();
            }
        }
    }
}