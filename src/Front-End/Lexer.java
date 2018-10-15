package front_end;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Lexer {
	private static enum Types{
		Identifier, Keyword, Separator, Operator, Literal 
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
			case Separator:
				st = "Separator<" + s + ">";
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
	private static List<String> remove_comments(HashMap<Integer, String> hm) {
		for(int i = 0; i < hm.size(); i++){

			String str = hm.get(i+1);
			for(int j = 0; j < str.length(); j++) {
				char a = str.charAt(j);
				if(a == '/') {
					char b = str.charAt(j-1);
					if(Character.isWhitespace(b)) {
						switch(str.charAt(j+1)) {
						case '/':
							hm.remove(i);
							break;
						case '*':
							for(int k = 0; k < hm.size() - i; k++) {
								if(hm.get(i+k).contains("*/")) {
									for(int l = 0; l <= k; l++) {
										hm.remove(i+l);
									}
									break;
								}
							}
							break;
						}
					}
				}
			}
		}
		List<String> temp = new ArrayList<String>();
		for(int i = 0; i < hm.size(); i++) {
			String s = hm.get(i+1);
			if(s == null) {
				continue;
			}
			else {
				temp.add(s);
			}
		}
		return temp;
	}
	private static List<String> Keywords = Arrays.asList("abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "syncronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while", "true", "null", "false");
	private static List<String> Separators = Arrays.asList("(", ")", "{", "}", "[", "]", ";", ",", ".", ":");
	private static List<String> Operators = Arrays.asList("+", "+=", "-", "-=", "*", "*=", "/", "/=", "%", "%=", ">", ">=", "<", "<=", "!", "!=", "++", "--", "&&", "||", "==", "=", "?:");
	private static List<String> Identifiers = Arrays.asList("String", "Scanner", "Stream", "ArrayList", "List", "HashMap");
	private static List<String> getLexemes(List<String> strg) {
		List<String> str = new ArrayList<String>();
		for(String s: strg) {
			for(int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if(Character.isWhitespace(c)) {
					continue;
				}
				else {
					if(Separators.contains(Character.toString(c))) {
						str.add(Character.toString(c));
						continue;
					}
					else if(Operators.contains(Character.toString(c))) {
						char d = s.charAt(i+1);
						String a = new StringBuilder().append(c).append(d).toString();
						if(Operators.contains(a)) {
							str.add(a);
							continue;
						}
						else {
							str.add(Character.toString(c));
							continue;
						}
					}
					else {
						for(int j = 1; j < s.length() - i; j++) {
							char a = s.charAt(i+j);
							int b = i+j;
							if(Character.isWhitespace(a)) {
								str.add(s.substring(i, b));
								i += j;
								break;
							}
							else if(Operators.contains(Character.toString(a))){
								str.add(s.substring(i, b));
								str.add(Character.toString(a));
								i += j;
								break;
							}
							else if(Separators.contains(Character.toString(a))) {
								str.add(s.substring(i, b));
								str.add(Character.toString(a));
								i += j;
								break;
							}
							
						}
					}
				}
			}
		}
		return str;
    }
	private static List<Types> getTypes(List<String> lst) {
		List<Types> typ = new ArrayList<Types>();
		for(int i = 0; i < lst.size(); i++) {
			String s = lst.get(i);
			if(Keywords.contains(s)) {
				typ.add(Types.Keyword);
			}
			else if(Separators.contains(s)) {
				typ.add(Types.Separator);
			}
			else if(Operators.contains(s)) {
				typ.add(Types.Operator);
			}
			else if(Keywords.contains(lst.get(i-1))) {
				typ.add(Types.Identifier);
			}
			else if(Separators.contains(lst.get(i-1))) {
				typ.add(Types.Identifier);
			}
			else if(Identifiers.contains(s)) {
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
        HashMap<Integer, String> lines = new HashMap<Integer, String>();
        for(int i = 0; i < Get_Lines.getLineCount(f); i++) {
            String line = br.readLine();
            lines.put(i+1, line);
        }
        List<String> nc_Lines = remove_comments(lines);
        List<String> lexemes = getLexemes(nc_Lines);
		List<Types> ty = getTypes(lexemes);
		for(int i = 0; i < lexemes.size(); i++) {
			Token t = new Token(ty.get(i), lexemes.get(i));
			result.add(t);
		}
        return result;
    }
}
