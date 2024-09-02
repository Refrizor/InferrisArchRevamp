package com.inferris.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inferris.model.PlayerData;

import java.io.IOException;

public class PlayerDataDeserializer extends JsonDeserializer<PlayerData> {

    @Override
    public PlayerData deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode rootNode = mapper.readTree(jsonParser);

        // Extract the "player" node
        JsonNode playerNode = rootNode.get("player");

        // Deserialize the player node into a PlayerData object
        return mapper.treeToValue(playerNode, PlayerData.class);
    }
}