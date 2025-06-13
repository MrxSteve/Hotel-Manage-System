package com.devsteve.hotel_manage_system.infra.scheduled;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupReservationCleaner {
    private final ExpiredReservationCleaner expiredReservationCleaner;

    @PostConstruct
    public void init() {
        expiredReservationCleaner.releaseRoomsWithExpiredReservations();
    }
}
