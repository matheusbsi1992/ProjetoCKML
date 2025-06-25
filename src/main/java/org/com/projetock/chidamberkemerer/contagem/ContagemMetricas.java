package org.com.projetock.chidamberkemerer.contagem;

import org.com.projetock.chidamberkemerer.modelo.Model;
import org.com.projetock.chidamberkemerer.normalizacao.PreProcessamento;
import org.com.projetock.chidamberkemerer.util.Util;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static org.com.projetock.chidamberkemerer.util.Util.tipoDecimal;

public class ContagemMetricas extends PreProcessamento {

    //                    "info.archinnov.achilles", "achilles",
    //                    "org.assertj.core", "assertj",
    //                    "org.apache.commons.io", "commons.io",
    //                    "org.joda", "joda.time",
    //                    "org.apache.commons.math", "math",
    //                    "org.apache.hadoop", "hadoop",
    //                    "jackrabbit", "jackrabbit",
    //                    "jfree", "jfreechart",
    //                    "lucene", "lucene",
    //                    "org.apache.poi", "poi"

    /*
             "info.archinnov.achilles", "1", "achilles"        ---> 01 - 02,
                   "org.assertj.core", "2", "assertj"          ---> 03 - 04,
                   "org.apache.commons.io", "3", "commons.io"  ---> 05 - 06,
                   "org.joda", "4", "joda.time"                ---> 07 - 08,
                   "org.apache.commons.math", "5", "math"      ---> 09 - 10,
                   "org.apache.hadoop", "6", "hadoop"          ---> 11 - 12,
                   "jackrabbit", "7", "jackrabbit"             ---> 13 - 14,
                   "jfree", "8", "jfreechart"                  ---> 15 - 16,
                   "lucene", "9", "lucene"                     ---> 17 - 18,
                   "org.apache.poi", "10" "poi"                ---> 19 - 20
           */
    public static void TestUsado(){
        Model menorValorMetricaAChillesCBO = new Model();
        //for (Model modelo: getModelList()){
            //System.out.println(modelo.getCLASSE());
            menorValorMetricaAChillesCBO = getModelList()
                    .stream()
                    .filter(model -> model.getCLASSE().equals("1"))
                    .max(Comparator.comparingInt(model -> Integer.parseInt(model.getCBO())))
                    .orElse(null);
          /*  System.out.println("\n<<<--CLASSES DEFINIDAS COMO TESTE-->>>\n");
            System.out.println(modelo.getUOTESTED());*/
        //}
        System.out.println("-----VALOR-----");
        System.out.println(menorValorMetricaAChillesCBO.getCBO());
        System.out.println(menorValorMetricaAChillesCBO.getCLASSE());
    }
    public static String quantidadeClasses() {
        // Calcular a soma dos números de todas as classes identificadas para tentativa de teste
        return String.format("|VALOR TOTAL DE CLASSES IDENTIFICADAS: %d|"
                , getModelList().stream().count());
    }

    public static String quantidadePorTipodeClasse() {

        long quantidadeTestAchilles = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .count();

        long quantidadeTestAssertJ = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .count();

        long quantidadeTestCommonsio = getModelList().stream()
                .filter(model ->model.getCLASSE().equals("3"))
                .count();

        long quantidadeTestJodaTime = getModelList().stream()
                .filter(model ->model.getCLASSE().equals("4"))
                .count();

        long quantidadeTestMath = getModelList().stream()
                .filter(model ->model.getCLASSE().equals("5"))
                .count();

        long quantidadeTestHadoop = getModelList().stream()
                .filter(model ->model.getCLASSE().equals("6"))
                .count();

        long quantidadeTestJackRabbit = getModelList().stream()
                .filter(model ->model.getCLASSE().equals("7"))
                .count();

        long quantidadeTestJfreeChart = getModelList().stream()
                .filter(model ->model.getCLASSE().equals("8"))
                .count();

        long quantidadeTestLucene = getModelList().stream()
                .filter(model ->model.getCLASSE().equals("9"))
                .count();

        long quantidadeTestPoi = getModelList().stream()
                .filter(model ->model.getCLASSE().equals("10"))
                .count();

        return String.format("CLASSES IDENTIFICADAS COMO TESTAVEIS \n" +
                        "|ACHILLES: %d|ASSERTJ: %d|COMMONS IO: %d|JODA TIME: %d|MATH: %d|HADOOP: %d|JACKRABBIT: %d|JFREECHART: %d|LUCENE: %d|POI: %d|"
                , quantidadeTestAchilles
                , quantidadeTestAssertJ
                , quantidadeTestCommonsio
                , quantidadeTestJodaTime
                , quantidadeTestMath
                , quantidadeTestHadoop
                , quantidadeTestJackRabbit
                , quantidadeTestJfreeChart
                , quantidadeTestLucene
                , quantidadeTestPoi
        );
    }
   /* public static String quantidadePorTipodeClasseIdentificadosComoTestaveis() {

        long quantidadeTestAchilles = getModelList().stream()
                .filter(model -> model.getUOTESTED().equals("1"))
                .count();

        long quantidadeTestAssertJ = getModelList().stream()
                .filter(model -> model.getUOTESTED().equals("2"))
                .count();

        long quantidadeTestCommonsio = getModelList().stream()
                .filter(model -> model.getUOTESTED().equals("3"))
                .count();

        long quantidadeTestJodaTime = getModelList().stream()
                .filter(model -> model.getUOTESTED().equals("4"))
                .count();

        long quantidadeTestMath = getModelList().stream()
                .filter(model -> model.getUOTESTED().equals("5"))
                .count();

        long quantidadeTestHadoop = getModelList().stream()
                .filter(model -> model.getUOTESTED().equals("6"))
                .count();

        long quantidadeTestJackRabbit = getModelList().stream()
                .filter(model -> model.getUOTESTED().equals("7"))
                .count();

        long quantidadeTestJfreeChart = getModelList().stream()
                .filter(model -> model.getUOTESTED().equals("8"))
                .count();

        long quantidadeTestLucene = getModelList().stream()
                .filter(model -> model.getUOTESTED().equals("9"))
                .count();

        long quantidadeTestPoi = getModelList().stream()
                .filter(model -> model.getUOTESTED().equals("10"))
                .count();

        return String.format("CLASSES IDENTIFICADAS COMO TESTAVEIS \n" +
                        "|ACHILLES: %d|ASSERTJ: %d|COMMONS IO: %d|JODA TIME: %d|MATH: %d|HADOOP: %d|JACKRABBIT: %d|JFREECHART: %d|LUCENE: %d|POI: %d|"
                , quantidadeTestAchilles
                , quantidadeTestAssertJ
                , quantidadeTestCommonsio
                , quantidadeTestJodaTime
                , quantidadeTestMath
                , quantidadeTestHadoop
                , quantidadeTestJackRabbit
                , quantidadeTestJfreeChart
                , quantidadeTestLucene
                , quantidadeTestPoi
        );
    }*/
    public static String valorSomaAchillesMetricasCK() {

        double somaValorMetricaAchillesCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .mapToDouble(model -> Double.parseDouble(model.getCBO()))
                .sum();

        double somaValorMetricaAchillesDIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .mapToDouble(model -> Double.parseDouble(model.getDIT()))
                .sum();

        double somaValorMetricaAchillesLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .mapToDouble(model -> Double.parseDouble(model.getLCOM()))
                .sum();

        double somaValorMetricaAchillesNOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .mapToDouble(model -> Double.parseDouble(model.getNOC()))
                .sum();

        double somaValorMetricaAchillesRFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .mapToDouble(model -> Double.parseDouble(model.getRFC()))
                .sum();

        double somaValorMetricaAchillesWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .mapToDouble(model -> Double.parseDouble(model.getWMC()))
                .sum();

        return "SOMA VALORES CK - ACHILLES \n" +
                "|CLASSE ACHILLES CBO: " + somaValorMetricaAchillesCBO +
                "|CLASSE ACHILLES DIT: " + somaValorMetricaAchillesDIT +
                "|CLASSE ACHILLES LCOM: " + somaValorMetricaAchillesLCOM +
                "|CLASSE ACHILLES NOC: " + somaValorMetricaAchillesNOC +
                "|CLASSE ACHILLES RFC: " + somaValorMetricaAchillesRFC +
                "|CLASSE ACHILLES WMC: " + somaValorMetricaAchillesWMC;
    }

