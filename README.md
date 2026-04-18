# RealmCore

RealmCore is a Paper plugin that groups a typical server core feature set into one project.

It covers homes, warps, teleport requests, spawn handling, daily rewards, random teleport and a small set of staff utilities. The plugin uses simple YAML storage and is aimed at single-server setups that want one central utility plugin instead of several small ones.

## Included systems

- homes and home GUI
- warps and warp GUI
- spawn and setspawn
- teleport requests (`/tpa`, `/tpahere`, `/tpaccept`, `/tpdeny`)
- daily rewards
- random teleport with cooldown
- heal, feed, fly, vanish and gamemode utilities
- starter kit support
- configurable messages and basic plugin config

## Commands

- `/realmcore`
- `/setspawn`
- `/spawn`
- `/sethome`
- `/home`
- `/setwarp`
- `/warp`
- `/tpa`
- `/tpahere`
- `/tpaccept`
- `/tpdeny`
- `/daily`
- `/heal`
- `/feed`
- `/fly`
- `/rtp`
- `/vanish`
- `/gm`

## Configuration highlights

The default config currently includes:

- a home limit
- teleport request expiry
- daily reward cooldown and reward items
- RTP cooldown and radius
- starter kit toggle

Configuration files live in:

- `src/main/resources/config.yml`
- `src/main/resources/messages.yml`

## Build

Requirements:

- Java 17
- Maven
- Paper API `1.20.6-R0.1-SNAPSHOT`

Build command:

```bash
mvn clean package
```

The compiled jar is written to:

```bash
target/RealmCore.jar
```

## Notes

- storage is YAML-based
- the plugin targets Paper rather than a multi-proxy setup
- the repository is a good base for extending an all-in-one server utility plugin
