package apec.final_prjct.repository.custom;

import apec.final_prjct.domain.Customer;
import apec.final_prjct.enums.CustomerGradeEnum;

public class CustomerRepository {

    public Customer findById(Long customerId) {
        return new Customer(customerId, "test", CustomerGradeEnum.VIP);
    }
    
}
