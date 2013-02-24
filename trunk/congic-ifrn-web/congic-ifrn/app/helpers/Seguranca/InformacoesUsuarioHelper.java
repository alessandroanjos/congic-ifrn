package helpers.Seguranca;

import models.Usuario;
import play.mvc.Controller;

public class InformacoesUsuarioHelper  extends Controller {
	/**
	 * Método para retorno do usuário logado.
	 */
	
	public static Usuario getUsuarioLogado() {
		Long idUsuario = Long.parseLong(session("usuarioLogadoID"));
		
		return Usuario.find.byId(idUsuario);
	}
	/**
	 * Método para verificar se o usuário da sessão está logado.
	 * 
	 */
	public static Boolean isLogado() {
		return session().get("usuarioLogadoID") == null ? false : true;
	}

}
