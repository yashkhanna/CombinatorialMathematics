package week1;

public class AndroidLockPatterns {
	
	static int sum = 0;
	
	public static void main (String[] args) throws java.lang.Exception
    {
        int[][] children = 
        {
            {-1, 0, 1, 0, 0, 0, 3, 0, 4},
            {0, -1, 0, 0, 0, 0, 0, 4, 0},
            {1, 0, -1, 0, 0, 0, 4, 0, 5},
            {0, 0, 0, -1, 0, 4, 0, 0, 0},
            {0, 0, 0, 0, -1, 0, 0, 0, 0},
            {0, 0, 0, 4, 0, -1, 0, 0, 0},
            {3, 0, 4, 0, 0, 0, -1, 0, 7},
            {0, 4, 0, 0, 0, 0, 0, -1, 0},
            {4, 0, 5, 0, 0, 0, 7, 0, -1}
        };
        
        int sum1 = 0;
        for (int x = 4; x<=9; x++) {
            kNodeWalks(children, x);
            
            System.out.println(x+" : "+(sum-sum1)+" ");    
            sum1 = sum;
        }
        
        System.out.println("Total : "+sum+" ");
    }
    
    public static void kNodeWalks(int[][] children, int k) {
        for (int i=0; i<9; i++) {
            int[] walk = new int[9];
            initWalk(walk, walk.length);
            dfs(children, i, walk, 0, k);
        }
    }
    
    public static void dfs(int[][] children, int current, int[] walk, int index, int k) {
        if (index == k)
            return;
        
        walk[index] = current;
        
        if (index+1 == k) {
            // printWalk(walk, k);
            sum++;
            return;
        }
        
        for (int j = 0; j < 9; j++) {
            int child = children[current][j];
            if (child == -1) {
                continue;
            }

            boolean walkContainsChild = walkContainsNode(walk, index+1, j);
            
            if (!walkContainsChild) {
                if (child == 0) {
                    dfs(children, j, walk, index+1, k);
                } else {
                    boolean directWalk = walkContainsNode(walk, index+1, children[current][j]);
                    if (directWalk) {
                        dfs(children, j, walk, index+1, k);
                    } 
                }
            }

        }
    } 

    public static boolean walkContainsNode(int[] walk, int k, int node) {
        for (int x =0; x < k; x++) {
            if (walk[x] == node) {
                return true;
            }
        }
        return false;
    }
     
    public static void initWalk(int arr[], int size) {
        for (int i=0; i<size; i++)
            arr[i] = -1;
    }
    
    public static void printWalk(int a[], int size) {
        for (int i=0; i<size; i++)
            System.out.print(a[i]+" ");
        System.out.println("");
    }
}
