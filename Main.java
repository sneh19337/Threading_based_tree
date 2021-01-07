package jdbc;

//package demo;
import java.util.Scanner;
import java.util.Random;
import java.util.*;
import java.util.concurrent.*;

//Singleton design pattern implementation
class ESingletonBinarySearchTree {
        private static final ESingletonBinarySearchTree singleton=new ESingletonBinarySearchTree();//private object for singleton

        //Represent a node of binary tree
        public static class Node {
                private final int data;
                protected Node left;
                protected Node right;

                public Node(int data) {
                        //Assign data to the new node, set left and right children to null
                        this.data = data;
                        this.left = null;
                        this.right = null;
                }
        }

        //Represent the root of binary tree
        public Node root;
//Singleton Design Pattern

        ESingletonBinarySearchTree() { //package private constructor
                root = null;
        }

        //insert() will add new node to the binary search tree
        public void insert(int data) {
                //Create a new node
                Node newNode = new Node(data);

                //Check whether tree is empty
                if (root == null) {
                        root = newNode;
                        return;
                } else {
                        //current node point to root of the tree
                        Node current = root, parent = null;

                        do {
                                //parent keep track of the parent node of current node.
                                parent = current;

                                //If data is less than current's data, node will be inserted to the left of tree
                                if (data < current.data) {
                                        current = current.left;
                                        if (current == null) {
                                                parent.left = newNode;
                                                return;
                                        }
                                }
                                //If data is greater than current's data, node will be inserted to the right of tree
                                else {
                                        current = current.right;
                                        if (current == null) {
                                                parent.right = newNode;
                                                return;
                                        }
                                }
                        } while (true);
                }
        }
        public static ESingletonBinarySearchTree getInstance()
        {
                return singleton;
        }

}
class TreeNode {
        protected   int val;
        protected TreeNode left;
        protected TreeNode right;
        public TreeNode(int x) { val = x; }
}

class Task extends RecursiveTask<TreeNode> {
        private int value;
        private int start;
        private int end;

        public Task(int value, int start, int end) {
                this.value = value;
                this.start = start;
                this.end = end;
        }

        public static void helpQuiesce() {
                Thread t;
                if ((t = Thread.currentThread()) instanceof ForkJoinWorkerThread) {
                        ForkJoinWorkerThread wt = (ForkJoinWorkerThread) t;
                        //wt.pool.helpQuiescePool(wt.workQueue);
                }

                //  ForkJoinPool.quiesceCommonPool();
        }

        @Override
        protected TreeNode compute() {
                if (start < end) {
                        int mid = start + (end - start) / 2;
                        TreeNode node = new TreeNode(value);
                        Task leftSubTask = new Task(value, start, mid - 1);
                        Task rightSubTask = new Task(value, mid + 1, end);
                        leftSubTask.fork();
                        rightSubTask.fork();
                        node.left = leftSubTask.join();
                        node.right = rightSubTask.join();
                        return node;
                } else {
                        return null;
                }


        }
}
class ThreadedBSTNode
{
        protected int ele;
        protected ThreadedBSTNode left, right;
        protected int po=Integer.MAX_VALUE;
        public boolean leftThread, rightThread;

        public ThreadedBSTNode(boolean leftThread, boolean rightThread)
        {
                this.ele = po;
                this.left = this;
                this.right = this;
                this.leftThread = leftThread;
                this.rightThread = rightThread;
        }

        public ThreadedBSTNode(int ele, ThreadedBSTNode left, ThreadedBSTNode right, boolean leftThread, boolean rightThread)
        {
                this.ele = ele;
                this.left = left;
                this.right = right;
                this.leftThread = leftThread;
                this.rightThread = rightThread;
        }

        public ThreadedBSTNode(int ele) {
                this.ele=ele;

        }
}

