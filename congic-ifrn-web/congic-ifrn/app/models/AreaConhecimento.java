package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

/**
 * Entidade responsÃ¡vel pela representaÃ§Ã£o das Areas de Conhecimento no banco de dados.
 * 
 * @author Alessandro
 */

@Entity
public class AreaConhecimento extends Model {
	
	@Id
	public int id;
	
	@Required(message="O campo deve ser preenchido.")
	@Column(unique=true)
	public String nome;
	
	@OneToMany(mappedBy="areaConhecimento")
	public List<AreaEspecifica> areaEspecifica;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<AreaEspecifica> getAreaEspecifica() {
		return areaEspecifica;
	}

	public void setAreaEspecifica(List<AreaEspecifica> areaEspecifica) {
		this.areaEspecifica = areaEspecifica;
	}

	public static Finder<Long, AreaConhecimento> find = new Finder<Long, AreaConhecimento>(Long.class, AreaConhecimento.class);


}
