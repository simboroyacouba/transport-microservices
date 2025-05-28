package com.ibam.reservationservice.config;

import com.ibam.reservationservice.entities.Reservation;
import com.ibam.reservationservice.entities.ReservationItem;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


@Configuration
public class RestRepositoryConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry registry) {
        config.exposeIdsFor(Reservation.class);
        config.exposeIdsFor(ReservationItem.class);
    }
}
