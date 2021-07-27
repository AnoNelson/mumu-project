package gov.rw.brd.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by hp on 6/24/2021.
 */
@Data
public class RequestCheck {
    private String requestId;
    private String comments;
    private BigDecimal approvedAmount;

    @Override
    public String toString() {
        return "RequestCheck{" +
                "requestId='" + requestId + '\'' +
                ", comments='" + comments + '\'' +
                ", approvedAmount=" + approvedAmount +
                '}';
    }
}
