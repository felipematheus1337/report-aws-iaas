package report_iaas.v1.rest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import report_iaas.v1.domain.Report;
import report_iaas.v1.service.ReportService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReportController {

    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody Report report, HttpServletRequest request) {

        String sessionId = request.getRequestedSessionId();

        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(report, sessionId));

    }

    @GetMapping
    public ResponseEntity<List<Report>> get() {
        return ResponseEntity.ok(service.list());
    }
}
