public class ListeZaKol2 {
    public static void main(String[] args) {
        ListForPractice list = new ListForPractice();
        list.addOnStartWithoutList("Nez", "Nez nez nez");
        list.addOnStartWithoutList("Nez2", "Nez3 nez 4nez");
        list.addOnStartWithoutList("Nez3", "Nez 5353nez nez");

        list.addOnEndWithoutList("Nez001", "desc edesce da");
        list.addOnEndWithoutList("Nez002", "desc edesce da");
        list.addOnEndWithoutList("Nez003", "desc edesce da");


        list.addNodeToType("Nez001", "Node Type test test", "Node desc desc test");

        Svetovid.out.println(list);
        list.print();

    }
}

class ListForPractice {
    class LFPNode {
        String type;
        String desc;
        LFPNode next;

        LFPNode(String type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public String toString() {
            return "Type: " + type + " " + "Desc: " + desc;
        }
    }

    class FullLFPN {
        String fType;
        String fDesc;
        LFPNode first;
        FullLFPN next;

        FullLFPN(String fType, String fDesc) {
            this.fType = fType;
            this.fDesc = fDesc;
            this.first = null;
        }

        public String toString() {
            return "Type: " + fType + " " + "Desc: " + fDesc + " " + first;
        }

        public void addOnStart(String type, String desc) {
            LFPNode node = new LFPNode(type, desc);
            node.next = first;
            first = node;
        }
    }

    FullLFPN first;

    public ListForPractice() {
        first = null;
    }

    public void addOnEndWithoutList(String type, String desc) {
        if (first == null) {
            first = new FullLFPN(type, desc);
        } else {
            FullLFPN tmp = first;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = new FullLFPN(type, desc);
        }
    }

    public void addOnStartWithoutList(String type, String desc) {
        if (first == null) {
            first = new FullLFPN(type, desc);
        } else {
            FullLFPN tmp = first;
            first = new FullLFPN(type, desc);
            first.next = tmp;
        }
    }

    public void addOnEnd(String type, String desc, LFPNode node) {
        if (first == null) {
            first = new FullLFPN(type, desc);
            first.first = node;
        } else {
            FullLFPN tmp = first;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = new FullLFPN(type, desc);
            tmp.next.first = node;
        }
    }

    public void addOnStart(String type, String desc, LFPNode node) {
        if (first == null) {
            first = new FullLFPN(type, desc);
            first.first = node;
        } else {
            FullLFPN tmp = first;
            first = new FullLFPN(type, desc);
            first.next = tmp;
            first.first = node;
        }
    }

    public void addNodeToType(String type, String typeNode, String descNode) {
        if(first == null)
            return;
        FullLFPN tmp = first;
        while (tmp != null) {
            if (tmp.fType.equals(type)) {
                if (tmp.first == null) {
                    tmp.first = new LFPNode(typeNode, descNode);
                } else {
                    LFPNode tmp2 = tmp.first;
                    while (tmp2.next != null) {
                        tmp2 = tmp2.next;
                    }
                    tmp2.next = new LFPNode(typeNode, descNode);
                }
            }
            tmp = tmp.next;
        }
    }

    public void print(){
        if(first == null){
            Svetovid.out.println("[Greska] Lista je prazna");
            return;
        }else {
            FullLFPN tmp = first;
            while(tmp != null){
                if(tmp.next != null){
                    Svetovid.out.println(tmp.toString() + " -> ");
                }else{
                    Svetovid.out.println(tmp.toString());
                }
                tmp = tmp.next;
            }  
        }
    }


    public String toString() {
        String rez = "ListForPractice [ ";
        FullLFPN tmp = first;
        while (tmp != null) {
            rez += tmp.toString() + " ";
            tmp = tmp.next;
        }
        return rez + " ]";
    }
}
