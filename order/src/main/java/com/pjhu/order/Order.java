package com.pjhu.order;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_ORDER")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private Long id;
    private Long accountId;
    private Long productId;
    private Integer productCount;
    private Integer orderAmountTotal;

    public Order(Long accountId, Long productId, Integer productCount, Integer orderAmountTotal) {
        this.id = IdGenerator.nextIdentity();
        this.accountId = accountId;
        this.productId = productId;
        this.productCount = productCount;
        this.orderAmountTotal = orderAmountTotal;
    }
}
