package nourl.mythicmetals;

import nourl.mythicmetals.config.ConfigHandler;
import nourl.mythicmetals.config.MythicConfig;
import nourl.mythicmetals.registry.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class MythicMetalsMain implements ModInitializer {
	public static Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "mythicmetals";
	public static final ItemGroup MYTHICMETALS = FabricItemGroupBuilder.create(
					new Identifier("mythicmetals", "main")).icon(() -> new ItemStack(RegisterIngots.Adamantite_Ingot)).build();
	public static final ItemGroup MYTHICMETALS_TOOLS = FabricItemGroupBuilder.create(
			new Identifier("mythicmetals", "tools")).icon(() -> new ItemStack(RegisterTools.ADAMANTITE_PICKAXE)).build();
	public static final ItemGroup MYTHICMETALS_ARMOR = FabricItemGroupBuilder.create(
			new Identifier("mythicmetals", "armor")).icon(() -> new ItemStack(RegisterArmor.ADAMANTITE_HELMET)).build();

	public static final ConfigHandler MYTHICHANDLER = new ConfigHandler(MythicConfig.class, MOD_ID);

				
		@Override
		public void onInitialize() {
			RegisterIngots.register();
			RegisterOres.register();
		  	RegisterTools.register();
		  	RegisterArmor.register();
		  	RegisterBlocks.register();
		  	RegisterItems.register();

		  	LOGGER.info("Mythic Metals is now initialized");

		 }

		public static void log(Level level, String message){
	        LOGGER.log(level, "[MYTHICMETALS] " + message);

	    }
	}
