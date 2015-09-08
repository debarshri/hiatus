package io.github.debarshri;

import org.codehaus.jettison.json.JSONException;
import org.junit.Test;

import java.io.IOException;

import static io.github.debarshri.HiatusShell.*;

public class HiatusShellTest {

    @Test
    public void readConfiguration() throws IOException, JSONException {

        readConf("conf/table.hiatus");

    }
}