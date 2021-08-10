import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Ipl {
   public static HashMap<String, Integer>   matchCountMap = new HashMap<String, Integer>();
    public static Map<String,HashMap<String,Integer>>   matchesWonPerTeam = new HashMap<String,HashMap<String,Integer>>();

    public static void main(String[] args) throws IOException {
        String line="";
        int skip=0;

        BufferedReader bf = new BufferedReader(new FileReader("matches.csv"));
        while((line=bf.readLine())!=null) {
            if (skip == 0) {
                skip++;
//                System.out.println(line);
                continue;
            }
            String[] match = line.split(",");
            if (matchCountMap.containsKey(match[1])) {
                matchCountMap.put(match[1], matchCountMap.get(match[1]) + 1);
            } else {
                matchCountMap.put(match[1], 1);
            }

//            System.out.println(matchesWonPerTeam.containsKey(match[1]));
//            System.out.println(line);

            if(matchesWonPerTeam.containsKey(match[1])){
//                System.out.println(matchesWonPerTeam.get(match[1]));

                if(matchesWonPerTeam.get(match[1]).containsKey(match[10])){
//                    System.out.println(matchesWonPerTeam.get(match[10]+"..."));
//                matchesWonPerTeam.put(match[1], (matchesWonPerTeam.put(matchesWonPerTeam.get(match[10])));
//                    matchesWonPerTeam.get(match[1]);
//                    System.out.println( matchesWonPerTeam.get(match[1]).get(match[10])+1);
                    matchesWonPerTeam.get(match[1]).put(match[10],matchesWonPerTeam.get(match[1]).get(match[10])+1);

                }
                else {
                  //  matchesWonPerTeam.put(match[1], new HashMap<String, Integer>());
                    matchesWonPerTeam.get(match[1]).put(match[10],1);
                }
            }
            else {
                matchesWonPerTeam.put(match[1],new HashMap<String,Integer>());
                matchesWonPerTeam.get(match[1]).put(match[10],1);
//                System.out.println(matchesWonPerTeam);

            }
        }
//        System.out.println(matchCountMap);
//        System.out.println("number of matches won per team per year");
        System.out.println(matchesWonPerTeam);

        }



}


