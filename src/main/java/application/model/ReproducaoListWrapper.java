package application.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe que ajuda a construis uma lista de reproduções.
 * usado para salvar uma lista de Reproducoes em XML.
 * 
 * @author Leticia
 */

@XmlRootElement(name = "reproducoes")
public class ReproducaoListWrapper {
	private List<Reproducao> reproducoes;

    @XmlElement(name = "reproducao")
    public List<Reproducao> getReproducoes() {
        return reproducoes;
    }

    public void setReproducoes(List<Reproducao> reproducoes) {
        this.reproducoes = reproducoes;
    }
}
