package com.example.demo.expenses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ExpenseReportService {
	
	@Autowired
	private ExpenseJpaRepo expenseJpaRepo;
	
	public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
		
	//String path ="C:\\Users\\PL 62 7RC\\Downloads\\Reports";	
	List<Expense> expenses = expenseJpaRepo.findAll();
	
	File file = ResourceUtils.getFile("classpath:Simple_Blue.jrxml");
	
	JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	
	JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(expenses);
	
	Map<String,Object> parameters = new HashMap<>();
	
	parameters.put("Created by","Jasper Reports");
	//parameters.put("Total","0.00");
	
	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
	
	 if (reportFormat.equalsIgnoreCase("html")){
         JasperExportManager.exportReportToHtmlFile(jasperPrint,"C:\\Users\\PL 62 7RC\\Downloads\\Reports"+"\\Expense.html");
       }
     if (reportFormat.equalsIgnoreCase("pdf")){
           JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\PL 62 7RC\\Downloads\\Reports"+"\\Expense.pdf");
       }
	
     
     return "Report has generated in downlord folder";
	}

}
