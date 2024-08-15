package com.nicson.apipostgres.models;

import java.io.Serializable;
import java.util.Date;

import com.nicson.apipostgres.models.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_order", sequenceName = "seq_order")
    @GeneratedValue(generator = "seq_order", strategy = GenerationType.IDENTITY)
    private long id;
    private Date moment;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    public Order() {
    }

    public Order(long id, Date moment, OrderStatus status, User user) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((moment == null) ? 0 : moment.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (id != other.id)
            return false;
        if (moment == null) {
            if (other.moment != null)
                return false;
        } else if (!moment.equals(other.moment))
            return false;
        if (status != other.status)
            return false;
        return true;
    }
}
