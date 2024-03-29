package controllers;

import helpers.Seguranca.InformacoesUsuarioHelper;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import models.AreaConhecimento;
import models.AreaEspecifica;
import models.Artigo;
import models.ArtigoAvaliado;
import models.Campus;
import models.Usuario;

import models.GrupoPesquisa;
import models.Artigo;

import org.apache.commons.io.IOUtils;

import com.mysql.jdbc.Blob;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;

public class Artigos extends Controller{

	public static Result index(){
		Long meuId = InformacoesUsuarioHelper.getUsuarioLogado().id;
		
		List<Artigo> artigos = Artigo.find.where().eq("usuario_avaliar", meuId).findList();
		
		List<Usuario> professores  = Usuario.find.where().eq("isProfessor", true).findList();
		//List<Artigo> artigos = Artigo.find.findList();
		
		if (InformacoesUsuarioHelper.getUsuarioLogado().isAdministrador)
		{
			return ok(views.html.Artigos.index.render(artigos, professores));
		}
		if(InformacoesUsuarioHelper.getUsuarioLogado().isProfessor)
		{
			return ok(views.html.Artigos.index.render(artigos, professores));
		}
		return ok(views.html.Administrador.index.render());
	}
	
	public static Result visualizar(Long id) {
		
		Artigo artigo = Artigo.find.byId(id);
		
		if (InformacoesUsuarioHelper.getUsuarioLogado().isProfessor) 
		{
			
			if(artigo.campus.id != InformacoesUsuarioHelper.getUsuarioLogado().campus.id){
				return ok(views.html.Artigos.visualizar2.render(artigo));
			}else{
				flash().put("error", "Projeto de mesmo Câmpus. Tente novamente!");
				redirect(routes.Artigos.index());
			}
			
			return ok(views.html.Artigos.visualizar.render(artigo));
		}
		
		else if( InformacoesUsuarioHelper.getUsuarioLogado().isAdministrador ){
		
			return ok(views.html.Artigos.visualizar2.render(artigo));
			
		} else {
			return ok(views.html.Artigos.visualizar.render(artigo));
		}
		
	}
	
	public static Result formulario() {	
		
		List<GrupoPesquisa> gruposPesquisa  = GrupoPesquisa.find.findList();
		List<AreaConhecimento> areaConhecimento  = AreaConhecimento.find.findList();
		List<AreaEspecifica> areaEspecifica  = AreaEspecifica.find.findList();
		//form(Projeto.class), editais, campus)
		return ok(views.html.Artigos.formulario.render(form(Artigo.class), gruposPesquisa, areaConhecimento, areaEspecifica));
	}
	
	public static Result submeter() {
		
		
		
		Form<Artigo> form = form(Artigo.class).bindFromRequest();
		Long idGrupoPesquisa = Long.valueOf(form.data().get("idGrupoPesquisa"));
		Long idAreaConhecimento = Long.valueOf(form.data().get("idAreaConhecimento"));
		Long idAreaEspecifica = Long.valueOf(form.data().get("idAreaEspecifica"));
		//Long idCampus = Long.parseLong(form.data().get(InformacoesUsuarioHelper.getUsuarioLogado().campus));
		
		if(form.hasErrors()) {
			List<GrupoPesquisa> gruposPesquisa  = GrupoPesquisa.find.findList();
			List<AreaConhecimento> areaConhecimento  = AreaConhecimento.find.findList();
			List<AreaEspecifica> areaEspecifica  = AreaEspecifica.find.findList();
			
			flash().put("error", "Você deve preencher todos os campos corretamente. Tente novamente!");
			return badRequest(views.html.Artigos.formulario.render(form, gruposPesquisa, areaConhecimento, areaEspecifica));
		}
		
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart article = body.getFile("arquivo");		
				
		
		//return redirect(routes.Artigos.index());
		
		Artigo artigo = form.get();
		artigo.autorUm = InformacoesUsuarioHelper.getUsuarioLogado();
		artigo.grupoPesquisa = GrupoPesquisa.find.byId(idGrupoPesquisa);
		artigo.campus = InformacoesUsuarioHelper.getUsuarioLogado().campus;
		artigo.areaConhecimento = AreaConhecimento.find.byId(idAreaConhecimento);
		artigo.areaEspecifica = AreaEspecifica.find.byId(idAreaEspecifica);
		artigo.usuarioAvaliar = 1L;
		
		Blob b = null;
		//Suporta um pouco mais de 60Kb
		if (article != null) {
			File file = article.getFile();

			try {
				byte barr[]= new byte[(int)file.length()];
				//barr=b.getBytes(1,(int)b.length()); 
				
				 barr= IOUtils.toByteArray(new FileInputStream(file));
				 
				 artigo.arquivo = barr;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		artigo.save();
		
		flash().put("success", "Artigo \""+ artigo.titulo +"\" submetido com sucesso à Pró-Reitoria de Pesquisa!");
		return redirect(routes.Artigos.meusArtigos());
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
	
	public static Result formularioAvaliacao(Long id) {
		/*Form<ProjetoForm> form = form(ProjetoForm.class);*/
		Artigo artigo = Artigo.find.byId(id);
		
		/*ProjetoAvaliado projetoAvaliado = ProjetoAvaliado.find.byId(id);*/
		
	//  if (InformacoesUsuarioHelper.isProjetoAvaliado(id)) {
	//	   flash().put("error", "Você Já avaliou este projeto!");
	//	   return badRequest(views.html.Projetos.visualizar2.render(projeto));
	//	} else if(InformacoesUsuarioHelper.isCampusIgual(id)==false){
	//		flash().put("error", "Você não tem permissão para avaliar este projeto!");
	//		return ok(views.html.Artigos.visualizar.render(projeto));
	//	}
		
		return ok(views.html.Artigos.formularioAvaliacao.render(form(ArtigoAvaliado.class), artigo));
	}
	
	public static Result avaliacaoArtigo(Long id) {
		return TODO;
	}
	
	public static Result meusArtigosAvaliados() {
		return TODO;
	}
	
	public static Result meusArtigos(){
        
		Long autorId = InformacoesUsuarioHelper.getUsuarioLogado().id;
		
		List<Artigo> projetos = Artigo.find.where().eq("autor_um_id", autorId).findList();//InformacoesUsuarioHelper.getUsuarioLogado().projetos;
		
		
		return ok(views.html.Artigos.visualizar4.render(projetos));
	}
	
	public static Result selecionarAvaliador(Long id) throws Exception {
		
		try {
			Form<Artigo> form = form(Artigo.class).bindFromRequest();
			
			Long idProfessor = Long.valueOf(form.data().get("idProfessores"));
			Artigo artigo = Artigo.find.byId(id);
			
			artigo.setUsuarioAvaliar(idProfessor);
			
			artigo.update();
			
			flash().put("success", "Artigo \""+ artigo.titulo +"\" Selecionado para Avaliação com sucesso!");
			
			return redirect(routes.Artigos.index());
			
						
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return internalServerError("Comportamento Inesperado..");
		}
		
	}
	
	public static Result getDocumento(Long id) {
		Artigo artigo = Artigo.find.byId(id);
		if (artigo != null) {
			response().setContentType("application/pdf");
			response().setHeader("Content-disposition", "attachment; filename="+artigo.id+"-Documento.pdf");
			response().setHeader("Content-Length", ""+artigo.arquivo.length);
			return ok(artigo.arquivo);
		}
		return badRequest("Artigo invalido!");
	}
}
