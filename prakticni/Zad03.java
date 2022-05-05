public class Zad03 {
    public static void main(String[] args) {
        Playground list = new Playground();

        // Boxes
        list.addBox(0, "Blue");
        list.addBox(1, "Red");
        list.addBox(2, "Green");
        list.addBox(3, "Pink");

        // Test if there is same id
        list.addBox(0, "Blue");

        // Toys box 0
        list.addToy(0, "wooden", "desc for this toy");
        list.addToy(0, "metal", "desc for this toy");
        list.addToy(0, "wooden", "desc for this toy");
        list.addToy(0, "plastic", "desc for this toy");
        list.addToy(0, "wooden", "desc for this toy");

        // Toys box 1
        list.addToy(1, "wooden", "desc for this toy");
        list.addToy(1, "plastic", "desc for this toy");
        list.addToy(1, "plastic", "desc for this toy");
        list.addToy(1, "plastic", "desc for this toy");
        list.addToy(1, "wooden", "desc for this toy");

        // Toys box 2
        list.addToy(2, "wooden", "desc for this toy");
        list.addToy(2, "wooden", "desc for this toy");
        list.addToy(2, "wooden", "desc for this toy");
        list.addToy(2, "wooden", "desc for this toy");
        list.addToy(2, "wooden", "desc for this toy");

        // Toys box 3
        list.addToy(3, "metal", "desc for this toy");
        list.addToy(3, "wooden", "desc for this toy");
        list.addToy(3, "wooden", "desc for this toy");

        System.out.println(list);

        System.out.println();

        // Test for printing box with the most toys of the given type
        list.moveTypeToLastBox("wooden");
        System.out.println();

        Svetovid.out.println("-----------------------------");
        // Test for moving toys
        list.moveTypeToLastBox("metal");
        System.out.println(list);

        Svetovid.out.println("-----------------------------");
        System.out.println();

    }
}

class Playground {
    class Toy {
        String type, desc;
        Toy link;

        public Toy(String type, String desc) {
            this.type = type;
            this.desc = desc;
            this.link = null;
        }

        public String toString() {
            return "Type: " + type ;//+ " Desc: " + desc;
        }
    }

    class Box {
        int id;
        String color;
        Toy first;
        Box link;

        public Box(int id, String color) {
            this.id = id;
            this.color = color;
            this.first = null;
            this.link = null;
        }

        public String toString() {
            String rez = "Box: " + id + " color: " + color + " Toys: [ ";
            if (first != null) {
                Toy tmp = first;
                while (tmp.link != null) {
                    rez += tmp + ", ";
                    tmp = tmp.link;
                }
                rez += tmp + " ]";
            } else {
                rez += "Empty! ]";
            }
            return rez;
        }
    }

    Box first;

    public Playground() {
        this.first = null;
    }

    private Boolean isThereBox(int id) {
        Box tmp = first;
        while (tmp != null) {
            if (tmp.id == id)
                return true;
            tmp = tmp.link;
        }
        return false;
    }

    public void addBox(int id, String color) {
        if (first == null || first.id > id) {
            Box nw = new Box(id, color);
            nw.link = first;
            first = nw;
        } else {
            if (!isThereBox(id)) {
                Box tmp = first;
                while (tmp.link != null && tmp.id < id) {
                    tmp = tmp.link;
                }
                Box nw = new Box(id, color);
                nw.link = tmp.link;
                tmp.link = nw;
            } else {
                System.out.println("[Error] - There is no box with that id! ");
            }
        }
    }

    public void addToy(int id, String type, String desc) {
        Box tmp = first;
        int there = 0;
        while (tmp != null) {
            if (tmp.id == id) {
                if (tmp.first == null || tmp.first.type.compareTo(type) > 0) {
                    Toy nw = new Toy(type, desc); // nw as new
                    nw.link = tmp.first;
                    tmp.first = nw;
                } else {
                    Toy hel = tmp.first; // hel as a helper
                    while (hel.link != null && hel.link.type.compareTo(type) < 0) {
                        hel = hel.link;
                    }
                    Toy nw = new Toy(type, desc);
                    nw.link = hel.link;
                    hel.link = nw;
                }
                there++;
            }
            tmp = tmp.link;
        }
        if (!(there > 0)) {
            System.out.println("[Error] - There is no box with that id! ");
        }
    }

    public void printBoxWithMostToysType(String type) {
        if (first == null) {
            System.out.println("Box list is empty.");
            return;
        }
        Box tmp = first;
        int max = 0;
        Box pom = null;
        while (tmp != null) {
            int counter = 0;
            if (tmp.first != null) {
                Toy hel = tmp.first;
                while (hel != null) {
                    if (hel.type.equalsIgnoreCase(type))
                        counter++;
                    hel = hel.link;
                }
                if (counter > max) {
                    max = counter;
                    pom = tmp;
                }
            }
            tmp = tmp.link;
        }
        System.out.println("Box with the most toys of the given type is " + pom);
    }

    public void moveAllGivenToysToLastBox(String type) {
        if (first != null) {
            Box last = first, tmp = first;
            while (tmp != null) {
                last = tmp;
                tmp = tmp.link;
            }
            tmp = first;
            while (tmp != last) {
                if (tmp.first != null) {
                    Toy tek = tmp.first, post = tmp.first;
                    while (tek != null) {
                        post = tek;
                        if (tek.type.equalsIgnoreCase(type)) {
                            if (last.first != null) {
                                tek.link = last.first.link;
                                last.first = tek;
                                tek = post.link;
                            } else {
                                last.first = tek;
                                tek.link = null;
                                last.first.link = tek.link;
                                tek = post.link;
                            }
                        }
                        tek = tek.link;
                    }
                }
                tmp = tmp.link;
            }
        }
    }

    // Help by github/vjxdev
    public void moveTypeToLastBox(String type) {
        if (first == null || first.link == null) {
            return;
        }
        Box lastBox = first;
        while (lastBox.link != null) {
            lastBox = lastBox.link;
        }

        Box currBox = first;
        // Prolazak kroz sve kutije
        while (currBox.link != null) {
            // Prebacivanje sa pocetka
            while (currBox.first != null && currBox.first.type.equals(type)) {
                Toy toyToMove = currBox.first;
                currBox.first = currBox.first.link;

                toyToMove.link = lastBox.first;
                lastBox.first = toyToMove;
            }
            // Prebacivanje ostatka
            if (currBox.first != null) {
                Toy curr = currBox.first, prev;
                while (curr.link != null) {
                    prev = curr;
                    curr = curr.link;
                    if (curr.type.equals(type)) {
                        prev.link = curr.link;


                        curr.link = lastBox.first;
                        lastBox.first = curr;

                        curr = prev;
                    }
                }
            }

            currBox = currBox.link;
        }
    }

    public String toString() {
        String rez = "Playground [ ";
        if (first != null) {
            Box tmp = first;
            while (tmp.link != null) {
                rez += tmp + ", ";
                tmp = tmp.link;
            }
            rez += tmp + " ]";
        } else {
            rez += "Empty! ]";
        }

        return rez;
    }
}
