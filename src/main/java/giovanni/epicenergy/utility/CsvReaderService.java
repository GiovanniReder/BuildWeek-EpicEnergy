package giovanni.epicenergy.utility;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class CsvReaderService {

    public List<String[]> readCsv(Path path) throws Exception { //Dichiaro un metodo pubblico che ritorna una lista con all'interno un array di Stringhe, accetta come parametro un Path
        try (Reader reader = Files.newBufferedReader(path); //Uso il metodo newBufferReader per creare un oggetto Reader che poi leggerà il file situato nel path passato sopra
             CSVReader csvReader = new CSVReaderBuilder(reader) //Qui creo un ogetto CsvReader  utilizzando un Builder passandogli come paramentro l'oggetto reader creato sopra
                     .withCSVParser(new CSVParserBuilder() //Qui configuro il CsvReader per usare un parser personalizzato che usa il (;) come separatore (così non uso quello default)
                             .withSeparator(';')
                             .build())
                     .withSkipLines(1) //Questa riga permette di saltare la prima riga del file (Quella di intestazione)
                     .build()) { //Completiamo il CsvReaderBuilder con il metodo .build
            return csvReader.readAll(); //Qui leggiamo tutti i dati che abbiamo nel file CSV che abbiamo passato nel path e restituisce i dati sotto forma di
            //una lista  di array di stringhe
        }
    }
}
