package Grammar;

import java.util.Hashmap;
import extra_files.Pair;

public class Grammar {
  protected HashMap<Pair, String> action_table = new Hashmap<Pair, String>();
  
  public class production_Rule {
	  private String left;
	  private String right;
	  
	  public production_Rule(String left, String right) {
		  this.left = left;
		  this.right = right;
	  }
  }
}
