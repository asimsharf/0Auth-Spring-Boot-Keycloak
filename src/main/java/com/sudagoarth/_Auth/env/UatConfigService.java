package com.sudagoarth._Auth.env;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("uat")
public class UatConfigService {
    public UatConfigService() {
        System.out.println("UAT configuration loaded...");
    }
}
