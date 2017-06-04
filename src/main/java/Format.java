/**
 * Created by antoine on 04/06/17.
 */
import java.io.*;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.*;

public class Format {
    public static ArrayList<Stops> stopsFormat() throws IOException {
        Charset charset = Charset.forName("UTF-8");
        List<String> list = new ArrayList<String>();
        String root = "/home/antoine/Bureau/Métro Paris/";
        List<Stops> stopsList = new ArrayList<>();
        for (int i = 1; i <= 16; i++) { // Stockage de toutes les lignes de métro, station par station (en "ligne")
            final String ligne;
            if (i == 15) { // Cas métro 3bis
                ligne = "RATP_GTFS_METRO_3b";
            } else if (i == 16) { // cas métro 7bis
                ligne = "RATP_GTFS_METRO_7b";
            } else { // cas métro 1 à 14
                ligne = "RATP_GTFS_METRO_" + String.valueOf(i);
            }

            FileInputStream is = new FileInputStream(root + ligne + "/stops.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));

            // read file line by line
            String line = null;
            Scanner scanner = null;
            int index = 0;


            int iteration = 0;
            NumberFormat nf = NumberFormat.getInstance();

            while ((line = reader.readLine()) != null) { // lecture de chaque ligne du fichier
                if (iteration == 0) { // on saute la première ligne, qui contient le nom de chaque colonne
                    iteration++;
                    continue;
                }
                // use comma as separator
                String[] cols = line.split(",");
                Stops stop = new Stops();
                scanner = new Scanner(line);
                scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    String data = scanner.next();
                    if (index == 0)
                        stop.setId(Integer.parseInt(cols[0])); // colonne stop_id
                    else if (index == 1)
                        stop.setName(cols[2]); // colonne stop_name
                    else if (index == 2)
                        stop.setLatitude(Double.parseDouble(cols[4])); // colonne stop_lat
                    else if (index == 3)
                        stop.setLongitude(Double.parseDouble(cols[5])); // colonne stop_lon
                    index++;
                }
                index = 0;
                stopsList.add(stop); // on ajoute chaque station à la liste, qui contiendra toutes les stations
            }

            //close reader
            reader.close();

            stopsList = new ArrayList<Stops>(new LinkedHashSet<Stops>(stopsList)); // Antiduplicat exact, ne marche pas quand les id d'une même station sont différents

        }
        return (ArrayList<Stops>) stopsList;
    }

    public static ArrayList<Routes> routesFormat() throws IOException {
        // open file input stream
        Charset charset = Charset.forName("UTF-8");
        List<String> list = new ArrayList<String>();
        String root = "/home/antoine/Bureau/Métro Paris/";
        List<Routes> routesList = new ArrayList<>();

        for (int i = 1; i <= 16; i++) { // Stockage des informations d'une ligne de métro
            final String ligne;
            if (i == 15) { // Cas métro 3bis
                ligne = "RATP_GTFS_METRO_3b";
            } else if (i == 16) { // cas métro 7bis
                ligne = "RATP_GTFS_METRO_7b";
            } else { // cas métro 1 à 14
                ligne = "RATP_GTFS_METRO_" + String.valueOf(i);
            }

            FileInputStream is = new FileInputStream(root + ligne + "/routes.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));

            // read file line by line
            String line = null;
            Scanner scanner = null;
            int index = 0;


            int iteration = 0;
            NumberFormat nf = NumberFormat.getInstance();

            while ((line = reader.readLine()) != null) { // lecture de chaque ligne du fichier
                if (iteration == 0) { // on saute la première ligne, qui contient le nom de chaque colonne
                    iteration++;
                    continue;
                }
                // use comma as separator
                String[] cols = line.split(",");
                Routes route = new Routes();
                scanner = new Scanner(line);
                scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    String data = scanner.next();
                    if (index == 0)
                        route.setId(Integer.parseInt(cols[0])); // colonne route_id
                    else if (index == 1)
                        route.setName(cols[3]); // colonne route_long_name
                    index++;
                }
                index = 0;
                routesList.add(route); // on ajoute chaque route à la liste
            }

            //close reader
            reader.close();

            routesList = new ArrayList<Routes>(new LinkedHashSet<Routes>(routesList)); // Antiduplicat exact, ne marche pas quand les id d'une même station sont différents


        }
        return (ArrayList<Routes>) routesList;
    }

    public static void main(String[] args) throws IOException {

    }
}
