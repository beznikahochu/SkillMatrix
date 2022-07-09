package by.skillmatrix.excel;

import org.apache.poi.ss.usermodel.*;

public class SkillMatrixCellStyler {

    private final CellStyle simpleCellStyle;
    private final CellStyle assessmentCellStyle;
    private final CellStyle tabNamesSellStyle;
    private final CellStyle categorySellStyle;
    private Workbook workbook;

    public SkillMatrixCellStyler(Workbook workbook) {
        this.workbook = workbook;
        simpleCellStyle = createSimpleCellStyle(workbook);
        assessmentCellStyle = createAssessmentCellStyle(workbook);
        tabNamesSellStyle = createTabNamesCellStyle(workbook);
        categorySellStyle = createCategoryCellStyle(workbook);
    }
    public CellStyle getSimpleCellStyle() {
        return simpleCellStyle;
    }

    public CellStyle getTabNamesSellStyle() {
        return tabNamesSellStyle;
    }

    public CellStyle getCategorySellStyle() {
        return categorySellStyle;
    }

    public CellStyle getAssessmentCellStyle() {
        return assessmentCellStyle;
    }

    private CellStyle createSimpleCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    private CellStyle createAssessmentCellStyle(Workbook workbook) {
        CellStyle style = createSimpleCellStyle(workbook);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private CellStyle createTabNamesCellStyle(Workbook workbook) {
        CellStyle style = createSimpleCellStyle(workbook);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private CellStyle createCategoryCellStyle(Workbook workbook) {
        CellStyle style = createSimpleCellStyle(workbook);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style.setAlignment(HorizontalAlignment.LEFT);
        return style;
    }
}
