package businesslogic;

import domain.Customer;
import domain.CustomerTable;
import java.time.LocalDate;
import java.time.LocalTime;

public class CustomerManager {

    public Customer findCustomer(int bsn){
        Customer customer = new Customer(bsn, "ids", "straat", "5959hj");
        return customer;
    }
    public CustomerTable findCustomerTable(int bsn){
        CustomerTable table = new CustomerTable(LocalDate.now(), LocalTime.now(), "hand");
        return table;
    }
}