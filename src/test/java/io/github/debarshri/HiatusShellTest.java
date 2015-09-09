package io.github.debarshri;

import io.github.debarshri.configuration.HiatusConfigurationManager;
import org.codehaus.jettison.json.JSONException;
import org.junit.Test;

import java.io.IOException;

import static io.github.debarshri.HiatusShell.*;

public class HiatusShellTest {

    @Test
    public void readConfiguration() throws IOException, JSONException {

        HiatusConfigurationManager hiatusConfigurationManager = readConf("conf/table.hiatus");

        HiatusAdmin hiatusAdmin = new HiatusAdmin(hiatusConfigurationManager);

        hiatusAdmin.invoke();

    }
}