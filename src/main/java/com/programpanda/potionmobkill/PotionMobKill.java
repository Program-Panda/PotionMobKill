package com.programpanda.potionmobkill;

import com.programpanda.potionmobkill.commands.PotionMobKillcommands;
import com.programpanda.potionmobkill.config.ConfigManager;
import com.programpanda.potionmobkill.events.EntityDeath;
import com.programpanda.potionmobkill.objects.MobObjectValues;
import com.programpanda.potionmobkill.objects.PotionMobObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.HashMap;

public class PotionMobKill extends JavaPlugin {

    public ConfigManager configManager;

    public HashMap<EntityType, PotionMobObject> potionEffectsToGivePlayer = new HashMap<>();
    public String permssionString = "potionmobs.admin";

    public void onEnable(){
        PluginDescriptionFile pluginInfo = getDescription();
        String pluginName = pluginInfo.getName();
        String pluginVer = pluginInfo.getVersion();
        String pluginAuthors = pluginInfo.getAuthors().toString();

        Bukkit.getConsoleSender().sendMessage("\n\n" + ChatColor.BLUE + "[" + pluginName + "]" + ChatColor.GREEN + " Plugin has been enabled!"
                + "\n" + ChatColor.BLUE + "[" + pluginName + "]" + ChatColor.GREEN + " Authors: " + pluginAuthors
                + "\n" + ChatColor.BLUE + "[" + pluginName + "]" + ChatColor.GREEN + " Version: " + pluginVer
                + "\n" + ChatColor.BLUE + "[" + pluginName + "]" + ChatColor.GREEN + " Commercial Use For DreamFire Only\n");

        EnableConfigs();
        RegisterCommands();
        LoadEvents();
    }

    private void EnableConfigs(){
        configManager = new ConfigManager(this);
        configManager.Setup();
        LoadConfigData();
    }

    private void RegisterCommands(){
        getCommand("potionmobs").setExecutor(new PotionMobKillcommands(this));
    }

    private void LoadConfigData(){
        if(configManager.configConfig.isConfigurationSection("effects")){
            for(String s: configManager.configConfig.getConfigurationSection("effects").getKeys(false)){
                PotionMobObject mobObject = new PotionMobObject();
                EntityType entityType = EntityType.valueOf(s);

                if(configManager.configConfig.isConfigurationSection("effects." + s + ".potions")){
                    for(String b: configManager.configConfig.getConfigurationSection("effects." + s + ".potions").getKeys(false)){
                        PotionEffectType potionEffectType = PotionEffectType.getByName(b);
                        MobObjectValues mobObjectValues = new MobObjectValues();

                        mobObjectValues.time = configManager.configConfig.getInt("effects." + s + ".potions." + b + ".time");
                        mobObjectValues.percent = configManager.configConfig.getInt("effects." + s + ".potions." + b + ".percent");

                        mobObject.potionEffects.put(potionEffectType, mobObjectValues);
                    }
                }

                potionEffectsToGivePlayer.put(entityType, mobObject);
            }
        }
    }

    private void LoadEvents(){
        Bukkit.getServer().getPluginManager().registerEvents(new EntityDeath(this), this);
    }

    public void onDisable(){
        SaveConfigData();
    }

    public void SaveConfigData(){
        configManager.configConfig.set("effects", "");
        for(EntityType entityType: potionEffectsToGivePlayer.keySet()){
            PotionMobObject mobObject = potionEffectsToGivePlayer.get(entityType);
            for(PotionEffectType type: mobObject.potionEffects.keySet()){
                MobObjectValues mobObjectValues = mobObject.potionEffects.get(type);
                configManager.configConfig.set("effects." + entityType.name() + ".potions." + type.getName() + ".percent", mobObjectValues.percent);
                configManager.configConfig.set("effects." + entityType.name() + ".potions." + type.getName() + ".time", mobObjectValues.time);
                configManager.SaveConfigs();
            }
        }

    }

}
