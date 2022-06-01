package com.pizzafactory.project.entities.keys;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderMenuKey implements Serializable {

    private Long orderId;
    private Long menuId;

    public OrderMenuKey() {
    }

    public OrderMenuKey(Long orderId, Long menuId) {
        this.orderId = orderId;
        this.menuId = menuId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderMenuKey that = (OrderMenuKey) o;
        return orderId.equals(that.orderId) && menuId.equals(that.menuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, menuId);
    }
}
