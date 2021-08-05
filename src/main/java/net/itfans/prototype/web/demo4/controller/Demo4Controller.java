package net.itfans.prototype.web.demo4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.itfans.prototype.web.demo4.service.Demo4Service;
import net.sf.jasperreports.engine.JRException;

@RequestMapping("/demo4")
@Controller
public class Demo4Controller {
    
    @Autowired
    private Demo4Service demo4Service;

    @GetMapping("")
    public String index() {
        return "demo4/show";
    }

    @PostMapping("print")
    public ResponseEntity<byte[]> post() {

        byte[] pdfBytes;
        try {
            pdfBytes = demo4Service.generatePdf();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<byte[]> response = generateResponse(pdfBytes, headers);
        return response;
    }

    private ResponseEntity<byte[]> generateResponse(byte[] pdfBytes, HttpHeaders headers) {
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "pdf1.pdf";
    
        headers.add("content-disposition", "inline;filename=" + filename);
    
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(pdfBytes, headers, HttpStatus.OK);
        return response;
    }
}
