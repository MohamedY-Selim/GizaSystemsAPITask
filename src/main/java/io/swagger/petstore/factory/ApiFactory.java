package io.swagger.petstore.factory;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.swagger.petstore.utils.ConfigUtils;

public class ApiFactory {

    private static final ThreadLocal<RequestSpecification> apiClient = ThreadLocal.withInitial(() -> {
        String baseUrl = ConfigUtils.getInstance().getProperty("api_base_url");
        String basePath = ConfigUtils.getInstance().getProperty("api_base_path");
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .setRelaxedHTTPSValidation()
                .addFilter(new AllureRestAssured())
                .build();
    });

    public static RequestSpecification getApiClient() {
        return apiClient.get();
    }

    public static void resetApiClient() {
        apiClient.remove();
    }
}