package com.programpanda.potionmobkill.commands;

import com.programpanda.potionmobkill.PotionMobKill;
import com.programpanda.potionmobkill.objects.PotionMobObject;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class PotionMobKillcommands implements CommandExecutor {

    private PotionMobKill potionMobKill;

    public PotionMobKillcommands(PotionMobKill instance){
        potionMobKill = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String tag, String[] args)
    {
        if(!sender.hasPermission(potionMobKill.permssionString)){
            sender.sendMessage(ChatColor.BLUE + "[PotionMobKill]" + ChatColor.RED + " You must have permssion to use this command!");
            return false;
        }

        //potionmobs mob <entitytype> <potioneffect> <time> <percent>
        //potionmobs mob <entitytype> remove <potioneffect>
        //potionmobs mob remove <entitytype>
        //potionmobs reload

        if(args.length == 1){
            String arg1 = args[0];
            if(arg1.equalsIgnoreCase("reload")){
                potionMobKill.configManager.ReloadConfigs();
            }
            return true;
        }

        if(args.length == 3){
            String arg1 = args[0];
            String arg2 = args[1];
            String arg3 = args[2];

            if(arg1.equalsIgnoreCase("mob") && arg2.equalsIgnoreCase("remove")){
                EntityType type = EntityType.valueOf(arg3);

                if(type != null){
                    if(potionMobKill.potionEffectsToGivePlayer.containsKey(type)){
                        potionMobKill.potionEffectsToGivePlayer.remove(type);
                        sender.sendMessage(ChatColor.GREEN + "You have removed " + ChatColor.WHITE + type.name() + ChatColor.GREEN +
                                " from the list!");
                        potionMobKill.SaveConfigData();
                        return true;
                    }
                }
            }
        }

        if(args.length == 4){
            String arg1 = args[0];
            String arg2 = args[1];
            String arg3 = args[2];
            String arg4 = args[3];

            if(arg1.equalsIgnoreCase("mob") && arg3.equalsIgnoreCase("remove")){
                EntityType type = EntityType.valueOf(arg2);
                PotionEffectType potionEffect = PotionEffectType.getByName(arg4);

                if(type != null && potionEffect != null){
                    potionMobKill.potionEffectsToGivePlayer.get(type).potionEffects.remove(potionEffect);
                    sender.sendMessage(ChatColor.GREEN + "You have removed " + ChatColor.WHITE + potionEffect.getName() + ChatColor.GREEN +
                            " from the " + ChatColor.WHITE + type.name() + ChatColor.GREEN + " list!");
                    potionMobKill.SaveConfigData();
                    return true;
                }
            }
        }

        if(args.length == 5){
            String arg1 = args[0];
            String arg2 = args[1];
            String arg3 = args[2];
            String arg4 = args[3];
            String arg5 = args[4];

            if(arg1.equalsIgnoreCase("mob") && isNumeric(arg4) && isNumeric(arg5)){
                EntityType type = EntityType.valueOf(arg2);
                PotionEffectType potionEffect = PotionEffectType.getByName(arg3);
                int time = Integer.parseInt(arg4);
                int percent = Integer.parseInt(arg5);

                if(type != null && potionEffect != null){
                    PotionMobObject mobObject = new PotionMobObject();
                    if(potionMobKill.potionEffectsToGivePlayer.containsKey(type)){
                        mobObject = potionMobKill.potionEffectsToGivePlayer.get(type);
                    }
                    mobObject.ChangePotionEffects(potionEffect, time, percent);

                    potionMobKill.potionEffectsToGivePlayer.put(type, mobObject);
                    sender.sendMessage(ChatColor.GREEN + "You have added: " + ChatColor.AQUA + type.name()
                            + " to the list with potion effect: " + ChatColor.AQUA + potionEffect.getName());
                    return true;
                }
            }

        }

        sender.sendMessage(ChatColor.BLUE + "[PotionMobKill]" + ChatColor.RED + "/potionmobs mob <entitytype> <potioneffect> <time> <percent>");
        sender.sendMessage(ChatColor.BLUE + "[PotionMobKill]" + ChatColor.RED + "/potionmobs mob <entitytype> remove <potioneffect>");
        sender.sendMessage(ChatColor.BLUE + "[PotionMobKill]" + ChatColor.RED + "/potionmobs mob remove <entitytype>");
        sender.sendMessage(ChatColor.BLUE + "[PotionMobKill]" + ChatColor.RED + "/potionmobs reload");

        return false;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
