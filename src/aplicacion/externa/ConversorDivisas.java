package aplicacion.externa;

import java.util.Locale;

public class ConversorDivisas {

	public static void main(String[] args) {

		if (args.length != 3) {
			System.err.println("Uso: ConversorDivisas <cantidad> <divisa_origen> <divisa_destino>");
			System.exit(1);
			return;
		}

		String valor = args[0];
		String divisa1 = args[1];
		String divisa2 = args[2];

		double cantidad;

		try {
			valor = valor.replace(",", ".");
			cantidad = Double.parseDouble(valor); 
			//Sustituimos las ',' por '.' y lo pasamos a Double
			
			double resultado = cantidad;
			divisa1 = divisa1.toLowerCase();
			divisa2 = divisa2.toLowerCase();
			
			/*
			 * 1 EUR = 1,10 USD
			 * 1 USD = 0,90 EUR
			 * 1 EUR = 0,85 GBP
			 * 1 USD = 0,75 GBP
			 * 1 GBP = 1,15 EUR
			 * 1 GBP = 1,25 USD
			 */
			
			if (divisa1.equals("eur")) {
				
				
				if (divisa2.equals("usd")){
					
					resultado *= 1.10;
					
				} else if (divisa2.equals("gbp")) {
					
					resultado *= 0.85;
					
				} else {
					
					System.err.println("Introduce divisas validas");
					System.exit(3);
					return;
					
				}
				
			} else if (divisa1.equals("usd")) {
				
				
				if (divisa2.equals("eur")){
					
					resultado *= 0.9;
					
				} else if (divisa2.equals("gbp")) {
					
					resultado *= 0.75;
					
				} else {
					System.err.println("Introduce divisas validas");
					System.exit(3);
					return;
					
				}
				
			} else if (divisa1.equals("gbp")) {
				
				
				if (divisa2.equals("eur")){
					
					resultado *= 1.15;
					
				} else if (divisa2.equals("usd")) {
					
					resultado *= 1.25;
					
				} else {
					
					System.err.println("Introduce divisas validas");
					System.exit(2);
					return;
					
				}
				
			} else {
				
				System.err.println("Introduce divisas validas");
				System.exit(2);
				return;
				
			}
			
			String resultadoTxt = String.format(Locale.US, "%.2f", resultado); 
			// Recortamos a dos decimales con formato US

			System.out.println("El resultado es: ");
			System.out.println(cantidad + " " + divisa1.toUpperCase() + ": " + resultadoTxt + " " + divisa2.toUpperCase());
			System.out.println();
			
		} catch (NumberFormatException e) {

			System.err.println("La cantidad de dinero debe ser un numero");
			System.exit(1);
			return;
		}
	}	

}
