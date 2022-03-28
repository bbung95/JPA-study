package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.dto.BookForm;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items/")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("bookForm", new BookForm());

        return "items/createItemForm";
    }

    @PostMapping("/new")
    public String create(@Valid BookForm form , BindingResult result){

        if(result.hasErrors()){
            return "items/createItemForm";
        }

        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setIsbn(form.getIsbn());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());

        itemService.saveItem(book);

        return "redirect:/items/";
    }

    @GetMapping("/")
    public String list(Model model){

        List<Item> items = itemService.findItems();
        model.addAttribute("items" , items);

        return "items/itemList";
    }

}
