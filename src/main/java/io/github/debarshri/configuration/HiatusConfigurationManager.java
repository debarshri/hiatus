package io.github.debarshri.configuration;

import java.util.List;

public class HiatusConfigurationManager {

    private List<HiatusConfiguration> hiatusConfigurations;

    public HiatusConfigurationManager(List<HiatusConfiguration> hiatusConfigurations) {
        this.hiatusConfigurations = hiatusConfigurations;
    }

    public void addConfiguration(HiatusConfiguration hiatusConfiguration) {
        hiatusConfigurations.add(hiatusConfiguration);
    }
}
