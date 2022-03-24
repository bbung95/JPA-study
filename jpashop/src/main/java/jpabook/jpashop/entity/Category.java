package jpabook.jpashop.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<ItemCategory> itemCategorys = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> childs = new ArrayList<>();

    public void addItemCategory (ItemCategory itemCategory){
        itemCategorys.add(itemCategory);
        itemCategory.setCategory(this);
    }

    public void addChildCategory(Category child){
        childs.add(child);
        child.setParent(this);
    }

    public void setParent(Category parent){
        this.parent = parent;
    }
}
