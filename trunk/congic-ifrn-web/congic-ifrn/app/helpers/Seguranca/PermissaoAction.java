package helpers.Seguranca;

import models.Usuario;
import play.mvc.Action;
import play.mvc.Result;
import play.mvc.Http.Context;

public class PermissaoAction extends Action<Permissao>{
	
	public Result call(Context ctx) throws Throwable {
		Usuario usuario;
		
		try {
			usuario = Usuario.find.byId(Long.valueOf(ctx.session().get("usuarioLogadoID")));
		} catch (NumberFormatException e) {
			usuario = null;
		}
		
		if (usuario == null) {
			ctx.flash().put("url", "GET".equals(ctx.request().method()) ? ctx.request().uri() : "/");
			return redirect(controllers.routes.Sessions.login());
		} else if (configuration.value().equalsIgnoreCase("Administrador") && !usuario.isAdministrador ||
				
				configuration.value().equalsIgnoreCase("Servidor") && !usuario.isServidor  ||
				
				configuration.value().equalsIgnoreCase("Bolsista") && !usuario.isBolsista ||
				
				configuration.value().equalsIgnoreCase("Professor") && !usuario.isProfessor
				
				) {
			ctx.flash().put("error", "Você não tem permissão para acessar este conteúdo!");
			return redirect(controllers.routes.Application.index());
		}
		
		return delegate.call(ctx);
	}

}
