# Discipline Specification Proposal
> Once an initial specification is finalised, and implementation has begun, the specification will be versioned, and this file will be renamed to spec-version.md

As discussions occur about a particular section of this specification, they should be linked to herein, so that further feedback can be solicited. 

Changes to this specification after the proposal stage should only be made with consensus, provided a sufficient amount of active user's support, or the support or 2 or more maintainers. 

This specification is being designed to provide a blueprint for contributors to begin implementation, once we are at such a stage where that is possible. It will also serve as the basis for the documentation created for users, and as such is not wasted time.

---

## 1. Name / Branding

The plugin is to be called discipline[^1], and will be centered around general moderation duties included but not limited to:

## 2. Aims and Principles

- **Punishments**

This is the bread-and-butter core of this plugin, it aims to be a more or less replacement for equivalent plugins in the space, both those which are proprietary and open source. Punishments should be transparent and auditable to the fullest extent possible, and individual actions will be able to be rolled back, should the need arise.

- **Moderation Management** 

Through the extension of the discipline web interface, the plugin aims to provide a knowledgebase on your players and allow for the easy tracking of appeals, complaints, and reports as well as the concatenation of multiple sources of information, including PLAN, various anti-cheats with the ability to export such information for viewing on dashboard applications such as Grafana.

- **Concerns of Overreach / 'Bloat'**

We do not desire for this plugin to overreach, and as such features which do not fit this goal may be provided as external add-ons for the core project, which may be maintained by the same parent organisation as the core project. The API is to be designed to be as extensible as possible.

## 3. Supported Platforms

The project will be developed around a central **core** which will then be used to implement platform-specific features as necessary.

