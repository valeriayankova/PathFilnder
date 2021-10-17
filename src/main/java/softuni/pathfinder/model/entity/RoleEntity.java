package softuni.pathfinder.model.entity;

import softuni.pathfinder.model.entity.enums.RoleNameEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    private RoleNameEnum role;

    public RoleEntity() {
    }

    @Enumerated(EnumType.STRING)
    public RoleNameEnum getRole() {
        return role;
    }

    public void setRole(RoleNameEnum name) {
        this.role = name;
    }
}
