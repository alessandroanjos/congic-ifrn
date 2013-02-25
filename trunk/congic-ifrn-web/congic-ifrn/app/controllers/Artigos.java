package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

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
		List<Campus> campus  = Campus.find.findList();
		List<AreaConhecimento> areaConhecimento  = AreaConhecimento.find.findList();
		List<AreaEspecifica> areaEspecifica  = AreaEspecifica.find.findList();
		//form(Projeto.class), editais, campus)
		return ok(views.html.Artigos.formulario.render(form(Artigo.class), gruposPesquisa, campus, areaConhecimento, areaEspecifica));
	}
	
	public static Result submeter() {
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart picture = body.getFile("arquivo_artigo");
		Form<Artigo> productForm = form(models.Artigo.class).bindFromRequest();
		models.Artigo newProduct = productForm.get();
		if (picture != null) {
			File file = picture.getFile();
			try {
				newProduct.arquivo = IOUtils.toByteArray(new FileInputStream(file));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		newProduct.save();//saveOrUpdate();
		return redirect(routes.Artigos.index());
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
