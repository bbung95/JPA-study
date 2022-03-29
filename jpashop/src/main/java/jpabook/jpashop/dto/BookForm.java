package jpabook.jpashop.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookForm {

    private Long id;

    @NotEmpty(message = "상품명을 입력해주세요")
    private String name;

    @NotNull(message = "가격을 입력해주세요")
    @Min(1000)
    private int price;

    @NotNull(message = "수량을 입력해주세요")
    @Min(1)
    private int stockQuantity;

    private String author;
    private String isbn;



}
