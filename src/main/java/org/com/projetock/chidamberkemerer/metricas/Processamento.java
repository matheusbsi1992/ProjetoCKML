package org.com.projetock.chidamberkemerer.metricas;


import org.com.projetock.chidamberkemerer.normalizacao.PreProcessamento;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.supervised.instance.ClassBalancer;
import weka.filters.supervised.instance.Resample;
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

        MultilayerPerceptron mlp = new MultilayerPerceptron();
        mlp.setOptions(new String[]{
                "-L", "0.3",   // Taxa de aprendizado
                "-M", "0.2",   // Momentum
                "-N", "500",  // Número de épocas
                "-H", "10"     // Número de neurônios na camada oculta
        });

        IBk knn = new IBk();

        // Outras opções (por exemplo, distância Euclidiana, pesos)
        knn.setDistanceWeighting(new SelectedTag(IBk.WEIGHT_NONE, IBk.TAGS_WEIGHTING));

        SMO svm = new SMO();

        // Define o kernel (por padrão, PolyKernel)
        svm.setKernel(new weka.classifiers.functions.supportVector.RBFKernel());
        // Define o custo C (controle do overfitting)
        svm.setC(1.0);

        Classifier[] classifiers = {
                new J48()/*,
                 mlp,
                knn,
                svm*/


        };
        return classifiers;
    }

    public static Instances[] divisaoTreinoeTeste(Instances data, double numerodePassagens) throws Exception {
        // Randomize data
        Randomize rand = new Randomize();
        rand.setInputFormat(data);
        rand.setRandomSeed((int) numerodePassagens);
        data = ClassBalancer.useFilter(data, rand);
        // Remove testpercentage from data to get the train set
        RemovePercentage rp = new RemovePercentage();
        rp.setInputFormat(data);
        rp.setPercentage(numerodePassagens);
        Instances train = ClassBalancer.useFilter(data, rp);
        // Remove trainpercentage from data to get the test set
        rp = new RemovePercentage();
        rp.setInputFormat(data);
        //70% treino e 30% teste de forma aleatoria
        rp.setPercentage(numerodePassagens);
        rp.setInvertSelection(true);
        Instances test = ClassBalancer.useFilter(data, rp);
        return new Instances[]{train, test};
    }

    public static Evaluation classificao(Classifier model
            , Instances conjuntoDataSet
            , int numPassos
            /*, Instances testingSet*/) throws Exception {

        evaluation = new Evaluation(conjuntoDataSet);

        //model.buildClassifier(trainingSet);

        //evaluation.evaluateModel(model, testingSet);

        //evaluation.crossValidateModel(model, trainingSet, Math.min(10, testingSet.size()), new Random(1));
        evaluation.crossValidateModel(model, conjuntoDataSet, numPassos, new Random(1));

        return evaluation;
    }

    public static Evaluation executarAvaliacaoBootstrap(Classifier modeloOriginal, Instances dadosOriginais, int numPassos) throws Exception {
        Evaluation evaluation = null;

        for (int i = 0; i < numPassos; i++) {

            Instances amostraBootstrap = gerarBootstrapComReposicao(dadosOriginais, i + 1);

            Classifier modelo = AbstractClassifier.makeCopy(modeloOriginal);
            modelo.buildClassifier(amostraBootstrap);
            
            evaluation = new Evaluation(dadosOriginais);
            evaluation.evaluateModel(modelo, dadosOriginais);
        }

        return evaluation;
    }

    /*public static Evaluation executarAvaliacao(Classifier modeloOriginal, Instances treino, Instances teste, int numPassos) throws Exception {

        Evaluation evaluation = null;
        for (int i = 0; i < numPassos; i++) {

            Instances amostraBootstrap = gerarBootstrapComReposicao(treino, i + 1);

            Classifier modelo = AbstractClassifier.makeCopy(modeloOriginal);
            modelo.buildClassifier(amostraBootstrap);

            evaluation = new Evaluation(amostraBootstrap);
            evaluation.evaluateModel(modelo, teste);

        }

        return evaluation;
    }*/

    private static Instances gerarBootstrapComReposicao(Instances dados, int seed) throws Exception {
        Resample resample = new Resample();
        resample.setNoReplacement(false);
        resample.setSampleSizePercent(100);
        resample.setRandomSeed(seed);
        resample.setInputFormat(dados);
        return Filter.useFilter(dados, resample);
    }

   /* public static void getValueMestrics(Evaluation eval) throws Exception {
        if (eval.numInstances() > 0) {
            List<String> valorMetrics = eval.getMetricsToDisplay();
            for (String value : valorMetrics) {
                System.out.println(value);
            }
        }else{
            if(eval.numInstances()==0) {
                throw new Exception("Instances not found!");
            }
        }
        ///----processo de regressao de error, para analise de Overfitting
        // Calcula a regressão do erro
        double valueErrorRate= eval.errorRate();
        System.out.println("----------Error Rate---------");
        System.out.println(valueErrorRate);

        // Exibe os resultados da avaliação
        System.out.println(eval.toSummaryString());
    }*/

   /* public static Map<Integer, List<Double>> evaluationResultados(Evaluation eval) throws Exception {
        Map<Integer, List<Double>> valoresVals = new HashMap<>();

        Attribute attribute = eval.getHeader().classAttribute();

        if (attribute.isNominal()) {

            for (int i = 0; i < eval.getHeader().classAttribute().numValues(); i++) {
                valoresVals.put(i, new ArrayList<>());

                System.out.println("VP"+eval.numTruePositives(i));
                System.out.println("VN"+eval.numTrueNegatives(i));
                System.out.println("FP"+eval.numFalsePositives(i));
                System.out.println("FN"+eval.numFalseNegatives(i));

                valoresVals.get(i).add(eval.numTruePositives(i));
                valoresVals.get(i).add(eval.numTrueNegatives(i));
                valoresVals.get(i).add(eval.numFalsePositives(i));
                valoresVals.get(i).add(eval.numFalseNegatives(i));
               //valoresVals.get(i).add(eval.areaUnderROC(i));
                //valoresVals.get(i).add(eval.errorRate());
            }

            System.out.println("RESULTADOS DEFINIDOS");
            System.out.println(eval.toMatrixString());
            System.out.println(eval.toMatrixString("a = 1"));
            System.out.println(eval.toMatrixString("b = 2"));
            System.out.println("RESULTADOS DEFINIDOS");
            System.out.println();
            double[][] confusionMatrix = eval.confusionMatrix();

            for (int i = 0; i < confusionMatrix.length; i++) {
                double vp = confusionMatrix[i][i];
                double fn = 0;
                double fp = 0;
                double vn = 0;

                // FN = linha i, exceto [i][i]
                for (int j = 0; j < confusionMatrix.length; j++) {
                    if (j != i) fn += confusionMatrix[i][j];
                }

                // FP = coluna i, exceto [i][i]
                for (int j = 0; j < confusionMatrix.length; j++) {
                    if (j != i) fp += confusionMatrix[j][i];
                }

                // VN = soma total - (VP + FP + FN)
                double total = 0;
                for (int m = 0; m < confusionMatrix.length; m++) {
                    for (int n = 0; n < confusionMatrix.length; n++) {
                        total += confusionMatrix[m][n];
                    }
                }

                vn = total - (vp + fp + fn);

                System.out.println("Classe " + i);
                System.out.println("VP: " + vp);
                System.out.println("FP: " + fp);
                System.out.println("FN: " + fn);
                System.out.println("VN: " + vn);
                System.out.println("--------------------");
            }

            // 5. Obter matriz de confusão
            double[][] cm = eval.confusionMatrix();
            double total = eval.numInstances();

            System.out.println("=== Matriz de Confusão ===");
            for (int i = 0; i < cm.length; i++) {
                for (int j = 0; j < cm.length; j++) {
                    System.out.print((int) cm[i][j] + "\t");
                }
                System.out.println();
            }

            System.out.println("\n=== VP, FP, FN, VN por classe ===");

            for (int i = 0; i < cm.length; i++) {
                String nomeClasse = eval.getHeader().classAttribute().value(i);

                double vp = cm[i][i];
                double fn = 0, fp = 0, vn;

                for (int j = 0; j < cm.length; j++) {
                    if (j != i) {
                        fn += cm[i][j]; // linha
                        fp += cm[j][i]; // coluna
                    }
                }

                vn = total - (vp + fp + fn);

                System.out.println("Classe: " + nomeClasse);
                System.out.println("VP: " + (int) vp);
                System.out.println("FP: " + (int) fp);
                System.out.println("FN: " + (int) fn);
                System.out.println("VN: " + (int) vn);
                System.out.println("---------------------------");
            }
            System.out.println(Arrays.stream(eval.confusionMatrix()).sequential().count());
            System.out.println(eval.kappa());
            System.out.println(eval.toMatrixString());
            System.out.println(eval.toClassDetailsString());
            System.out.println(eval.pctCorrect()-eval.pctIncorrect());
        }
        return valoresVals;
    }*/

    public static Map<Integer, List<Double>> evaluationResultados(Evaluation eval) throws Exception {
        Map<Integer, List<Double>> valoresVals = new HashMap<>();
        try {

        /*for (int i = 0; i < eval.getHeader().classAttribute().numValues(); i++) {
            valoresVals.put(i, new ArrayList<>());

            System.out.println("VP " + eval.numTruePositives(i));
            System.out.println("VN " + eval.numTrueNegatives(i));
            System.out.println("FP " + eval.numFalsePositives(i));
            System.out.println("FN " + eval.numFalseNegatives(i)+"\n");

        }*/

            double[][] confusionMatrix = eval.confusionMatrix();

            // Verifica se a classe é nominal (problema de classificação)
            Attribute classAttribute = eval.getHeader().classAttribute();

            if (!classAttribute.isNominal()) {
                throw new Exception("A classe não é nominal. Este método é apenas para classificação.");
            }

            System.out.println("=== Matriz de Confusão ===\n");
            for (int i = 0; i < confusionMatrix.length; i++) {
                for (int j = 0; j < confusionMatrix[i].length; j++) {
                    System.out.print((int) confusionMatrix[i][j] + "\t");
                }
                System.out.println("| " + classAttribute.value(i));
            }

            System.out.println("\n=== Métricas por Classe (VP, FP, FN, VN) ===\n");

            for (int i = 0; i < confusionMatrix.length; i++) {
                String className = classAttribute.value(i);
                double vp = confusionMatrix[i][i]; // Verdadeiros Positivos (diagonal principal)
                double vn = 0.0;
                double fn = 0.0;
                double fp = 0.0;

                // Calcula FN (soma da linha, exceto diagonal)
                for (int j = 0; j < confusionMatrix.length; j++) {
                    if (j != i) {
                        fn += confusionMatrix[i][j];
                    }
                }

                // Calcula FP (soma da coluna, exceto diagonal)
                for (int j = 0; j < confusionMatrix.length; j++) {
                    if (j != i) {
                        fp += confusionMatrix[j][i];
                    }
                }
                // Calcula VN (soma da coluna, exceto diagonal)
                for (int j = 0; j < confusionMatrix.length; j++) {
                    if (j != i) {
                        vn += confusionMatrix[j][i + 1];
                    }
                }

                // Armazena os valores no mapa
                List<Double> metrics = new ArrayList<>();
                metrics.add(vp);
                metrics.add(vn);
                metrics.add(fp);
                metrics.add(fn);
                valoresVals.put(i, metrics);

                // Exibe os resultados
                System.out.println("Classe: " + className + " (" + i + ")");
                System.out.println("VP: " + (int) vp);
                System.out.println("FP: " + (int) fp);
                System.out.println("FN: " + (int) fn);
                System.out.println("VN: " + (int) vn);
                System.out.println("---------------------------");
            }


        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.getMessage();
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
        equacaoRaiz = (valores.get(0) + valores.get(2))
                * (valores.get(0) + valores.get(3))
                * (valores.get(1) + valores.get(2))
                * (valores.get(1) + valores.get(3));
        return ((valores.get(0) * valores.get(1)) - (valores.get(2) * valores.get(3))) / Math.sqrt(equacaoRaiz);
    }

}
