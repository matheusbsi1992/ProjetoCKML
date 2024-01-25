package org.com.projetock.chidamberkemerer.util;

import java.text.DecimalFormat;

public class Util {

    public static String tipoDecimal(Double media){
        DecimalFormat df = new DecimalFormat("0.00");
        return  df.format(media);
    }
}