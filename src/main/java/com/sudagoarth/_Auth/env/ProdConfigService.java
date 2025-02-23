package com.sudagoarth._Auth.env;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdConfigService {
    public ProdConfigService() {
        System.out.println("PROD configuration loaded...");
    }
}
