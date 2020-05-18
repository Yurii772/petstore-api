package model;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Category {
    private String id;
    private String name;
}