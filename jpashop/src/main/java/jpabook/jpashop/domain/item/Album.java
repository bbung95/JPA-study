package jpabook.jpashop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ITEM")
public class Album extends Item{

    private String artist;
    private String etc;
}
