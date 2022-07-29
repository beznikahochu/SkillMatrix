package by.skillmatrix.excel;

import by.skillmatrix.entity.Skill;
import by.skillmatrix.entity.SkillCategory;
import by.skillmatrix.entity.SkillMatrix;
import by.skillmatrix.exception.ExcelBuildException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SkillMatrixExcelBuilder {

    public byte[] build(SkillMatrix skillMatrix) {
        Workbook workbook = new XSSFWorkbook();
        SkillMatrixCellStyler styler = new SkillMatrixCellStyler(workbook);

        Sheet sheet = workbook.createSheet(skillMatrix.getName());
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 2000);
        sheet.setColumnWidth(2, 7000);

        int rowNum = 0;

        rowNum = buildHeader(sheet, styler, rowNum,skillMatrix) + 1;
        rowNum = buildTabNamesRow(sheet, styler,rowNum, "Название ", "Оценка","Комментарий");
        rowNum = buildCategoriesAndSkillsRows(sheet, styler,rowNum,skillMatrix);
        buildResultRow(sheet, styler,rowNum,skillMatrix);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new ExcelBuildException(e);
        }
        byte[] excel = outputStream.toByteArray();
        return excel;
    }

    private int buildHeader(Sheet sheet, SkillMatrixCellStyler styler,
                            int rowNum, SkillMatrix skillMatrix) {
        String firstName = skillMatrix.getPerson().getFirstName();
        String lastName = skillMatrix.getPerson().getLastName();
        String matrixName = skillMatrix.getName();
        String schemeName = skillMatrix.getSkillMatrixScheme().getName();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = skillMatrix.getCreationDate().format(dateFormat);

        buildHeaderRow(sheet, styler, rowNum++, "Название матрицы:", matrixName);
        buildHeaderRow(sheet, styler, rowNum++, "Схема матрицы:", schemeName);
        buildHeaderRow(sheet, styler, rowNum++, "Фамилия и имя:", firstName + " " + lastName);
        buildHeaderRow(sheet, styler, rowNum++, "Дата создания:", date);

        return rowNum;
    }

    private void buildHeaderRow(Sheet sheet, SkillMatrixCellStyler styler,
                                int rowNum, String str1, String str2) {
        sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,1,2));
        CellStyle cellStyle = styler.simpleCellStyle;

        Row row = sheet.createRow(rowNum);
        row.setHeight((short) 350);
        Cell cell1 = row.createCell(0);
        cell1.setCellStyle(cellStyle);
        Cell cell2 = row.createCell(1);
        cell2.setCellStyle(cellStyle);
        Cell cell3 = row.createCell(2);
        cell3.setCellStyle(cellStyle);
        cell1.setCellValue(str1);
        cell2.setCellValue(str2);
    }

    private int buildTabNamesRow(Sheet sheet, SkillMatrixCellStyler styler,
                                 int rowNum, String str1, String str2, String str3) {
        CellStyle tabStyle = styler.tabNamesSellStyle;
        Row row = sheet.createRow(rowNum);
        row.setHeight((short) 350);
        Cell cell1 = row.createCell(0);
        Cell cell2 = row.createCell(1);
        Cell cell3 = row.createCell(2);
        cell1.setCellStyle(tabStyle);
        cell2.setCellStyle(tabStyle);
        cell3.setCellStyle(tabStyle);
        cell1.setCellValue(str1);
        cell2.setCellValue(str2);
        cell3.setCellValue(str3);
        return ++rowNum;
    }

    private int buildCategoriesAndSkillsRows(Sheet sheet, SkillMatrixCellStyler styler,
                                             int rowNum, SkillMatrix skillMatrix) {
        List<SkillCategory> categories = skillMatrix.getSkillMatrixScheme().getSkillCategories();
        CellStyle categoryStyle = styler.categorySellStyle;
        CellStyle simpleStyle = styler.simpleCellStyle;
        CellStyle assessmentStyle = styler.assessmentCellStyle;

        for (SkillCategory category: categories) {
            sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,0,2));
            Row row = sheet.createRow(rowNum);
            Cell cell1 = row.createCell(0);
            Cell cell3 = row.createCell(2);
            cell1.setCellStyle(categoryStyle);
            cell3.setCellStyle(categoryStyle);
            cell1.setCellValue(category.getName());
            rowNum++;
            for (Skill skill: category.getSkills()) {
                Row skillRow = sheet.createRow(rowNum);
                Cell skillCell1 = skillRow.createCell(0);
                Cell skillCell2 = skillRow.createCell(1);
                Cell skillCell3 = skillRow.createCell(2);
                skillCell1.setCellStyle(simpleStyle);
                skillCell2.setCellStyle(assessmentStyle);
                skillCell3.setCellStyle(simpleStyle);
                skillCell1.setCellValue(skill.getName());
                if (!skill.getSkillAssessments().isEmpty()) {
                    skillCell2.setCellValue(skill.getSkillAssessments().get(0).getAssessment());
                    skillCell3.setCellValue(skill.getSkillAssessments().get(0).getComment());
                }
                rowNum++;
            }
        }

        return rowNum;
    }

    private int buildResultRow(Sheet sheet, SkillMatrixCellStyler styler,
                               int rowNum, SkillMatrix skillMatrix) {
        if (skillMatrix.getAvgAssessment() == null) {
            return rowNum;
        }
        CellStyle style = styler.avgAssessmentCellStyle;
        Row skillRow = sheet.createRow(rowNum++);
        Cell resultCell = skillRow.createCell(1);
        resultCell.setCellStyle(style);
        resultCell.setCellValue(skillMatrix.getAvgAssessment());
        return rowNum;
    }

    private static class SkillMatrixCellStyler {

        private final CellStyle simpleCellStyle;
        private final CellStyle assessmentCellStyle;
        private final CellStyle tabNamesSellStyle;
        private final CellStyle categorySellStyle;
        private final CellStyle avgAssessmentCellStyle;
        private Workbook workbook;

        public SkillMatrixCellStyler(Workbook workbook) {
            this.workbook = workbook;
            simpleCellStyle = createSimpleCellStyle(workbook);
            assessmentCellStyle = createAssessmentCellStyle(workbook);
            tabNamesSellStyle = createTabNamesCellStyle(workbook);
            categorySellStyle = createCategoryCellStyle(workbook);
            avgAssessmentCellStyle = createAvgAssessmentCellStyle(workbook);
        }

        private CellStyle createSimpleCellStyle(Workbook workbook) {
            CellStyle style = workbook.createCellStyle();
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setAlignment(HorizontalAlignment.LEFT);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setWrapText(true);
            return style;
        }

        private CellStyle createAssessmentCellStyle(Workbook workbook) {
            CellStyle style = createSimpleCellStyle(workbook);
            style.setAlignment(HorizontalAlignment.CENTER);
            return style;
        }

        private CellStyle createAvgAssessmentCellStyle(Workbook workbook) {
            CellStyle style = createAssessmentCellStyle(workbook);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
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
}
