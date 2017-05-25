package corp.skaj.foretagskvitton.services.textcollector;

import java.util.List;

class VatCollector extends AbstractCollector {

    public VatCollector() {
        //package private
    }

    public String getVat(List<String> strings){
        for(int i = 0; i < strings.size(); i++){
            String currentString = strings.get(i);
            if (currentString.equals("25.00%")){
                return currentString;
            }
            if (currentString.equals("12.00%")){
                return currentString;
            }
            if (currentString.equals("6.00%")){
                return currentString;
            }
        }
        return "0,00";
    }
}
