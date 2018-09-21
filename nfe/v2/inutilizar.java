import java.util.HashMap;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class NFeInutilizacao {

	public static void main(String[] args) throws JSONException{
		
		String login = "Token_enviado_pelo_suporte";
		
		/* Para ambiente de produção use a variável abaixo:
		String server = "https://api.focusnfe.com.br/"; */
 		String server = "http://homologacao.acrasnfe.acras.com.br/";
 		
 		String url = server.concat("v2/nfe/inutilizacao");
 		
 		/* Aqui criamos um hash que irá receber as chaves e valores esperados para gerar a inutilização. */
		HashMap<String, String> dadosInutilizacao = new HashMap<String, String>();
		dadosInutilizacao.put("cnpj", "51916585009999");
		dadosInutilizacao.put("serie", "9");
		dadosInutilizacao.put("numero_inicial", "7730");
		dadosInutilizacao.put("numero_final", "7732");
		dadosInutilizacao.put("justificativa", "Informe aqui a justificativa para realizar a inutilizacao da numeracao.");
		
		/* Criamos um objeto JSON que irá receber o input dos dados, para então enviar a requisição. */
		JSONObject json = new JSONObject (dadosInutilizacao);
		
		/* Testar se o JSON gerado está dentro do formato esperado.
		System.out.print(json); */
		
		/* Configuração para realizar o HTTP BasicAuth. */
		Object config = new DefaultClientConfig();
		Client client = Client.create((ClientConfig) config);
		client.addFilter(new HTTPBasicAuthFilter(login, ""));

		WebResource request = client.resource(url);

		ClientResponse resposta = request.post(ClientResponse.class, json);

		int hHttpCode = resposta.getStatus(); 

		String body = resposta.getEntity(String.class);
		
		 /* As três linhas abaixo imprimem as informações retornadas pela API. 
		  * Aqui o seu sistema deverá interpretar e lidar com o retorno. */
		System.out.print("HTTP Code: ");
		System.out.print(hHttpCode);
		System.out.printf(body); 
	}
}