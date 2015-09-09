package io.github.debarshri;

import io.github.debarshri.configuration.HiatusConfiguration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Controller {

    private Set<HiatusConfiguration> hiatusConfigurationManager;
    private Object configurations;

    public Controller() {
        this.hiatusConfigurationManager = new HashSet<HiatusConfiguration>();
    }

    public void addAll(List<HiatusConfiguration> hiatusConfigurationManager) {

        this.hiatusConfigurationManager.addAll(hiatusConfigurationManager);
    }

    public Set<HiatusConfiguration> getConfigurations() {
        return hiatusConfigurationManager;
    }
}
