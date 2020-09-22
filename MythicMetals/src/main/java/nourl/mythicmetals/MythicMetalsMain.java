package nourl.mythicmetals;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.registry.RegisterArmor;
import nourl.mythicmetals.registry.RegisterIngots;
import nourl.mythicmetals.registry.RegisterOres;
import nourl.mythicmetals.registry.RegisterTools;

public class MythicMetalsMain implements ModInitializer {
	public static Logger LOGGER = LogManager.getLogger();
	public static final ItemGroup MYTHICMETALS = FabricItemGroupBuilder.create(
					new Identifier("mythicmetals", "main")).icon(() -> new ItemStack(RegisterIngots.Adamantite_Ingot)).build();
	public static final ItemGroup MYTHICMETALS_TOOLS = FabricItemGroupBuilder.create(
			new Identifier("mythicmetals", "tools")).icon(() -> new ItemStack(RegisterTools.ADAMANTITE_PICKAXE)).build();
	public static final ItemGroup MYTHICMETALS_ARMOR = FabricItemGroupBuilder.create(
			new Identifier("mythicmetals", "armor")).icon(() -> new ItemStack(RegisterArmor.ADAMANTITE_HELMET)).build();

				
		@Override
		public void onInitialize() {
			RegisterIngots.register();
			RegisterOres.register();
		  	RegisterTools.register();
		  	RegisterArmor.register();

		 }

		public static void log(Level level, String message){
	        LOGGER.log(level, "[MYTHICMETALS] " + message);

	    }
	}