    public static String valorSomaAssertjMetricasCK() {

        double somaValorMetricaAssertjCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .mapToDouble(model -> Double.parseDouble(model.getCBO()))
                .sum();

        double somaValorMetricaAssertjDIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .mapToDouble(model -> Double.parseDouble(model.getDIT()))
                .sum();

        double somaValorMetricaAssertjLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .mapToDouble(model -> Double.parseDouble(model.getLCOM()))
                .sum();

        double somaValorMetricaAssertjNOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .mapToDouble(model -> Double.parseDouble(model.getNOC()))
                .sum();

        double somaValorMetricaAssertjRFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .mapToDouble(model -> Double.parseDouble(model.getRFC()))
                .sum();

        double somaValorMetricaAssertjWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .mapToDouble(model -> Double.parseDouble(model.getWMC()))
                .sum();

        return "SOMA VALORES CK - ASSERTJ \n" +
                "|CLASSE ASSERTJ CBO: " + somaValorMetricaAssertjCBO +
                "|CLASSE ASSERTJ DIT: " + somaValorMetricaAssertjDIT +
                "|CLASSE ASSERTJ LCOM: " + somaValorMetricaAssertjLCOM +
                "|CLASSE ASSERTJ NOC: " + somaValorMetricaAssertjNOC +
                "|CLASSE ASSERTJ RFC: " + somaValorMetricaAssertjRFC +
                "|CLASSE ASSERTJ WMC: " + somaValorMetricaAssertjWMC;
    }

    public static String valorSomaCommonsIOMetricasCK() {

        double somaValorMetricaCommonsIOCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .mapToDouble(model -> Double.parseDouble(model.getCBO()))
                .sum();

        double somaValorMetricaCommonsIODIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .mapToDouble(model -> Double.parseDouble(model.getDIT()))
                .sum();

        double somaValorMetricaCommonsIOLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .mapToDouble(model -> Double.parseDouble(model.getLCOM()))
                .sum();

        double somaValorMetricaCommonsIONOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .mapToDouble(model -> Double.parseDouble(model.getNOC()))
                .sum();

        double somaValorMetricaCommonsIORFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .mapToDouble(model -> Double.parseDouble(model.getRFC()))
                .sum();

        double somaValorMetricaCommonsIOWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .mapToDouble(model -> Double.parseDouble(model.getWMC()))
                .sum();

        return "SOMA VALORES CK - COMMONSIO \n" +
                "|CLASSE COMMONSIO CBO: " + somaValorMetricaCommonsIOCBO +
                "|CLASSE COMMONSIO DIT: " + somaValorMetricaCommonsIODIT +
                "|CLASSE COMMONSIO LCOM: " + somaValorMetricaCommonsIOLCOM +
                "|CLASSE COMMONSIO NOC: " + somaValorMetricaCommonsIONOC +
                "|CLASSE COMMONSIO RFC: " + somaValorMetricaCommonsIORFC +
                "|CLASSE COMMONSIO WMC: " + somaValorMetricaCommonsIOWMC;
    }

    public static String valorSomaJodaMetricasCK() {

        double somaValorMetricaJodaCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .mapToDouble(model -> Double.parseDouble(model.getCBO()))
                .sum();

        double somaValorMetricaJodaDIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .mapToDouble(model -> Double.parseDouble(model.getDIT()))
                .sum();

        double somaValorMetricaJodaLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .mapToDouble(model -> Double.parseDouble(model.getLCOM()))
                .sum();

        double somaValorMetricaJodaNOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .mapToDouble(model -> Double.parseDouble(model.getNOC()))
                .sum();

        double somaValorMetricaJodaRFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .mapToDouble(model -> Double.parseDouble(model.getRFC()))
                .sum();

        double somaValorMetricaJodaWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .mapToDouble(model -> Double.parseDouble(model.getWMC()))
                .sum();

        return "SOMA VALORES CK - JODA.TIME \n" +
                "|CLASSE JODA.TIME CBO: " + somaValorMetricaJodaCBO +
                "|CLASSE JODA.TIME DIT: " + somaValorMetricaJodaDIT +
                "|CLASSE JODA.TIME LCOM: " + somaValorMetricaJodaLCOM +
                "|CLASSE JODA.TIME NOC: " + somaValorMetricaJodaNOC +
                "|CLASSE JODA.TIME RFC: " + somaValorMetricaJodaRFC +
                "|CLASSE JODA.TIME WMC: " + somaValorMetricaJodaWMC;
    }

    public static String valorSomaMathMetricasCK() {

        double somaValorMetricaMathCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .mapToDouble(model -> Double.parseDouble(model.getCBO()))
                .sum();

        double somaValorMetricaMathDIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .mapToDouble(model -> Double.parseDouble(model.getDIT()))
                .sum();

        double somaValorMetricaMathLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .mapToDouble(model -> Double.parseDouble(model.getLCOM()))
                .sum();

        double somaValorMetricaMathNOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .mapToDouble(model -> Double.parseDouble(model.getNOC()))
                .sum();

        double somaValorMetricaMathRFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .mapToDouble(model -> Double.parseDouble(model.getRFC()))
                .sum();

        double somaValorMetricaMathWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .mapToDouble(model -> Double.parseDouble(model.getWMC()))
                .sum();

        return "SOMA VALORES CK - MATH \n" +
                "|CLASSE MATH CBO: " + somaValorMetricaMathCBO +
                "|CLASSE MATH DIT: " + somaValorMetricaMathDIT +
                "|CLASSE MATH LCOM: " + somaValorMetricaMathLCOM +
                "|CLASSE MATH NOC: " + somaValorMetricaMathNOC +
                "|CLASSE MATH RFC: " + somaValorMetricaMathRFC +
                "|CLASSE MATH WMC: " + somaValorMetricaMathWMC;
    }

    public static String valorSomaHadoopMetricasCK() {

        double somaValorMetricaHadoopCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .mapToDouble(model -> Double.parseDouble(model.getCBO()))
                .sum();

        double somaValorMetricaHadoopDIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .mapToDouble(model -> Double.parseDouble(model.getDIT()))
                .sum();

        double somaValorMetricaHadoopLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .mapToDouble(model -> Double.parseDouble(model.getLCOM()))
                .sum();

        double somaValorMetricaHadoopNOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .mapToDouble(model -> Double.parseDouble(model.getNOC()))
                .sum();

        double somaValorMetricaHadoopRFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .mapToDouble(model -> Double.parseDouble(model.getRFC()))
                .sum();

        double somaValorMetricaHadoopWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .mapToDouble(model -> Double.parseDouble(model.getWMC()))
                .sum();

        return "SOMA VALORES CK - HADOOP \n" +
                "|CLASSE HADOOP CBO: " + somaValorMetricaHadoopCBO +
                "|CLASSE HADOOP DIT: " + somaValorMetricaHadoopDIT +
                "|CLASSE HADOOP LCOM: " + somaValorMetricaHadoopLCOM +
                "|CLASSE HADOOP NOC: " + somaValorMetricaHadoopNOC +
                "|CLASSE HADOOP RFC: " + somaValorMetricaHadoopRFC +
                "|CLASSE HADOOP WMC: " + somaValorMetricaHadoopWMC;
    }

