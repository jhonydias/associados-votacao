package com.example.springapi.config;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
public class DateConfig {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneId.of("America/Sao_Paulo"));

    private static final Instant dateBrazilSpNow = Instant.parse(formatter.format(Instant.now()));

    public static Instant getDateBrazilSpNow() {
        return dateBrazilSpNow;
    }


}
