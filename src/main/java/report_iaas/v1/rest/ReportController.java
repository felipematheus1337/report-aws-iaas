package report_iaas.v1.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import report_iaas.v1.domain.Report;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ReportController {

    @PostMapping
    public ResponseEntity<List<Report>> createReport(@RequestBody List<Report> reportList) {

    }
}
