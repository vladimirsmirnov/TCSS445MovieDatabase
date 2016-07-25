import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		System.out.println("Vlad is Great!");
        Actors.generateOutputFile("actors.list", "actorOutput.txt");
        Actors.generateOutputFile("actresses.list", "actressOutput.txt");
	}

}
