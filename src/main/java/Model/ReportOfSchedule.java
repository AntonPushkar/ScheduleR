package Model;

import Entity.ScheduleWrapperForTable;
import Model.CreaterSchedule.ScheduleManager;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

public class ReportOfSchedule
{
  private static Manager manager = ScheduleManager.getScheduleManager();
  public static void print() throws JRException {
    List<ScheduleWrapperForTable> listSchedule = manager.getListEntities();
    JasperReport jasperReport = JasperCompileManager.compileReport(
        "C:\\Users\\Anton\\Desktop\\Projects\\Schedule11\\src\\main\\resources\\Blank_A4.jrxml");

    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listSchedule);
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), dataSource);
    String PATH = "C:\\Users\\Anton\\Desktop\\Projects\\Schedule11\\src\\test";
    JasperExportManager.exportReportToHtmlFile(jasperPrint, PATH+"report333.xls");

    JRXlsExporter exporter = new JRXlsExporter();

    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(PATH+"report3.html"));

    SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
    configuration.setOnePagePerSheet(true);
    exporter.setConfiguration(configuration);
    jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.graphics", "true");


    exporter.exportReport();

    JasperPrintManager.printReport(jasperPrint, true);
  }

}
