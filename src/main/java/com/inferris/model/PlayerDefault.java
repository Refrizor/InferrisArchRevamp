package com.inferris.model;

import com.inferris.model.rank.StaffRank;
import com.inferris.model.rank.SupporterRank;

import java.time.Instant;

public class PlayerDefault {
    public static final Rank DEFAULT_RANK = new Rank(SupporterRank.NONE, StaffRank.NONE);
    public static final Profile DEFAULT_PROFILE = new Profile(
            Instant.EPOCH.getEpochSecond(), Instant.EPOCH.getEpochSecond(),
            null, null,
            0, false, false);
    public static final int DEFAULT_COINS = 100;
    public static final Server DEFAULT_CURRENT_SERVER = Server.LOBBY;
    public static final UserPreferences DEFAULT_USER_PREFERENCES = new UserPreferences(true, false, false);
    public static final PlayerSettings DEFAULT_PLAYER_SETTINGS = new PlayerSettings();
}

