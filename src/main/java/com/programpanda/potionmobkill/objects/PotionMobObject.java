package com.programpanda.potionmobkill.objects;

import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.List;

public class PotionMobObject {

    public HashMap<PotionEffectType, MobObjectValues> potionEffects = new HashMap<>();

    public void ChangePotionEffects(PotionEffectType type, int time, int percent){
        MobObjectValues values = new MobObjectValues();
        if(potionEffects.containsKey(type)){
            values = potionEffects.get(type);
        }
        values.time = time;
        values.percent = percent;
        potionEffects.put(type,values);
    }

}
