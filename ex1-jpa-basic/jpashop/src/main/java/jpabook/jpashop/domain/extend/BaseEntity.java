package jpabook.jpashop.domain.extend;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    private LocalDateTime createdDate;

    private LocalDateTime lastModifedDate;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifedDate() {
        return lastModifedDate;
    }

    public void setLastModifedDate(LocalDateTime lastModifedDate) {
        this.lastModifedDate = lastModifedDate;
    }
}
