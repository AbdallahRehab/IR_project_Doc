/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 *
 * @author assem
 */
public class matrix {

    void searching(String s) throws FileNotFoundException, IOException {
        
       

        // TODO code application logic here
        String dataDir = "dataDir"; // Index *.txt files from this directory
        File[] files = new File(dataDir).listFiles();

        for (File f : files) {   // for each file in the directory
            if (!f.isDirectory() && !f.isHidden() && f.exists() && f.canRead()) {
                System.out.println("Indexing " + f.getCanonicalPath());

            }
        }

        //doc 1
        LinkedList<String> obj1 = new LinkedList<String>();
        String f1 = null;
        Scanner input1 = new Scanner(files[0]);
        while (input1.hasNext()) {
            f1 = input1.nextLine();
        }
        StringTokenizer st1 = new StringTokenizer(f1, " ");
        while (st1.hasMoreTokens()) {
            obj1.add(st1.nextToken());
        }
        System.out.println(obj1);

        //doc 2
        LinkedList<String> obj2 = new LinkedList<String>();
        String f2 = null;
        Scanner input2 = new Scanner(files[1]);
        while (input2.hasNext()) {
            f2 = input2.nextLine();
            // System.out.println(f2);
        }
        StringTokenizer st2 = new StringTokenizer(f2, " ");
        while (st2.hasMoreTokens()) {
            obj2.add(st2.nextToken());
        }
        System.out.println(obj2);

        //doc 3
   
        
        LinkedList<String> obj3 = new LinkedList<String>();
        String f3 = null;
        Scanner input3 = new Scanner(files[2]);
        while (input3.hasNext()) {
            f3 = input3.nextLine();
        }
        StringTokenizer st3 = new StringTokenizer(f3, " ");
        while (st3.hasMoreTokens()) {
            obj3.add(st3.nextToken());
        }
        System.out.println(obj3);

        //doc 4
        LinkedList<String> obj4 = new LinkedList<String>();
        String f4 = null;
        Scanner input4 = new Scanner(files[3]);
        while (input4.hasNext()) {
            f4 = input4.nextLine();
        }
        StringTokenizer st4 = new StringTokenizer(f4, " ");
        while (st4.hasMoreTokens()) {
            obj4.add(st4.nextToken());
        }
        System.out.println(obj4);

        System.out.println("-----------------------------------------");

        LinkedList<String> objAll = new LinkedList<String>();

        String fAll = f1 + " " + f2 + " " + f3 + " " + f4;
        //stemmting
        PorterStemmer porterStemmer = new PorterStemmer();

        // fAll= porterStemmer.stemWord(fAll);
        // System.out.println(stem);
        //System.out.println(fAll);
        StringTokenizer stAll = new StringTokenizer(fAll, " ");
        while (stAll.hasMoreTokens()) {

            objAll.add(stAll.nextToken());
        }
        System.out.println("no sort----------->");
        System.out.println(objAll);

        objAll.sort(null);
        System.out.println("Sort-------------->");
        System.out.println(objAll);
        int sizeli = objAll.size();
        List<String> deduped = objAll;
        deduped = deduped.stream().distinct().collect(Collectors.toList());
        //System.out.println(objAll);
        System.out.println("remove dubliction--");
        System.out.println(deduped);

        Hashtable<Integer, String> stopdos = new Hashtable<Integer, String>();
        int stopind = 0;
        for (int i = 0; i < deduped.size(); i++) {
            String fstop = null;
            Scanner inputstop = new Scanner(files[4]);
            while (inputstop.hasNext()) {
                fstop = inputstop.nextLine();
                fstop.toLowerCase();
                fstop = fstop.replace("[(+_.\"\'^:),-]", "");
            }
            StringTokenizer sdstop = new StringTokenizer(fstop, " ");
            if (fstop.contains(deduped.get(i)) && (deduped.get(i).contains(fstop)) != false) {

            } else {
                //PorterStemmer stemmer =  new PorterStemmer();

                stopdos.put(stopind++, porterStemmer.stemWord(deduped.get(i)));
                //stopdos.put(f4, deduped.get(i));
                //System.out.println("44");
            }

        }

        System.out.println(stopdos);
        System.out.println(stopdos.get(0));

        System.out.println("\t" + "\t" + "\t" + "inicidence Matreix" + "\n");
        int[][] inMatreix = new int[stopdos.size()][files.length - 1];
        //System.out.println("\t"+"\t"+"\t"+"         "+"Doc1"+"\t"+"Doc2"+"\t"+"Doc3"+"\t"+"Doc4"+"\t");

        for (int i = 0; i < stopdos.size(); i++) {
            System.out.print(stopdos.get(i) + "\t" + "\t");
            for (int j = 0; j < files.length - 1; j++) {
                String x = null;
                Scanner inp = new Scanner(files[j]);
                while (inp.hasNext()) {
                    x = inp.nextLine();
                }

                if (x.contains(stopdos.get(i))) {
                    inMatreix[i][j] = 1;
                } else {
                    inMatreix[i][j] = 0;
                }

                System.out.print("\t" + "  " + inMatreix[i][j]);
            }
            //System.out.println(inMatreix[i]);
            System.out.println("");
        }
        System.out.println("----------");
        for (int i = 0; i < stopdos.size(); i++) {
            System.out.print(deduped.get(i) + "\t");
            for (int j = 0; j < files.length - 1; j++) {
                System.out.print(inMatreix[i][j]);
            }
            System.out.println("");

        }

        

        //search two word\
    StringTokenizer tokensearch = new StringTokenizer(s);
      
       LinkedList<String> twoword1=new LinkedList<>();
        int ttwo_wor1 = 0;
        
        while (tokensearch.hasMoreTokens()) {
            if (tokensearch.countTokens() > 3) {
                System.out.println("this quary is error !!");
                break;
            }
            //System.out.print(defaultTokenizer.nextToken()+"  ");
            //twoword1[ttwo_wor1] = tokensearch.nextToken();
            twoword1.add(tokensearch.nextToken());
            System.out.println("lll"+twoword1);
            ttwo_wor1++;
        }
        System.out.println("jjj"+twoword1.size());
   
 
        
    if(twoword1.size()>1){
        String gettwoword = s;
        //System.out.println("two");
        String[] twoword = new String[3];
        int ttwo_wor = 0;
        StringTokenizer defaultTokenizer = new StringTokenizer(gettwoword);
        while (defaultTokenizer.hasMoreTokens()) {
            if (defaultTokenizer.countTokens() > 3) {
                System.out.println("this quary is error !!");
                break;
            }
            //System.out.print(defaultTokenizer.nextToken()+"  ");
            twoword[ttwo_wor] = defaultTokenizer.nextToken();
            ttwo_wor++;
        }
        if (stopdos.containsValue(twoword[0]) && stopdos.containsValue(twoword[2])) {

            if (twoword[1].equals("&") || twoword[1].equals("+") || twoword[1].equals("and")) {
                //System.out.println("&+andd");
                LinkedList<Integer> Andtwoword1 = new LinkedList<>();
                LinkedList<Integer> Andtwoword2 = new LinkedList<>();

                for (int i = 0; i < stopdos.size(); i++) {

                    if (twoword[0].equals(stopdos.get(i))) {
                        String xword = "";
                        for (int j = 0; j < inMatreix[i].length; j++) {
                            //System.out.print(inMatreix[i][j]);
                            xword += inMatreix[i][j];
                            Andtwoword1.add(inMatreix[i][j]);
                        }

                        //System.out.println("");
                        System.out.println("X1 : " + xword);

                    }

                    // System.out.println("");
                    if (twoword[2].equals(stopdos.get(i))) {
                        String xxword = "";
                        for (int j = 0; j < inMatreix[i].length; j++) {
                            //System.out.print(inMatreix[i][j]);
                            xxword += inMatreix[i][j];
                            Andtwoword2.add(inMatreix[i][j]);

                        }

                        //System.out.println("");
                        System.out.println("X2 : " + xxword);

                    }

                }

                //System.out.println("\n and " + (x & xx));
                for (int i = 0; i < Andtwoword1.size(); i++) {
                    if (Andtwoword1.get(i) == 1 && Andtwoword2.get(i) == 1) {
                        System.out.println("Doc : " + (i + 1));
                    }
                }
            } else if (twoword[1].equals("|") || twoword[1].equals("OR")) {
                LinkedList<Integer> Andtwoword1 = new LinkedList<>();
                LinkedList<Integer> Andtwoword2 = new LinkedList<>();
                int x = 0, xx = 0;
                for (int i = 0; i < stopdos.size(); i++) {

                    if (twoword[0].equals(stopdos.get(i))) {
                        String xword = "";
                        for (int j = 0; j < inMatreix[i].length; j++) {
                            //System.out.print(inMatreix[i][j]);
                            xword += inMatreix[i][j];
                            Andtwoword1.add(inMatreix[i][j]);
                        }

                        System.out.println("\nX  " + xword);

                        x = Integer.parseInt(xword);

                    }

                    // System.out.println("");
                    if (twoword[2].equals(stopdos.get(i))) {
                        String xxword = "";
                        for (int j = 0; j < inMatreix[i].length; j++) {
                            //System.out.print(inMatreix[i][j]);
                            xxword += inMatreix[i][j];
                            Andtwoword2.add(inMatreix[i][j]);
                        }

                        System.out.println("\nX  " + xxword);

                        xx = Integer.parseInt(xxword);

                    }

                }
                for (int i = 0; i < Andtwoword1.size(); i++) {
                    if (Andtwoword1.get(i) == 1 || Andtwoword2.get(i) == 1) {
                        System.out.println("Doc : " + (i + 1));
                    }
                }
                
                
            }
        } else {
            System.out.println("this ONE of two word OR two word don't found in docs  ");
        }
    }
    else {
    //search single word\
        
            //String getword = s;
            String getword = twoword1.get(0);
        if (stopdos.containsValue(getword)) {
            for (int i = 0; i < stopdos.size(); i++) {

                if (getword.equals(stopdos.get(i))) {

                    for (int j = 0; j < inMatreix[i].length; j++) {
                        //System.out.print(inMatreix[i][j]);
                        if (inMatreix[i][j] == 1) {
                            System.out.print("Doc : " + (j + 1) + "   ");
                        }

                    }
                    System.out.println("");
                }
                // System.out.println("");

            }
        } else {
            System.out.println("this word don't found in docs");
        }
   
    }
    }
}
