package com.customerwebsite.customer.website.BatchConfig;

import com.customerwebsite.customer.website.Models.CustomUserDetails;
import com.customerwebsite.customer.website.Models.Customer;
import com.customerwebsite.customer.website.Models.Role;
import com.customerwebsite.customer.website.Models.Snowboard;
import com.customerwebsite.customer.website.Repositories.SnowboardRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.*;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;

@Configuration

public class BatchConfiguration {

    @Bean
    public Job job(JobRepository jobRepository, Step snowStep, Step userStep, Step roleStep, Step customerStep) {

        return new JobBuilder("data-loader-job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(snowStep)
                .next(roleStep)
                .next(userStep)
                .next(customerStep)
                .build();


    }

    //steps
    @Bean
    public Step snowStep(JobRepository jobRepository, ItemReader<Snowboard> csvReader, Processors.BoardProcessor processor, Writers.BoardWriter writer, PlatformTransactionManager transactionManager){
        return new StepBuilder("snow-step", jobRepository)
                .<Snowboard, Snowboard>chunk(1000,transactionManager)
                .reader(csvReader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step userStep(JobRepository jobRepository, ItemReader<Customer> csvReader, Processors.UserProcessor processor, userWriter writer, PlatformTransactionManager transactionManager){
        return new StepBuilder("user-step", jobRepository)
                .<Customer, Customer>chunk(1000,transactionManager)
                .reader(csvReader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step roleStep(JobRepository jobRepository, ItemReader<Role> csvReader, Processors.RoleProcessor processor, RoleWriter writer, PlatformTransactionManager transactionManager){
        return new StepBuilder("role-step", jobRepository)
                .<Role, Role>chunk(1000,transactionManager)
                .reader(csvReader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step customerStep(JobRepository jobRepository, ItemReader<CustomUserDetails> csvReader, CustomerProcessor processor, CustomerWriter writer, PlatformTransactionManager transactionManager){
        return new StepBuilder("customer-step", jobRepository)
                .<Snowboard, Snowboard>chunk(1000,transactionManager)
                .reader(csvReader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    //Item readers
    @Bean
    public FlatFileItemReader<Snowboard> csvReader(@Value("${snowFile}") String inputFile){
        return new FlatFileItemReaderBuilder<Snowboard>()
                .name("csv-reader")
                .resource(new ClassPathResource(inputFile))
                .delimited()
                .names("id","type","brand,name","customer_id")
                .linesToSkip(1)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){
                    {setTargetType(Snowboard.class);}
                })
                .build();

    }

    @Bean
    public RepositoryItemReader<Snowboard> repositoryReader(SnowboardRepository snowboardRepository){
        return new RepositoryItemReaderBuilder<Snowboard>()
                .repository(snowboardRepository)
                .methodName("findAll")
                .sorts(Map.of("id", Sort.Direction.ASC))
                .name("repository-reader")
                .build();
    }


}
