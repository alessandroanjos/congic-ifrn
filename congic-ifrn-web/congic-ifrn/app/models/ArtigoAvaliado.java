package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

/**
 * Entidade responsÃ¡vel pela representaÃ§Ã£o dos Artigos Avaliados do sistema 
 * no banco de dados.
 *  
 * @author Alessandro
 */

@Entity
public class ArtigoAvaliado extends Model{

	@Id
	public int id;
	
	@ManyToOne
	public Usuario usuario;
		
	@ManyToOne
	public Artigo artigo;
	
	public boolean situacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Artigo getArtigo() {
		return artigo;
	}

	public void setArtigo(Artigo artigo) {
		this.artigo = artigo;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}
	
}