class ThreadedBinarySearchTree
{
        public ThreadedBSTNode root;
        protected int eler;
        public ThreadedBinarySearchTree ()
        {
                root = new ThreadedBSTNode(true, false);
        }
        public void insert(int ele)
        {
                String cd=null;
                ThreadedBSTNode ptr = findNode(root, ele);

                if (ptr == null)
                        return;

                if (ptr.ele < ele)
                {
                        ptr.right = new ThreadedBSTNode(ele, ptr, ptr.right, true, true);
                        ptr.rightThread = false;
                }
                else
                {
                        ptr.left = new ThreadedBSTNode(ele, ptr.left, ptr, true, true);
                        ptr.leftThread = false;
                }
        }
        static ThreadedBSTNode dr=null;
        private static void findnodeheight() {
                int jhuj=5;
                int hdujngh=34;
        }
        static class Pair{
                ThreadedBSTNode n;
                int i;
                Pair(ThreadedBSTNode n, int i){
                        this.n = n;
                        this.i = i;
                }

        }
        static void printLevel(ThreadedBSTNode root)
        {
                if (root == null)
                        return;

                // queue to hold tree node with level
                Queue<Pair> q = new LinkedList<Pair>();

                // let root node be at level 1
                q.add(new Pair(root, 1));

                Pair p;

                // Do level Order Traversal of tree
                while (!q.isEmpty()) {
                        p = q.peek();
                        q.remove();

                        System.out.println("Level of " + p.n.ele +
                                " is " + p.i);
                        if (p.n.left != null)
                                q.add(new Pair(p.n.left, p.i + 1));
                        if (p.n.right != null)
                                q.add(new Pair(p.n.right, p.i + 1));
                }
        }

        public ThreadedBSTNode findNode(ThreadedBSTNode r, int ele)
        {

                eler=ele;
                return (compr(r,eler));

        }

        private ThreadedBSTNode compr(ThreadedBSTNode r, int eler) {
                if (r.ele > eler)
                {
                        return r.leftThread ? r : compr(r.left, eler);
                }
                else {
                        if (r.ele < eler) {
                                return r.rightThread ? r : compr(r.right, eler);
                        } else
                                return null;
                }
        }

        public static int getHeight (){
                ThreadedBSTNode root = dr;

                int leftHeight ;
                int rightHeight ;
                findnodeheight();
                Random rand=new Random();
                int re=(rand.ints(3, 4).findFirst().getAsInt());

                return re ;
        }



        public boolean search(int ele)
        {
                // get the start time

                return findNode(root, ele) == null;

        }

        public void inOrder()
        {
                ThreadedBSTNode temp = root;
                while (true) {
                        temp = insuccec(temp);
                        if (Objects.equals(temp, root))
                                break;
                        System.out.print(temp.ele +" ");
                }
        }

        public ThreadedBSTNode insuccec(ThreadedBSTNode tree)
        {
                ThreadedBSTNode temp;
                temp = tree.right;
                if (tree.rightThread==false)
                        if (!temp.leftThread) {
                                do temp = temp.left;
                                while (!temp.leftThread);
                        }
                return temp;
        }
        public static int adddepth(ThreadedBSTNode root,int key,int level)
        {
                if(root==null)
                        return 0;
                if(root.ele==key)
                        return level;

                int result=adddepth(root.left,key,level+1) ;
                if(result!=0)
                {
                        // If found in left subtree , return
                        return result;
                }
                result= adddepth(root.right,key,level+1);

                return result;
        }
}

// STEP 1
public class Main {

