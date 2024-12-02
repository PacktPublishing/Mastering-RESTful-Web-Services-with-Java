package com.packt.ordermanagementapi.integration;

import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class CreateOrderSimulation extends Simulation {

    private static final HttpProtocolBuilder HTTP_PROTOCOL_BUILDER = setupProtocolForSimulation();
    private static final ScenarioBuilder POST_SCENARIO_BUILDER = buildCreateOrderScenario();

    public CreateOrderSimulation() {
        setUp(POST_SCENARIO_BUILDER.injectOpen(rampUsersPerSec(10).to(300).during(Duration.ofSeconds(10)),
                        constantUsersPerSec(300).during(Duration.ofSeconds(80)))
                .protocols(HTTP_PROTOCOL_BUILDER))
                .assertions(
                        global().responseTime().max().lte(5000),
                        global().successfulRequests().percent().gt(90d));
    }

    private static HttpProtocolBuilder setupProtocolForSimulation() {
        return HttpDsl.http.baseUrl("http://localhost:8090")
                .acceptHeader("application/json")
                .maxConnectionsPerHost(10)
                .userAgentHeader("Gatling/Performance Test");
    }

    private static ScenarioBuilder buildCreateOrderScenario() {
        return CoreDsl.scenario("Load Test Creating Orders")
                .exec(http("create-order-request").post("/orders")
                        .header("Content-Type", "application/json")
                        .body(StringBody("""
                                {
                                   "customer": {
                                     "streetAddress": "string",
                                     "city": "string",
                                     "postalCode": "string",
                                     "customerType": "company"
                                   },
                                   "products": [
                                     {
                                       "productSKU": "AK21101",
                                       "quantity": 1
                                     }
                                   ]
                                 }
                                """))
                        .check(status().is(201)));
    }

}
