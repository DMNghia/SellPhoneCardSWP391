package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private long id;
    private User user;
    private String status;
    private int totalAmount;
    private boolean isDelete;
    private Timestamp createdAt;
    private User createdBy;
    private Timestamp updatedAt;
    private User updatedBy;

    private Timestamp deletedAt;
    private User deletedBy;

    private List<Storage> listStorage;


}
