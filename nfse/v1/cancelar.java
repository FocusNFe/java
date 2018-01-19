import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Main {

	public static void main(String[] args){
		
		/* Para ambiente de produção use a variável abaixo:
		String server = "https://api.focusnfe.com.br/"; */
 		String server = "http://homologacao.acrasnfe.acras.com.br/";

		String token =  "token_enviado_pelo_suporte";

		/* Substituir pela sua identificação interna da nota. */
		String ref = "12345";

		String justificativa = "Teste_de_cancelamento_de_nota";

		Client client = Client.create();

		String url = server.concat("nfse/" + ref + "?token="+ token +"&justificativa="+justificativa);

		WebResource request = client.resource(url);

		ClientResponse resposta = request.delete(ClientResponse.class);

		int HttpCode = resposta.getStatus(); 

		String body = resposta.getEntity(String.class);
		
		/* As três linhas abaixo imprimem as informações retornadas pela API. 
		 * Aqui o seu sistema deverá interpretar e lidar com o retorno. */
		System.out.print("HTTP Code: ");
		System.out.print(HttpCode);
		System.out.printf(body);
	}
}
