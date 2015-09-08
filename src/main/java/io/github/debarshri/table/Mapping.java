package io.github.debarshri.table;

import java.util.Map;

public class Mapping {
    private Map<String, String> oraToHBase;
    private Map<String, String> hbaseToOra;

    public Mapping(Map<String, String> oraToHBase, Map<String, String> hbaseToOra) {
        this.oraToHBase = oraToHBase;
        this.hbaseToOra = hbaseToOra;
    }

    public String getHbaseCol(String oraCol) {
        return oraToHBase.get(oraCol);
    }

    public String getOraCol(String hbaseCol) {
        return hbaseToOra.get(hbaseCol);
    }

    public void add(String hbaseColumn, String oracleColumn) {

        oraToHBase.put(oracleColumn, hbaseColumn);
        hbaseToOra.put(hbaseColumn, oracleColumn);

    }
}
