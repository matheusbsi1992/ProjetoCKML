package org.com.projetock;


import org.com.projetock.chidamberkemerer.util.Util;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static org.com.projetock.chidamberkemerer.contagem.ContagemMetricas.*;
import static org.com.projetock.chidamberkemerer.metricas.Processamento.*;
import static org.com.projetock.chidamberkemerer.util.Util.tipoDecimal;

public class Main {

    public static void resultEvalution() {

        try {
            Instances[] instances = divisaoTreinoeTeste(dataInstancia(), 30);
            Instances treino = instances[0];
            Instances teste = instances[1];

            for (int i = 0; i < classificadoresWeka().length; i++) {

                Evaluation evaluation = classificao(classificadoresWeka()[i], treino, teste, 10);

                System.out.println("*******************************************");
                System.out.println(classificadoresWeka()[i].getClass().getSimpleName());
                System.out.println("*******************************************\n");


                // Mapear resultados de volta para exemplos de dados reais
                Map<String, Set<Integer>> uotestedMapping = createUOTESTEDMapping();
                Map<Integer, List<Double>> resultados = evaluationResultados(evaluation);
                System.out.println("****************************");
                getValueMestrics(evaluation);
                System.out.println("****************************\n");

                for (Map.Entry<Integer, List<Double>> entry : resultados.entrySet()) {
                    int classeIndex = entry.getKey();
                    List<Double> valores = entry.getValue();

                    String classeValue = mapIndexToCLASSE(classeIndex); // Mapear índice para valor de CLASSE
                    Set<Integer> uotestedValues = uotestedMapping.get(classeValue); // Obter valor correspondente de UOTESTED

                    for (Integer value : uotestedValues) {
                        System.out.println("Resultados para a classe " + classeValue + ":");
                        System.out.println("Verdadeiros Positivos (VP): " + valores.get(0));
                        System.out.println("Verdadeiros Negativos (VN): " + valores.get(1));
                        System.out.println("Falsos Positivos (FP): " + valores.get(2));
                        System.out.println("Falsos Negativos (FN): " + valores.get(3));
                        System.out.println("Área sob a Curva ROC: " + String.format("%.2f%%", valores.get(4) * 100));
                        //System.out.println("Valor ErrorRate: " + String.format("%.2f%%",valores.get(5)*100));
                        System.out.println("Acurácia: " + String.format("%.2f%%", acuracia(valores) * 100));
//                        System.out.println("Precisão: "+String.format("%.2f%%", precisao(valores)*100));
//                        System.out.println("Recall: "+String.format("%.2f%%", recall(valores)*100));
//                        System.out.println("fMedida: "+String.format("%.2f%%", fMedida(valores)*100));
//                        System.out.println("MCC: "+String.format("%.2f%%", mcc(valores)*100));
//                        System.out.println("UOTESTED correspondente: " +value);
                        System.out.println("-----------------------------------------------------\n");
                    }
                }

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        resultEvalution();

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

        /*"CBO", "DIT", "LCOM", "NOC", "RFC", "WMC"*/

        System.out.println("--------------------------------------------------");
        System.out.println("DADOS RELACIONADOS A ESTRUTURA NÃO NORMALIZADA");
        System.out.println("--------------------------------------------------");
        System.out.println("QUANTIDADE DE CLASSES IDENTIFICADAS");
        System.out.println(quantidadeClasses());
        System.out.println("QUANTIDADE DE CLASSES IDENTIFICADAS");
        System.out.println("-------------------------MENOR-------------------------");
        System.out.println(valorMenorAchillesMetricasCK());
        System.out.println(valorMenorAssertjMetricasCK());
        System.out.println(valorMenorCommonsIOMetricasCK());
        System.out.println(valorMenorJodaMetricasCK());
        System.out.println(valorMenorMathMetricasCK());
        System.out.println(valorMenorHadoopMetricasCK());
        System.out.println(valorMenorJackrabbitMetricasCK());
        System.out.println(valorMenorJfreechartMetricasCK());
        System.out.println(valorMenorLuceneMetricasCK());
        System.out.println(valorMenorPoiMetricasCK());
        System.out.println("-------------------------MENOR-------------------------\n");
        System.out.println("-------------------------MEDIA-------------------------");
        System.out.println(valorMediaAchillesMetricasCK());
        System.out.println(valorMediaAssertJMetricasCK());
        System.out.println(valorMediaCommonsIOMetricasCK());
        System.out.println(valorMediaJodaMetricasCK());
        System.out.println(valorMediaMathMetricasCK());
        System.out.println(valorMediaHadoopMathMetricasCK());
        System.out.println(valorMediaJackRabbitMetricasCK());
        System.out.println(valorMediaJfreeChartMetricasCK());
        System.out.println(valorMediaLuceneMetricasCK());
        System.out.println(valorMediaPoiMetricasCK());
        System.out.println("-------------------------MEDIA-------------------------");

        // Filtra modelos com classe "1" e extrai valores de DIT como DoubleStream
        DoubleStream ditStream = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .mapToDouble(model -> Double.parseDouble(model.getCBO()));

        // Calcula a média
        double media = ditStream.average().orElse(0.0);

        // Calcula a soma dos quadrados das diferenças em relação à média
        double somaQuadradosDiferencas = getModelList().stream()
                .filter(model -> model.getCLASSE().equals("1"))
                .mapToDouble(model -> {
                    double dit = Double.parseDouble(model.getCBO());
                    return Math.pow(dit - media, 2);
                })
                .sum();

        // Calcula a variância
        double variancia = somaQuadradosDiferencas / (getModelList().size() - 1);

        // Calcula o desvio padrão como a raiz quadrada da variância
        double desvioPadrao = Math.sqrt(variancia);

        System.out.println("Desvio Padrão: " +  tipoDecimal(desvioPadrao));

    }


}