// src/main/java/com/architecturelab/hexagonal/infrastructure/export/ExcelExportAdapter.java
package com.architecturelab.hexagonal.infrastructure.export;

import com.architecturelab.hexagonal.domain.model.Dossier;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Component
public class ExcelExportAdapter {

    public byte[] export(List<Dossier> dossiers) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Dossiers");

        // Header
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Référence");
        header.createCell(2).setCellValue("Titre");
        header.createCell(3).setCellValue("Statut");
        header.createCell(4).setCellValue("Créé le");
        header.createCell(5).setCellValue("MAJ le");

        int rowIdx = 1;
        for (Dossier d : dossiers) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(d.getId() != null ? d.getId() : 0);
            row.createCell(1).setCellValue(d.getReference() != null ? d.getReference() : "");
            row.createCell(2).setCellValue(d.getTitre() != null ? d.getTitre() : "");
            row.createCell(3).setCellValue(d.getStatut().name());
            row.createCell(4).setCellValue(d.getCreatedAt() != null ? d.getCreatedAt().toString() : "");
            row.createCell(5).setCellValue(d.getUpdatedAt() != null ? d.getUpdatedAt().toString() : "");
        }

        for (int i = 0; i <= 5; i++) sheet.autoSizeColumn(i);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();
        return out.toByteArray();
    }
}
