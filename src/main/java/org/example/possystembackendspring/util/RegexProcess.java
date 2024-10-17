package org.example.possystembackendspring.util;

public class RegexProcess {
    public static final String CUSTOMER_ID_REGEX = "^C\\d{3}$";
    public static final String ITEM_ID_REGEX = "I\\d{2}-\\d{3}";
    public static final String ORDER_ID_REGEX = "OID-\\d{3}";
    public static final String CONTACT_REGEX = "^(?:\\+94|94|0)?7\\d{8}$";
}
