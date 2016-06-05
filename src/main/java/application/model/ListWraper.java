package application.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe que ajuda a construis uma lista de reproduções.
 * usado para salvar uma lista de Reproducoes em XML.
 * 
 * @author Leticia
 */

public class ListWraper {

	@XmlElementWrapper(name = "wrapperList")
    private List<String> list;

    public void setList(List<String> list) {
        this.list = list;
    }
}
