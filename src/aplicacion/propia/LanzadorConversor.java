
package aplicacion.propia;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LanzadorConversor {

	public static void main(String[] args) {

		if (args.length == 3) {
			convertir(args[0], args[1], args[2]);
		} else {
			convertir("100", "eur", "gbp");
			convertir("100", "eur", "usd");
			convertir("200", "gbp", "usd");
			convertir("1", "eur", "gbp");
			convertir("1", "eur", "usd");
			convertir("eur", "10", "gbp"); // provoca un error a propósito
			convertir("1", "lop", "err"); // provoca un error a propósito
		}

	}

	private static void convertir(String valor, String divisa1, String divisa2) {
		// Ruta del ejecutable "java" de la JVM que está ejecutando este programa
		// (así funciona igual en Windows y en Linux, sin depender del PATH).
		String java = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
		System.out.println(java);

		// Classpath actual: contiene las clases compiladas de este proyecto,
		// incluida la clase Calculadora.
		String classpath = System.getProperty("java.class.path");
		System.out.println(classpath);

		ArrayList<String> comando = new ArrayList<String>();
		comando.add(java);
		// -cp es la abreviatura de classpath (ruta de clases).
		// Le dice a la JVM en qué carpetas o archivos .jar
		// debe buscar las clases compiladas (.class)
		// que necesita para ejecutar el programa.
		comando.add("-cp");
		comando.add(classpath);
		comando.add("aplicacion.externa.ConversorDivisas");
		comando.add(valor);
		comando.add(divisa1);
		comando.add(divisa2);
		

		ProcessBuilder pb = new ProcessBuilder(comando);
		try {
			Process procesoConversor = pb.start();

			String resultado = leer(procesoConversor.getInputStream());

			String error = leer(procesoConversor.getErrorStream());

			int codigoSalida = procesoConversor.waitFor();

			if (codigoSalida == 0) {
				System.out.println(resultado);
			} else {
				System.err.println(codigoSalida + " " + error);
				}

			} catch (IOException | InterruptedException e) {
				System.err.println(e.getMessage());
			}

		}

	private static String leer(java.io.InputStream flujo) throws IOException {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader lector = new BufferedReader(new InputStreamReader(flujo))) {
			String linea;
			while ((linea = lector.readLine()) != null) {
				if (sb.length() > 0) {
					sb.append(System.lineSeparator());
				}
				sb.append(linea);
			}
		}
		return sb.toString();
	}

}


