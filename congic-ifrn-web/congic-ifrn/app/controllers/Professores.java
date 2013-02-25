package controllers;

import java.util.List;

import models.AreaConhecimento;
import models.AreaEspecifica;
import models.Campus;
import models.GrupoPesquisa;
import models.Usuario;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Classe controladora de Professores.
 * @author Alessandro
 *
 */

public class Professores extends Controller{
	
	
	public static Result index(){
		return ok(views.html.Professores.index.render());
	}
	
	public static Result listaProfessores(){
		return TODO;
	}
	
	public static Result formulario(){
		
		List<Campus> campus = Campus.find.findList();
		List<AreaConhecimento> areaConhecimento  = AreaConhecimento.find.findList();
		List<AreaEspecifica> areaEspecifica  = AreaEspecifica.find.findList();
		
	    
		return ok(views.html.Professores.formulario.render(form(Usuario.class), campus, areaConhecimento, areaEspecifica));
	}
	
	public static Result cadastrar() {
		Form<Usuario> form = form(Usuario.class).bindFromRequest();
		Long idCampus = Long.valueOf(form.data().get("idCampus"));
		Long idAreaConhecimento = Long.valueOf(form.data().get("idAreaConhecimento"));
		Long idAreaEspecifica = Long.valueOf(form.data().get("idAreaEspecifica"));
		
		if(form.hasErrors() || !form.get().senha.equals(form.data().get("confirmacaoSenha"))) {
			
			List<Campus> campus = Campus.find.findList();
			List<AreaConhecimento> areaConhecimento  = AreaConhecimento.find.findList();
			List<AreaEspecifica> areaEspecifica  = AreaEspecifica.find.findList();
			
			flash().put("error", "VocÃª deve preencher todos os campos corretamente. Tente novamente!");
			return badRequest(views.html.Professores.formulario.render(form(Usuario.class), campus, areaConhecimento, areaEspecifica));
		}
		
		Usuario professor = form.get();
		professor.isAtivo = true;
		professor.isProfessor = true;
		professor.campus = Campus.find.byId(idCampus);
		professor.areaConhecimento = AreaConhecimento.find.byId(idAreaConhecimento);
		professor.areaEspecifica = AreaEspecifica.find.byId(idAreaEspecifica);
		professor.bolsa = null;
		//professor.dataNascimento = 
		professor.save();
		
		// Envia o email de confirmaÃ§Ã£o de cadastro no sistema!
		//RegistroMailer.enviarMensagemRegistro(professor);
		
		flash().put("success", "Professor \""+ professor.nome +"\" cadastrado com sucesso!");
		
		
		return redirect(routes.Administracao.index());
	}
	
	public static Result formularioEdicao(Long id) {
		return TODO;
	}
	
	public static Result editar(Long id) {
		return TODO;
	}
	
	public static Result deletar(Long id) {
		return TODO;
	}

}
