package com.inferris.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.inferris.model.PlayerData;

import java.io.IOException;

public class PlayerDataSerializer extends JsonSerializer<PlayerData> {

    @Override
    public void serialize(PlayerData playerData, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("uuid", playerData.getUuid().toString());
        jsonGenerator.writeStringField("username", playerData.getUsername());
        jsonGenerator.writeObjectField("packageRank", playerData.getPackageRank());
        jsonGenerator.writeObjectField("playerRank", playerData.getPlayerRank());
        jsonGenerator.writeObjectField("profile", playerData.getProfile());
        jsonGenerator.writeObjectField("coins", playerData.getCoins());
        jsonGenerator.writeObjectField("channel", playerData.getChannel());
        jsonGenerator.writeObjectField("vanished", playerData.isVanished());
        jsonGenerator.writeObjectField("currentServer", playerData.getCurrentServer());
        jsonGenerator.writeEndObject();
    }
}