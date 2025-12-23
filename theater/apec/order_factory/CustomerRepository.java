package apec.order_factory;

import static apec.order_factory.CustomerGradeEnum.NORMAL;

public class CustomerRepository {
    public Customer findById(Long id) {
        return new Customer(id, "Customer Name 1", NORMAL);
    }
}
