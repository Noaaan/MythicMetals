package nourl.mythicmetals.data;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.blocks.BlockSet;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.utils.RegistryHelper;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@SuppressWarnings("unused")
public class MythicTags {

    public static final TagKey<Item> CARMOT_ARMOR = TagKey.of(Registry.ITEM_KEY, RegistryHelper.id("carmot_armor"));
    public static final TagKey<Item> COPPER_ARMOR = TagKey.of(Registry.ITEM_KEY, RegistryHelper.id("copper_armor"));
    public static final TagKey<Item> PALLADIUM_ARMOR = TagKey.of(Registry.ITEM_KEY, RegistryHelper.id("palladium_armor"));
    public static final TagKey<Item> PROMETHEUM_ARMOR = TagKey.of(Registry.ITEM_KEY, RegistryHelper.id("prometheum_armor"));
    public static final TagKey<Item> PROMETHEUM_TOOLS = TagKey.of(Registry.ITEM_KEY, RegistryHelper.id("prometheum_tools"));
    public static final TagKey<Item> CARMOT_STAFF_BLOCKS = TagKey.of(Registry.ITEM_KEY, RegistryHelper.id("carmot_staff_blocks"));

    public static final TagKey<Block> MYTHIC_ORES = TagKey.of(Registry.BLOCK_KEY, RegistryHelper.id("ores"));
    public static final TagKey<Block> NUKE_CORES = TagKey.of(Registry.BLOCK_KEY, RegistryHelper.id("nuke_cores"));
    public static final TagKey<Block> CARMOT_NUKE_IGNORED = TagKey.of(Registry.BLOCK_KEY, RegistryHelper.id("carmot_nuke_ignored"));

    public static final TagKey<Biome> HUMID_BIOMES = TagKey.of(Registry.BIOME_KEY, new Identifier("c", "humid"));

    public static class Blocks implements AdvancedTagHelper<Block> {

        public final Map<String, MetalTagHelper<Block>> ADVANCED_METAL_TAGS = new HashMap<>();

        public static final Blocks INSTANCE = new Blocks();

        public static final MetalTagHelper<Block> ADAMANTITE = INSTANCE.createMetalTags("adamantite", MythicBlocks.ADAMANTITE);
        public static final MetalTagHelper<Block> AQUARIUM = INSTANCE.createMetalTags("aquarium", MythicBlocks.AQUARIUM);
        public static final MetalTagHelper<Block> BANGLUM = INSTANCE.createMetalTags("banglum", MythicBlocks.BANGLUM);

        public static final MetalTagHelper<Block> BRONZE = INSTANCE.createMetalTags("bronze", MythicBlocks.BRONZE);

        public static final MetalTagHelper<Block> CARMOT = INSTANCE.createMetalTags("carmot", MythicBlocks.CARMOT);

        public static final MetalTagHelper<Block> CELESTIUM = INSTANCE.createMetalTags("celestium", MythicBlocks.CELESTIUM);
        public static final MetalTagHelper<Block> DURASTEEL = INSTANCE.createMetalTags("durasteel", MythicBlocks.DURASTEEL);
        public static final MetalTagHelper<Block> HALLOWED = INSTANCE.createMetalTags("hallowed", MythicBlocks.HALLOWED);

        public static final MetalTagHelper<Block> KYBER = INSTANCE.createMetalTags("kyber", MythicBlocks.KYBER);
        public static final MetalTagHelper<Block> MANGANESE = INSTANCE.createMetalTags("manganese", MythicBlocks.MANGANESE);

        public static final MetalTagHelper<Block> METALLURGIUM = INSTANCE.createMetalTags("metallurgium", MythicBlocks.METALLURGIUM);

        public static final MetalTagHelper<Block> MORKITE = INSTANCE.createMetalTags("morkite", MythicBlocks.MORKITE);

        public static final MetalTagHelper<Block> MIDAS_GOLD = INSTANCE.createMetalTags("midas_gold", MythicBlocks.MIDAS_GOLD);
        public static final MetalTagHelper<Block> MYTHRIL = INSTANCE.createMetalTags("mythril", MythicBlocks.MYTHRIL);

        public static final MetalTagHelper<Block> ORICHALCUM = INSTANCE.createMetalTags("orichalcum", MythicBlocks.ORICHALCUM);
        public static final MetalTagHelper<Block> OSMIUM = INSTANCE.createMetalTags("osmium", MythicBlocks.OSMIUM);
        public static final MetalTagHelper<Block> PALLADIUM = INSTANCE.createMetalTags("palladium", MythicBlocks.PALLADIUM);
        public static final MetalTagHelper<Block> PLATINUM = INSTANCE.createMetalTags("platinum", MythicBlocks.PLATINUM);
        public static final MetalTagHelper<Block> PROMETHEUM = INSTANCE.createMetalTags("prometheum", MythicBlocks.PROMETHEUM);
        public static final MetalTagHelper<Block> QUADRILLUM = INSTANCE.createMetalTags("quadrillum", MythicBlocks.QUADRILLUM);
        public static final MetalTagHelper<Block> RUNITE = INSTANCE.createMetalTags("runite", MythicBlocks.RUNITE);
        public static final MetalTagHelper<Block> SILVER = INSTANCE.createMetalTags("silver", MythicBlocks.SILVER);

