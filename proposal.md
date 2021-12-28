# As of yet, an unnamed bans / punishment plugin.

## Name

What to name the plugin? This is also something we're open for feedback on. 

-   On the nose, and name it akin to current existing plugins, e.g. “LiteBans,” “LibertyBans,” etc.
-   Something more generic, don’t base it on bans and see it as an overall punishment management system

>  I feel like we should go for an epic sounding mononym like “discipline,” which encompasses everything the plugin is trying to be, also calling the plugin discipline sounds pretty fkn metal imo
- Rian

## Platforms
-   Paper + forks? (Undecided if Spigot will be supported atm)
-   Velocity (bungee is too much work to maintain)
-   Sponge, post-first release likely
-   Fabric support, depending on interest
-   Geyser support
    
## Misc Features
-   Absolutely NO gimmicky GUIs
-   Ban importing from other plugins
-   Support for punishing offline players
-   [Prometheus](https://prometheus.io/) support for Grafana Fanboys
-   UUID based
-   Fully customizable locationization
-   HOCON support for configuration
-   Staff notifications on duplicate IPs
-   Punishment rollbacks
-   SQL & NoSQL support 
-   Fully customizable screens on kick, ban, and join while banned
-   Utilize internal placeholders and PlaceholderAPI
-   Discord / Guilded Webhook support for notification / audit logging. 
-   Full audit log, including web-panel activity
-   Weighted warning system
-   Vote to kick, vote to mute

## Commands
Commands will be implemented using cloud.
<required> [optional]

-   Issue Punishments
		-   /ban 
		-   /banip
		-   /mute
		-   /muteip
		-   /shadowmute :: Only the sender sees their message
		-   /kick
		-   /kickip
		-   /warn
		-   /warnip
		-   /timeout [combination of kick/mute?]
		-   /surveil [Add specific user to social spy]
-   Lookup
Due to the ergonomics of minecraft commands, it would be best if this experience was limited in such a way that it endorses the use of the web panel

-   /banlookup <user:ip:uuid> 
-   /mutelookup <user:ip:uuid>
-   /kicklookup <user:ip:uuid>
-   /warnlookup <user:ip:uuid>
-   /lookup [type] [issuer] [timeperiod] 
-   /blame <issuer> [type]

## Stack

-   Java 17, only support for the latest minecraft version upon release	
-   Adventure for Components
-   Cloud for command dispatching 
-   Ebean as an ORM?
-   MongoDB support?
-   List other dependencies as they may arise
    
## Jargon

####  Timeperiod
A string formatted as a piece of time for example “1M2w4d16h10m30s” 

| Symbol  | Meaning |
| --------|---------|
| M       | month   |
| w       | week    |
| d       | day     |
| h       | hour    |
| m       | minute  |
| s       | second  |
* Enforced order of above symbols. 

Alternatively, add support for Unix Timestamps as well, for expiry. 

## Web Panel

-   Ability for the general public to view [akin to litebans’ viewer see example [here](https://www.roxbot.com/bans/bans.php)]
-   Ability for appeals to be lodged, and for moderators/administrators to be able to see and manage appeals, and have a workflow in-place for dealing with these
-   Administrator control which allows for pruning, and modifying records from the web panel
-   Full audit log
-   Software as a service hosting for this? [Potentially, would require some implementation of a web server/auth so we’re not directly interfacing with the db]
-   Built in Vue/Nuxt? 
-   Authentication? 
-   Hosting
-   Provide docker images/compose [include DB in here too?]
-   Heroku Templates, etc?

## API / Integration

Plugin API :: General implementation of an API which allows plugins to perform operations without *shudders* dispatchCommand 

RESTful API :: Provide associated Swagger implementation, allows users to have a web interface and other non-minecraft services to hook into the plugin, perhaps laying the basis for cross-game support for guilds that host servers across multiple games. 

## Plugin Hooks

Many plugins already exist as de facto standards in the community for certain things, Placeholder API comes to mind. We should provide support for these projects so far as is possible in the hope they do the same for this project into the future.

**Placeholder API** :: Expose Expiry, reasons, etc for warnings that way things such as featherboard could display punishments. Internal Placeholders will be available for use on Velocity.
**PLAN** :: Allow punishments to be viewable on Plan, as well as have some PLAN metrics available on our web panel, if present

## Cross-server considerations

There could be a usecase in which having a ban *not* be universal across all servers in a network, in such a situation a method to identify the desired servers that the user is banned on should be added, if the field is blank it should be assumed they are banned everywhere. 
