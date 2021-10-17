package softuni.pathfinder.model.entity;

import softuni.pathfinder.model.entity.enums.CategoryNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

    private CategoryNameEnum name;
    private String description;

    public CategoryEntity() {
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    public CategoryNameEnum getName() {
        return name;
    }

    public void setName(CategoryNameEnum name) {
        this.name = name;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
