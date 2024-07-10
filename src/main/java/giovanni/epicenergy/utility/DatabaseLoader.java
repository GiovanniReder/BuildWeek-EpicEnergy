package giovanni.epicenergy.utility;

import giovanni.epicenergy.entities.Comune;
import giovanni.epicenergy.entities.Provincia;
import giovanni.epicenergy.repositories.comuni_e_province.ComuneRepository;
import giovanni.epicenergy.repositories.comuni_e_province.ProvinciaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DatabaseLoader {
    @Autowired
   private CsvReaderService csvReaderService;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private ComuneRepository comuneRepository;

    @PostConstruct //Il PostConstruct  è utilizzato per specificare un metodo che deve essere eseguito dopo che un'istanza di una classe è stata costruita
    //e tutte le sue dipendenze sono state iniettate
    //ma prima che l'istanza sia utilizzata.

    public void saveProvincieComuniIntoDatabase() throws Exception{

        //Cerchiamo nel database le provincie e i comuni già esistenti
        List<Provincia> existingProvincia = provinciaRepository.findAll();
        List<Comune> existingComune = comuneRepository.findAll();

        //Ci salviamo in una variabile i Path dei file csv (Per comodità)
        Path csvComuni = Paths.get("src/main/java/giovanni/epicenergy/csv/comuni-italiani.csv");
        Path csvProvince = Paths.get("src/main/java/giovanni/epicenergy/csv/province-italiane.csv");

        //dalla lettura del file prendo i dati e li salvo in una lista che ritorna un array di stringhe
        List<String[]> csvProvincia = csvReaderService.readCsv(csvProvince);
        List<String[]> csvComune = csvReaderService.readCsv(csvComuni);

        //Funzioni che fanno il map  sulla lista di stringhe che ho creato sopra e creano delle classi Comune e Provincia
        List<Comune> comuni = csvComune.stream().map(element ->{
            Comune comune = new Comune(element[0], element[3], element[1], element[2]);
            return comune;
        }).collect(Collectors.toList());

        List<Provincia> provincie = csvProvincia.stream().map(element ->{
            Provincia provincia = new Provincia(element[0], element[1], element[2]);
            return provincia;
        }).collect(Collectors.toList());

        //il Set ci serve per non far finire elementi duplicati all'interno del DB, se ci sono elementi duplicati allora si rimuovono
        Set<Provincia> newProvinciaSet = new HashSet<>(provincie);
        newProvinciaSet.removeAll(existingProvincia);

        Set<Comune> newComuneSet = new HashSet<>(comuni);
        newComuneSet.removeAll(existingComune);

        //con questa funzione ci salviamo tutti i dati all'interno del DB
        //newComuneSet.forEach(comuneRepository::save);
        //newProvinciaSet.forEach(provinciaRepository::save);




        //TODO Controllo per non far riempire il db con gli stessi dati, sia su comuni che su

    }
}