        public static final MetalTagHelper<Block> STAR_PLATINUM = INSTANCE.createMetalTags("star_platinum", MythicBlocks.STAR_PLATINUM);
        public static final MetalTagHelper<Block> STARRITE = INSTANCE.createMetalTags("starrite", MythicBlocks.STARRITE);

        public static final MetalTagHelper<Block> STEEL = INSTANCE.createMetalTags("steel", MythicBlocks.STEEL);

        public static final MetalTagHelper<Block> STORMYX = INSTANCE.createMetalTags("stormyx", MythicBlocks.STORMYX);
        public static final MetalTagHelper<Block> TIN = INSTANCE.createMetalTags("tin", MythicBlocks.TIN);

        public static final MetalTagHelper<Block> UNOBTAINIUM = INSTANCE.createMetalTags("unobtainium", MythicBlocks.UNOBTAINIUM);

        @Override
        public MetalTagHelper<Block> createMetalTags(String material, BlockSet set, boolean createAsCommon) {
            MetalTagHelper<Block> metalTags = AdvancedTagHelper.super.createMetalTags(material, set, createAsCommon);

            INSTANCE.ADVANCED_METAL_TAGS.put(material, metalTags);

            return metalTags;
        }

        @Override
        public String getModId() {
            return MythicMetals.MOD_ID;
        }

        @Override
        public Registry<Block> getRegistry() {
            return Registry.BLOCK;
        }
    }

    public static class Items implements AdvancedTagHelper<Item> {

        public final Map<String, MetalTagHelper<Item>> ADVANCED_METAL_TAGS = new HashMap<>();

        public static final Items INSTANCE = new Items();

        public static final MetalTagHelper<Item> ADAMANTITE = INSTANCE.createMetalTags("adamantite", MythicBlocks.ADAMANTITE);
        public static final MetalTagHelper<Item> AQUARIUM = INSTANCE.createMetalTags("aquarium", MythicBlocks.AQUARIUM);
        public static final MetalTagHelper<Item> BANGLUM = INSTANCE.createMetalTags("banglum", MythicBlocks.BANGLUM);

        public static final MetalTagHelper<Item> BRONZE = INSTANCE.createMetalTags("bronze", MythicBlocks.BRONZE);

        public static final MetalTagHelper<Item> CARMOT = INSTANCE.createMetalTags("carmot", MythicBlocks.CARMOT);

        public static final MetalTagHelper<Item> CELESTIUM = INSTANCE.createMetalTags("celestium", MythicBlocks.CELESTIUM);
        public static final MetalTagHelper<Item> DURASTEEL = INSTANCE.createMetalTags("durasteel", MythicBlocks.DURASTEEL);
        public static final MetalTagHelper<Item> HALLOWED = INSTANCE.createMetalTags("hallowed", MythicBlocks.HALLOWED);

        public static final MetalTagHelper<Item> KYBER = INSTANCE.createMetalTags("kyber", MythicBlocks.KYBER);
        public static final MetalTagHelper<Item> MANGANESE = INSTANCE.createMetalTags("manganese", MythicBlocks.MANGANESE);

        public static final MetalTagHelper<Item> METALLURGIUM = INSTANCE.createMetalTags("metallurgium", MythicBlocks.METALLURGIUM);

        public static final MetalTagHelper<Item> MORKITE = INSTANCE.createMetalTags("morkite", MythicBlocks.MORKITE);

        public static final MetalTagHelper<Item> MIDAS_GOLD = INSTANCE.createMetalTags("midas_gold", MythicBlocks.MIDAS_GOLD);
        public static final MetalTagHelper<Item> MYTHRIL = INSTANCE.createMetalTags("mythril", MythicBlocks.MYTHRIL);

        public static final MetalTagHelper<Item> ORICHALCUM = INSTANCE.createMetalTags("orichalcum", MythicBlocks.ORICHALCUM);
        public static final MetalTagHelper<Item> OSMIUM = INSTANCE.createMetalTags("osmium", MythicBlocks.OSMIUM);
        public static final MetalTagHelper<Item> PALLADIUM = INSTANCE.createMetalTags("palladium", MythicBlocks.PALLADIUM);
        public static final MetalTagHelper<Item> PLATINUM = INSTANCE.createMetalTags("platinum", MythicBlocks.PLATINUM);
        public static final MetalTagHelper<Item> PROMETHEUM = INSTANCE.createMetalTags("prometheum", MythicBlocks.PROMETHEUM);
        public static final MetalTagHelper<Item> QUADRILLUM = INSTANCE.createMetalTags("quadrillum", MythicBlocks.QUADRILLUM);
        public static final MetalTagHelper<Item> RUNITE = INSTANCE.createMetalTags("runite", MythicBlocks.RUNITE);
        public static final MetalTagHelper<Item> SILVER = INSTANCE.createMetalTags("silver", MythicBlocks.SILVER);

