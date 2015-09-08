package io.github.debarshri;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HiatusServer {

    public static void main(String[] args) {

        Spark.get("/", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "Hiatus is on..biatch";
            }
        });

        Spark.get("/list", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "";
            }
        });

    }
}
