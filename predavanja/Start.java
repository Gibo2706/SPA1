public class Start {
    public static void main(String[] args) {
        Jpl list = new Jpl();
        list.insertFirst("elem");
        list.printFirstToLast();
    }
}

class Jpl {
    class SSLNode {
        Object element;
        SSLNode succ;

        public SSLNode (Object elem, SSLNode succ){
            this.element = elem;
            this.succ = succ;
        }
    }
    SSLNode firNode;
    public Jpl(){
        this.firNode = null;
    }
    
    public void insertFirst(Object elem){
        SSLNode ins = new SSLNode(elem, null);
        ins.succ = this.firNode;
        this.firNode = ins;
    }

    public void insert(Object elem, SSLNode pred){
        SSLNode ins = new SSLNode(elem, null);
        if(pred == null){
            ins.succ = this.firNode;
            this.firNode = ins;
        }else{
            ins.succ = pred.succ;
            pred.succ = ins;
        }
    }
    public void printFirstToLast(){
        SSLNode curr = this.firNode;
        while(curr != null){
            System.out.println(curr.element);
            curr = curr.succ;
        }
    }
}


