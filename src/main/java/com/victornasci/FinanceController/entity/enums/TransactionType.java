package com.victornasci.FinanceController.entity.enums;


public enum TransactionType {
    INCOME(1),
    EXPENSE(2);


    private final int code;

    TransactionType(int code) {
        this.code = code;
    }


    public int getCode() {
        return code;
    }

    public static TransactionType fromCode(int code){
        for(TransactionType type : TransactionType.values()){
            if(type.getCode() == code){
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid TransactionType code: " + code);
    }

}
