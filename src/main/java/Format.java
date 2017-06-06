/**
 * Created by antoine on 04/06/17.
 */
import java.io.*;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Format {
    public static ArrayList<Stops> stopsFormat() throws IOException {
        Charset charset = Charset.forName("UTF-8");
        List<String> list = new ArrayList<String>();
        String root = "/home/antoine/Bureau/Métro Paris/";
        ArrayList<Stops> stopsList = new ArrayList<>();
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
                if (i == 15) { // Cas métro 3bis
                    stop.setLine("3b");
                } else if (i == 16) { // cas métro 7bis
                    stop.setLine("7b");
                } else { // cas métro 1 à 14
                    stop.setLine(Integer.toString(i));
                }
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
            Stops.searchDuplicates(stopsList); // Vrai antiduplicat; les doublons de stations de la même ligne sont effacées, mais pas ceux de lignes différentes

        }
        return stopsList;
    }

    public static HashMap<Integer, ArrayList<Integer>> edgeLine() throws IOException, ParseException {
        // open file input stream
        Charset charset = Charset.forName("UTF-8");
        String root = "/home/antoine/Bureau/Métro Paris/";
        HashMap<Integer, ArrayList<Integer>> stationsListPerLine = new HashMap<>(16);
        ArrayList<Integer> stationsList = new ArrayList<>();
        ArrayList<Integer> noDuplicates = new ArrayList<>();
        for (int i = 1; i <= 16; i++) { // Stockage des informations d'une ligne de métro
            final String ligne;
            if (i == 15) { // Cas métro 3bis
                ligne = "RATP_GTFS_METRO_3b";
            } else if (i == 16) { // cas métro 7bis
                ligne = "RATP_GTFS_METRO_7b";
            } else { // cas métro 1 à 14
                ligne = "RATP_GTFS_METRO_" + String.valueOf(i);
            }

            FileInputStream is = new FileInputStream(root + ligne + "/stop_times.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));

            // read file line by line
            String line = null;
            Scanner scanner = null;
            int index = 0;

            int iteration = 0;

            while ((line = reader.readLine()) != null) { // lecture de chaque ligne du fichier
                if (iteration == 0) { // on saute la première ligne, qui contient le nom de chaque colonne
                    iteration++;
                    continue;
                }
                // use comma as separator
                String[] cols = line.split(",");
                scanner = new Scanner(line);
                scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    String data = scanner.next();
                    if (!stationsList.contains(Integer.parseInt(cols[3]))) {
                        stationsList.add(Integer.parseInt(cols[3])); // colonne stop_id
                    }
                }
            }
            //System.out.println(stationsList);
            stationsListPerLine.put(i, stationsList);
            stationsList = new ArrayList<Integer>();

            //close reader
            reader.close();
            //stopTimesList = new ArrayList<stopTimes>(new LinkedHashSet<stopTimes>(stopTimesList)); // Antiduplicat exact, ne marche pas quand les id d'une même station sont différents

        }
        return stationsListPerLine;
    }

    public static ArrayList<Transfers> transfersFormat() throws IOException, ParseException {
        // open file input stream
        Charset charset = Charset.forName("UTF-8");
        List<String> list = new ArrayList<String>();
        String root = "/home/antoine/Bureau/Métro Paris/";
        List<Transfers> transfersList = new ArrayList<>();

        for (int i = 1; i <= 16; i++) { // Stockage des informations d'une ligne de métro
            final String ligne;
            if (i == 15) { // Cas métro 3bis
                ligne = "RATP_GTFS_METRO_3b";
            } else if (i == 16) { // cas métro 7bis
                ligne = "RATP_GTFS_METRO_7b";
            } else { // cas métro 1 à 14
                ligne = "RATP_GTFS_METRO_" + String.valueOf(i);
            }

            FileInputStream is = new FileInputStream(root + ligne + "/transfers.txt");
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
                Transfers transfer = new Transfers();
                scanner = new Scanner(line);
                scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    String data = scanner.next();
                    if (index == 0)
                        transfer.setFrom(Integer.parseInt(cols[0])); // colonne trip_id
                    else if (index == 1)
                        transfer.setTo(Integer.parseInt(cols[1])); // colonne arrival_date
                    else if (index == 2)
                        transfer.setTransfer_time(Integer.parseInt(cols[3])); // colonne departure_date
                    index++;
                }
                index = 0;
                transfersList.add(transfer); // on ajoute chaque route à la liste
            }

            //close reader
            reader.close();

            transfersList = new ArrayList<Transfers>(new LinkedHashSet<Transfers>(transfersList)); // Antiduplicat exact, ne marche pas quand les id d'une même station sont différents


        }
        return (ArrayList<Transfers>) transfersList;
    }
    public static void main(String[] args) throws IOException {

    }
}
