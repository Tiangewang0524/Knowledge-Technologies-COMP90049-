/**
 * Created by wtg on 3/10/17.
 */

import java.io.*;

public class Main {

    //Determine whether the tweet has the word which contains more than 11 letters.
    public static void New_attribute_length(){
        int i;
        String judgement;

        try {
            StringBuilder result = new StringBuilder();
            BufferedReader in = new BufferedReader(new FileReader(new File("test.txt")));
            while (true) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }
                String[] lines = line.split("\t");
                String[] words = lines[2].split("\\s+");
                for (i = 0; i< words.length; i++)
                    words[i] = words[i].replaceAll("[\\pP‘’“”]",""); //Delete the punctuation.
                for (i = 0; i< words.length; i++)
                    if (words[i].length() > 11){
                        judgement = "Y";
                        result.append(judgement);
                        result.append("\n");
                        break;
                    }
                if (i == words.length){
                    judgement = "N";
                    result.append(judgement);
                    result.append("\n");
                }
            }
            PrintWriter printWriter = new PrintWriter(new File("result_long_words.txt"));
            printWriter.write(result.toString());
            printWriter.close();
        }catch (Exception e) {
            e.printStackTrace();
            return;
        }
        System.out.println("The result saved to result_long_words.txt");
    }

    //Determine whether a tweet has negative words.
    public static void New_attribute_nega(){
        int i;
        String judgement;

        try {
            StringBuilder result2 = new StringBuilder();
            BufferedReader in = new BufferedReader(new FileReader(new File("test.txt")));
            while (true) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }
                String[] lines = line.split("\t");
                String[] words = lines[2].split("\\s+");

                for (i = 0; i< words.length; i++)
                    if (words[i].equals("not") || (words[i].indexOf("'t"))>0
                            || words[i].equals("cannot") ){ //Determine whether words[i] contains "not" and "'t".
                        judgement = "Y";
                        result2.append(judgement);
                        result2.append("\n");
                        break;
                    }
                if (i == words.length){
                    judgement = "N";
                    result2.append(judgement);
                    result2.append("\n");
                }
            }
            PrintWriter printWriter2 = new PrintWriter(new File("result_nega_words.txt"));
            printWriter2.write(result2.toString());
            printWriter2.close();
        }catch (Exception e) {
            e.printStackTrace();
            return;
        }
        System.out.println("The result saved to result_nega_words.txt");
    }

    public static void main(String[] args) {
        New_attribute_length();
        New_attribute_nega();

    }
}
