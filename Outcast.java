/**
 * Auto Generated Java Class.
 */
public class Outcast {
    private WordNet myWordnet;
    public Outcast(WordNet wordnet){
        this.myWordnet=wordnet;
    }
    public String outcast(String[] nouns){
        int n=nouns.length;
        int maxim=0,maxpos=0;
        int[] d=new int[n];
        for(int i=0;i<n;i++){
            int dist=0;
            for(int j=0;j<n;j++){
                if(i==j)
                    continue;
//                System.out.println("i "+i+" j "+j);
//                System.out.println("Nouns[i] "+nouns[i]+" Nouns[j] "+nouns[j]);
                int l=myWordnet.distance(nouns[i],nouns[j]);
//                System.out.println(l);
                if(l==-1){
                    dist+=(10000000);
                }
                else{
                    dist+=l;
                }
            }
            if(dist>maxim){
                maxim=dist;
                maxpos=i;
            }
        }
        return nouns[maxpos];    
    }
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }

}
