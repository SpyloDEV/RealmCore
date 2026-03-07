# RealmCore

RealmCore is a larger modular Minecraft Paper plugin built as a portfolio project and marketplace base product.

## Included systems

- Homes with GUI
- Warps with GUI
- Spawn / SetSpawn
- TPA / TPAHERE / Accept / Deny
- Daily rewards
- RTP with cooldown
- Heal / Feed / Fly
- Vanish
- Gamemode command
- Starter kit
- YAML-based storage
- Maven + GitHub-ready structure

## Tech stack

- Java 17
- Paper API
- Maven
- YAML config storage

## Build

```bash
mvn clean package
```

The compiled jar will be created in:

```bash
target/RealmCore.jar
```

## Suggested GitHub description

Feature-rich Paper plugin with homes, warps, teleport systems, GUI menus, daily rewards and admin utilities.

## Suggested topics

minecraft, paper, spigot, minecraft-plugin, java, paper-plugin, spigot-plugin, minecraft-server

## Command overview

- `/home [name]`
- `/sethome [name]`
- `/warp [name]`
- `/setwarp [name]`
- `/spawn`
- `/setspawn`
- `/tpa <player>`
- `/tpahere <player>`
- `/tpaccept`
- `/tpdeny`
- `/daily`
- `/heal`
- `/feed`
- `/fly`
- `/rtp`
- `/vanish`
- `/gm <0|1|2|3>`
- `/realmcore reload`

## Notes

This project was prepared as a strong starting point for a portfolio or BuiltByBit product page.  
For a real premium release, the next best upgrades would be:

- MySQL support
- better permissions granularity
- polish pass for GUI design
- cooldown config expansion
- placeholders / API hooks
- customizable menu icons
