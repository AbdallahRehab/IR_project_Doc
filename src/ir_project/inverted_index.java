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
public class inverted_index {
    
    void invertedindex(String s) throws FileNotFoundException, IOException{
        
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
        
     System.out.println("\n" + "\t" + "\t" + "\t" + "inverted index" + "\n");
        LinkedList<Hashtable<String, Integer>> inverindex = new LinkedList<Hashtable<String, Integer>>();
        // Map<new Hashtable<String, Integer>,String> inverhash=new Map<new Hashtable<String,Integer>, String>;
        //////Map<Hashtable<String, String>, Hashtable<String,Integer>> inv = new Hashtable<>();
        //Map<HashMap<String, Integer>, LinkedList<String>> inv = new HashMap<>();
        //Hashtable() h =new Hashtable(Hashtable<Integer, String>)();
        Hashtable<String, Integer> invfreq = new Hashtable<>();
        for (int i = 0; i < stopdos.size(); i++) {
            inverindex.add(new Hashtable<String, Integer>());
            //inv.put(new HashMap<String, Integer>(),new HashMap<String,Integer>() );
            // inv.put(new HashMap<String, Integer>(),new LinkedList<String>() );
        }
        for (int i = 0; i < stopdos.size(); i++) {
            int freq = 0;
            //System.out.print(stopdos.get(i)+"\t"+"\t");
            int xx = 1;
            for (int j = 0; j < files.length - 1; j++) {
                String x = null;
                Scanner inp = new Scanner(files[j]);
                while (inp.hasNext()) {
                    x = inp.nextLine();
                }
                StringTokenizer sd = new StringTokenizer(f4, " ");
                if (x.contains(stopdos.get(i))) {
                    String hh = stopdos.get(i) + (xx);
                    freq++;
                    inverindex.get(i).put(hh, (j + 1));
                    //inv.put(new HashMap<String, Integer>().put(stopdos.get(i), freq)
                    //      ,new HashMap<String, Integer>().put(hh, ("Doc"+(j+1))));

//                   inv.put(new HashMap<String, Integer>().put(stopdos.get(i), freq)
//                           ,new LinkedList<String>().add( ("Doc"+(j+1))));
//                   
                    xx++;

                }

            }
            // System.out.println(inMatreix[i][0]);
            System.out.print(stopdos.get(i) + " freq=" + freq + "\t" + "\t");
            invfreq.put(stopdos.get(i), freq);

            //System.out.print("\t"+inverindex.get(i));
            System.out.print("\t" + inverindex.get(i));
            System.out.println("");
            // inv.put(stopdos, stopdos);
        }
        System.out.println("\n" + inverindex);

        System.out.println(invfreq);

     

        //and or inverded index
      
        String gettwowordinver = s;
        //System.out.println("two");
       
        StringTokenizer tokensearch = new StringTokenizer(s);
       // String[] twoword1 = new String[3];
       LinkedList<String> twowordinver=new LinkedList<>();
        int ttwo_wor1 = 0;
        
        while (tokensearch.hasMoreTokens()) {
            if (tokensearch.countTokens() > 3) {
                System.out.println("this quary is error !!");
                break;
            }
            //System.out.print(defaultTokenizer.nextToken()+"  ");
            //twoword1[ttwo_wor1] = tokensearch.nextToken();
            twowordinver.add(tokensearch.nextToken());
            System.out.println("lll"+twowordinver);
            ttwo_wor1++;
        }
        System.out.println("jjj"+twowordinver.size());
        //System.out.println(twowordinver[0]);
        if(twowordinver.size()>1){
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

        if (stopdos.containsValue(twowordinver.get(0)) && stopdos.containsValue(twowordinver.get(2))) {
            Hashtable<String, Integer> term1 = null, term2 = null;
            if (twowordinver.get(1).equals("&") || twowordinver.get(1).equals("+") || twowordinver.get(1).equals("and")) {
                //System.out.println("&+andd");

                for (int i = 0; i < stopdos.size(); i++) {

                    if (twowordinver.get(0).equals(stopdos.get(i))) {
                        System.out.println(inverindex.get(i));
                        term1 = inverindex.get(i);
                    }
                    if (twowordinver.get(2).equals(stopdos.get(i))) {
                        System.out.println(inverindex.get(i));
                        term2 = inverindex.get(i);
                    }
                }
                LinkedList<Integer> reslutsearchinver = inversearchand(term1, term2,twowordinver.get(0),twowordinver.get(0));
                System.out.println("rrrrrAnd" + reslutsearchinver);
            } else if (twowordinver.get(1).equals("|") || twowordinver.get(1).equals("OR")) {
                for (int i = 0; i < stopdos.size(); i++) {

                    if (twowordinver.get(0).equals(stopdos.get(i))) {
                        System.out.println(inverindex.get(i));
                        term1 = inverindex.get(i);
                    }
                    if (twowordinver.get(2).equals(stopdos.get(i))) {
                        System.out.println(inverindex.get(i));
                        term2 = inverindex.get(i);
                    }
                }
                LinkedList<Integer> reslutsearchinver = inversearchor(term1, term2,twowordinver.get(0),twowordinver.get(2));
                System.out.println("rrrrrrreOr" + reslutsearchinver);

            } else {
                System.out.println("this ONE of two word OR two word don't found in docs inverded ");
            }
        }
    }
        else{
            
            //search in inverded index
        
        
        String getwordinver = s;
        if (stopdos.containsValue(getwordinver)) {
            for (int i = 0; i < stopdos.size(); i++) {

                if (getwordinver.equals(stopdos.get(i))) {
                    System.out.println(inverindex.get(i));
                }
            }
        } else {
            System.out.println("this word don't found in docs");
        }
        
        
        }
    }
    public static LinkedList inversearchand(Hashtable term1, Hashtable term2,String T1,String T2) {
        LinkedList<Integer> merge_inver = new LinkedList<>();
        int c = 0;
        try {
            System.out.println("abdoooooooo");
            int x = 1;
            int y = 1;
            System.out.println(term1 + "  " + term2);

            while (x <= term1.size() || y <= term2.size()) {
               // System.out.print("while "+term1.size()+"  "+term2.size()+"\n "+term1.get(T1+x));
                c++;
                if (term1.get(T1+x).equals(term2.get(T2+y))) {
                    merge_inver.add((Integer) term1.get(T1+x));
                    System.out.println("" + term1.get(T1+x));
                    y++;

                    x++;
                } else if ((int) term1.get(T1+x) < (int) term2.get(T2+y)) {
                    x++;
                    System.out.println("xxx");
                    //System.out.println(term1.get(T1+x));
                } else {
                    merge_inver.add((Integer) term2.get(T2+y));
                    y++;

                }
            }

        } catch (Exception eee) {

        }
        System.out.println("cc" + c);
        return merge_inver;
    }

