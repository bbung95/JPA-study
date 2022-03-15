package hellojpa.extend;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    private String createBy;
    private LocalDateTime createdDate;
    private String lastModifedBy;
    private LocalDateTime lastModifedDate;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifedBy() {
        return lastModifedBy;
    }

    public void setLastModifedBy(String lastModifedBy) {
        this.lastModifedBy = lastModifedBy;
    }

    public LocalDateTime getLastModifedDate() {
        return lastModifedDate;
    }

    public void setLastModifedDate(LocalDateTime lastModifedDate) {
        this.lastModifedDate = lastModifedDate;
    }
}
