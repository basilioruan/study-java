package com.study.java.jasper.services;

import com.study.java.jasper.infrastructure.model.Student;
import com.study.java.jasper.infrastructure.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentService {

    public static final String CERTIFICATES = "classpath:jasper/certificates";

    public static final String JRXML_FILE = "cert.jrxml";

    public static final String DESTINATION_PDF = "C:\\jasper-report\\";

    public static final String DIRECTORY_SAVED_CERTIFICATES = "saved-certificates";

    public static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public void generateStudentCertificate(Long id) {
        Student student = this.studentRepository.findById(id).orElseThrow();
        this.generate(student);
    }

    public void generate(Student student) {
        Map<String, Object> params = this.buildParams(student);

        try {
            String path = CERTIFICATES + JRXML_FILE;
            String folderPdfDirectory = this.getSaveDirectory(DIRECTORY_SAVED_CERTIFICATES);
            String absolutePath = this.getAbsolutePath(path);
            JasperReport report = JasperCompileManager.compileReport(path);
            LOGGER.info("Report compilado: {}", absolutePath);
            JasperPrint print = JasperFillManager.fillReport(report, params, new JREmptyDataSource());
            LOGGER.info("Jasper print");
            JasperExportManager.exportReportToPdfFile(print, folderPdfDirectory);
        } catch (JRException | FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Map<String, Object> buildParams(Student student) {
        return Map.of(
            "nome", student.getName(),
            "curso", student.getCourse(),
            "cargaHoraria", student.getWorkLoad(),
            "dataInicioCurso", student.getInitialCourseDate(),
            "dataFimCurso", student.getFinishCourseDate()
        );
    }

    private String getAbsolutePath(String path) throws FileNotFoundException {
        return ResourceUtils.getFile(path).getAbsolutePath();
    }

    private String getSaveDirectory(String directoryName) {
        this.createDirectory(directoryName);
        return DESTINATION_PDF + directoryName.concat(".pdf");
    }

    private void createDirectory(String name) {
        File dir = new File(name);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

}
