package com.redhat.energy;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class Trade {

    private final Integer tradeId;
    private final String exchange;
    private final String instrument;
    private final String side;
    private final BigDecimal quantity;
    private final BigDecimal price;
    private final Long tradeTimestamp;

    @ProtoFactory
    public Trade(Integer tradeId, String exchange, String instrument, String side, BigDecimal quantity, BigDecimal price, Long tradeTimestamp) {
        this.tradeId = tradeId;
        this.exchange = exchange;
        this.instrument = instrument;
        this.side = side;
        this.quantity = quantity;
        this.price = price;
        this.tradeTimestamp = tradeTimestamp;
    }

    @ProtoField(number = 1, required = true)
    public Integer getTradeId() {
        return tradeId;
    }

    @ProtoField(number = 2, required = true)
    public String getExchange() {
        return exchange;
    }

    @ProtoField(number = 3, required = true)
    public String getInstrument() {
        return instrument;
    }

    @ProtoField(number = 4, required = true)
    public String getSide() {
        return side;
    }

    @ProtoField(number = 5, required = true)
    public BigDecimal getQuantity() {
        return quantity;
    }

    @ProtoField(number = 6, required = true)
    public BigDecimal getPrice() {
        return price;
    }

    @ProtoField(number = 7, required = true)
    public Long getTradeTimestamp() {
        return tradeTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return Objects.equals(tradeId, trade.tradeId) && Objects.equals(exchange, trade.exchange) && Objects.equals(instrument, trade.instrument) && Objects.equals(side, trade.side) && Objects.equals(quantity, trade.quantity) && Objects.equals(price, trade.price) && Objects.equals(tradeTimestamp, trade.tradeTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeId, exchange, instrument, side, quantity, price, tradeTimestamp);
    }

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId=" + tradeId +
                ", exchange='" + exchange + '\'' +
                ", instrument='" + instrument + '\'' +
                ", side='" + side + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", tradeTimestamp=" + tradeTimestamp +
                '}';
    }

}
