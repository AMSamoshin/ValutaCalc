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
public interface valuta {
    public String getString(double value);
    public String valRegExp();
    public String getName();
    public double getValue(String numb);
}
