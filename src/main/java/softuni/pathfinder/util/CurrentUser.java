package softuni.pathfinder.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import softuni.pathfinder.model.entity.RoleEntity;
import softuni.pathfinder.model.entity.enums.LevelEnum;

import java.util.Set;

@Component
@SessionScope
public class CurrentUser {

    private Long id;
    private String username;
    private String password;
    private Integer age;
    private String fullName;
    private Set<RoleEntity> roles;
    private LevelEnum level;
    private boolean isLoggedIn;

    public Long getId() {
        return id;
    }

    public CurrentUser setId(Long id) {
        this.id = id;

        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;

        return this;
    }

    public String getPassword() {
        return password;
    }

    public CurrentUser setPassword(String password) {
        this.password = password;

        return this;
    }

    public Integer getAge() {
        return age;
    }

    public CurrentUser setAge(Integer age) {
        this.age = age;

        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public CurrentUser setFullName(String fullName) {
        this.fullName = fullName;

        return this;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public CurrentUser setRoles(Set<RoleEntity> roles) {
        this.roles = roles;

        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public CurrentUser setLevel(LevelEnum level) {
        this.level = level;

        return this;
    }

    public void clearRoles() {
        roles.clear();
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public CurrentUser setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;

        return this;
    }
}
