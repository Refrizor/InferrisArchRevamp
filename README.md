# Proxy
This is an isolated demo architecture for Inferris that works with the [Inferris REST API](https://github.com/inferris-com/InferrisAPI) microservice. It is an improvement
from the current codebase, and a lot of work is being done to plan, design, and then implement
key features so that the plugin can build on them.

Once done, this architecture will be integrated into our *current* proxy codebase.

Some content is for debugging scenarios and examples, and may not reflect what is actually shown on the Minecraft network.

## Architecture
The architecture is designed to handle the new REST API. Previously, we relied on the proxy plugin to become the frontend. It was boasting
with CRUD operations, and became a hassle for future scenarios; example: we will want our admin panel and other external platforms to query data,
make changes, and then publish the changes while ensuring the Minecraft network is consistently in sync, without the proxy doing all the work.

The REST API solves that, and ensures our systems use a more solidified single-responsibility approach.

### Key architecture improvements:
- REST API integration
- - PlayerDataManager and PlayerDataRepository has been removed
- Switched from BungeeCord to Velocity
- - New color palette that works with Components
- Rank system redesign
  - The rank system has been redesigned from an integer-based branch system to an enum-based structure, allowing for better flexibility, customization, and clearer management of rank behavior and display
- User Preferences
- - Preferences are now a thing, which allows greater customization catering to a player's needs

## PlayerData
`PlayerData` is setup to be serialized, and acts as a Data Transfer Object (DTO) that is put into the body of API **UPDATE** and **POST** requests. The Player Data object includes:

### Core Attributes:
- **UUID**  
  _Type:_ `UUID`  
  _Description:_ Unique identifier for the player.

- **Username**  
  _Type:_ `String`  
  _Description:_ Player's in-game username.

- **Rank data**  
  _Type:_ `Rank`  
  _Description:_ Current rank information; `SupporterRank` and `StaffRank`.

- **Lumina**  
  _Type:_ `int`  
  _Description:_ Player's current coins (Lumina).

- **Current channel**  
  _Type:_ `Channel`  
  _Description:_ The channel where the player is currently active.

- **Vanish status**  
  _Type:_ `boolean`  
  _Description:_ Whether the player is currently in vanish mode (hidden from other players).

- **Current server**  
  _Type:_ `Server`  
  _Description:_ The server the player is currently connected to.

---

### Profile Information:
- **Registration date**  
  _Type:_ `Long`  
  _Description:_ Timestamp of when the player joined.

- **Last activity**  
  _Type:_ `Long`  
  _Description:_ Timestamp of the player's last recorded activity.

- **Bio**  
  _Type:_ `String`  
  _Description:_ Short biography provided by the player.

- **Pronouns**  
  _Type:_ `String`  
  _Description:_ Player's preferred pronouns.

- **XenForo ID**  
  _Type:_ `int`  
  _Description:_ Player's Account ID on the XenForo platform.

- **Discord link status**  
  _Type:_ `boolean`  
  _Description:_ Whether the player's account is linked to Discord.

- **Flagged status**  
  _Type:_ `boolean`  
  _Description:_ Indicates if the player has been flagged for moderation or other reasons. See POI.

---

### User Preferences:
- **Should always vanish**  
  _Type:_ `boolean`  
  _Description:_ Whether the player should always log in as vanished.

- **Should send welcome message**  
  _Type:_ `boolean`  
  _Description:_ Whether a welcome message should be sent upon the player's login.

- **Should use secret mode**  
  _Type:_ `boolean`  
  _Description:_ Whether the player prefers using secret mode for additional privacy or anonymity.

---

## Future plans:
- More API endpoints
- Nickname system
- Achievements?
