import { InjectionToken } from "@angular/core";

export interface EnvironmentConfig {
    environment: {
        customerBaseUrl: string;
    }
}

export const ENV_CONFIG = new InjectionToken<EnvironmentConfig>('EnvironmentConfig');
