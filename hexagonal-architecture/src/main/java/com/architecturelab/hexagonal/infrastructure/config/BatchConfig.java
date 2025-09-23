package com.architecturelab.hexagonal.infrastructure.config;

import com.architecturelab.hexagonal.domain.model.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    // 1. Reader : lire un CSV
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

    // 2. Processor : transformer les données
    @Bean
    public ItemProcessor<Product, Product> processor() {
        return product -> {
            product.setName(product.getName().toUpperCase()); // exemple
            return product;
        };
    }

    // 3. Writer : sauvegarder (DB, fichier, etc.)
    @Bean
    public ItemWriter<Product> writer() {
        return items -> {
            for (Product p : items) {
                System.out.println("==> Saving product: " + p.getName() + " / " + p.getPrice());
                // tu peux appeler ton repository ici
            }
        };
    }

    // 4. Step
    @Bean
    public Step importStep(ItemReader<Product> reader, ItemProcessor<Product, Product> processor, ItemWriter<Product> writer) {
        return stepBuilderFactory.get("importStep")
                .<Product, Product>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    // 5. Job
    @Bean
    public Job importJob(Step importStep) {
        return jobBuilderFactory.get("importJob")
                .start(importStep)
                .build();
    }
}
