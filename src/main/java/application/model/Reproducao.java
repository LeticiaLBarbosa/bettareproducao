package application.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import application.util.LocalDateAdapter;
import application.util.XmlGenericMapAdapter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reproducao {
	private final StringProperty ID;
	private final StringProperty pai_macho;
	private final StringProperty mae_macho;
	private final StringProperty pai_femea;
	private final StringProperty mae_femea;
	private final StringProperty macho;
	private final StringProperty femea;
	private final StringProperty infoFemea;
	private final StringProperty infoMacho;
	private final StringProperty linhagem_paiMacho;
	private final StringProperty linhagem_maeMacho;
	private final StringProperty linhagem_paiFemea;
	private final StringProperty linhagem_maeFemea;
	private final StringProperty linhagem_macho;
	private final StringProperty linhagem_femea;
	private final ObjectProperty<LocalDate> inicio;
	private final ObjectProperty<LocalDate> retirada_femea;
	private final ObjectProperty<LocalDate> retirada_macho;
	private final StringProperty informacoes;
	@XmlJavaTypeAdapter(XmlGenericMapAdapter.class)
	private Map<Integer, String> resultados;
	private ObjectProperty<LocalDate> ultimaAtualizacao;

	public Reproducao() {
		ID = new SimpleStringProperty();
		pai_macho = new SimpleStringProperty();
		mae_macho = new SimpleStringProperty();
		pai_femea = new SimpleStringProperty();
		mae_femea = new SimpleStringProperty();
		macho = new SimpleStringProperty();
		femea = new SimpleStringProperty();
		infoMacho = new SimpleStringProperty();
		infoFemea = new SimpleStringProperty();
		linhagem_paiMacho = new SimpleStringProperty();
		linhagem_maeMacho = new SimpleStringProperty();
		linhagem_paiFemea = new SimpleStringProperty();
		linhagem_maeFemea = new SimpleStringProperty();
		linhagem_femea = new SimpleStringProperty();
		linhagem_macho = new SimpleStringProperty();
		inicio = new SimpleObjectProperty<LocalDate>();
		retirada_femea = new SimpleObjectProperty<LocalDate>();
		retirada_macho = new SimpleObjectProperty<LocalDate>();
		informacoes = new SimpleStringProperty();
		resultados = new HashMap<>();
		ultimaAtualizacao = new SimpleObjectProperty<LocalDate>();
	}

	public String getID() {
		return ID.get();
	}

	public void setID(String iD) {
		this.ID.set(iD);
	}

	public StringProperty getIDProperty() {
		return ID;
	}

	public String getPai_macho() {
		return pai_macho.get();
	}

	public void setPai_macho(String pai_macho) {
		this.pai_macho.set(pai_macho);
	}

	public StringProperty getPai_machoProperty() {
		return pai_macho;
	}

	public String getMae_macho() {
		return mae_macho.get();
	}

	public void setMae_macho(String mae_macho) {
		this.mae_macho.set(mae_macho);
	}

	public StringProperty getMae_machoProperty() {
		return mae_macho;
	}

	public String getPai_femea() {
		return pai_femea.get();
	}

	public void setPai_femea(String pai_femea) {
		this.pai_femea.set(pai_femea);
	}

	public StringProperty getPai_femeaProperty() {
		return pai_femea;
	}

	public String getMae_femea() {
		return mae_femea.get();
	}

	public void setMae_femea(String mae_femea) {
		this.mae_femea.set(mae_femea);
	}

	public StringProperty getMae_femeaProperty() {
		return mae_femea;
	}

	public String getMacho() {
		return macho.get();
	}

	public void setMacho(String macho) {
		this.macho.set(macho);
	}

	public StringProperty getMachoProperty() {
		return macho;
	}

	public String getFemea() {
		return femea.get();
	}

	public void setFemea(String femea) {
		this.femea.set(femea);
	}

	public StringProperty getFemeaProperty() {
		return femea;
	}

	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getInicio() {
		return inicio.get();
	}

	public void setInicio(LocalDate inicio) {
		this.inicio.set(inicio);
	}

	public ObjectProperty<LocalDate> getInicioProperty() {
		return inicio;
	}

	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getRetirada_femea() {
		return retirada_femea.get();
	}

	public void setRetirada_femea(LocalDate retirada_femea) {
		this.retirada_femea.set(retirada_femea);
	}

	public ObjectProperty<LocalDate> getRetirada_femeaProperty() {
		return retirada_femea;
	}

	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getRetirada_macho() {
		return retirada_macho.get();
	}

	public void setRetirada_macho(LocalDate retirada_macho) {
		this.retirada_macho.set(retirada_macho);
	}

	public ObjectProperty<LocalDate> getRetirada_machoProperty() {
		return retirada_macho;
	}

	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getUltimaAtualizacao() {
		return ultimaAtualizacao.get();
	}

	public void setUltimaAtualizacao(LocalDate ultimaAtualizacao) {
		this.ultimaAtualizacao.set(ultimaAtualizacao);
	}

	public ObjectProperty<LocalDate> getUltimaAtualizacaoProperty() {
		return ultimaAtualizacao;
	}
	
	public String getInformacoes() {
		return informacoes.get();
	}

	public void setInformacoes(String informacoes) {
		this.informacoes.set(informacoes);
	}

	public Map<Integer, String> getResultados() {
		return this.resultados;
	}

	public void setResultados(Map<Integer, String> resultados) {
		this.resultados = resultados;
	}

	public String getInfoMacho() {
		return infoMacho.get();
	}

	public void setInfoMacho(String infoMacho) {
		this.infoMacho.set(infoMacho);
	}

	public StringProperty getInfoMachoProperty() {
		return infoMacho;
	}

	public String getInfoFemea() {
		return infoFemea.get();
	}

	public void setInfoFemea(String infoFemea) {
		this.infoFemea.set(infoFemea);
	}

	public StringProperty getInfoFemeaProperty() {
		return infoFemea;
	}

	public String getLinhagemPaiMacho() {
		return linhagem_paiMacho.get();
	}

	public void setLinhagemPaiMacho(String linhagem_paiMacho) {
		this.linhagem_paiMacho.set(linhagem_paiMacho);
	}

	public StringProperty getLinhagemPaiMachoProperty() {
		return linhagem_paiMacho;
	}

	public String getLinhagemMaeMacho() {
		return linhagem_maeMacho.get();
	}

	public void setLinhagemMaeMacho(String linhagem_maeMacho) {
		this.linhagem_maeMacho.set(linhagem_maeMacho);
	}

	public StringProperty getLinhagemMaeMachoProperty() {
		return linhagem_maeMacho;
	}

	public String getLinhagemPaiFemea() {
		return linhagem_paiFemea.get();
	}

	public void setLinhagemPaiFemea(String linhagem_paiFemea) {
		this.linhagem_paiFemea.set(linhagem_paiFemea);
	}

	public StringProperty getLinhagemPaiFemeaProperty() {
		return linhagem_paiFemea;
	}

	public String getLinhagemMaeFemea() {
		return linhagem_maeFemea.get();
	}

	public void setLinhagemMaeFemea(String linhagem_maeFemea) {
		this.linhagem_maeFemea.set(linhagem_maeFemea);
	}

	public StringProperty getLinhagemMaeFemeaProperty() {
		return linhagem_maeFemea;
	}

	public String getLinhagemFemea() {
		return linhagem_femea.get();
	}

	public void setLinhagemFemea(String linhagem_femea) {
		this.linhagem_femea.set(linhagem_femea);
	}

	public StringProperty getLinhagemFemeaProperty() {
		return linhagem_femea;
	}

	public String getLinhagemMacho() {
		return linhagem_macho.get();
	}

	public void setLinhagemMacho(String linhagem_macho) {
		this.linhagem_macho.set(linhagem_macho);
	}

	public StringProperty getLinhagemMachoProperty() {
		return linhagem_macho;
	}
}
