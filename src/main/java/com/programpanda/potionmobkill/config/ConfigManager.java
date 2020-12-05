package com.programpanda.potionmobkill.config;

import com.programpanda.potionmobkill.PotionMobKill;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private PotionMobKill potionMobKill;

    public FileConfiguration configConfig;
    public File configFile;

    public ConfigManager(PotionMobKill instance){
        potionMobKill = instance;
    }

    public void Setup(){
        if(!potionMobKill.getDataFolder().exists()){
            potionMobKill.getDataFolder().mkdir();
        }

        configFile = new File(potionMobKill.getDataFolder(), "Config.yml");
        CheckFileExists(configFile);
        ReloadConfigs();
    }

    public void ReloadConfigs(){
        configConfig = YamlConfiguration.loadConfiguration(configFile);
    }

    public void SaveConfigs(){
        try {
            configConfig.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void CheckFileExists(File targetFile){
        if(!targetFile.exists()){
            try {
                targetFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
