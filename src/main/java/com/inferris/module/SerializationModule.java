package com.inferris.module;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.inferris.serialization.PlayerDataDeserializer;
import com.inferris.serialization.PlayerDataSerializer;
import com.inferris.model.PlayerData;

public class SerializationModule extends SimpleModule {

    public SerializationModule() {
        addDeserializer(PlayerData.class, new PlayerDataDeserializer());

        addSerializer(PlayerData.class, new PlayerDataSerializer());
    }
}