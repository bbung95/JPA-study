package jpabook.jpashop.domain;

import jpabook.jpashop.enums.DeliveryStatus;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Delivery {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "delivery_id")
    private Long id;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @OneToOne(mappedBy = "delivery" , fetch = FetchType.LAZY)
    private Order order;

    public Delivery() {
    }

    public Delivery(Long id, Address address, DeliveryStatus deliveryStatus, Order order) {
        this.id = id;
        this.address = address;
        this.deliveryStatus = deliveryStatus;
        this.order = order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
