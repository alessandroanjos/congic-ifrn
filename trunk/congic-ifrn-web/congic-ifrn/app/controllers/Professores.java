package controllers;

import java.util.List;

import models.Campus;
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
		
		return ok(views.html.Professores.formulario.render(form(Usuario.class), campus));
	}
	
	public static Result cadastrar() {
		Form<Usuario> form = form(Usuario.class).bindFromRequest();
		Long idCampus = Long.valueOf(form.data().get("idCampus"));
		
		if(form.hasErrors() || !form.get().senha.equals(form.data().get("confirmacaoSenha"))) {
			
			List<Campus> campus = Campus.find.findList();
			
			flash().put("error", "VocÃª deve preencher todos os campos corretamente. Tente novamente!");
			return badRequest(views.html.Professores.formulario.render(form, campus));
		}
		
		Usuario professor = form.get();
		professor.isAtivo = true;
		professor.isProfessor = true;
		professor.campus = Campus.find.byId(idCampus);
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
