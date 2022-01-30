package homework;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    private final TreeMap<Customer, String> customerDataMap =
            new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        return getEntryClone(
                customerDataMap.firstEntry()
        );
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return getEntryClone(
                customerDataMap.tailMap(customer, false).firstEntry()
        );
    }

    public void add(Customer customer, String data) {
        customerDataMap.put(customer, data);
    }

    private Map.Entry<Customer,String> getEntryClone(Map.Entry<Customer,String> entry){
        return entry == null ? null :
                new AbstractMap.SimpleEntry<>(
                        new Customer(entry.getKey()),
                        entry.getValue()
                );
    }
}
