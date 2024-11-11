package com.pomog.schedulerV1.process_scheduler.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "room", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"code"})
})
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class RoomEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ID; // Generate UUID when an instance is created
    
    @Column(name = "code")
    @NonNull
    private String code;
}
