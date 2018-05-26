package front_end;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lexer {
	private static enum Types{
		Identifier, Keyword, Seperator, Operator, Literal 
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
			}
			return st;
		}
	}
	private static List<String> remove_comments(List<String> ls) {
		List<String> temp = ls;
		for(String str: temp){
			for(int i = 0; i < str.length(); i++) {
				char a = str.charAt(i);
				if(a == '/') {
					char b = str.charAt(i-1);
					if(Character.isWhitespace(b)) {
						if(str.charAt(i+1) == '/') {
							temp.remove(str);
						}
					}
				}
				else {
					break;
				}
			}
		}
		return temp;
	}
	private static List<String> getLexemes(List<String> strg) {
		Blankboard.remove_comments(strg);
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
	private static List<String> Keywords = Arrays.asList("abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "syncronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while", "true", "null", "false");
	private static List<String> Separators = Arrays.asList("(", ")", "{", "}", "[", "]", ";", ",", ".", ":");
	private static List<String> Operators = Arrays.asList("+", "+=", "-", "-=", "*", "*=", "/", "/=", "%", "%=", ">", ">=", "<", "<=", "!", "!=", "++", "--", "&&", "||", "==", "=", "?:");
	private static List<Types> getTypes(List<String> lst) {
		List<Types> typ = new ArrayList<Types>();
		for(int i = 0; i < lst.size(); i++) {
			String s = lst.get(i);
			if(Keywords.contains(s)) {
				typ.add(Types.Keyword);
			}
			if(Separators.contains(s)) {
				typ.add(Types.Separator);
			}
			if(Operators.contains(s)) {
				typ.add(Types.Operator);
			}
			if(Keywords.contains(lst.get(i-1))) {
				typ.add(Types.Identifier);
			}
			else {
				typ.add(Types.Literal);
			}
		}
		return typ;
	}
	static List<Token> lex(File f) throws FileNotFoundException, IOException{
        List<Token> result = new ArrayList<Token>();
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        List<String> lines = new ArrayList<String>();
        for(int i = 0; i < Get_Lines.getLineCount(f); i++) {
            String line = br.readLine();
            lines.add(line);
        }
        List<String> lexemes = getLexemes(lines);
		List<Types> ty = getTypes(lexemes);
		for(int i = 0; i < lexemes.size(); i++) {
			Token t = new Token(ty.get(i), lexemes.get(i));
			result.add(t);
		}
        return result;
    }
}