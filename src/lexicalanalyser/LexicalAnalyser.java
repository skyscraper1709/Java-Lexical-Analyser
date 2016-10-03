/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyser;

/**
 *
 * @author Safat
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyser {

    String fullCode="";
    String keywords [] ={"if","else","int","float"};
    String mOps []={"+","-","*","/","="};
    String lOps []={">","<"};
    
    
    List<String> javaWords = new ArrayList<String>();
    List<String> identifiers = new ArrayList<String>();
    List<String> mathOperators = new ArrayList<String>();
    List<String> logicalOerators = new ArrayList<String>();
    List<String> numericvalues = new ArrayList<String>();
    List<String> others = new ArrayList<String>();
	public static void main(String[] args) {
            LexicalAnalyser la=new LexicalAnalyser();
		BufferedReader br = null;

		try {

			String sCurrentLine;
                        
			br = new BufferedReader(new FileReader("E:\\\\NetBeans Project workspace\\\\LexicalAnalyser\\\\src\\\\lexicalanalyser\\input.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
			 la.fullCode=la.fullCode+sCurrentLine;
//                            System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
                
                String currentBuffer="";
                for (int i = 0; i < la.fullCode.length(); i++) {
                    
                    if(la.fullCode.charAt(i)=='<' || la.fullCode.charAt(i)=='>' ||
                            la.fullCode.charAt(i)==',' || la.fullCode.charAt(i)==';' || la.fullCode.charAt(i)==' '
                            || la.fullCode.charAt(i)=='(' || la.fullCode.charAt(i)==')' || la.fullCode.charAt(i)=='{'
                            || la.fullCode.charAt(i)=='}' || la.fullCode.charAt(i)=='=' || la.fullCode.charAt(i)=='\t'
                            || la.fullCode.charAt(i)=='-'|| la.fullCode.charAt(i)=='+' || la.fullCode.charAt(i)=='/'
                            || la.fullCode.charAt(i)=='*' || la.fullCode.charAt(i)=='%'){
                        if(currentBuffer.length()!=0){
                        la.checkBuffer(currentBuffer);
                        --i;
                        }else if(la.fullCode.charAt(i)!=' '){
                        la.checkBuffer(""+la.fullCode.charAt(i));    
                        }
//                        System.out.println("inBuffer: "+currentBuffer);
                        currentBuffer="";
                    }else
                    currentBuffer+=la.fullCode.charAt(i);
            }
                la.printResults();
	}
        void checkBuffer(String buffer){
            //TRUNCATE IF NEEDED
            buffer=buffer.trim();
            if(isKeyword(buffer)) {
                if(notThere(javaWords,buffer))
            javaWords.add(buffer);
            }
            else if(isMOperator(buffer)) {
            if(notThere(mathOperators,buffer))
                mathOperators.add(buffer);
            }
            else if(isLOperator(buffer)) {
                if(notThere(logicalOerators,buffer))
            logicalOerators.add(buffer);
            }
            else if(isNumericValue(buffer)) {
            if(notThere(numericvalues,buffer))
            numericvalues.add(buffer);
            }
            else if(isVar(buffer)) {
                if(notThere(identifiers,buffer))
            identifiers.add(buffer);
            }
            else {
                if(notThere(others,buffer))
                others.add(buffer);
                    }
        }

    private boolean isKeyword(String buffer) {
        for (int i = 0; i < keywords.length; i++) {
            if(keywords[i].equals(buffer)) return true;
        }
        return false;
    }
    private boolean isMOperator(String buffer) {
        for (int i = 0; i < mOps.length; i++) {
            if(mOps[i].equals(buffer)) return true;
        }
        return false;
    }

    private boolean isLOperator(String buffer) {
        for (int i = 0; i < lOps.length; i++) {
            if(lOps[i].equals(buffer)) return true;
        }
        return false;
    }

    private boolean isNumericValue(String buffer) {
        try{
        double d=Double.parseDouble(buffer);
        }
        catch(NumberFormatException error){
            return false;
        }
        
        return true;
    }

    private boolean isVar(String buffer) {
//        System.out.println("buffre: "+buffer);
        if(buffer.length()>=1){
        char c0 = buffer.charAt(0);
        if(Character.isDigit(c0)){
            return false;
        }
        }
        for (int i=0; i<buffer.length(); i++) {
            char c = buffer.charAt(i);
            if (!Character.isDigit(c) && !Character.isLetter(c))
                return false;
        }

        return true;
        
    }
 private void printResults(){
     System.out.println("Output:\n");
     
     System.out.println("\nKeywords");
     for (String str : javaWords) {
         System.out.print(str+" ");
     }
     System.out.println("\n\nIdentifiers");
     for (String str : identifiers) {
         System.out.print(str+" ");
     }
     System.out.println("\n\nMath Ops");
     for (String str : mathOperators) {
         System.out.print(str+" ");
     }
     System.out.println("\n\nLogical Ops");
     for (String str : logicalOerators) {
         System.out.print(str+" ");
     }
     System.out.println("\n\nNumeric Values");
     for (String str : numericvalues ) {
         System.out.print(str+" ");
     }
     System.out.println("\n\nOthers");
     for (String str : others) {
         System.out.print(str+" ");
     }
 }

    private boolean notThere(List<String> theList, String buffer) {
        for (String s : theList) {
            if(s.equals(buffer))
                return false;
        }
        return true;
    }
}

