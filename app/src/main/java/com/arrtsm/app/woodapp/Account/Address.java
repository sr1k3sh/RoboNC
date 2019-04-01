package com.arrtsm.app.woodapp.Account;

public class Address implements IAddress{
    private final int houseNumber;
    private final String street;
    private final String zipCode;
    private final String city;
    private Address(Builder builder){
        this.houseNumber=builder.houseNumber;
        this.street=builder.street;
        this.zipCode=builder.zipCode;
        this.city=builder.city;
    }

    @Override
    public int getHouseNumber() {
        return houseNumber;
    }

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String getCity() {
        return city;
    }

    public static class Builder{
        private int houseNumber;
        private String street;
        private String zipCode;
        private String city;
        public Builder houseNumber(final int houseNumber){
            this.houseNumber = houseNumber;
            return this;
        }
        public Builder street(final String street){
            this.street = street;
            return this;
        }
        public Builder zipCode(final String zipCode){
            this.zipCode = zipCode;
            return this;
        }
        public Builder city(final String city){
            this.city = city;
            return this;
        }
        public Address build(){
            return new Address(this);
        }
    }
}
