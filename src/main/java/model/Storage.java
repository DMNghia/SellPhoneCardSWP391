package model;

import lombok.*;

import java.sql.Timestamp;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    private long id;
    private String serialNumber;
    private String cardNumber;
    private Timestamp expiredAt;
    private int productId;
    private boolean isUsed;
    private Timestamp createdAt;
    private int createdBy;
    private Timestamp updatedAt;
    private int updatedBy;
    private Timestamp deletedAt;
    private int deletedBy;
}
