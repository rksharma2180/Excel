package com.excel.exceloperations.services.pdf

import be.quodlibet.boxable.BaseTable
import be.quodlibet.boxable.HorizontalAlignment
import be.quodlibet.boxable.Row
import be.quodlibet.boxable.VerticalAlignment
import be.quodlibet.boxable.line.LineStyle
import org.apache.pdfbox.pdmodel.font.PDType1Font
import org.springframework.stereotype.Service
import org.apache.pdfbox.pdmodel.common.PDRectangle
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.poi.hssf.usermodel.HeaderFooter.page
import org.apache.poi.hssf.usermodel.HeaderFooter.page
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.poi.hssf.usermodel.HeaderFooter.page
import org.apache.poi.hssf.usermodel.HeaderFooter.page
import org.apache.poi.hssf.usermodel.HeaderFooter.page
import javax.swing.text.StyleConstants.setLineSpacing
import javax.swing.text.StyleConstants.setFontSize
import javax.print.attribute.standard.MediaTray.MIDDLE
import org.apache.poi.hssf.record.aggregates.RowRecordsAggregate.createRow
import org.apache.poi.hssf.util.HSSFColor.GREEN
import org.apache.poi.hssf.util.HSSFColor.RED
import org.apache.poi.hssf.util.HSSFColor.BLACK
import java.awt.Color
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream


@Service
class PDFService() {

    fun generatePDF(): ByteArrayInputStream {

        val outStream = ByteArrayOutputStream()
        var fontPlain = PDType1Font.HELVETICA
        val fontBold = PDType1Font.HELVETICA_BOLD
        val fontItalic = PDType1Font.HELVETICA_OBLIQUE
        val fontMono = PDType1Font.COURIER

        val document = PDDocument()
        val page = PDPage(PDRectangle.A4)
        var rect = page.mediaBox

        document.addPage(page);
        val cos = PDPageContentStream(document, page)
        val margin = 50f //left-right
        println("page.mediaBox.height ${page.mediaBox.height} page.mediaBox.width ${page.mediaBox.width}")
        val yStartNewPage = page.mediaBox.height - (2 * margin)
        val tableWidth = page.mediaBox.width - (2 * margin)

        val drawContent = true
        val yStart = yStartNewPage
        val bottomMargin = 10f
        val yPosition = 800f

        println("yStartNewPage $yStartNewPage, yPosition $yPosition, bottomMargin $bottomMargin tableWidth $tableWidth margin $margin")

        val table = BaseTable(yPosition, yStartNewPage,
                bottomMargin, tableWidth, margin, document, page, true, drawContent)

        val headerRow = table.createRow(50F)
        var cell = headerRow.createCell(100F, "DEMO SKL - 001")
        //cell.font = fontBold
        cell.fontSize = 25F
        cell.valign = VerticalAlignment.BOTTOM
        cell.align = HorizontalAlignment.CENTER
        //cell.setTopBorderStyle(LineStyle(Color.BLACK, 2F))
        cell.setBottomBorderStyle(LineStyle(Color.white, 1F))
        table.addHeaderRow(headerRow)

        var row = table.createRow(10F)
        cell = row.createCell(100F, "Address: Test Address")
        cell.fontSize = 10F
        cell.setTopBorderStyle(LineStyle(Color.white, 1F))
        cell.valign = VerticalAlignment.TOP
        cell.align = HorizontalAlignment.CENTER

        row = table.createRow(10F)
        cell = row.createCell(18F, "Fee Concession Report")
        cell.fontSize = 10F
        cell.font = fontBold
        cell.valign = VerticalAlignment.MIDDLE
        cell.align = HorizontalAlignment.LEFT


        //row = table.createRow(10F)
        cell = row.createCell(23F, "Academic Year: 2019 - 2020")
        cell.fontSize = 10F
        cell.font = fontBold
        cell.valign = VerticalAlignment.MIDDLE
        cell.align = HorizontalAlignment.LEFT

        //row = table.createRow(10F)
        cell = row.createCell(27F, "Class(es): Class I, Class X, Nursery")
        cell.fontSize = 10F
        cell.font = fontBold
        cell.valign = VerticalAlignment.MIDDLE
        cell.align = HorizontalAlignment.LEFT

        //row = table.createRow(10F)
        cell = row.createCell(12F, "Secion(s): A")
        cell.fontSize = 10F
        cell.font = fontBold
        cell.valign = VerticalAlignment.MIDDLE
        cell.align = HorizontalAlignment.LEFT

        //row = table.createRow(10F)
        cell = row.createCell(20F, "Total Concession: 21560")
        cell.fontSize = 10F
        cell.font = fontBold
        cell.valign = VerticalAlignment.MIDDLE
        cell.align = HorizontalAlignment.LEFT

        row = table.createRow(20F)
        cell = row.createCell(100F, "")

        row = table.createRow(20F)
        cell = row.createCell(10F, "Roll Number")
        cell.fontSize = 10F
        cell.font = fontBold
        cell.valign = VerticalAlignment.MIDDLE
        cell.align = HorizontalAlignment.LEFT

        cell = row.createCell(8F, "Class Name")
        cell.fontSize = 10F
        cell.font = fontBold
        cell.valign = VerticalAlignment.MIDDLE
        cell.align = HorizontalAlignment.LEFT

        cell = row.createCell(12F, "Section Name")
        cell.fontSize = 10F
        cell.font = fontBold
        cell.valign = VerticalAlignment.MIDDLE
        cell.align = HorizontalAlignment.LEFT

        cell = row.createCell(20F, "Student Name")
        cell.fontSize = 10F
        cell.font = fontBold
        cell.valign = VerticalAlignment.MIDDLE
        cell.align = HorizontalAlignment.LEFT

        cell = row.createCell(9F, "Term Fees-1")
        cell.fontSize = 10F
        cell.font = fontBold
        cell.valign = VerticalAlignment.MIDDLE
        cell.align = HorizontalAlignment.LEFT

        cell = row.createCell(10F, "Activity Fees")
        cell.fontSize = 10F
        cell.font = fontBold
        cell.valign = VerticalAlignment.MIDDLE
        cell.align = HorizontalAlignment.LEFT

        cell = row.createCell(9F, "Team Fees-2")
        cell.fontSize = 10F
        cell.font = fontBold
        cell.valign = VerticalAlignment.MIDDLE
        cell.align = HorizontalAlignment.LEFT

        cell = row.createCell(9F, "Team Fees-3")
        cell.fontSize = 10F
        cell.font = fontBold
        cell.valign = VerticalAlignment.MIDDLE
        cell.align = HorizontalAlignment.LEFT

        cell = row.createCell(13.00F, "Total Concession")
        cell.fontSize = 10F
        cell.font = fontBold
        cell.valign = VerticalAlignment.MIDDLE
        cell.align = HorizontalAlignment.LEFT

        table.draw()

        val tableHeight = table.headerAndDataHeight
        println("tableHeight = $tableHeight")

        cos.close()

        document.save(outStream)
        document.close()
        return ByteArrayInputStream(outStream.toByteArray())
    }

    private fun createCell(
            row: Row<PDPage>, width: Float, label: String, value: String, valign: VerticalAlignment,
            halign: HorizontalAlignment, font: PDType1Font, fontSize: Float) {
        val cell = row.createCell(width, "$label $value")
        cell.fontSize = fontSize
        cell.font = font
        cell.valign = valign
        cell.align = halign
    }
}
