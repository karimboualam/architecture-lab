// src/main/java/com/architecturelab/hexagonal/application/controller/DossierExportController.java
package com.architecturelab.hexagonal.application.controller;

import com.architecturelab.hexagonal.application.mapper.DossierMapper;
import com.architecturelab.hexagonal.application.service.DossierApplicationService;
import com.architecturelab.hexagonal.infrastructure.export.ExcelExportAdapter;
import com.architecturelab.hexagonal.infrastructure.export.PdfExportAdapter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dossiers/export")
public class DossierExportController {

    private final DossierApplicationService service;
    private final PdfExportAdapter pdf;
    private final ExcelExportAdapter excel;

    public DossierExportController(DossierApplicationService service, DossierMapper mapper,
                                   PdfExportAdapter pdf, ExcelExportAdapter excel) {
        this.service = service;
        this.pdf = pdf;
        this.excel = excel;
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> exportPdf() throws Exception {
        byte[] data = pdf.export(service.getAll());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dossiers.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(data);
    }

    @GetMapping("/excel")
    public ResponseEntity<byte[]> exportExcel() throws Exception {
        byte[] data = excel.export(service.getAll());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dossiers.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(data);
    }
}
