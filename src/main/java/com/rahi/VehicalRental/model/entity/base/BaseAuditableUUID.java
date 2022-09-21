package com.rahi.VehicalRental.model.entity.base;

import com.rahi.VehicalRental.util.UUIDUtility;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@AllArgsConstructor
public class BaseAuditableUUID extends BaseAuditable implements Serializable {
    @Id
    private UUID id;

    public BaseAuditableUUID() {
        this.id = UUIDUtility.generate();
    }
}
