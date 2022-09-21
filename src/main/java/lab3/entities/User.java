package lab3.entities;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String username;
    private Integer salary;
    private Boolean isMarried;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Boolean isMarried() {
        return isMarried;
    }

    public void setMarried(Boolean married) {
        isMarried = married;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", salary=" + salary +
                ", isMarried=" + isMarried +
                '}';
    }
}
