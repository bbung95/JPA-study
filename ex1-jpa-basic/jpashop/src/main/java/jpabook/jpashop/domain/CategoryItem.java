package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORT_ITEM")
public class CategoryItem {

    @Id @GeneratedValue
    @Column(name = "CATTEGORY_ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
