package lab3;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private int salary;
    boolean isMarried;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", salary=" + salary +
                ", isMarried=" + isMarried +
                '}';
    }
}
