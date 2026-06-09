package se.lexicon.entity;
// Represents the lifecycle state of an order. Stored as a String in the database.
public enum OrderStatus {
    NEW,
    PAID,
    SHIPPED,
    CANCELLED
}
