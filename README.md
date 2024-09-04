# Proxy
This is the newly changed player data architecture for Inferris that works with the [Inferris REST API](https://github.com/inferris-com/InferrisAPI) microservice. This will be integrated and updated into the private proxy plugin's current architecture.

It was decided to keep this completely separate, and not in a new branch, to help with testing.

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
