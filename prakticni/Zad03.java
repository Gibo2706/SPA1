public class Zad03 {
    public static void main(String[] args) {
        Igraonica igraonica = new Igraonica();
        // Test igraonica class
        // insert boxes in igraonica, insert toys in boxes, print igraonica
        // id: 1,2,3,4,...,n
        // box: box1, box2, box3, box4,...,boxn
        // toy: toy1, toy2, toy3, toy4,...,toyn
        // type: wooden, plastic, metal,
        // color: red, green, blue,
        // desc: description1, description2, description3, description4,...,descriptionn
        // n = 10
        igraonica.insertBox(1, "red");
        igraonica.insertBox(2, "green");
        igraonica.insertBox(4, "red");
        igraonica.insertBox(5, "green");

        igraonica.insertToy(1, "1wooden", "description1");
        igraonica.insertToy(2, "2plastic", "description2");
        igraonica.insertToy(4, "3metal", "description4");
        igraonica.insertToy(5, "1plastic", "description5");
        igraonica.insertToy(1, "5wooden", "description6");
        igraonica.insertToy(1, "9plastic", "description7");
        igraonica.insertToy(1, "5metal", "description8");
        igraonica.insertToy(1, "1metal", "description9");
        igraonica.insertToy(1, "2metal", "description9");
        igraonica.insertToy(1, "3metal", "description9");
        igraonica.insertToy(1, "3metal", "description9");
        igraonica.insertToy(1, "2metal", "description9");
        igraonica.insertToy(1, "1metal", "description9");
        igraonica.insertToy(1, "9metal", "description9");

        // print igraonica
        System.out.println(igraonica);

        // print box with the most toys of given type
        System.out.println("Box with the most toys of given type:");
        igraonica.printBoxWithMostToys("wooden");

        igraonica.moveToysToLastBox();
        System.out.println(igraonica);

    }
}

class Igraonica {
    class Toy{
        String type;
        String desc;
        Toy next;

        Toy(String type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public String toString() {
            return type + " " + desc;
        }
    }

    class ToysList{
        Toy first;

        ToysList() {
            this.first = null;
        }

        // insert toy by type
        public void insertToy(String type, String desc) {
            Toy novi = new Toy(type, desc);
            novi.next = this.first;
            this.first = novi;
        }

        // insert toy and sort by type
        public void insertToyAndSort(String type, String desc) {
            Toy novi = new Toy(type, desc);
            Toy temp = this.first;
            if (this.first == null) {
                this.first = novi;
                return;
            }
            if (this.first.type.compareTo(type) > 0) {
                novi.next = this.first;
                this.first = novi;
                return;
            }
            while (temp.next != null) {
                if (temp.next.type.compareTo(type) > 0) {
                    novi.next = temp.next;
                    temp.next = novi;
                    return;
                }
                temp = temp.next;
            }
            temp.next = novi;
        }


        public String isThereToy(String type) {
            Toy cur = this.first;
            while (cur != null) {
                if (cur.type.equalsIgnoreCase(type)) {
                    return cur.type;
                }
                cur = cur.next;
            }
            return null;
        }

        public String toString() {
            String rez = "Igracke [ ";
            Toy cur = this.first;
            while (cur != null) {
                rez += cur.toString() + " ";
                cur = cur.next;
            }
            rez += "]";
            return rez;
        }

        // sort toys by type
        public void sortToys() {
            Toy cur = this.first;
            Toy next = cur.next;
            Toy prev = null;
            while (next != null) {
                if (cur.type.compareTo(next.type) > 0) {
                    if (prev == null) {
                        this.first = next;
                    } else {
                        prev.next = next;
                    }
                    Toy tmp = cur;
                    cur = next;
                    next = tmp;
                    tmp.next = cur.next;
                    cur.next = tmp;
                    prev = cur;
                } else {
                    prev = cur;
                    cur = next;
                    next = cur.next;
                }
            }
        }

    }

    class Box{
        int id;
        String color;
        ToysList toys;
        Box next;

        Box(int id, String color) {
            this.id = id;
            this.color = color;
            this.toys = new ToysList();
        }

        public String toString() {
            return "Box " + id + " " + color + " " + toys.toString();
        }

        public void insertToy(String type, String desc) {
            toys.insertToyAndSort(type, desc);
        }

        public String isThereToy(String type) {
            return toys.isThereToy(type);
        }

    }

    class BoxesList{
        Box first;

        BoxesList() {
            this.first = null;
        }

        public void insertBox(int id, String color) {
            Box cur = this.first;
            while (cur != null) {
                if (cur.id == id) {
                    Svetovid.out.println("Vec postoji box!");
                    return;
                }
                cur = cur.next;
            }
            Box novi = new Box(id, color);
            novi.next = this.first;
            this.first = novi;
        }

        public String isThereBox(int id) {
            Box cur = this.first;
            while (cur != null) {
                if (cur.id == id) {
                    return cur.color;
                }
                cur = cur.next;
            }
            return null;
        }
        
        public void insertToy(int id, String type, String desc) {
            Box cur = this.first;
            while (cur != null) {
                if (cur.id == id) {
                    cur.insertToy(type, desc);
                    return;
                }
                cur = cur.next;
            }
            Svetovid.out.println("Nema boxa sa tim id-om!");
        }


        public String isThereToy(int id, String type) {
            Box cur = this.first;
            while (cur != null) {
                if (cur.id == id) {
                    return cur.isThereToy(type);
                }
                cur = cur.next;
            }
            return null;
        }

        public String toString() {
            String rez = "Boxes [ ";
            Box cur = this.first;
            while (cur != null) {
                rez += cur.toString() + " ";
                cur = cur.next;
            }
            rez += " ]";
            return rez;
        }
        
    }

    BoxesList boxes;

    public Igraonica() {
        this.boxes = new BoxesList();
    }

    public void insertBox(int id, String color) {
        boxes.insertBox(id, color);
    }

    public String isThereBox(int id) {
        return boxes.isThereBox(id);
    }

    public void insertToy(int id, String type, String desc) {
        boxes.insertToy(id, type, desc);
    }

    public String isThereToy(int id, String type) {
        return boxes.isThereToy(id, type);
    }

    // print box with the most of toys given type
    public void printBoxWithMostToys(String type) {
        Box cur = boxes.first;
        int max = 0;
        while (cur != null) {
            int count = 0;
            Toy toys = cur.toys.first;
            while (toys != null) {
                if (cur.toys.isThereToy(type) != null) {
                    count++;
                }
                toys = toys.next;
            }
            if (count > max) {
                max = count;
                Svetovid.out.println("Box " + cur.id + " " + cur.color);
            }
            cur = cur.next;
        }
    }

    //Move all toys from all the boxes to the last box and delete all boxes, except the last one
    public void moveToysToLastBox() {
        Box cur = boxes.first;
        Box last = null;
        while (cur != null) {
            last = cur;
            cur = cur.next;
        }
        cur = boxes.first;
        while (cur != null) {
            Toy toys = cur.toys.first;
            while (toys != null) {
                last.toys.insertToy(cur.toys.first.type, cur.toys.first.desc);
                toys = toys.next;
            }
            cur = cur.next;
        }
        cur = boxes.first;
        while (cur != null) {
            Box tmp = cur.next;
            cur.next = null;
            cur = tmp;
        }
    }

    public String toString() {
        return boxes.toString();
    }

}
