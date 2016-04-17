package application.model;

import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import application.util.LocalDateAdapter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

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
	private final ListProperty<String> resultados;
	private ObjectProperty<LocalDate> ultimaAtualizacao;

	public Reproducao() {
		this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
				null, null);
	}

	public Reproducao(String ID, String pai_macho, String mae_macho, String pai_femea, String mae_femea, String macho,
			String femea, String infoMacho, String infoFemea, String linhagem_paiMacho, String linhagem_maeMacho,
			String linhagem_paiFemea, String linhagem_maeFemea, String linhagem_macho, String linhagem_femea,
			LocalDate inicio, LocalDate retirada_femea, LocalDate retirada_macho, String informacoes,
			List<String> resultados) {
		this.ID = new SimpleStringProperty(ID);
		this.pai_macho = new SimpleStringProperty(pai_macho);
		this.mae_macho = new SimpleStringProperty(mae_macho);
		this.pai_femea = new SimpleStringProperty(pai_femea);
		this.mae_femea = new SimpleStringProperty(mae_femea);
		this.macho = new SimpleStringProperty(macho);
		this.femea = new SimpleStringProperty(femea);
		this.infoMacho = new SimpleStringProperty(infoMacho);
		this.infoFemea = new SimpleStringProperty(infoFemea);
		this.linhagem_paiMacho = new SimpleStringProperty(linhagem_paiMacho);
		this.linhagem_maeMacho = new SimpleStringProperty(linhagem_maeMacho);
		this.linhagem_paiFemea = new SimpleStringProperty(linhagem_paiFemea);
		this.linhagem_maeFemea = new SimpleStringProperty(linhagem_maeFemea);
		this.linhagem_femea = new SimpleStringProperty(linhagem_femea);
		this.linhagem_macho = new SimpleStringProperty(linhagem_macho);
		this.inicio = new SimpleObjectProperty<LocalDate>(inicio);
		this.retirada_femea = new SimpleObjectProperty<LocalDate>(retirada_femea);
		this.retirada_macho = new SimpleObjectProperty<LocalDate>(retirada_macho);
		this.informacoes = new SimpleStringProperty(informacoes);
		this.resultados = new SimpleListProperty<>();
		this.ultimaAtualizacao = new SimpleObjectProperty<LocalDate>();
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

	public List<String> getResultados() {
		return resultados;
	}

	public void setResultados(List<String> resultados) {
		for (String resultado : resultados) {
			this.resultados.add(resultado);
		}
	}

	public ListProperty<String> getResultadosProperty() {
		return resultados;
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
