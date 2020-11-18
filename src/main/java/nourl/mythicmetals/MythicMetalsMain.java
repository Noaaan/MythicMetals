package nourl.mythicmetals;

import nourl.mythicmetals.config.*;
import nourl.mythicmetals.config.confighandler.*;
import nourl.mythicmetals.registry.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.io.File;

public class MythicMetalsMain implements ModInitializer {
	public static File configDir;
	public static Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "mythicmetals";

	public static final ItemGroup MYTHICMETALS = FabricItemGroupBuilder.create(
					new Identifier("mythicmetals", "main")).icon(() -> new ItemStack(RegisterIngots.Adamantite_Ingot)).build();
	public static final ItemGroup MYTHICMETALS_TOOLS = FabricItemGroupBuilder.create(
			new Identifier("mythicmetals", "tools")).icon(() -> new ItemStack(RegisterTools.ADAMANTITE_PICKAXE)).build();
	public static final ItemGroup MYTHICMETALS_ARMOR = FabricItemGroupBuilder.create(
			new Identifier("mythicmetals", "armor")).icon(() -> new ItemStack(RegisterArmor.ADAMANTITE_HELMET)).build();

				
		@Override
		public void onInitialize() {
			new ConfigHandler(MythicConfig.class, MOD_ID);

			RegisterIngots.register();
		  	RegisterTools.register();
			RegisterArmor.register();
			RegisterBlocks.register();
			RegisterItems.register();
			RegisterOres.register();

		  	LOGGER.info("Mythic Metals is now initialized");

		 }
	}
