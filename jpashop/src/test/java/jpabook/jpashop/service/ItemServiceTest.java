package jpabook.jpashop.service;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.domain.ItemCategory;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void saveItemTest() throws Exception {
        //given
        Book book = getBook();

        //when
        itemService.saveItem(book);

        //then
        assertEquals(book , itemService.findById(book.getId()));
    }
    
    @Test
    public void stockQuantityAddTest() throws Exception {
        //given
        Book book = getBook();

        //when
        itemService.saveItem(book);
        book.addStock(50);

        //then
        assertEquals(book.getStockQuantity() , itemService.findById(book.getId()).getStockQuantity());
    }

    public Book getBook(){
        Category category = new Category();
        category.setName("TestCat");

        Book book = new Book();
        book.setName("TestItem");
        book.setPrice(100000);
        book.setStockQuantity(100);
        book.setAuthor("bbung95");
        book.setIsbn("123");

        book.addItemCategory(category);

        return book;
    }
}