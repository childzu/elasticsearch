package elasticsearch.sample.elasticsearch.service;

import elasticsearch.sample.elasticsearch.data.Customer;
import elasticsearch.sample.elasticsearch.repo.CustomerRepository;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {
    private CustomerRepository repository;

    @Autowired
    private ElasticsearchRestTemplate esTemplate;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> retrieveCustomers() {
        return repository.findAll().getContent();
    }

    public Optional<Customer> retrieveCustomers(String id) {
        return repository.findById(id);
    }

    public List<Customer> retrieveCustomersByName(String name) {
        return repository.findByFirstName(name);
    }

    public Customer createCustomer(Customer customer) {
        return repository.save(customer);
    }

    public Optional<Customer> updateCustomer(String id, Customer customer) {
        Optional<Customer> customerOpt = repository.findById(id);
        if(!customerOpt.isPresent()) {
            return customerOpt;
        }
        customer.setId(id);
        return Optional.of(repository.save(customer));
    }

    public boolean deleteCustomer(String id) {
        try {
            repository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public SearchHits<Customer> searchCustomer(String query) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withFilter(QueryBuilders.queryStringQuery(query)).build();
        SearchHits<Customer> customers = this.esTemplate.search(searchQuery, Customer.class);
        return customers;
    }
}
