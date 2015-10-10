public class WordNet{
    
    
    Digraph relations;
    TrieST< Bag<Integer> > allNouns;
    String[] allSynsets;
    String[] allHypernyms;
    SAP mySap;
    public WordNet(String synsets,String hypernyms){
        allNouns=new TrieST<Bag<Integer> >();
        In syn=new In(synsets);
        String[] allSynsets =syn.readAllLines();
        int i,j,n;
        n=allSynsets.length;
        for(i=0;i<n;i++){
            String temp=allSynsets[i];
            String[] syns=temp.split("\\s*,\\s*");
            
            int ID=Integer.parseInt(syns[0]);
            String nouns[]=syns[1].split("\\s+");
            Bag<Integer> tempBag;
            for(j=0;j<nouns.length;j++){
                if(allNouns.get(nouns[j])==null){
                    tempBag=new Bag<Integer>();
                }    
                else{
                    tempBag=allNouns.get(nouns[j]);
                }
                tempBag.add(ID);
                allNouns.put(nouns[j],tempBag);
            }    
        }
        
        In hyp=new In(hypernyms);
        String[] allHypernyms=hyp.readAllLines();
        int nHyp=allHypernyms.length;
        relations=new Digraph(nHyp);
        for(i=0;i<nHyp;i++){
            String temp=allHypernyms[i];
            String[] hyps=temp.split(",");
            int u=Integer.parseInt(hyps[0]);
            for(j=1;j<hyps.length;j++){
                int v=Integer.parseInt(hyps[j]);
                relations.addEdge(u,v);
            }
        }
        mySap=new SAP(relations);
    }    
 
    public Iterable<String> nouns(){
        return allNouns.keys();
    }
    public boolean isNoun(String word){
        return (allNouns.get(word)!=null); 
    }    
    public int distance(String nounA,String nounB){
       if(!isNoun(nounA) || !isNoun(nounB))
           return -1;
       else{
           Bag<Integer> A=allNouns.get(nounA);
           Bag<Integer> B=allNouns.get(nounB);
           int ans=mySap.length(A,B);
           return ans;
       }
    }    
//    
    public String sap(String nounA,String nounB){
        if(!isNoun(nounA) || !isNoun(nounB))
           return null;
       
       else{
           Bag<Integer> A=allNouns.get(nounA);
           Bag<Integer> B=allNouns.get(nounB);
           int ans=mySap.ancestor(A,B);
           if(ans!=-1){
               String[] splited=allSynsets[ans].split(",");
               return splited[1];
           }
           else
               return null;
       }
       
    }    
//    
//    public static void main(String[] args ){
//        
//    }    
}    