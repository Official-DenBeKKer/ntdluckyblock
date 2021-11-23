package me.DenBeKKer.ntdLuckyBlock.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import me.DenBeKKer.ntdLuckyBlock.LBMain;
import me.DenBeKKer.ntdLuckyBlock.LBMain.LuckyBlockType;
import me.DenBeKKer.ntdLuckyBlock.api.loader.PathLoader;
import me.DenBeKKer.ntdLuckyBlock.api.loader.StringLoader;
import me.DenBeKKer.ntdLuckyBlock.customitem.CustomItemFactory;
import me.DenBeKKer.ntdLuckyBlock.customitem.Identifier;
import me.DenBeKKer.ntdLuckyBlock.loader.JSONLoader;
import me.DenBeKKer.ntdLuckyBlock.loader.LegacyLoader;
import me.DenBeKKer.ntdLuckyBlock.util.Config;
import me.DenBeKKer.ntdLuckyBlock.variables.LuckyBlock;
import me.DenBeKKer.ntdLuckyBlock.variables.LuckyDrop;
import me.DenBeKKer.ntdLuckyBlock.variables.LuckyEntry;
import me.DenBeKKer.ntdLuckyBlock.variables.drop.ItemDrop;

public class LuckyBlockAPI {
	
	private final static StringLoader LEGACY = new LegacyLoader();
	private final static PathLoader JSON = new JSONLoader();
	
	/**
	 * 
	 * @return LegacyLoader (Free version config loader)
	 */
	public static StringLoader getLegacyLoader() {
		return LEGACY;
	}
	
	/**
	 * 
	 * @return JSON loader (Premium version config loader)
	 */
	public static PathLoader getJSONLoader() {
		return JSON;
	}
	
	/**
	 * 
	 * @param loaded Config for LuckyEntry loading
	 * @param path Config path
	 * @return LuckyEntry from config path
	 */
	public static LuckyEntry loadDropEntry(Config loaded, String path) { }
	
	/**
	 * 
	 * @deprecated
	 * 	Legacy: {@link #getLegacyLoader()}
	 * 	JSON: {@link #getJSONLoader()}
	 */
	@Deprecated
	public static LuckyDrop loadDrop(String drop0) { return LEGACY.load(drop0); }
	
	/**
	 * 
	 * @deprecated Get LuckyBlock using {@link LuckyBlockType#get()} and add intent {@link LuckyBlock#addIntent(LuckyEntry)}
	 */
	@Deprecated
	public static void addLuckyDrop(LuckyBlockType type, LuckyDrop drop) throws LuckyBlockNotLoadedException {
		addLuckyDrop(type, Arrays.asList(drop));
	}
	
	/**
	 * 
	 * @deprecated Get LuckyBlock using {@link LuckyBlockType#get()} and add intent {@link LuckyBlock#addIntent(LuckyEntry)}
	 */
	@Deprecated
	public static void addLuckyDrop(LuckyBlockType type, LuckyDrop... drop) throws LuckyBlockNotLoadedException {
		addLuckyDrop(type, Arrays.asList(drop));
	}
	
	/**
	 * 
	 * @deprecated Get LuckyBlock using {@link LuckyBlockType#get()} and add intent {@link LuckyBlock#addIntent(LuckyEntry)}
	 */
	@Deprecated
	public static void addLuckyDrop(LuckyBlockType type, Collection<LuckyDrop> drop) throws LuckyBlockNotLoadedException {
		
		LuckyBlock block = type.get();
		block.addIntent(new LuckyEntry(drop));
		
	}
	
	@Deprecated
	public static boolean isLuckyBlock(ItemStack item) {
		return getLuckyBlock(item) != null;
	}
	
	@Deprecated
	public static LuckyBlockType getLuckyBlock(ItemStack item) {
		
		return getLuckyBlock(item, false, true);
		
	}
	
	@Deprecated
	public static boolean isLuckyBlock(ItemStack item, boolean unavailable, boolean checkUUID) {
		return getLuckyBlock(item, unavailable, checkUUID) != null;
	}
	
	@Deprecated
	public static LuckyBlockType getLuckyBlock(ItemStack item, boolean unavailable, boolean checkUUID) {
		return parseLuckyBlock(item, checkUUID, false);
	}
	
	/**
	 * Check that item is LuckyBlock
	 * @param stack ItemStack to be checked
	 * @return Checks that item is LuckyBlock
	 */
	public static boolean checkLuckyBlock(ItemStack stack) {
		return checkLuckyBlock(stack, true);
	}
	
	/**
	 * Check that item is LuckyBlock
	 * @param stack ItemStack to be checked
	 * @param check_tag Check tag
	 * @return Checks that item is LuckyBlock
	 */
	public static boolean checkLuckyBlock(ItemStack stack, boolean check_tag) {
		return checkLuckyBlock(stack, true, check_tag);
	}
	
	/**
	 * Check that item is LuckyBlock
	 * @param stack ItemStack to be checked
	 * @param check_uuid Check uuid
	 * @param check_tag Check tag
	 * @return Checks that item is LuckyBlock
	 */
	public static boolean checkLuckyBlock(ItemStack stack, boolean check_uuid, boolean check_tag) {
		return parseLuckyBlock(stack, check_uuid, check_tag) != null;
	}
	
