package io.github.debarshri;

import io.github.debarshri.configuration.HiatusConfiguration;
import io.github.debarshri.configuration.HiatusConfigurationManager;
import io.github.debarshri.configuration.SyncStrategy;
import io.github.debarshri.parser.Parser;
import io.github.debarshri.table.HBaseTable;
import io.github.debarshri.table.Mapping;
import io.github.debarshri.table.ORATable;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.apache.commons.io.FileUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class HiatusShell {
    public static void main(String[] args) throws IOException, JSONException {

        OptionParser parser = Parser.parser();

        if (args.length < 1) {
            parser.printHelpOn(System.out);
            System.exit(1);
        }

        OptionSet parse = Parser.parser().parse(args);

        HiatusConfigurationManager hiatusConfigurationManager = null;

        if (parse.has("conf")) {
            String conf = parse.valueOf("conf").toString();
            hiatusConfigurationManager = readConf(conf);
        }
        String sqlConnection;

        if (parse.has("sql")) {
            sqlConnection = parse.valueOf("sql").toString();
        }

        //TODO
        System.out.println("Test connection and get table details");
        System.out.println("Select * from table_name where 1=1");

        //TODO Start streaming daemon
        //TODO setup admin
        HiatusAdmin hiatusAdmin = new HiatusAdmin(hiatusConfigurationManager);
        hiatusAdmin.invoke();

    }

    protected static HiatusConfigurationManager readConf(String conf) throws IOException, JSONException {
        HiatusConfigurationManager hiatusConfigurationManager = new HiatusConfigurationManager(new ArrayList<HiatusConfiguration>());

        JSONArray jsonObjects = new JSONArray(FileUtils.readFileToString(new File(conf)));

        for (int i = 0; i < jsonObjects.length(); i++) {
            JSONObject jsonObject = jsonObjects.getJSONObject(i);
            Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                String configurationName = keys.next().toString();
                JSONObject innerConfiguration = jsonObject.getJSONObject(configurationName);
                System.out.println(innerConfiguration);

                HBaseTable hBaseTable = new HBaseTable(innerConfiguration.getString("source"));
                ORATable oraTable = new ORATable(innerConfiguration.getString("target"));

                JSONObject mapping = innerConfiguration.getJSONObject("mapping");

                System.out.println(mapping);

                Iterator keys1 = mapping.keys();

                Mapping mappings = new Mapping(new HashMap<String, String>(), new HashMap<String, String>());

                while (keys1.hasNext()) {
                    String key = keys1.next().toString();
                    System.out.println(key);
                    mappings.add(key, mapping.getString(key));
                }

                SyncStrategy syncStrategy;
                boolean upsert;

                if (innerConfiguration.has("sync")) {
                    if (innerConfiguration.getString("sync").toUpperCase().equals("ASAP")) {
                        syncStrategy = SyncStrategy.ASAP;
                    } else if (innerConfiguration.getString("sync").toUpperCase().equals("FAST_REFRESH")) {
                        syncStrategy = SyncStrategy.FAST_REFRESH;
                    } else if (innerConfiguration.getString("sync").toUpperCase().equals("BULK_REFRESH")) {
                        syncStrategy = SyncStrategy.BULK_REFRESH;
                    } else if (innerConfiguration.getString("sync").toUpperCase().equals("FULL_REFRESH")) {
                        syncStrategy = SyncStrategy.FULL_REFRESH;
                    } else {
                        syncStrategy = SyncStrategy.ASAP;
                    }
                } else {
                    syncStrategy = SyncStrategy.ASAP;
                }

                if (innerConfiguration.has("upsert")) {
                    upsert = innerConfiguration.getBoolean("upsert");
                } else {
                    upsert = false;
                }

                HiatusConfiguration hiatusConfiguration = new HiatusConfiguration(configurationName,
                        oraTable,
                        hBaseTable,
                        mappings,
                        syncStrategy,
                        upsert);

                hiatusConfigurationManager.addConfiguration(hiatusConfiguration);
            }
        }

        return hiatusConfigurationManager;
    }
}
