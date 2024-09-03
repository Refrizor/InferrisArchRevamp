package com.inferris.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Profile {
    @JsonProperty("registrationDate")
    private Long registrationDate;
    @JsonProperty("lastActivity")
    private Long lastActivity;
    @JsonProperty("bio")
    private String bio;
    @JsonProperty("pronouns")
    private String pronouns;
    @JsonProperty("xenforoId")
    private int xenforoId;
    @JsonProperty("discordLinked")
    private boolean discordLinked;
    @JsonProperty("flagged")
    private boolean flagged;

    public Profile(Long registrationDate, Long lastActivity, String bio, String pronouns, int xenforoId, boolean discordLinked, boolean flagged) {
        this.registrationDate = registrationDate;
        this.lastActivity = lastActivity;
        this.bio = bio;
        this.pronouns = pronouns;
        this.xenforoId = xenforoId;
        this.discordLinked = discordLinked;
        this.flagged = flagged;
    }

    public Profile() {
    }

    // Method to get the Unix timestamp of the registration date
    @JsonIgnore
    public Long getRegistrationDate() {
        return (registrationDate == null) ? Instant.now().getEpochSecond() : registrationDate;
    }

    @JsonIgnore
    public Long getLastActivity() {
        return lastActivity;
    }

    // Method to get the formatted registration date with a custom pattern
    @JsonIgnore
    public String getFormattedRegistrationDate(String pattern) {
        Instant instant = (registrationDate == null) ? Instant.now() : Instant.ofEpochSecond(registrationDate);
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC"));
        return zonedDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    // Method to get only the date part in the default format (yyyy-MM-dd)
    @JsonIgnore
    public String getRegistrationDateOnly() {
        return getFormattedRegistrationDate("yyyy-MM-dd");
    }

    // Method to get only the time part in the default format (HH:mm)
    @JsonIgnore
    public String getRegistrationTimeOnly() {
        return getFormattedRegistrationDate("HH:mm z");
    }

    // Method to get both date and time in the default format (yyyy-MM-dd HH:mm z)
    @JsonIgnore
    public String getRegistrationDateTime() {
        return getFormattedRegistrationDate("yyyy-MM-dd HH:mm z");
    }

    public String getBio() {
        return bio;
    }

    public String getPronouns() {
        return pronouns;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setPronouns(String pronouns) {
        this.pronouns = pronouns;
    }

    public void setRegistrationDate(Long registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setLastActivity(Long lastActivity) {
        this.lastActivity = lastActivity;
    }

    public int getXenforoId() {
        return xenforoId;
    }

    public void setXenforoId(int xenforoId) {
        this.xenforoId = xenforoId;
    }

    public boolean isDiscordLinked() {
        return discordLinked;
    }

    public void setDiscordLinked(boolean discordLinked) {
        this.discordLinked = discordLinked;
    }

    public boolean isFlagged() {
        return flagged;
    }

    @JsonIgnore
    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }
}
