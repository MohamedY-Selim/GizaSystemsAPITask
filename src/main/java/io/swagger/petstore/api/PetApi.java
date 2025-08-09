// src/main/java/io/swagger/petstore/api/PetApi.java
package io.swagger.petstore.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.swagger.petstore.base.BaseApi;
import io.swagger.petstore.config.EndPoint;
import io.swagger.petstore.objects.Pet;

import static io.restassured.RestAssured.given;

public class PetApi extends BaseApi {

    public PetApi() { super(); }

    public Response getPetById(long petId) {
        return given()
                .spec(spec)
                .log().all()
                .when()
                .get(EndPoint.GET_PET_BY_ID.getPath(petId))
                .then()
                .log().all()
                .extract().response();
    }

    public Response findPetsByStatus(String status) {
        return given()
                .spec(spec)
                .log().all()
                .queryParam("status", status)
                .when()
                .get(EndPoint.FIND_BY_STATUS.getPath())
                .then()
                .log().all()
                .extract().response();
    }

    public Response createPet(Pet pet) {
        return given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all()
                .body(pet)
                .when()
                .post(EndPoint.ADD_PET.getPath())
                .then()
                .log().all()
                .extract().response();
    }
}