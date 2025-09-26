package com.architecturelab.hexagonal.infrastructure.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller permettant de déclencher l'exécution d'un Job Spring Batch.
 * Ici, le Job lit un fichier CSV (products.csv) et importe les données en base de données.
 */
@RestController
@RequestMapping("/api/batch")
public class BatchController {

    private final JobLauncher jobLauncher; // Permet de lancer un Job Batch
    private final Job importJob;           // Référence du Job défini dans BatchConfig

    /**
     * Injection des dépendances via le constructeur.
     *
     * @param jobLauncher permet de lancer un job Spring Batch.
     * @param importJob le job configuré dans BatchConfig (import CSV → DB).
     */
    public BatchController(JobLauncher jobLauncher, Job importJob) {
        this.jobLauncher = jobLauncher;
        this.importJob = importJob;
    }

    /**
     * Endpoint REST pour exécuter le batch manuellement.
     *
     * Exemple : POST http://localhost:8080/api/batch/run
     *
     * @return message de confirmation une fois le batch lancé.
     * @throws Exception en cas d'erreur lors de l'exécution du job.
     */
    @PostMapping("/run")
    public String runBatch() throws Exception {
        // On génère des paramètres uniques à chaque lancement.
        // Cela évite que Spring Batch considère l’exécution comme un doublon.
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        // Lancement du job (asynchrone, mais bloquant jusqu’à son exécution)
        jobLauncher.run(importJob, params);

        return "✅ Batch lancé avec succès ! Vérifie la base de données.";
    }
}
