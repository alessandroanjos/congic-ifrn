package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

/**
 * Entidade responsÃ¡vel pelo Grupo de Pesquisa do sistema no banco de dados.
 * 
 * @author Alessandro
 */

@Entity
public class GrupoPesquisa extends Model{

	@Id
	public Long id;
	
	@Column(unique=true)
	@Required(message="O campo deve ser preenchido.")
	public String nome;
		
	@ManyToOne
	public Campus campus;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}
	
	public static Finder<Long, GrupoPesquisa> find = new Finder<Long, GrupoPesquisa>(Long.class, GrupoPesquisa.class);
}
