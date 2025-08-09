package io.swagger.petstore.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Represents a pet object
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    private Long id;
    private Category category;
    private String name;

    @JsonProperty("photoUrls")
    private List<String> photoUrls;

    private List<Tag> tags;
    private String status; // available | pending | sold
}