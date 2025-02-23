package com.sudagoarth._Auth.env;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevDatabaseSeeder {
    public DevDatabaseSeeder() {
        System.out.println("Seeding development database...");
    }
}

