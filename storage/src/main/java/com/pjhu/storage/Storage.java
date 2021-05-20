package com.pjhu.storage;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STORAGE")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Storage {
    @Id
    private Long id;
    private Integer count;

    public Storage(Integer count) {
        this.id = IdGenerator.nextIdentity();
        this.count = count;
    }

    public void decrease(Integer count) {
        this.count -= count;
    }
}
