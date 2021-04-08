import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws IOException {

		try (Socket conexao = new Socket("127.0.0.1", 2001)) {
			System.out.println("Vamos l�.\n");

			DataInputStream entrada = new DataInputStream(conexao.getInputStream());
			DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());

			String linha;
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				System.out.print("> ");
				linha = teclado.readLine();
				saida.writeUTF(linha);
				linha = entrada.readUTF();
				if (linha.trim().equals("") || linha.equalsIgnoreCase("sair")) {
					System.out.println("\nConex�o Finalizada.");
					break;
				}
				System.out.println(linha);
			}
		} catch (Exception e) {
			System.out.println("Erro, tente mais tarde.");
		}
	}
}
