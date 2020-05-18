package model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Pet {
    private String id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private Tags[] tags;
    private Status status;
}