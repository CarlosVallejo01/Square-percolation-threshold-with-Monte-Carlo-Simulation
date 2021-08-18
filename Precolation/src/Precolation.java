public class Precolation {


    public Precolation(int N) {
        this.N = N;
        int size = N*N + 2;
        this.status = new int[size];
        this.id = new int[size];
        this.descendantsNumber = new int[size];
        for (int i = 0 ; i < size ; i++){
            status[i] = 0;
            id[i] = i;
            descendantsNumber[i] = 1;

        }


        //Make connections between from 0 to n-1
        for (int i = 0; i < N ; i++){
            id[i] = id[size -1];
        }
        // from (n*n-1) to  n
        for (int i = ((N*N-1)-N); i < N*N; i++){
            id[i] = id[size -2];
        }
    }

    private int root(int e){
        while(id[e] != e){
           id[e] = id[id[e]];
           e = id[e];
        }
        return e;
    }

    public boolean find(int a, int b){ //Tell us if two elements are connected
        if (root(a) == root(b)){
            return true;
        }
        return false;
    }

    public void union(int a, int b){ //Make union between two elements
        if (!find(a,b)) {
            int rootA = root(a);
            int rootB = root(b);
            if (descendantsNumber[rootA] >= descendantsNumber[rootB]) {
                id[rootB] = id[rootA];
                descendantsNumber[rootA] = descendantsNumber[rootA] + descendantsNumber[rootB];
            } else {
                id[rootA] = id[rootB];
                descendantsNumber[rootB] = descendantsNumber[rootB] + descendantsNumber[rootA];
            }
        }else {
           // System.out.println(a + " is already connected with " + b);
        }
    }

    public void openSite(int a){ //Open a new site. Use the method Union for adjoining neighbors iff open. (up, down, left and right)
        int left, right, up, down;
        status[a] = 1;
        left = a-1;
        right = a + 1;
        down = a - N; //Down is the one closet to 0.
        up = a + N;


        if ((a >= 0) && (a%N !=0) && (status[left] == 1))
            union(a, left);

        if ((right < (N*N)) && (right % N != 0) && (status[right] == 1))
            union(a, right);

        if (down >= 0 && status[down] == 1)
            union(a, down);

        if (up < (N*N) && status[up] == 1)
            union(a, up);
    }

    public void printConnections(){
        int index = 0;
        for (int i = 0; i < N ; i++){
            for (int j = 0; j < N ; j++){
                System.out.print(status[index] + " ");
                index = index + 1;
            }
            System.out.println("");
        }
    }
    public boolean precolated(){
        int size = N*N + 2;
        return find(size-1, size-2);
    }

    public double openPercentage(){
        double counter = 0;
        for (int i = 0; i < N*N ; i++){
            if (status[i] == 1){
                counter = counter + 1;
            }
        }
        return (counter/(N*N));
    }

    private int status[];
    private int id[];
    private int descendantsNumber[];
    private int N;
}
