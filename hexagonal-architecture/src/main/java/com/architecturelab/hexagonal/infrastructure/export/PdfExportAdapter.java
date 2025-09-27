// src/main/java/com/architecturelab/hexagonal/infrastructure/export/PdfExportAdapter.java
package com.architecturelab.hexagonal.infrastructure.export;

import com.architecturelab.hexagonal.domain.model.Dossier;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Component
public class PdfExportAdapter {

    public byte[] export(List<Dossier> dossiers) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, out);
        doc.open();

        Paragraph title = new Paragraph("Liste des dossiers");
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(10f);
        doc.add(title);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{10f, 20f, 25f, 15f, 30f});

        addHeader(table, "ID");
        addHeader(table, "Référence");
        addHeader(table, "Titre");
        addHeader(table, "Statut");
        addHeader(table, "Dernière Maj");

        for (Dossier d : dossiers) {
            table.addCell(String.valueOf(d.getId()));
            table.addCell(nullSafe(d.getReference()));
            table.addCell(nullSafe(d.getTitre()));
            table.addCell(d.getStatut().name());
            table.addCell(d.getUpdatedAt() != null ? d.getUpdatedAt().toString() : "");
        }

        doc.add(table);
        doc.close();
        return out.toByteArray();
    }

    private void addHeader(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setGrayFill(0.9f);
        table.addCell(cell);
    }

    private String nullSafe(String s) { return s == null ? "" : s; }
}
