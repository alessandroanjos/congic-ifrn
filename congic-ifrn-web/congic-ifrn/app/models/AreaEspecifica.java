package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

/**
 * Entidade responsável por representar a Area Específica da Area de Conhecimento no banco de dados.
 * 
 * @author Alessandro
 */

@Entity
public class AreaEspecifica extends Model {
	
	@Id
	public int id;
	
	@Required(message="O campo deve ser preenchido.")
	public String nome;
	
	@ManyToOne
	public AreaConhecimento areaConhecimento;

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

	public AreaConhecimento getAreaConhecimento() {
		return areaConhecimento;
	}

	public void setAreaConhecimento(AreaConhecimento areaConhecimento) {
		this.areaConhecimento = areaConhecimento;
	}
	
	public static Finder<Long, AreaEspecifica> find = new Finder<Long, AreaEspecifica>(Long.class, AreaEspecifica.class);

}
