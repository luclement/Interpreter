package interpreter;

public class CaseletList {
	private Caselet caselet;
	private CaseletList caseletlist;
	
	CaseletList(){
		caselet = null;
		caseletlist = null;
	}
	
	public void parse() {
		caselet = new Caselet();
		caselet.parse();
		if(MyScanner.currentToken().equals("BAR")){
			MyScanner.nextToken(); //<caselet-list>
			caseletlist = new CaseletList();
			caseletlist.parse();
		}
	}

	public void print() {
		caselet.print();
		if (caseletlist != null){
			System.out.print("\n  | ");
			caseletlist.print();
		}
	}

}
