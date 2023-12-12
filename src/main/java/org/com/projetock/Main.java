package org.com.projetock;


import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.com.projetock.chidamberkemerer.metricas.Processamento.*;

public class Main {

    public static void resultEvalution()  {

        try {
            Instances[] instances = divisaoTreinoeTeste(dataInstancia(), 30);
            Instances treino = instances[0];
            Instances teste = instances[1];

            for (int i = 0; i < classificadoresWeka().length; i++) {

               Evaluation evaluation = classificao(classificadoresWeka()[i], treino, teste,10);

                System.out.println("*******************************************");
                System.out.println(classificadoresWeka()[i].getClass().getSimpleName());
                System.out.println("*******************************************\n");


                // Mapear resultados de volta para exemplos de dados reais
                Map<String, Set<Integer>> uotestedMapping   = createUOTESTEDMapping();
                Map<Integer, List<Double>> resultados       = evaluationResultados(evaluation);

                for (Map.Entry<Integer, List<Double>> entry : resultados.entrySet()) {
                    int classeIndex      = entry.getKey();
                    List<Double> valores = entry.getValue();

                    String classeValue          = mapIndexToCLASSE(classeIndex); // Mapear índice para valor de CLASSE
                    Set<Integer> uotestedValues = uotestedMapping.get(classeValue); // Obter valor correspondente de UOTESTED

                    for (Integer value : uotestedValues) {
                        System.out.println("Resultados para a classe " + classeValue + ":");
                        System.out.println("Verdadeiros Positivos (VP): " + valores.get(0));
                        System.out.println("Verdadeiros Negativos (VN): " + valores.get(1));
                        System.out.println("Falsos Positivos (FP): " + valores.get(2));
                        System.out.println("Falsos Negativos (FN): " + valores.get(3));
                        System.out.println("Área sob a Curva ROC: " + String.format("%.2f%%",valores.get(4)*100));
                        System.out.println("Acurácia: "+String.format("%.2f%%", acuracia(valores)*100));
                        System.out.println("Precisão: "+String.format("%.2f%%", precisao(valores)*100));
                        System.out.println("Recall: "+String.format("%.2f%%", recall(valores)*100));
                        System.out.println("fMedida: "+String.format("%.2f%%", fMedida(valores)*100));
                        System.out.println("MCC: "+String.format("%.2f%%", mcc(valores)*100));
                        System.out.println("UOTESTED correspondente: " +value);
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
    }
}