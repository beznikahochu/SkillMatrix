package by.skillmatrix.excel;

import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.entity.SkillEntity;
import by.skillmatrix.entity.SkillMatrixEntity;
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

    public byte[] build(SkillMatrixEntity skillMatrix) {
        Workbook workbook = new XSSFWorkbook();
        SkillMatrixCellStyler styler = new SkillMatrixCellStyler(workbook);

        Sheet sheet = workbook.createSheet(skillMatrix.getName());
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 2000);
        sheet.setColumnWidth(2, 7000);

        buildHeader(sheet, styler, 0,skillMatrix);
        buildTabNamesRow(sheet, styler,5, "Название", "Оценка","Комментарий");
        buildCategoriesAndSkillsRows(sheet, styler,6,skillMatrix);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new ExcelBuildException(e);
        }
        byte[] excel = outputStream.toByteArray();
        return excel;
    }

    private void buildHeader(Sheet sheet, SkillMatrixCellStyler styler,
                             int startRowNum, SkillMatrixEntity skillMatrix) {
        String firstName = skillMatrix.getEmployee().getFirstName();
        String lastName = skillMatrix.getEmployee().getLastName();
        String matrixName = skillMatrix.getName();
        String schemeName = skillMatrix.getSkillMatrixScheme().getName();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = skillMatrix.getCreationDate().format(dateFormat);

        buildHeaderRow(sheet, styler, startRowNum, "Название матрицы:", matrixName);
        buildHeaderRow(sheet, styler, startRowNum+1, "Схема матрицы:", schemeName);
        buildHeaderRow(sheet, styler, startRowNum+2, "Сотрудник:", firstName + " " + lastName);
        buildHeaderRow(sheet, styler, startRowNum+3, "Дата создания:", date);
    }

    private void buildHeaderRow(Sheet sheet, SkillMatrixCellStyler styler,
                                int rowNum, String str1, String str2) {
        sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,1,2));
        CellStyle cellStyle = styler.getSimpleCellStyle();

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

    private void buildTabNamesRow(Sheet sheet, SkillMatrixCellStyler styler,
                                  int rowNum, String str1, String str2, String str3) {
        CellStyle tabStyle = styler.getTabNamesSellStyle();
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
    }

    private void buildCategoriesAndSkillsRows(Sheet sheet, SkillMatrixCellStyler styler,
                                int startRowNum, SkillMatrixEntity skillMatrix) {
        List<SkillCategoryEntity> categories = skillMatrix.getSkillMatrixScheme().getSkillCategories();
        CellStyle categoryStyle = styler.getCategorySellStyle();
        CellStyle simpleStyle = styler.getSimpleCellStyle();
        CellStyle assessmentStyle = styler.getAssessmentCellStyle();

        int rowNum = startRowNum;

        for (SkillCategoryEntity category: categories) {
            sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,0,2));
            Row row = sheet.createRow(rowNum);
            Cell cell1 = row.createCell(0);
            Cell cell3 = row.createCell(2);
            cell1.setCellStyle(categoryStyle);
            cell3.setCellStyle(categoryStyle);
            cell1.setCellValue(category.getName());
            rowNum++;
            for (SkillEntity skill: category.getSkills()) {
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
    }
}
