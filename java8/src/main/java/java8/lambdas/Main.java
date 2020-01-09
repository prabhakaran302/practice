package java8.lambdas;

public class Main {
	public static void main(String[] args) {
		FunctionalInterfaceEx lambda = msg -> {
		    System.out.println("Executing...");
			return msg;
		};
	}
}
