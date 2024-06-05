package com.customerwebsite.customer.website.BatchConfig;

import com.customerwebsite.customer.website.Models.CustomUserDetails;
import com.customerwebsite.customer.website.Models.Customer;
import com.customerwebsite.customer.website.Models.Role;
import com.customerwebsite.customer.website.Models.Snowboard;
import com.customerwebsite.customer.website.Repositories.CustomerRepository;
import com.customerwebsite.customer.website.Repositories.RoleRepository;
import com.customerwebsite.customer.website.Repositories.SnowboardRepository;
import com.customerwebsite.customer.website.Repositories.UserRepository;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class Readers {
    @Bean
    public FlatFileItemReader<Snowboard> SnowCsvReader(@Value("${snowFile}") String inputFile){
        return new FlatFileItemReaderBuilder<Snowboard>()
                .name("csv-reader")
                .resource(new ClassPathResource(inputFile))
                .delimited()
                .names("id","type","brand","name","customer_id")
                .linesToSkip(1)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){
                    {setTargetType(Snowboard.class);}
                })
                .build();

    }

    @Bean
    public RepositoryItemReader<Snowboard> SnowRepositoryReader(SnowboardRepository snowboardRepository){
        return new RepositoryItemReaderBuilder<Snowboard>()
                .repository(snowboardRepository)
                .methodName("findAll")
                .sorts(Map.of("id", Sort.Direction.ASC))
                .name("repository-reader")
                .build();
    }

    @Bean
    public FlatFileItemReader<Role> RoleCsvReader(@Value("${roleFile}") String inputFile){
        return new FlatFileItemReaderBuilder<Role>()
                .name("csv-reader")
                .resource(new ClassPathResource(inputFile))
                .delimited()
                .names("id","role","user_id")
                .linesToSkip(1)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){
                    {setTargetType(Role.class);}
                })
                .build();

    }

    @Bean
    public RepositoryItemReader<Role> RoleRepositoryReader(RoleRepository roleRepository){
        return new RepositoryItemReaderBuilder<Role>()
                .repository(roleRepository)
                .methodName("findAll")
                .sorts(Map.of("id", Sort.Direction.ASC))
                .name("repository-reader")
                .build();
    }

    @Bean
    public FlatFileItemReader<CustomUserDetails> UserCsvReader(@Value("${userFile}") String inputFile){
        return new FlatFileItemReaderBuilder<CustomUserDetails>()
                .name("csv-reader")
                .resource(new ClassPathResource(inputFile))
                .delimited()
                .names("id","username","password","is_account_non_expired","is_account_non_locked","is_credentials_non_expired","is_enabled,customer_id")
                .linesToSkip(1)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){
                    {setTargetType(CustomUserDetails.class);}
                })
                .build();

    }

    @Bean
    public RepositoryItemReader<CustomUserDetails> UserRepositoryReader(UserRepository userRepository){
        return new RepositoryItemReaderBuilder<CustomUserDetails>()
                .repository(userRepository)
                .methodName("findAll")
                .sorts(Map.of("id", Sort.Direction.ASC))
                .name("repository-reader")
                .build();
    }

    @Bean
    public FlatFileItemReader<Customer> CustomerCsvReader(@Value("${customerFile}") String inputFile){
        return new FlatFileItemReaderBuilder<Customer>()
                .name("csv-reader")
                .resource(new ClassPathResource(inputFile))
                .delimited()
                .names("id","address","age","email_address","full_name")
                .linesToSkip(1)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){
                    {setTargetType(Customer.class);}
                })
                .build();

    }

    @Bean
    public RepositoryItemReader<Customer> CustomerRepositoryReader(CustomerRepository customerRepository){
        return new RepositoryItemReaderBuilder<Customer>()
                .repository(customerRepository)
                .methodName("findAll")
                .sorts(Map.of("id", Sort.Direction.ASC))
                .name("repository-reader")
                .build();
    }
}
