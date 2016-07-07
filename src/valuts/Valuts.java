/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valuts;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author KonEvgeni
 */
public class Valuts {
//    private static Valuts INSTANCE = new Valuts();
    private final static Set<valuta> vals;
    
    static{
        vals = new HashSet<>();
        valuta v = new Dollar();
        vals.add(v);
        v = new Euro();
        vals.add(v);
    }
    
    private static valuta valutaName(String name) throws ValExcept{
        Matcher m;
        for(valuta v:vals){
            if(v.getName().equals(name)){
                return v;
            }
        }
        throw new ValExcept("Valuta " + name + " not found");
    }
    
    private static valuta valutaValue(String value) throws ValExcept{
        Matcher m;
        for(valuta v:vals){
            m = Pattern.compile(v.valRegExp()).matcher(value);
            if(m.matches()){
                return v;
            }
        }
        throw new ValExcept("Valuta " + value + " not found");
    }
    
    public static String sumVal(String val1, String val2) throws ValExcept{
        valuta v = valutaValue(val1);
        if(!(v == valutaValue(val2))){
            throw new ValExcept("Valutes " + val1 + " and " + val2 + " not coincide");
        }
        return v.getString(v.getValue(val1) + v.getValue(val2));
    }
    
    public static String difVal(String val1, String val2) throws ValExcept{
        valuta v = valutaValue(val1);
        if(!(v == valutaValue(val2))){
            throw new ValExcept("Valutes " + val1 + " and " + val2 + " not match");
        }
        return v.getString(v.getValue(val1) - v.getValue(val2));
    }
    
//    public static String divVal(String val1, String val2) throws ValExcept{
//        valuta v = valutaValue(val1);
//        if(!(v == valutaValue(val2))){
//            throw new ValExcept("Valutes " + val1 + " and " + val2 + " not coincide");
//        }
//        return v.getString(v.getValue(val1)/v.getValue(val2));
//    }
//    
//    public static String multVal(String val1, String val2) throws ValExcept{
//        valuta v = valutaValue(val1);
//        if(!(v == valutaValue(val2))){
//            throw new ValExcept("Valutes " + val1 + " and " + val2 + " not coincide");
//        }
//        return v.getString(v.getValue(val1)*v.getValue(val2));
//    }
    
//    private static String vToV(double value, String valName) throws ValExcept{
//        return valutaName(valName).getString(value);
//    }
    
    public static String toValuta(String value, String name) throws ValExcept{
        valuta valTo = valutaName(name);
        valuta valFrom = valutaValue(value);
        File f = new File("src/valuts/valuts.xml");
        try (FileReader reader = new FileReader(f)) {
            char[] buffer = new char[(int) f.length()];
            // считаем файл полностью
            reader.read(buffer);
            int countStr = 0;
            for (int i=0; i<buffer.length; i++){
                if(buffer[i] == '='){
                    char[] nameVal = new char[i-countStr];
                    System.arraycopy(buffer, countStr, nameVal, 0, i-countStr);
                    valuta val = valutaValue(new String(nameVal));
                    if(valFrom == val){
                        int end = buffer.length-1;
                        for (int j=i+1; j<buffer.length; j++){
                            if(buffer[j] == '\n'){
                                end = j-1;
                                break;
                            }
                        }
                        nameVal = new char[end-i];
                        System.arraycopy(buffer, i+1, nameVal, 0, end-i);
                        val = valutaValue(new String(nameVal));
                        if(valTo == val){
                            double coef = valTo.getValue(new String(nameVal));
                            return valTo.getString(coef*valFrom.getValue(value));
                        }
                    }
                }
                if(buffer[i] == '\n'){
                    countStr = i+1;
                }
            }
            throw new ValExcept("From " + valFrom.getName() + " to " + name + " rate not found");
        } catch (IOException ex) {
            throw new ValExcept("Error read file config");
        }
    }
}