# Proxy
This is the newly developed Inferris player data architecture that works with the REST API microservice. Includes just some of the features, and is not the actual proxy plugin.
This was made public, and will be merged into the actual BungeeCord proxy plugin's architecture.
This repository will likely be kept up for the time being as an old source to get a timelapse of how our architecture has improved.

A lot of the content are just debugging scenarios and examples, and may not reflect what is actually shown on the Minecraft network.

## Architecture
The architecture is designed to handle the new REST API. Previously, we relied on the proxy plugin to become the backend. It was boasting
with data operations, and became a hassle for future scenarios where we will want our admin panel and other external platforms to query data
and make changes.

The REST API solves that.

## PlayerData
Player Data is setup to be serialized, and acts as a Data Transfer Object (DTO) that is put into the body of API requests. The Player Data includes:
- UUID
- Username
- Rank data
- Profile
- - Registration date
  - Last activity
  - Bio
  - Pronouns
  - XenForo ID
  - Discord link status
  - Whether or not they are flagged
- Lumina (Coins)
- Current channel
- Vanish status: true or false
- Current server

## Future plans:
- More API endpoints
- Preferences
- - Always vanish?
  - Opt out of friendly join messages?
- Nickname system
- Achievements?
