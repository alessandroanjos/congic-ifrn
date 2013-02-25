package controllers;

import helpers.Seguranca.InformacoesUsuarioHelper;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import models.AreaConhecimento;
import models.AreaEspecifica;
import models.Artigo;
import models.Campus;

import models.GrupoPesquisa;
import models.Artigo;

import org.apache.commons.io.IOUtils;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;

public class Artigos extends Controller{

	public static Result index(){
		return TODO;
	}
	
	public static Result visualizar(Long id) {
		return TODO;
	}
	
	public static Result formulario() {	
		
		List<GrupoPesquisa> gruposPesquisa  = GrupoPesquisa.find.findList();
		List<AreaConhecimento> areaConhecimento  = AreaConhecimento.find.findList();
		List<AreaEspecifica> areaEspecifica  = AreaEspecifica.find.findList();
		//form(Projeto.class), editais, campus)
		return ok(views.html.Artigos.formulario.render(form(Artigo.class), gruposPesquisa, areaConhecimento, areaEspecifica));
	}
	
	public static Result submeter() {
		
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart article = body.getFile("arquivo");		
		
		Form<Artigo> form = form(Artigo.class).bindFromRequest();
		Long idGrupoPesquisa = Long.valueOf(form.data().get("idGrupoPesquisa"));
		Long idAreaConhecimento = Long.valueOf(form.data().get("idAreaConhecimento"));
		Long idAreaEspecifica = Long.valueOf(form.data().get("idAreaEspecifica"));
	//	Long idCampus = Long.parseLong(form.data().get(InformacoesUsuarioHelper.getUsuarioLogado().campus));
		
		if(form.hasErrors()) {
			List<GrupoPesquisa> gruposPesquisa  = GrupoPesquisa.find.findList();
			List<AreaConhecimento> areaConhecimento  = AreaConhecimento.find.findList();
			List<AreaEspecifica> areaEspecifica  = AreaEspecifica.find.findList();
			
			flash().put("error", "Você deve preencher todos os campos corretamente. Tente novamente!");
			return badRequest(views.html.Artigos.formulario.render(form, gruposPesquisa, areaConhecimento, areaEspecifica));
		}
		
	
				
		
		//return redirect(routes.Artigos.index());
		
		Artigo artigo = form.get();
		artigo.autorUm = InformacoesUsuarioHelper.getUsuarioLogado();
		artigo.grupoPesquisa = GrupoPesquisa.find.byId(idGrupoPesquisa);
		artigo.campus = InformacoesUsuarioHelper.getUsuarioLogado().campus;
		artigo.areaConhecimento = AreaConhecimento.find.byId(idAreaConhecimento);
		artigo.areaEspecifica = AreaEspecifica.find.byId(idAreaEspecifica);
		artigo.usuarioAvaliar = 1;
		
		
		if (article != null) {
			File file = article.getFile();
			try {
				artigo.arquivo = IOUtils.toByteArray(new FileInputStream(file));
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
		return TODO;
	}
	
	public static Result avaliacaoArtigo(Long id) {
		return TODO;
	}
	
	public static Result meusArtigosAvaliados() {
		return TODO;
	}
	
	public static Result meusArtigos(){
		return TODO;
	}
}
