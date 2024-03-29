package models;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

/**
 * Entidade responsÃ¡vel pela representaÃ§Ã£o dos Artigo no banco de dados.
 * 
 * @author Alessandro
 */

@Entity
public class Artigo extends Model{
	
	@Id
	public Long id;
	
	@ManyToOne
	public Usuario autorUm;
	
	public String autorDois;
	public String autorTres;
	public String nomeOrientador;
	
	@ManyToOne
	public GrupoPesquisa grupoPesquisa;
	
	@ManyToOne
	public Campus campus;
	
	@ManyToOne
	public AreaConhecimento areaConhecimento;
	
	@ManyToOne
	public AreaEspecifica areaEspecifica;
	
	
	@Required(message="O campo deve ser preenchido.")
	@Column(unique=true)
	public String titulo;
	
	@Lob
	@Basic(fetch=FetchType.EAGER)
	@Required(message="O campo deve ser preenchido.")
	public String resumo;
	
	@Lob
	@Basic(fetch=FetchType.EAGER)
	@Required(message="O campo deve ser preenchido.")
	public String introducao;
	
	@Lob
	@Basic(fetch=FetchType.EAGER)
	@Required(message="O campo deve ser preenchido.")
	public String fundamentacaoTeorica;
	
	@Lob
	@Basic(fetch=FetchType.EAGER)
	@Required(message="O campo deve ser preenchido.")
	public String justificativa;
	
	@Lob
	@Basic(fetch=FetchType.EAGER)
	@Required(message="O campo deve ser preenchido.")
	public String objetivos;
	
	@Lob
	@Basic(fetch=FetchType.EAGER)
	@Required(message="O campo deve ser preenchido.")
	public String metodologia;
	
	@Lob
	@Basic(fetch=FetchType.EAGER)
	@Required(message="O campo deve ser preenchido.")
	public String referencias;	
	
	@OneToMany
	public List<ArtigoAvaliado> progressoArtigo;
	
	public Long usuarioAvaliar;
	
	
	/*
	 * http://docs.oracle.com/javaee/6/api/javax/persistence/Lob.html
	 */
	@Lob
	@Basic
	@Column(name = "arquivo", columnDefinition = "BLOB NOT NULL")
	@Required(message="Não feito o uploud do arquivo.")
	public byte[] arquivo;

	public URI urlDownload;
	
	
	
	public URI getUrlDownload() {
		return urlDownload;
	}









	public void setUrlDownload(URI urlDownload) {
		this.urlDownload = urlDownload;
	}









	public Artigo(){
		
		this.progressoArtigo = new ArrayList<ArtigoAvaliado>();
		arquivo = new byte[20 * 1024 * 1024];
		
	}
	
	
	
	
	   
	 
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getAutorUm() {
		return autorUm;
	}

	public void setAutorUm(Usuario autorUm) {
		this.autorUm = autorUm;
	}

	public String getAutorDois() {
		return autorDois;
	}

	public void setAutorDois(String autorDois) {
		this.autorDois = autorDois;
	}

	public String getAutorTres() {
		return autorTres;
	}

	public void setAutorTres(String autorTres) {
		this.autorTres = autorTres;
	}

	public String getNomeOrientador() {
		return nomeOrientador;
	}

	public void setNomeOrientador(String nomeOrientador) {
		this.nomeOrientador = nomeOrientador;
	}

	public GrupoPesquisa getGrupoPesquisa() {
		return grupoPesquisa;
	}

	public void setGrupoPesquisa(GrupoPesquisa grupoPesquisa) {
		this.grupoPesquisa = grupoPesquisa;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	public AreaConhecimento getAreaConhecimento() {
		return areaConhecimento;
	}

	public void setAreaConhecimento(AreaConhecimento areaConhecimento) {
		this.areaConhecimento = areaConhecimento;
	}

	public AreaEspecifica getAreaEspecifica() {
		return areaEspecifica;
	}

	public void setAreaEspecifica(AreaEspecifica areaEspecifica) {
		this.areaEspecifica = areaEspecifica;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getIntroducao() {
		return introducao;
	}

	public void setIntroducao(String introducao) {
		this.introducao = introducao;
	}

	public String getFundamentacaoTeorica() {
		return fundamentacaoTeorica;
	}

	public void setFundamentacaoTeorica(String fundamentacaoTeorica) {
		this.fundamentacaoTeorica = fundamentacaoTeorica;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public String getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}

	public String getMetodologia() {
		return metodologia;
	}

	public void setMetodologia(String metodologia) {
		this.metodologia = metodologia;
	}

	public String getReferencias() {
		return referencias;
	}

	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}

	public List<ArtigoAvaliado> getProgressoArtigo() {
		return progressoArtigo;
	}

	public void setProgressoArtigo(List<ArtigoAvaliado> progressoArtigo) {
		this.progressoArtigo = progressoArtigo;
	}
	
	public Long getUsuarioAvaliar() {
		return usuarioAvaliar;
	}

	public void setUsuarioAvaliar(Long usuarioAvaliar) {
	    this.usuarioAvaliar = usuarioAvaliar;
	}	

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}
	public static Finder<Long, Artigo> find = new Finder<Long, Artigo>(Long.class, Artigo.class);

}