    public static String valorSomaJackRabbitMetricasCK() {

        double somaValorMetricaJackrabbitCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .mapToDouble(model -> Double.parseDouble(model.getCBO()))
                .sum();

        double somaValorMetricaJackrabbitDIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .mapToDouble(model -> Double.parseDouble(model.getDIT()))
                .sum();

        double somaValorMetricaJackrabbitLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .mapToDouble(model -> Double.parseDouble(model.getLCOM()))
                .sum();

        double somaValorMetricaJackrabbitNOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .mapToDouble(model -> Double.parseDouble(model.getNOC()))
                .sum();

        double somaValorMetricaJackrabbitRFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .mapToDouble(model -> Double.parseDouble(model.getRFC()))
                .sum();

        double somaValorMetricaJackrabbitWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .mapToDouble(model -> Double.parseDouble(model.getWMC()))
                .sum();

        return "SOMA VALORES CK - JACKRABBIT \n" +
                "|CLASSE JACKRABBIT CBO: " + somaValorMetricaJackrabbitCBO +
                "|CLASSE JACKRABBIT DIT: " + somaValorMetricaJackrabbitDIT +
                "|CLASSE JACKRABBIT LCOM: " + somaValorMetricaJackrabbitLCOM +
                "|CLASSE JACKRABBIT NOC: " + somaValorMetricaJackrabbitNOC +
                "|CLASSE JACKRABBIT RFC: " + somaValorMetricaJackrabbitRFC +
                "|CLASSE JACKRABBIT WMC: " + somaValorMetricaJackrabbitWMC;
    }

    public static String valorSomaJfreeChartMetricasCK() {

        double somaValorMetricaJackrabbitCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .mapToDouble(model -> Double.parseDouble(model.getCBO()))
                .sum();

        double somaValorMetricaJackrabbitDIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .mapToDouble(model -> Double.parseDouble(model.getDIT()))
                .sum();

        double somaValorMetricaJackrabbitLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .mapToDouble(model -> Double.parseDouble(model.getLCOM()))
                .sum();

        double somaValorMetricaJackrabbitNOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .mapToDouble(model -> Double.parseDouble(model.getNOC()))
                .sum();

        double somaValorMetricaJackrabbitRFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .mapToDouble(model -> Double.parseDouble(model.getRFC()))
                .sum();

        double somaValorMetricaJackrabbitWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .mapToDouble(model -> Double.parseDouble(model.getWMC()))
                .sum();

        return "SOMA VALORES CK - JACKRABBIT \n" +
                "|CLASSE JACKRABBIT CBO: " + somaValorMetricaJackrabbitCBO +
                "|CLASSE JACKRABBIT DIT: " + somaValorMetricaJackrabbitDIT +
                "|CLASSE JACKRABBIT LCOM: " + somaValorMetricaJackrabbitLCOM +
                "|CLASSE JACKRABBIT NOC: " + somaValorMetricaJackrabbitNOC +
                "|CLASSE JACKRABBIT RFC: " + somaValorMetricaJackrabbitRFC +
                "|CLASSE JACKRABBIT WMC: " + somaValorMetricaJackrabbitWMC;
    }

    public static String valorSomaLuceneMetricasCK() {

        double somaValorMetricaLuceneCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .mapToDouble(model -> Double.parseDouble(model.getCBO()))
                .sum();

        double somaValorMetricaLuceneDIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .mapToDouble(model -> Double.parseDouble(model.getDIT()))
                .sum();

        double somaValorMetricaLuceneLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .mapToDouble(model -> Double.parseDouble(model.getLCOM()))
                .sum();

        double somaValorMetricaLuceneNOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .mapToDouble(model -> Double.parseDouble(model.getNOC()))
                .sum();

        double somaValorMetricaLuceneRFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .mapToDouble(model -> Double.parseDouble(model.getRFC()))
                .sum();

        double somaValorMetricaLuceneWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .mapToDouble(model -> Double.parseDouble(model.getWMC()))
                .sum();

        return "SOMA VALORES CK - LUCENE \n" +
                "|CLASSE LUCENE CBO: " + somaValorMetricaLuceneCBO +
                "|CLASSE LUCENE DIT: " + somaValorMetricaLuceneDIT +
                "|CLASSE LUCENE LCOM: " + somaValorMetricaLuceneLCOM +
                "|CLASSE LUCENE NOC: " + somaValorMetricaLuceneNOC +
                "|CLASSE LUCENE RFC: " + somaValorMetricaLuceneRFC +
                "|CLASSE LUCENE WMC: " + somaValorMetricaLuceneWMC;
    }

    public static String valorSomaPoiMetricasCK() {

        double somaValorMetricaPoiCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .mapToDouble(model -> Double.parseDouble(model.getCBO()))
                .sum();

        double somaValorMetricaPoiDIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .mapToDouble(model -> Double.parseDouble(model.getDIT()))
                .sum();

        double somaValorMetricaPoiLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .mapToDouble(model -> Double.parseDouble(model.getLCOM()))
                .sum();

        double somaValorMetricaPoiNOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .mapToDouble(model -> Double.parseDouble(model.getNOC()))
                .sum();

        double somaValorMetricaPoiRFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .mapToDouble(model -> Double.parseDouble(model.getRFC()))
                .sum();

        double somaValorMetricaPoiWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .mapToDouble(model -> Double.parseDouble(model.getWMC()))
                .sum();

        return "SOMA VALORES CK - POI \n" +
                "|CLASSE POI CBO: " + somaValorMetricaPoiCBO +
                "|CLASSE POI DIT: " + somaValorMetricaPoiDIT +
                "|CLASSE POI LCOM: " + somaValorMetricaPoiLCOM +
                "|CLASSE POI NOC: " + somaValorMetricaPoiNOC +
                "|CLASSE POI RFC: " + somaValorMetricaPoiRFC +
                "|CLASSE POI WMC: " + somaValorMetricaPoiWMC;
    }

