import com.ipl.model.Match;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Ipl {
    public static void main(String[] args) throws IOException {
        List<Match>  matchData=getMatchData();

        matchesPlayedPerYear(matchData);
        matchesWonPerTeamPerYear(matchData);

//        String line="";
//        int skip=0;
//        Deliveries dev=new Deliveries();
//        List<String[]> content=dev.readData();
//        String[]  arrayOfYear = new String[300];
//
//
//        BufferedReader bf= new BufferedReader(new FileReader("matches.csv"));
//        while((line=bf.readLine())!=null) {
//            if (skip == 0) {
//                skip++;
//                System.out.println(line);
//                continue;
//            }
////            System.out.println(line);
//            String[] match = line.split(",");
//
////            arrayOfYear.push(match[1]);
////            System.out.println(match[1]);
//
//            if (matchCountMap.containsKey(match[1])) {
//                matchCountMap.put(match[1], matchCountMap.get(match[1]) + 1);
//            } else {
//                matchCountMap.put(match[1], 1);
//
//
//            }
//
////            System.out.println(matchesWonPerTeam.containsKey(match[1]));
////            System.out.println(line);
//
//            if(matchesWonPerTeam.containsKey(match[1])){
////                System.out.println(matchesWonPerTeam.get(match[1]));
//
//                if(matchesWonPerTeam.get(match[1]).containsKey(match[10])){
////                    System.out.println(matchesWonPerTeam.get(match[10]+"..."));
////                matchesWonPerTeam.put(match[1], (matchesWonPerTeam.put(matchesWonPerTeam.get(match[10])));
////                    matchesWonPerTeam.get(match[1]);
////                    System.out.println( matchesWonPerTeam.get(match[1]).get(match[10])+1);
//                    matchesWonPerTeam.get(match[1]).put(match[10],matchesWonPerTeam.get(match[1]).get(match[10])+1);
//
//                }
//                else {
//                  //  matchesWonPerTeam.put(match[1], new HashMap<String, Integer>());
//                    matchesWonPerTeam.get(match[1]).put(match[10],1);
//                }
//            }
//            else {
//                matchesWonPerTeam.put(match[1],new HashMap<String,Integer>());
//                matchesWonPerTeam.get(match[1]).put(match[10],1);
////                System.out.println(matchesWonPerTeam);
//
//            }
////            System.out.println("Extra runs conceded per team in the year 2016\n");
//
//
//
//
//        }
//        System.out.println("number of matches played per year");
//        System.out.println(matchCountMap);
//        System.out.println("number of matches won per team per year");
//        System.out.println(matchesWonPerTeam);
//
////        System.out.println(arrayOfYear);
//

    }


    public  static List<Match>  getMatchData() throws IOException {
  List<Match>  matches=new ArrayList<>();
    String line="";

    int skip=0;
        BufferedReader bf= new BufferedReader(new FileReader("matches.csv"));
        while((line=bf.readLine())!=null) {
            Match match=new Match();
            if (skip == 0) {
                skip++;
                System.out.println(line);
                continue;
            }
//         System.out.println(line);
            String[] matchData = line.split(",");
             match.setSeason(matchData[1]);
             match.setWinner(matchData[10]);
             matches.add(match);
        }
        System.out.println(matches.size());
//        for (Match m: matches) {
//            System.out.println(m.getSeason());
//        }
        return matches;
    }

    public static void  matchesPlayedPerYear(List<Match> matches){
        HashMap<String, Integer>   matchCountMap = new HashMap<String, Integer>();

        for (Match matchData:matches) {

            if (matchCountMap.containsKey(matchData.getSeason())) {
                matchCountMap.put(matchData.getSeason(), matchCountMap.get(matchData.getSeason()) + 1);
            } else {
                matchCountMap.put(matchData.getSeason(), 1);

            }

        }
        System.out.println(matchCountMap);
    }

    public static void matchesWonPerTeamPerYear( List<Match> matches) {
        Map<String, HashMap<String, Integer>> matchesWonPerTeam = new HashMap<String, HashMap<String, Integer>>();
        System.out.println("matches won per team per year");

        for (Match matchData : matches) {

            if (matchesWonPerTeam.containsKey(matchData.getSeason())) {
//                System.out.println(matchesWonPerTeam.get(match[1]));

                if (matchesWonPerTeam.get(matchData.getSeason()).containsKey(matchData.getWinner())) {
//                    System.out.println(matchesWonPerTeam.get(match[10]+"..."));
//                matchesWonPerTeam.put(match[1], (matchesWonPerTeam.put(matchesWonPerTeam.get(match[10])));
//                    matchesWonPerTeam.get(match[1]);
//                    System.out.println( matchesWonPerTeam.get(match[1]).get(match[10])+1);
                    matchesWonPerTeam.get(matchData.getSeason()).put(matchData.getWinner(), matchesWonPerTeam.get(matchData.getSeason()).get(matchData.getWinner()) + 1);

                } else {
                    //  matchesWonPerTeam.put(match[1], new HashMap<String, Integer>());
                    matchesWonPerTeam.get(matchData.getSeason()).put(matchData.getWinner(), 1);
                }
            } else {
                matchesWonPerTeam.put(matchData.getSeason(), new HashMap<String, Integer>());
                matchesWonPerTeam.get(matchData.getSeason()).put(matchData.getWinner(), 1);
//
            }
        }
        System.out.println(matchesWonPerTeam);
    }

//    public static void extraRunConceded()

}


