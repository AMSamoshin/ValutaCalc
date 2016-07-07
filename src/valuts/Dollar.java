/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valuts;

/**
 *
 * @author KonEvgeni
 */
class Dollar implements valuta{
    
    @Override
    public String getString(double value) {
        return "$" + value;
    }
    
    @Override
    public String valRegExp(){
        return "^\\$(\\d+(?:[\\.]\\d+)?)$";
    }

    @Override
    public String getName() {
        return "Dollar";
    }

    @Override
    public double getValue(String numb) {
        Double d = new Double(numb.substring(1));
        return d;
    }
}
