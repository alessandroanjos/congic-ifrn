package controllers;

import models.*;
import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;

public class Administracao extends Controller {

	/**
     * Area de incio do Administrador
     */
	@helpers.Seguranca.Permissao("Administrador")
	public static Result index() {
		return ok(views.html.Administrador.index.render());
	}

	public static Result save() {
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart picture = body.getFile("arquivo");
		Form<Artigo> productForm = form(models.Artigo.class).bindFromRequest();
		models.Artigo newProduct = productForm.get();
		if (picture != null) {
			File file = picture.getFile();
			System.out.println("Passei aq");
			try {
				newProduct.arquivo = IOUtils.toByteArray(new FileInputStream(file));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		newProduct.save();//saveOrUpdate();
		return redirect(routes.Artigos.meusArtigos());
	}
}
