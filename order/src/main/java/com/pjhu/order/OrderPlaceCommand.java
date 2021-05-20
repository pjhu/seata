package com.pjhu.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlaceCommand {

    private Long userId;
    private Long productId;
    private Integer productCount;
}
