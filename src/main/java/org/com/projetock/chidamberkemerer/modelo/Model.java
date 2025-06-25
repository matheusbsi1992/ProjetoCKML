package org.com.projetock.chidamberkemerer.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Model {
    private String CBO;
    private String DIT;
    private String LCOM;
    private String NOC;
    private String RFC;
    private String WMC;
    private String CLASSE;
    private String UOTESTED;

    public String toARFFString() {
        return String.join(",", CLASSE, CBO, DIT, LCOM, NOC, RFC, WMC, UOTESTED);
    }
}
