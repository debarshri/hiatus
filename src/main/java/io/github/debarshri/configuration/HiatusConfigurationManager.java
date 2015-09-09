package io.github.debarshri.configuration;

import java.io.Serializable;
import java.util.List;

public class HiatusConfigurationManager implements Serializable {

    public List<HiatusConfiguration> getHiatusConfigurations() {
        return hiatusConfigurations;
    }

    private List<HiatusConfiguration> hiatusConfigurations;

    public HiatusConfigurationManager() {
        //required for deserialization
    }

    public HiatusConfigurationManager(List<HiatusConfiguration> hiatusConfigurations) {
        this.hiatusConfigurations = hiatusConfigurations;
    }

    public void addConfiguration(HiatusConfiguration hiatusConfiguration) {
        hiatusConfigurations.add(hiatusConfiguration);
    }
}
