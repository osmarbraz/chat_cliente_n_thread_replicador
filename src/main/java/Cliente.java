
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente implements Runnable, ConfiguracoesServidor {

    private Socket con;
    private BufferedReader input;
    private PrintWriter out;
    private String nome = "";

    public Cliente() {
        try {
            con = new Socket(ConfiguracoesServidor.ENDERECO, ConfiguracoesServidor.PORTA);
            input = new BufferedReader(new InputStreamReader(con.getInputStream()));
            out = new PrintWriter(con.getOutputStream(), true);
            System.out.println("Conectado ao servidor: " + ConfiguracoesServidor.ENDERECO + " Porta:" + ConfiguracoesServidor.PORTA);
            System.out.println("-----------------------------------------");
        } catch (IOException ioe) {
            System.out.println("Sem Conexao!");
            Scanner input = new Scanner(System.in);
            System.out.println("Pressione qualquer tecla para terminar. . .");
            String mensagem = input.nextLine();
            System.exit(0);
        }
    }

    public void executa() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Indentifique-se, digite seu nome:");
        nome = scanner.nextLine();
        System.out.println("-----------------------------------------");
        System.out.println("Conectando cliente. . .");
        out.println(nome);
        Thread t1 = new Thread(this);
        t1.start();
        System.out.println("Usuario:" + nome + " conectado!");
        System.out.println("Aguardando comandos:");
        System.out.println("Digite 'fim' para encerrar!");
        System.out.println("-----------------------------------------");
        String mensagem = scanner.nextLine();
        while (!mensagem.equals("fim")) {
            out.println(mensagem);
            mensagem = scanner.nextLine();
        }
        out.println(mensagem);
        Scanner input = new Scanner(System.in);
        System.out.println("Pressione qualquer tecla para terminar. . .");
        mensagem = input.nextLine();
        System.exit(0);
    }

    public void run() {
        try {
            while (true) {
                System.out.println(input.readLine());
            }
        } catch (IOException ioe) {
            System.out.println("Sem Conexao!");
            Scanner input = new Scanner(System.in);
            System.out.println("Pressione qualquer tecla para terminar. . .");
            String mensagem = input.nextLine();
            System.exit(0);
        }
    }
}
