package io.github.debarshri;

import io.github.debarshri.configuration.HiatusConfiguration;
import io.github.debarshri.configuration.HiatusConfigurationManager;
import org.codehaus.jackson.map.ObjectMapper;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HiatusServer {

    public static void main(String[] args) {
        final ObjectMapper objectMapper = new ObjectMapper();
        Spark.port(9080);

        final Controller controller = new Controller();

        Spark.get("/", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "Hiatus server, online. Biatch.";
            }
        });

        Spark.post("/add", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                HiatusConfigurationManager hiatusConfigurationManager = objectMapper.readValue(request.body(), HiatusConfigurationManager.class);
                controller.addAll(hiatusConfigurationManager.getHiatusConfigurations());

                return hiatusConfigurationManager.getHiatusConfigurations();
            }
        });

        Spark.get("/list", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                response.type("application/json");
                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(controller.getConfigurations());
            }
        });

        Spark.get("/conf/:conf", new Route() {
            @Override
            public Object handle(final Request request, Response response) throws Exception {
                response.type("application/json");

                Optional<HiatusConfiguration> conf = controller.getConfigurations().stream().filter(new Predicate<HiatusConfiguration>() {
                    @Override
                    public boolean test(HiatusConfiguration hiatusConfiguration) {
                        return hiatusConfiguration.getConfigurationName().equals(request.params("conf"));
                    }
                }).findFirst();

                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(conf.get());
            }
        });

        Spark.get("/oracle/:tableName", new Route() {
            @Override
            public Object handle(final Request request, Response response) throws Exception {
                response.type("application/json");

                Stream<HiatusConfiguration> conf = controller.getConfigurations().stream().filter(new Predicate<HiatusConfiguration>() {
                    @Override
                    public boolean test(HiatusConfiguration hiatusConfiguration) {
                        return hiatusConfiguration.getOraTable().getTableName().equals(request.params("tableName"));
                    }
                }).distinct();

                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(conf.collect(Collectors.toList()));
            }
        });
    }
}