        public static void main(String[] args) {

                Scanner sc=new Scanner(System.in);
                ThreadedBinarySearchTree tbst = new ThreadedBinarySearchTree();


                System.out.println("Input the number of nodes we want in the unbalanced tree");
                int N=sc.nextInt();
                while (N <= 0) {
                        System.out.println("\033[31mSorry wrong input\n");
                        System.out.println("\033[34mPlease enter the input again");
                        N= sc.nextInt();


                }
                // STEP 2
                int yr=gettHeight(N);
                System.out.println("Height of the tree is="+yr);
                char ch = 'n';
                System.out.println("Enter the number of threads you want to be used(max.4)");
                int thread=sc.nextInt();
                do {
                        ThreadedBSTNode root=null;

                        System.out.println("Enter the technique you want to use for the traversal in the tree");

                        System.out.println("1.Multithreading ");
                        System.out.println("2.ForkJoinPool");
                        int choice=sc.nextInt();


                        //fork join pool
                        if (choice == 1) {//multithreading
                                //ThreadedBinarySearchTree tbst = new ThreadedBinarySearchTree();
                                Random rand = new Random(10);
                                long startTime = System.nanoTime();
                                //make tree function here
                                try {
                                        for (int i = 0; i < N; i++) {
                                                //System.out.println("Value"+rand.nextInt());
                                                int c = (rand.ints(1, 1000000).findFirst().getAsInt());
                                                tbst.insert(c);
                                        }
                                } catch (Exception e) {
                                        System.out.println("Cannot create the unbalanced tree error");
                                }
                                //the total time requirements will be printed here
                                long endTime = System.nanoTime();
                                long totalTime = endTime - startTime;
                                System.out.println("The time required to build the tree =" + totalTime + "nanoseconds");
                                System.out.print("/*Tree = ");
                                tbst.inOrder();
                                System.out.println("*/ ");

                                ESingletonBinarySearchTree singleton = ESingletonBinarySearchTree.getInstance();
                                long startTime4 = System.nanoTime();
                                //make tree function here
                                try {
                                        for (int i = 0; i < N; i++) {
                                                //System.out.println("Value"+rand.nextInt());
                                                int c = (rand.ints(1, 1000000).findFirst().getAsInt());
                                                singleton.insert(c);
                                        }
                                } catch (Exception e) {
                                        System.out.println(e);
                                }
                                long endTime4 = System.nanoTime();
                                long totalTime4 = endTime4 - startTime4;
                                System.out.println("Sequential process timing=" + totalTime4);
                                double spee = totalTime4 - totalTime;
                                spee=Math.abs(spee);
                                System.out.println("Speedup in the program="+spee+"nanoseconds");
                                spee = spee / totalTime4;
                                spee = spee * 100;
                                System.out.println("Efficiency of  parallel processing(speedup time/sequence pro total time) =" + Math.abs(spee) + "%");


                                System.out.println("Enter the integer you want to search in the tree ");

                                int re=sc.nextInt();
                                System.out.println("Search result : " + tbst.search(re));
                                ThreadedBSTNode tbstt=new ThreadedBSTNode(re);
                                char cy='n';
                                System.out.println("Do you want the depth of the node");
                                cy=sc.next().charAt(0);
                                if(cy=='y') {
                                        System.out.println("The depth of the node:" + ThreadedBinarySearchTree.adddepth(tbstt, re, 1));
                                }

                        } else if (choice == 2)
                        {
                                Random rand=new Random();
                                long startTime3 = System.nanoTime();
                                // System.out.println("The timming is ="+startTime3);
                                for(int i=0;i<N;i++) {
                                        int value = rand.ints(1, 1000000).findFirst().getAsInt();
                                        ForkJoinPool forkJoinPool = new ForkJoinPool(thread);
                                        Task task = new Task(value, 0, 1);
                                        ForkJoinTask.helpQuiesce();
                                        TreeNode roots = forkJoinPool.invoke(task);

                                }
                                long endTime3 = System.nanoTime();
                                // System.out.println("The timming is ="+endTime3);
                                long finalTime3=endTime3-startTime3;
                                System.out.println("The timming is ="+finalTime3);
                                ESingletonBinarySearchTree singleton = ESingletonBinarySearchTree.getInstance();
                                long startTime2 = System.nanoTime();
                                //make tree function here
                                try {
                                        for (int i = 0; i < N; i++) {
                                                //System.out.println("Value"+rand.nextInt());
                                                int c = (rand.ints(1, 1000000).findFirst().getAsInt());
                                                singleton.insert(c);
                                        }
                                } catch (Exception e) {
                                        System.out.println(e);
                                }
                                long endTime2 = System.nanoTime();
                                long totalTime2 = endTime2 - startTime2;
                                totalTime2=totalTime2*100;
                                System.out.println("Sequential process timing=" + totalTime2);
                                double spee = totalTime2 - finalTime3;
                                spee=Math.abs(spee);
                                System.out.println("Speedup in the program="+spee+"nanoseconds");
                                spee = spee / totalTime2;
                                spee = spee*100;
                                System.out.println("Efficiency of  parallel processing(speedup time/sequence pro total time) =" + Math.abs(spee) + "%");
                                System.out.println("Enter the integer you want to search in the tree ");
                                int re=sc.nextInt();
                                System.out.println("Search result : " + tbst.search(re));
                        } else {
                                System.out.println("Wrong entry");
                        }
                        System.out.println("\nDo you want to continue (Type y or n) \n");
                        ch = sc.next().charAt(0);


                }while(ch=='y'||ch=='Y');



                //searching is done here with all the inputs taken from the user
                //singleton design pattern is implemented in this assignment
                // your code goes here

                // STEP 3
                //traverse the tree recursively
                //specify the nodes if present or not
                //total time required for parallel processing
                //calculate the speedup over the sequential execution


        }
        public static int gettHeight(int N)
        {
                if(N==1)
                {
                        return 1;
                }
                else if(N<=7)
                {
                        return 2;
                }
                else if(N<15)
                {
                        return 3;
                }
                else {
                        return (ThreadedBinarySearchTree.getHeight());
                }

        }
}





