package apec.test8;

import static apec.test8.CustomerGradeEnum.NORMAL;

public class CustomerRepository {
    public Customer findById(Long id) {
        return new Customer(id, "Customer Name 1", NORMAL);
    }
}
