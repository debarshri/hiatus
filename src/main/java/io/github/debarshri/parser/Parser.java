package io.github.debarshri.parser;

import joptsimple.OptionParser;

public class Parser {
    public static OptionParser parser() {
        OptionParser parser = new OptionParser();

        parser.accepts("conf", "Use as --conf=<args>.\n Location of the configuration.")
                .withRequiredArg();
        parser.accepts("sql", "Use as --sql=<args>. \n Connection String to sql.")
                .withRequiredArg();
        parser.accepts("hbase", "Use as --hbase=<quorom1>,<quorom2>,<quorom3>. \n Connection zookeeper quorams to hbase.")
                .withRequiredArg();
        parser.accepts("status", "Use as --running. \n Status of hiatus.");
        parser.accepts("restart_server", "Use as --restart_server. \n Restarts server.");
        return parser;
    }
}
