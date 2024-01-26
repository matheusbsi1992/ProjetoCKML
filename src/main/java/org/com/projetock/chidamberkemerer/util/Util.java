package org.com.projetock.chidamberkemerer.util;

import org.com.projetock.chidamberkemerer.modelo.Model;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static org.com.projetock.chidamberkemerer.normalizacao.PreProcessamento.getModelList;

public class Util {

    // Mapeamento para a condicão da "Classe"
   public static Map<String, String> mapeamentoClasse = Map.of(
            "info.archinnov.achilles", "1",
            "org.assertj.core", "2",
            "org.apache.commons.io", "3",
            "org.joda", "4",
            "org.apache.commons.math", "5",
            "org.apache.hadoop", "6",
            "jackrabbit", "7",
            "jfree", "8",
            "lucene", "9",
            "org.apache.poi", "10"
    );

    public static String tipoDecimal(Double media){
        DecimalFormat df = new DecimalFormat("0.00");
        return  df.format(media);
    }


}