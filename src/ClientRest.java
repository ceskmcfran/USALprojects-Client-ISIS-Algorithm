import java.net.URI;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

//TODO hacer que conecte con uribuilder y webtarget
public class ClientRest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String ipServer[] = new String[3];
		int isISIS = 0;
		final int numParams = 5;

		Scanner scan = new Scanner(System.in);
		for(int i = 0 ; i < 3; i++){
			System.out.print("Enter ip"+i+": ");
			ipServer[i] = scan.next();
		}
		System.out.print("Do you wanna execute with ISIS algorithm?: ");
		isISIS = scan.nextInt();

		boolean checked = checkParams(ipServer, isISIS);

		System.out.print(checked);

		for(int i = 0 ; i < 3; i++){
			String requested = i+";"+ipServer[0]+";"+ipServer[1]+";"+ipServer[2]+";"+isISIS;

			Client client = ClientBuilder.newClient();
			URI uri=UriBuilder.fromUri("http://" + ipServer[i] + ":8080/PracticaIsis/").build();
			WebTarget target = client.target(uri);

			target.path("rest").path("server/create")
			.queryParam("params", requested)
			.queryParam("numParams", numParams)
			.request(MediaType.TEXT_PLAIN).get(String.class);
		}
	}

	private static boolean checkParams(String[] ipServer, int isISIS) {
		for(int i = 0 ; i < 3; i++){
			if(ipServer[i].length() != 11){
				System.out.print("Error: Wrong IP length");
				return false;
			}
		}
		if(isISIS < 0 || isISIS > 1){
			System.out.print("Error: Wrong ISIS param.\nEnter '0' to execute without ISIS or '1' with ISIS");
			return false;
		}
		return true;
	}

}
