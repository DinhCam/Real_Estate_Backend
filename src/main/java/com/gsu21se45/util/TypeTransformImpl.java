package com.gsu21se45.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;

public class TypeTransformImpl {
    public static LocalDate castObjectToLocalDate(Object field) {
        if (field != null) {
            return ((Timestamp) field).toLocalDateTime().toLocalDate();
        }
        return null;
    }

    public static Long castObjectToLong(Object field) {
        if (field != null) {
            return ((Integer) field).longValue();
        }
        return null;
    }

    public static Integer castObjectToInt(Object field) {
        if (field != null) {
            return (Integer) field;
        }
        return null;
    }

    public static BigInteger castObjectToBigInt(Object field) {
        if (field != null) {
            return (BigInteger) field;
        }
        return null;
    }

    public static BigDecimal castObjectToBigDecimal(Object field) {
        if (field != null) {
            return (BigDecimal) field;
        }
        return null;
    }

    public static String castObjectToString(Object field) {
        if (field != null) {
            return (String) field;
        }
        return "";
    }

    public static Boolean castObjectToBoolean(Object field) {
        if (field != null) {
            return (Boolean) field;
        }
        return null;
    }
}