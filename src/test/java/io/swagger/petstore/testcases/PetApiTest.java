package io.swagger.petstore.testcases;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.swagger.petstore.api.PetApi;
import io.swagger.petstore.base.BaseTest;
import io.swagger.petstore.objects.Pet;
import io.swagger.petstore.utils.PetUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class PetApiTest extends BaseTest {

    private PetApi petApi;
    private final Random random = new Random();

    @BeforeClass
    public void setupTest() {
        super.setup();
        petApi = new PetApi();
    }

    @Test(description = "Create new pet and validate response")
    public void createPet_shouldSucceed() {
        Pet newPet = PetUtils.randomPet();

        Response resp = petApi.createPet(newPet);
        assertStatus(resp, 200, 201);

        JsonPath jp = resp.jsonPath();
        Long createdPetId = jp.getLong("id");
        String createdPetName = jp.getString("name");

        Assert.assertNotNull(createdPetId, "Pet id is null");
        Assert.assertEquals(createdPetName, newPet.getName(), "Pet name mismatch");
    }

    @Test(description = "Get random pet by id (picked from findByStatus) and extract its name")
    public void getPetById_shouldReturnName() {
        Response listResp = petApi.findPetsByStatus("available");
        assertStatus(listResp, 200);

        List<Long> ids = listResp.jsonPath().getList("id", Long.class);
        Assert.assertNotNull(ids, "IDs list is null");
        Assert.assertFalse(ids.isEmpty(), "No pets returned for status=available");

        Long pickedId = ids.get(random.nextInt(ids.size()));

        Response getResp = petApi.getPetById(pickedId);
        assertStatus(getResp, 200);

        String name = getResp.jsonPath().getString("name");
        Assert.assertNotNull(name, "Returned name is null");
        System.out.println("Pet id: " + pickedId + " | name: " + name);
    }

    @Test(description = "Find pets by status and collect names")
    public void findByStatus_shouldReturnNames() {
        Response resp = petApi.findPetsByStatus("available");
        assertStatus(resp, 200);

        List<String> names = resp.jsonPath().getList("name");
        Assert.assertNotNull(names, "Names list is null");
        Assert.assertFalse(names.isEmpty(), "No pets returned for status=available");

        System.out.println("Sample names: " + names.stream().limit(5).toList());
    }
}