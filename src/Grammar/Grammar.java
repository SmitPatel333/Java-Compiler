package Grammar;

import java.util.Hashmap;
import java.util.ArrayList;
import extra_files.Pair;

public class Grammar {
  public class production_Rule {
	  private String left;
	  private String right;
	  
	  public production_Rule(String left, String right) {
		  this.left = left;
		  this.right = right;
	  }
  }
  protected HashMap<Pair, String> action_table = new Hashmap<Pair, String>();
  protected ArrayList<production_Rule> productions = new ArrayList<production_List>();
}
