package v2.NFSe;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class NFSe_envia_email {

	public static void main(String[] args) throws JSONException{
		
		String login = "Token_enviado_pelo_suporte";

		/* Substituir pela sua identificação interna da nota. */
		String ref = "12345";
		
		/* Para ambiente de produção use a variável abaixo:
		String server = "https://api.focusnfe.com.br/"; */
 		String server = "http://homologacao.acrasnfe.acras.com.br/";

		String url = server.concat("v2/nfse/"+ref+"/email");
		
		/* Criamos o um objeto JSON que receberá um JSON Array com a lista de e-mails. */
		JSONObject json = new JSONObject ();	
		JSONArray ListaEmails = new JSONArray();
		ListaEmails.put("email_01@acras.com.br");
		ListaEmails.put("email_02@acras.com.br");
		ListaEmails.put("email_03@acras.com.br");
		json.put("emails", ListaEmails);	
		
		/* Testar se o JSON gerado está dentro do formato esperado.
		System.out.print(json); */
		
		/* Configuração para realizar o HTTP BasicAuth. */
		Object config = new DefaultClientConfig();
		Client client = Client.create((ClientConfig) config);
		client.addFilter(new HTTPBasicAuthFilter(login, ""));

		WebResource request = client.resource(url);

		ClientResponse resposta = request.post(ClientResponse.class, json);

		int HttpCode = resposta.getStatus(); 

		String body = resposta.getEntity(String.class);
		
		/* As três linhas abaixo imprimem as informações retornadas pela API. 
		 * Aqui o seu sistema deverá interpretar e lidar com o retorno. */
		System.out.print("HTTP Code: ");
		System.out.print(HttpCode);
		System.out.printf(body); 
	}
}