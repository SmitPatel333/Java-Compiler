package front_end;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
	private static enum Types{
		Identifier, Keyword, Seperator, Operator, Literal, Comment 
	}
	private static class Token {
		private final Types t;
		private final String s;
		
		private Token(Types t, String s) {
			this.t = t;
			this.s = s;
		}
		public String toString() {
			String st = "";
			switch(t) {
			case Identifier:
				st = "Identifier<" + s + ">";
				break;
			case Keyword:
				st = "Keyword<" + s + ">";
				break;
			case Seperator:
				st = "Seperator<" + s + ">";
				break;
			case Operator:
				st = "Operator<" + s + ">";
				break;
			case Literal:
				st = "Literal<" + s + ">";
				break;
			case Comment:
				st = "Comment<" + s + ">";
				break;
			}
			return st;
		}
	}
	private static List<String> getLexemes(List<Character> chars) {
        /*int j = i;
        for( ; j < s.length(); ) {
            if(Character.isLetter(s.charAt(j))) {
                j++;
            } else {
                return s.substring(i, j);
            }
        }
        return s.substring(i, j);*/
		List<String> temp = new ArrayList<String>();
		return temp;
    }
	private static List<Types> getTypes(List<String> lst) {
		/*if(str.equals("True") || str.equals("False")) {
			return Types.Literal;
		}
		if(((str.charAt(0)) == '/')) {
			return Types.Comment;
		}
		if((str.equals("=") || str.equals("+") || str.equals("-") || str.equals("*"))) {
			return Types.Operator;
		}
		for(int i = 0; i < str.length(); ) {
			if(Character.isDigit(str.charAt(i))) {
				return Types.Literal;
			}
			if(Character.isLetter(str.charAt(i))) {
				
			}
		}
		return Types.Comment;*/
		List<Types> temp = new ArrayList<Types>();
		return temp;
	}
	static List<Token> lex(ArrayList<Character> arl) {
        	List<Token> result = new ArrayList<Token>();
        	List<String> lexemes = getLexemes(arl);
		List<Types> ty = getTypes(lexemes);
		for(int i = 0; i < lexemes.size(); i++) {
			Token t = new Token(ty.get(i), lexemes.get(i));
			result.add(t);
		}
        return result;
    }
}
