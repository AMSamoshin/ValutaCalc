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
class Euro implements valuta{

    @Override
    public String getString(double value) {
        return value + "eur";
    }

    @Override
    public String valRegExp() {
        return "^(-)?(\\d+(?:[\\.,]\\d(\\d)?)?)eur$";
    }

    @Override
    public String getName() {
        return "Euro";
    }

    @Override
    public double getValue(String numb) {
        Double d = new Double(numb.substring(0, numb.length()-3));
        return d;
    }
    
}
