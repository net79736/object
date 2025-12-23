package apec.final_prjct.domain;

import apec.final_prjct.enums.CustomerGradeEnum;

public class Customer {
    private Long id;
    private String name;
    private CustomerGradeEnum grade;

    public Customer(Long id, String name, CustomerGradeEnum grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public CustomerGradeEnum getGrade() {
        return grade;
    }

    public boolean isSalesGrade() {
        return grade.isVip() || grade.isGold();
    }
}
