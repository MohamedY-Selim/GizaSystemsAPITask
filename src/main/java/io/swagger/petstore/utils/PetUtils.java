package io.swagger.petstore.utils;

import com.github.javafaker.Faker;
import io.swagger.petstore.objects.Category;
import io.swagger.petstore.objects.Pet;
import io.swagger.petstore.objects.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PetUtils {
    private static final Faker faker = new Faker();
    private static final Random random = new Random();
    private static final String[] STATUSES = {"available", "pending", "sold"};

    public static Pet randomPet() {
        long id = randomId();
        return new Pet(
                id,
                randomCategory(),
                "pet-" + faker.animal().name().replace(' ', '-') + "-" + id,
                randomPhotoUrls(1 + random.nextInt(3)),
                randomTags(1 + random.nextInt(3)),
                randomStatus()
        );
    }

    public static String randomStatus() {
        return STATUSES[random.nextInt(STATUSES.length)];
    }

    public static Category randomCategory() {
        long id = randomId();
        return new Category(id, faker.animal().name());
    }

    public static List<Tag> randomTags(int count) {
        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            tags.add(new Tag(randomId(), faker.color().name()));
        }
        return tags;
    }

    public static List<String> randomPhotoUrls(int count) {
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            urls.add("https://picsum.photos/seed/" + faker.internet().uuid() + "/600/400");
        }
        return urls;
    }

    public static long randomId() {
        return Math.abs(random.nextLong() % 1_000_000_000L);
    }
}