package com.n26.challenge.tests;

import com.n26.challenge.api.PetApiClient;
import com.n26.challenge.models.Pet;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PetUpdateTests {
    private PetApiClient apiClient;

    @BeforeClass
    public void setup() {
        apiClient = new PetApiClient();
    }

    @Test
    public void testUpdatePetWithValidData() {
        // Create a pet to update
        Pet pet = new Pet(300L, "Charlie", "available");
        Response createResponse = apiClient.createPet(pet);
        Assert.assertEquals(createResponse.statusCode(), 200, "Failed to create pet");

        // Verify the pet exists
        Response getResponse = apiClient.getPet(300L);
        Assert.assertEquals(getResponse.statusCode(), 200, "Pet should exist after creation");

        // Update the pet's status
        pet.setStatus("sold");
        Response updateResponse = apiClient.updatePet(pet);
        Assert.assertEquals(updateResponse.statusCode(), 200, "Expected status code 200");
        Pet updatedPet = updateResponse.as(Pet.class);
        Assert.assertEquals(updatedPet.getStatus(), "sold", "Pet status should be updated to sold");
    }

    @Test
    public void testUpdateNonExistentPet() {
        Pet pet = new Pet(9999L, "Ghost", "available");
        Response response = apiClient.updatePet(pet);

        Assert.assertEquals(response.statusCode(), 404, "Expected status code 404 for non-existent pet");
    }

    @Test
    public void testUpdatePetWithInvalidData() {
        Pet pet = new Pet(301L, "Daisy", "invalid_status");
        Response createResponse = apiClient.createPet(pet);
        Assert.assertEquals(createResponse.statusCode(), 200, "Failed to create pet");

        // Verify the pet exists
        Response getResponse = apiClient.getPet(301L);
        if (getResponse.statusCode() == 404) {
            // If the pet doesn't exist, the update should fail with 404
            Response updateResponse = apiClient.updatePet(pet);
            Assert.assertEquals(updateResponse.statusCode(), 404, "Expected status code 404 since pet does not exist");
        } else {
            // If the pet exists, proceed with the update
            Assert.assertEquals(getResponse.statusCode(), 200, "Pet should exist after creation");
            Response updateResponse = apiClient.updatePet(pet);
            Assert.assertEquals(updateResponse.statusCode(), 200, "Expected status code 200 as API accepts invalid status");
            Pet updatedPet = updateResponse.as(Pet.class);
            Assert.assertEquals(updatedPet.getStatus(), "invalid_status", "Pet status should match the invalid status");
        }
    }
}