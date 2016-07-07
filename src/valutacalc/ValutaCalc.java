/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valutacalc;

import java.util.Scanner;
import valuts.ValExcept;
import valuts.Valuts;

/**
 *
 * @author KonEvgeni
 */
public class ValutaCalc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        String c = in.nextLine();
        try {
            System.out.println(parse(c));
        } catch (ValExcept ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private static String parse(String str) throws ValExcept{
        if(str.charAt(0) == 't')
            if(str.charAt(1) == 'o'){
                int openBkt=0, closeBkt=0, countbkt=0;
                for(int j=2; j<str.length(); j++){
                    if(str.charAt(j) == '('){
                        if (countbkt == 0)
                            openBkt = j;
                        countbkt++;
                    }
                    if(str.charAt(j) == ')'){
                        countbkt--;
                        if (countbkt == 0)
                           closeBkt = j;
                    }
                }
                str = Valuts.toValuta(parse(str.substring(openBkt+1, closeBkt)), str.substring(2, openBkt))+str.substring(closeBkt+1); 
            }
        
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == '+'){
                str = Valuts.sumVal(str.substring(0, i), str.substring(i+1));
                break;
            }
            if(str.charAt(i) == '-'){
                str = Valuts.difVal(str.substring(0, i), str.substring(i+1));
                break;
            }
//            if(str.charAt(i) == '*'){
//                str = Valuts.multVal(str.substring(0, i), str.substring(i+1));
//                break;
//            }
//            if(str.charAt(i) == '/'){
//                str = Valuts.divVal(str.substring(0, i), str.substring(i+1));
//                break;
//            }
        }
        return str;
    }
}