    public static LinkedList inversearchor(Hashtable term1, Hashtable term2,String T1,String T2) {
        LinkedList<Integer> merge_inver = new LinkedList<>();
        int c=0;
        try {
            System.out.println("abdoooooooo");
            int x = 1;
            int y = 1;
            System.out.println(term1 + "  " + term2);

            while (x <= term1.size()+1 && y <= term2.size()+1) {
               // System.out.print("while "+term1.size()+"  "+term2.size()+"\n "+term1.get(T1+x));
                c++;
                if (term1.get(T1+x).equals(term2.get(T2+y))) {
                    merge_inver.add((Integer) term1.get(T1+x));
                    System.out.println("" + term1.get(T1+x));
                    y++;

                    x++;
                } else if ((int) term1.get(T1+x) < (int) term2.get(T2+y)||term2.get(T2+y).equals(null)) {
                    System.out.println("xxx");
                    merge_inver.add((Integer) term1.get(T1+x));
                    x++;
                    //System.out.println(term1.get(T1+x));
                } else if((int) term1.get(T1+x) > (int) term2.get(T2+y)||term1.get(T1+x).equals(null)) {
                    merge_inver.add((Integer) term2.get(T2+y));
                    System.out.println("yyyyy");
                    y++;

                }
            }

        } catch (Exception e) {

        }
   
        
        return merge_inver;
    }
    
}
