package elasticsearch.sample.elasticsearch.data;


import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "customer")
public class Customer {
    @Id
    private String id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Integer age;

    @NotNull
    private String email;

}
