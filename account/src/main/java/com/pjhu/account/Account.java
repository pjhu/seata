package com.pjhu.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    private Long id;
    private Integer money;

    public Account(Integer money) {
        this.id = IdGenerator.nextIdentity();
        this.money = money;
    }

    public void decrease(int amount) {
        this.money -= amount;
    }
}
