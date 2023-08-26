
/*
 * @Author: James Aworo
 * @Email: james.aworo@outlook.com
 * @Year: 2020
 *
 * @Project: Schoolbox
 */

package com.jamesportal.slms.modules.SLMS_StudentManagement.service;

import com.jamesportal.slms.modules.SLMS_StudentManagement.dto.StudentProfileDTO;
import com.jamesportal.slms.modules.SLMS_StudentManagement.dto.StudentSearchParamDTO;
import com.jamesportal.slms.modules.SLMS_StudentManagement.entity.Student;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StudentReportService {
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	private ServletContext context;
	
	@Autowired
	StudentServiceImpl studentService;
	
	public String generateReportToPDF(List<StudentProfileDTO> resultSet, Map<String, Object> parameters  ) {
		String generatedReportPath = "";
		try {
			Path resourceDirectory = Paths.get("src", "main", "resources", "reports");
			String reportPath = resourceDirectory.toFile().getAbsolutePath();
			
			InputStream resource = new ClassPathResource("reports/rpt_studentReport.jrxml").getInputStream();
			JasperReport jasperReport = JasperCompileManager.compileReport(resource);
			
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(resultSet);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);
			
			JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "/StudentReport.pdf");
			generatedReportPath = reportPath + "/StudentReport.pdf";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return generatedReportPath;
	}
	
	public void generateReportToXLSX(List<StudentProfileDTO> data, HttpServletResponse response, Map<String, Object> parameters) {
	
		try{
			InputStream  jasperStream = this.getClass().getResourceAsStream("/reports/rpt_studentReport.jrxml");
			JasperDesign design = JRXmlLoader.load(jasperStream);
			JasperReport report = JasperCompileManager.compileReport(design);
			JRDataSource jrDataSource = new JRBeanCollectionDataSource(data);
			parameters.put("dataSource", jrDataSource);
			parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);

			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, jrDataSource);
			
			response.setContentType("application/x-xls");
			response.setHeader("Content-Disposition", "inline; filename=STUDENT REPORT.xls");
			
			final OutputStream outputStream = response.getOutputStream();


			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));


            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();

			configuration.setOnePagePerSheet(false);
			configuration.setRemoveEmptySpaceBetweenRows(true);
			configuration.setWhitePageBackground(true);

			configuration.setDetectCellType(true);
			configuration.setCollapseRowSpan(false);
			configuration.setIgnoreCellBackground(true);
			configuration.setIgnoreCellBorder(true);
			exporter.setConfiguration(configuration);

			exporter.exportReport();
			
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void downloadReportFileFromPath(String fullPath, HttpServletResponse response, String fileName){
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		if (file.exists()){
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename="+fileName);
				OutputStream outputStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer))!= -1){
					outputStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outputStream.close();
				file.delete();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


    public void generateReportPDFAjax(StudentSearchParamDTO searchParamDTO, HttpServletResponse response) {
        try{
            InputStream jasperStream = this.getClass().getResourceAsStream("report/rpt_student_search.jrxml");
            JasperDesign design = JRXmlLoader.load(jasperStream);
            JasperReport report = JasperCompileManager.compileReport(design);
            
            Map<String, Object> parameterMap = new HashMap<>();
            
            List<Student> studentList = studentService.getStudentReportParameter(searchParamDTO);
            JRDataSource jrDataSource = new JRBeanCollectionDataSource(studentList);
            
            parameterMap.put("datasource", jrDataSource);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameterMap, jrDataSource);
            response.setContentType("application/x-pdf");
            response.setHeader("Content-Disposition", "inline; filename=ReportData.pdf" );
            
            final OutputStream outputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }
    }
    
}

