package cn.cnstrong.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @���� �������ݼ�(������)����Excel�ļ�
 */
public class ExcelUtil {

    /**
     * ��ʵ��
     */
    private static ExcelUtil export;

    /**
     * excel�ĵ�
     */
    private HSSFWorkbook workbook;

    /**
     * excel sheet
     */
    private HSSFSheet sheet;

    /**
     * �ֽ���
     */
    private OutputStream fileOutput;

    /**
     * ����˽�й��췽��
     */
    private ExcelUtil() {

    }

    /**
     * ����һ��excel����������ʵ��(����ģʽ)
     * 
     * @return excel�������������
     */
    public static ExcelUtil newInstance() {
        if (export == null)
            export = new ExcelUtil();
        return export;
    }

    /**
     * @�������� ����excel�ĵ�(����)
     * @������ 
     * @����ʱ�� 
     * @param tName
     *            excel������
     * @param tHeader
     *            excel��ͷ���ݼ�
     * @param tValue
     *            excel�����ݼ�(����ͷ)
     * @param tHeaderStyle
     *            excel��ͷ��Ԫ����ʽ
     * @param tValueStyle
     *            excel�����ݵ�Ԫ����ʽ(����ͷ)
     * @param filePath
     *            excel�ļ���ַ
     * @throws Exception
     *             �쳣�����׳�
     */
    public void exportExcel(String tName, ArrayList<String> tHeader, ArrayList<ArrayList<Object>> tValue,
            Map<String, Short> tHeaderStyle, Map<String, Short> tValueStyle, String filePath) throws Exception {

        try {
            // ��excel�ĵ�������ʱ����
            workbook = new HSSFWorkbook();

            // ��������ֵ����ʽ
            this.setSheet(tName, tHeader, tValue, tHeaderStyle, tValueStyle);
            // ����excel�ļ�
            this.export(workbook, filePath);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * @�������� ����excel�ĵ�(����)
     * @������ 
     * @����ʱ�� 
     * @param tName
     *            excel������
     * @param tHeader
     *            excel��ͷ���ݼ�
     * @param tValue
     *            excel�����ݼ�(����ͷ)
     * @param tHeaderStyle
     *            excel��ͷ��Ԫ����ʽ
     * @param tValueStyle
     *            excel�����ݵ�Ԫ����ʽ(����ͷ)
     * @throws Exception
     *             �쳣�����׳�
     */
    public InputStream exportExcelToStream(String tName, ArrayList<String> tHeader,
            ArrayList<ArrayList<Object>> tValue, Map<String, Short> tHeaderStyle, Map<String, Short> tValueStyle)
            throws Exception {

        // ��excel�ĵ�������ʱ����
        workbook = new HSSFWorkbook();

        // ��������ֵ����ʽ
        this.setSheet(tName, tHeader, tValue, tHeaderStyle, tValueStyle);

        // ����excel�ļ�
        return export(workbook);

    }

    /**
     * @�������� ����excel�ĵ�(���)
     * @������ 
     * @����ʱ�� 
     * @param tName
     *            excel������
     * @param tHeader
     *            excel��ͷ���ݼ�
     * @param tValue
     *            excel�����ݼ�(����ͷ)
     * @param tHeaderStyle
     *            excel��ͷ��Ԫ����ʽ
     * @param tValueStyle
     *            excel�����ݵ�Ԫ����ʽ(����ͷ)
     * @param filePath
     *            excel�ļ���ַ
     * @throws Exception
     *             �쳣�����׳�
     */
    public void exportExcel(ArrayList<String> tName, ArrayList<ArrayList<String>> tHeader,
            ArrayList<ArrayList<ArrayList<Object>>> tValue, ArrayList<Map<String, Short>> tHeaderStyle,
            ArrayList<Map<String, Short>> tValueStyle, String filePath) throws Exception {

        try {
            // ��excel�ĵ�������ʱ����
            workbook = new HSSFWorkbook();

            // forѭ������ĵ��������ĸ�ֵ����ʽ
            for (int i = 0; i < tName.size(); i++) {
                this.setSheet(tName.get(i), tHeader.get(i), tValue.get(i), tHeaderStyle.get(i), tValueStyle.get(i)); // ��������ֵ����ʽ
            }

            // ����excel�ļ�
            this.export(workbook, filePath);

        } catch (Exception e) {
        }
    }

    /**
     * @�������� ����excel�ĵ�(���)
     * @������ 
     * @����ʱ�� 
     * @param tName
     *            excel������
     * @param tHeader
     *            excel��ͷ���ݼ�
     * @param tValue
     *            excel�����ݼ�(����ͷ)
     * @param tHeaderStyle
     *            excel��ͷ��Ԫ����ʽ
     * @param tValueStyle
     *            excel�����ݵ�Ԫ����ʽ(����ͷ)
     * @throws Exception
     *             �쳣�����׳�
     */
    public InputStream exportExcelToStream(ArrayList<String> tName, ArrayList<ArrayList<String>> tHeader,
            ArrayList<ArrayList<ArrayList<Object>>> tValue, ArrayList<Map<String, Short>> tHeaderStyle,
            ArrayList<Map<String, Short>> tValueStyle) throws Exception {

        // ��excel�ĵ�������ʱ����
        workbook = new HSSFWorkbook();

        // forѭ������ĵ��������ĸ�ֵ����ʽ
        for (int i = 0; i < tName.size(); i++) {
            this.setSheet(tName.get(i), tHeader.get(i), tValue.get(i), tHeaderStyle.get(i), tValueStyle.get(i)); // ��������ֵ����ʽ
        }
        return export(workbook);
    }

    /**
     * @�������� ����excel��
     * @������ 
     * @����ʱ�� 
     * @param tName
     *            excel����
     * @param tHeader
     *            excel��ͷ���ݼ�
     * @param tValue
     *            excel�����ݼ�(����ͷ)
     * @param tHeaderStyle
     *            excel��ͷ��Ԫ����ʽ
     * @param tValueStyle
     *            excel�����ݵ�Ԫ����ʽ(����ͷ)
     * @throws Exception
     *             �쳣�����׳�
     */
    private void setSheet(String tName, ArrayList<String> tHeader, ArrayList<ArrayList<Object>> tValue,
            Map<String, Short> tHeaderStyle, Map<String, Short> tValueStyle) throws Exception {

        try {
            // �����������������
            sheet = workbook.createSheet(tName);

            // ��������
            HSSFRow tRow = sheet.createRow(0);

            // ��ֵ����ʽ(��ʱ,Ϊ��ͷ��)
            tRow = this.setTRow(tRow, tHeader, tHeaderStyle);

            // forѭ����ɱ����ݵĸ�ֵ����ʽ(����ͷ)
            for (int i = 0; i < tValue.size(); i++) {
                tRow = sheet.createRow(i + 1); // ��ȡ����

                tRow = this.setTRow(tRow, tValue.get(i), tValueStyle); // ���õ�ǰ�е����ݺ���ʽ
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @�������� ����excel��������
     * @������ 
     * @����ʱ�� 
     * @param row
     *            excel����
     * @param tRow
     *            excel��������
     * @param tHeaderStyle
     *            excel��ͷ��ʽ
     * @return ���ú�ĵı���
     * @throws Exception
     *             �쳣�����׳�
     */
    @SuppressWarnings("unchecked")
    private HSSFRow setTRow(HSSFRow row, ArrayList tRow, Map<String, Short> tHeaderStyle) throws Exception {

        try {
            // ��ȡ��Ԫ����ʽ
            HSSFCellStyle cellStyle = this.setCellStyle(tHeaderStyle);
            // ������Ԫ��
            HSSFCell cell = null;

            // forѭ����ɸñ�ĳ�и����и�ֵ����ʽ
            for (int i = 0; i < tRow.size(); i++) {
                cell = row.createCell(i); // ��ȡÿ�е�Ԫ��
                cell.setCellStyle(cellStyle); // ������ʽ

                sheet.autoSizeColumn((short) i); // ���õ�Ԫ������Ӧ
                Object obj = tRow.get(i); // ��ȡ��ǰ�е�ֵ
                // �ж϶�����������, ��ǿת
                if (obj instanceof Integer) // ������ʱ
                    cell.setCellValue((Integer) obj);
                if (obj instanceof String) // ��Ϊ�ַ���ʱ
                    cell.setCellValue((String) obj);
                if (obj instanceof Boolean) // ��Ϊ����ʱ
                    cell.setCellValue((Boolean) obj);
                if (obj instanceof Date) // ��Ϊʱ��ʱ
                    cell.setCellValue((Date) obj);
                if (obj instanceof Calendar) // ��Ϊʱ��ʱ
                    cell.setCellValue((Calendar) obj);
                if (obj instanceof Double) // ��ΪС��ʱ
                    cell.setCellValue((Double) obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row; // ����
    }

    /**
     * @�������� ���õ�Ԫ����ʽ
     * @������ 
     * @����ʱ�� 
     * @param fontStyle
     *            ��ʽMap����
     * @return ���ú�Ԫ����ʽ
     * @throws Exception
     *             �쳣�����׳�
     */
    private HSSFCellStyle setCellStyle(Map<String, Short> fontStyle) throws Exception {

        // ������Ԫ����ʽ
        HSSFCellStyle cellStyle = null;
        try {
            // ��������
            HSSFFont font = workbook.createFont();
            // ����������ʽ
            // ����������ɫ(��ɫΪ:HSSFFont.COLOR_RED �����ʾshort���� 10)
            font.setColor(fontStyle.get("color"));
            // ������������(����Ϊ:HSSFFont.BOLDWEIGHT_BOLD 700) -- ����
            font.setBoldweight(fontStyle.get("weight"));

            // ������Ԫ����ʽ
            cellStyle = workbook.createCellStyle();
            // ���������ʽ
            cellStyle.setFont(font);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cellStyle; // ����
    }

    /**
     * @�������� ����Excel
     * @������ 
     * @����ʱ�� 
     * @param workbook
     *            excel�ĵ�
     * @param filePath
     *            xls�ļ���ַ
     * @throws Exception
     *             �쳣�����׳�
     */
    private void export(HSSFWorkbook workbook, String filePath) throws Exception {

        try {
            // ����ָ��xls�ļ������ļ��ַ���
            fileOutput = new FileOutputStream(filePath);
            // ���ĵ�д��ָ���ļ�
            workbook.write(fileOutput);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // �ر���, �ͷ���Դ
                fileOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
                ;
            }
        }
    }

    /**
     * @�������� ��ȡ��
     * @������ 
     * @����ʱ�� 
     * @param workbook
     *            excel�ĵ�
     * @throws Exception
     *             �쳣�����׳�
     */
    private InputStream export(HSSFWorkbook workbook) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            try {
                workbook.write(baos);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] ba = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(ba);
            return bais;
        } finally {
            // �ر���, �ͷ���Դ
            baos.close();
        }
    }
}
