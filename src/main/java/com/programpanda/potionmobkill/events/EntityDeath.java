package com.programpanda.potionmobkill.events;

import com.programpanda.potionmobkill.PotionMobKill;
import com.programpanda.potionmobkill.objects.MobObjectValues;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.HashMap;
import java.util.Random;

public class EntityDeath implements Listener {

    private PotionMobKill potionMobKill;

    public EntityDeath(PotionMobKill instance){
        potionMobKill = instance;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        LivingEntity dead = event.getEntity();
        if(potionMobKill.potionEffectsToGivePlayer.containsKey(dead.getType())){
            Player killer = dead.getKiller();
            Random r = new Random();
            int result = r.nextInt(100);

            HashMap<PotionEffectType, MobObjectValues> potionEffects = potionMobKill.potionEffectsToGivePlayer.get(dead.getType()).potionEffects;
            for(PotionEffectType type: potionEffects.keySet()){
                MobObjectValues mobObjectValues = potionEffects.get(type);
                int percent = mobObjectValues.percent;
                int time = mobObjectValues.time;

                if(result <= percent){
                    killer.addPotionEffect(new PotionEffect(type, 20 * time, 10));
                    killer.sendMessage(ChatColor.GREEN + "You have been given: " + ChatColor.WHITE + type.getName()
                            + ChatColor.GREEN + " for " + ChatColor.WHITE + time + ChatColor.GREEN + " seconds!");
                }
            }
        }
    }

}
