package ch07_design_patterns.after.apec.final_prjct.repository.custom;

import ch07_design_patterns.after.apec.final_prjct.domain.Customer;import ch07_design_patterns.after.apec.final_prjct.enums.CustomerGradeEnum;
public class CustomerRepository {

    public Customer findById(Long customerId) {
        return new Customer(customerId, "test", CustomerGradeEnum.VIP);
    }
    
}
