package giovanni.epicenergy.utility;

import giovanni.epicenergy.entities.Comune;
import giovanni.epicenergy.entities.Provincia;
import giovanni.epicenergy.repositories.comuni_e_province.ComuneRepository;
import giovanni.epicenergy.repositories.comuni_e_province.ProvinciaRepository;
import giovanni.epicenergy.services.ComuneService;
import giovanni.epicenergy.services.ProvinciaService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DatabaseLoader {
    @Autowired
   private CsvReaderService csvReaderService;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private ProvinciaService provinciaService;

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private ComuneService comuneService;

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
        HashSet<String> listaErrori = new HashSet<>();

        final int[] n = {1};
        List<Comune> comuni = csvComune.stream().map(element ->{
            Comune newComune = new Comune();
            try{
                newComune.setNomeComune(element[2]);
                newComune.setCap(element[0]+element[1]);
                newComune.setNomeProvincia(provinciaService.findByProvincia(element[3]).getProvincia());
            }catch (Exception error){
                listaErrori.add(element[3]);
            }

            if (Objects.equals(element[1], "#RIF!")){
                String formattedNumber = String.format("%03d", n[0]);
                Comune comune = new Comune(element[3],element[2], element[0] + formattedNumber);
                n[0]++;
                return comune;
            }else{
                return new Comune(element[3],element[2], element[0] + element[1]);
            }
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

        for (Provincia provincia: newProvinciaSet){
            if (Objects.equals(provincia.getProvincia(), "Carbonia Iglesias")){
                provincia.setProvincia("Sud Sardegna");
                provincia.setSigla("SU");
            }
            if (Objects.equals(provincia.getProvincia(), "Verbania")){
                provincia.setProvincia("Verbano-Cusio-Ossola");
                provincia.setSigla("VCO");
            }
            if (Objects.equals(provincia.getProvincia(), "Monza-Brianza")) provincia.setProvincia("Monza e della Brianza");
            if (Objects.equals(provincia.getProvincia(), "Vibo-Valentia")) provincia.setProvincia("Vibo Valentia");
            if (Objects.equals(provincia.getProvincia(), "La-Spezia")) provincia.setProvincia("La Spezia");
            if (Objects.equals(provincia.getProvincia(), "Aosta")) provincia.setProvincia("Valle d'Aosta/Vallée d'Aoste");
            if (Objects.equals(provincia.getProvincia(), "Ascoli-Piceno")) provincia.setProvincia("Ascoli Piceno");
            if (Objects.equals(provincia.getProvincia(), "Bolzano")) provincia.setProvincia("Bolzano/Bozen");
            if (Objects.equals(provincia.getProvincia(), "Pesaro-Urbino")) provincia.setProvincia("Pesaro e Urbino");
            if (Objects.equals(provincia.getProvincia(), "Reggio-Calabria")) provincia.setProvincia("Reggio Calabria");
            if (Objects.equals(provincia.getProvincia(), "Forli-Cesena")) provincia.setProvincia("Forlì-Cesena");
            if (Objects.equals(provincia.getProvincia(), "Reggio-Emilia")) provincia.setProvincia("Reggio nell'Emilia");
            // provinciaRepository.save(provincia);   // solo prima run per inserire i dati nel db
        }

        listaErrori.forEach(System.out::println);

        for (Comune comune: newComuneSet){
            for (Provincia provincia: newProvinciaSet){
                if (comune.getNomeProvincia().indexOf(provincia.getProvincia()) == 0){
                    comune.setProvincia(provinciaService.findByProvincia(comune.getNomeProvincia()));
                    // comuneRepository.save(comune);  // solo prima run per inserire i dati nel db
                }
            }
        }

    }
}
