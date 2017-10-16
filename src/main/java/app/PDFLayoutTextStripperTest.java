package app;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDFLayoutTextStripperTest {

    private static final String ROW_WITHDATE =
            "             16 Mar 17     VIS   MORGAN STANLEY                                                                                                     ";
    private static final String ROW_WITHPAYOUT =
            "                                 CANARY WHARF                                                20.00                              30,543.37           ";
    private static final String ROW_WITHPAYIN =
            "                           CR    MORGAN STANLEY UK                                                            3,455.75          33,952.12           ";
    private static final String ROW_STANDALONE =
            "             20 Mar 17     DD    TALKTALK LIMITED                                            27.00                              30,516.37           ";
    private static final String ROW_NOPAYINOUT_NODATE =
            "                           VIS   SAVING                                                                                                             ";
    private static final String TABLE_START = " BALANCE BROUGHT FORWARD\n";

    @Test
    public void standaloneRow() throws IOException {
        PDFLayoutTextStripper app  = new PDFLayoutTextStripper();
        List<TextLine> list = new ArrayList<TextLine>();
        TextLine tableStart = new TextLine(149);
        tableStart.setLine(TABLE_START);
        list.add(tableStart);
        TextLine standaloneRow = new TextLine(149);
        standaloneRow.setLine(ROW_STANDALONE);
        list.add(standaloneRow);

        List<TextLine> result = app.filterTableContents(list);
        TestCase.assertEquals("20 Mar 17;DD    TALKTALK LIMITED;27.00;", result.get(0).getLine());
    }

    @Test
    public void splitRow() throws IOException {
        PDFLayoutTextStripper app  = new PDFLayoutTextStripper();
        List<TextLine> list = new ArrayList<TextLine>();
        TextLine tableStart = new TextLine(149);
        tableStart.setLine(TABLE_START);
        list.add(tableStart);
        TextLine rowWithDate = new TextLine(149);
        rowWithDate.setLine(ROW_WITHDATE);
        list.add(rowWithDate);
        TextLine rowWithPayout = new TextLine(149);
        rowWithPayout.setLine(ROW_WITHPAYOUT);
        list.add(rowWithPayout);

        List<TextLine> result = app.filterTableContents(list);
        TestCase.assertEquals("16 Mar 17;VIS   MORGAN STANLEY CANARY WHARF;20.00;", result.get(0).getLine());
    }

    @Test
    public void standaloneAndSplitRowOnSameDay() throws IOException {
        PDFLayoutTextStripper app  = new PDFLayoutTextStripper();
        List<TextLine> list = new ArrayList<TextLine>();
        TextLine tableStart = new TextLine(149);
        tableStart.setLine(TABLE_START);
        list.add(tableStart);
        TextLine standaloneRow = new TextLine(149);
        standaloneRow.setLine(ROW_STANDALONE);
        list.add(standaloneRow);
        TextLine rowWithDate = new TextLine(149);
        rowWithDate.setLine(ROW_NOPAYINOUT_NODATE);
        list.add(rowWithDate);
        TextLine rowWithPayout = new TextLine(149);
        rowWithPayout.setLine(ROW_WITHPAYOUT);
        list.add(rowWithPayout);

        List<TextLine> result = app.filterTableContents(list);
        TestCase.assertEquals("20 Mar 17;DD    TALKTALK LIMITED;27.00;", result.get(0).getLine());
        TestCase.assertEquals("20 Mar 17;VIS   SAVING CANARY WHARF;20.00;", result.get(1).getLine());
    }

    @Test
    public void twoSplitRowsOnSameDay() throws IOException {
        PDFLayoutTextStripper app  = new PDFLayoutTextStripper();
        List<TextLine> list = new ArrayList<TextLine>();
        TextLine tableStart = new TextLine(149);
        tableStart.setLine(TABLE_START);
        list.add(tableStart);
        TextLine rowWithDate = new TextLine(149);
        rowWithDate.setLine(ROW_WITHDATE);
        list.add(rowWithDate);
        TextLine rowWithPayIn = new TextLine(149);
        rowWithPayIn.setLine(ROW_WITHPAYIN);
        list.add(rowWithPayIn);
        TextLine rowNoPayInOutNoDate = new TextLine(149);
        rowNoPayInOutNoDate.setLine(ROW_NOPAYINOUT_NODATE);
        list.add(rowNoPayInOutNoDate);
        TextLine rowWithPayout = new TextLine(149);
        rowWithPayout.setLine(ROW_WITHPAYOUT);
        list.add(rowWithPayout);

        List<TextLine> result = app.filterTableContents(list);
        TestCase.assertEquals("16 Mar 17;VIS   MORGAN STANLEY CR    MORGAN STANLEY UK;;3455.75", result.get(0).getLine());
        TestCase.assertEquals("16 Mar 17;VIS   SAVING CANARY WHARF;20.00;", result.get(1).getLine());
    }



}
