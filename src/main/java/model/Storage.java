package model;

import lombok.*;
import org.json.JSONObject;

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
    private Product product;
    private boolean isUsed;
    private boolean isDelete;
    private Timestamp createdAt;
    private User createdBy;
    private Timestamp updatedAt;
    private User updatedBy;
    private Timestamp deletedAt;
    private User deletedBy;

    public String toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("serialNumber", serialNumber);
        json.put("cardNumber", cardNumber);
        json.put("expiredAt", expiredAt);

        return json.toString();
    }
}
