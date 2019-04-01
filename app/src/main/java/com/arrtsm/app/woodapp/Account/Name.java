package com.arrtsm.app.woodapp.Account;

public class Name implements IName{
    private  final String firstName;
    private final String middleName;
    private final String lastName;
    private Name(Builder builder){
        this.firstName=builder.firstName;
        this.middleName=builder.middleName;
        this.lastName=builder.lastName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }


    public static class Builder{
        private  String firstName;
        private String middleName;
        private String lastName;
        public Builder firstName(final String firstName){
            this.firstName=firstName;
            return this;
        }
        public Builder middleName(final String middleName){
            this.middleName=middleName;
            return this;
        }
        public Builder lastName(final String lastName){
            this.lastName=lastName;
            return this;
        }
        public Name build(){
            return new Name(this);
        }
    }
}
