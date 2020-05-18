package model;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Order {
    private int id;
    private String petId;
    private String quantity;
    private long shipDate;
    private OrderStatus status;
    private Boolean complete;

}


