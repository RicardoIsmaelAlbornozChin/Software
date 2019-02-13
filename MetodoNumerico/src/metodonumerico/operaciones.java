
package metodonumerico;
import javax.swing.*;

public class operaciones {
    /**
    * @param dateIndex indice de la opcion del ComboBox
    *@return numeros de decimales a quitar.
    */
    public int selection(int dateIndex){
        String decimal = "";
        decimal = JOptionPane.showInputDialog(null, "Número de posiciones", "Quitando Decimales", JOptionPane.QUESTION_MESSAGE);
       return Integer.parseInt(decimal);
    }
    
    /**
     * @param valueTrue valor verdadero
     * @param valueAprox valor aoroximado
    *  @return error relativo.
    */
    public double relativeError(double valueTrue, double valueAprox){
        return ((valueTrue - valueAprox)/valueAprox);
    }
    
    /**
     * @param valueTrue valor verdadero
     * @param valueAprox valor aoroximado
     * @return error relativo porcentual
    */
    public double relativeErrorPercentage(double valueTrue, double valueAprox){
        return ((valueTrue - valueAprox)/valueAprox)*100;
    }
    
    /**
     * @param valueTrue valor verdadero
     * @param valueAprox valor aoroximado
     * @return error relativo
    */
    public double absolutError(double valueTrue, double valueAprox){
        return valueTrue - valueAprox;
    }
    
    /**
     * @param value resultado de opereaciones (errores)
     * @param numberOfDecimal numero de decimales que quitara
    *  @return truncamiento del numero
    *  Quita los decimales.
    */
    public double truncament(double value,int numberOfDecimal){
        String date = String.valueOf(value); //transformamos el double en String.
        int positionOfChar = numberCharacter(date);
        date = date.substring(0,(numberOfDecimal+positionOfChar)); 
        return Double.parseDouble(date);  
    }
  
    /**
     *@param date el resultado convertido en String
     *@return el numero de decimales que estan antes de los decimales del dato.
     */
    public int numberCharacter(String date){
        int PositionOfCharacter = 0;
        char[] dateChar = date.toCharArray();
        
        for(int i = 0; i < dateChar.length; i++){
            if(dateChar[i] == '.'){
                PositionOfCharacter = i;
            }
        }
        return PositionOfCharacter + 1;
    }
    
    /**
     * @param value valor del resultado de errores
     * @param numberOfDecimal numeros de decimales a quitar
    *@return el numero redondeado
    */
    public double rounding(double value,int numberOfDecimal){
        String date = String.format("%."+(numberOfDecimal)+"f", value);
        return Double.parseDouble(date);
    }
   /**
     * @param valueTrue valor verdadero
     * @param valueAprox valor aproximado
     * @param dateIndex indice de opcion del ComboBox
    *  @return un arreglo con los resultados de cada seleccion.
    *  valida que opcion del ComboBox se eligio.
    */
    public double[] operationSelection(double valueTrue, double valueAprox,int dateIndex){
        double[] resultsOperations = new double[3];
        
        double valueRelativePercentage = relativeErrorPercentage(valueTrue, valueAprox);
        double valueRelativeError = relativeError(valueTrue, valueAprox);
        double valueAbsolutError = absolutError(valueTrue, valueAprox);
        
        resultsOperations[0] = valueAbsolutError;
        resultsOperations[1]= valueRelativeError;
        resultsOperations[2] = valueRelativePercentage;
        
        int numberOfDecimal = 0;
        
        if(dateIndex == 0){
            return resultsOperations;
        }
        
        if(dateIndex == 1){
            try{ //evitar el error que arroja selection al estar vacio el JOptionPane
                numberOfDecimal = selection(dateIndex);
                resultsOperations[0] = truncament(valueAbsolutError,numberOfDecimal);
                resultsOperations[1] = truncament(valueRelativeError,numberOfDecimal);
                resultsOperations[2] = truncament(valueRelativePercentage, numberOfDecimal);
            }catch(Exception e){}
        }
        if(dateIndex == 2){
            try{
                numberOfDecimal = selection(dateIndex);
                resultsOperations[0] = rounding(valueAbsolutError,numberOfDecimal);
                resultsOperations[1] = rounding(valueRelativeError,numberOfDecimal);
                resultsOperations[2] = rounding(valueRelativePercentage,numberOfDecimal);
            }catch(Exception e){}
        }
        return resultsOperations;
    }
}
