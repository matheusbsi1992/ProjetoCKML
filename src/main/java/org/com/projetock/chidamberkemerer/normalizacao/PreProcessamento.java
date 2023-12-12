package org.com.projetock.chidamberkemerer.normalizacao;

import lombok.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.com.projetock.chidamberkemerer.modelo.Model;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@Data
public class PreProcessamento {

    private static Model model = new Model();
    private static List<Model> modelList = new ArrayList<>();

    public static List<Model> getModelList() {
        return modelList;
    }

    public static Model getModel() {
        return model;
    }

    public PreProcessamento() {
    }

    private static Model tipoAtributos(String itemv[]) {
        model.setCLASSE(itemv[0]);
        model.setCBO(itemv[1]);
        model.setDIT(itemv[2]);
        model.setLCOM(itemv[3]);
        model.setNOC(itemv[4]);
        model.setRFC(itemv[5]);
        model.setWMC(itemv[6]);
        itemv = null;
        return model;
    }

    public static List<Model> abrirSessaoConjuntodeDados() {

        String itemv[] = null;

        try (Reader reader = new FileReader("projetocontido/conjuntodedadosnaonormalizadoatualizado.csv");

             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(contencaodeCaracteresEspeciais().listIterator().next()))) {

            for (CSVRecord csvRecord : csvParser) {

                if (containsDescricoes(csvRecord)) {
                    continue;
                } else {
                    itemv = (Arrays.stream(csvRecord
                                    .values())
                            .iterator()
                            .next()
                            .replace(contencaodeCaracteresEspeciais()
                                    .toString(), "")
                            .split(","));

                    model = tipoAtributos(itemv);
                    contemValorAlteradoParaZero();
                    modelList = normalizacaoDados(model);
                }
            }
            converterListaemARFF();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelList;
    }


    private static void converterListaemARFF() {
        try {
            String[] atributosArray = {"CLASSE", "CBO", "DIT", "LCOM", "NOC", "RFC", "WMC", "UOTESTED"};

            Path arffFilePath = Paths.get(System.getProperty("user.home"), "Documents", "projetocK", "projetocontido", "preprocessamentoCK.arff");

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(arffFilePath.toFile()))) {
                bufferedWriter.write("@relation preprocessamentoCK\n\n");
                int valorAux = 0;
                for (String atributo : atributosArray) {
                    //String tipoAtributo = atributo.equals("CLASSE") ? "STRING" : "REAL";
                    if (valorAux <= atributosArray.length - 2) {
                        bufferedWriter.write(String.format("@attribute %s %s \n", atributo, "REAL"));//%s//, tipoAtributo));
                    }
                    valorAux++;
                }

                Set<String> valoresClass = obterValoresUnicosAtributoClass();

                List<String> auxValor = new ArrayList<>(valoresClass);

                bufferedWriter.write(String.format("@attribute UOTESTED {%s}\n", String.join(",", String.valueOf(listOrder(auxValor))
                        .replaceAll("[\\{\\[\\]\\}]", ""))));
                bufferedWriter.write("@data\n");

                for (Model model : modelList) {
                    bufferedWriter.write(model.toARFFString() + "\n");
                }
                bufferedWriter.flush();
                bufferedWriter.close();
            }


        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static List<Integer> listOrder(List<String> valores){

        List<Integer> list = new ArrayList<>();
        for (String s : valores) {
            list.add(Integer.parseInt(s));
        }

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }


        return list;
    }

    private static Set<String> obterValoresUnicosAtributoClass() {
        Set<String> valoresClass = new HashSet<>();
        for (Model model : modelList) {
            valoresClass.add(model.getUOTESTED());
        }
        return valoresClass;
    }

    private static Model contemValorAlteradoParaZero() {

        Map<String, String> valorExistente = Map.of("n/a", "0");

        for (Map.Entry<String, String> valorNovo : valorExistente.entrySet()) {
            //--CBO
            if (model.getCBO().contains(valorNovo.getKey())) {
                model.setCBO(valorNovo.getValue());
            }
            //--DIT
            if (model.getDIT().contains(valorNovo.getKey())) {
                model.setDIT(valorNovo.getValue());
            }
            //--LCOM
            if (model.getLCOM().contains(valorNovo.getKey())) {
                model.setLCOM(valorNovo.getValue());
            }
            //--NOC
            if (model.getNOC().contains(valorNovo.getKey())) {
                model.setNOC(valorNovo.getValue());
            }
            //--RFC
            if (model.getRFC().contains(valorNovo.getKey())) {
                model.setRFC(valorNovo.getValue());
            }
            //--WMC
            if (model.getWMC().contains(valorNovo.getKey())) {
                model.setWMC(valorNovo.getValue());
            }
        }
        return model;
    }

    private static List<Character> contencaodeCaracteresEspeciais() {
        return List.of('[', ']', ',');
    }

    private static boolean containsDescricoes(CSVRecord csvRecord) {
        return csvRecord.toString().contains("Chidamber-Kemerer")
                || csvRecord.toString().contains("$")
                || csvRecord.toString().contains("Class");
    }

    // Função auxiliar para verificar se a string contém alguma das palavras fornecidas
    private static boolean containsValorTeste(String texto, String[] palavras) {
        for (String palavra : palavras) {
            if (texto.trim().toLowerCase().contains(palavra.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private static List<Model> normalizacaoDados(Model modelo) {

        try {

            // Mapeamento para a condição "Classe"
            Map<String, String> mapeamentoClasse = Map.of(
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

            String valorNomeClasse = modelo.getCLASSE();

            for (Map.Entry<String, String> entrada : mapeamentoClasse.entrySet()) {
                if (modelo.getCLASSE().trim().toLowerCase().contains(entrada.getKey())) {

                    String[] palavrasUOTESTED = {"test", "tested", "test case", "testing", "case test", "_test"};

                    // Mapei valor para o modelo correspondente
                    modelo.setCLASSE(entrada.getValue());
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

                    // Mapeamento para a condição "Teste"
                    int valorSet = 1;
                    for (int i = 1; i <= 10; i++) {
                        if (modelo.getCLASSE().equals(String.valueOf(i))) {
                            // Mapeamento para a condição "UOTESTED"
                            int uotestedValue = containsValorTeste(valorNomeClasse, palavrasUOTESTED) ? valorSet : valorSet + 1;
                            modelo.setUOTESTED(String.valueOf(uotestedValue));

                            modelList.add(new Model(modelo.getCBO(),
                                    modelo.getDIT(),
                                    modelo.getLCOM(),
                                    modelo.getNOC(),
                                    modelo.getRFC(),
                                    modelo.getWMC(),
                                    modelo.getCLASSE(),
                                    modelo.getUOTESTED())
                            );
                            break;
                        }
                        valorSet += 2;
                    }
                    break;
                } else {
                    modelo.setUOTESTED("invalido");
                }
            }

        } catch (Exception exception) {
            exception.getMessage();
        }
        return modelList;
    }

}