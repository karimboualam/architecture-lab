// src/main/java/com/architecturelab/hexagonal/infrastructure/export/ExportScheduler.java
package com.architecturelab.hexagonal.infrastructure.export;

import com.architecturelab.hexagonal.domain.port.DossierServicePort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ExportScheduler {

    private final DossierServicePort dossierService;
    private final PdfExportAdapter pdf;
    private final ExcelExportAdapter excel;

    public ExportScheduler(DossierServicePort dossierService, PdfExportAdapter pdf, ExcelExportAdapter excel) {
        this.dossierService = dossierService;
        this.pdf = pdf;
        this.excel = excel;
    }

    // Chaque jour à 02:30
    @Scheduled(cron = "0 30 2 * * *")
    public void exportDaily() {
        try {
            var dossiers = dossierService.getAll();
            var stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmm"));
            Files.createDirectories(Path.of("exports"));

            byte[] pdfBytes = pdf.export(dossiers);
            Files.write(Path.of("exports/dossiers-" + stamp + ".pdf"), pdfBytes);

            byte[] xlsx = excel.export(dossiers);
            Files.write(Path.of("exports/dossiers-" + stamp + ".xlsx"), xlsx);
        } catch (Exception e) {
            // log minimal (tu peux brancher un vrai logger)
            System.err.println("ExportScheduler error: " + e.getMessage());
        }
    }
}
