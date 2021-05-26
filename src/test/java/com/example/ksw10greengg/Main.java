package com.example.ksw10greengg;

import java.security.Timestamp;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();

//        Timestamp timestamp = new Timestamp(cal.get);
        long l1 = setTimeMillis(cal);
        cal.add(Calendar.DAY_OF_MONTH,1);
        long l2 = setTimeMillis(cal);

        System.out.println(l1-l2);


    }

    private static long setTimeMillis(Calendar cal){
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        return cal.getTimeInMillis();
    }


}
