# PotionMobKill

[![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)

Plugin created in response to: https://bukkit.org/threads/get-potion-effect-on-mob-kill.487585/

## Description
The plugin is designed to add potion effects to mob kills when a player kills a entity. The ops (permsion node can be given) can set what potion effects are given on the death of any entity they want and can add multiple potion effects (each with there own time duration and likelhood) to each entity death trigger. The plugin allows the op to set these through the config, however, commands are provided to allow the user to set these values from inside the game whilst auto updating the config.

> Commands:
/potionmobs reload - Reload the config
<br />/potionmobs mob <EntityType> <PotionEffectType> <Duration Of Effect> <Likelyhood of getting it (1-100)> - Add a potion effect to a entity
/potionmobs mob remove <EntityType> - Remove all potion effects for a entity
/potionmobs mob <EntityType> remove <PotionEffectType> - Remove a spefic potion effect from a entity

> Permssions:
potionmobs.admin - Give users access to all commands

> Node Defintion:
 <EntityType> - [Spigot Entity](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/EntityType.html)
 <PotionEffectType> - [Spigot Potion Effect Type](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/potion/PotionEffectType.html)
 <Duration Of Effect> - how long the potion effect will last upon getting the effect
 <Likelyhood of getting it (1-100)> - 1% to 100% likely to get the effect upon death of the entity.

## Video

## Download Options
- Download the .jar file named PotionMobKill from the root of the repo.
- Download Link: ~ [Direct Download](https://onedrive.live.com/download?cid=BC6F02EA0BA906A0&resid=BC6F02EA0BA906A0%216362&authkey=AIIKlpmPIFXI8xg)