        public static final MetalTagHelper<Item> STAR_PLATINUM = INSTANCE.createMetalTags("star_platinum", MythicBlocks.STAR_PLATINUM);
        public static final MetalTagHelper<Item> STARRITE = INSTANCE.createMetalTags("starrite", MythicBlocks.STARRITE);

        public static final MetalTagHelper<Item> STEEL = INSTANCE.createMetalTags("steel", MythicBlocks.STEEL);

        public static final MetalTagHelper<Item> STORMYX = INSTANCE.createMetalTags("stormyx", MythicBlocks.STORMYX);
        public static final MetalTagHelper<Item> TIN = INSTANCE.createMetalTags("tin", MythicBlocks.TIN);

        public static final MetalTagHelper<Item> UNOBTAINIUM = INSTANCE.createMetalTags("unobtainium", MythicBlocks.UNOBTAINIUM);

        @Override
        public MetalTagHelper<Item> createMetalTags(String material, BlockSet set, boolean createAsCommon) {
            MetalTagHelper<Item> metalTags = AdvancedTagHelper.super.createMetalTags(material, set, createAsCommon);

            INSTANCE.ADVANCED_METAL_TAGS.put(material, metalTags);

            return metalTags;
        }

        @Override
        public Registry<Item> getRegistry() {
            return Registry.ITEM;
        }

        @Override
        public String getModId() {
            return MythicMetals.MOD_ID;
        }
    }

    private interface AdvancedTagHelper<T extends ItemConvertible> extends TagHelper<T> {

        default MetalTagHelper<T> createMetalTags(String material, BlockSet set) {
            return createMetalTags(material, set, false);
        }

        default MetalTagHelper<T> createMetalTags(String material, BlockSet set, boolean createAsCommon) {
            Function<String, TagKey<T>> tagBuilder = createAsCommon ? this::createCommonTag : this::createModTag;

            boolean hasRawOreStuff = set.getOreStorageBlock() != null;

            MetalItemTagHelper<T> itemSpecificHelper = null;

            if(getRegistry() == Registry.ITEM){
                TagKey<T> ingotLikeTag =  Objects.equals(material, "unobtainium")
                        || Objects.equals(material, "morkite")
                        || Objects.equals(material, "starrite")
                        || Objects.equals(material, "star_platinum")
                        ? tagBuilder.apply(material)
                        : tagBuilder.apply(material + "_ingots");

                itemSpecificHelper = new MetalItemTagHelper<>(
                        material,
                        ingotLikeTag,
                        hasRawOreStuff ? tagBuilder.apply("raw_" + material + "_ores") : null
                );
            }


            return new MetalTagHelper<>(material,
                tagBuilder.apply(material + "_blocks"),
                set.getOre() != null ? tagBuilder.apply(material + "_ores") : null,
                hasRawOreStuff ? tagBuilder.apply("raw_" + material + "_blocks") : null,
                itemSpecificHelper,
                getRegistry().getKey());
        }

    }

    private interface TagHelper<T> {

        default TagKey<T> createCommonTag(String path) {
            return TagKey.of(getRegistry().getKey(), new Identifier("c", path));
        }

        default TagKey<T> createModTag(String path) {
            return TagKey.of(getRegistry().getKey(), new Identifier(getModId(), path));
        }

        String getModId();

        Registry<T> getRegistry();
    }

    public record MetalTagHelper<T extends ItemConvertible>(String material, TagKey<T> block, @Nullable TagKey<T> oreBlock, @Nullable TagKey<T> rawOreBlock, MetalItemTagHelper<T> itemSpecficHelper, RegistryKey<? extends Registry<T>> registryKey){

        public MetalTagHelper<T> createCommonVersion(){
            return new MetalTagHelper<>(
                material,
                createCommonTag(block.id()),
                oreBlock != null ? createCommonTag(oreBlock.id()) : null,
                rawOreBlock != null ? createCommonTag(rawOreBlock.id()) : null,
                itemSpecficHelper != null ? itemSpecficHelper.createCommonVersion() : null,
                registryKey
            );
        }

        private TagKey<T> createCommonTag(Identifier id){
            return TagKey.of(registryKey, new Identifier("c", id.getPath()));
        }
    }

    public record MetalItemTagHelper<T extends ItemConvertible>(String material, TagKey<T> ingot, @Nullable TagKey<T> rawOre){

        public MetalItemTagHelper<T> createCommonVersion(){
            return new MetalItemTagHelper<>(
                    material,
                    createCommonTag(ingot.id()),
                    rawOre != null ? createCommonTag(rawOre.id()) : null
            );
        }

        @SuppressWarnings("unchecked")
        private TagKey<T> createCommonTag(Identifier id){
            return (TagKey<T>) TagKey.of(Registry.ITEM.getKey(), new Identifier("c", id.getPath()));
        }
    }

}


