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
public class MixinRecipeManager {

    @Inject(method = "apply", at = @At("HEAD"), cancellable = true)
    private void deserialize(Map<Identifier, JsonObject> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
        Iterator<Map.Entry<Identifier, JsonObject>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Identifier, JsonObject> entry = iterator.next();
            JsonObject json = entry.getValue();

            if (!FabricLoader.getInstance().isModLoaded("techreborn")) {
                String string = JsonHelper.getString(json, "type");
                if (string.contains("techreborn")) {
                    iterator.remove();
                }
            }
            if (!FabricLoader.getInstance().isModLoaded("mechanix")) {
                String string = JsonHelper.getString(json, "type");
                if (string.contains("mechanix")) {
                    iterator.remove();
                }
            }
            if (!FabricLoader.getInstance().isModLoaded("modern_industrialization")) {
                String string = JsonHelper.getString(json, "type");
                if (string.contains("modern_industrialization")) {
                    iterator.remove();
                }
            }
        }
    }
}