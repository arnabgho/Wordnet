/**
 * Auto Generated Java Class.
 */
public class SAP {
    private Digraph SAPG;
    public SAP(Digraph G){
        this.SAPG=G;
    }
    
    public int length(int v,int w){
        
        BreadthFirstDirectedPaths bfsw=new BreadthFirstDirectedPaths(SAPG,w);
        BreadthFirstDirectedPaths bfsv=new BreadthFirstDirectedPaths(SAPG,v);
        
        int i,minim;
        minim=100000000;
        boolean found=false;
        for(i=0;i<SAPG.V();i++){
            if(bfsw.hasPathTo(i) && bfsv.hasPathTo(i)){
                if(bfsw.distTo(i)+bfsv.distTo(i)<minim){
                    minim=bfsw.distTo(i)+bfsv.distTo(i);
                    found=true;
                }    
            }    
        }
        if(found)
            return minim;
        else
            return -1;
    }
    public int ancestor(int v,int w){
        BreadthFirstDirectedPaths bfsw=new BreadthFirstDirectedPaths(SAPG,w);
        BreadthFirstDirectedPaths bfsv=new BreadthFirstDirectedPaths(SAPG,v);
        int i,minim,anc=-1;
        minim=100000000;
        boolean found=false;
        for(i=0;i<SAPG.V();i++){
            if(bfsw.hasPathTo(i) && bfsv.hasPathTo(i)){
                if(bfsw.distTo(i)+bfsv.distTo(i)<minim){
                    anc=i;
                    minim=bfsw.distTo(i)+bfsv.distTo(i);
                    found=true;
                }    
            }    
        }
        if(found)
            return anc;
        else
            return -1;        
    }
    public int length(Iterable<Integer> v,Iterable<Integer> w){
        int minim=100000000;
        for(int V:v){
            for(int W:w){
                int l=length(V,W);
                if(l!=-1){
                    if(l<minim){
                        minim=l;
                    }
                }    
            }
        }
        if(minim==10000000)
            return -1;
        else
            return minim;
    }
    public int ancestor(Iterable<Integer> v,Iterable<Integer> w){
        int minim=100000000;
        int anc=-1;
        for(int V:v){
            for(int W:w){
                int l=length(V,W);
                if(l!=-1){
                    if(l<minim){
                        minim=l;
                        anc=ancestor(V,W);
                    }
                }    
            }
        }
        if(minim==10000000)
            return -1;
        else
            return anc;
    }
    public static void main(String[] args) {
        System.out.println(args[0]);
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
