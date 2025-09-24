package com.architecturelab.hexagonal.infrastructure.config;

import com.architecturelab.hexagonal.domain.model.Product;
import com.architecturelab.hexagonal.infrastructure.repository.ProductRepositoryAdapter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
public class BatchConfig {

    private final ProductRepositoryAdapter repositoryAdapter;

    public BatchConfig(ProductRepositoryAdapter repositoryAdapter) {
        this.repositoryAdapter = repositoryAdapter;
    }

    // 1. Reader
    @Bean
    public ItemReader<Product> reader() {
        return new FlatFileItemReaderBuilder<Product>()
                .name("productItemReader")
                .resource(new ClassPathResource("products.csv"))
                .delimited()
                .names("name", "price")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Product.class);
                }})
                .build();
    }

    // 2. Processor
    @Bean
    public ItemProcessor<Product, Product> processor() {
        return product -> {
            product.setName(product.getName().toUpperCase());
            return product;
        };
    }

    // 3. Writer
    @Bean
    public ItemWriter<Product> writer() {
        return chunk -> repositoryAdapter.saveAll((List<Product>) (List<?>) chunk.getItems());
    }




    // 4. Step
    @Bean
    public Step importStep(JobRepository jobRepository,
                           PlatformTransactionManager transactionManager,
                           ItemReader<Product> reader,
                           ItemProcessor<Product, Product> processor,
                           ItemWriter<Product> writer) {
        return new StepBuilder("importStep", jobRepository)
                .<Product, Product>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    // 5. Job
    @Bean
    public Job importJob(JobRepository jobRepository, Step importStep) {
        return new JobBuilder("importJob", jobRepository)
                .start(importStep)
                .build();
    }
}
