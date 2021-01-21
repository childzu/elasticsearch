package elasticsearch.sample.elasticsearch.repo;

import elasticsearch.sample.elasticsearch.data.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import static org.elasticsearch.index.query.QueryBuilders.*;

import java.util.List;

@EnableElasticsearchRepositories
public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {
    Page<Customer> findAll();
    List<Customer> findByFirstName(String firstName);
}
