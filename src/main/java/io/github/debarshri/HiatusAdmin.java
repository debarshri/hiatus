package io.github.debarshri;

import io.github.debarshri.configuration.HiatusConfigurationManager;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class HiatusAdmin {

    private HiatusConfigurationManager hiatusConfigurationManager;

    public HiatusAdmin(HiatusConfigurationManager hiatusConfigurationManager) {

        this.hiatusConfigurationManager = hiatusConfigurationManager;
    }

    public void invoke() {
        try {
            Request request = Request.Post("http://localhost:9080/add").bodyString(convert(hiatusConfigurationManager), ContentType.APPLICATION_JSON);
            Response execute = request.execute();
            System.out.println(execute.returnContent().asString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String convert(HiatusConfigurationManager hiatusConfigurations) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(hiatusConfigurations);
    }
}
