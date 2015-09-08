package io.github.debarshri.configuration;

import io.github.debarshri.table.Mapping;
import io.github.debarshri.table.HBaseTable;
import io.github.debarshri.table.ORATable;

public class HiatusConfiguration {
    private String configurationName;
    private ORATable oraTable;
    private HBaseTable hBaseTable;
    private Mapping mapping;
    private SyncStrategy syncStrategy;
    private boolean upsert;

    public HiatusConfiguration(String configurationName, ORATable oraTable,
                               HBaseTable hBaseTable,
                               Mapping mapping,
                               SyncStrategy syncStrategy,
                               boolean upsert) {
        this.configurationName = configurationName;
        this.oraTable = oraTable;
        this.hBaseTable = hBaseTable;
        this.mapping = mapping;
        this.syncStrategy = syncStrategy;
        this.upsert = upsert;
    }

    public ORATable getOraTable() {
        return oraTable;
    }

    public void setOraTable(ORATable oraTable) {
        this.oraTable = oraTable;
    }

    public HBaseTable gethBaseTable() {
        return hBaseTable;
    }

    public void sethBaseTable(HBaseTable hBaseTable) {
        this.hBaseTable = hBaseTable;
    }

    public Mapping getMapping() {
        return mapping;
    }

    public void setMapping(Mapping mapping) {
        this.mapping = mapping;
    }

    public SyncStrategy getSyncStrategy() {
        return syncStrategy;
    }

    public boolean isUpsert() {
        return upsert;
    }

    public String getConfigurationName() {
        return configurationName;
    }
}