	/**
	 * Parse LuckyBlock type from ItemStack
	 * @param stack ItemStack to be parsed
	 * @return LuckyBlockType from item if item is LuckyBlock and null if not
	 */
	public static LuckyBlockType parseLuckyBlock(ItemStack stack) {
		return parseLuckyBlock(stack, true);
	}
	
	/**
	 * Parse LuckyBlock type from ItemStack
	 * @param stack ItemStack to be parsed
	 * @param check_tag Check tag
	 * @return LuckyBlockType from item if item is LuckyBlock and null if not
	 */
	public static LuckyBlockType parseLuckyBlock(ItemStack stack, boolean check_tag) {
		return parseLuckyBlock(stack, true, check_tag);
	}
	
	/**
	 * Parse LuckyBlock type from ItemStack
	 * @param stack ItemStack to be parsed
	 * @param check_uuid Check uuid
	 * @param check_tag Check tag
	 * @return LuckyBlockType from item if item is LuckyBlock and null if not
	 */
	public static LuckyBlockType parseLuckyBlock(ItemStack stack, boolean check_uuid, boolean check_tag) { }
	
	/**
	 * 
	 * @param stack ItemStack to mark
	 * @param tag_name Tag name
	 * @param plugin Plugin
	 * @param value Value to insert
	 * @return Insert custom tag
	 */
	public static ItemStack insertTag(ItemStack stack, String tag_name, Plugin plugin, String value) {
		return new Identifier(plugin, tag_name, value).apply(stack);
	}
	
	/**
	 * 
	 * @param stack ItemStack to check
	 * @param tag_name Tag name
	 * @param Plugin
	 * @param value Value
	 * @return Check if ItemStack has tag and value is same
	 */
	public static boolean compareTag(ItemStack stack, String tag_name, Plugin plugin, String value) {
		return new Identifier(plugin, tag_name, value).compare(stack);
	}
	
	/**
	 * 
	 * @param b Block that will be replaced
	 * @param type LuckyBlockType to be inserted
	 * @throws LuckyBlockNotLoadedException
	 */
	public static void placeLuckyBlock(Block b, LuckyBlockType type) throws LuckyBlockNotLoadedException {
		
		if(type.get() != null) {
			type.get().placeBlock(b, false);
		} else throw new LuckyBlockNotLoadedException(type);
		
	}
	
	public static LuckyBlockType parseOldLuckyBlock(ItemStack stack) { }
	
	/**
	 * 
	 * @param b Block to be checked
	 * @return Check if block is LuckyBlock
	 */
	public static boolean isLuckyBlock(Block b) { }
	
	/**
	 * 
	 * @deprecated {@link #breakLuckyBlock0(Block, boolean)}
	 */
	@Deprecated
	public static void breakLuckyBlock0(Block b) { breakLuckyBlock0(b, false); }
	
	/**
	 * 
	 * If you want destroy luckyblock without drop use {@link #breakLuckyBlock(Block, Player, boolean, boolean)} (Player null)
	 * 
	 * @param b Block to break
	 * @param ignore Ignore cancellable
	 */
	public static void breakLuckyBlock0(Block b, boolean ignore) { }
	
	/**
	 * 
	 * @deprecated {@link #breakLuckyBlock(Block, Player, boolean, boolean)}
	 */
	@Deprecated
	public static void breakLuckyBlock(Block b, Player p) { breakLuckyBlock(b, p, true); }
	
	/**
	 * 
	 * @deprecated {@link #breakLuckyBlock(Block, Player, boolean, boolean)}
	 */
	@Deprecated
	public static void breakLuckyBlock(Block b, Player p, boolean drop) { breakLuckyBlock(b, p, drop, false); }
	
	/**
	 * 
	 * @param b Block to break
	 * @param p Player that break it ( target )
	 * @param drop Drop items (IF DROP FALSE PLAYER MAY BE NULL)
	 * @param ignore Ignore cancellable
	 */
	public static void breakLuckyBlock(Block b, Player p, boolean drop, boolean ignore) { }
	
	/**
	 * Change {@link LuckyBlockType#map()} element to null
	 * @param luckyblock
	 */
	public static void destroy(LuckyBlockType type) {
		LuckyBlockType.map().put(type, null);
	}
	
	/**
	 * Change {@link LuckyBlockType#map()} element to null
	 * @param luckyblock
	 */
	public static void destroy(LuckyBlock luckyblock) {
		LuckyBlockType.map().put(luckyblock.getType(), null);
	}
	
	/**
	 * Change {@link LuckyBlockType#map()} element to new one
	 * @param luckyblock
	 */
	public static void rewrite(LuckyBlock luckyblock) {
		LuckyBlockType.map().put(luckyblock.getType(), luckyblock);
	}
	
	public static void system_load(Plugin your_plugin_instance) {
		if(your_plugin_instance.equals(LBMain.getInstance()))
			throw new UnsupportedOperationException("Provide your plugin instance, not NTD LuckyBlock");
		LBMain.log(Level.WARNING, "Loading system by plugin " + your_plugin_instance.getName() + " v" + your_plugin_instance.getDescription().getVersion());
		LBMain.getInstance().system_load();
	}
	
	public static String getVersion() { return LBMain.getVersion(); }
	public static String getLastUpdate() { return LBMain.getLastUpdate(); }
	public static int getBuild() { return LBMain.getBuild(); }
	
	public static LBMain getMainInstance() { return LBMain.getInstance(); }
	
	public static boolean isPremium() { return LBMain.isPremium(); }
	
}
