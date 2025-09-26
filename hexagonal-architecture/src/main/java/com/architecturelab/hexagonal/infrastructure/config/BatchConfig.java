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

@Configuration // 📌 Indique à Spring que cette classe contient des beans de configuration
public class BatchConfig {

    private final ProductRepositoryAdapter repositoryAdapter;

    // 📌 On injecte l’adaptateur qui permet de sauvegarder des produits en DB
    public BatchConfig(ProductRepositoryAdapter repositoryAdapter) {
        this.repositoryAdapter = repositoryAdapter;
    }

    // 1️⃣ READER : lit les données depuis le fichier CSV
    @Bean
    public ItemReader<Product> reader() {
        return new FlatFileItemReaderBuilder<Product>()
                .name("productItemReader")                   // nom du reader (utile pour debug/logs)
                .resource(new ClassPathResource("products.csv")) // fichier CSV placé dans src/main/resources
                .delimited()                                // lecture des champs séparés par une virgule
                .names("name", "price")                     // correspondance avec les attributs de Product
                .linesToSkip(1)                             // ⚠️ ignorer la première ligne (header du CSV)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Product.class);           // chaque ligne → objet Product
                }})
                .build();
    }

    // 2️⃣ PROCESSOR : transforme les données avant insertion
    @Bean
    public ItemProcessor<Product, Product> processor() {
        return product -> {
            // Exemple : on met le nom en majuscules
            product.setName(product.getName().toUpperCase());
            return product;
        };
    }

    // 3️⃣ WRITER : écrit les données transformées en base
    @Bean
    public ItemWriter<Product> writer() {
        return chunk -> repositoryAdapter.saveAll(
                (List<Product>) (List<?>) chunk.getItems()
        );
        // chunk.getItems() = lot d’objets lus → ici, sauvegardés en DB via repositoryAdapter
    }

    // 4️⃣ STEP : définit une étape du Job
    @Bean
    public Step importStep(JobRepository jobRepository,
                           PlatformTransactionManager transactionManager,
                           ItemReader<Product> reader,
                           ItemProcessor<Product, Product> processor,
                           ItemWriter<Product> writer) {
        return new StepBuilder("importStep", jobRepository)
                .<Product, Product>chunk(10, transactionManager)
                // 📌 chunk(10) = traite les données par lots de 10 lignes
                .reader(reader)       // lit depuis le CSV
                .processor(processor) // transforme (noms → majuscules)
                .writer(writer)       // insère en base
                .build();
    }

    // 5️⃣ JOB : regroupe une ou plusieurs étapes
    @Bean
    public Job importJob(JobRepository jobRepository, Step importStep) {
        return new JobBuilder("importJob", jobRepository)
                .start(importStep) // 📌 ici, un seul Step : importStep
                .build();
    }
}