### 1.0
- [Paper](https://papermc.io/) (and its derivatives such as Airplane)
- [Velocity](https://velocitypowered.com/) 
- [Geyser](https://geysermc.org/) 

### Considered
Support for these will be based on demand.
- [Sponge](https://www.spongepowered.org/)
- [Fabric](https://github.com/CaffeineMC/lithium-fabric)
- [Forge](https://minecraftforge.net/)

## 4. Planned Features
- Command-focused

Minecraft 'Graphical User Interfaces' are gimmicky, clunky, and slow to use, it is best if such a feature set is not included in the core plugin, but if a third party wishes to create a GUI on top of the project's API they will be supported, and we would be happy to promote such a project.

- Easy to switch to

We want the onboarding process for new server owners, and those with existing punishment setups to be as simple as possible through detailed documentation, and support from the community on discord and via the discussion board on GitHub. We will also be providing the ability to import punishments from community-used existing projects, such as LiteBans and LibertyBans, as well as those which were inflicted using the first-party implementation of punishments. 

- Support for punishing offline players

There's nothing more frustrating than not being able to inflict your wrath on a player who has just logged off, or not being able to deal with appeals and complaints while the player is offline, as such all of this will be possible with the player offline.

- Fully localizable 

Every string sent in-game will be modifiable in such a way that makes it easy to translate, potentially through the use of online utilities such as Crowdin to source contributions.

- Highly Customizable 

Based on Configurate, several configuration formats will be available, primarily HOCON and YAML. 

- Notifications for on-duty staff

Staff members (identified by role, context, or permission) will be notified if users sharing an IP address join, and if another username tries to join from the same IP as a punished player in order to detect punishment evasion early. It should be possible to outright prevent it by default. 

- Support for Egress

Nothing is more fun than looking at pretty graphs, and for that, we're going to try to create hooks for the likes of Grafana, so you can have all the pretty graphs you could ever want.

- SQL and NoSQL support

We will provide support for several types of databases, SQLLite will be used by default however it is HIGHLY recommended that a proper database server is utilised such as Postgres, or MariaDB. Support for MongoDB is being considered as well. 

- Rollbacks

In the event of questionable action by a particular member of staff, compromised accounts, etc it will be possible to roll back the actions of a particular member of staff over a time period, as well as the revocation of related tokens, for use in the web-panel.

- Customizable disconnect messages

With sensible defaults, and a variety of out-of-the-box themes. 

- Placeholders

Through the use of internal placeholders and PlaceholderAPI, you will be able to customize messages and integrate discipline into other plugins without the use of our API.

- Audit Logging

It will be possible to use webhooks to receive audit log events for use on the likes of discord, and an audit log will be viewable online. Modification to this will not be possible outside of direct database modification to prevent abuse. This will also include web-panel activity.

- Weighted warning system

It will be possible to associate a weighting to a given warning, that way a warning for a racist remark is not weighted the same as spamming, this will be possible through command flags, and automatic punishments will be implemented by the total weight score, rather than a simple number of warnings.

- Vote kicking/Vote temp ban

If there is no member of staff online, it will be possible to vote kick, then vote temp-ban a player for a period of up to 8 hours, the users involved in such a vote will be logged, in the event that it is used maliciously.

## 5. Proposed Commands and usage.
Commands will be implemented using cloud and will make use of command-flags, and the native auto-completion.

**N.B**: <> denotes a required parameter, and [] an optional parameter.

#### Punishment issuing commands
| command      | usage                                        | description                                                    |
| ------------ | -------------------------------------------- | -------------------------------------------------------------- |
| warn         | `/warn -[ip,uuid,e,w] <target> [reason]`     | Warns a user, sending them a message as to why, with automatic punishments for reaching certain (configurable) thresholds of warnings. |
| mute         | `/mute -[ip,uuid,e] <target> [reason]`       | Mutes a user, preventing them from being able to send messages |
| kick         | `/kick -[ip,uuid] <target> [reason]`         | Kicks a user, disconnecting them from the server               |
| ban          | `/ban -[ip,uuid,e] <target> [reason]`        | Bans a user, preventing them from joining the server on an ongoing basis.
| tempban      | `/tempban -[ip,uuid] <target> [reason]`      | Temporarily bans a user, syntactic sugar for /ban -e           |
| shadowmute   | `/shadowmute -[ip,uuid,e] <target> [reason]` | Mutes a player without them knowing, only they will be able to see their messages. |
| surveil      | `/surveil -[ip,uuid,e] <target> [reason]`    | Adds the player to a surveilance list, which notifies staff of their use of certain commands [e.g. direct messaging.] |
| note         | `/note -[ip,uuid] <target> <note>`           | Add a note to a given player, the most recent of which will be displayed to online staff on join, and will be available to be browsed online and via lookup.

#### Lookup / History commands

> Due to the ergonomics of Minecraft commands, it would be best if this experience was limited in such a way that it endorses the use of the web panel

| command      | usage                                      | description                                                    |
| ------------ | ------------------------------------------ | -------------------------------------------------------------- |
| lookup       | `/lookup -[ip,uuid,t,e] <target>`            | Checks previous infractions a user has committed, as well as notes, use -t to specify a particular type of infraction and -e to set the period of the lookup. |
| staffhistory | `/staffhistory -[ip,uuid,t,e] <target>`      | Checks previous infractions a user has issued.
| warnlookup   | `/warnlookup -[ip,uuid,e] <target>`          | Syntactic sugar for `/lookup -t warn`
| mutelookup   | `/mutelookup -[ip,uuid,e] <target>`          | Syntactic sugar for `/lookup -t mute`
| kicklookup   | `/kicklookup -[ip,uuid,e] <target>`          | Syntactic sugar for `/lookup -t kick`
| banlookup    | `/banlookup -[ip,uuid,e] <target>`           | Syntactic sugar for `/lookup -t ban`
| notelookup   | `/notelookup -[ip,uuid,e] <target>`          | Syntactic sugar for `/lookup -t note`

## 6. Stack / Architecture

This list only serves as a rough guide to show the layout and architecture of the project.

-   Java 11, so as to support reasonably old versions, from 1.8 onwards. 
-   Gradle: Kotlin DSL
-   JUnit 5
-   Adventure, as a wrapper for Minecraft-native components.
-   Cloud for command dispatching, auto-competition, and flag-parsing
-   Configurate for configuration handling
-   Ebean as a Java ORM

## 7. Jargon Buster

This proposal may have made use of certain key terms which have a particular meaning within the scope of this project, this section serves to define those as well as act as an appendix for additional information on a given topic.

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

There is currently a work-in-progress [PR](https://github.com/Incendo/cloud/pull/330) which adds this functionality to cloud, however, this has not been merged to date. 

#### Flags
If an IP or UUID is not specified then it is assumed the target is a username.

| flag | example                                     | purpose                                     |
| ---- | ------------------------------------------- | ------------------------------------------- |
| ip   | -ip 127.0.0.1                               | Interpret user as an IP                     |
| uuid | -uuid 22d45362-ccfc-4845-a843-3012aa8dbfd9  | Interpret user as a UUID                    |
| e    | -e 1M2w3d                                   | Set an expiry for an action [if supported] or the lookup period for a given request.  |
| w    | -w 3                                        | Sets the weight of a warning                |
| t    | -t warn                                     | Sets the type of the request, valid entries are warn, mute, kick, ban, note |

## Web Panel

-   Ability for the general public to view [akin to litebans’ viewer see example [here](https://www.roxbot.com/bans/bans.php)]
-   Ability for appeals to be lodged, and for moderators/administrators to be able to see and manage appeals, and have a workflow in place for dealing with these
-   Ability for reports to be made, with a facility for attachments such as screenshots.
-   Administrator control which allows for pruning, and modifying records from the web panel.
-   Ability to configure the plugin from the web panel.
-   Full audit log
-   Built-in Vue/Nuxt 
-   Authentication [^2]
-   Hosting
-   Provide docker images/compose [include DB in here in a compose/stack]
-   Heroku Templates, etc?
-   Software as a service offering?

## API / Integration

Plugin API :: General implementation of an API, this API will be fleshed out at a later date. [^3]

RESTful API :: A swagger implementation will be created at a later date, allowing users to have a web interface and other non-Minecraft services to hook into the plugin, perhaps laying the basis for cross-game support for guilds that host servers across multiple games. 

## Hooks

Many plugins already exist as de facto standards in the community for certain things, Placeholder API comes to mind. We should provide support for these projects so far as is possible in the hope they do the same for this project into the future.

- [**Placeholder API**](https://github.com/PlaceholderAPI/PlaceholderAPI) :: Expose Expiry, reasons, etc for warnings that way things such as featherboard could display punishments. Internal Placeholders will be available for use on Velocity.
- [**PLAN**](https://github.com/plan-player-analytics/Plan) :: Allow punishments to be viewable on Plan, as well as have some PLAN metrics available on our web panel, if present
- PROPOSED: [**Analyse**](https://analyse.net/) :: For the same reasons as PLAN above.
- PROPOSED: [MCBanner](https://mcbanners.com/) implementation :: To show common offenders, and contribute to the database. (Opt-in)

## Cross-server considerations

There could be a use-case in which having a ban *not* be universal across all servers in a network, in such a situation a method to identify the desired servers that the user is banned on should be added, if the field is blank it should be assumed they are banned everywhere. 

---

[^1]: The core project remains unnamed, in this version of the specification. A discussion (2021-12-28) on branding can be found here: https://github.com/as-of-yet-unnamed/plugin/discussions/4

[^2]: A discussion has been opened on the topic of web authentication 2021-12-28 https://github.com/as-of-yet-unnamed/plugin/discussions/3

[^3]: A discussion has been opened in order to develop a RESTful API specification https://github.com/as-of-yet-unnamed/plugin/discussions/6

**N.B**: Please notify the maintainers if a link to a discussion or relevant issue/PR is missing.
