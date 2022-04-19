package net.etfbl.ip.vm.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.parser.Line;
import net.etfbl.ip.vm.models.entities.Log;
import net.etfbl.ip.vm.models.entities.LogEntity;
import net.etfbl.ip.vm.repositories.LogRepository;
import net.etfbl.ip.vm.util.UserTokenContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@EnableScheduling
public class LoggingService {

    private static File logFile = new File("src/main/resources/logs/logs.txt");
    private static File pdf = new File("src/main/resources/logs/logs.pdf");
    @Autowired
    private UserTokenContainer userTokenContainer;
    @Autowired
    private LogRepository logRepository;

    public void log(Log log) {
//        System.out.println(log);
        try {
            Files.write(logFile.toPath(), (log.toString() + "\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String convertToPDF() throws Exception {
        Document pdfDoc = new Document(PageSize.A4);

        PdfWriter.getInstance(pdfDoc, new FileOutputStream(pdf))
                .setPdfVersion(PdfWriter.PDF_VERSION_1_7);
        pdfDoc.open();

        Font myfont = new Font();
        myfont.setStyle(Font.NORMAL);
        myfont.setSize(9);
        pdfDoc.add(new Paragraph("\n"));

        BufferedReader br = new BufferedReader(new FileReader(logFile));
        String strLine;
        while ((strLine = br.readLine()) != null) {
            Paragraph para = new Paragraph("\n" + strLine + "\n", myfont);
            para.setAlignment(Element.ALIGN_JUSTIFIED);
            Chunk linebreak = new Chunk(new DottedLineSeparator());

            pdfDoc.add(para);
            pdfDoc.add(linebreak);
        }
        pdfDoc.close();
        br.close();
        return pdf.getAbsolutePath();
    }

//        @Scheduled(cron = "0 */1 * * * *")
    @Scheduled(cron = "0 0 */1  * * *")
    public void hourlyUserLog()
    {
       Long n = getNumberOfActiveUsers();
        System.out.println( new Timestamp(new Date().getTime()) + "  |  Online users:  " + n);

        logRepository.save(new LogEntity(0 ,new Timestamp(new Date().getTime()), n.intValue() ));
    }

    public long getNumberOfActiveUsers(){
        return  userTokenContainer.getUserTokens().stream().filter(
                e -> {
                    String token = e.getValue();
                    Date date = new Date(Long.parseLong(token));
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.add(Calendar.HOUR_OF_DAY, 1);
                    return !calendar.getTime().before(new Date());
                }
        ).count();
    }

    public List<LogEntity> getHourlyReport(){
        return logRepository.findAll();
    }
}
