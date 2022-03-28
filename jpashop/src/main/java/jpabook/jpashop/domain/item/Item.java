package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.domain.ItemCategory;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "item" , cascade = CascadeType.ALL)
    private List<ItemCategory> itemCategorys = new ArrayList<>();

    @OneToMany(mappedBy = "item" , cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Item() {
    }

    public void addItemCategory(Category category){
        ItemCategory itemCategory= new ItemCategory();
        itemCategory.setCategory(category);
        itemCategory.setItem(this);

        this.itemCategorys.add(itemCategory);
    }

    //== 비즈니스 로직 ==//
    /*
    stock 증가
    */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /*
    stock 감소
    */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;

        System.out.println("stockQuantity = " + stockQuantity);
        System.out.println("quantity = " + quantity);

        if(restStock < 0){
            System.out.println("restStock = " + restStock);
            throw new NotEnoughStockException("need more stock");
        }

        this.stockQuantity = restStock;
    }
}
