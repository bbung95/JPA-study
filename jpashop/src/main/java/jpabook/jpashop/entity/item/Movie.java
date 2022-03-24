package jpabook.jpashop.entity.item;

import lombok.Getter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("MOVIE")
public class Movie extends Item{

    private String director;
    private String actor;
}
