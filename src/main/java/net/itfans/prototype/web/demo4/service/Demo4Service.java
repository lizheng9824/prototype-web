package net.itfans.prototype.web.demo4.service;

import java.io.InputStream;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

@Service
public class Demo4Service {
    
    public byte[] generatePdf() throws JRException {

        InputStream jrxmlInputStream = this.getClass().getResourceAsStream("/jrxml/japan_font_demo.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlInputStream);

        byte[] pdfBytes = JasperRunManager.runReportToPdf(jasperReport, new HashMap<String, Object>(),  new JREmptyDataSource());
        
        return pdfBytes;
    }
}
