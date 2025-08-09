// src/main/java/io/swagger/petstore/base/BaseApi.java
package io.swagger.petstore.base;

import io.restassured.specification.RequestSpecification;
import io.swagger.petstore.factory.ApiFactory;

public class BaseApi {
    protected final RequestSpecification spec;

    public BaseApi() {
        this.spec = ApiFactory.getApiClient();
    }
}