    public static String valorMaiorPoiMetricasCK() {
        Model maiorValorMetricaPoiCBO = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getCBO())))
                .orElse(null);

        Model maiorValorMetricaPoiDIT = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model maiorValorMetricaPoiLCOM = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model maiorValorMetricaPoiNOC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaPoiRFC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaPoiWMC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MAIOR VALORES CK - POI \n" +
                "|CLASSE POI CBO: " + Double.parseDouble(maiorValorMetricaPoiCBO.getCBO()) +
                "|CLASSE POI DIT: " + Double.parseDouble(maiorValorMetricaPoiDIT.getDIT()) +
                "|CLASSE POI LCOM: " + Double.parseDouble(maiorValorMetricaPoiLCOM.getLCOM()) +
                "|CLASSE POI NOC: " + Double.parseDouble(maiorValorMetricaPoiNOC.getNOC()) +
                "|CLASSE POI RFC: " + Double.parseDouble(maiorValorMetricaPoiRFC.getRFC()) +
                "|CLASSE POI WMC: " + Double.parseDouble(maiorValorMetricaPoiWMC.getWMC());
    }

    public static String valorMenorPoiMetricasCK() {
        Model menorValorMetricaPoiCBO = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getCBO())))
                .orElse(null);

        Model menorValorMetricaPoiDIT = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model menorValorMetricaPoiLCOM = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model menorValorMetricaPoiNOC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaPoiRFC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaPoiWMC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MENOR VALORES CK - POI \n" +
                "|CLASSE POI CBO: " + Double.parseDouble(menorValorMetricaPoiCBO.getCBO()) +
                "|CLASSE POI DIT: " + Double.parseDouble(menorValorMetricaPoiDIT.getDIT()) +
                "|CLASSE POI LCOM: " + Double.parseDouble(menorValorMetricaPoiLCOM.getLCOM()) +
                "|CLASSE POI NOC: " + Double.parseDouble(menorValorMetricaPoiNOC.getNOC()) +
                "|CLASSE POI RFC: " + Double.parseDouble(menorValorMetricaPoiRFC.getRFC()) +
                "|CLASSE POI WMC: " + Double.parseDouble(menorValorMetricaPoiWMC.getWMC());
    }

    public static String valorMaiorLuceneMetricasCK() {
        Model maiorValorMetricaLuceneCBO = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getCBO())))
                .orElse(null);

        Model maiorValorMetricaLuceneDIT = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model maiorValorMetricaLuceneLCOM = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model maiorValorMetricaLuceneNOC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaLuceneRFC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaLuceneWMC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MAIOR VALORES CK - LUCENE \n" +
                "|CLASSE LUCENE CBO: " + Double.parseDouble(maiorValorMetricaLuceneCBO.getCBO()) +
                "|CLASSE LUCENE DIT: " + Double.parseDouble(maiorValorMetricaLuceneDIT.getDIT()) +
                "|CLASSE LUCENE LCOM: " + Double.parseDouble(maiorValorMetricaLuceneLCOM.getLCOM()) +
                "|CLASSE LUCENE NOC: " + Double.parseDouble(maiorValorMetricaLuceneNOC.getNOC()) +
                "|CLASSE LUCENE RFC: " + Double.parseDouble(maiorValorMetricaLuceneRFC.getRFC()) +
                "|CLASSE LUCENE WMC: " + Double.parseDouble(maiorValorMetricaLuceneWMC.getWMC());
    }

    public static String valorMenorLuceneMetricasCK() {
        Model menorValorMetricaLuceneCBO = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getCBO())))
                .orElse(null);

        Model menorValorMetricaLuceneDIT = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model menorValorMetricaLuceneLCOM = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model menorValorMetricaLuceneNOC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaLuceneRFC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaLuceneWMC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MENOR VALORES CK - LUCENE \n" +
                "|CLASSE LUCENE CBO: " + Double.parseDouble(menorValorMetricaLuceneCBO.getCBO()) +
                "|CLASSE LUCENE DIT: " + Double.parseDouble(menorValorMetricaLuceneDIT.getDIT()) +
                "|CLASSE LUCENE LCOM: " + Double.parseDouble(menorValorMetricaLuceneLCOM.getLCOM()) +
                "|CLASSE LUCENE NOC: " + Double.parseDouble(menorValorMetricaLuceneNOC.getNOC()) +
                "|CLASSE LUCENE RFC: " + Double.parseDouble(menorValorMetricaLuceneRFC.getRFC()) +
                "|CLASSE LUCENE WMC: " + Double.parseDouble(menorValorMetricaLuceneWMC.getWMC());
    }

    public static String valorMaiorJfreeChartMetricasCK() {
        Model maiorValorMetricaJfreechartCBO = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getCBO())))
                .orElse(null);

        Model maiorValorMetricaJfreechartDIT = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model maiorValorMetricaJfreechartLCOM = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model maiorValorMetricaJfreechartNOC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaJfreechartRFC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaJfreechartWMC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MAIOR VALORES CK - JFREECHART \n" +
                "|CLASSE JFREECHART CBO: " + Double.parseDouble(maiorValorMetricaJfreechartCBO.getCBO()) +
                "|CLASSE JFREECHART DIT: " + Double.parseDouble(maiorValorMetricaJfreechartDIT.getDIT()) +
                "|CLASSE JFREECHART LCOM: " + Double.parseDouble(maiorValorMetricaJfreechartLCOM.getLCOM()) +
                "|CLASSE JFREECHART NOC: " + Double.parseDouble(maiorValorMetricaJfreechartNOC.getNOC()) +
                "|CLASSE JFREECHART RFC: " + Double.parseDouble(maiorValorMetricaJfreechartRFC.getRFC()) +
                "|CLASSE JFREECHART WMC: " + Double.parseDouble(maiorValorMetricaJfreechartWMC.getWMC());
    }

    public static String valorMenorJfreechartMetricasCK() {
        Model menorValorMetricaJfreechartCBO = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getCBO())))
                .orElse(null);

        Model menorValorMetricaJfreechartDIT = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model menorValorMetricaJfreechartLCOM = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model menorValorMetricaJfreechartNOC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaJfreechartRFC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaJfreechartWMC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MENOR VALORES CK - JFREECHART \n" +
                "|CLASSE JFREECHART CBO: " + Double.parseDouble(menorValorMetricaJfreechartCBO.getCBO()) +
                "|CLASSE JFREECHART DIT: " + Double.parseDouble(menorValorMetricaJfreechartDIT.getDIT()) +
                "|CLASSE JFREECHART LCOM: " + Double.parseDouble(menorValorMetricaJfreechartLCOM.getLCOM()) +
                "|CLASSE JFREECHART NOC: " + Double.parseDouble(menorValorMetricaJfreechartNOC.getNOC()) +
                "|CLASSE JFREECHART RFC: " + Double.parseDouble(menorValorMetricaJfreechartRFC.getRFC()) +
                "|CLASSE JFREECHART WMC: " + Double.parseDouble(menorValorMetricaJfreechartWMC.getWMC());
    }

    public static String valorMaiorJackRabbitMetricasCK() {
        Model maiorValorMetricaJackrabbitCBO = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getCBO())))
                .orElse(null);

        Model maiorValorMetricaJackrabbitDIT = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model maiorValorMetricaJackrabbitLCOM = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model maiorValorMetricaJackrabbitNOC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaJackrabbitRFC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaJackrabbitWMC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MAIOR VALORES CK - JACKRABBIT \n" +
                "|CLASSE JACKRABBIT CBO: " + Double.parseDouble(maiorValorMetricaJackrabbitCBO.getCBO()) +
                "|CLASSE JACKRABBIT DIT: " + Double.parseDouble(maiorValorMetricaJackrabbitDIT.getDIT()) +
                "|CLASSE JACKRABBIT LCOM: " + Double.parseDouble(maiorValorMetricaJackrabbitLCOM.getLCOM()) +
                "|CLASSE JACKRABBIT NOC: " + Double.parseDouble(maiorValorMetricaJackrabbitNOC.getNOC()) +
                "|CLASSE JACKRABBIT RFC: " + Double.parseDouble(maiorValorMetricaJackrabbitRFC.getRFC()) +
                "|CLASSE JACKRABBIT WMC: " + Double.parseDouble(maiorValorMetricaJackrabbitWMC.getWMC());
    }

    public static String valorMenorJackrabbitMetricasCK() {
        Model menorValorMetricaJackrabbitCBO = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getCBO())))
                .orElse(null);

        Model menorValorMetricaJackrabbitDIT = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model menorValorMetricaJackrabbitLCOM = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model menorValorMetricaJackrabbitNOC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaJackrabbitRFC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaJackrabbitWMC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MENOR VALORES CK -JACKRABBIT \n" +
                "|CLASSE JACKRABBIT CBO: " + Double.parseDouble(menorValorMetricaJackrabbitCBO.getCBO()) +
                "|CLASSE JACKRABBIT DIT: " + Double.parseDouble(menorValorMetricaJackrabbitDIT.getDIT()) +
                "|CLASSE JACKRABBIT LCOM: " + Double.parseDouble(menorValorMetricaJackrabbitLCOM.getLCOM()) +
                "|CLASSE JACKRABBIT NOC: " + Double.parseDouble(menorValorMetricaJackrabbitNOC.getNOC()) +
                "|CLASSE JACKRABBIT RFC: " + Double.parseDouble(menorValorMetricaJackrabbitRFC.getRFC()) +
                "|CLASSE JACKRABBIT WMC: " + Double.parseDouble(menorValorMetricaJackrabbitWMC.getWMC());
    }

    public static String valorMaiorHadoopMetricasCK() {
        Model maiorValorMetricaHadoopCBO = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getCBO())))
                .orElse(null);

        Model maiorValorMetricaHadoopDIT = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model maiorValorMetricaHadoopLCOM = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model maiorValorMetricaHadoopNOC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaHadoopRFC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaHadoopWMC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MAIOR VALORES CK - HADOOP \n" +
                "|CLASSE HADOOP CBO: " + Double.parseDouble(maiorValorMetricaHadoopCBO.getCBO()) +
                "|CLASSE HADOOP DIT: " + Double.parseDouble(maiorValorMetricaHadoopDIT.getDIT()) +
                "|CLASSE HADOOP LCOM: " + Double.parseDouble(maiorValorMetricaHadoopLCOM.getLCOM()) +
                "|CLASSE HADOOP NOC: " + Double.parseDouble(maiorValorMetricaHadoopNOC.getNOC()) +
                "|CLASSE HADOOP RFC: " + Double.parseDouble(maiorValorMetricaHadoopRFC.getRFC()) +
                "|CLASSE HADOOP WMC: " + Double.parseDouble(maiorValorMetricaHadoopWMC.getWMC());
    }

    public static String valorMenorHadoopMetricasCK() {
        Model menorValorMetricaHadoopCBO = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getCBO())))
                .orElse(null);

        Model menorValorMetricaHadoopDIT = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model menorValorMetricaHadoopLCOM = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model menorValorMetricaHadoopNOC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaHadoopRFC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaHadoopWMC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MENOR VALORES CK - HADOOP \n" +
                "|CLASSE HADOOP CBO: " + Double.parseDouble(menorValorMetricaHadoopCBO.getCBO()) +
                "|CLASSE HADOOP DIT: " + Double.parseDouble(menorValorMetricaHadoopDIT.getDIT()) +
                "|CLASSE HADOOP LCOM: " + Double.parseDouble(menorValorMetricaHadoopLCOM.getLCOM()) +
                "|CLASSE HADOOP NOC: " + Double.parseDouble(menorValorMetricaHadoopNOC.getNOC()) +
                "|CLASSE HADOOP RFC: " + Double.parseDouble(menorValorMetricaHadoopRFC.getRFC()) +
                "|CLASSE HADOOP WMC: " + Double.parseDouble(menorValorMetricaHadoopWMC.getWMC());
    }

    public static String valorMaiorMathMetricasCK() {
        Model maiorValorMetricaMathCBO = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getCBO())))
                .orElse(null);

        Model maiorValorMetricaMathDIT = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model maiorValorMetricaMathLCOM = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model maiorValorMetricaMathNOC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaMathRFC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaMathWMC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MAIOR VALORES CK - MATH \n" +
                "|CLASSE MATH CBO: " + Double.parseDouble(maiorValorMetricaMathCBO.getCBO()) +
                "|CLASSE MATH DIT: " + Double.parseDouble(maiorValorMetricaMathDIT.getDIT()) +
                "|CLASSE MATH LCOM: " + Double.parseDouble(maiorValorMetricaMathLCOM.getLCOM()) +
                "|CLASSE MATH NOC: " + Double.parseDouble(maiorValorMetricaMathNOC.getNOC()) +
                "|CLASSE MATH RFC: " + Double.parseDouble(maiorValorMetricaMathRFC.getRFC()) +
                "|CLASSE MATH WMC: " + Double.parseDouble(maiorValorMetricaMathWMC.getWMC());
    }

    public static String valorMenorMathMetricasCK() {
        Model menorValorMetricaMathCBO = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getCBO())))
                .orElse(null);

        Model menorValorMetricaMathDIT = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model menorValorMetricaMathLCOM = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model menorValorMetricaMathNOC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaMathRFC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaMathWMC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MENOR VALORES CK - MATH \n" +
                "|CLASSE MATH CBO: " + Double.parseDouble(menorValorMetricaMathCBO.getCBO()) +
                "|CLASSE MATH DIT: " + Double.parseDouble(menorValorMetricaMathDIT.getDIT()) +
                "|CLASSE MATH LCOM: " + Double.parseDouble(menorValorMetricaMathLCOM.getLCOM()) +
                "|CLASSE MATH NOC: " + Double.parseDouble(menorValorMetricaMathNOC.getNOC()) +
                "|CLASSE MATH RFC: " + Double.parseDouble(menorValorMetricaMathRFC.getRFC()) +
                "|CLASSE MATH WMC: " + Double.parseDouble(menorValorMetricaMathWMC.getWMC());
    }

    public static String valorMaiorJodaMetricasCK() {
        Model maiorValorMetricaJodaCBO = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getCBO())))
                .orElse(null);

        Model maiorValorMetricaJodaDIT = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model maiorValorMetricaJodaLCOM = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model maiorValorMetricaJodaNOC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaJodaRFC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaJodaWMC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MAIOR VALORES CK - JODA \n" +
                "|CLASSE JODA CBO: " + Double.parseDouble(maiorValorMetricaJodaCBO.getCBO()) +
                "|CLASSE JODA DIT: " + Double.parseDouble(maiorValorMetricaJodaDIT.getDIT()) +
                "|CLASSE JODA LCOM: " + Double.parseDouble(maiorValorMetricaJodaLCOM.getLCOM()) +
                "|CLASSE JODA NOC: " + Double.parseDouble(maiorValorMetricaJodaNOC.getNOC()) +
                "|CLASSE JODA RFC: " + Double.parseDouble(maiorValorMetricaJodaRFC.getRFC()) +
                "|CLASSE JODA WMC: " + Double.parseDouble(maiorValorMetricaJodaWMC.getWMC());
    }

    public static String valorMenorJodaMetricasCK() {
        Model menorValorMetricaJodaCBO = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getCBO())))
                .orElse(null);

        Model menorValorMetricaJodaDIT = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model menorValorMetricaJodaLCOM = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model menorValorMetricaJodaNOC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaJodaRFC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaJodaWMC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MENOR VALORES CK - JODA \n" +
                "|CLASSE JODA CBO: " + Double.parseDouble(menorValorMetricaJodaCBO.getCBO()) +
                "|CLASSE JODA DIT: " + Double.parseDouble(menorValorMetricaJodaDIT.getDIT()) +
                "|CLASSE JODA LCOM: " + Double.parseDouble(menorValorMetricaJodaLCOM.getLCOM()) +
                "|CLASSE JODA NOC: " + Double.parseDouble(menorValorMetricaJodaNOC.getNOC()) +
                "|CLASSE JODA RFC: " + Double.parseDouble(menorValorMetricaJodaRFC.getRFC()) +
                "|CLASSE JODA WMC: " + Double.parseDouble(menorValorMetricaJodaWMC.getWMC());
    }

    public static String valorMaiorAchillesMetricasCK() {
        Model maiorValorMetricaAChillesCBO = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getCBO())))
                .orElse(null);

        Model maiorValorMetricaAChillesDIT = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model maiorValorMetricaAChillesLCOM = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model maiorValorMetricaAChillesNOC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaAChillesRFC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaAChillesWMC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MAIOR VALORES CK - ACHILLES \n" +
                "|CLASSE ACHILLES CBO: " + Double.parseDouble(maiorValorMetricaAChillesCBO.getCBO()) +
                "|CLASSE ACHILLES DIT: " + Double.parseDouble(maiorValorMetricaAChillesDIT.getDIT()) +
                "|CLASSE ACHILLES LCOM: " + Double.parseDouble(maiorValorMetricaAChillesLCOM.getLCOM()) +
                "|CLASSE ACHILLES NOC: " + Double.parseDouble(maiorValorMetricaAChillesNOC.getNOC()) +
                "|CLASSE ACHILLES RFC: " + Double.parseDouble(maiorValorMetricaAChillesRFC.getRFC()) +
                "|CLASSE ACHILLES WMC: " + Double.parseDouble(maiorValorMetricaAChillesWMC.getWMC());
    }

    public static String valorMenorAchillesMetricasCK() {
        Model menorValorMetricaAChillesCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .min(Comparator.comparingDouble(model -> Double.parseDouble(model.getCBO())))
                .orElse(null);

        Model menorValorMetricaAChillesDIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model menorValorMetricaAChillesLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model menorValorMetricaAChillesNOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaAChillesRFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaAChillesWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MENOR VALORES CK - ACHILLES \n" +
                "|CLASSE ACHILLES CBO: " + Double.parseDouble(menorValorMetricaAChillesCBO.getCBO()) +
                "|CLASSE ACHILLES DIT: " + Double.parseDouble(menorValorMetricaAChillesDIT.getDIT()) +
                "|CLASSE ACHILLES LCOM: " + Double.parseDouble(menorValorMetricaAChillesLCOM.getLCOM()) +
                "|CLASSE ACHILLES NOC: " + Double.parseDouble(menorValorMetricaAChillesNOC.getNOC()) +
                "|CLASSE ACHILLES RFC: " + Double.parseDouble(menorValorMetricaAChillesRFC.getRFC()) +
                "|CLASSE ACHILLES WMC: " + Double.parseDouble(menorValorMetricaAChillesWMC.getWMC());
    }

    public static String valorMaiorAssertjMetricasCK() {
        Model maiorValorMetricaAssertjCBO = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getCBO())))
                .orElse(null);

        Model maiorValorMetricaAssertjDIT = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model maiorValorMetricaAssertjLCOM = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model maiorValorMetricaAssertjNOC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaAssertjRFC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaAssertjWMC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MAIOR VALORES CK - ASSERTJ \n" +
                "|CLASSE ASSERTJ CBO: " + Double.parseDouble(maiorValorMetricaAssertjCBO.getCBO()) +
                "|CLASSE ASSERTJ DIT: " + Double.parseDouble(maiorValorMetricaAssertjDIT.getDIT()) +
                "|CLASSE ASSERTJ LCOM: " + Double.parseDouble(maiorValorMetricaAssertjLCOM.getLCOM()) +
                "|CLASSE ASSERTJ NOC: " + Double.parseDouble(maiorValorMetricaAssertjNOC.getNOC()) +
                "|CLASSE ASSERTJ RFC: " + Double.parseDouble(maiorValorMetricaAssertjRFC.getRFC()) +
                "|CLASSE ASSERTJ WMC: " + Double.parseDouble(maiorValorMetricaAssertjWMC.getWMC());
    }

    public static String valorMenorAssertjMetricasCK() {
        Model menorValorMetricaAssertjCBO = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getCBO())))
                .orElse(null);

        Model menorValorMetricaAssertjDIT = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model menorValorMetricaAssertjLCOM = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model menorValorMetricaAssertjNOC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaAssertjRFC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaAssertjWMC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MENOR VALORES CK - ASSERTJ \n" +
                "|CLASSE ASSERTJ CBO: " + Double.parseDouble(menorValorMetricaAssertjCBO.getCBO()) +
                "|CLASSE ASSERTJ DIT: " + Double.parseDouble(menorValorMetricaAssertjDIT.getDIT()) +
                "|CLASSE ASSERTJ LCOM: " + Double.parseDouble(menorValorMetricaAssertjLCOM.getLCOM()) +
                "|CLASSE ASSERTJ NOC: " + Double.parseDouble(menorValorMetricaAssertjNOC.getNOC()) +
                "|CLASSE ASSERTJ RFC: " + Double.parseDouble(menorValorMetricaAssertjRFC.getRFC()) +
                "|CLASSE ASSERTJ WMC: " + Double.parseDouble(menorValorMetricaAssertjWMC.getWMC());
    }


    public static String valorMaiorCommonsIOMetricasCK() {
        Model maiorValorMetricaCommonsIOCBO = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getCBO())))
                .orElse(null);

        Model maiorValorMetricaCommonsIODIT = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model maiorValorMetricaCommonsIOLCOM = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model maiorValorMetricaCommonsIONOC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaCommonsIORFC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model maiorValorMetricaCommonsIOWMC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .max(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MAIOR VALORES CK - COMMONS.IO \n" +
                "|CLASSE COMMONS.IO CBO: " + Double.parseDouble(maiorValorMetricaCommonsIOCBO.getCBO()) +
                "|CLASSE COMMONS.IO DIT: " + Double.parseDouble(maiorValorMetricaCommonsIODIT.getDIT()) +
                "|CLASSE COMMONS.IO LCOM: " + Double.parseDouble(maiorValorMetricaCommonsIOLCOM.getLCOM()) +
                "|CLASSE COMMONS.IO NOC: " + Double.parseDouble(maiorValorMetricaCommonsIONOC.getNOC()) +
                "|CLASSE COMMONS.IO RFC: " + Double.parseDouble(maiorValorMetricaCommonsIORFC.getRFC()) +
                "|CLASSE COMMONS.IO WMC: " + Double.parseDouble(maiorValorMetricaCommonsIOWMC.getWMC());
    }

    public static String valorMenorCommonsIOMetricasCK() {

        Model menorValorMetricaCommonsIOCBO = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getCBO())))
                .orElse(null);

        Model menorValorMetricaCommonsIODIT = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getDIT())))
                .orElse(null);

        Model menorValorMetricaCommonsIOLCOM = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getLCOM())))
                .orElse(null);

        Model menorValorMetricaCommonsIONOC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaCommonsIORFC = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getNOC())))
                .orElse(null);

        Model menorValorMetricaCommonsIOWMC = getModelList().stream()
                .filter(model -> model.getCLASSE().contains("3"))
                .min(Comparator.comparingDouble(modelo -> Double.parseDouble(modelo.getWMC())))
                .orElse(null);

        return "MENOR VALORES CK - COMMONS.IO \n" +
                "|CLASSE COMMONS.IO CBO: " + Double.parseDouble(menorValorMetricaCommonsIOCBO.getCBO()) +
                "|CLASSE COMMONS.IO DIT: " + Double.parseDouble(menorValorMetricaCommonsIODIT.getDIT()) +
                "|CLASSE COMMONS.IO LCOM: " + Double.parseDouble(menorValorMetricaCommonsIOLCOM.getLCOM()) +
                "|CLASSE COMMONS.IO NOC: " + Double.parseDouble(menorValorMetricaCommonsIONOC.getNOC()) +
                "|CLASSE COMMONS.IO RFC: " + Double.parseDouble(menorValorMetricaCommonsIORFC.getRFC()) +
                "|CLASSE COMMONS.IO WMC: " + Double.parseDouble(menorValorMetricaCommonsIOWMC.getWMC());

    }

    public static String valorMediaAchillesMetricasCK(){
        double mediaValorMetricaAChillesCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getCBO())));

        double mediaValorMetricaAChillesDIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getDIT())));

        double mediaValorMetricaAChillesLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getLCOM())));

        double mediaValorMetricaAChillesNOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getNOC())));

        double mediaValorMetricaAChillesRFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getRFC())));

        double mediaValorMetricaAChillesWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getWMC())));

        return "MEDIA VALORES CK - ACHILLES \n" +
                "|CLASSE ACHILLES CBO: " + tipoDecimal(mediaValorMetricaAChillesCBO)+
                "|CLASSE ACHILLES DIT: " + tipoDecimal(mediaValorMetricaAChillesDIT) +
                "|CLASSE ACHILLES LCOM: " + tipoDecimal(mediaValorMetricaAChillesLCOM) +
                "|CLASSE ACHILLES NOC: " + tipoDecimal(mediaValorMetricaAChillesNOC) +
                "|CLASSE ACHILLES RFC: " + tipoDecimal(mediaValorMetricaAChillesRFC) +
                "|CLASSE ACHILLES WMC: " + tipoDecimal(mediaValorMetricaAChillesWMC);

    }

    public static String valorMediaAssertJMetricasCK(){
        double mediaValorMetricaAssertJCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getCBO())));

        double mediaValorMetricaAssertJDIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getDIT())));

        double mediaValorMetricaAssertJLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getLCOM())));

        double mediaValorMetricaAssertJNOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getNOC())));

        double mediaValorMetricaAssertJRFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getRFC())));

        double mediaValorMetricaAssertJWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("2"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getWMC())));

        return "MEDIA VALORES CK - ASSERTJ \n" +
                "|CLASSE ASSERTJ CBO: " + tipoDecimal(mediaValorMetricaAssertJCBO)+
                "|CLASSE ASSERTJ DIT: " + tipoDecimal(mediaValorMetricaAssertJDIT) +
                "|CLASSE ASSERTJ LCOM: " + tipoDecimal(mediaValorMetricaAssertJLCOM) +
                "|CLASSE ASSERTJ NOC: " + tipoDecimal(mediaValorMetricaAssertJNOC) +
                "|CLASSE ASSERTJ RFC: " + tipoDecimal(mediaValorMetricaAssertJRFC) +
                "|CLASSE ASSERTJ WMC: " + tipoDecimal(mediaValorMetricaAssertJWMC);

    }


    public static String valorMediaCommonsIOMetricasCK(){
        double mediaValorCommonsIOCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getCBO())));

        double mediaValorCommonsIODIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getDIT())));

        double mediaValorCommonsIOLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getLCOM())));

        double mediaValorCommonsIONOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getNOC())));

        double mediaValorCommonsIORFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getRFC())));

        double mediaValorCommonsIOWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("3"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getWMC())));

        return "MEDIA VALORES CK - COMMONS.IO \n" +
                "|CLASSE COMMONS.IO CBO: " + tipoDecimal(mediaValorCommonsIOCBO)+
                "|CLASSE COMMONS.IO DIT: " + tipoDecimal(mediaValorCommonsIODIT) +
                "|CLASSE COMMONS.IO LCOM: " + tipoDecimal(mediaValorCommonsIOLCOM) +
                "|CLASSE COMMONS.IO NOC: " + tipoDecimal(mediaValorCommonsIONOC) +
                "|CLASSE COMMONS.IO RFC: " + tipoDecimal(mediaValorCommonsIORFC) +
                "|CLASSE COMMONS.IO WMC: " + tipoDecimal(mediaValorCommonsIOWMC);

    }

    public static String valorMediaJodaMetricasCK(){
        double mediaValorJodaCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getCBO())));

        double mediaValorJodaDIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getDIT())));

        double mediaValorJodaLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getLCOM())));

        double mediaValorJodaNOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getNOC())));

        double mediaValorJodaRFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getRFC())));

        double mediaValorJodaWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("4"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getWMC())));

        return "MEDIA VALORES CK - JODA \n" +
                "|CLASSE JODA CBO: " + tipoDecimal(mediaValorJodaCBO)+
                "|CLASSE JODA DIT: " + tipoDecimal(mediaValorJodaDIT) +
                "|CLASSE JODA LCOM: " + tipoDecimal(mediaValorJodaLCOM) +
                "|CLASSE JODA NOC: " + tipoDecimal(mediaValorJodaNOC) +
                "|CLASSE JODA RFC: " + tipoDecimal(mediaValorJodaRFC) +
                "|CLASSE JODA WMC: " + tipoDecimal(mediaValorJodaWMC);

    }

    public static String valorMediaMathMetricasCK(){
        double mediaValorMathCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getCBO())));

        double mediaValorMathDIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getDIT())));

        double mediaValorMathLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getLCOM())));

        double mediaValorMathNOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getNOC())));

        double mediaValorMathRFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getRFC())));

        double mediaValorMathWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("5"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getWMC())));

        return "MEDIA VALORES CK - MATH \n" +
                "|CLASSE MATH CBO: " + tipoDecimal(mediaValorMathCBO)+
                "|CLASSE MATH DIT: " + tipoDecimal(mediaValorMathDIT) +
                "|CLASSE MATH LCOM: " + tipoDecimal(mediaValorMathLCOM) +
                "|CLASSE MATH NOC: " + tipoDecimal(mediaValorMathNOC) +
                "|CLASSE MATH RFC: " + tipoDecimal(mediaValorMathRFC) +
                "|CLASSE MATH WMC: " + tipoDecimal(mediaValorMathWMC);

    }
    public static String valorMediaHadoopMathMetricasCK(){
        double mediaValorHadoopCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getCBO())));

        double mediaValorHadoopDIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getDIT())));

        double mediaValorHadoopLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getLCOM())));

        double mediaValorHadoopNOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getNOC())));

        double mediaValorHadoopRFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getRFC())));

        double mediaValorHadoopWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("6"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getWMC())));

        return "MEDIA VALORES CK - HADOOP \n" +
                "|CLASSE HADOOP CBO: " + tipoDecimal(mediaValorHadoopCBO)+
                "|CLASSE HADOOP DIT: " + tipoDecimal(mediaValorHadoopDIT) +
                "|CLASSE HADOOP LCOM: " + tipoDecimal(mediaValorHadoopLCOM) +
                "|CLASSE HADOOP NOC: " + tipoDecimal(mediaValorHadoopNOC) +
                "|CLASSE HADOOP RFC: " + tipoDecimal(mediaValorHadoopRFC) +
                "|CLASSE HADOOP WMC: " + tipoDecimal(mediaValorHadoopWMC);
    }

    public static String valorMediaJackRabbitMetricasCK(){
        double mediaValorJackRabbitCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getCBO())));

        double mediaValorJackRabbitDIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getDIT())));

        double mediaValorJackRabbitLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getLCOM())));

        double mediaValorJackRabbitNOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getNOC())));

        double mediaValorJackRabbitRFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getRFC())));

        double mediaValorJackRabbitWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("7"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getWMC())));

        return "MEDIA VALORES CK - JACKRABBIT \n" +
                "|CLASSE JACKRABBIT CBO: " + tipoDecimal(mediaValorJackRabbitCBO)+
                "|CLASSE JACKRABBIT DIT: " + tipoDecimal(mediaValorJackRabbitDIT) +
                "|CLASSE JACKRABBIT LCOM: " + tipoDecimal(mediaValorJackRabbitLCOM) +
                "|CLASSE JACKRABBIT NOC: " + tipoDecimal(mediaValorJackRabbitNOC) +
                "|CLASSE JACKRABBIT RFC: " + tipoDecimal(mediaValorJackRabbitRFC) +
                "|CLASSE JACKRABBIT WMC: " + tipoDecimal(mediaValorJackRabbitWMC);
    }

    public static String valorMediaJfreeChartMetricasCK(){
        double mediaValorJfreeChartCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getCBO())));

        double mediaValorJfreeChartDIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getDIT())));

        double mediaValorJfreeChartLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getLCOM())));

        double mediaValorJfreeChartNOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getNOC())));

        double mediaValorJfreeChartRFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getRFC())));

        double mediaValorJfreeChartWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("8"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getWMC())));

        return "MEDIA VALORES CK - JFREECHART \n" +
                "|CLASSE JFREECHART CBO: " + tipoDecimal(mediaValorJfreeChartCBO)+
                "|CLASSE JFREECHART DIT: " + tipoDecimal(mediaValorJfreeChartDIT) +
                "|CLASSE JFREECHART LCOM: " + tipoDecimal(mediaValorJfreeChartLCOM) +
                "|CLASSE JFREECHART NOC: " + tipoDecimal(mediaValorJfreeChartNOC) +
                "|CLASSE JFREECHART RFC: " + tipoDecimal(mediaValorJfreeChartRFC) +
                "|CLASSE JFREECHART WMC: " + tipoDecimal(mediaValorJfreeChartWMC);
    }
    public static String valorMediaLuceneMetricasCK(){
        double mediaValorLuceneCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getCBO())));

        double mediaValorLuceneDIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getDIT())));

        double mediaValorLuceneLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getLCOM())));

        double mediaValorLuceneNOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getNOC())));

        double mediaValorLuceneRFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getRFC())));

        double mediaValorLuceneWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("9"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getWMC())));

        return "MEDIA VALORES CK - LUCENE \n" +
                "|CLASSE LUCENE CBO: " + tipoDecimal(mediaValorLuceneCBO)+
                "|CLASSE LUCENE DIT: " + tipoDecimal(mediaValorLuceneDIT) +
                "|CLASSE LUCENE LCOM: " + tipoDecimal(mediaValorLuceneLCOM) +
                "|CLASSE LUCENE NOC: " + tipoDecimal(mediaValorLuceneNOC) +
                "|CLASSE LUCENE RFC: " + tipoDecimal(mediaValorLuceneRFC) +
                "|CLASSE LUCENE WMC: " + tipoDecimal(mediaValorLuceneWMC);
    }

    public static String  valorMediaPoiMetricasCK(){
        double mediaValorPoiCBO = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getCBO())));

        double mediaValorPoiDIT = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getDIT())));

        double mediaValorPoiLCOM = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getLCOM())));

        double mediaValorPoiNOC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getNOC())));

        double mediaValorPoiRFC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getRFC())));

        double mediaValorPoiWMC = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals("10"))
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getWMC())));

        return "MEDIA VALORES CK - POI \n" +
                "|CLASSE POI CBO: " + tipoDecimal(mediaValorPoiCBO)+
                "|CLASSE POI DIT: " + tipoDecimal(mediaValorPoiDIT) +
                "|CLASSE POI LCOM: " + tipoDecimal(mediaValorPoiLCOM) +
                "|CLASSE POI NOC: " + tipoDecimal(mediaValorPoiNOC) +
                "|CLASSE POI RFC: " + tipoDecimal(mediaValorPoiRFC) +
                "|CLASSE POI WMC: " + tipoDecimal(mediaValorPoiWMC);
    }

    public static String desvioPadraoCBO(String valorExistente){
        double media = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals((valorExistente)))
                //.mapToDouble(model -> Double.parseDouble(model.getCBO()));
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getCBO())));


        // Calcula a soma dos quadrados das diferenças em relação à média
        double somaQuadradosDiferencas = getModelList().stream()
                .filter(model -> model.getCLASSE().equals(valorExistente))
                .mapToDouble(model -> {
                    double dit = Double.parseDouble(model.getCBO());
                    return Math.pow(dit - media, 2);
                })
                .sum();

        // Calcula a variancia
        double variancia = somaQuadradosDiferencas / (getModelList().size() - 1);

        // Calcula o desvio padrao como a raiz quadrada da variancia
        double desvioPadrao = Math.sqrt(variancia);

        return tipoDecimal(desvioPadrao);

    }
    public static String desvioPadraoDIT(String valorExistente){
        double media = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals(valorExistente))
                //.mapToDouble(model -> Double.parseDouble(model.getCBO()));
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getDIT())));

        // Calcula a soma dos quadrados das diferenças em relação à média
        double somaQuadradosDiferencas = getModelList().stream()
                .filter(model -> model.getCLASSE().equals(valorExistente))
                .mapToDouble(model -> {
                    double dit = Double.parseDouble(model.getDIT());
                    return Math.pow(dit - media, 2);
                })
                .sum();

        // Calcula a variancia
        double variancia = somaQuadradosDiferencas / (getModelList().size() - 1);

        // Calcula o desvio padrao como a raiz quadrada da variancia
        double desvioPadrao = Math.sqrt(variancia);

        return tipoDecimal(desvioPadrao);

    }

    public static String desvioPadraoLCOM(String valorExistente){
        double media = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals((valorExistente)))
                //.mapToDouble(model -> Double.parseDouble(model.getCBO()));
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getLCOM())));

        // Calcula a soma dos quadrados das diferenças em relação à média
        double somaQuadradosDiferencas = getModelList().stream()
                .filter(model -> model.getCLASSE().equals(valorExistente))
                .mapToDouble(model -> {
                    double dit = Double.parseDouble(model.getLCOM());
                    return Math.pow(dit - media, 2);
                })
                .sum();

        // Calcula a variancia
        double variancia = somaQuadradosDiferencas / (getModelList().size() - 1);

        // Calcula o desvio padrao como a raiz quadrada da variancia
        double desvioPadrao = Math.sqrt(variancia);

        return tipoDecimal(desvioPadrao);

    }
    public static String desvioPadraoNOC(String valorExistente){
        double media = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals((valorExistente)))
                //.mapToDouble(model -> Double.parseDouble(model.getCBO()));
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getNOC())));

        // Calcula a soma dos quadrados das diferenças em relação à média
        double somaQuadradosDiferencas = getModelList().stream()
                .filter(model -> model.getCLASSE().equals(valorExistente))
                .mapToDouble(model -> {
                    double dit = Double.parseDouble(model.getNOC());
                    return Math.pow(dit - media, 2);
                })
                .sum();

        // Calcula a variancia
        double variancia = somaQuadradosDiferencas / (getModelList().size() - 1);

        // Calcula o desvio padrao como a raiz quadrada da variancia
        double desvioPadrao = Math.sqrt(variancia);

        return tipoDecimal(desvioPadrao);
    }

    public static String desvioPadraoRFC(String valorExistente){
        double media = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals((valorExistente)))
                //.mapToDouble(model -> Double.parseDouble(model.getCBO()));
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getRFC())));

        // Calcula a soma dos quadrados das diferenças em relação à média
        double somaQuadradosDiferencas = getModelList().stream()
                .filter(model -> model.getCLASSE().equals(valorExistente))
                .mapToDouble(model -> {
                    double dit = Double.parseDouble(model.getRFC());
                    return Math.pow(dit - media, 2);
                })
                .sum();

        // Calcula a variancia
        double variancia = somaQuadradosDiferencas / (getModelList().size() - 1);

        // Calcula o desvio padrao como a raiz quadrada da variancia
        double desvioPadrao = Math.sqrt(variancia);

        return tipoDecimal(desvioPadrao);
    }

    public static String desvioPadraoWMC(String valorExistente){
        double media = getModelList()
                .stream()
                .filter(model -> model.getCLASSE().equals((valorExistente)))
                //.mapToDouble(model -> Double.parseDouble(model.getCBO()));
                .collect(Collectors.averagingDouble(model -> Double.parseDouble(model.getWMC())));

        // Calcula a soma dos quadrados das diferenças em relação à média
        double somaQuadradosDiferencas = getModelList().stream()
                .filter(model -> model.getCLASSE().equals(valorExistente))
                .mapToDouble(model -> {
                    double dit = Double.parseDouble(model.getWMC());
                    return Math.pow(dit - media, 2);
                })
                .sum();

        // Calcula a variancia
        double variancia = somaQuadradosDiferencas / (getModelList().size() - 1);

        // Calcula o desvio padrao como a raiz quadrada da variancia
        double desvioPadrao = Math.sqrt(variancia);

        return tipoDecimal(desvioPadrao);
    }


    /*public static String quantidadeClassePorTipodeTesteIdentificado() {
        long quantidadeAchilles = getModelList().stream()
                .filter(model -> model.getCLASSE().contains("achilles") && model.getUOTESTED().contains("1"))
                .count();

        long quantidadeAssertJ = getModelList().stream()
                .filter(model -> model.getCLASSE().contains("assertj") && model.getUOTESTED().contains("1"))
                .count();

        long quantidadeCommonsio = getModelList().stream()
                .filter(model -> model.getCLASSE().contains("commons.io") && model.getUOTESTED().contains("1"))
                .count();

        long quantidadeJodaTime = getModelList().stream()
                .filter(model -> model.getCLASSE().contains("joda.time") && model.getUOTESTED().contains("1"))
                .count();

        long quantidadeMath = getModelList().stream()
                .filter(model -> model.getCLASSE().contains("math") && model.getUOTESTED().contains("1"))
                .count();

        long quantidadeHadoop = getModelList().stream()
                .filter(model -> model.getCLASSE().contains("hadoop") && model.getUOTESTED().contains("1"))
                .count();

        long quantidadeJackRabbit = getModelList().stream()
                .filter(model -> model.getCLASSE().contains("jackrabbit") && model.getUOTESTED().contains("1"))
                .count();

        long quantidadeJfreeChart = getModelList().stream()
                .filter(model -> model.getCLASSE().contains("jfreechart") && model.getUOTESTED().contains("1"))
                .count();

        long quantidadeLucene = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("9") && model.getUOTESTED().contains("1"))
                .count();

        long quantidadePoi = getModelList().stream()
                .filter(model -> model.getCLASSE().contains("poi") && model.getUOTESTED().contains("1"))
                .count();

        return String.format("|ACHILLES: %d|ASSERTJ: %d|COMMONS IO: %d|JODA TIME: %d|MATH: %d|HADOOP: %d|JACKRABBIT: %d|JFREECHART: %d|LUCENE: %d|POI: %d|"
                , quantidadeAchilles
                , quantidadeAssertJ
                , quantidadeCommonsio
                , quantidadeJodaTime
                , quantidadeMath
                , quantidadeHadoop
                , quantidadeJackRabbit
                , quantidadeJfreeChart
                , quantidadeLucene
                , quantidadePoi);
    }*/


}