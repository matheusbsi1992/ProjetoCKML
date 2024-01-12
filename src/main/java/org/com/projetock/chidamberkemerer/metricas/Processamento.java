package org.com.projetock.chidamberkemerer.metricas;


import org.com.projetock.chidamberkemerer.normalizacao.PreProcessamento;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.lazy.IBk;
import weka.classifiers.rules.DecisionTable;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.Randomize;
import weka.filters.unsupervised.instance.RemovePercentage;

import java.util.*;


public class Processamento {

    private PreProcessamento preProcessamento;
    private static Evaluation evaluation;



    public Processamento() {
        preProcessamento = new PreProcessamento();
    }

    public static Instances dataInstancia() {

        PreProcessamento.abrirSessaoConjuntodeDados();

        Instances dataInstances = null;

        try {
            ConverterUtils.DataSource dataSource = new ConverterUtils.DataSource("projetocontido/preprocessamentoCK.arff");
            dataInstances = dataSource.getDataSet();
            dataInstances.setClassIndex(dataInstances.numAttributes() - 1);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return dataInstances;
    }

    public static Classifier[] classificadoresWeka() throws Exception {

        IBk knn = new IBk();
        knn.setKNN(3);

        Classifier[] classifiers = {new NaiveBayes()
                , new DecisionTable()
                , knn
        };
        return classifiers;
    }

    public static Instances[] divisaoTreinoeTeste(Instances data, double numerodePassagens) throws Exception {
        // Randomize data
        Randomize rand = new Randomize();
        rand.setInputFormat(data);
        rand.setRandomSeed((int) numerodePassagens);
        data = Filter.useFilter(data, rand);
        // Remove testpercentage from data to get the train set
        RemovePercentage rp = new RemovePercentage();
        rp.setInputFormat(data);
        rp.setPercentage(numerodePassagens);
        Instances train = Filter.useFilter(data, rp);
        // Remove trainpercentage from data to get the test set
        rp = new RemovePercentage();
        rp.setInputFormat(data);
        //70% treino e 30% teste de forma aleatoria
        rp.setPercentage(numerodePassagens);
        rp.setInvertSelection(true);
        Instances test = Filter.useFilter(data, rp);
        return new Instances[]{train, test};
    }

    public static Evaluation classificao(Classifier model
            , Instances trainingSet
            , Instances testingSet
            , int quantidadeFolds) throws Exception {

        evaluation = new Evaluation(trainingSet);

        model.buildClassifier(trainingSet);

        //evaluation.evaluateModel(model, testingSet);

        evaluation.crossValidateModel(model, trainingSet, Math.min(quantidadeFolds, testingSet.size()), new Random(1));

        return evaluation;
    }

    public void getValueMestrics() throws Exception {
        if (evaluation.numInstances() > 0) {
            List<String> valorMetrics = evaluation.getMetricsToDisplay();
            for (String value : valorMetrics) {
                System.out.println(value);
            }
        }else{
            if(evaluation.numInstances()==0) {
                throw new Exception("Instances not found!");
            }
        }
        ///----processo de regressao de error, para analise de Overfitting
        // Calcula a regressão do erro
        double valueErrorRate= evaluation.errorRate();
        System.out.println("----------Error Rate---------");
        System.out.println(valueErrorRate);

        // Exibe os resultados da avaliação
        System.out.println(evaluation.toSummaryString());
    }

    public static Map<Integer, List<Double>> evaluationResultados(Evaluation eval) throws Exception {
        Map<Integer, List<Double>> valoresVals = new HashMap<>();

        Attribute attribute = eval.getHeader().classAttribute();

        if (attribute.isNominal()) {

            for (int i = 0; i < eval.getHeader().classAttribute().numValues(); i++) {
                valoresVals.put(i, new ArrayList<>());

                valoresVals.get(i).add(eval.numTruePositives(i));
                valoresVals.get(i).add(eval.numTrueNegatives(i));
                valoresVals.get(i).add(eval.numFalsePositives(i));
                valoresVals.get(i).add(eval.numFalseNegatives(i));
                valoresVals.get(i).add(eval.areaUnderROC(i));
                valoresVals.get(i).add(eval.errorRate());
            }
        }
        return valoresVals;
    }

    public static String mapIndexToCLASSE(int index) {
        switch (index) {
            case 0:
                return "achilles.test";
            case 1:
                return "achilles.not.test";
            case 2:
                return "assertj.test";
            case 3:
                return "assertj.not.test";
            case 4:
                return "commons.io.test";
            case 5:
                return "commons.io.not.test";
            case 6:
                return "joda.time.test";
            case 7:
                return "joda.time.not.test";
            case 8:
                return "math.test";
            case 9:
                return "math.not.test";
            case 10:
                return "hadoop.test";
            case 11:
                return "hadoop.not.test";
            case 12:
                return "jackrabbit.test";
            case 13:
                return "jackrabbit.not.test";
            case 14:
                return "jfreechart.test";
            case 15:
                return "jfreechart.not.test";
            case 16:
                return "lucene.test";
            case 17:
                return "lucene.not.test";
            case 18:
                return "poi.test";
            case 19:
                return "poi.not.test";
            default:
                return "";
        }
    }

    public static Map<String, Set<Integer>> createUOTESTEDMapping() {
        Map<String, Set<Integer>> uotestedMapping = new HashMap<>();

        addUOTESTEDValue(uotestedMapping, "achilles.test", 0);
        addUOTESTEDValue(uotestedMapping, "achilles.not.test", 1);
        addUOTESTEDValue(uotestedMapping, "assertj.test", 2);
        addUOTESTEDValue(uotestedMapping, "assertj.not.test", 3);
        addUOTESTEDValue(uotestedMapping, "commons.io.test", 4);
        addUOTESTEDValue(uotestedMapping, "commons.io.not.test", 5);
        addUOTESTEDValue(uotestedMapping, "joda.time.test", 6);
        addUOTESTEDValue(uotestedMapping, "joda.time.not.test", 7);
        addUOTESTEDValue(uotestedMapping, "math.test", 8);
        addUOTESTEDValue(uotestedMapping, "math.not.test", 9);
        addUOTESTEDValue(uotestedMapping, "hadoop.test", 10);
        addUOTESTEDValue(uotestedMapping, "hadoop.not.test", 11);
        addUOTESTEDValue(uotestedMapping, "jackrabbit.test", 12);
        addUOTESTEDValue(uotestedMapping, "jackrabbit.not.test", 13);
        addUOTESTEDValue(uotestedMapping, "jfreechart.test", 14);
        addUOTESTEDValue(uotestedMapping, "jfreechart.not.test", 15);
        addUOTESTEDValue(uotestedMapping, "lucene.test", 16);
        addUOTESTEDValue(uotestedMapping, "lucene.not.test", 17);
        addUOTESTEDValue(uotestedMapping, "poi.test", 18);
        addUOTESTEDValue(uotestedMapping, "poi.not.test", 19);

        return uotestedMapping;
    }

    private static void addUOTESTEDValue(Map<String, Set<Integer>> mapping, String classe, int value) {
        // Se a classe já existe no mapeamento, apenas adicione o valor
        if (mapping.containsKey(classe)) {
            mapping.get(classe).add(value);
        } else {
            // Se a classe não existe, crie um novo conjunto e adicione o valor
            Set<Integer> values = new HashSet<>();
            values.add(value);
            mapping.put(classe, values);
        }
    }

    /*
      "Verdadeiros Positivos (VP): " --->>> valores.get(0)
      "Verdadeiros Negativos (VN): " --->>> valores.get(1)
      "Falsos Positivos (FP): "      --->>> valores.get(2)
      "Falsos Negativos (FN): "      --->>> valores.get(3)
      */

    public static double acuracia(List<Double> valores) {
        return (valores.get(0) + valores.get(1)) / (valores.get(0) + valores.get(1) + valores.get(2) + valores.get(3));
    }
    public static double precisao(List<Double> valores) {
        return (valores.get(0)) / (valores.get(0) + valores.get(2));
    }

    public static double recall(List<Double> valores) {
        return (valores.get(0)) / ((valores.get(0)) + valores.get(3));
    }
    public static double fMedida(List<Double> valores) {
        return (2 * (precisao(valores) * recall(valores)) / (precisao(valores) + recall(valores)));
    }

    public static double mcc(List<Double> valores) {
        double equacaoRaiz = 0;
        equacaoRaiz =     (valores.get(0) + valores.get(2))
                        * (valores.get(0) + valores.get(3))
                        * (valores.get(1) + valores.get(2))
                        * (valores.get(1) + valores.get(3));
        return ((valores.get(0) * valores.get(1)) - (valores.get(2) * valores.get(3))) / Math.sqrt(equacaoRaiz);
    }

}
