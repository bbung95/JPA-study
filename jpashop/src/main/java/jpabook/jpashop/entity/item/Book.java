package jpabook.jpashop.entity.item;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("BOOK")
public class Book extends Item{

    private String author;
    private String isbn;
}
