package report_iaas.v1.domain;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReportItens {

    private String author;

    private BigDecimal value;

    private LocalDateTime date;

    public ReportItens() {
    }

    public ReportItens(String author, BigDecimal value, LocalDateTime date) {
        this.author = author;
        this.value = value;
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
