package models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

/**
 * Entidade responsÃ¡vel pela representaÃ§Ã£o dos usuÃ¡rios do sistema no banco de dados.
 * 
 * @author Alessandro
 */

@Entity
public class Usuario extends Model{

	@Id
	public Long id;
	
	@Required(message="O campo deve ser preenchido.")
	@Column(unique=true)
	@MinLength(value=4,message="A login deve conter no mÃ­nimo 4 caracteres.")
	public String login;
	
	@Required(message="O campo deve ser preenchido.")
	@MinLength(value=4,message="A senha deve conter no mÃ­nimo 4 caracteres.")
	public String senha;
	
	@Required(message="O campo deve ser preenchido.")
	public String nome;
	
	@Required(message="O campo deve ser preenchido.")
	public String cpf;
	
	@Required(message="O campo deve ser preenchido.")
	public String sexo;
	
	@Temporal(TemporalType.DATE)
	public String dataNascimento;
	
	@Required(message="O campo deve ser preenchido.")
	@Column(unique=true)
	@Email(message="VocÃª deve informar um email vÃ¡lido.")
	public String email;
	
	@Required(message="O campo deve ser preenchido.")
	public String telefone;
	
	@ManyToOne
	public Campus campus;
	
	@ManyToOne
	public AreaConhecimento areaConhecimento;
	
	@ManyToOne
	public AreaEspecifica areaEspecifica;
	
	@ManyToOne
	public Bolsa bolsa;
	
	@OneToMany
	public List<Artigo> artigos;
	
	@OneToMany
	public List<ArtigoAvaliado> progresso;
	
	public boolean isProfessor;
	public boolean isServidor;
	public boolean isAdministrador;
	public boolean isBolsista;
	public boolean isAtivo;
	
	
	
	public Usuario() {
		
		this.isAtivo = false;
		this.progresso = new ArrayList<ArtigoAvaliado>();
		this.artigos = new ArrayList<Artigo>();
		
	}

	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDataNascimento() {
		return new SimpleDateFormat("dd/MM/yyyy").format(dataNascimento);
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	public Bolsa getBolsa() {
		return bolsa;
	}

	public void setBolsa(Bolsa bolsa) {
		this.bolsa = bolsa;
	}

	public List<Artigo> getArtigos() {
		return artigos;
	}

	public void setArtigos(List<Artigo> artigos) {
		this.artigos = artigos;
	}

	public List<ArtigoAvaliado> getProgresso() {
		return progresso;
	}

	public void setProgresso(List<ArtigoAvaliado> progresso) {
		this.progresso = progresso;
	}

	public boolean isProfessor() {
		return isProfessor;
	}

	public void setProfessor(boolean isProfessor) {
		this.isProfessor = isProfessor;
	}

	public boolean isServidor() {
		return isServidor;
	}

	public void setServidor(boolean isServidor) {
		this.isServidor = isServidor;
	}

	public boolean isAdministrador() {
		return isAdministrador;
	}

	public void setAdministrador(boolean isAdministrador) {
		this.isAdministrador = isAdministrador;
	}

	public boolean isBolsista() {
		return isBolsista;
	}

	public void setBolsista(boolean isBolsista) {
		this.isBolsista = isBolsista;
	}

	public boolean isAtivo() {
		return isAtivo;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}
	
	
	
 



public static Finder<Long, Usuario> find = new Finder<Long, Usuario>(Long.class, Usuario.class);

	
	
	/**
	 * MÃ©todo <strong>Save()</strong> sobrescrito para criptografar 
	 * a senha do usuÃ¡rio antes de enviar para o banco de dados.
	 * <br/>
	 * MÃ©todo de Criptografia: MD5
	 */
	@Override
	public void save() {
		try {
			this.senha = criptografarSenha(senha);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		super.save();
	}
	
	/**
     * MÃ©todo responsÃ¡vel por verificar se o usuÃ¡rio e senha conferem no banco de dados.
	 * @throws NoSuchAlgorithmException 
     */
    public static Usuario autenticar(String login, String senha) throws NoSuchAlgorithmException {
        return find.where()
            .eq("login", login)
            .eq("senha", criptografarSenha(senha))
            .findUnique();
    }
    
    /**
     * MÃ©todo responsÃ¡vel pela criptografia em MD5 da senha do usuÃ¡rio 
     * no momento em que o objeto Ã© persistido no banco de dados.
     * 
     * @param senha
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String criptografarSenha(String senha) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(senha.getBytes(), 0, senha.length());
		return new BigInteger(1, digest.digest()).toString(16);
	}
	
}
