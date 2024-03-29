package interpreter;

public class Assign {
	private Expr expr;
	private Decl declList;
	private String var;
	
	Assign(){
		expr = null;
		declList = new Decl();
		var = null;
	}
	
	public void parse() {
		if(!MyScanner.currentToken().substring(0,2).equals("ID")){
			System.out.println("ERROR: Expected ID at start of assign");
			System.exit(0);
		}
		StringBuilder id = new StringBuilder();
		int x = 3;
		char c;
		while((c = MyScanner.currentToken().charAt(x)) != ']'){
			id.append(c);
			x++;
		}
		var = id.toString();
		if(!(declList.getDeclared()).containsKey(var)){
			System.out.println("ERROR: Cannot assign undeclared ID[" + var + "]");
			System.exit(0);
		}
		MyScanner.nextToken(); //ASSIGN
		if(!MyScanner.currentToken().equals("ASSIGN")){
			System.out.println("ERROR: Expected ASSIGN after ID in assign");
			System.exit(0);
		}
		MyScanner.nextToken(); //<expr>
		expr = new Expr();
		int[] array = expr.parse();
		if(array[0] == 1){ //is constant
			declList.getDeclared().put(var, array[1]);
		}
		if(!MyScanner.currentToken().equals("SEMICOL")){
			System.out.println("ERROR: Expected SEMICOL at end of assign");
			System.exit(0);
		}
		MyScanner.nextToken();
	}

	public void print() {
		System.out.print("  " + var + ":=");
		expr.print();
		System.out.println(";");
	}
}
