package ru.otus.message_server.messageSystem;

public class MessageSystemContext {
    private Address frontAddress;
    private Address dbAddress;

    public Address getFrontAddress() {
        return frontAddress;
    }

    public void setFrontAddress(Address frontAddress) {
        this.frontAddress = frontAddress;
    }

    public Address getDbAddress() {
        return dbAddress;
    }

    public void setDbAddress(Address dbAddress) {
        this.dbAddress = dbAddress;
    }

    @Override
    public String toString() {
        return "MessageSystemContext{" +
                "frontAddress=" + frontAddress +
                ", dbAddress=" + dbAddress +
                '}';
    }
}
