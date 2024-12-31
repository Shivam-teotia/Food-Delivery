package org.shivam.fooddelivery.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User customer;

    @ManyToOne
    private Restaurant restaurant;

    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date sentAt;

    private boolean readStatus;
}
