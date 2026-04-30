package report_iaas.v1.service;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import report_iaas.v1.domain.Report;
import report_iaas.v1.repository.ReportRepository;

import java.util.List;

@Service
public class ReportService {

    private final ReportRepository repository;
    private static final Logger log = LoggerFactory.getLogger(ReportService.class);

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Report create(Report report, String sessionId) {
        log.info("Creating a Report... with request session id: {} :::", sessionId);

        return repository.save(report);
    }
}
