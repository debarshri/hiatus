package io.github.debarshri;

import io.github.debarshri.configuration.SyncStrategy;
import io.github.debarshri.table.HBaseTable;
import io.github.debarshri.table.ORATable;

import java.util.concurrent.TimeUnit;

public class HiatusAdmin {

    public void updateConf(String confLocation)
    {
        //todo
    }

    public void sync(HBaseTable hbaseTable, ORATable oracleTable)
    {
        //todo
    }

    public void sync(HBaseTable hbaseTable, ORATable oracleTable, TimeUnit timeUnit)
    {
        //todo
    }

    public void sync(HBaseTable hbaseTable, ORATable oracleTable, SyncStrategy syncStrategy)
    {
        //todo
    }

    public void lastUpdated(ORATable oraTable)
    {
        //todo
    }
}
